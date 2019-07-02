package core;

import be.he2b.esi.dto.LinkDto;
import be.he2b.esi.dto.MemberDto;
import be.he2b.esi.exception.DtoException;
import be.he2b.esi.exception.NetworkException;
import be.he2b.esi.network.BDFacade;
import be.he2b.esi.network.BDModel;
import static core.Utility.readDB;
import static core.Utility.writeToFile;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Network, the model face
 *
 * @author Bryan & Dylan
 */
public class Network extends AbstractNetwork {//TODO javadoc

    /**
     * Represents all registered members
     */
    private final Map<Integer, Node> network;

    /**
     * Person who is currently connected.
     */
    private Node connected;

    private final BDFacade bd;

    /**
     * Consctuctor. Create a plateforme of the Social Network
     */
    public Network() {
        //network = (Map) readFile();
        //network = new HashMap<>();
        bd = new BDModel();
        network = (Map) readDB(bd);
        System.out.println("test");
    }

    /**
     * Connect two people in the social network.
     *
     * @param nodeN who send the a demand
     * @param nodeM who receive the demand
     * @param linkType type of link for the connection
     */
    @Override
    public void addLink(final Node nodeN, final Node nodeM, final LinkType linkType) {

        checkNode(nodeN, nodeM);
        checkExistantLink(nodeN, nodeM, linkType);

        switch (linkType) {
            case FRIENDS:
                final Link friendLink = new Link(LinkType.FRIENDS, nodeN, nodeM);
                nodeN.addLinks(friendLink);
                nodeM.addLinks(friendLink);
                break;

            case MATCH:
                //check si il y a un lien potentiel avant ! -check dans accept
                Link matchLink;
                if (nodeN == connected) {
                    matchLink = new Link(linkType, nodeN, nodeM);
                } else {
                    matchLink = new Link(linkType, nodeM, nodeN);
                }
                nodeN.addLinks(matchLink);
                nodeM.addLinks(matchLink);
                break;
            case MATCH_POTENTIAL:
                final Link potentialLink = new Link(linkType, nodeN, nodeM);
                nodeN.addLinks(potentialLink);
                nodeM.addLinks(potentialLink);
                break;
            default:
                //REFUSED
                final Link refuse = new Link(linkType, nodeN, nodeM);
                nodeN.addLinks(refuse);
                nodeM.addLinks(refuse);
                break;
        }

        try {
            bd.addLink(new LinkDto(linkType.toString(), 0, nodeN.getNodeID(), nodeM.getNodeID(), false));
        } catch (DtoException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NetworkException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }

        writeToFile(network); //TODO check message

    }

    /**
     * Creat a new profil in the social Network
     *
     * @param info all information to add on the currently profil
     */
    @Override
    public void createProfil(final Information... info) {
        final Profil profil = new Profil(network.size(), info);
        network.put(network.size(), profil);

        try {
            bd.addMember(new MemberDto(profil.getInformation().toArray(new Information[profil.getInformation().size()])));
        } catch (DtoException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NetworkException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }

        writeToFile(network);
    }

    /**
     * Add more information later in a profil.
     *
     * @param profil profil to add information.
     * @param info all information to add.
     */
    @Override
    public void addProfilInfo(final Profil profil, final Information... info) {
        if (!isRegister(profil)) {
            throw new IllegalArgumentException("This profil don't exist in the network");
        }
        profil.addInformation(info);
    }

    /**
     * Accept a demand of a another member.
     *
     * @param nodeN member to accept the demand
     * @param nodeM member who send the demand
     * @param linkType type of acceptation
     */
    @Override
    public void acceptLink(final Node nodeN, final Node nodeM, final LinkType linkType) {

        checkNode(nodeN, nodeM);
        //-- pour accepté un lien il faut qu'il existe ! -- rechercher le lien
        final Link linkToAccept = findLink(nodeN, nodeM, linkType);
        if (linkToAccept != null) {
            switch (linkType) {

                case MATCH_POTENTIAL:
                    //créer un lien match à false
                    deleteLink(nodeN, nodeM);
                    addLink(nodeN, nodeM, LinkType.MATCH);

                    break;

                case FRIENDS:
                    try {
                        bd.updateLink(new LinkDto(linkType.toString(), 0, nodeM.getNodeID(), nodeN.getNodeID(), false));
                    } catch (DtoException ex) {
                        Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NetworkException ex) {
                        Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case MATCH:
                    //mettre le link [friends|match] a true 
                    //-- rechercher les links false 
                    //-- check si on est reicever
                    linkToAccept.setConfirmed();

                    try {
                        bd.updateLink(new LinkDto(linkType.toString(), 0, nodeN.getNodeID(), nodeM.getNodeID(), false));
                    } catch (DtoException ex) {
                        Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NetworkException ex) {
                        Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    //REFUSED ne rien faire dans ce cas 
                    break;
            }
        }else{
            throw new IllegalArgumentException("Can't accept a link with not sender");
        }
        writeToFile(network); //TODO check message
    }

    /**
     * Decline a demand of a another member.
     *
     * @param nodeN member to decline the demand
     * @param nodeM member who send the demand
     * @param linkType type of link
     */
    @Override
    public void declineLink(final Node nodeN, final Node nodeM, final LinkType linkType) {

        checkNode(nodeN, nodeM);
        //-- pour décliné un lien il faut qu'il existe ! -- rechercher le lien
        final Link linkToDicline = findLink(nodeN, nodeM, linkType);
        if (linkToDicline != null) {
            switch (linkType) {
                case MATCH_POTENTIAL:
                case MATCH:
                    //delete le lien && on crée un lien refuse
                    deleteLink(nodeN, nodeM);
                    addLink(nodeN, nodeM, LinkType.REFUSED);

                    try {
                        bd.addLink(new LinkDto(linkType.toString(), 0, nodeN.getNodeID(), nodeM.getNodeID(), false));
                    } catch (DtoException ex) {
                        Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NetworkException ex) {
                        Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case FRIENDS:
                    //delete le lien
                    deleteLink(nodeN, nodeM);
                    break;
                default:
                    //REFUSED ne rien faire dans ce cas
                    break;
            }
        }
        writeToFile(network); //TODO check message
    }

    /**
     * Delete a relation with a member in the social Network
     *
     * @param nodeN who send the demand
     * @param nodeM person who will be deleted
     */
    @Override
    public void deleteLink(final Node nodeN, final Node nodeM) {

        checkNode(nodeN, nodeM);

        for (final Link link : nodeN.getLinks()) {
            if (link.getNodeSender() == nodeM && link.getNodeReicever() == nodeN
                    || link.getNodeSender() == nodeN && link.getNodeReicever() == nodeM) {
                nodeN.deleteLink(link);
                nodeM.deleteLink(link);
                try {
                    bd.removeLink(new LinkDto(link.getType().toString(), 0, link.getSenderID(), link.getReiceverID(), link.isConfirmed()));
                } catch (DtoException ex) {
                    Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NetworkException ex) {
                    Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
        writeToFile(network); //TODO check message
    }

    /**
     * Make connection with all member in the social network.
     */
    @Override
    public void matchSuggestion() {

        final List<Profil> potentialMatch = new ArrayList<>();
        final List<Profil> potentialMToAdd = new ArrayList<>();

        for (int i = 1; i <= network.size(); i++) {
            final Profil profil = (Profil) network.get(i);
            potentialMatch.clear();

            for (final Profil p : getProfil(profil, profil.getFreind())) {
                addcheckedFriendsProfil(profil, p, potentialMatch, potentialMatch);
            }

            potentialMToAdd.clear();
            potentialMToAdd.addAll(potentialMatch);

            while (!potentialMToAdd.isEmpty()) {
                potentialMToAdd.clear();
                for (final Profil p : potentialMatch) {
                    addcheckedFriendsProfil(profil, p, potentialMatch, potentialMToAdd);
                }
                for (final Profil p : potentialMToAdd) {
                    potentialMatch.add(p);
                }
            }

            removeBadProfil(potentialMatch, profil);

            addPotentialMatchLink(potentialMatch, profil);
        }

    }

    /**
     * Connect a member in the social network
     *
     * @param node
     * @param password
     */
    @Override
    public void connect(final String node, final String password) {
        boolean connect;
        connect = network.containsKey(Integer.parseInt(node));
        if (connect) {
            connected = network.get(Integer.parseInt(node));
        }
        setChanged();
        notifyObservers((boolean) connect);
    }

    /**
     * Get peroson connected
     *
     * @return person connected
     */
    @Override
    public Node getConnected() {
        return connected;
    }

    /**
     * Get plateform of the social network. Contains all member
     *
     * @return all member
     */
    @Override
    public Map getMap() {
        return network;
    }

    /**
     * Remove all bad profil who does not agree for a potential patch
     *
     * @param toRemove all profil to remove
     * @param removeFrom all profil to remove
     */
    private void removeBadProfil(final List<Profil> toRemove, final List<Profil> removeFrom) {
        for (final Profil p : toRemove) {
            removeFrom.remove(p);
        }
    }

    private void removeBadProfil(final List<Profil> potentialMatch, final Profil profil) {

        final List<Profil> toRemove = new ArrayList<>();
        final PreferenceInformation prefA = profil.getPreference();
        PreferenceInformation prefB;

        for (final Profil p : potentialMatch) {

            for (final Information info : p.getInformation()) {

                checkPreference(prefA, p, info, toRemove);
                if (info.getType() == InformationType.PREFERENCE) {
                    prefB = (PreferenceInformation) info;
                    if (prefB != null) {
                        checkPreference(p, profil.getInformation(), prefB, toRemove);
                    }
                }

            }

        }
        removeBadProfil(toRemove, potentialMatch);
    }

    /**
     * Check all preference of a member to determine the best suggestion of all
     * potential match
     *
     * @param pref Preference
     * @param profilB profil
     * @param info all infrmation of a member
     * @param toRemove
     */
    private void checkPreference(final PreferenceInformation pref,
            final Profil profilB, final Information info, final List<Profil> toRemove) {

        if (pref != null) {
            switch (info.getType()) {
                case BRITHDATE:
                    final BirthDateInformation birthDate = (BirthDateInformation) info;
                    if (pref != null && birthDate.getAge() > pref.getAgeMax()
                            || birthDate.getAge() < pref.getAgeMin()) {
                        toRemove.add(profilB);
                    }
                    break;
                case SEX:
                    final SexInformation sex = (SexInformation) info;
                    if (pref != null && sex.getSex() != pref.getSex()) {
                        toRemove.add(profilB);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Check all preference of a member to determine the best suggestion
     *
     * @param profil
     * @param infos
     * @param pref
     * @param toRemove
     */
    private void checkPreference(final Profil profil, final List<Information> infos,
            final PreferenceInformation pref, final List<Profil> toRemove) {

        for (final Information info : infos) {
            checkPreference(pref, profil, info, toRemove);
        }
    }

    /**
     * Add potential link for all profil who are in agree from all criteria
     *
     * @param potentialMatch all potential match
     * @param profil sender
     */
    private void addPotentialMatchLink(final List<Profil> potentialMatch, final Profil profil) {
        boolean contain = false;
        for (Profil p : potentialMatch) {
            for (final Link l : profil.getLinks()) {
                if (l.getType() == LinkType.MATCH
                        || l.getType() == LinkType.MATCH_POTENTIAL
                        || l.getType() == LinkType.REFUSED) {
                    contain = true;
                }
            }
            if (!contain) {
                addLink(profil, p, LinkType.MATCH_POTENTIAL);
            }
        }

    }

    /**
     * Check all friends of a profil
     *
     * @param profil
     * @param friend
     * @param potentialMatch
     * @param toAdd
     */
    private void addcheckedFriendsProfil(final Profil profil,
            final Profil friend, final List<Profil> potentialMatch, final List<Profil> toAdd) {

        for (final Profil p : getProfil(friend, friend.getFreind())) {
            if (!p.equals(profil)
                    && !potentialMatch.contains(p)
                    && !toAdd.contains(p)
                    && !getProfil(profil, profil.getFreind()).contains(p)) {
                toAdd.add(p);
            }
        }

    }

    /**
     *
     * @param profil
     * @param links
     * @return
     */
    private List<Profil> getProfil(final Profil profil, final List<Link> links) {
        final List<Profil> profils = new ArrayList<>();
        for (final Link l : links) {
            if (l.getSenderID() == profil.getNodeID()) {
                profils.add((Profil) l.getNodeReicever());
            } else {
                profils.add((Profil) l.getNodeSender());
            }
        }
        return profils;
    }

    /**
     * Cjeck if a node is register in the network
     *
     * @param node node to check
     * @return if a node is register in the social network
     */
    private boolean isRegister(final Node node) {
        return network.containsValue(node);
    }

    /**
     * Check if two node are register and differents
     *
     * @param nodeN node A
     * @param nodeM node B
     */
    private void checkNode(final Node nodeN, final Node nodeM) {

        if (!isRegister(nodeN) || !isRegister(nodeM)) {
            throw new IllegalArgumentException("Node is not register in the Application");
        }
        if (nodeN.equals(nodeM)) {
            throw new IllegalArgumentException("You can't to add yourself");
        }

    }

    /**
     *
     * @param nodeN
     * @param nodeM
     * @param linkType
     */
    private void checkExistantLink(final Node nodeN, final Node nodeM, final LinkType linkType) {

        List<Link> links;

        switch (linkType) {
            case FRIENDS:
                links = nodeN.getFreind();
                break;
            case MATCH:
                links = nodeN.getMatchs();
                break;
            case MATCH_POTENTIAL:
                links = nodeN.getPotentialMatchs();
                break;
            default:
                links = nodeN.getDeadLink();
                break;
        }
        for (final Link link : links) {
            if ((link.getNodeSender() == nodeN
                    && link.getNodeReicever() == nodeM)
                    || (link.getNodeSender() == nodeM
                    && link.getNodeReicever() == nodeN)) {
                throw new IllegalArgumentException(
                        "A request was already sent"
                );
            }
        }
    }

    private Link findLink(final List<Link> listToFind,
            final Boolean isMatchPotential, final Node nodeN, final Node nodeM) {

        Link goodLink = null;
        for (final Link link : listToFind) {//link.has(node) && equals
            if (link.getNodeSender() == nodeM && link.getNodeReicever() == nodeN
                    || (isMatchPotential && link.getNodeSender() == nodeN
                    && link.getNodeReicever() == nodeM)) {
                goodLink = link;
            }
            if (link.getType() == LinkType.MATCH && link.getNodeSender() == nodeN && link.getNodeReicever() == nodeM) {
                goodLink = link;
            }
        }
        return goodLink;
    }

    private Link findLink(final Node nodeN, final Node nodeM, final LinkType linkType) {

        List<Link> listToFind;
        Boolean ismatchPotential = false;

        switch (linkType) {
            case FRIENDS:
                listToFind = nodeN.getFreindWait();
                break;
            case MATCH_POTENTIAL:
                ismatchPotential = true;
                listToFind = nodeN.getPotentialMatchs();
                break;
            case MATCH:
                listToFind = nodeN.getMatchWait();
                break;
            default:
                //REFUSED rien faire !
                listToFind = new ArrayList<>();
                break;
        }

        return findLink(listToFind, ismatchPotential, nodeN, nodeM);
    }
}

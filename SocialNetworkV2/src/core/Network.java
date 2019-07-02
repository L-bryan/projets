package core;

import static core.Utility.readFile;
import static core.Utility.writeToFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Network implements NetworkIterface {

    private final Map<Integer, Node> network;

    public Network() throws IOException, FileNotFoundException, ClassNotFoundException {
        //network = (Map) readFile(); 
        network = new HashMap<>();
    }

    @Override
    public void addLink(Node n, Node m, LinkType l) {
        //test if n && m are register in the network OK
        if (!network.containsValue(n) || !network.containsValue(m)) {
            throw new IllegalArgumentException("Node is not register in the network");
        }
        //test if n && m are differents ok
        if (n == m) {
            throw new IllegalArgumentException("You can't to add yourself");
        }

        //test if n or m are not already sent a invitation
        for (Link link : n.getLinks()) {
            if (link.getType() != LinkType.MATCH_POTENTIAL && (link.getNodeSender() == n && link.getNodeReicever() == m || link.getNodeSender() == m && link.getNodeReicever() == n)) {
                throw new IllegalArgumentException("A request as already sent");
            }
        }

        if (l == LinkType.FRIENDS || l == LinkType.MATCH || l == LinkType.MATCH_POTENTIAL) {
            if (l == LinkType.MATCH) {
                for (Link link : n.getLinks()) {
                    if (link.getNodeSender() == n && link.getNodeReicever() == m) {
                        if (link.getType() == LinkType.MATCH_POTENTIAL) {
                            Link linkADD = new Link(l, n, m);
                            n.addLinks(linkADD);
                            m.addLinks(linkADD);
                        }
                        break;
                    }
                }
            } else {
                Link linkADD = new Link(l, n, m);
                n.addLinks(linkADD);
                m.addLinks(linkADD);
            }
        } else {
            throw new IllegalArgumentException("Link is not accepted for add a person");
        }

        try {
            writeToFile(network);
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void acceptLink(Node n, Node m) {
        if (!network.containsValue(n) || !network.containsValue(m)) {
            throw new IllegalArgumentException("Node is not register in the network");
        }
        if (n == m) {
            throw new IllegalArgumentException("You can't to add yourself");
        }

        for (Link link : n.getLinks()) {
            if ((link.getType() == LinkType.MATCH || link.getType() == LinkType.FRIENDS) && (link.getNodeSender() == m && link.getNodeReicever() == n)) {
                link.setConfirmed(true);
                break;
            }
        }

        try {
            writeToFile(network);
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void diclineLink(Node n, Node m) {
        if (!network.containsValue(n) || !network.containsValue(m)) {
            throw new IllegalArgumentException("Node is not register in the network");
        }
        if (n == m) {
            throw new IllegalArgumentException("You can't to add yourself");
        }

        for (Link link : n.getLinks()) {
            if ((link.getType() == LinkType.MATCH || link.getType() == LinkType.FRIENDS) && (link.getNodeSender() == m && link.getNodeReicever() == n)) {
                link.setType(LinkType.REFUSED);
                break;
            }
        }

        try {
            writeToFile(network);
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteLink(Node n, Node m) {
        //supprimer le lien et non le mettre en delete ! 
        if (!network.containsValue(n) || !network.containsValue(m)) {
            throw new IllegalArgumentException("Node is not register in the network");
        }
        if (n == m) {
            throw new IllegalArgumentException("You can't to add yourself");
        }

        for (Link link : n.getLinks()) {
            if ((link.getType() == LinkType.MATCH || link.getType() == LinkType.FRIENDS) && (link.getNodeSender() == m && link.getNodeReicever() == n)) {
                n.deleteLink(link);
                m.deleteLink(link);
                break;
            }
        }
        try {
            writeToFile(network);
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createProfil(Information... info) {
        Profil profil = new Profil(network.size(), info);
        network.put(network.size(), profil);
        try {
            writeToFile(network);
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addProfilInfo(Profil n, Information... info) {
        n.addInformation(info);
    }

    @Override
    public void matchSuggestion() {//je sais je dois le rendre plus modulable
        for (int i = 0; i < network.size(); i++) {
            Profil profil = (Profil) network.get(i);
            Preference pref = profil.getPreference();
            List<Link> links = profil.getLinks();
            List<Profil> potentialMatch = new ArrayList<>();
            List<Profil> tmp; //= new ArrayList<>(getProfil(profil,profil.getFreind()));

            for (Profil p : getProfil(profil, profil.getFreind())) {//pour chaque ami de A
                for (Profil pp : getProfil(p, p.getFreind())) {//pour chaque ami d'ami de A (ami de G,ami de B,ami de C)
                    if (pp != profil //n'est pas le profil A
                            && !potentialMatch.contains(pp) //n'est pas déjà présent
                            && !getProfil(profil, profil.getFreind()).contains(pp) // n'est pas un ami direct du profil A
                            ) {
                        potentialMatch.add(pp);
                    }
                }
            }

            tmp = new ArrayList<>(potentialMatch);
            while (!tmp.isEmpty()) {
                tmp.clear();
                for (Profil p : potentialMatch) {// F && D
                    for (Profil pp : getProfil(p, p.getFreind())) {
                        if (pp != profil //n'est pas le profil A
                                && !potentialMatch.contains(pp) //n'est pas déjà présent
                                && !getProfil(profil, profil.getFreind()).contains(pp) // n'est pas un ami direct du profil A
                                ) {
                            //potentialMatch.add(pp);
                            tmp.add(pp);
                        }
                    }
                }
                for (Profil p : tmp) {//anti concurrence
                    potentialMatch.add(p);
                }
            }//suite sur la totalité de match potential
            //1) vérifier avec les préférences du profile et retiré les inconvenant
            // si il a  des préférence
            List<Profil> toRemove = new ArrayList<>();
            if (pref != null) {
                for (Profil p : potentialMatch) {
                    for (Information info : p.getInformation()) {
                        if (info.getType() == InformationType.BRITHDATE) {
                            BirthDateInformation bd = (BirthDateInformation) info;
                            if (bd.getAge() > pref.getAge_max() || bd.getAge() < pref.getAge_min()) {
                                toRemove.add(p);
                            }
                        } else if (info.getType() == InformationType.SEX) {
                            SexInformation sex = (SexInformation) info;
                            if (sex.getSex() != pref.getSex().getSex()) {
                                toRemove.add(p);
                            }
                        }
                    }
                }
            }
            for (Profil p : toRemove) {
                potentialMatch.remove(p);
            }
            //2) ajouté un lien POTENTIAL_MATCH entre profil et les NODE de la liste
            for (Profil p : potentialMatch) {
                addLink(profil, p, LinkType.MATCH_POTENTIAL);//Dylan quesque tu as fais
            }

        }
    }

    @Override
    public Map getMap() {
        return this.network;
    }

    private List<Profil> getProfil(Profil profil, List<Link> links) {
        List<Profil> profils = new ArrayList<>();
        for (Link l : links) {
            if (l.getNodeSender().getId() == profil.getId()) {
                profils.add((Profil) l.getNodeReicever());
            } else {
                profils.add((Profil) l.getNodeSender());
            }
        }
        return profils;
    }

    //test parcours map
    public void printMap() {
        for (Map.Entry entry : network.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : "
                    + entry.getValue());
        }
    }
}

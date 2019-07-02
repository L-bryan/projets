package core;

import be.he2b.esi.dto.HobbieDto;
import be.he2b.esi.dto.LinkDto;
import be.he2b.esi.dto.MemberDto;
import be.he2b.esi.exception.NetworkException;
import be.he2b.esi.network.BDFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Utility for read and write in file and DB.
 *
 * @author Bryan et Dylan
 */
public class Utility {

    /**
     * Serializable an object WRITE
     *
     * @param map
     */
    public static void writeToFile(final Map<Integer, Node> map) {

        final URL url = Utility.class.getResource("/data/Person.dat");
        try {
            final File file = new File(url.toURI());
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(map);
        } catch (IOException | URISyntaxException e) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Serializable an object. READ
     *
     * @return
     */
    public static Object readFile() {
        final URL url = Utility.class.getResource("/data/Person.dat");
        try {
            final File file = new File(url.toURI());
            final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            final Object object = objectInputStream.readObject();
            return object;
        } catch (IOException | ClassNotFoundException | URISyntaxException e) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static Object readDB(BDFacade bd) {
        //BDFacade bd = new BDModel();
        try {
            List<MemberDto> membersDto = bd.getMembers();
            List<LinkDto> linksDto = bd.getLinks();
            List<HobbieDto> hobbiesDto = bd.getHobbies();

            Map<Integer, Profil> profils = new HashMap<>();
            List<Link> links = new ArrayList<>();

            //création des Profils
            for (MemberDto m : membersDto) {

                profils.put(m.getId(), new Profil(m.getId(),
                        new NameInformation(m.getNameInformation().getName(),
                                m.getNameInformation().getFirstname()),
                        new SexInformation(m.getSexInformation().getSex()),
                        new BirthDateInformation(m.getBirthDateInformation().getDate()),
                        new ContactInformation(m.getContactInformation().getTel(),
                                m.getContactInformation().getEmail()),
                        new PreferenceInformation(m.getPreferenceInformation().getSexInformation(),
                                m.getPreferenceInformation().getFriendDistance(),
                                 m.getPreferenceInformation().getAgeMin(),
                                m.getPreferenceInformation().getAgeMax())
                //trouvé un moyen de rendre PreferenceInformation null
                )
                );
            }

            //création des liens
            for (LinkDto l : linksDto) {
                LinkType lt;
                switch (l.getLinkType()) {
                    case "FRIENDS":
                        lt = LinkType.FRIENDS;
                        break;
                    case "MATCH":
                        lt = LinkType.MATCH;
                        break;
                    case "MATCH_POTENTIAL":
                        lt = LinkType.MATCH_POTENTIAL;
                        break;
                    default:
                        lt = LinkType.REFUSED;
                }
                links.add(
                        new Link(
                                lt,
                                profils.get(l.getSender()),
                                profils.get(l.getReceiver())
                        )
                );
                if (l.isConfirmed()) {
                    links.get(links.size() - 1).setConfirmed();
                }
            }

            //ajout des hobbie aux profils
            for (HobbieDto h : hobbiesDto) {
                profils.get(h.getIdMember())
                        .addInformation(new HobbieInformation(h.getName()));
            }

            //ajout des liens aux profils
            for (int i = 1; i <= profils.size(); ++i) {
                for (Link l : links) {
                    if (l.getReiceverID() == i || l.getSenderID() == i) {
                        profils.get(i).addLinks(l);
                    }
                }
            }
            return profils;

        } catch (NetworkException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new HashMap<>();
    }

    public static void writeToDB(final Map<Integer, Profil> map) {
        //CODE
    }
}

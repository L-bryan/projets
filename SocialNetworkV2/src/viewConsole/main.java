package viewConsole;

import core.BirthDateInformation;
import core.HobbieInformation;
import core.Information;
import core.Link;
import core.LinkType;
import core.NameInformation;
import core.Network;
import core.Node;
import core.Sex;
import core.SexInformation;
import java.io.FileNotFoundException;
import java.io.IOException;


public class main {
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Network net = new Network();
        
        Information info1 = new NameInformation("Levy", "Dylan");
        Information info2 = new SexInformation(Sex.MAN);
        Information info3 = new BirthDateInformation(9, 8, 1996);
        Information info4 = new HobbieInformation("Informatique");

        Information info5 = new NameInformation("Levy", "Sarah");
        Information info6 = new SexInformation(Sex.WOMAN);
        Information info7 = new BirthDateInformation(26, 03, 1996);


        net.createProfil(info1, info2, info3, info4);
        net.createProfil( info1, info2, info3, info4);
        net.createProfil(info1, info2, info3, info4);
        net.createProfil(info1, info2, info3, info4);
        net.createProfil( info1, info2, info3, info4);
        net.createProfil( info1, info2, info3, info4);        
        net.createProfil(info5, info6, info7);
        net.createProfil(info5, info6, info7);
        net.createProfil(info5, info6, info7);

        Node node0 = (Node) net.getMap().get(0);
        Node node1 = (Node) net.getMap().get(1);
        Node node2 = (Node) net.getMap().get(2);
        Node node3 = (Node) net.getMap().get(3);
        Node node4 = (Node) net.getMap().get(4);
        Node node5 = (Node) net.getMap().get(5);
        Node node6 = (Node) net.getMap().get(6);
        Node node7 = (Node) net.getMap().get(7);
        Node node8 = (Node) net.getMap().get(8);
        Node node9=null;
        
        
        
        //demande d'amis non accapté
        net.addLink(node0, node1, LinkType.FRIENDS);
        net.addLink(node0, node2, LinkType.FRIENDS);
        
        //demande amis accepté
        net.addLink(node1, node2, LinkType.FRIENDS);
        net.addLink(node2, node3, LinkType.FRIENDS);
        net.acceptLink(node2, node1);
        net.acceptLink(node3, node2);
        
        //demande de match non accepté
        Link test = new Link(LinkType.MATCH_POTENTIAL,node4,node5);
        Link test2 = new Link(LinkType.MATCH_POTENTIAL,node5,node6);
        
        node4.addLinks(test);
        node5.addLinks(test2);
        
        net.addLink(node4, node5, LinkType.MATCH);
        net.addLink(node5, node6, LinkType.MATCH);
        
        //demande match accepté
        Link test3 = new Link(LinkType.MATCH_POTENTIAL,node7,node8);
        node7.addLinks(test3);
        
        net.addLink(node7, node8, LinkType.MATCH);
        net.acceptLink(node8, node7);

        //decline friend
        net.diclineLink(node2, node0);
        net.matchSuggestion();
 
        Console console = new Console();
        //network.matchSuggestion(null);
        console.start(net);
    }
    
}

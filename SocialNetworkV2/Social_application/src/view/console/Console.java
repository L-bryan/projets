package view.console;

import core.Link;
import core.LinkType;
import core.Network;
import core.Node;
import core.Profil;
import java.util.ArrayList;
import java.util.List;

public class Console {

    public void start(Network network) {
        System.out.println("Welcome to the SocialNetwork");

        System.out.println("FRIENDS");
        show(network, LinkType.FRIENDS, false);
        
        System.out.println("FRIENDS REQUESTS");
        show(network, LinkType.FRIENDS, true);

        System.out.println("MATCH");
        show(network, LinkType.MATCH, false);
        
        System.out.println("MATCH REQUESTS");
        show(network, LinkType.MATCH, true);
        
        System.out.println("MATCH POTENTIAL");
        show(network, LinkType.MATCH_POTENTIAL, true);
        
        System.out.println("REFUSED");
        show(network, LinkType.REFUSED, true);
    }

    public void show(Network network, LinkType link, boolean requests) {
        /*System.out.print("     ");
        for (int i = 0; i < network.getMap().size(); i++) {
            System.out.print(i + "  ");
        }
        System.out.println("");
        for (int i = 0; i < network.getMap().size(); i++) {
            System.out.print("-----");
        }
        System.out.println("");
        for (int i = 0; i < network.getMap().size(); i++) {
            Node p = (Node) network.getMap().get(i);
            List<Node> l = null;
            
            switch(link){
                case FRIENDS:
                    l = getNode( p,p.getLinks2(link, requests));
                    break;
                case MATCH:
                    l = getNode( p,p.getLinks2(link, requests));
                    break;
                case MATCH_POTENTIAL:
                    l = getNode( p,p.getLinks2(link, requests));
                    break;
                case REFUSED:
                    l = getNode( p,p.getLinks2(link, requests));

            }
        if(l != null){
            System.out.print(i + "-> ");
        for (int j = 0; j < network.getMap().size(); j++) {
            Node p2 = (Node) network.getMap().get(j);
            if (l.contains(p2)) {
                System.out.print(" 1 ");
            } else if (p2 == p) {
                System.out.print(" - ");
            } else {
                System.out.print(" 0 ");
            }
        }
        System.out.println("");
            
        }  
        
    }*/
}

private List<Node> getNode(Node node,List<Link> links){
        List<Node> nodes = new ArrayList<>();
        for(Link l :links){
            if(l.getNodeSender().getNodeID() == node.getNodeID()){
                nodes.add((Profil) l.getNodeReicever());
            }else{
                nodes.add((Profil) l.getNodeSender());
            }
        }
        return nodes;
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fxml;

import be.he2b.components.CarouselControl;
import controller.AbstractController;
import core.BirthDateInformation;
import core.HobbieInformation;
import core.Information;
import core.Link;
import core.NameInformation;
import core.Profil;
import core.SexInformation;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dylanlevymorini
 */
public class HomeView extends Parent {

    /**
     * Controller
     */
    private final AbstractController controller;
    /**
     * all match potential to display
     */
    private List<Information> match;
    /**
     * Profil of person who is the sender
     */
    private Link link;
    
    /**
     * Components, Carousel. Display all match with details
     */
    @FXML
    private CarouselControl carousel;
    
    /**
     * To display all match
     */
    @FXML
    private Button matchs;
    /**
     * To display all friend
     */
    @FXML
    private Button friends;
    
    /**
     * Use for add a friend
     */
    @FXML
    private Button addfriend;
    
    /**
     * Number of node of a person for add in friends
     */
    private @FXML TextField textaddfriend;
    
    

    /**
     * Hobbies
     */
    final private ObservableList<String> hobbies;

    /**
     * Constructor
     * @param controller 
     */
    public HomeView(AbstractController controller) {
        this.controller = controller;
        hobbies = FXCollections.observableArrayList();
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/homeView.fxml"));
            loader.setController(this);
            final AnchorPane pane = (AnchorPane) loader.load();
            getChildren().add(pane);

        } catch (IOException ex) {
            Logger.getLogger(FxmlView.class.getName()).log(Level.SEVERE, "loader.load() failed", ex);
        }
        
        
        

        carousel.accept(new EventHandler<ActionEvent>() {
            
            /**
             * Accept a match
             * @param accept 
             */
            @Override
            public void handle(final ActionEvent accept) {
                controller.acceptMatch(link);
                setInfoMatch();
                
            }
        });

        carousel.decline(new EventHandler<ActionEvent>() {
            
            /**
             * Decline a match
             * @param decline 
             */
            @Override
            public void handle(final ActionEvent decline) {
                controller.declineMatch(link);
                setInfoMatch();
            }
        });
        
        matchs.setOnAction((event) -> {
            controller.setMatchFriendsView("MATCH");
        });
        friends.setOnAction((event) -> {
            controller.setMatchFriendsView("FRIEND");
        });
        
        addfriend.setOnAction((event) -> {
            controller.addFriend(textaddfriend.getText());
        });
    }

    /**
     * Use for complete all data of the carousel
     */
    public void setInfoMatch() { // affiche les information des match(false) et match potentiel
        link = controller.getLinkIntrestingMatch();
        if (link != null) {

            
            Profil matchToShow;
            if (link.getNodeReicever().equals(controller.getConnected())) {
                matchToShow = (Profil) link.getNodeSender();
            } else {
                matchToShow = (Profil) link.getNodeReicever();
            }

            List<Information> infos = matchToShow.getInformation();
            for (final Information info : infos) {
                switch (info.getType()) {
                    case BRITHDATE:
                        final BirthDateInformation age = (BirthDateInformation) info;
                        carousel.setAge(age.getAge());
                        break;
                    case NAME:
                        final NameInformation name = (NameInformation) info;
                        carousel.setName(name.getName());
                        carousel.setFirstName(name.getFirstname());
                        break;
                    case SEX:
                        final SexInformation sex = (SexInformation) info;
                        carousel.setSexe(sex.getSex().name());
                        break;
                    case HOBBIE:
                        hobbies.add(((HobbieInformation) info).getName());

                        break;
                }
            }
            carousel.getHobbieList().setItems(hobbies);

        }else{
            //controller.setAloneView();
            carousel.clear();
        }
    }

    /**
     * call after the load of fxml file. Initialise the potential match 
     * in the network.
     */
    public void initialize2() {
        controller.setMatch();
        setInfoMatch();
    }
}

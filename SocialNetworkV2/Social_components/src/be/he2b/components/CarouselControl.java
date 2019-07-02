package be.he2b.components;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author dylanlevymorini
 */
public class CarouselControl extends HBox implements Control {
    
    /**
     * Accept Button
     */
    @FXML
    private Button accept;
    /**
     * Decline button
     */
    @FXML
    private Button decline;
    
    /**
     * Name of the potential match
     */
    @FXML
    private Label labelName;
    /**
     * Firstname of the potential match
     */
    @FXML
    private Label labelFirstName;
    /**
     * Sex of the potential match
     */
    @FXML
    private Label labelSexe;
    /**
     * Age of the potential match
     */
    @FXML 
    private Label labelAge;
    
    /**
     * All hobbies of the potential match
     */
    @FXML
    private ListView hobbieList;
    
    private ImageView alone2;
    

    /**
     * name
     */
    private final SimpleStringProperty name;
    /**
     * firstname
     */
    private final SimpleStringProperty firstname;
    /**
     * sex
     */
    private final SimpleStringProperty sexe;
    /**
     * age
     */
    private final SimpleIntegerProperty age;
    
    
    /**
     * constructor
     */
    public CarouselControl(){
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Carousel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CarouselControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final Image icon = new Image(getClass().getResource("/img/alone.jpg").toExternalForm());

        alone2 = new ImageView(icon);
        
        
        name = new SimpleStringProperty("unknown");
        firstname = new SimpleStringProperty("unknown");
        sexe = new SimpleStringProperty("unknown");
        age = new SimpleIntegerProperty(0);
        
        Bindings.bindBidirectional(labelName.textProperty(),
                name
                );
        Bindings.bindBidirectional(labelFirstName.textProperty(),
                firstname
                );
        Bindings.bindBidirectional(labelSexe.textProperty(),
                sexe
                );
        
        Bindings.bindBidirectional(labelAge.textProperty(),
                age,
                new NumberStringConverter());
    }

    @Override
    public void accept(final EventHandler<ActionEvent> handler) {
        accept.setOnAction(handler);
    }
    
    @Override
    public void decline(final EventHandler<ActionEvent> handler) {
        decline.setOnAction(handler);
    }
    
    @Override
    public void clear() {
        getChildren().clear();
        getChildren().add(new NoMatchSuggestion());
        //this.getChildren().add(alone2);
        //alone2.setFitWidth(700);
        
    }
    
    /**
     * Name of the potential match
     * @return 
     */
    public synchronized SimpleStringProperty nameProperty() {
        return name;
    }
    /**
     * name of the potential match
     * @return name
     */
    public synchronized String getName() {
        return name.get();
    }
    /**
     * set of the potentential match
     * @param name name of potential match
     */
    public synchronized void setName(final String name) {
        this.name.set(name);
    }
    
    /**
     * firstname of thepotential match. Property
     * @return firstname
     */
    public synchronized SimpleStringProperty firstNameProperty() {
        return firstname;
    }
    /**
     * Get firstname of the potential match
     * @return firstname of the potential match
     */
    public synchronized String getFirstName() {
        return name.get();
    }
    
    /**
     * Set firstname of the potential match
     * @param firstName the new firsname of the potential match
     */
    public synchronized void setFirstName(final String firstName) {
        this.firstname.set(firstName);
    }
    
    /**
     * Get property of sex of the potential match
     * @return property of sex
     */
    public synchronized SimpleStringProperty sexeProperty() {
        return sexe;
    }
    
    /**
     * Get sex of the potential match
     * @return sex of the potential match
     */
    public synchronized String getSexe() {
        return sexe.get();
    }
    /**
     * Set sex of the potential match
     * @param sexe 
     */
    public synchronized void setSexe(final String sexe) {
        this.sexe.set(sexe);
    }
    
    /**
     * Get property age of the potential match
     * @return property age
     */
    public synchronized SimpleIntegerProperty ageProperty() {
        return age;
    }
    
    /**
     * get age of the potential match
     * @return age of the potential match
     */
    public synchronized int getAge() {
        return age.get();
    }
    
    /**
     * Set age of the potential match
     * @param age 
     */
    public synchronized void setAge(final int age) {
        this.age.set(age);
    }
    
    /**
     * Get all hobbies of the potential match
     * @return hobbies of the potential match
     */
    public ListView getHobbieList(){
        return this.hobbieList;
    }
   
}

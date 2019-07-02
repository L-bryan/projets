package view.fxml;

import controller.AbstractController;
import core.BirthDateInformation;
import core.ContactInformation;
import core.HobbieInformation;
import core.InformationType;
import core.NameInformation;
import core.Profil;
import core.SexInformation;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Lyan
 */
public class Match_Friends_Detail extends Parent{
    
    private AbstractController controller;
    
    @FXML
    private Button back;
    
    @FXML
    private ListView hobbies;
    
    @FXML
    private Label title;
    
    @FXML
    private Label sex;
    
    @FXML
    private Label name;
    
    @FXML
    private Label age;
    
    @FXML
    private TextField tel;
    
    @FXML
    private TextField email;
    
    private ObservableList<String> obsHobbies;

    public Match_Friends_Detail(AbstractController controller) {
        
        this.controller = controller;
        obsHobbies = FXCollections.observableArrayList();
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass()
                    .getResource("/fxml/Match_Friend_Profil.fxml"));
            loader.setController(this);
            AnchorPane pane = (AnchorPane) loader.load();
            getChildren().add(pane);
        } catch (IOException ex) {
            System.err.println("error loader fxml -> Match_Friend_Profil.fxml");
        }
        
        back.setOnAction((event) -> {
            controller.setHomeView();
        });
    }
    
    public void init(Profil p){
        name.setText(p.getStringFullName());
        age.setText(""+p.getBirthDateInformation().getAge());
        sex.setText(""+p.getSexInformation().getSex());
        tel.setText(p.getStringTel());
        email.setText(p.getStringEmail());

        
        obsHobbies.clear();
        for(HobbieInformation hobs : p.getHobbieInformation()){
            obsHobbies.add(hobs.getName());
        }
        hobbies.setItems(obsHobbies);

    }
    
    public void setTitle(String s){
        title.setText(s);
    }
    
    
    
    
}

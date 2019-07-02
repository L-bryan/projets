package view.fxml;

import controller.AbstractController;
import core.BirthDateInformation;
import core.ContactInformation;
import core.Information;
import core.NameInformation;
import core.PreferenceInformation;
import core.Sex;
import core.SexInformation;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Register a user in the social Network
 *
 * @author Dylan et Bryan
 */
public class Inscription extends Parent {

    /**
     * Controller
     */
    private final AbstractController controller;
    /**
     * All information of the user
     */
    private List<Information> information;

    /**
     * Back button
     */
    @FXML
    private Button back;
    /**
     * Submit inscription
     */
    @FXML
    private Button inscription;
    /**
     * The name
     */
    @FXML
    private TextField name;
    /**
     * The firstname
     */
    @FXML
    private TextField firstname;
    /**
     * Birthdate
     */
    @FXML
    private DatePicker birthdate;
    /**
     * Sexe
     */
    @FXML
    private ComboBox sexe;
    /**
     * Private data. Mobile number
     */
    @FXML
    private TextField mobile;
    /**
     * Private Date. Email address
     */
    @FXML
    private TextField email;

    @FXML
    private ComboBox sexeP;

    @FXML
    private TextField dstFriend;
    @FXML
    private TextField ageMin;
    @FXML
    private TextField ageMax;

    /**
     * Constructor
     *
     * @param controller
     */
    public Inscription(final AbstractController controller) {
        this.controller = controller;
        information = new ArrayList();
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/inscription.fxml"));
            loader.setController(this);
            AnchorPane pane = (AnchorPane) loader.load();
            getChildren().add(pane);
        } catch (IOException ex) {
            Logger.getLogger(FxmlView.class.getName()).log(Level.SEVERE, "loader.load() failed", ex);
        }

        back.setOnAction((event) -> {
            controller.setConnectionView();
        });

        inscription.setOnAction((event) -> {
            try {
                prepareInformation();
                controller.register(information);
                controller.setConnectionView();
            } catch (IllegalArgumentException e) {
                information.clear();
                controller.error(e.getMessage());

            }
        });

        sexe.getItems().addAll("MAN", "WOMAN");
        sexeP.getItems().addAll("MAN", "WOMAN");
    }

    private void prepareInformation() {
        if (name.getText().length() != 0 && firstname.getText().length() != 0) {
            information.add(new NameInformation(name.getText(), firstname.getText()));
        } else {
            throw new IllegalArgumentException("Name and firstname don't exist ");
        }

        if (birthdate.getValue() != null) {
            LocalDate localDate = birthdate.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            information.add(new BirthDateInformation(date));
        } else {
            throw new IllegalArgumentException("Birthdate don't exist");

        }

        if (sexe.getValue() != null) {
            information.add(new SexInformation(Sex.valueOf(sexe.getValue().toString())));
            System.out.println(sexe.getValue().toString());
        }

        if (mobile.getText().length() != 0 && email.getText().length() != 0) {
            information.add(new ContactInformation(mobile.getText(), email.getText()));
        }

        if (dstFriend.getText().length() != 0 && ageMin.getText().length() != 0 && ageMax.getText().length() != 0 && sexeP.getValue() != null) {

            int dst = Integer.parseInt(dstFriend.getText());
            int min = Integer.parseInt(ageMin.getText());
            int max = Integer.parseInt(ageMax.getText());

            information.add(new PreferenceInformation(new SexInformation(Sex.valueOf(sexeP.getValue().toString())), dst, min, max));
        }

    }
}

package view.fxml;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import controller.AbstractController;

/**
 *
 * @author jlc
 */
public class MyMenuBar extends MenuBar {

    /**
     *
     * @param controller
     */
    public MyMenuBar(final AbstractController controller) {

        final Menu file = new Menu("File");
        final MenuItem newMenu = new MenuItem("New");
        final MenuItem openMenu = new MenuItem("Open");
        final MenuItem saveMenu = new MenuItem("Save");
        final MenuItem exitMenu = new MenuItem("Exit");

        
        file.getItems().addAll(newMenu, openMenu, saveMenu, exitMenu);

        final Menu edit = new Menu("Edit");
        final MenuItem undoMenu = new MenuItem("Undo");
        final MenuItem redoMenu = new MenuItem("Redo");

        undoMenu.setDisable(true);

        redoMenu.setDisable(true);

        edit.getItems().addAll(undoMenu, redoMenu);

        final Menu help = new Menu("Help");
        final MenuItem aboutMenu = new MenuItem("about");
        help.getItems().addAll(aboutMenu);

        this.getMenus().addAll(file, edit, help);

        newMenu.setDisable(true);
        saveMenu.setDisable(true);

        openMenu.setDisable(true);

        exitMenu.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                controller.quit();
            }
        });

        aboutMenu.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                controller.help();
            }
        });
    }

}

/*
 * Life sucks....
 */
package hall_management.gui.admin;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Abhik_Blaze
 */
public class Admin_viewController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private BorderPane rootBorderPane;
    @FXML
    private MenuBar menubar;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
    @FXML
    private Tab tab4;
    @FXML
    private Tab tab5;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initDrawer();
        

    }

    private void initDrawer() {
        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/hall_management/gui/admin/toolbar/toolbar.fxml"));
            drawer.setSidePane(toolbar);
        } catch (IOException ex) {
            Logger.getLogger(Admin_viewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            if (drawer.isHidden()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });
    }
    
}

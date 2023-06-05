package ma.emsi.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ma.emsi.dao.UserDao;
import ma.emsi.dao.impl.UserDaoImpl;
import ma.emsi.entities.User;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label messageLabel ;
    UserDao userDao = new UserDaoImpl();

    @FXML
    protected void onLogInButtonClick() throws Exception
    {
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());
        User user = userDao.getUser(usernameField.getText()) ;
        if (user == null)
            messageLabel.setText("User doesn't exist");
        else if (user.getPassword().equals(passwordField.getText())) {
            loginButton.setOnAction(event->{
                Parent root ;
                try {
                    root = FXMLLoader.load(HelloApplication.class.getResource("VoitureList.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root) ;
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
                window.setScene(scene);
                window.show();
            });
        }
        else
            messageLabel.setText("password incorrect");
    }
}
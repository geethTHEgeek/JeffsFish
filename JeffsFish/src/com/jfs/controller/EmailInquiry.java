package com.jfs.controller;

import com.jfs.service.EmailService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmailInquiry implements Initializable{

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextArea inquiryText;

    @FXML
    private Label lbl1;

    public static Map<String,String> userDetails;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email.setText(CustomerHome.userDetails.get("email"));
        lbl1.setText("Hi! "+userDetails.get("firstName")+" please give us your inquiry");

    }

    /**
     * Send the email inquiry
     */
    public void submitInquiry(){
        EmailService emailService = new EmailService();

        String inquiry= "      <p>\n" +
                "         Customer:\t"+userDetails.get("firstName")+" "+userDetails.get("lastName")+"\t<br>\t\n" +
                "         "+userDetails.get("address")+"\t<br><br>\t\t\t\t\n" +
                "         Customer#:\t"+userDetails.get("userId")+"\t<br>\t\t\n" +
                "         Customer email:"+userDetails.get("email")+"<br>\t\n" +
                "      </p>\n" +
                "<p>"+inquiryText.getText()+"</p>";
        String from=email.getText();
        String senderpassword=password.getText();
        emailService.sendEmail2(from,senderpassword,inquiry,"gvkodikara1@gmail.com");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Email Inquiry");
        alert.setHeaderText(null);
        alert.setContentText("Your Inquiry Was Successfully Sent");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            Parent root = null;
            try {
                URL url = new File("src/com/jfs/ui/customer_home.fxml").toURL();
                root = FXMLLoader.load(url);
            } catch (IOException e) {

            }
            Scene scene = new Scene(root);
            Stage stage = (Stage) email.getScene().getWindow();

            stage.setTitle("Customer Home");
            stage.setScene(scene);
            stage.show();

        }

    }

    /**
     * Send the customer back to home screen
     */
    public void goBack(){

        Parent root = null;
        try {
            URL url = new File("src/com/jfs/ui/customer_home.fxml").toURL();
            root = FXMLLoader.load(url);
        } catch (IOException e) {

        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) email.getScene().getWindow();

        stage.setTitle("Customer Home");
        stage.setScene(scene);
        stage.show();

    }

}

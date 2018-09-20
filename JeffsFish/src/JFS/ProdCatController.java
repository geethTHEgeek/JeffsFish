package JFS;

import com.jfs.controller.LogIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProdCatController implements Initializable {

    private ObservableList<String> quantity_1_List= FXCollections.observableArrayList("100m","200m","300m");
    private ObservableList<String> quantity_2_List= FXCollections.observableArrayList("50g","100g","150g");
    private ObservableList<String> quantity_3_List= FXCollections.observableArrayList("Small","Medium","Large");
    private ObservableList<String> quantity_4_List= FXCollections.observableArrayList("#4","#5","#6");
    private ObservableList<String> quantity_5_List= FXCollections.observableArrayList("1m","2m","3m");
    private ObservableList<String> quantity_6_List= FXCollections.observableArrayList("S","M","L");


    @FXML
    private ChoiceBox quantity1;

    @FXML
    private ChoiceBox quantity2;

    @FXML
    private ChoiceBox quantity3;

    @FXML
    private ChoiceBox quantity4;

    @FXML
    private ChoiceBox quantity5;

    @FXML
    private ChoiceBox quantity6;

    @FXML
    private Label userName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quantity1.setValue("100m");
        quantity1.setItems(quantity_1_List);

        quantity2.setValue("50g");
        quantity2.setItems(quantity_2_List);

        quantity3.setValue("Small");
        quantity3.setItems(quantity_3_List);

        quantity4.setValue("#4");
        quantity4.setItems(quantity_4_List);

        quantity5.setValue("1m");
        quantity5.setItems(quantity_5_List);

        quantity6.setValue("S");
        quantity6.setItems(quantity_6_List);

        userName.setText(LogIn.user);

    }

    public void afterInitialize(){

        System.out.println(quantity1.getValue());
    }
}

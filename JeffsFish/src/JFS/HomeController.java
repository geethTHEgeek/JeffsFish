package JFS;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {



    public static String user;

    public void customerSelection() throws IOException {

        user="customer";
        Parent root = FXMLLoader.load(getClass().getResource("../com/jfs/ui/Log_In.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();

    }

    public void staffSelection() throws IOException {

        user="staff";
        Parent root = FXMLLoader.load(getClass().getResource("../com/jfs/ui/Log_In.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();

    }

    public void adminSelection() throws IOException {

        user="admin";
        Parent root = FXMLLoader.load(getClass().getResource("../com/jfs/ui/Log_In.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();

    }

}

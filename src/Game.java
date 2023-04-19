import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args) {
        //readSettings(); -> oyun baslamadan once ayarlari okumaya ihtiyac duyacak, bu fonksiyonu buraya gececegiz.
        launch(args);
    }

    private static final String FILE_PATH = "settings.conf"; // -> settings.conf production asamasinda out/production/ClickGame icerisinde, oyunun ismi degisicek :D
//bos ekran haci
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Menü metni
        Text txtMenu = new Text("Game");
        txtMenu.setFont(Font.font(36));
        //txtMenu.setFill(Color.WHITE);
        //StackPane.setMargin(btnAyarlar, new Insets(0, 0, 20, 0));
        StackPane stackPane = new StackPane();

        // Scene ve Stage ayarları
        Scene scene = new Scene(stackPane, 800, 600); //resolution
        primaryStage.setTitle("Oyun"); // oyun pencere ismi
        primaryStage.setScene(scene); // sahne olusturma
        primaryStage.show();
    }


}
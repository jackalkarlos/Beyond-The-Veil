import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Game extends Application {
    public static void main(String[] args) {
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.checkifSettings(); //once ayarlarin olup olmadigi kontrol edilecek
        //readSettings(); sonrasinda oyun baslamadan once ayarlari okumaya ihtiyac duyacak,
        launch(args); // oyunu baslat,
    }

    private static final String FILE_PATH = "settings.conf"; // -> settings.conf production asamasinda out/production/ClickGame icerisinde, oyunun ismi degisicek :D@Override
    public void start(Stage primaryStage) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Menü metni
        Text txtMenu = new Text("Game");
        txtMenu.setFont(Font.font(36));
        StackPane stackPane = new StackPane();

        // Scene ve Stage ayarları
        Scene scene = new Scene(stackPane, 800, 600); //resolution
        primaryStage.setTitle("Oyun"); // oyun pencere ismi
        primaryStage.setScene(scene); // sahne olusturma
        primaryStage.show();
    }
    //public static List getSettings() { -> burasi ayar okuma kismi olacak oyun baslamadan once ilk bunu okumali
    //}
}
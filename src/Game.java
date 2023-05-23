import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//
public class Game extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) {
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.checkifSettings(); //once ayarlarin olup olmadigi kontrol edilecek
        launch(args); // oyunu baslat,
    }

    private static final String FILE_PATH = "settings.conf"; // -> settings.conf production asamasinda out/production/ClickGame icerisinde, oyunun ismi degisicek :D
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        SettingsManager settingsMng = new SettingsManager();
        int intAudio = settingsMng.returnAudioValue();

        String strLevel = settingsMng.returnlevelValue();

        int intOpacity = settingsMng.returnOpacityValue();
        double fakeOpacity=intOpacity / 10;
        double opacity=fakeOpacity / 10;


        String strRes= settingsMng.returnResValue();
        String[] parts = strRes.split("x");
        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);

        String fullScreen= settingsMng.returnScreenValue();
        boolean isFullScreen=Boolean.parseBoolean(fullScreen);

        Label label1 = new Label("yazı yazmaca");
        Button button1 = new Button("İntroyu geç");
        //layout1
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, width,height);;
        //layout 1 ve scene 1 bitiş

        //layout2
        Button button2 = new Button("geri dön");
        button2.setOnAction(e -> window.getScene().setRoot(layout1));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        //layout2 bitiş

        button1.setOnAction(e -> window.getScene().setRoot(layout2));
        // a


        window.setTitle("Beyond The Veil");
        window.setScene(scene1);
        window.setOpacity(opacity);
        window.setFullScreen(isFullScreen);
        window.show();
    }
}
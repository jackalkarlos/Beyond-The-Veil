import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    Scene scene1, scene2, scene3;
    //commit test 2

    public static void main(String[] args) {
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.checkifSettings(); //once ayarlarin olup olmadigi kontrol edilecek
        launch(args); // oyunu baslat,
    }

    private static final String FILE_PATH = "settings.conf"; // -> settings.conf production asamasinda out/production/ClickGame icerisinde, oyunun ismi degisicek :D
    @Override
    public void start(Stage primaryStage) {
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

        String imagePath = "src/images/arkaplan.png";
        Image backgroundImage = new Image(new File(imagePath).toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        Label label1 = new Label("yazı yazmaca");
        Font font = Font.font("Calibri", FontWeight.NORMAL, FontPosture.ITALIC, 20);
        label1.setFont(font);

        Button button1 = new Button("İntroyu geç");
        button1.setTranslateY(50);

        //layout1
        StackPane layout1 = new StackPane();
        layout1.setBackground(new Background(background));
        layout1.getChildren().addAll(label1, button1);
        StackPane.setAlignment(label1, Pos.CENTER);
        StackPane.setAlignment(button1, Pos.CENTER);
        StackPane.setMargin(label1, new Insets(0, 0, 50, 0)); // Label'ın alt boşluğunu ayarlayabilirsiniz

        scene1 = new Scene(layout1, width,height);;


        //layout 1 ve scene 1 bitiş

        Media media = new Media(new File("src/videos/video.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        //mediaPlayer.setAutoPlay(true); // Otomatik olarak oynatma özelliğini etkinleştirin
        mediaView.fitWidthProperty().bind(primaryStage.widthProperty()); // Genişlik sığacak şekilde ayarlanır
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty()); //
        mediaPlayer.setOnEndOfMedia(() -> {
            window.setScene(scene3);
        });

        VBox esek = new VBox();
        esek.getChildren().add(mediaView);

        window = primaryStage;



        //layout2
        Button button2 = new Button("geri dön");
        button2.setOnAction(e -> window.getScene().setRoot(layout1) );

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        //layout2 bitiş

        button1.setOnAction(e -> {
            window.getScene().setRoot(esek);
            mediaPlayer.play();
        });
        // button 3

        Button button3 = new Button("marhaba");


        String imagePath2 = "src/ingame/backgrounds/scene3.png";
        Image background3Image = new Image(new File(imagePath2).toURI().toString());
        BackgroundSize background3Size = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background3 = new BackgroundImage(background3Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, background3Size);

        String ekImagePath1 = "src/ingame/characters/1smile.png";
        Image ekImage1 = new Image(new File(ekImagePath1).toURI().toString());
        ImageView ImageView1 = new ImageView(ekImage1);

        String ekImagePath2 = "src/ingame/textbox/textbox.png";
        Image ekImage2 = new Image(new File(ekImagePath2).toURI().toString());
        ImageView ImageView2 = new ImageView(ekImage2);

        //layout3
        StackPane layout3 = new StackPane();
        layout3.getChildren().addAll(button3, ImageView1, ImageView2);
        layout3.setBackground(new Background(background3));
        StackPane.setAlignment(button3, Pos.CENTER);
        StackPane.setAlignment(ImageView1, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(ImageView2, Pos.BOTTOM_CENTER);

        ImageView1.setFitWidth(525); // İstenilen genişlik değerini belirleyin
        ImageView1.setFitHeight(1000); // İstenilen yükseklik değerini belirleyin
        StackPane.setMargin(ImageView1, new Insets(0, 0, 0, 110)); // İstenilen boşluk değerlerini belirleyin

        ImageView2.fitWidthProperty().bind(layout3.widthProperty());


        scene3 = new Scene(layout3, width, height);

        window.setTitle("Beyond The Veil");
        window.setScene(scene1);
        window.setOpacity(opacity);
        window.setFullScreen(isFullScreen);
        window.show();
    }
}
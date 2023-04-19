import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos; ///
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class OyunMenu extends Application {
    private MediaPlayer mediaPlayer;


    public static void main(String[] args) {
        launch(args); //javafx'de application sınıfının baslatilmasini launch metodu saglar

    }
    @Override
    public void start(Stage primaryStage) {
        // Working Directory Tanimlamasi - Burasi silinecek calisma sirasinda takip edebilmek icin yapildi
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));

        Image backgroundImage = new Image("images/Background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        Image AppLogo = new Image("images/Logo.png");
        ImageView AppLogoView = new ImageView(AppLogo);

        DropShadow glowEffect = new DropShadow(); // DropShadow efekti oluştur
        glowEffect.setColor(javafx.scene.paint.Color.YELLOW); // Glowing rengini ayarla

        String musicFile = "src/sounds/menutheme.mp3";
        //String musicFile = getClass().getResource("/sounds/menutheme.mp3").toExternalForm();
        Media media = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        //double volume = 0.5; //  ses seviyesi (0.0 - 1.0 arasında bir değer)
        //mediaPlayer.setVolume(volume);


        //Game.Java class'indaki gui'yi çağıran buton
        Button btnBaslat = new Button("");
        Image startbutonimage = new Image(getClass().getResourceAsStream("images/start.png"));
        btnBaslat.setGraphic(new ImageView(startbutonimage));
        btnBaslat.setBackground(Background.EMPTY);
        btnBaslat.setOnAction(event -> {
            Stage gameStage = new Stage();
            Game gameApp = new Game();
            gameApp.start(gameStage);
            primaryStage.close();
        });
        btnBaslat.setOnMouseEntered(event -> {
            btnBaslat.setEffect(glowEffect);
        });
        btnBaslat.setOnMouseExited(event -> {
            btnBaslat.setEffect(null);
        });

        Button btnCikis = new Button("");
        Image cikisbutonimage = new Image(getClass().getResourceAsStream("images/exit.png"));
        btnCikis.setGraphic(new ImageView(cikisbutonimage));
        btnCikis.setBackground(Background.EMPTY);
        btnCikis.setOnAction(event -> {
            primaryStage.close();
        });
        btnCikis.setOnMouseEntered(event -> {
            btnCikis.setEffect(glowEffect);
        });
        btnCikis.setOnMouseExited(event -> {
            btnCikis.setEffect(null);
        });



        //SettingsManager.Java class'indaki gui'yi çağıran buton
        Button btnAyarlar = new Button("");
        Image settingsbutonimage = new Image(getClass().getResourceAsStream("images/settings.png"));
        btnAyarlar.setGraphic(new ImageView(settingsbutonimage));
        btnAyarlar.setBackground(Background.EMPTY);
        //VBox vbox2 = new VBox();
        //vbox2.getChildren().addAll(btnAyarlar);
        //vbox2.setPadding(new Insets(600, 0, 0, 950));
        btnAyarlar.setOnMouseEntered(event -> {
            btnAyarlar.setEffect(glowEffect);
        });
        btnAyarlar.setOnMouseExited(event -> {
            btnAyarlar.setEffect(null);
        });


        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(500, 0, 0, 950));
        vbox1.getChildren().addAll(btnBaslat, btnAyarlar, btnCikis);
        vbox1.setSpacing(25);

        btnAyarlar.setOnAction(event -> {
                    Stage settingsManagerStage = new Stage();
                    SettingsManager settingsManagerApp = new SettingsManager();
                    settingsManagerApp.start(settingsManagerStage);
                    primaryStage.close();
        });

        // Stackpane (duzen ve ortalama icin, masa ve masadaki tabaklar gibi dusunun) -> konum belirleme isleri burda olacak.
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, AppLogoView, vbox1);
        StackPane.setAlignment(backgroundImageView, Pos.CENTER);
        StackPane.setAlignment(AppLogoView, Pos.CENTER);
        //StackPane.setAlignment(btnBaslat, Pos.BOTTOM_LEFT);
        //StackPane.setAlignment(btnAyarlar, Pos.BOTTOM_RIGHT);

        // Scene ve Stage ayarları -> resolution icin update gececegiz unutturmayin settings confta width height olarak ayrilmasi lazim
        Scene scene = new Scene(stackPane, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Oyun Menüsü");
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
        AppLogoView.fitWidthProperty().bind(scene.widthProperty());
        AppLogoView.fitHeightProperty().bind(scene.heightProperty());
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}

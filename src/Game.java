import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
public class Game extends Application {

    private static final String kayanyazi = "Başlamak İstediğine Eminmisin";// Yazılacak meti
    private static final int yazidelay = 100; // Yazma gecikmesi (milisaniye cinsinden)
    private static final int FONT_SIZE = 20; // Yazı font büyüklüğü
    private int currentIndex = 0; // Yazıda işlenen karakterin indeksi

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

        String imagePath = "src/ingame/backgrounds/scene3.png";
        Image backgroundImage = new Image(new File(imagePath).toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        String eminmisin = "src/ingame/textbox/sure.png";
        Image eminim = new Image(new File(eminmisin).toURI().toString());
        ImageView emine = new ImageView(eminim);

        String evet = "src/ingame/textbox/button.png";
        Image evet2 = new Image(new File(evet).toURI().toString());
        ImageView evet3 = new ImageView(eminim);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GRAY);
        dropShadow.setRadius(5);
        dropShadow.setSpread(0.5);

        Button button1 = new Button("");
        button1.setGraphic(new ImageView(evet2));
        button1.setStyle("-fx-background-color: transparent;");
        button1.setOnMousePressed(event -> {
            button1.setScaleX(0.9);
            button1.setScaleY(0.9);
        });
        button1.setOnMouseReleased(event -> {
            button1.setScaleX(1.0);
            button1.setScaleY(1.0);
        });



        button1.setTranslateY(50);

        //layout1
        StackPane layout1 = new StackPane();
        layout1.setBackground(new Background(background));
        layout1.getChildren().addAll(emine, button1);
        StackPane.setAlignment(emine, Pos.CENTER);
        StackPane.setAlignment(button1, Pos.CENTER);
        StackPane.setMargin(emine, new Insets(0, 0, 50, 0)); // Label'ın alt boşluğunu ayarlayabilirsiniz

        scene1 = new Scene(layout1, width,height);;


        //layout 1 ve scene 1 bitiş

        Media media = new Media(new File("src/videos/intro.mp4").toURI().toString());
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


        String imagePath2 = "src/images/2.png";
        Image background3Image = new Image(new File(imagePath2).toURI().toString());
        BackgroundSize background3Size = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background3 = new BackgroundImage(background3Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, background3Size);

        String ekImagePath1 = "src/ingame/characters/katman.png";
        Image ekImage1 = new Image(new File(ekImagePath1).toURI().toString());
        ImageView ImageView1 = new ImageView(ekImage1);

        String ekImagePath2 = "src/ingame/textbox/textbox.png";
        Image ekImage2 = new Image(new File(ekImagePath2).toURI().toString());
        ImageView ImageView2 = new ImageView(ekImage2);

        //layout3
        StackPane layout3 = new StackPane();
        layout3.setAlignment(Pos.BOTTOM_LEFT);
        layout3.getChildren().addAll(ImageView1, ImageView2);
        layout3.setBackground(new Background(background3));
        StackPane.setAlignment(ImageView1, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(ImageView2, Pos.BOTTOM_CENTER);


        String kayanyazi = "Dünyaları yıkılan maymun karşıma çıktı. Kafasını eline koymuş bir şekilde bana doğru yöneldi ve bana baktı. Sanki gözlerinin içerisinde bir deprem olmuş ve tüm muzlarının üstüne ağaç düşmüş gibiydi. Bu maymun çok üzgündü ve yardıma ihtiyacı vardı. Ona yardım etmeliydim.";


        Text text = new Text();
        text.setFont(Font.font("Arial", FontWeight.BOLD, 17.5)); // Yazıyı 17.5 punto boyutunda ayarlandı.
        layout3.getChildren().add(text);
        text.setTranslateY(-220); // Yazıyı 220 birim aşağıya kaydırdık.
        text.setTranslateX(167);
        text.setFill(Color.CORNFLOWERBLUE);

        text.setWrappingWidth(1400); // Bir satırın genişliğini belirleyin
        text.setLineSpacing(10); // Satırlar arasındaki boşluğu ayarlayın
        text.setText(kayanyazi); // Metnin tamamını ayarlayın

        ImageView1.setFitWidth(525); // İstenilen genişlik değerini belirleyin
        ImageView1.setFitHeight(1000); // İstenilen yükseklik değerini belirleyin
        StackPane.setMargin(ImageView1, new Insets(0, 0, 0, 110)); // İstenilen boşluk değerlerini belirleyin

        ImageView2.fitWidthProperty().bind(layout3.widthProperty());

        layout3.setOnMouseClicked(event -> {
            currentIndex = kayanyazi.length(); // Yazıda işlenen karakterin indeksini son karaktere set et
            text.setText(kayanyazi); // Kayan yazıyı tamamlanan metin olarak güncelle
        });

        scene3 = new Scene(layout3, width, height);

        primaryStage.setScene(scene3);
        primaryStage.show();
        Timeline typingTimeline = new Timeline(
                new KeyFrame(Duration.millis(yazidelay), event -> {
                    if (currentIndex <= kayanyazi.length()) {
                        text.setText(kayanyazi.substring(0, currentIndex++));
                    }
                })
        );
        typingTimeline.setCycleCount(kayanyazi.length() + 1);
        typingTimeline.play();

        String buttonImageUrl = "file:src/ingame/textbox/button.png"; // Butonun görselinin dosya yolu

        Image buttonImage = new Image(buttonImageUrl);
        ImageView buttonImageView = new ImageView(buttonImage);
        buttonImageView.setFitWidth(100); // Görselin genişlik ve yükseklik ayarları
        buttonImageView.setFitHeight(40);

        Button nextButton = new Button();
        nextButton.setGraphic(buttonImageView); // Görseli butona ekle

        nextButton.setOnAction(event -> {
            // Yeni sahneyi oluştur
            Stage newStage = new Stage();
            StackPane newLayout = new StackPane();
            Scene newScene = new Scene(newLayout, width, height);
            newStage.setScene(newScene);
            newStage.show();

            // Mevcut sahneyi gizle
            primaryStage.hide();
        });

        layout3.getChildren().add(nextButton);
        StackPane.setAlignment(nextButton, Pos.BOTTOM_RIGHT);

        nextButton.setVisible(false); // İlk başta butonu gizle

        typingTimeline.setOnFinished(event -> {
            nextButton.setVisible(true); // Yazı gösterimi tamamlandığında butonu göster
        });

        typingTimeline.play();
        window.setTitle("Beyond The Veil");
        window.setScene(scene1);
        window.setOpacity(opacity);
        window.setFullScreen(isFullScreen);
        window.show();
    }
}
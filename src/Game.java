import javafx.animation.FadeTransition;
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
import javafx.scene.input.MouseButton;
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
    private static final String kayanyazi2 = "Başlamak İstediğine Eminmisin";
    private static final int yazidelay = 100; // Yazma gecikmesi (milisaniye cinsinden)
    private static final int FONT_SIZE = 20; // Yazı font büyüklüğü
    private int currentIndex = 0; // Yazıda işlenen karakterin indeksi
    private boolean textFinished = false; // Yazı tamamlandığında true olacak durum değişkeni
    private boolean isFirstClick = true;

    Stage window;
    Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9, scene10;
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

        // layout3 arkaplan
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

        ImageView1.setFitWidth(525); // İstenilen genişlik değerini belirleyin
        ImageView1.setFitHeight(1000); // İstenilen yükseklik değerini belirleyin
        StackPane.setMargin(ImageView1, new Insets(0, 0, 0, 110)); // İstenilen boşluk değerlerini belirleyin

        ImageView2.fitWidthProperty().bind(layout3.widthProperty());


        String kayanyazi = "(Ally'nin iç sesi: 1.. 2.. 3.. 4.. 5.. 6..)\nKoyunları saymakla geçen günlerime yeni bir tanesi daha eklendi. Cidden, toplum farklı olanı dışladığını sanırken, ben onları dışlamıştım aslında. Ya durum tam tersiyse?";


        //chatbox kayan yazı
        Text text = new Text();
        text.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        layout3.getChildren().add(text);
        text.setTranslateY(-220);
        text.setTranslateX(167);
        text.setFill(Color.CORNFLOWERBLUE);
        text.setWrappingWidth(1400);
        text.setLineSpacing(10);

        //chatbox isim
        Text extraText = new Text();
        extraText.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        extraText.setText("Ally");
        extraText.setFill(Color.PALEVIOLETRED);
        extraText.setWrappingWidth(1400);
        extraText.setLineSpacing(10);
        extraText.setTranslateX(310);
        extraText.setTranslateY(-350);
        extraText.setOpacity(0);
        layout3.getChildren().add(extraText);

        FadeTransition ft = new FadeTransition(Duration.millis(3000), extraText);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();



        layout3.setOnMouseClicked(event -> {
            if (isFirstClick) { // İlk tıklama
                if (!textFinished) {
                    currentIndex = kayanyazi.length();
                    text.setText(kayanyazi);
                    textFinished = true;
                }
                isFirstClick = false;
            } else { // İkinci tıklama
                window.setScene(scene4);
            }
        });

        scene3 = new Scene(layout3, width, height);

        primaryStage.setScene(scene3);
        primaryStage.show();
        scene3.setOnMouseEntered(event -> {
            ft.playFromStart();
            if (!textFinished && currentIndex <= kayanyazi.length()) {
                Timeline typingTimeline = new Timeline(
                        new KeyFrame(Duration.millis(yazidelay), e -> {
                            if (currentIndex <= kayanyazi.length()) {
                                text.setText(kayanyazi.substring(0, currentIndex++));
                                // Metin tamamlanınca textFinished'i güncelle
                                if (currentIndex == kayanyazi.length()) {
                                    textFinished = true;
                                }
                            }
                        })
                );
                typingTimeline.setCycleCount(kayanyazi.length() + 1);
                typingTimeline.play();
            }
        });

        String imagePath3 = "src/images/2.png";
        Image background4Image = new Image(new File(imagePath3).toURI().toString());
        BackgroundSize background4Size = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background4 = new BackgroundImage(background4Image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, background4Size);

        String ekImagePath3 = "src/ingame/characters/sm1_sensei_pout.png";
        Image ekImage3 = new Image(new File(ekImagePath3).toURI().toString());
        ImageView ImageView3 = new ImageView(ekImage3);

        String ekImagePath4 = "src/ingame/textbox/textbox.png";
        Image ekImage4 = new Image(new File(ekImagePath4).toURI().toString());
        ImageView ImageView4 = new ImageView(ekImage4);

//layout4
        StackPane layout4 = new StackPane();
        layout4.setAlignment(Pos.BOTTOM_LEFT);
        layout4.getChildren().addAll(ImageView3, ImageView4);
        layout4.setBackground(new Background(background4));
        StackPane.setAlignment(ImageView3, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(ImageView4, Pos.BOTTOM_CENTER);

        ImageView3.setFitWidth(525); // İstenilen genişlik değerini belirleyin
        ImageView3.setFitHeight(1000); // İstenilen yükseklik değerini belirleyin
        StackPane.setMargin(ImageView3, new Insets(0, 0, 0, 110)); // İstenilen boşluk değerlerini belirleyin

        ImageView4.fitWidthProperty().bind(layout4.widthProperty());


//chatbox isim
        Text extraText2 = new Text();
        extraText2.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        extraText2.setText("Ally");
        extraText2.setFill(Color.PALEVIOLETRED);
        extraText2.setWrappingWidth(1400);
        extraText2.setLineSpacing(10);
        extraText2.setTranslateX(310);
        extraText2.setTranslateY(-350);
        extraText2.setOpacity(0);
        layout4.getChildren().add(extraText2);

        FadeTransition ft2 = new FadeTransition(Duration.millis(3000), extraText2);
        ft2.setFromValue(0.0);
        ft2.setToValue(1.0);
        ft2.play();

        //chatbox kayan yazı
        Text text2 = new Text();
        text2.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        text2.setFill(Color.CORNFLOWERBLUE);
        text2.setWrappingWidth(1400);
        text2.setLineSpacing(10);
        layout4.getChildren().add(text2);
        text2.setTranslateY(-220);
        text2.setTranslateX(167);

        String kayanyazi2 = "babisbabisbabisbabis";

        layout4.setOnMouseClicked(event -> {
            if (isFirstClick) { // İlk tıklama
                if (!textFinished) {
                    currentIndex = kayanyazi2.length();
                    text2.setText(kayanyazi2);
                    textFinished = true;
                }
                isFirstClick = false;
            } else { // İkinci tıklama
                // İstenilen işlemleri yapabilirsiniz
            }
        });

        scene4 = new Scene(layout4, width, height);

        primaryStage.setScene(scene4);
        primaryStage.show();

        scene4.setOnMouseEntered(event -> {
            ft2.playFromStart();
            if (!textFinished && currentIndex <= kayanyazi2.length()) {
                Timeline typingTimeline = new Timeline(
                        new KeyFrame(Duration.millis(yazidelay), e -> {
                            if (currentIndex <= kayanyazi2.length()) {
                                text2.setText(kayanyazi2.substring(0, currentIndex++));
                                // Metin tamamlandığında textFinished'i güncelleyin
                                if (currentIndex == kayanyazi2.length()) {
                                    textFinished = true;
                                }
                            }
                        })
                );
                typingTimeline.setCycleCount(kayanyazi2.length() + 1);
                typingTimeline.play();
            }
        });


        window.setTitle("Beyond The Veil");
        window.setScene(scene3);
        window.setOpacity(opacity);
        window.setFullScreen(isFullScreen);
        window.show();
    }
}
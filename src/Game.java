import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    public static final int yazidelay = 30; // Yazma gecikmesi (milisaniye cinsinden)
    public static int animasyonIndex = 0;

    private int currentIndex = 0; // Yazıda işlenen karakterin indeksi
    private boolean textFinished = false; // Yazı tamamlandığında true olacak durum değişkeni
    private boolean isFirstClick = true;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    private MediaPlayer outro;


    VBox intro = new VBox();
    StackPane sahne1 = new StackPane();
    VBox sahne1vbox = new VBox();

    Stage window;
    Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9, scene10;
    Text sahne1yazi = new Text();
    Text AllyName = new Text();


    //commit test 2

    public static void main(String[] args) {
        SettingsManager settingsManager = new SettingsManager();
        settingsManager.checkifSettings(); //once ayarlarin olup olmadigi kontrol edilecek
        launch(args); // oyunu baslat,
    }

    private static final String FILE_PATH = "settings.conf"; // -> settings.conf production asamasinda out/production/ClickGame icerisinde, oyunun ismi degisicek :D
    @Override
    public void start(Stage primaryStage) {
        System.setProperty("prism.allowhidpi", "true");
        System.setProperty("prism.lcdtext", "true");
        //ayarlarin okunmasi
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
        //ayarlarin okunmasinin bitirilmesi

        //Burası Scene1 Tasarımının başladığı yer. EMIN MISIN KISMI!!!!! ILK SCENE!!!



        //Burası Emin Misin Fotoğraf Tanımlaması
        String eminmisinlocation = "src/ingame/textbox/sure.png";
        Image eminmisinimage= new Image(new File(eminmisinlocation).toURI().toString());
        ImageView eminmisin = new ImageView(eminmisinimage);
        //Burası Emin Misin Fotoğraf Tanımlamasının Bitişi

        //Burası Emin Misin Butonunun Fotoğraf Tanımlaması
        String evet = "src/ingame/textbox/button.png";
        Image evet2 = new Image(new File(evet).toURI().toString());
        //Burası Emin Misin Butonunun Fotoğraf Tanımlaması

        // Emin misin Butonu Fonksiyon Ayarları
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
        // Emin misin Butonu bitişi

        // Scene 1 Arkaplan Resmi
        String imagePath = "src/ingame/backgrounds/scene3.png";
        Image backgroundImage = new Image(new File(imagePath).toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        // Scene 1 Arkaplan Resmi Bitişi


        // Scene1'e eklenecek layout
        StackPane layout1 = new StackPane();
        layout1.setBackground(new Background(background));
        layout1.getChildren().addAll(eminmisin, button1);
        StackPane.setAlignment(eminmisin, Pos.CENTER);
        StackPane.setAlignment(button1, Pos.CENTER);
        StackPane.setMargin(eminmisin, new Insets(0, 0, 50, 0)); // Label'ın alt boşluğunu ayarlayabilirsiniz

        scene1 = new Scene(layout1, width,height);
        //Layout 1 ve Scene 1 bitiş

        //Burası yine Scene1 içinde, Sadece  yni bir layout oluşturulup layout Değişikliği yapılıyor, bu şekilde gitmeliyiz
        //intro vbox
        Media media = new Media(new File("src/videos/intro.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        intro.getChildren().add(mediaView);
        window = primaryStage;

        button1.setOnAction(e -> {
            window.getScene().setRoot(intro); //scene1'i alıp rootunu intro olarak ayarlıyor
            mediaPlayer.play();
        });
        mediaPlayer.setOnEndOfMedia(() -> {
            window.getScene().setRoot(sahne1vbox); //scene1'i alıp rootunu intro olarak ayarlıyor
            mediaPlayer3.play();
            mediaPlayer3.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer3.setVolume(intAudio);
        });

        //sahne1 vbox'u için karakter fotosu
        String ekImagePath1 = "src/ingame/characters/katman.png";
        Image ekImage1 = new Image(new File(ekImagePath1).toURI().toString());
        ImageView ImageView1 = new ImageView(ekImage1);

        //sahne1 vbox'u için arkaplan resmi

        String backPath = "src/images/2.png";
        Image backImage = new Image(new File(backPath).toURI().toString());
        ImageView backImageView1 = new ImageView(backImage);


        //sahne1 vbox'u için textbox resmi
        String ekImagePath2 = "src/ingame/textbox/textbox.png";
        Image ekImage2 = new Image(new File(ekImagePath2).toURI().toString());
        ImageView ImageView2 = new ImageView(ekImage2);

        //sahne1 vbox
        sahne1.setAlignment(Pos.BOTTOM_LEFT);
        sahne1.getChildren().addAll(backImageView1, ImageView1, ImageView2);
        StackPane.setAlignment(ImageView1, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(ImageView2, Pos.BOTTOM_CENTER);

        ImageView1.setFitWidth(525); // İstenilen genişlik değerini belirleyin
        ImageView1.setFitHeight(1000); // İstenilen yükseklik değerini belirleyin
        StackPane.setMargin(ImageView1, new Insets(0, 0, 0, 0)); // İstenilen boşluk değerlerini belirleyin
        ImageView2.fitWidthProperty().bind(sahne1.widthProperty());

        //ChatBox İsim
        AllyName.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        AllyName.setText("Ally");
        AllyName.setFill(Color.PALEVIOLETRED);
        AllyName.setWrappingWidth(1400);
        AllyName.setLineSpacing(10);
        AllyName.setTranslateX(310);
        AllyName.setTranslateY(-350);
        sahne1.getChildren().add(AllyName);

        String kayanyazi1 = "(Ally'nin iç sesi: 1.. 2.. 3.. 4.. 5.. 6..) ";
        String kayanyazi2 = "Koyunları saymakla geçen günlerime yeni bir tanesi daha eklendi. ";
        String kayanyazi3 = "Cidden, toplum farklı olanı dışladığını sanırken, ben onları dışlamıştım aslında. Ya durum tam tersiyse? ";
        String kayanyazi4 = "Geçenlerde kendi içimde bir toplantı düzenledim. Tek katılımcı bendim, katılımcı sayısı epey yüksekti. ";
        String kayanyazi5 = "Düşüncelerimi ve hislerimi konuştuk bol bol. ";
        String kayanyazi6 = "Birisi çıkıp 'hislerin umrumuzda değil' dedi, onu görememiştim, ama neden bu şekilde bağırdığını anlayabiliyordum. ";
        String kayanyazi7 = "Henüz ne ben onları, ne de onlar beni kabullenebilmişti. ";
        String kayanyazi8 = "Aykırı bir tarza mı sahiptim? Yoksa çok mu saçma cümleler kuruyordum? Ya da sakarlığım yüzünden miydi? ";
        String kayanyazi9 = "Ya da sevdiğim insanlara gerçekten değer verebildiğim için miydi? Bunların hepsi bir varsayım.. ";
        String kayanyazi10 = "(Ally'nin telefonuna gizemli bir mesaj gelir) ";
        String kayanyazi11 = "(Ally telefonunu kontrol eder) ";
        String kayanyazi12 = "Merhaba, Ally. Uzun zaman oldu değil mi? Hala kafanın içinde dönüp duran tilkilerle savaşıyor musun:) ";
        String kayanyazi13 = "(Ally karşıdaki kişiye bir mesaj gönderir) ";
        String kayanyazi14 = "Kimsin? ";
        String kayanyazi15 = "Ben senin içindeki karanlığım. ";
        String kayanyazi16= "(Ally, şaşkınlıkla telefonunu elinde tutar, elleri titremeye başlar ve gözleri mesajdaki kelimelerde takılı kalır.) ";
        String kayanyazi17= "(İçinden bir ürperti geçer, ancak merakı daha güçlüdür. Bir süre düşünür ve ardından mesaja cevap verir.) ";
        String kayanyazi18= "İçimdeki karanlık mı? Ne demek istiyorsun? Kim olduğunu söyle! ";
        String kayanyazi19= "(Ally mesajına cevap alamaz. Karşıdaki kişinin ne istediğini merak eder.) ";
        String kayanyazi20= "(Kendisini kontrol etmeye çalışan Ally, cesaretini toplar ve tekrar mesaj atar.) ";
        String kayanyazi21= "Söyle bakalım, içimdeki karanlık kim? Ne istiyorsun benden? ";
        String kayanyazi22= "Cevaplarını bulmak için sana bir teklifim var. Hadi bir oyun oynayalım. ";
        String kayanyazi23= "(Gizemli kişi bir konum gönderir.) ";
        String kayanyazi24= "(Ally düşüncelidir, fakat hayatında başka kayda değer bir şey olmadığı için gitmeye karar verir.) ";
        String kayanyazi25= "(Belki de içimdeki karmaşık düşüncelere bir macera fırsatı olabilir.) ";
        String kayanyazi26= "Kararını veren Ally, telefonunu sıkıca kavrar ve mesajdaki konumu takip etmeye başlar. ";
        String kayanyazi27= "Korku ve merak karışımı bir heyecanla, kararan sokakları geçerek hedefine doğru ilerler. ";
        String kayanyazi28= "Kısa bir süre sonra, Ally, gönderilen konumun belirtildiği bir binanın önünde durur. ";
        String kayanyazi29= "Bina, eski ve harap bir görünüme sahiptir. Kapısı yarı açık durumdadır. ";
        String kayanyazi30= "Titreyen elleriyle kapıyı iten Ally, yavaşça binanın içine girer. ";
        String kayanyazi31= "Işıksız koridorlar boyunca ilerlerken ayak sesleri yankılanır. Zihni, içsel bir savaşın ortasında gibidir. ";
        String kayanyazi32= "Birden, koridorun sonundaki bir kapının ışık sızdırdığını fark eder. ";
        String kayanyazi33= "Adımlarını hızlandırır ve kapıya yaklaşır. Kapının ardında, bilinmeyenle yüzleşeceği gerçeğiyle bir kez daha yüzleşir. ";
        String kayanyazi34= "Ally, son bir derin nefes alır ve kapıyı açar. Işık, içeriye dökülürken, karşısında bekleyen kişiyle göz göze gelir. ";
        String kayanyazi35= "Görüyorsun dimi Ally, buraya kadar geldin. ";
        String kayanyazi36= "Neden beni buraya çağırdın? ";
        String kayanyazi37= "Ne yaptığının farkına varmanı istiyorum. ";
        String kayanyazi38= "Ne demek istiyorsun? ";
        String kayanyazi39= "Uzun zaman boyunca kendini toplumdan soyutladın. ";
        String kayanyazi40= "Onlardan farklı olduğunu düşündün. ";
        String kayanyazi41= "Ama içgüdün olan merağın yüzünden buraya kadar geldin. ";
        String kayanyazi42= "Tüm davranışların tipik bir insanın davranışı ile aynıydı. ";
        String kayanyazi43= "Kendi benliğini bulamadığın için aldığın ilk mesaja sarıldın. ";
        String kayanyazi44= "Burada seni öldürecek birisi de olabilirdi, düşünmedin bile. ";
        String kayanyazi45= "İnsanların bir saniye olmadan düşünmeden hareket etmesi gibiydi. ";
        String kayanyazi46= "Aldığın bir mesajı kurtuluş sandın. ";
        String kayanyazi47= "Peki ya sen, oyuncu? ";
        String kayanyazi48= "Sırf merak ettiğin için hikayenin sonuna kadar geldin. ";
        String kayanyazi49= "Lütfen farklı olduğunu düşünme, bu özel olmadığın anlamına gelmez. ";
        String kayanyazi50= "Sadece, dünyada senin gibi düşünecek ve sana sarılacak insanlar olduğunun farkına var. ";
        String kayanyazi51= "Ally, lütfen kendine iyi bak ve hayatına çekidüzen ver. ";
        String kayanyazi52= "Eski bir dostun olarak seni düşünmek benim görevim. ";
        String kayanyazi53= "Noah, ne diyeceğimi bilmiyorum. ";
        String kayanyazi54= "Bir şey demene gerek yok, sadece kendine iyi bak. ";




        String musicFile = "sounds/vibration.mp3";
        Media mediam = new Media(new File(musicFile).toURI().toString());
        mediaPlayer2 = new MediaPlayer(mediam);

        String themeFile = "sounds/gametheme.mp3";
        Media media3 = new Media(new File(themeFile).toURI().toString());
        mediaPlayer3 = new MediaPlayer(media3);


        List<YazıAnimasyonu> animasyonListesi = new ArrayList<>();
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi1, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi2, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi3, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi4, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi5, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi6, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi7, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi8, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi9, sahne1yazi, -240, () -> {
            mediaPlayer2.play();
        },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi10, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/telefon1.png";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0,backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
        },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi11, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/telmesaj1.png";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0,backImageView12);
            sahne1.getChildren().remove(1);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
        },() -> {
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi12, sahne1yazi, -240, () -> {
        },() -> {
            AllyName.setText("Unknown");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi13, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/telmesaj2.png";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0,backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
        },() -> {
            AllyName.setText("Ally");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi14, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/telmesaj3.png";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0,backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
        },() -> {
            AllyName.setText("Ally");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi15, sahne1yazi, -240, () -> {
        },() -> {
            AllyName.setText("Unknown");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi16, sahne1yazi, -240, () -> {},() -> {
            AllyName.setText("Ally");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi17, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/telmesaj4.png";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0, backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);},
                () -> {}
        ));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi18, sahne1yazi, -240, () -> {},() -> {
            AllyName.setText("Ally");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi19, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi20, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/telmesaj5.png";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0, backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi21, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/telmesaj6.png";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0, backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
            },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi22, sahne1yazi, -240, () -> {},() -> {
            AllyName.setText("Unknown");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi23, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/telmesaj7.png";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0, backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
            },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi24, sahne1yazi, -240, () -> {},() -> {
            AllyName.setText("Ally");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi25, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi26, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/3.jpg";
            Image backImage123 = new Image(new File(backPath1).toURI().toString());
            ImageView backPhoto = new ImageView(backImage123);
            backPhoto.fitWidthProperty().bind(scene1.widthProperty());
            backPhoto.fitHeightProperty().bind(scene1.heightProperty());

            String chrPath1 = "src/ingame/characters/sm1_sensei_normal.png";
            Image ekImage0 = new Image(new File(chrPath1).toURI().toString());
            ImageView ImageView13 = new ImageView(ekImage0);
            ImageView13.setFitWidth(525); // İstenilen genişlik değerini belirleyin
            ImageView13.setFitHeight(1000); // İstenilen yükseklik değerini belirleyin


            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0, backPhoto);
            sahne1.getChildren().add(1, ImageView13);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
        },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi27, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi28, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/4.jpg";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0,backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
        },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi29, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi30, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/5.jpg";
            Image backImage1 = new Image(new File(backPath1).toURI().toString());
            ImageView backImageView12 = new ImageView(backImage1);
            backImageView12.fitWidthProperty().bind(scene1.widthProperty());
            backImageView12.fitHeightProperty().bind(scene1.heightProperty());

            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0,backImageView12);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
        },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi31, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi32, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi33, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi34, sahne1yazi, -240, () -> {
            String backPath1 = "src/images/5.jpg";
            Image backImage123 = new Image(new File(backPath1).toURI().toString());
            ImageView backPhoto = new ImageView(backImage123);
            backPhoto.fitWidthProperty().bind(scene1.widthProperty());
            backPhoto.fitHeightProperty().bind(scene1.heightProperty());

            String chrPath2 = "src/ingame/characters/ssn_sports_angry.png";
            Image ekImagem1 = new Image(new File(chrPath2).toURI().toString());
            ImageView ImageView14 = new ImageView(ekImagem1);
            ImageView14.setFitWidth(480); // İstenilen genişlik değerini belirleyin
            ImageView14.setFitHeight(1000); // İstenilen yükseklik değerini belirleyin
            StackPane.setAlignment(ImageView14, Pos.BOTTOM_RIGHT);


            sahne1.getChildren().remove(0);
            sahne1.getChildren().add(0, backPhoto);
            sahne1.getChildren().add(1, ImageView14);
            sahne1vbox.getChildren().remove(sahne1);
            sahne1vbox.getChildren().add(sahne1);
        },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi35, sahne1yazi, -240, () -> {
        },() -> {
            AllyName.setText("Noah");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi36, sahne1yazi, -240, () -> {
        },() -> {
            AllyName.setText("Ally");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi37, sahne1yazi, -240, () -> {
        },() -> {
            AllyName.setText("Noah");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi38, sahne1yazi, -240, () -> {
        },() -> {
            AllyName.setText("Ally");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi39, sahne1yazi, -240, () -> {},() -> {
            AllyName.setText("Noah");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi40, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi41, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi42, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi43, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi44, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi45, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi46, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi47, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi48, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi49, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi50, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi51, sahne1yazi, -240, () -> {},() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi52, sahne1yazi, -240, () -> {
        },() -> {}));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi53, sahne1yazi, -240, () -> {
        },() -> {
            AllyName.setText("Ally");
        }));
        animasyonListesi.add(new YazıAnimasyonu(kayanyazi54, sahne1yazi, -240, () -> {
            mediaPlayer3.stop();


            Media outro1 = new Media(new File("src/videos/outro.mp4").toURI().toString());
            MediaPlayer outro = new MediaPlayer(outro1);
            MediaView mediaView100 = new MediaView(outro);
            mediaView100.fitWidthProperty().bind(primaryStage.widthProperty());
            mediaView100.fitHeightProperty().bind(primaryStage.heightProperty());

            Scene scene10 = new Scene(new Group(mediaView100));
            primaryStage.setScene(scene10);
            primaryStage.setFullScreen(isFullScreen);
            outro.play();

            Stage currentStage = (Stage) primaryStage.getScene().getWindow();

            outro.setOnEndOfMedia(() -> {
                currentStage.close();
            });

        },() -> {
            AllyName.setText("Noah");
        }));


        sahne1.getChildren().add(sahne1yazi);

        sahne1.setOnMouseEntered(event -> {
            if (!animasyonListesi.get(animasyonIndex).isAnimasyonTamamlandı()) {
                animasyonListesi.get(animasyonIndex).baslat();
            }
        });

        sahne1.setOnMouseClicked(event -> {
            if (!animasyonListesi.get(animasyonIndex).isAnimasyonTamamlandı()) {
                animasyonListesi.get(animasyonIndex).tamamla();
            } else {
                animasyonIndex++;
                if (animasyonIndex < animasyonListesi.size()) {
                    animasyonListesi.get(animasyonIndex).baslat();
                } else {
                }
            }
        });

        sahne1vbox.prefHeightProperty().bind(scene1.heightProperty());
        sahne1vbox.prefWidthProperty().bind(scene1.widthProperty());

        sahne1vbox.getChildren().add(sahne1);


        window.setTitle("Beyond The Veil");
        window.setScene(scene1);
        window.setOpacity(opacity);
        window.setFullScreen(isFullScreen);
        window.show();
    }
}
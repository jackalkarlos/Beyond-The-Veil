import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager extends Application{
    public static void main(String[] args) {
        checkifSettings();
        launch(args); //bro just testin
    }
    private static final String FILE_PATH = "settings.conf"; // ayarlari okusun bi zahmet
    public static void checkifSettings() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            String audio = properties.getProperty("audio");
            String opacity = properties.getProperty("opacity");
            String resolution = properties.getProperty("resolution");
            String fullScreen = properties.getProperty("full-screen");
            String level = properties.getProperty("level");
            fileInputStream.close();
            if (audio == null || audio == "null" || opacity == null || opacity == "null" || resolution == null || resolution == "null" || fullScreen == null || fullScreen == "null" || level == null|| level == "null") {
                throw new IOException("abooo");
            }
        } catch (IOException e) {
            try {
                Properties properties = new Properties();
                properties.setProperty("audio", "50");
                properties.setProperty("opacity", "50");
                properties.setProperty("resolution", "1920x1080");
                properties.setProperty("level", "1");
                properties.setProperty("full-screen", "True");
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                properties.store(fileOutputStream, "Settings");
                fileOutputStream.close();
            } catch (IOException ex) {
                System.err.println("Hata: " + ex.getMessage());
            }
        }
    }
    public static int returnAudioValue() {
        int audioVal=0;
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            String audioString = properties.getProperty("audio"); // "audio" isimli özelliğin değerini oku
            audioVal = Integer.parseInt(audioString);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Hata: " + e.getMessage());
        }
        return audioVal;

    }
    public static int returnOpacityValue() {
        int opacityVal=0;
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            String audioString = properties.getProperty("opacity"); // "opacity" isimli özelliğin değerini oku
            opacityVal = Integer.parseInt(audioString);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Hata: " + e.getMessage());
        }
        return opacityVal;
    }
    public static String returnResValue() {
        String ResVal = "0";
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            ResVal = properties.getProperty("resolution");
        } catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        }
        return ResVal;
    }
    public static String returnlevelValue() {
        String strLevel = "0";
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            strLevel = properties.getProperty("level");
        } catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        }
        return strLevel;
    }
    public static String returnScreenValue() {
        String ScreenVal = "False";
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            ScreenVal = properties.getProperty("full-screen");
        } catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        }
        return ScreenVal;
    }

    @Override
    public void start(Stage primaryStage) {
        int audioValue = returnAudioValue();
        int opacityValue = returnOpacityValue();
        String resValue = returnResValue();
        String screenValue = returnScreenValue();
        int listindex = -1; // Başlangıç değeri atanması
        if (resValue.equals("800x600")) {
            listindex=0;
        } else if (resValue.equals("1280x720")) {
            listindex=1;
        } else if (resValue.equals("1920x1080")) {
            listindex=2;
        }
        Text audioText = new Text();
        audioText.setFont(new Font(20));
        audioText.setTextAlignment(TextAlignment.CENTER);
        audioText.setText("Ses Ayarı");

        Slider slider = new Slider(0, 100, audioValue);
        slider.setBlockIncrement(10);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue <? extends Number >
                                                observable, Number oldValue, Number newValue)
                    {
                    }
                }); // burayi internetten arakladim, degisen slider degerini mantikli bir kodla almanin bir yolu yoktu

        Text opacityText = new Text();
        opacityText.setFont(new Font(20));
        opacityText.setTextAlignment(TextAlignment.CENTER);
        opacityText.setText("Parlaklık");

        Slider slider2 = new Slider(0, 100, opacityValue);
        slider2.setBlockIncrement(10);
        slider2.setMajorTickUnit(10);
        slider2.setMinorTickCount(0);
        slider2.setShowTickLabels(true);
        slider2.setSnapToTicks(true);

        //System.out.println(slider2.getValue());
        slider2.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue <? extends Number >
                                                observable, Number oldValue, Number slider2NewValue)
                    {
                    }
                });

        Text resText = new Text();
        resText.setFont(new Font(20));
        resText.setTextAlignment(TextAlignment.CENTER);
        resText.setText("Çözünürlük");

        String week_days[] =
                {"800x600", "1280x720", "1920x1080"};

        ComboBox combo_box =
                new ComboBox(FXCollections
                        .observableArrayList(week_days));
        boolean screenBoolean=Boolean.parseBoolean(screenValue);
        combo_box.getSelectionModel().select(listindex);


        CheckBox cb1 = new CheckBox();
        cb1.setText("Tam Ekran");
        cb1.setSelected(screenBoolean);
        cb1.setFont(Font.font("Arial", 17));

        Image backgroundImage = new Image("images/settingswp.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        StackPane stackPane = new StackPane();
        // Scene ve Stage ayarları
        Scene scene = new Scene(stackPane, 350, 500);

        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(0, 0, 0, 0));
        vbox1.getChildren().addAll(audioText, slider, opacityText, slider2, resText, combo_box);
        vbox1.setSpacing(10);
        vbox1.setAlignment(Pos.TOP_CENTER);

        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
        backgroundImageView.setOpacity(0.5);
        Button kaydet = new Button("Kaydet");
        kaydet.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        kaydet.setOnAction(event -> {
            double sliderDeger = slider.getValue(); // Ses
            int sliderDeger2 = (int) sliderDeger;
            String sesDegeri = Integer.toString(sliderDeger2);

            double slider2Deger = slider2.getValue(); // Opaklık
            int slider2Deger2 = (int) slider2Deger;
            String opacityDegeri = Integer.toString(slider2Deger2);

            String levelValue = returnlevelValue(); // Seviye - Kullanıcı tarafından değiştirilemez

            boolean isSelected = cb1.isSelected(); // Tam ekran
            String fullScreenValue;
            if (isSelected) {
                fullScreenValue = "True";
            } else {
                fullScreenValue = "False";
            }
            Object res1 = combo_box.getValue();
            String resolution = res1.toString();

            try {
                Properties properties = new Properties();
                properties.setProperty("audio", sesDegeri);
                properties.setProperty("opacity", opacityDegeri);
                properties.setProperty("resolution", resolution);
                properties.setProperty("level", levelValue);
                properties.setProperty("full-screen", fullScreenValue);
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                properties.store(fileOutputStream, "Settings");
                fileOutputStream.close();
            } catch (IOException ex) {
                System.err.println("Hata: " + ex.getMessage());
            }
        });
        Button menuyedon = new Button("Menüye Dön");
        menuyedon.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        menuyedon.setOnAction(event -> {
            Stage oyunMenuStage = new Stage();
            OyunMenu oyunMenuApp = new OyunMenu();
            oyunMenuApp.start(oyunMenuStage);
            primaryStage.close();
        });


        Image anime = new Image("images/Logosiyah.png");
        ImageView animeView = new ImageView(anime);
        animeView.setFitHeight(150);
        animeView.setFitWidth(200);


        stackPane.getChildren().addAll(backgroundImageView, vbox1, cb1,animeView,kaydet,menuyedon);
        StackPane.setAlignment(cb1, Pos.CENTER_LEFT);
        StackPane.setAlignment(kaydet, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(menuyedon, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(animeView, new Insets(300, 0, 0, -20));


        primaryStage.setTitle("Settings Manager");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

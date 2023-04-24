import java.io.*;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;

public class SettingsManager extends Application{
    public static void main(String[] args) {
        checkifSettings();
        launch(args); //
    }
    private static final String FILE_PATH = "settings.conf"; // ayarlari okusun bi zahmet
    public static void checkifSettings() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            properties.getProperty("audio");
            properties.getProperty("opacity");
            properties.getProperty("resolution");
            properties.getProperty("full-screen");
            properties.getProperty("level");
            fileInputStream.close();
        } catch (IOException e) {
            try {
                Properties properties = new Properties();
                properties.setProperty("audio", "50");
                properties.setProperty("opacity", "50");
                properties.setProperty("resolution", "1920x1080");
                properties.setProperty("level", "1");
                properties.setProperty("full-screen", "True");
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
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

    @Override
    public void start(Stage primaryStage) {
        int audioValue = returnAudioValue();
        int opacityValue = returnOpacityValue();
        String resValue = returnResValue();


        Text audioText = new Text();
        audioText.setFont(new Font(20));
        audioText.setTextAlignment(TextAlignment.CENTER);
        audioText.setText("Game Volume");

        Slider slider = new Slider(0, 100, audioValue);
        slider.setBlockIncrement(10);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);

        //System.out.println(slider.getValue());
        slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue <? extends Number >
                                                observable, Number oldValue, Number newValue)
                    {
                    }
                });

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

        // Create a combo box
        ComboBox combo_box =
                new ComboBox(FXCollections
                        .observableArrayList(week_days));


        CheckBox cb1 = new CheckBox();
//A checkbox with a string caption
        cb1.setText("Tam Ekran");
        cb1.setSelected(true);
        //boolean isSelected = checkBox1.isSelected();
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
        Button menuyedon = new Button("Menüye Dön");
        menuyedon.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Image anime = new Image("images/setimage.png");
        ImageView animeView = new ImageView(anime);
        animeView.setFitHeight(215);
        animeView.setFitWidth(245);


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
    /*
            properties.setProperty("audio", String.valueOf(audio));
            properties.setProperty("opacity", String.valueOf(opacity));
            properties.setProperty("resolution", resolution);

            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            properties.store(fileOutputStream, null);
            fileOutputStream.close();

     */

}

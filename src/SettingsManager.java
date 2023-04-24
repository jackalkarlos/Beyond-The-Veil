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
            fileInputStream.close();
        } catch (IOException e) {
            try {
                Properties properties = new Properties();
                properties.setProperty("audio", "50");
                properties.setProperty("opacity", "50");
                properties.setProperty("resolution", "800x600");
                properties.setProperty("level", "1");
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                properties.store(fileOutputStream, "Varsayılan Ayarlar");
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

    @Override
    public void start(Stage primaryStage) {
        int audioValue = returnAudioValue();

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

        System.out.println(slider.getValue());
        slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue <? extends Number >
                                                observable, Number oldValue, Number newValue)
                    {

                        System.out.println(newValue.intValue());
                    }
                });

        Image backgroundImage = new Image("images/settingswp.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        StackPane stackPane = new StackPane();
        // Scene ve Stage ayarları
        Scene scene = new Scene(stackPane, 350, 500);
        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(0, 0, 0, 0));
        vbox1.getChildren().addAll(audioText, slider);
        vbox1.setSpacing(10);
        vbox1.setAlignment(Pos.TOP_CENTER);
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
        backgroundImageView.setOpacity(0.5);
        stackPane.getChildren().addAll(backgroundImageView, vbox1);
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

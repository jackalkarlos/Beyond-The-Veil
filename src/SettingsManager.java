import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsManager extends Application{
    public static void main(String[] args) {
        readSettings();
        //updateSettings(); -> gui icerisinde edit atacagimiz icin ornek bir fonksiyon bulup kodun sonuna attim, gui icerisinde ayarlama yapilcak
        // bunun icin guide secmeyle yukseltilen ayarlar yapacagiz
        launch(args); //
    }
    private static final String FILE_PATH = "settings.conf"; // ayarlari okusun bi zahmet
    public static void readSettings() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();

            //productiondan sonra burasi silinecek
            System.out.println("[audio]: " + properties.getProperty("audio"));
            System.out.println("[opacity]: " + properties.getProperty("opacity"));
            System.out.println("[resolution]: " + properties.getProperty("resolution"));
            System.out.println("[level]: " + properties.getProperty("level"));

        } catch (IOException e) {
            //System.err.println("Hata: " + e.getMessage());
            //System.out.println("Dosya bulunamadı, varsayılan ayarlar yazılıyor.");
            try {
                Properties properties = new Properties();
                properties.setProperty("audio", "50");
                properties.setProperty("opacity", "50");
                properties.setProperty("resolution", "800x600");
                properties.setProperty("level", "1");

                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                properties.store(fileOutputStream, "Varsayılan Ayarlar");
                fileOutputStream.close();
                System.out.println("Varsayılan ayarlar dosyaya kaydedildi.");
            } catch (IOException ex) {
                System.err.println("Hata: " + ex.getMessage());
            }
        }
    }
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Image backgroundImage = new Image("images/arka_plan_resmi.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        Button btnBaslat = new Button("Güncelle");
        btnBaslat.setFont(Font.font(24));
        btnBaslat.setOnAction(event -> {
            // Ayar kaydetme fonksiyonu, butonlar ve düğmeler tanımlandıktan sonra burası güncellenecek
            System.out.println("Kaydedildi!");
        });

        // Ayarlar düğmesi
        Button btnAyarlar = new Button("Menüye Dön.");
        btnAyarlar.setFont(Font.font(24));
        btnAyarlar.setOnAction(event -> {
            // Ayarları başlatan fonksiyon buraya tanımlanacak
            // Yeni bir Stage (pencere) oluşturarak SettingsManager uygulamasını başlat
            Stage oyunMenuStage = new Stage();
            OyunMenu oyunMenuApp = new OyunMenu();
            oyunMenuApp.start(oyunMenuStage);
            primaryStage.close();
        });

        // Menü metni
        Text txtMenu = new Text("Settings");
        txtMenu.setFont(Font.font(36));
        //txtMenu.setFill(Color.WHITE);

        // Ortalanmış düğmeleri içeren StackPane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, txtMenu, btnBaslat, btnAyarlar);
        StackPane.setAlignment(backgroundImageView, Pos.CENTER);
        StackPane.setAlignment(txtMenu, Pos.TOP_CENTER);
        StackPane.setAlignment(btnBaslat, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(btnAyarlar, Pos.BOTTOM_RIGHT);
        //StackPane.setMargin(btnBaslat, new Insets(0, 0, 50, 0));
        //StackPane.setMargin(btnAyarlar, new Insets(0, 0, 20, 0));

        // Scene ve Stage ayarları
        Scene scene = new Scene(stackPane, 800, 600);
        primaryStage.setTitle("Oyun Menüsü");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*
    public static void updateSettings() {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ses ayarini girin (int): ");
            int audio = scanner.nextInt();
            System.out.println("Parlaklik ayarini girin (int): ");
            int opacity = scanner.nextInt();
            System.out.println("Cozunurluk ayarini girin (" + properties.getProperty("resolution") + "): ");
            String resolution = scanner.next();

            properties.setProperty("audio", String.valueOf(audio));
            properties.setProperty("opacity", String.valueOf(opacity));
            properties.setProperty("resolution", resolution);

            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            properties.store(fileOutputStream, null);
            fileOutputStream.close();

            System.out.println("Ayarlar guncellendi.");
        } catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        }
     }
     */

}

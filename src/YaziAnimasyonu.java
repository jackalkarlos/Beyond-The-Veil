import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

class YazıAnimasyonu {
    private String yazı;
    private Text yazıNesnesi;
    private boolean animasyonTamamlandı;
    private int currentIndex;
    private double yBaslangic;
    private Timeline typingTimeline;

    public YazıAnimasyonu(String yazı, Text yazıNesnesi) {
        this.yazı = yazı;
        this.yazıNesnesi = yazıNesnesi;
        this.animasyonTamamlandı = false;
        this.currentIndex = 0;
        this.yazıNesnesi.setTranslateX(180);
        this.yazıNesnesi.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        this.yazıNesnesi.setFill(Color.WHITESMOKE);
        this.yazıNesnesi.setWrappingWidth(1550);
        this.yazıNesnesi.setLineSpacing(10);
        this.yBaslangic = -240; // Başlangıç y konumu burada ayarlanacak
        this.yazıNesnesi.setTranslateY(yBaslangic);
    }

    public boolean isAnimasyonTamamlandı() {
        return animasyonTamamlandı;
    }

    public void baslat() {
        typingTimeline = new Timeline(
                new KeyFrame(Duration.millis(Game.yazidelay), e -> {
                    if (currentIndex <= yazı.length()) {
                        String currentText = yazı.substring(0, currentIndex);
                        int newLineIndex = currentText.indexOf("\n", currentIndex - 1);
                        if (newLineIndex >= 0) {
                            int numLines = currentText.substring(0, newLineIndex).split("\n").length;
                            double newY = yBaslangic + numLines * yazıNesnesi.getLineSpacing();
                            yazıNesnesi.setTranslateY(newY);
                        }
                        yazıNesnesi.setText(currentText);
                        currentIndex++;
                        // Metin tamamlandığında animasyonTamamlandı değerini güncelle
                        if (currentIndex == yazı.length()) {
                            animasyonTamamlandı = true;
                            typingTimeline.stop(); // Animasyon tamamlandığında durdur
                        }
                    }
                })
        );
        typingTimeline.setCycleCount(Timeline.INDEFINITE);
        typingTimeline.play();
    }

    public void tamamla() {
        yazıNesnesi.setText(yazı);
        animasyonTamamlandı = true;
        typingTimeline.stop(); // Animasyon tamamlandığında durdur
    }
}

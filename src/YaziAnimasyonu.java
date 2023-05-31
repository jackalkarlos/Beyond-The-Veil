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
    private Runnable tamamlanmaOlayı;
    private Runnable baslangicOlayi;

    public YazıAnimasyonu(String yazı, Text yazıNesnesi, double yBaslangic, Runnable tamamlanmaOlayı, Runnable baslangicOlayi) {
        this.yazı = yazı;
        this.yazıNesnesi = yazıNesnesi;
        this.animasyonTamamlandı = false;
        this.currentIndex = 0;
        this.yazıNesnesi.setTranslateX(180);
        this.yazıNesnesi.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        this.yazıNesnesi.setFill(Color.WHITESMOKE);
        this.yazıNesnesi.setWrappingWidth(1500);
        this.yazıNesnesi.setLineSpacing(10);
        this.yBaslangic = yBaslangic;
        this.yazıNesnesi.setTranslateY(yBaslangic);
        this.tamamlanmaOlayı = tamamlanmaOlayı;
        this.baslangicOlayi = baslangicOlayi;
    }

    public boolean isAnimasyonTamamlandı() {
        return animasyonTamamlandı;
    }

    public void baslat() {
        if (baslangicOlayi != null) {
            baslangicOlayi.run();
        }
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
                            if (tamamlanmaOlayı != null) {
                                tamamlanmaOlayı.run();
                            }
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
        if (tamamlanmaOlayı != null) {
            tamamlanmaOlayı.run();
        }
    }
}

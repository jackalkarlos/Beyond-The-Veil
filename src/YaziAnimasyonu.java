import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

class YazıAnimasyonu {
    private String yazı;
    private Text yazıNesnesi;
    private boolean animasyonTamamlandı;
    private int currentIndex;

    public YazıAnimasyonu(String yazı, Text yazıNesnesi) {
        this.yazı = yazı;
        this.yazıNesnesi = yazıNesnesi;
        this.animasyonTamamlandı = false;
        this.currentIndex = 0;
    }

    public boolean isAnimasyonTamamlandı() {
        return animasyonTamamlandı;
    }

    public void baslat() {
        Timeline typingTimeline = new Timeline(
                new KeyFrame(Duration.millis(Game.yazidelay), e -> {
                    if (currentIndex <= yazı.length()) {
                        String currentText = yazı.substring(0, currentIndex);
                        int newLineIndex = currentText.indexOf("\n", currentIndex - 1);
                        if (newLineIndex >= 0) {
                            int numLines = currentText.substring(0, newLineIndex).split("\n").length;
                            yazıNesnesi.setTranslateY(-240 + numLines * 46);
                        }
                        yazıNesnesi.setText(currentText);
                        currentIndex++;
                        // Metin tamamlandığında animasyonTamamlandı değerini güncelle
                        if (currentIndex == yazı.length()) {
                            animasyonTamamlandı = true;
                        }
                    }
                })
        );
        typingTimeline.setCycleCount(yazı.length() + 1);
        typingTimeline.play();
    }

    public void tamamla() {
        yazıNesnesi.setText(yazı);
        animasyonTamamlandı = true;
    }
}

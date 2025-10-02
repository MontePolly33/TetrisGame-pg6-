package sound;

import javafx.scene.media.AudioClip;
import java.net.URL;

public class SoundManager {
    private static AudioClip menuSound;
    private static AudioClip moveTurnSound;
    private static AudioClip eraseLineSound;
    private static AudioClip levelUpSound;
    private static AudioClip gameFinishSound;

    static {
        try {
            URL resource = SoundManager.class.getResource("/menu2.wav");
            if (resource == null) throw new RuntimeException("menu2.wav not found!");
            menuSound = new AudioClip(resource.toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            URL resource = SoundManager.class.getResource("/move-turn.wav");
            if (resource == null) throw new RuntimeException("move-turn.wav not found!");
            moveTurnSound = new AudioClip(resource.toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            URL resource = SoundManager.class.getResource("/erase-line.wav");
            if (resource == null) throw new RuntimeException("erase-line.wav not found!");
            eraseLineSound = new AudioClip(resource.toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            URL resource = SoundManager.class.getResource("/level-up.wav");
            if (resource == null) throw new RuntimeException("level-up.wav not found!");
            levelUpSound = new AudioClip(resource.toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            URL resource = SoundManager.class.getResource("/game-finish.wav");
            if (resource == null) throw new RuntimeException("game-finish.wav not found!");
            gameFinishSound = new AudioClip(resource.toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playMenuSound() {
        if (menuSound != null) menuSound.play();
    }

    public static void stopMenuSound() {
        if (menuSound != null) menuSound.stop();
    }

    public static void playMoveTurnSound() {
        if (moveTurnSound != null)
            moveTurnSound.setVolume(0.1);
        moveTurnSound.play();

    }

    public static void playEraseLineSound() {
        if (eraseLineSound != null) eraseLineSound.play();
    }

    public static void playLevelUpSound() {
        if (levelUpSound != null) levelUpSound.play();
    }

    public static void playGameFinishSound() {
        if (gameFinishSound != null) gameFinishSound.play();
    }
}

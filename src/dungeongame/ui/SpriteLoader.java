package dungeongame.ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SpriteLoader {

    private static final Map<String, BufferedImage> cache = new HashMap<>();

    public static BufferedImage load(String path) {
        return cache.computeIfAbsent(path, p -> {
            try (InputStream is = SpriteLoader.class.getClassLoader().getResourceAsStream(p)) {
                if (is == null) {
                    System.err.println("Спрайт не найден: " + p);
                    return null;
                }
                return ImageIO.read(is);
            } catch (IOException e) {
                System.err.println("Ошибка загрузки: " + p + " → " + e.getMessage());
                return null;
            }
        });
    }
}

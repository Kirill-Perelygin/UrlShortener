import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UserURLs {

    public static final String fixURL = "https://www.clck.ru/";
    public static String shortUrl;

    // TODO придумать метод, который будет рандомно генерировать 6 знаков UPPERlowerCaSe+numbers
    public static String createShortUrl(String longUrl){
        shortUrl = fixURL + "357";
        System.out.println("Ваша сокращенная ссылка равняется " + shortUrl);
        return shortUrl;
    }

    public static int getCounter(int counter) {
        System.out.println("Счетчик ваших переходов равен: " + counter);
        return counter;
    }

    public static int changeCounter (int counter) {
        System.out.println("Счетчик ваших переходов равен: " + (counter + 1));
        return counter;
    }

    public static void visitShortUrl(String longUrl) {
        try {
            Desktop.getDesktop().browse(new URI(longUrl));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}

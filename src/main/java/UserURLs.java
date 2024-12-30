import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class UserURLs {

    public static final String fixURL = "clck.ru/";
    public static String shortUrl;

    // TODO придумать метод, который будет рандомно генерировать 6 знаков UPPERlowerCaSe+numbers -> https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    public static String createShortUrl(String longUrl) {
        Random rand = new Random();
        String str = rand.ints(48, 123)
                .filter(num -> (num<58 || num>64) && (num<91 || num>96))
                .limit(5)
                .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();
        shortUrl = fixURL + str;
        System.out.println("Ваша сокращенная ссылка равняется " + shortUrl);
        return shortUrl;
    }

    public static int getCounter(int counter) {
        System.out.println("Счетчик ваших переходов равен: " + counter);
        return counter;
    }

    public static int changeCounter(int counter) {
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

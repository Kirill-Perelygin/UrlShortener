import java.util.UUID;

public class User {

    public static final String fixURL = "https://www.clck.ru/";
    public static String shortUrl;
    public static int counter;


    public static String createUserUuid(String uuid) {
        UUID newUUID = UUID.randomUUID();
        String uuidAsString = newUUID.toString();
        System.out.println("Твой специльно сгенерированный UUID: " + uuidAsString);
        return uuidAsString;
    };

    public static String createShortUrl(String longUrl, int counter){
        shortUrl = fixURL + "357";
        System.out.println("Ваша сокращенная ссылка равняется " + shortUrl + "Счетчик ваших переходов установлен на " + counter);
        return shortUrl;
    }

    public static int getCounter(int counter) {
        System.out.println("Счетчик ваших переходов равен: " + counter);
        return counter;
    }

    public static void main(String[] args) {

    }
}
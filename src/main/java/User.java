import java.util.UUID;

public class User {

    public static final String fixURL = "https://www.clck.ru/";
    public static String shortUrl;
    public static int counter;


    public static String createUserUuid(String uuid) {
        UUID newUUID = UUID.randomUUID();
        String uuidAsString = newUUID.toString();
        System.out.println("Ваш UUID равняется: " + uuidAsString);
        return uuidAsString;
    };

    public static String createShortUrl(String longUrl){
        shortUrl = fixURL + "357";
        System.out.println("Ваша сокращенная ссылка равняется " + shortUrl);
        return shortUrl;
    }
    

    public static void main(String[] args) {
    }
}
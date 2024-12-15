public class UserURLs {

    public static final String fixURL = "https://www.clck.ru/";
    public static String shortUrl;


    public static String createShortUrl(String longUrl){
        shortUrl = fixURL + "357";
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

    public static String visitShortUrl(String shortUrl) {
        
    }

    public static void main(String[] args) {

    }
}

import java.util.Scanner;

public class Main extends User {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userUUID = scanner.next();
        createUserUuid(userUUID);
        String inputUrl = scanner.next();
        createShortUrl(inputUrl);
    }
}

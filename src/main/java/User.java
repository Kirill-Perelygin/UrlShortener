import java.util.UUID;

public class User {

    public static int counter;


    public static String createUserUuid(String uuid) {
        UUID newUUID = UUID.randomUUID();
        String uuidAsString = newUUID.toString();
        System.out.println("Твой специльно сгенерированный UUID: " + uuidAsString);
        return uuidAsString;
    };

    public static void main(String[] args) {

    }
}
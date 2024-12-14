import java.sql.SQLException;
import java.util.Scanner;

public class Main extends User {

   public static int choiceValue;
   public static String userName;
   public static String userUUID;

    public static void main(String[] args) {

        System.out.println("Привет! Вы уже является нашим пользователем или вам нужно зарегистрироваться?");
        System.out.println("1. Я пользователь - введу UUID");
        System.out.println("2. Я новенький - зарегистрируйте меня");
        Scanner scanner = new Scanner(System.in);
        choiceValue = scanner.nextInt();

        switch (choiceValue){
            case(1): {
                System.out.println("Введи ранее выданный тебе UUID");
                userUUID = scanner.next();
                try {
                    UserDatabase.checkUserExistance(userUUID);
                }
                catch (SQLException e){
                    throw new RuntimeException(e);
                }
                break;
            }
            case(2): {
                String userUUID = createUserUuid(userName);
                try {
                    UserDatabase.addUserToTheTable(userUUID);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            default: ;
        }
    }
}

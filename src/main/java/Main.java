import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static int choiceValue;
    public static int secondChoiceValue;
    public static String userName;
    public static String userUUID;
    public static String longUrl;
    public static String shortUrl;

    public static void main(String[] args) {


        System.out.println("Привет! Вы уже является нашим пользователем или вам нужно зарегистрироваться?");
        System.out.println("1. Я пользователь - введу UUID");
        System.out.println("2. Я новенький - зарегистрируйте меня");
        Scanner scanner = new Scanner(System.in);
        choiceValue = scanner.nextInt();
            switch (choiceValue) {
                case (1): {
                    System.out.println("Введи ранее выданный тебе UUID");
                    userUUID = scanner.next();
                    // TODO Придумать регуляроное выражение, потому что сейчас это строка и пускает по любому символу
                    try {
                        UserDatabase.checkUserExistance(userUUID);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                // TODO Проверка на наличие записи теперь есть, но даже при наличии ошибки идем дальше по процессу -> нужно понять как этого избежать

                   /* System.out.println("У вас уже есть добавленные ссылки?");
                while (true) {
                    System.out.println("1. Введу ранее добавленную ссылку");
                    System.out.println("2. Мне нужно сгенерировать ссылку");
                    secondChoiceValue = scanner.nextInt();
                    switch (secondChoiceValue) {
                        case (1): {
                            System.out.println("Ха ха, стаб");
                            break;
                        }
                        case (2): {
                            System.out.println("Введите ссылку, которую нужно сократить");
                            longUrl = scanner.next();
                            shortUrl = User.createShortUrl(longUrl, 0);
                            break;
                        }
                    }
                }
            }
*/
                case (2): {
                    String userUUID = User.createUserUuid(userName);
                    try {
                        UserDatabase.addUserToTheTable(userUUID);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                default:
                    ;
            }

        }
    }
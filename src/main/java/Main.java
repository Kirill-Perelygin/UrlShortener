import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.Date;


public class Main {

    public static int choiceValue;
    public static int secondChoiceValue;
    public static int counterValue;
    public static String userName;
    public static String userUUID;
    public static String longUrl;
    public static String shortUrl;
    public static Boolean isBoolean = true;
    public static Boolean isBoolean2 = true;

    public static void main(String[] args) {
            System.out.println("Привет! Вы уже является нашим пользователем или вам нужно зарегистрироваться?");
        while (true) {
            isBoolean2 = true;
            System.out.println("1. Я пользователь - введу UUID");
            System.out.println("2. Я новенький - зарегистрируйте меня");
            Scanner scanner = new Scanner(System.in);
            choiceValue = scanner.nextInt();
            while (isBoolean2) {
                switch (choiceValue) {
                    case (1): {
                        System.out.println("Введи ранее выданный тебе UUID");
                        userUUID = scanner.next();
                        // TODO Придумать регуляроное выражение, потому что сейчас это строка и пускает по любому символу
                        try {
                            if (UserDatabase.checkUserExistance(userUUID)) {
                                isBoolean = true;
                                System.out.println("С вовзращением!");
                                System.out.println("Что хотите сделать?");
                                while (isBoolean) {
                                    System.out.println("1. Введу ранее добавленную ссылку");
                                    System.out.println("2. Мне нужно сгенерировать ссылку");
                                    System.out.println("3. Хочу узнать все свои ссылки");
                                    System.out.println("4. Удалить ранее сгенерированную ссылку");
                                    System.out.println("5. Хочу назад");
                                    secondChoiceValue = scanner.nextInt();
                                    switch (secondChoiceValue) {
                                        case (1): {
                                            System.out.println("Введите ранее выданную короткую ссылку");
                                            shortUrl = scanner.next();
                                            longUrl = UserDatabase.getLongUrl(shortUrl);
                                            UserURLs.visitShortUrl(longUrl);
                                            UserDatabase.counterPlus(shortUrl);
                                            // TODO добавить метод, который будет брать новый каунтер и выводить его как строку -> ваше количетсво переводов или типа того
                                            UserDatabase.getConter(shortUrl);
                                            UserDatabase.deleteMaxCounterRows();

                                            // TODO Метод, который получает из таблицы значение каунтера.
                                            break;
                                        }
                                        case (2): {
                                            System.out.println("Введите ссылку, которую нужно сократить");
                                            longUrl = scanner.next();
                                            shortUrl = UserURLs.createShortUrl(longUrl);
                                            counterValue = UserURLs.getCounter(0);
                                            UserDatabase.addingUserInfoToTheTable(userUUID, longUrl, shortUrl, counterValue);
                                            break;
                                        }
                                        case (3): {
                                            UserDatabase.selectAllUserUrls(userUUID);
                                            break;
                                        }
                                        case (4): {
                                            System.out.println("Введите ссылку, которую хотите удалить");
                                            shortUrl = scanner.next();
                                            UserDatabase.deletSelectedUrl(userUUID, shortUrl);
                                            break;
                                        }
                                        case (5): {
                                            isBoolean = false;
                                            break;
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Такого пользователя не знаем, попробуйте зарегистрироваться");
                            }
                            ;
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        isBoolean2 = false;
                        break;
                    }
                    // TODO Проверка на наличие записи теперь есть, но даже при наличии ошибки идем дальше по процессу -> нужно понять как этого избежать


                    case (2): {
                        String userUUID = User.createUserUuid(userName);
                        try {
                            UserDatabase.addUserToTheTable(userUUID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        isBoolean2 = false;
                        break;
                    }
                    default:

                        ;
                }

            }
        }
    }
}

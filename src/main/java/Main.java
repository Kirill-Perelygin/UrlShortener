import java.sql.SQLException;
import java.util.Scanner;

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
            System.out.println("3. Завершить работу программы");
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
                                while (isBoolean) {
                                System.out.println("Что хотите сделать?");
                                    System.out.println("1. Хочу перейти по ранее добавленной ссылке");
                                    System.out.println("2. Мне нужно сгенерировать ссылку");
                                    System.out.println("3. Хочу узнать все свои ссылки");
                                    System.out.println("4. Удалить ранее сгенерированную ссылку");
                                    System.out.println("5. Хочу назад");
                                    System.out.println("6. Завершить работу программы");
                                    secondChoiceValue = scanner.nextInt();
                                    switch (secondChoiceValue) {
                                        case (1): {
                                            System.out.println("Введите ранее выданную короткую ссылку");
                                            shortUrl = scanner.next();
                                            longUrl = UserDatabase.getLongUrl(userUUID, shortUrl);
                                            boolean result = UserDatabase.deleteBasedOnTimestamp();
                                            if (longUrl == null || !result || UserDatabase.getConter(shortUrl) == 0) {
                                                System.out.println("У пользователя нет такой короткой ссылки или она удалена из-за превышения срока");
                                                break;
                                            }
                                            UserURLs.visitShortUrl(longUrl);
                                            UserDatabase.counterMinus(shortUrl);
                                          //  UserDatabase.deleteMaxCounterRows();
                                            break;
                                        }
                                        case (2): {
                                            System.out.println("Введите ссылку, которую нужно сократить");
                                            longUrl = scanner.next();
                                            shortUrl = UserURLs.createShortUrl(longUrl);
                                            System.out.println("Вы хотите установить количество переходов?");
                                            System.out.println("1. Да");
                                            System.out.println("2. Нет");
                                            int counterNumberValue = scanner.nextInt();
                                            switch (counterNumberValue) {
                                                case (1): {
                                                    System.out.println("Введите желаемое количество переходов");
                                                    counterValue = scanner.nextInt();
                                                    UserURLs.getCounter(counterValue);
                                                    break;
                                                }
                                                case (2): {
                                                    System.out.println("Устанавливаем дефолтное количество переходов");
                                                    counterValue = 5; // TODO придумать количество переходов в конфиге
                                                    UserURLs.getCounter(counterValue);
                                                    break;
                                                }
                                                default: ;
                                            }
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
                                        case (6): {
                                            System.exit(0);
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

                    case (3): {
                        System.exit(0);
                    }
                    default:

                        ;
                }

            }
        }
    }
}

import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        UserInfo userInfo = new UserInfo();
        Mapper mapper = new Mapper();
        int loginOption;
        String optionOption;


        System.out.println("Привет! Вы ходите зарегистрироваться или Вы уже наш пользователь?");
        System.out.println("1. Войти в личный кабинет");
        System.out.println("2. Хочу зарегистрироваться");
        Scanner loginValue = new Scanner(System.in);
        loginOption = loginValue.nextInt();

        switch (loginOption) {
            case (1):
                System.out.println("Введите ранее выданный вам UUID");
                Scanner optionValue = new Scanner(System.in);
                userInfo.setUserUUID(userInfo.userUUID = optionValue.next());



                break;
            case (2):
                UUID uuid = UUID.randomUUID();
                String uuidAsString = uuid.toString();
                userInfo.setUserUUID(uuidAsString);
                mapper.writeNewUser(userInfo.getUserUUID());
                System.out.println("Специально для вас сгенерировали UUID. Запишите его " + userInfo.getUserUUID());


                break;

            default:
                System.out.println("Что-то пошло не так, простите. Попробуйте еще раз.");
                break;

        }
        ;
    }

    ;
}
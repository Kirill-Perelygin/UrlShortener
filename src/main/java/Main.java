import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserInfo userInfo = new UserInfo();
        
        int optionValue;



        System.out.println("Привет! Вы ходите зарегистрироваться или Вы уже наш пользователь?");
        Scanner optionValue = new Scanner(System.in);
        userInfo.userUUID = optionValue.next();



        };
}
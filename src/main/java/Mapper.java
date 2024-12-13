import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Mapper {

    public static void writeNewUser(String string){
        UserInfo userInfo = new UserInfo();
        ObjectMapper userMapper = new ObjectMapper();
        File userUUIDFile = new File("./src/main/java/userUUIDFile.json");
        if (!userUUIDFile.exists()) { // Проверяем существование файла
            boolean isCreated = false; // Создаем файл
            try {
                isCreated = userUUIDFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (isCreated) {
                System.out.println("Файл успешно создан.");
            } else {
                System.out.println("Не удалось создать файл.");
            }
        } else {
            System.out.println("Файл уже существует.");
        }
        try {
            userMapper.writeValueAsString(userInfo.getUserUUID());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ;
    }
    public static void main(String[] args) {
        writeNewUser();
    }

}

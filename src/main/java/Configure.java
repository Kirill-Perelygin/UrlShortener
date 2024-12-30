import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configure {
    private static Properties properties = new Properties();

    public static void loadConfig() throws IOException {
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/database.properties")) {
            properties.load(inputStream);
        }
    }

    private void load(FileInputStream inputStream) {
    }

    public static int getIntValue(String key) {
        String value = properties.getProperty(key);
        return Integer.parseInt(value);
    }

    public static void main(String[] args) {

    }
}

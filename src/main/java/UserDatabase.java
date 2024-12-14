import java.sql.*;

public class UserDatabase {

    public static final String url = "jdbc:mysql://localhost:3306/urlshortenerdb";
    public static final String user = "user";
    public static final String password = "user";

    public static String addUserToTheTable(String uuid) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO userTable (UUID) VALUES (?)");
        pstmt.setString(1, uuid);
        pstmt.executeUpdate();
        connection.close();
        return uuid;
    }

    public static void checkUserExistance(String uuid) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT UUID FROM userTable where UUID = (uuid)"; // замените 'your_table_name' на имя вашей таблицы
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Добро пожаловать!");
    }

    public static void main(String[] args) throws SQLException {
    /*  Connection connection = DriverManager.getConnection(url, user, password);

            // Шаг 3: Выполнить запрос SELECT
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM urlshortener where UUID is not null"; // замените 'your_table_name' на имя вашей таблицы
            ResultSet resultSet = statement.executeQuery(query);

            // Шаг 4: Обработать результат
            while (resultSet.next()) {
                int id = resultSet.getInt("id");   // замените 'id' на название вашего столбца
                String name = resultSet.getString("UUID"); // замените 'name' на название вашего столбца

                System.out.printf("ID: %d, Name: %s\n", id, name);
            }
            */

            // Шаг 5: Закрыть ресурсы

    }
}


import java.sql.*;

public class UserDatabase {

    public static final String url = "jdbc:mysql://localhost:3306/urlshortenerdb";
    public static final String user = "user";
    public static final String password = "user";

    public static void addUserToTheTable(String uuid) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO userTable (UUID) VALUES (?)");
        pstmt.setString(1, uuid);
        pstmt.executeUpdate();
        connection.close();
    }

    /* public static void checkUserExistance(String uuid) throws SQLException {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "SELECT UUID FROM userTable WHERE EXISTS (SELECT UUID FROM userTable WHERE UUID = '" + uuid + "')"; // замените 'your_table_name' на имя вашей таблицы
            ResultSet resultSet = statement.executeQuery(query);
    }
    */

    public static boolean checkUserExistance(String uuid) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT UUID FROM userTable WHERE UUID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, uuid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String foundUuid = rs.getString("UUID");
                System.out.println("Найден UUID: " + foundUuid);
                return true;
            } else {
                System.out.println("Такого пользователя не знаем, простите. Попробуйте зарегистрироваться");
                return false;
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
        return true;
    }


    /* public static void addingUserInfoToTheTable (String uuid, String longUrl, String shortUrl, int counter) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO userTable (UUID, LONGURL, SHORTULR, COUNTER) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, uuid);
        pstmt.setString(2, longUrl);
        pstmt.setString(3, shortUrl);
        pstmt.setString(4, String.valueOf(counter));
        pstmt.executeUpdate();
        connection.close();
    }
*/

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


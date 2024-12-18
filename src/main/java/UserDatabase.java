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
            } else {
                return false;
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static void addingUserInfoToTheTable (String uuid, String longUrl, String shortUrl, int counter) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO userTable (UUID, LONGURL, SHORTURL, COUNTER) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, uuid);
        pstmt.setString(2, longUrl);
        pstmt.setString(3, shortUrl);
        pstmt.setString(4, String.valueOf(counter));
        pstmt.executeUpdate();
        connection.close();
    }

    public static void selectAllUserUrls(String userUUID) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement("SELECT id, SHORTURL FROM userTable WHERE UUID = ? and SHORTURL is not null");
            pstmt.setString(1, userUUID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String foundUuid = rs.getString("id");
                String shortUrl = rs.getString("SHORTURL");
                System.out.println("Id ссылки - " + foundUuid + " а ссылка - " + shortUrl);
        }
    }

    public static void deletSelectedUrl(String userUUID, String shortUrl) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM userTable WHERE UUID = ? AND SHORTURL = ?");
        pstmt.setString(1, userUUID);
        pstmt.setString(2, shortUrl);
        pstmt.executeUpdate();
        System.out.println("Выбранная ссылка удалена");
        connection.close();
    }

    public static String getLongUrl(String shortUrl) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("SELECT LONGURL FROM userTable WHERE SHORTURL = ?");
        pstmt.setString(1, shortUrl);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            String longUrl = rs.getString("LONGURL");
            System.out.println(longUrl + " это длинная ссылка");
        }
        return shortUrl;
    }

    public static void counterPlus(String shortUrl) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("UPDATE userTable SET COUNTER = COUNTER + 1 WHERE SHORTURL = ?");
        pstmt.setString(1, shortUrl);
        pstmt.executeUpdate();
    }

    public static void deleteMaxCounterRows() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM userTable WHERE COUNTER >= 5");
        pstmt.executeUpdate();
        connection.close();
    }

   // public static String extractShortUrl(String shortUrl, int counter){

   // }

    // TODO создать метод, который берет ShortUrl + изменяет каунтер


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


import java.sql.*;
import java.util.Properties;

public class UserDatabase {

    public static final String url = "jdbc:mysql://localhost:3306/urlshortenerdb";
    public static final String user = "user";
    public static final String password = "user";

    public static void databaseAndTableCreation() throws ClassNotFoundException, SQLException {
        try {
            // Загрузка драйвера MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Подключение к серверу без указания конкретной базы данных
            Connection connection = DriverManager.getConnection(url, user, password);

            // Создание новой базы данных
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS urlshortenerdb;";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("База данных успешно создана.");

            // Переключение контекста на созданную базу данных
            connection.setCatalog("urlshortenerdb");

            // Создание таблицы в новой базе данных
            String createTableQuery = "CREATE TABLE IF NOT EXISTS userTable (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "UUID VARCHAR(255)," +
                    "LONGURL VARCHAR(255)," +
                    "SHORTURL VARCHAR(255)," +
                    "COUNTER INT," +
                    "CREATIONDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    "DAYSTOEXPIRE INT," +
                    ");";
            statement.executeUpdate(createTableQuery);
            System.out.println("Таблица успешно создана.");

            // Закрытие соединения
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер MySQL не найден.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Ошибка при создании базы данных или таблицы.");
            e.printStackTrace();
        }
    }


    public static void addUserToTheTable(String uuid) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO userTable (UUID) VALUES (?)");
        pstmt.setString(1, uuid);
        pstmt.executeUpdate();
        PreparedStatement pstmt2 = connection.prepareStatement("UPDATE userTable SET CREATIONDATE = NULL WHERE UUID = ?");
        pstmt2.setString(1, uuid);
        pstmt2.executeUpdate();
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


    public static void addingUserInfoToTheTable (String uuid, String longUrl, String shortUrl, int counter, int daysValue) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO userTable (UUID, LONGURL, SHORTURL, COUNTER, DAYSTOEXPIRE) VALUES (?, ?, ?, ?, ?)");
        pstmt.setString(1, uuid);
        pstmt.setString(2, longUrl);
        pstmt.setString(3, shortUrl);
        pstmt.setString(4, String.valueOf(counter));
        pstmt.setString(5, String.valueOf(daysValue));
        pstmt.executeUpdate();
        connection.close();
    }

    public static void selectAllUserUrls(String userUUID) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement("SELECT id, SHORTURL FROM userTable WHERE UUID = ? and SHORTURL is not null");
            pstmt.setString(1, userUUID);

            ResultSet rs = pstmt.executeQuery();
            System.out.println("Доступные вам короткие ссылки:");
        int i = 1;
        while (rs.next()) {
                String  shortUrl = rs.getString("SHORTURL");
                    System.out.println(i + "." + "  " + shortUrl);
                    i++;
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

    public static String getLongUrl(String userUUID, String shortUrl) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String longUrl1 = null;
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT LONGURL FROM userTable WHERE SHORTURL = ? AND UUID = ?")) {
            pstmt.setString(1, shortUrl);
            pstmt.setString(2, userUUID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String longUrl = rs.getString("LONGURL");
                longUrl1 = longUrl;
            }
        }
        catch (SQLException | RuntimeException e) {
        }
        return longUrl1;
    }

    /* public static void counterMinus(String shortUrl) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("UPDATE userTable SET COUNTER = COUNTER - 1 WHERE SHORTURL = ?");
        pstmt.setString(1, shortUrl);
        pstmt.executeUpdate();
    } */

    public static int getConter(String shortUrl) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE userTable SET COUNTER = COUNTER - 1 WHERE SHORTURL = ?");
        pstmt.setString(1, shortUrl);
        pstmt.executeUpdate();
        PreparedStatement pstmt2 = connection.prepareStatement("SELECT COUNTER FROM userTable WHERE SHORTURL = ?");
        pstmt2.setString(1, shortUrl);

        ResultSet rs = pstmt2.executeQuery();
        int counterVal = 0;
        while (rs.next()) {
            int counterValuer = rs.getInt("COUNTER");
            System.out.println("Количество переходов равняется " + counterValuer);
            counterVal = counterValuer;
            if (counterVal == 0) {
                deleteMaxCounterRows();
            }
        };
        return counterVal;
    }

    public static boolean deleteBasedOnTimestamp(String shortUrl) throws SQLException {
        Boolean isTrue = true;
        if (isTrue) {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement pstm1 = connection.prepareStatement("SELECT DAYSTOEXPIRE FROM userTable WHERE SHORTURL = ?");
            pstm1.setString(1, shortUrl);
            ResultSet rs = pstm1.executeQuery();
            while (rs.next()) {
                int number = rs.getInt("DAYSTOEXPIRE");
                PreparedStatement pstmt = connection.prepareStatement("DELETE FROM userTable WHERE CREATIONDATE < NOW() - INTERVAL ? DAY");
                pstmt.setString(1, String.valueOf(number));
                pstmt.executeUpdate();
            }
            return isTrue = true;
        }
        else return isTrue = false;
    }

    public static void deleteMaxCounterRows() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM userTable WHERE COUNTER = 0");
        pstmt.executeUpdate();
        connection.close();
    }

    // TODO подумать о логике того, что если сгенерированная строка уже есть есть в таблице, то сгенерировать её снова -> вероятно через IF - ELSE или через WHILE с boolean
    // TODO значением

   // public static String extractShortUrl(String shortUrl, int counter){

   // }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            databaseAndTableCreation();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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


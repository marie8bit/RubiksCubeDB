/**
 * Created by Marie on 11/14/2016.
 */
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class CubeDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cube";
    static final String USER = "Marie";
    static final String PASSWORD = "tryapassphrase";
    public static void main(String[] args)throws Exception {
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        statement.execute("Create table if not EXISTS records (holder varchar(150), record float)");
        String prepStatInsert = "insert into records values (?,?)";
        PreparedStatement psInsert = connection.prepareStatement((prepStatInsert));
        psInsert.setString(1, "Cubestormer II, robot");
        psInsert.setDouble(2, 5.270);
        psInsert.executeUpdate();
        psInsert.setString(1, "Fakhri Raihaan, with his feet");
        psInsert.setDouble(2, 27.93);
        psInsert.executeUpdate();
        psInsert.setString(1, "Ruxin Liu, 3 years old");
        psInsert.setDouble(2, 99.33);
        psInsert.executeUpdate();
        psInsert.setString(1, "Mats Valk,human record holder");
        psInsert.setDouble(2, 6.27);
        psInsert.executeUpdate();
        String again = "y";
        printAllRows(connection);

        System.out.println("Would you like to add new record?");
        again = scanner.nextLine();

        System.out.println( "Emter the record holder");
        String updholder = scanner.nextLine();
        psInsert.setString(1,updholder);

        try {
            double updrecord = Double.parseDouble(scanner.nextLine());
            psInsert.setDouble(2,updrecord);
        }
        catch (InputMismatchException ime){
            System.out.println("Please enter a numeric value");
        }
        catch (NumberFormatException nfe){
            System.out.println("Please enter a numeric value");
        }
        psInsert.executeUpdate();

        connection.close();
        psInsert.close();
    }

    private static void printAllRows(Connection connection) {
        String getResultsQuery = "Select * from records";
        try {
        PreparedStatement getResultsStatement = connection.prepareStatement(getResultsQuery);
            ResultSet rs = getResultsStatement.executeQuery();
            System.out.println("Rubik's Cude solution records");
            int numberOfResults = 0;
            while (rs.next()) {
                numberOfResults++;
                String holder = rs.getString("holder");
                double record = rs.getDouble("record");
                System.out.println("Record holder: " + holder + " Time: " + record);
            }
            if (numberOfResults == 0) {
                System.out.println("No records found in database");
            }

        }
        catch (SQLException se){
            System.out.println("Connection Error");
        }
    }
}

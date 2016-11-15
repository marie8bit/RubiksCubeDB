/**
 * Created by Marie on 11/14/2016.
 */
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class CubeDB {
    //identify driver
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //set up connection url
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cube";
    //identify user information for DB connection
    static final String USER = "Marie";
    static final String PASSWORD = "tryapassphrase";
    public static void main(String[] args)throws Exception {
        Class.forName(JDBC_DRIVER);
        //generate connection to DB
        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        //create table if it doesn't already exist
        statement.execute("Create table if not EXISTS records (holder varchar(150), record float)");
        //generate prepared statement for filling in DB
        String getResultsQuery = "Select * from records";
        PreparedStatement getResultsStatement = connection.prepareStatement(getResultsQuery);
        //get result set from table
        ResultSet rs = getResultsStatement.executeQuery();
        String prepStatInsert = "insert into records values (?,?)";
        PreparedStatement psInsert = connection.prepareStatement((prepStatInsert));
        //fill in table if table is empty by reusing prepared statements
        if (!rs.isBeforeFirst()) {
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
        }
        //method to print table data
        printAllRows(connection);

        System.out.println("Would you like to add new record? Y for yes");
        String again = scanner.nextLine();
        //allows user to add multiple entries
        while(again.equalsIgnoreCase("y")){
            System.out.println("Emter the record holder");
            String updholder = scanner.nextLine();
            psInsert.setString(1, updholder);
            //validate user input
            while (true) {
                try {
                    System.out.println("Enter the record time as (minutes).(seconds)");
                    double updrecord = Double.parseDouble(scanner.nextLine());
                    psInsert.setDouble(2, updrecord);
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a numeric value");
                }
            }
            //executes prepared statement, setting data in table
            psInsert.executeUpdate();
            //prints whole table (not a good idea for large tables
            printAllRows(connection);
            //add multiple entries
            System.out.println("Would you like to add another new record? Y for yes");
            again = scanner.nextLine();
        }
        connection.close();
        psInsert.close();
    }

    private static void printAllRows(Connection connection) {
        //get rows from db table
        String getResultsQuery = "Select * from records";
        try {
            //create db connection
            PreparedStatement getResultsStatement = connection.prepareStatement(getResultsQuery);
            //get result set from db
            ResultSet rs = getResultsStatement.executeQuery();
            System.out.println("Rubik's Cude solution records");
            int numberOfResults = 0;
            //get row data variables and display information to the user
            while (rs.next()) {
                numberOfResults++;
                String holder = rs.getString("holder");
                double record = rs.getDouble("record");
                System.out.println("Record holder: " + holder + " Time: " + record);
            }
            //provideuser friendly experience
            if (numberOfResults == 0) {
                System.out.println("No records found in database");
            }

        }
        //handle db exceptions
        catch (SQLException se){
            System.out.println("Database Error");
        }
    }
}

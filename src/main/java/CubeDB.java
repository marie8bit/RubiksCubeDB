/**
 * Created by Marie on 11/14/2016.
 * Write an application that creates a table called “cubes”.
 You MUST start from scratch, not copy and paste from the examples!
 The cubes table should have two columns, one for the name of a thing that can solve
 Rubik’s cubes, and the other for the best time taken to solve the Rubik’s cube. Here’s some data:
 Thing that can solve a rubik’s cube 	Time taken, in seconds
 Cubestormer II robot	5.270
 Fakhri Raihaan (using his feet)	27.93
 Ruxin Liu (age 3)	99.33
 Mats Valk (human record holder)	6.27
 Sources: http://www.recordholders.org/en/list/rubik.html
 http://en.wikipedia.org/wiki/CubeStormer_II
 Your program should create this table, and add the data in the table above.
 Your column names should be all one word (such as cube_solver) and avoid SQL keywords
 in column names (so  avoid names like time)
 But best times can be improved, and new records might be added.
 Your program should be able to take input from the user when a new time should be recorded.
 For example, your program should be able to ask if you want to add a new solver and time, for example,
 Cubestormer III robot	3.253
 You can write this all in one class if you like.
 * You’ve just heard that your table is out of date. Mats Valk has broken his previous record
 * and set a new time of 5.55 seconds.
 Please modify your previous program so you can search for an entry in the table, and update the time.

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

    CubeDB()throws Exception {
        Class.forName(JDBC_DRIVER);
        //generate connection to DB
        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
//        printAllRows(connection);
//        int caa = chooseAnAction();



        //allows user to add multiple entries
//        while(caa!=3){
//            switch (caa) {
//                case 1: {
//
//                    System.out.println("Emter the record holder");
//                    String updholder = scanner.nextLine();
//                    psInsert.setString(1, updholder);
//                    //validate user input
//                    while (true) {
//                        try {
//                            System.out.println("Enter the record time as (minutes).(seconds)");
//                            double updrecord = Double.parseDouble(scanner.nextLine());
//                            psInsert.setDouble(2, updrecord);
//                            break;
//                        } catch (NumberFormatException nfe) {
//                            System.out.println("Please enter a numeric value");
//                        }
//                    }
//                    //executes prepared statement, setting data in table
//                    psInsert.executeUpdate();
//                    //prints whole table (not a good idea for large tables
//                    printAllRows(connection);
//                    //add multiple entries
//                    caa = chooseAnAction();
//                }
//                case 2:{
//                    //use prepared statement to udpate data in the db
//                    String prepStatUpdate = "update records set record  = ? where holder like ?";
//                    PreparedStatement psUpdate = connection.prepareStatement((prepStatUpdate));
//                    System.out.println("Enter the record holders last name");
//                    //add % sign to allow for partial holder entries
//                    String name = "%"+scanner.nextLine()+"%";
//                    psUpdate.setString(2,name);
//                    while (true) {
//                        try {
//                            System.out.println("Enter the record time as (minutes).(seconds)");
//                            double updrecord = Double.parseDouble(scanner.nextLine());
//                            psUpdate.setDouble(1, updrecord);
//                            break;
//                        } catch (NumberFormatException nfe) {
//                            System.out.println("Please enter a numeric value");
//                        }
//                    }
//
//                    psUpdate.executeUpdate();
//                    //prints whole table (not a good idea for large tables
//                    printAllRows(connection);
//                    //add multiple entries
//                    caa = chooseAnAction();
//
//                }
//            }
//        }
//        System.out.println("Goodbye!");
        //connection.close();
        //psInsert.close();
    }
    //provide user with options for interacting with the database
//    private static int chooseAnAction() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Choose an action");
//        System.out.println("1. Add an entry");
//        System.out.println("2. Update a record");
//        System.out.println("3. Quit");
//        String s = scanner.nextLine();
//        int i=0;
//        while (true) {
//            try {
//                i = Integer.parseInt(s);
//                break;
//            } catch (NumberFormatException nfe) {
//                System.out.println("Please enter a numeric value");
//            }
//        }
//
//        return i;
//    }

//    private static void printAllRows() throws Exception{
//        //get rows from db table
//        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
//        String getResultsQuery = "Select * from records";
//        try {
//            //create db connection
//            PreparedStatement getResultsStatement = connection.prepareStatement(getResultsQuery);
//            //get result set from db
//            ResultSet rs = getResultsStatement.executeQuery();
//            //System.out.println("Rubik's Cude solution records");
//            int numberOfResults = 0;
//            //get row data variables and display information to the user
//            while (rs.next()) {
//                numberOfResults++;
//                String holder = rs.getString("holder");
//                double record = rs.getDouble("record");
//                System.out.println("Record holder: " + holder + " Time: " + record);
//            }
//            //provideuser friendly experience
//            if (numberOfResults == 0) {
//                System.out.println("No records found in database");
//            }
//            rs.close();
//
//        }
//        //handle db exceptions
//        catch (SQLException se){
//            System.out.println("Database Error");
//        }
//        connection.close();
//    }
    public static ResultSet updateResultSet( int column, String newNalue, String oldValue)throws Exception{
        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
        switch (column) {
            case 1: {
                String prepStatUpdate = "update records set holder = ? where holder = ?";
                PreparedStatement psUpdate = connection.prepareStatement(prepStatUpdate);
                psUpdate.setString(1,newNalue);
                psUpdate.setString(2,oldValue);
                psUpdate.executeUpdate();

            }
            case 2:{
                String prepStatUpdate = "update records set record = ? where record = ?";
                PreparedStatement psUpdate = connection.prepareStatement(prepStatUpdate);
                Double dNew = Double.parseDouble(newNalue);
                Double dOld = Double.parseDouble(oldValue);
                psUpdate.setDouble(1,dNew);
                psUpdate.setDouble(2,dOld);
                psUpdate.executeUpdate();

            }

        }
        ResultSet rs = getMyResultSet();
        return rs;

    }
    public static ResultSet getMyResultSet() throws Exception{
//        try {
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
            String getResultsQuery = "Select * from records";

            //create db connection
            PreparedStatement getResultsStatement = connection.prepareStatement(getResultsQuery);
            //get result set from db
            ResultSet rs = getResultsStatement.executeQuery();
            //System.out.println("Rubik's Cude solution records");
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

            //rs.close();
            //connection.close();
            return rs;
//        }
//        //handle db exceptions
//        catch (SQLException se) {
//            System.out.println("Database Error");
//        }
//        return rs;
    }
}

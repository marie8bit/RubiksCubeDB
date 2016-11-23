import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Marie on 11/20/2016.
 * controller class to intatiate db class and gui form
 */
public class Controller {
    //creates class constants
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet rs = null;
    public static void main(String[] args) {
        //instaniate dn
        try {
            CubeDB db;
            db = new CubeDB();
            //initialize result set for jtable
            rs = db.getMyResultSet();
            TableModel recordsModel = new TableModel(rs);
            //Create and show the GUI
            RubiksCubeForm tableGUI = new RubiksCubeForm(recordsModel);
        //catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
            //closes the program
            System.exit(-1);
        }
    }
    public static void shutdown(){

        //Close resources - ResultSet, statement, connection - and tidy up whether this code worked or not.

        //Close ResultSet...
        try {
            if (rs != null) {
                rs.close();
                System.out.println("Result set closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        //And then the statement....
        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();

        }

        //And then the connection
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }

        //And quit the program
        System.exit(0);
    }

}

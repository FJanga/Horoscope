import com.Utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try{
            /*Creating an instance of the built-in connection class where we call...
             * ...our 'connect()' method from the ConnectionManager class.*/
            Connection connection = ConnectionManager.connect();
            /*Print statement to test our connection*/
            System.out.println(connection.getSchema());
        }/*try block ending*/

        catch(SQLException e){
            System.out.println(e.getMessage());
        }/*catch block ending*/

    }
}

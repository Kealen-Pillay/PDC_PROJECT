
package softwareconstruction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kealenpillay
 */
public class DBManager
{
    //----------------------------------------------------- Instance Variables ---------------------------------------------------------
    private static final String USERNAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:VirtualPetGameDB; create=true";
    private Connection conn;
   
    //----------------------------------------------------- Constructor ---------------------------------------------------------
    /**
     * Zero parameter constructor.
     */
    public DBManager()
    {
        this.establishConnection();
    }
    
    //----------------------------------------------------- Methods ---------------------------------------------------------
    
    /**
     * @return the conn.
     */
    public Connection getConn()
    {
        return conn;
    }
    
    /**
     * Establish a connection to the database.
     */
    public void establishConnection()
    {
        if(this.conn == null)
        {
            try
            {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println(URL + " Connected Successfully ....");
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    /**
     * Closes the connection to the database.
     */
    public void closeConnections()
    {
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}

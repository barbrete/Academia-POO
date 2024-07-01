
package mvcAcademia.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 *
 * @author Rogério
 */
public class ConexaoAcademia {
      
    public Connection getConnection(){
        
        try{
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "admwindows");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "America/Sao_Paulo");
            properties.setProperty("allowPublicKeyRetrieval", "true");
            
            String url = "jdbc:mysql://localhost/projetopoofinal";
            return DriverManager.getConnection(url, properties);
              
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
 
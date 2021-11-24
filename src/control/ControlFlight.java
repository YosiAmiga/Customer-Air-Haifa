package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.Consts;

public class ControlFlight {
	
	public boolean createNewPlane(String planeNumber, int planeSize) {
		
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt2 = 
                            conn.prepareStatement(Consts.SQL_INS_AIRPLANE);
                    
                    int i=1;
            
                    stmt2.setString(i++, planeNumber);
                    stmt2.setInt(i++, planeSize);
           
                    stmt2.executeUpdate();
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		return true;
	}

}

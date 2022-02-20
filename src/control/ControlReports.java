package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import entity.Airplane;
import entity.Consts;
import entity.Customer;
import entity.Flight;
import entity.ReportTest;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class ControlReports {
	//the customer currently logged in
	public static Customer customer;
	
	 private static ControlReports _instance;
	    public static ControlReports getInstance() {
	        if (_instance == null)
	            _instance = new ControlReports();
	        return _instance;
	    }
	    
		/******GET REPROT******/

		/*********reports on updated flights, each customer effected and it's orders by times**********/
		public ArrayList<ReportTest> ordersReport() {
			ArrayList<ReportTest> report= new ArrayList<>();

			try {
	            Class.forName(Consts.JDBC_STR);
	            
	            try {
	            	Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	            		//plane SQL
	                    PreparedStatement stmt = conn.prepareStatement(Consts.ORDERS_REPORT2);

	                    try(ResultSet rs = stmt.executeQuery())
	                    {
	    	            	while (rs.next()) {
	    	            		int j = 1;
	    	            		//show the customer only HIS orders by times
	    	            		if(rs.getInt(1) == customer.getPassportNumber()) {
	    	            			report.add(
	    	            					new ReportTest(
	    	            							rs.getInt(j++),
	    	            							rs.getString(j++),
	    	            							rs.getString(j++),
	    	            							rs.getInt(j++),
	    	            							rs.getInt(j++),
	    	            							rs.getInt(j++),
	    	            							rs.getInt(j++))
	    	            					);    	            		
	    	            		}    	            			
	    	            		}
	                    	
	                    }
	                    return report;
	                   
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();

	        }
	        return report;
		}
	    
	    
	    public JFrame getBigFlightsReport(Date departure, Date arrival, int seats) {
			try {
	            Class.forName(Consts.JDBC_STR);
	            
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){

						try {
							System.out.println(departure+" "+arrival+" "+seats);
							HashMap<String, Object> params = new HashMap<>();
							Date a = new java.sql.Date(departure.getTime());
							Date b = new java.sql.Date(arrival.getTime());
							System.out.println(a+" "+b+" "+seats);
							params.put("FlightDeparture", a);
							params.put("FlightArrival",b);
							params.put("Quantity", (Integer) seats);

							JasperPrint print = JasperFillManager.fillReport(
									getClass().getResourceAsStream("/boundry/RptFlightInRange.jasper"),
									params, conn);

							JFrame frame = new JFrame("Show Report");
							frame.getContentPane().add(new JRViewer(print));
							frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
							frame.pack();
							return frame;
							
						} 
						catch (JRException e) {
							// TODO Auto-generated catch block

							e.printStackTrace();
						}
	                    
                
	            } catch (SQLException e) {

	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();

	        }
			return null;

	    }
	    

	    
	    
	   
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	    
	    

}

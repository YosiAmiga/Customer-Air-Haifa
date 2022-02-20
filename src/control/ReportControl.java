package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import entity.Consts;

public class ReportControl {

	public static ReportControl instance;
	
	public static ReportControl getInstance() {
		if (instance == null)
			instance = new ReportControl();
		return instance;
	}
	
	public JFrame produceReport() 
	{
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR))
			{
				HashMap<String, Object> params = new HashMap<>();

				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/report.jasper"),
						params, conn);
				JFrame frame = new JFrame("Orders Report for " + LocalDate.now());
				frame.getContentPane().add(new JRViewer(print));
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.pack();
				return frame;
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
}

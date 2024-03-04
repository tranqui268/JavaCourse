import GUI.MainJFrame;
import java.sql.SQLException;


public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainJFrame main = new MainJFrame();
//					main.lauch();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		new MainJFrame();
	}
}

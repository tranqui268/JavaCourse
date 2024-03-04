package GUI.view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.ColorTheme;

public class CourseManager extends JPanel{

	private static final long serialVersionUID = 1L;
        public static CourseOnlineJPanel COnl = new CourseOnlineJPanel();
        public static CourseOnsiteJPanel COns = new CourseOnsiteJPanel();
	/**
	 * Create the panel.
	 */
	public CourseManager() {
		setLayout(new CardLayout(0, 0));
		/*JLabel lblThisIsHome = new JLabel("This is course manager");
		lblThisIsHome.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblThisIsHome, "name_140143002992856");*/
                add(COnl, "Course-Online");
                COnl.setVisible(true);
                add(COns, "Course-Onsite");
                COns.setVisible(false);
                
		setBackground(Color.decode(ColorTheme.neutral));
	}

}

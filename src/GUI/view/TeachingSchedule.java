package GUI.view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.ColorTheme;
import javax.swing.JFrame;

public class TeachingSchedule extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TeachingSchedule() {
		setLayout(new CardLayout(0, 0));
                
                JPanel TeachingSchedulePanel = new TeachingSchedulePanel();
                this.add(TeachingSchedulePanel);
	}

}

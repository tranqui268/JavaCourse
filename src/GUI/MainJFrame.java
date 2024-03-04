package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.net.URL;

import GUI.ColorTheme;
import GUI.view.CourseManager;
import GUI.view.PersonManager;
import GUI.view.StudentGrade;
import GUI.view.TeachingSchedule;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;

public class MainJFrame {

	private JFrame frame;
	private JPanel navigateView, mainView;
	private JLabel avatar;
	private JButton courseManageBtn, personManageBtn, teachingScheduleBtn, studentGradeBtn, logOutBtn;
	private JPanel panel;
	private static String resourcePath = "/GUI/res/";

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		
//	}
	public void lauch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame window = new MainJFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainJFrame() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 600);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Course manager app");
		frame.setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		// set layout with 2 column, first has min 350, second has min 100
		gridBagLayout.columnWidths = new int[] { 250, 100 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		this.initNavigateView();
		this.initMainView();

		frame.setVisible(true);
	}

	private void initNavigateView() {
		// add JPanel to navigate
		this.navigateView = new JPanel();
		navigateView.setBackground(Color.decode(ColorTheme.base));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(this.navigateView, gbc_panel);
		// create Circle Image
		try {
			URL imageSource = new URL("https://i.pravatar.cc/100");
			BufferedImage image = ImageIO.read(imageSource);
			BufferedImage circularImage = new BufferedImage(image.getWidth(), image.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = circularImage.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Color.WHITE);
			g2.fillOval(0, 0, circularImage.getWidth(), circularImage.getHeight());
			g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, circularImage.getWidth(), circularImage.getHeight()));
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
			GridBagLayout gbl_navigateView = new GridBagLayout();
			gbl_navigateView.columnWidths = new int[] { 245, 0 };
			gbl_navigateView.rowHeights = new int[] { 200, 100, 0 };
			gbl_navigateView.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gbl_navigateView.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			navigateView.setLayout(gbl_navigateView);
			this.avatar = new JLabel(new ImageIcon(circularImage));
			this.avatar.setText("Admin");
			this.avatar.setIconTextGap(20);
			this.avatar.setForeground(Color.white);
			avatar.setFont(new Font("Dialog", Font.BOLD, 24));
			GridBagConstraints gbc_avatar = new GridBagConstraints();
			gbc_avatar.fill = GridBagConstraints.BOTH;
			gbc_avatar.insets = new Insets(0, 0, 5, 0);
			gbc_avatar.gridx = 0;
			gbc_avatar.gridy = 0;
			this.navigateView.add(this.avatar, gbc_avatar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		panel = new JPanel();
		panel.setBackground(Color.decode(ColorTheme.base));
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 1;
		navigateView.add(panel, gbc_panel1);
		panel.setLayout(new GridLayout(5, 1, 0, 0));
		this.courseManageBtn = new JButton("Course Manager");
		courseManageBtn.setHorizontalAlignment(SwingConstants.LEADING);
		courseManageBtn.setIcon(new ImageIcon(getClass().getResource(resourcePath + "icons8-classroom-48.png")));
		addStyleBtn(courseManageBtn);
		courseManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchView("course-manager", courseManageBtn);
			}
		});
		panel.add(courseManageBtn);
		this.personManageBtn = new JButton("Person Manager");
		personManageBtn.setHorizontalAlignment(SwingConstants.LEADING);
		personManageBtn.setIcon(new ImageIcon(getClass().getResource(resourcePath + "icons8-user-48.png")));
		addStyleBtn(personManageBtn);
		personManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchView("person-manager", personManageBtn);
			}
		});
		panel.add(personManageBtn);
		this.teachingScheduleBtn = new JButton("Teaching Schedule");
		teachingScheduleBtn.setHorizontalAlignment(SwingConstants.LEADING);
		teachingScheduleBtn.setIcon(new ImageIcon(getClass().getResource(resourcePath + "icons8-calendar-48.png")));
		addStyleBtn(teachingScheduleBtn);
		teachingScheduleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchView("teaching-schedule", teachingScheduleBtn);
			}
		});
		panel.add(teachingScheduleBtn);
		this.studentGradeBtn = new JButton("Student Grade");
		studentGradeBtn.setHorizontalAlignment(SwingConstants.LEADING);
		studentGradeBtn.setIcon(new ImageIcon(getClass().getResource(resourcePath + "icons8-report-card-48.png")));
		addStyleBtn(studentGradeBtn);
		studentGradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchView("student-grade", studentGradeBtn);
			}
		});
		panel.add(studentGradeBtn);
		this.logOutBtn = new JButton("Log out");
		logOutBtn.setHorizontalAlignment(SwingConstants.LEADING);
		logOutBtn.setIcon(new ImageIcon(getClass().getResource(resourcePath + "icons8-import-48.png")));
		addStyleBtn(logOutBtn);
		panel.add(logOutBtn);
	}

	private void initMainView() throws SQLException {
		// add JPanel to switch between multiple JPanel
		this.mainView = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		frame.getContentPane().add(this.mainView, gbc_panel_1);
		// add other panel from View package to main view
		this.mainView.setLayout(new CardLayout());
		this.mainView.add(new CourseManager(), "course-manager");
		this.mainView.add(new PersonManager(), "person-manager");
		this.mainView.add(new TeachingSchedule(), "teaching-schedule");
            try {
                this.mainView.add(new StudentGrade(), "student-grade");
            } catch (SQLException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	private void switchView(String view, JButton activeBtn) {
		JButton[] btnGroup = { this.courseManageBtn, this.personManageBtn, this.teachingScheduleBtn,
				this.studentGradeBtn, this.logOutBtn };
		// reset color of all button
		for (JButton btn : btnGroup) {
			btn.setBackground(Color.decode(ColorTheme.base));
			btn.setForeground(Color.white);
//			btn.setContentAreaFilled(true);
		}
		// assign new color for active btn
		activeBtn.setBackground(Color.decode(ColorTheme.primary));
		activeBtn.setForeground(Color.black);
		CardLayout layout = (CardLayout) mainView.getLayout();
		layout.show(mainView, view);
	}

	// style for btn here
	private void addStyleBtn(JButton btn) {
		String bg = ColorTheme.base;
		btn.setFont(new Font("Dialog", Font.BOLD, 16));
		btn.setBorder(new EmptyBorder(10, 35, 10, 20));
		btn.setBackground(Color.decode(bg));
		btn.setForeground(Color.white);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}

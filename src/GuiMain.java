import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.ResultSetMetaData;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;

public class GuiMain {

	private JFrame frmPerfectGraniteSolutions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMain window = new GuiMain();
					window.frmPerfectGraniteSolutions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPerfectGraniteSolutions = new JFrame();
		frmPerfectGraniteSolutions.setTitle("Perfect Granite Solutions e-mail helper");
		frmPerfectGraniteSolutions.setBounds(100, 100, 409, 731);
		frmPerfectGraniteSolutions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPerfectGraniteSolutions.getContentPane().setLayout(null);
		
		JButton btnNewLead = new JButton("New Lead");
		btnNewLead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addLead addLeadWindow = new addLead();
				addLeadWindow.setVisible(true);
			}
		});
		btnNewLead.setBounds(10, 403, 258, 94);
		frmPerfectGraniteSolutions.getContentPane().add(btnNewLead);
		
		JButton btnRemindLeads = new JButton("Remind Leads");
		btnRemindLeads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remindLead remindLeadWindow = new remindLead();
				remindLeadWindow.setVisible(true);
			}
		});
		btnRemindLeads.setBounds(10, 11, 258, 94);
		frmPerfectGraniteSolutions.getContentPane().add(btnRemindLeads);
		
		JTextPane txtpnClickHereTo = new JTextPane();
		txtpnClickHereTo.setBackground(UIManager.getColor("Panel.background"));
		txtpnClickHereTo.setEditable(false);
		txtpnClickHereTo.setText("Click here to send follow-up emails to leads who have not responded.");
		txtpnClickHereTo.setBounds(278, 11, 105, 94);
		frmPerfectGraniteSolutions.getContentPane().add(txtpnClickHereTo);
		
		JButton btnNewEmail = new JButton("New email");
		btnNewEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEmail newEmailWindow = new newEmail();
				newEmailWindow.setVisible(true);
			}
		});
		btnNewEmail.setBounds(10, 145, 258, 94);
		frmPerfectGraniteSolutions.getContentPane().add(btnNewEmail);
		
		JTextPane txtpnClickHereTo_1 = new JTextPane();
		txtpnClickHereTo_1.setBackground(UIManager.getColor("Panel.background"));
		txtpnClickHereTo_1.setEditable(false);
		txtpnClickHereTo_1.setText("Click here to send emails to leads who have not yet been contacted.");
		txtpnClickHereTo_1.setBounds(278, 145, 105, 94);
		frmPerfectGraniteSolutions.getContentPane().add(txtpnClickHereTo_1);
		
		JButton btnUpdateLead = new JButton("Update Lead");
		btnUpdateLead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLead updateLeadWindow = new updateLead();
				updateLeadWindow.setVisible(true);
			}
		});
		btnUpdateLead.setBounds(10, 277, 258, 94);
		frmPerfectGraniteSolutions.getContentPane().add(btnUpdateLead);
		
		JTextPane txtpnClickHereTo_2 = new JTextPane();
		txtpnClickHereTo_2.setBackground(UIManager.getColor("Panel.background"));
		txtpnClickHereTo_2.setEditable(false);
		txtpnClickHereTo_2.setText("Click here to change details for a lead, or mark if they have responded to an email.");
		txtpnClickHereTo_2.setBounds(278, 277, 105, 101);
		frmPerfectGraniteSolutions.getContentPane().add(txtpnClickHereTo_2);
		
		JTextPane txtpnClickHereTo_3 = new JTextPane();
		txtpnClickHereTo_3.setBackground(UIManager.getColor("Panel.background"));
		txtpnClickHereTo_3.setEditable(false);
		txtpnClickHereTo_3.setText("Click here to add a new lead to the database.");
		txtpnClickHereTo_3.setBounds(278, 403, 105, 94);
		frmPerfectGraniteSolutions.getContentPane().add(txtpnClickHereTo_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(377, 431, -318, 101);
		frmPerfectGraniteSolutions.getContentPane().add(textPane);
		
		JTextPane txtpnNeedHelpUsing = new JTextPane();
		txtpnNeedHelpUsing.setBackground(UIManager.getColor("Panel.background"));
		txtpnNeedHelpUsing.setEditable(false);
		txtpnNeedHelpUsing.setText("Need help using the program or modifying the database? Contact Chase Goehring - 1(714) 580-8672, by text preferrably.");
		txtpnNeedHelpUsing.setBounds(10, 637, 373, 44);
		frmPerfectGraniteSolutions.getContentPane().add(txtpnNeedHelpUsing);
		
		JButton btnViewLeadInfo = new JButton("View Lead Info");
		btnViewLeadInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewLead viewLeadWindow = new viewLead();
				viewLeadWindow.setVisible(true);
			}
		});
		btnViewLeadInfo.setBounds(10, 523, 258, 94);
		frmPerfectGraniteSolutions.getContentPane().add(btnViewLeadInfo);
		
		JTextPane txtpnClickHereTo_4 = new JTextPane();
		txtpnClickHereTo_4.setBackground(UIManager.getColor("Panel.background"));
		txtpnClickHereTo_4.setEditable(false);
		txtpnClickHereTo_4.setText("Click here to see all info stored on a Lead.");
		txtpnClickHereTo_4.setBounds(278, 523, 105, 94);
		frmPerfectGraniteSolutions.getContentPane().add(txtpnClickHereTo_4);
	}
}

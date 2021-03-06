import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class remindLead extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JSpinner spinner;
	JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			remindLead dialog = new remindLead();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public remindLead() {
		setTitle("Remind Leads");
		setBounds(100, 100, 450, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTypeOfLead = new JLabel("Type of lead to remind:");
			lblTypeOfLead.setBounds(10, 11, 112, 14);
			contentPanel.add(lblTypeOfLead);
		}
		{
			spinner = new JSpinner();
			spinner.setModel(new SpinnerListModel(new String[] {"Stone Supplier", "Hotel", "Fabricator", "Existing Client", "Designer"}));
			spinner.setBounds(132, 8, 292, 20);
			contentPanel.add(spinner);
		}
		{
			JButton btnRemind = new JButton("Remind");
			btnRemind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String leadType = spinner.getValue().toString().replaceAll("[\';\"#()]", "");
					String sql = "SELECT email FROM `leads` WHERE type = '" + leadType + 
							"' and noSent != 0 and hasResponded = False and noSent < 10 and DATEDIFF(lastSent, CURDATE()) < -6";
					MysqlConnect mysqlConnect = new MysqlConnect();
					String allEmails = "";
					//
					try {
						
					    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
					    ResultSet resultSet = statement.executeQuery();
					    
					    //resultSet extraction implementation by Zeb on StackOverflow
					    ResultSetMetaData rsmd = resultSet.getMetaData();
					    int columnsNumber = rsmd.getColumnCount();
					    while (resultSet.next())
					    {
					    	for (int i = 1; i <= columnsNumber; i++) {
					    		if (i > 1) System.out.print(", ");
					    		String columnValue = resultSet.getString(i);
					    		allEmails = allEmails + columnValue;
					    	}
					    	if (!resultSet.isLast()) allEmails = allEmails + ", ";
					    }
					    
					    
					} catch (SQLException e1) {
					    e1.printStackTrace();
					    JOptionPane.showMessageDialog(null, "There was a problem connecting to the Database.");
					} finally {
					    mysqlConnect.disconnect();
					    System.out.println("Disconnected from the SQL server");
					}
					textPane.setText(allEmails);
				}
			});
			btnRemind.setBounds(132, 39, 89, 23);
			contentPanel.add(btnRemind);
		}
		{
			JLabel lblEmails = new JLabel("emails:");
			lblEmails.setBounds(10, 85, 46, 14);
			contentPanel.add(lblEmails);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 110, 414, 100);
			contentPanel.add(scrollPane);
			{
				textPane = new JTextPane();
				scrollPane.setViewportView(textPane);
				textPane.setEditable(false);
			}
		}
		{
			JLabel lblContents = new JLabel("contents:");
			lblContents.setBounds(10, 221, 89, 14);
			contentPanel.add(lblContents);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 246, 414, 391);
			contentPanel.add(scrollPane);
			{
				JTextPane bigTextPane = new JTextPane();
				scrollPane.setViewportView(bigTextPane);
				bigTextPane.setText("Right now, I don't have access to the actual copy-paste emails. Please send them to Chase or insert the code to load them yourself.");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblPleaseClickConfirm = new JLabel("Please click confirm after sending emails:");
				buttonPane.add(lblPleaseClickConfirm);
			}
			{
				JButton cancelButton = new JButton("Confirm");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String leadType = spinner.getValue().toString().replaceAll("[\';\"#()]", "");
						MysqlConnect mysqlConnect = new MysqlConnect();
						
						String sql = "UPDATE `leads` SET noSent = noSent + 1, lastSent = CURDATE() WHERE type = '" + leadType + 
								"' and noSent != 0 and hasResponded = False and noSent < 10 and DATEDIFF(lastSent, CURDATE()) < -6";
						
						try {
						    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
						    statement.executeUpdate();						    
						    
						} catch (SQLException e1) {
						    e1.printStackTrace();
						    JOptionPane.showMessageDialog(null, "There was a problem inserting this into the database. Please try again!");
						} finally {
						    mysqlConnect.disconnect();
						    System.out.println("Disconnected from the SQL server");
						}
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton btnCancel = new JButton("Close");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCancel);
			}
		}
	}

}

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addLead extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private JTextField txtPhoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			addLead dialog = new addLead();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public addLead() {
		setTitle("Add Lead...");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtName = new JTextField();
			txtName.setBounds(142, 11, 282, 20);
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			txtAddress = new JTextField();
			txtAddress.setBounds(142, 42, 282, 20);
			contentPanel.add(txtAddress);
			txtAddress.setColumns(10);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setBounds(142, 73, 282, 20);
			contentPanel.add(txtEmail);
			txtEmail.setColumns(10);
		}
		{//This line is likely left over from the old implementation, ignore
			String[] selections = {"Hotel", "Stone Supplier", "Existing Client", "Fabricator", "Designer"};
		}
		{
			txtPhoneNumber = new JTextField();
			txtPhoneNumber.setBounds(142, 135, 282, 20);
			contentPanel.add(txtPhoneNumber);
			txtPhoneNumber.setColumns(10);
		}
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 11, 122, 20);
		contentPanel.add(lblName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 42, 122, 20);
		contentPanel.add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 73, 122, 20);
		contentPanel.add(lblEmail);
		
		JLabel lblTypeOfLead = new JLabel("Type of Lead:");
		lblTypeOfLead.setBounds(10, 104, 122, 20);
		contentPanel.add(lblTypeOfLead);
		
		JLabel lblNewLabel = new JLabel("Phone Number:");
		lblNewLabel.setBounds(10, 135, 122, 20);
		contentPanel.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] {"Stone Supplier", "Hotel", "Fabricator", "Existing Client", "Designer"}));
		spinner.setBounds(142, 104, 282, 20);
		contentPanel.add(spinner);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String leadType = spinner.getValue().toString().replaceAll("[\';\"#()]", "");
						String leadName = txtName.getText().replaceAll("[\';\"#()]", "");
						String leadAddress = txtAddress.getText().replaceAll("[\';\"#()]", "");
						String leadEmail = txtEmail.getText().replaceAll("[\';\"#()]", "");
						String leadPhoneNumber = txtPhoneNumber.getText().replaceAll("[\';\"#()]", "");
						//JOptionPane.showMessageDialog(null, "You Entered: " + leadType + ", " + leadName + ", " + leadAddress + ", " + leadEmail +", " + leadPhoneNumber + ".");
						
						//Add new data to the database
						MysqlConnect mysqlConnect = new MysqlConnect();
						
						String sql = "INSERT INTO `leads` (`name`, `address`, `email`, `phoneNumber`, `type`, `noSent`, `lastSent`, `hasResponded`) VALUES ('" +
						leadName + "', '" + leadAddress + "', '" + leadEmail + "', '" +
								leadPhoneNumber + "', '" + leadType + "', 0, '1000-01-01', False)";
						
						try {
						    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
						    statement.executeUpdate();						    
						    
						} catch (SQLException e) {
						    e.printStackTrace();
						    JOptionPane.showMessageDialog(null, "There was a problem inserting this into the database. Please try again!");
						} finally {
						    mysqlConnect.disconnect();
						    System.out.println("Disconnected from the SQL server");
						}
						
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

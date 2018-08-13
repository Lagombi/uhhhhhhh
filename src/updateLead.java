import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class updateLead extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField curName;
	private JTextField newName;
	private JTextField newAddress;
	private JTextField newEmail;
	private JTextField newPhone;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			updateLead dialog = new updateLead();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public updateLead() {
		setTitle("Update Lead...");
		setBounds(100, 100, 530, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(10, 72, 147, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblAddress = new JLabel("Address:");
			lblAddress.setBounds(10, 97, 147, 14);
			contentPanel.add(lblAddress);
		}
		{
			JLabel lblEmail = new JLabel("email:");
			lblEmail.setBounds(10, 122, 147, 14);
			contentPanel.add(lblEmail);
		}
		{
			JLabel lblPhone = new JLabel("Phone #:");
			lblPhone.setBounds(10, 147, 147, 14);
			contentPanel.add(lblPhone);
		}
		{
			JLabel lblType = new JLabel("type:");
			lblType.setBounds(10, 172, 147, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblNameAsStored = new JLabel("Name in database:");
			lblNameAsStored.setBounds(10, 11, 201, 14);
			contentPanel.add(lblNameAsStored);
		}
		{
			JLabel lblInsertNewInfo = new JLabel("Insert new info below:");
			lblInsertNewInfo.setBounds(10, 36, 147, 14);
			contentPanel.add(lblInsertNewInfo);
		}
		{
			curName = new JTextField();
			curName.setBounds(167, 8, 337, 20);
			contentPanel.add(curName);
			curName.setColumns(10);
		}
		{
			newName = new JTextField();
			newName.setBounds(167, 69, 337, 20);
			contentPanel.add(newName);
			newName.setColumns(10);
		}
		{
			newAddress = new JTextField();
			newAddress.setBounds(167, 94, 337, 20);
			contentPanel.add(newAddress);
			newAddress.setColumns(10);
		}
		{
			newEmail = new JTextField();
			newEmail.setBounds(167, 119, 337, 20);
			contentPanel.add(newEmail);
			newEmail.setColumns(10);
		}
		{
			newPhone = new JTextField();
			newPhone.setBounds(167, 144, 337, 20);
			contentPanel.add(newPhone);
			newPhone.setColumns(10);
		}
		{
			spinner = new JSpinner();
			spinner.setModel(new SpinnerListModel(new String[] {"Stone Supplier", "Hotel", "Fabricator", "Existing Client", "Designer"}));
			spinner.setBounds(167, 169, 337, 20);
			contentPanel.add(spinner);
		}
		{
			JLabel lblHasRespondedTo = new JLabel("Has responded to email:");
			lblHasRespondedTo.setBounds(10, 197, 147, 14);
			contentPanel.add(lblHasRespondedTo);
		}
		
		JCheckBox hasResponded = new JCheckBox("");
		hasResponded.setBounds(167, 193, 97, 23);
		contentPanel.add(hasResponded);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JCheckBox chckbxIAmSure = new JCheckBox("I am sure I want to delete this lead");
			buttonPane.add(chckbxIAmSure);
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (chckbxIAmSure.isSelected()) {
						MysqlConnect mysqlConnect = new MysqlConnect();
						
						String oldName = curName.getText().replaceAll("[\';\"#()]", "");
						String sql = "DELETE FROM `leads` WHERE name = '" + oldName + "'";
						
						try {
						    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
						    statement.executeUpdate();						    
						    
						} catch (SQLException e1) {
						    e1.printStackTrace();
						    JOptionPane.showMessageDialog(null, "There was a problem removing this from the database. Please try again!");
						} finally {
						    mysqlConnect.disconnect();
						    System.out.println("Disconnected from the SQL server");
						}
						dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Please check the box to confirm a delete.");
				}
			});
			buttonPane.add(btnDelete);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//get all filled values here
						String oldName = curName.getText().replaceAll("[\';\"#()]", "");
						String leadName = newName.getText().replaceAll("[\';\"#()]", "");
						String leadAddress = newAddress.getText().replaceAll("[\';\"#()]", "");
						String leadEmail = newEmail.getText().replaceAll("[\';\"#()]", "");
						String leadPhone = newPhone.getText().replaceAll("[\';\"#()]", "");
						String leadType = spinner.getValue().toString().replaceAll("[\';\"#()]", "");
						String leadResponded = "False";
						if (hasResponded.isSelected()) leadResponded = "True";
						
						//
						MysqlConnect mysqlConnect = new MysqlConnect();
						
						String sql = "UPDATE `leads` SET name = '"
								+ leadName + "', address = '" + leadAddress + "', email = '" + leadEmail + "', phoneNumber = '" + leadPhone + "', type = '"
								+ leadType + "', hasResponded = " + leadResponded + " WHERE name = '" + oldName + "'";
						
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
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

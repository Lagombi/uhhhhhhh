import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class viewLead extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextArea textArea;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			viewLead dialog = new viewLead();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public viewLead() {
		setTitle("View Lead Info");
		setBounds(100, 100, 450, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(10, 11, 46, 14);
			contentPanel.add(lblName);
		}
		{
			txtName = new JTextField();
			txtName.setBounds(66, 8, 358, 20);
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			JButton btnLookUp = new JButton("Look Up");
			btnLookUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					MysqlConnect mysqlConnect = new MysqlConnect();
					String leadName = txtName.getText().replaceAll("[\';\"#()]", "");
					String sql = "SELECT * FROM `leads` WHERE name = '" + leadName + "'";
					String printMe = "";
					
					try {
					    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
					    ResultSet resultSet = statement.executeQuery();
					    
					    //resultSet extraction implementation by Zeb on StackOverflow
					    ResultSetMetaData rsmd = resultSet.getMetaData();
					    int columnsNumber = rsmd.getColumnCount();
					    while (resultSet.next())
					    {
					    	for (int i = 1; i <= columnsNumber; i++) {
					    		//if (i > 1) System.out.print(", \n");
					    		if (i > 1) printMe = printMe + ", \n";
					    		String columnValue = resultSet.getString(i);
					    		//System.out.print(columnValue + " " + rsmd.getColumnName(i));
					    		//System.out.print("[" + rsmd.getColumnName(i) + "]: " + columnValue);
					    		printMe = printMe + "[" + rsmd.getColumnName(i) + "]: " + columnValue;
					    		//System.out.print(columnValue);
					    	}
					    	//System.out.println("");
					    	printMe = printMe + "\n\n";
					    	//if (!resultSet.isLast()) System.out.print(", ");
					    }
					    
					    
					} catch (SQLException e1) {
					    e1.printStackTrace();
					} finally {
					    mysqlConnect.disconnect();
					    System.out.println("Disconnected from the SQL server");
					}
					textArea.setText(printMe);
				}
			});
			btnLookUp.setBounds(66, 39, 89, 23);
			contentPanel.add(btnLookUp);
		}
		{
			JLabel lblEmail = new JLabel("email:");
			lblEmail.setBounds(10, 80, 46, 14);
			contentPanel.add(lblEmail);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setBounds(66, 77, 358, 20);
			contentPanel.add(txtEmail);
			txtEmail.setColumns(10);
		}
		{
			JButton btnLookUp_1 = new JButton("Look Up");
			btnLookUp_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MysqlConnect mysqlConnect = new MysqlConnect();
					String leadEmail = txtEmail.getText().replaceAll("[\';\"#()]", "");
					String sql = "SELECT * FROM `leads` WHERE email = '" + leadEmail + "'";
					String printMe = "";
					
					try {
					    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
					    ResultSet resultSet = statement.executeQuery();
					    
					    //resultSet extraction implementation by Zeb on StackOverflow
					    ResultSetMetaData rsmd = resultSet.getMetaData();
					    int columnsNumber = rsmd.getColumnCount();
					    while (resultSet.next())
					    {
					    	for (int i = 1; i <= columnsNumber; i++) {
					    		//if (i > 1) System.out.print(", \n");
					    		if (i > 1) printMe = printMe + ", \n";
					    		String columnValue = resultSet.getString(i);
					    		//System.out.print(columnValue + " " + rsmd.getColumnName(i));
					    		//System.out.print("[" + rsmd.getColumnName(i) + "]: " + columnValue);
					    		printMe = printMe + "[" + rsmd.getColumnName(i) + "]: " + columnValue;
					    		//System.out.print(columnValue);
					    	}
					    	System.out.println("");
					    	printMe = printMe + "\n\n";
					    	//if (!resultSet.isLast()) System.out.print(", ");
					    }
					    
					    
					} catch (SQLException e1) {
					    e1.printStackTrace();
					} finally {
					    mysqlConnect.disconnect();
					    System.out.println("Disconnected from the SQL server");
					}
					textArea.setText(printMe);
				}
			});
			btnLookUp_1.setBounds(66, 108, 89, 23);
			contentPanel.add(btnLookUp_1);
		}
		{
			JLabel lblPhone = new JLabel("Phone #:");
			lblPhone.setBounds(10, 150, 46, 14);
			contentPanel.add(lblPhone);
		}
		{
			txtPhone = new JTextField();
			txtPhone.setBounds(66, 147, 358, 20);
			contentPanel.add(txtPhone);
			txtPhone.setColumns(10);
		}
		{
			JButton btnLookUp_2 = new JButton("Look Up");
			btnLookUp_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					MysqlConnect mysqlConnect = new MysqlConnect();
					String leadPhone = txtPhone.getText().replaceAll("[\';\"#()]", "");
					String sql = "SELECT * FROM `leads` WHERE phoneNumber = '" + leadPhone + "'";
					String printMe = "";
					
					try {
					    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
					    ResultSet resultSet = statement.executeQuery();
					    
					    //resultSet extraction implementation by Zeb on StackOverflow
					    ResultSetMetaData rsmd = resultSet.getMetaData();
					    int columnsNumber = rsmd.getColumnCount();
					    while (resultSet.next())
					    {
					    	for (int i = 1; i <= columnsNumber; i++) {
					    		//if (i > 1) System.out.print(", \n");
					    		if (i > 1) printMe = printMe + ", \n";
					    		String columnValue = resultSet.getString(i);
					    		//System.out.print(columnValue + " " + rsmd.getColumnName(i));
					    		//System.out.print("[" + rsmd.getColumnName(i) + "]: " + columnValue);
					    		printMe = printMe + "[" + rsmd.getColumnName(i) + "]: " + columnValue;
					    		//System.out.print(columnValue);
					    	}
					    	System.out.println("");
					    	printMe = printMe + "\n\n";
					    	//if (!resultSet.isLast()) System.out.print(", ");
					    }
					    
					    
					} catch (SQLException e1) {
					    e1.printStackTrace();
					} finally {
					    mysqlConnect.disconnect();
					    System.out.println("Disconnected from the SQL server");
					}
					textArea.setText(printMe);
					
				}
			});
			btnLookUp_2.setBounds(66, 178, 89, 23);
			contentPanel.add(btnLookUp_2);
		}
		{
			JLabel lblType = new JLabel("Type:");
			lblType.setBounds(10, 220, 46, 14);
			contentPanel.add(lblType);
		}
		{
			spinner = new JSpinner();
			spinner.setModel(new SpinnerListModel(new String[] {"Stone Supplier", "Hotel", "Fabricator", "Existing Client", "Designer"}));
			spinner.setBounds(66, 217, 358, 20);
			contentPanel.add(spinner);
		}
		{
			JButton btnLookUp_3 = new JButton("Look Up");
			btnLookUp_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					MysqlConnect mysqlConnect = new MysqlConnect();
					String leadType = spinner.getValue().toString().replaceAll("[\';\"#()]", "");
					String sql = "SELECT * FROM `leads` WHERE type = '" + leadType + "'";
					String printMe = "";
					
					try {
					    PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
					    ResultSet resultSet = statement.executeQuery();
					    
					    //resultSet extraction implementation by Zeb on StackOverflow
					    ResultSetMetaData rsmd = resultSet.getMetaData();
					    int columnsNumber = rsmd.getColumnCount();
					    while (resultSet.next())
					    {
					    	for (int i = 1; i <= columnsNumber; i++) {
					    		//if (i > 1) System.out.print(", \n");
					    		if (i > 1) printMe = printMe + ", \n";
					    		String columnValue = resultSet.getString(i);
					    		//System.out.print(columnValue + " " + rsmd.getColumnName(i));
					    		//System.out.print("[" + rsmd.getColumnName(i) + "]: " + columnValue);
					    		printMe = printMe + "[" + rsmd.getColumnName(i) + "]: " + columnValue;
					    		//System.out.print(columnValue);
					    	}
					    	System.out.println("");
					    	printMe = printMe + "\n\n";
					    	//if (!resultSet.isLast()) System.out.print(", ");
					    }
					    
					    
					} catch (SQLException e1) {
					    e1.printStackTrace();
					} finally {
					    mysqlConnect.disconnect();
					    System.out.println("Disconnected from the SQL server");
					}
					textArea.setText(printMe);
					
				}
			});
			btnLookUp_3.setBounds(66, 248, 89, 23);
			contentPanel.add(btnLookUp_3);
		}
		{
			JLabel lblResults = new JLabel("Results :");
			lblResults.setBounds(10, 274, 46, 14);
			contentPanel.add(lblResults);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 299, 414, 338);
			contentPanel.add(scrollPane);
			
			textArea = new JTextArea();
			scrollPane.setViewportView(textArea);
			textArea.setEditable(false);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		//JScrollPane scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	}
}

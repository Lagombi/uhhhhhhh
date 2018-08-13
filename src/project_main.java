/*
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.ResultSetMetaData;

//USE THIS CLASS to test queries for development

public class project_main {
	
	public static void getEmails(MysqlConnect mysqlConnect)
	{
		String usrE = "TEST";
		String sql = "SELECT * FROM `client` NATURAL JOIN `email` WHERE email = 'dan099@yahoo.com'";
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
		    		//System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    		System.out.print("[" + rsmd.getColumnName(i) + "]: " + columnValue);
		    	}
		    	System.out.println("");
		    }
		    
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		    System.out.println("Disconnected from the SQL server");
		}
	}
	
	
	public static void main (String[] args)
	{
		System.out.println("HelLo, world!");
		
		String printme = "What's up #quertyman\" 72()?".replaceAll("[\';\"#()]", "");
		System.out.println(printme);
		//
		MysqlConnect mysqlConnect = new MysqlConnect();
		String sql = "SELECT email FROM `client`";
		
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
		    		//System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    		//System.out.print("[" + rsmd.getColumnName(i) + "]: " + columnValue);
		    		System.out.print(columnValue);
		    	}
		    	//System.out.println("");
		    	if (!resultSet.isLast()) System.out.print(", ");
		    }
		    
		    
		} catch (SQLException e1) {
		    e1.printStackTrace();
		} finally {
		    mysqlConnect.disconnect();
		    System.out.println("Disconnected from the SQL server");
		}
	}
	//
	

}
*/
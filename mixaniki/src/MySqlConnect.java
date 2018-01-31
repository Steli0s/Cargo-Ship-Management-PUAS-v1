import java.sql.*;
import javax.swing.*;

public class MySqlConnect {
	Connection conn=null;
	
	public static Connection ConnectDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://78.46.89.150:3306/user2964783641_stelmix","user2_stek","S123456!");
			
			return conn;
		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
			
		}
		
	}
}

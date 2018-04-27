package MyF;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_MySQL {
	
	public static Connection getMyConnection(String ip, String id, String pw) {
		Connection result = null;
		
		String url = "jdbc:mysql://" + ip + ":3306";
		url += "?autoReconnect=true&useSSL=false";
		try {
			result = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}
	
	public static boolean useDB (Connection con, String strDB) {
		boolean result = false;
		Statement st = null;
		try {
			st = con.createStatement();
			st.execute("USE " + strDB + ";");
			result = true; 
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static ResultSet getSelect(Connection con, String SQL) {
		ResultSet result = null;
		Statement st = null;
		
		try {
			st = con.createStatement();
			if(st.execute(SQL)) {
				result = st.getResultSet();
			} else {
				result = null;
			} 
			st.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static boolean sendSQL (Connection con, String SQL) {
		boolean result = false;
		Statement st = null;
		
		try {
			st = con.createStatement();
			result = st.execute(SQL);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

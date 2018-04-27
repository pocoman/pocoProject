package MyF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Access {
	
	public Connection DBConnection(String dir, String AccessPassword) {
		// Access DB ¿¬°á
		// ucanaccess-2.0.9.2.jar, commons-lang-2.6.jar, 
		// commons-logging-1.1.1.jar, hsqldb.jar, jackcess-2.0.6.jar
				
		Connection dbConn = null;
		String url = "jdbc:ucanaccess://" + dir;
		try {
			if(AccessPassword.trim().equals("")) {
				dbConn = DriverManager.getConnection(url);
			} else {
				dbConn = DriverManager.getConnection(url, "", AccessPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbConn;
	}
	public boolean ExecuteUpdate(Connection dbConn, String SQL) {
		PreparedStatement stmt = null;
		boolean result = true;
		
		try {
			stmt = dbConn.prepareStatement(SQL);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	public ResultSet ExecuteQuery(Connection dbConn, String SQL) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = dbConn.prepareStatement(SQL);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			rs = null;
		}
		
		return rs;		
	}
	
	public boolean ShowRS(ResultSet rs) throws SQLException {
		boolean result = false;
		
		if(rs.isClosed() || rs == null) {
			return result;
		} else {
			try {
				while(rs.next()){
					for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
						System.out.print(rs.getString(i));
						if(i<rs.getMetaData().getColumnCount()) {
							System.out.print(" / ");
						} else {
							System.out.println("");
						}
					}
				}
				
				result = true;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}

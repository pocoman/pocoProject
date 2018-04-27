package WorkCarte;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import MyF.DB_MySQL;
import MyF.Function;

public class db_function implements infWork {
	static Connection con = null;
	
	static {
		con = frmProject.con;
	}
	
	
	public static ResultSet loadProjectData() {
		String SQL = "";
		
		SQL = " SELECT * ";
		SQL += " FROM " + TABLE_PROJECT;
		SQL += " ORDER BY pr_title; ";
		
		ResultSet rs = DB_MySQL.getSelect(con, SQL);
		
		return rs;
	}

	public static ResultSet loadProjectData(int id) {
		String SQL = "";
		
		SQL = " SELECT * ";
		SQL += " FROM " + TABLE_PROJECT;
		SQL += " WHERE pr_id = " + id + ";";
		
		ResultSet rs = DB_MySQL.getSelect(con, SQL);
		
		return rs;
	}
	public static ResultSet loadTicketData(int pr_id) {
		String SQL = "";
		
		SQL = " SELECT * ";
		SQL += " FROM " + TABLE_TICKET;
		SQL += " WHERE pr_id = " + pr_id;
		SQL += " ORDER BY updated DESC; ";
		
		ResultSet rs = DB_MySQL.getSelect(con, SQL);
		
		return rs;
	}

	public static ResultSet loadTicketData(int pr_id, int cr_id) {
		String SQL = "";
		
		SQL = " SELECT * ";
		SQL += " FROM " + TABLE_TICKET;
		SQL += " WHERE pr_id = " + pr_id;
		SQL += " AND cr_id = " + cr_id + ";";
		
		ResultSet rs = DB_MySQL.getSelect(con, SQL);
		
		return rs;
	}
	
	public static boolean saveProjectData(String SQL, String title, String key, String url) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		boolean result = false;
		
		pstmt.setString(1, title);
		pstmt.setString(2, key);
		pstmt.setString(3, url);
		
		try {
			pstmt.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static boolean saveProjectData(String SQL, String title, String key, String url, int id) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		boolean result = false;

		pstmt.setString(1, title);
		pstmt.setString(2, key);
		pstmt.setString(3, url);
		pstmt.setInt(4, id);

		try {
			pstmt.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean saveTicketData(String SQL, String[] data) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		boolean result = false;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		pstmt.setInt(1, Integer.parseInt(data[DATA_PR_ID]));
		pstmt.setString(2, data[DATA_TICKET]);
		pstmt.setString(3, data[DATA_TITLE]);
		try {
			Date st_date;
			Date ed_date;
			if(data[DATA_START].equals("Blank")) {
				st_date = null;
			} else {
				st_date = Function.ConvertUtilSQL(dateFormat.parse(data[DATA_START]));
			}
			if(data[DATA_END].equals("Blank")) {
				ed_date = null;
			} else {
				ed_date = Function.ConvertUtilSQL(dateFormat.parse(data[DATA_END]));
			}
			pstmt.setDate(4, st_date);
			pstmt.setDate(5, ed_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(data[DATA_HOUR].equals("Blank")) {
			pstmt.setInt(6, 0);
		} else {
			pstmt.setInt(6, Integer.parseInt(data[DATA_HOUR]));
		}
		pstmt.setString(7, data[DATA_MEMO]);
		pstmt.setString(8, data[DATA_CARTE]);

		try {
			pstmt.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean saveTicketData(String SQL, String[] data, int cr_id) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL);
		boolean result = false;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		pstmt.setString(1, data[DATA_TICKET]);
		pstmt.setString(2, data[DATA_TITLE]);
		try {
			Date st_date;
			Date ed_date;
			if(data[DATA_START].equals("Blank")) {
				st_date = null;
			} else {
				st_date = Function.ConvertUtilSQL(dateFormat.parse(data[DATA_START]));
			}
			if(data[DATA_END].equals("Blank")) {
				ed_date = null;
			} else {
				ed_date = Function.ConvertUtilSQL(dateFormat.parse(data[DATA_END]));
			}
			pstmt.setDate(3, st_date);
			pstmt.setDate(4, ed_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(data[DATA_HOUR].equals("Blank")) {
			pstmt.setInt(5, 0);
		} else {
			pstmt.setInt(5, Integer.parseInt(data[DATA_HOUR]));
		}
		pstmt.setString(6, data[DATA_MEMO]);
		pstmt.setString(7, data[DATA_CARTE]);
		pstmt.setInt(8, cr_id);

		try {
			pstmt.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

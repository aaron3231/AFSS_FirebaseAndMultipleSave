package databaseNew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import userName.UserName;

public class DatabaseNew{
	private Connection con = null; //Database Connection objects
	private Statement stmt = null;
	private String createDBHead = "CREATE DATABASE ";
	private String createDBTail = " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
	private String dbName = "default";

	public DatabaseNew(String name) {
		dbName = name;
	}
	
	public DatabaseNew()
	{
		dbName = UserName.userName;
	}

	public void createDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //註冊driver
		    con = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
		    try {
		    	con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, "root", "");
		    } catch(SQLException e) {
		    	stmt = con.createStatement();
			    try {
			    	stmt.execute(createDBHead + dbName + createDBTail);
			    } finally {
			    	stmt.close();
			    }
		    } finally {
		    	con.close();
		    }
		    con.close();
		    con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, "root", "");
	    } catch(ClassNotFoundException e1) {
	    	System.out.println("DriverClassNotFound :" + e1.toString());
	    } catch(SQLException e2) {
	    	System.out.println("Exception :" + e2.toString());
	    }
	}

	/*
	private ResultSet rs = null; //結果集
	private PreparedStatement pst = null; //執行,傳入之sql為預儲之字串,需要傳入變數之位置
	private String dropdbSQL = "DROP TABLE User ";

	private String createdbSQL = "CREATE TABLE User (" +
		"    id     INTEGER " +
		"  , name    VARCHAR(20) " +
		"  , passwd  VARCHAR(20))";

	private String insertdbSQL = "insert into User(id,name,passwd) " +
			"select ifNULL(max(id),0)+1,?,? FROM User";

	private String selectSQL = "select * from User ";
	*/

	/*
	public void createTable() {
	    try {
	    	stmt = con.createStatement();
	    	stmt.executeUpdate(createdbSQL);
	    } catch(SQLException e) {
	    	System.out.println("CreateDB Exception :" + e.toString());
	    } finally {
	    	Close();
	    }
	}

	public void insertTable(String name, String passwd) { //新增資料
	    try {
	    	pst = con.prepareStatement(insertdbSQL);
	    	pst.setString(1, name);
	    	pst.setString(2, passwd);
	    	pst.executeUpdate();
	    } catch(SQLException e) {
	    	System.out.println("InsertDB Exception :" + e.toString());
	    } finally {
	    	Close();
	    }
	}

	public void dropTable() { //刪除Table
	    try {
	    	stmt = con.createStatement();
	    	stmt.executeUpdate(dropdbSQL);
	    } catch(SQLException e) {
	    	System.out.println("DropDB Exception :" + e.toString());
	    } finally {
	    	Close();
	    }
	}

	public void SelectTable() { //查詢資料
	    try {
	    	stmt = con.createStatement();
	    	rs = stmt.executeQuery(selectSQL);
	    	System.out.println("ID\t\tName\t\tPASSWORD");
	    	while(rs.next()) {
	    		System.out.println(rs.getInt("id") + "\t\t" + rs.getString("name") + "\t\t" + rs.getString("passwd"));
	    	}
	    } catch(SQLException e) {
	    	System.out.println("DropDB Exception :" + e.toString());
	    } finally {
	    	Close();
	    }
	}

	private void Close() {
		//完整使用完資料庫後,記得要關閉所有Object
		//否則在等待Timeout時, 可能會有Connection poor的狀況
	    try {
	    	if(rs != null) {
	    		rs.close();
	    		rs = null;
	    	}
	    	if(stmt != null) {
	    		stmt.close();
	    		stmt = null;
	    	}
	    	if(pst != null) {
	    		pst.close();
	    		pst = null;
	    	}
	    } catch(SQLException e) {
	    	System.out.println("Close Exception :" + e.toString());
	    }
	}
	*/
}
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import userName.UserName;

public abstract class Connect_data {
	  protected Connection con = null; 
	  protected Statement stat = null; 
	  protected ResultSet rs = null; 
	  protected PreparedStatement pst = null; 
	  public Connect_data() {
		  connect_data(); 
	  } 
	  //與資料庫建立連線
	  public  void connect_data() 
	  { 
	     try { 
	      Class.forName("com.mysql.jdbc.Driver"); 
	      con = DriverManager.getConnection( 
	      "jdbc:mysql://localhost/" + UserName.userName +"?useUnicode=true&characterEncoding=Big5",
	      "root","");    
	      System.out.println("建立連結    "+UserName.userName ); 
	    } 
	    catch(ClassNotFoundException e) 
	    { 
	      System.out.println("DriverClassNotFound :"+e.toString()); 
	    }
	    catch(SQLException x) { 
	      System.out.println("Exception :"+x.toString()); 
	    } 
	     catch(NullPointerException e)
	     {
	    	 System.out.println("GG"); 
	     }
	  }
	  public void setClose() throws SQLException {con.setAutoCommit(false);}
	  public void setOpen() throws SQLException {    con.commit();
	  con.setAutoCommit(true);
		  
	  }
	//與資料庫關閉連線
	  public void Close() { 
	      try 
	      { 
	        if(rs!=null) 
	        { 
	          rs.close(); 
	          rs = null; 
	        } 
	        if(stat!=null) 
	        { 
	          stat.close(); 
	          stat = null; 
	        } 
	        if(pst!=null) 
	        { 
	          pst.close(); 
	          pst = null; 
	        } 
	      } 
	      catch(SQLException e) 
	      { 
	        System.out.println("Close Exception :" + e.toString()); 
	      } 
	    }  
}

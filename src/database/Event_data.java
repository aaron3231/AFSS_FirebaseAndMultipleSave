package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Event_data extends Connect_data{
	  private String dropdbSQL = "DROP TABLE Event "; 
	  private String createdbSQL = "CREATE TABLE Event (" + 
	    "    Event_id           INTEGER " + 
	    "  , Event_Type         VARCHAR(20) CHARACTER SET utf8mb4"+
	    "  , Event_Description  VARCHAR(80) CHARACTER SET utf8mb4"+
	    "  , Date_Id            INTEGER)"
       ; 
	  
	  private String insertdbSQL = "insert into Event(Event_id,Event_Type,Event_Description,Date_Id) " + 
	      "select ifNULL(max(Event_id),0)+1,?,?,? FROM Event"; 
	  private String selectCountSQL = "SELECT COUNT(*) FROM Event";     
	  private String selectSQL = "select * from Event "; 
	  private String setPrimaryKey="ALTER TABLE Event ADD PRIMARY KEY(Event_id)";
	  private String setReference="ALTER TABLE Event ADD FOREIGN KEY (Date_Id) REFERENCES Date(Date_Id) ON DELETE RESTRICT ON UPDATE RESTRICT";
      private String selectWantedSQL="";
      
	  public Event_data() 
	  { 
		  connect_data();
	  } 
	  //建立table的方式 
	  public void createTable() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      stat.executeUpdate(createdbSQL);
	      stat.executeUpdate(setPrimaryKey); 
	      stat.executeUpdate(setReference); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("CreateDB EventTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  //新增資料  
	  public void insertTable( String event_type,String Event_description,int Date_id) 
	  { 
	    try 
	    { 
	      pst = con.prepareStatement(insertdbSQL); 
	      
	   
	      pst.setString(1, event_type); 
	      pst.setString(2, Event_description); 
	      pst.setInt(3, Date_id);
	      
	      pst.executeUpdate(); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("InsertDB EventTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  // 刪除table內的所有資料
	  public void deleteData() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      stat.executeUpdate("DELETE  FROM Event"); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB EventTable deleteData Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  //刪除Table
	  public void dropTable() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      stat.executeUpdate(dropdbSQL); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB EventTable dropTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  // count資料的資料
	  public int CountData() 
	  { 
		int count=0;
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(selectCountSQL); 
	      
	      while(rs.next()){
	    	    count=rs.getInt("COUNT(*)");
	    	    System.out.println("COUNT(*)="+rs.getInt("COUNT(*)"));	
			  }
	     
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB EventTable CountData Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	     return count;
	  } 
	  //Select 欲選取的資料
	  public void SelectType(String[] get,String Select_dateBegin,String Select_dateEnd) 
	  { 
	    try 
	    { 
	      int temp=0;
	      pst = con.prepareStatement(selectWantedSQL);
		    
	      this.selectWantedSQL = "select * from Event inner join Date on Event.Date_Id =Date.Date_id where Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
	     // pst.executeUpdate(selectWantedSQL); 
	      pst.executeQuery(selectWantedSQL);
	  //    System.out.println(selectWantedSQL);   
	      rs=pst.getResultSet();
	  //    System.out.println("ID\t\tEvent_Type"); 
	      while(rs.next()) 
	      { 
	   // 	  System.out.println(rs.getInt("Event_id")+"\t\t"+rs.getString("Event_Type")); 
            get[temp]=(rs.getString("Event_Type"));   
            temp++;
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB EventTable SelectType Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  //Select 欲選取的資料
	  public void SelectDescription(String[] get,String Select_dateBegin,String Select_dateEnd) 
	  { 
	   
	      int temp=0;
	      try {
			pst = con.prepareStatement(selectWantedSQL);
	        this.selectWantedSQL = "select * from Event inner join Date on Event.Date_Id=Date.Date_id where Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
	        pst.executeQuery(selectWantedSQL);
	        rs=pst.getResultSet();
	 	  while(rs.next()){ 
	  //  	  System.out.println(rs.getInt("Event_id")+"\t\t"+rs.getString("Event_Description")); 
            get[temp]=(rs.getString("Event_Description"));   
            temp++;
	       } 
	      }
	       catch (SQLException e) {
	 	      System.out.println("DropDB EventTable SelectDescription Exception :" + e.toString()); 
		}
	     finally { Close();} 
	    
	  } 
	  
	  public void SelectDateDescription(String[] get,String Select_dateBegin,String Select_dateEnd) 
	  { 
	   
	      int temp=0;
	      try {
			pst = con.prepareStatement(selectWantedSQL);
	        this.selectWantedSQL = "select * from Event inner join Date on Event.Date_Id=Date.Date_id where Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
	        pst.executeQuery(selectWantedSQL);
	        rs=pst.getResultSet();
	 	  while(rs.next()){ 
	  //  	  System.out.println(rs.getInt("Event_id")+"\t\t"+rs.getString("Event_Description")); 
            get[temp]=(rs.getString("Date_time"));   
            temp++;
	       } 
	      }
	       catch (SQLException e) {
			// TODO Auto-generated catch block
		  System.out.println("DropDB EventTable SelectDateDescription Exception :" + e.toString()); 
		}
	     finally { Close();} 
	    
	  } 
	  
	  public int getDataCount(String Select_dateBegin,String Select_dateEnd) {  
		  int getCount=0;
		  try{
			pst = con.prepareStatement(selectWantedSQL);
		    this.selectWantedSQL = "select COUNT(*) as count from Event inner join Date on Event.Date_id=Date.Date_id where Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
		    // System.out.print(selectWantedSQL+"\n");
		    pst.executeQuery(selectWantedSQL);
		    rs=pst.getResultSet();	
		  while(rs.next()){ 
		    	  getCount=(rs.getInt("count"));  
		      } 
		   } 
		  catch (SQLException e) {
			// TODO Auto-generated catch block
	 	      System.out.println("DropDB EventTable getDataCount Exception :" + e.toString()); 
		   }
           finally { Close();
		      return getCount;
           }
	  }
	  //查詢資料 
	  public void SelectTable() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(selectSQL); 
	      System.out.println("ID\t\tEvent_Type"); 
	      while(rs.next()) 
	      { 
	        System.out.println(rs.getInt("Event_id")+"\t\t"+rs.getString("Event_Type")); 
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB EventTable SelectTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cost_data extends Connect_data{
  	  private String dropdbSQL = "DROP TABLE  Cost";   
	  private String createdbSQL = "CREATE TABLE Cost (" + 
	    "    Cost_id            INTEGER " + 
	    "  , Cost_Thing_Name    VARCHAR(20) CHARACTER SET utf8mb4 " + 
	    "  , Cost_Money         INTEGER"+
	    "  , Date_ID            INTEGER)"
       ; 
	  
	  private String insertdbSQL = "insert into Cost(Cost_id,Cost_Thing_Name ,Cost_Money,Date_ID) " + 
	      "select ifNULL(max(Cost_id),0)+1,?,?,? FROM Cost"; 
	  private String selectCountSQL = "SELECT COUNT(*) FROM Cost"; 
	  private String selectSQL = "select * from Cost "; 
	  private String setPrimaryKey="ALTER TABLE Cost ADD PRIMARY KEY(Cost_id)";
	  private String setReferenceKey=" ALTER TABLE `cost` ADD FOREIGN KEY (Date_ID) REFERENCES date(Date_id)";
      private String selectWantedSQL="";
	  
	  public Cost_data() 
	  { 
		  connect_data();
	  } 
	  //建立table
	  public void createTable() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      stat.executeUpdate(createdbSQL);
	      stat.executeUpdate(setPrimaryKey); 
	      stat.executeUpdate(setReferenceKey); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("CreateDB  createCostTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  //新增資料 
	  public void insertTable(String name,int money,int Date_id) 
	  { 
	    try 
	    { 
	      pst = con.prepareStatement(insertdbSQL); 
	      
	      pst.setString(1, name); 
	      pst.setInt(2, money); 
	      pst.setInt(3, Date_id); 
	      
	      pst.executeUpdate(); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("InsertDB  insertCostTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  
	  public int getDataCount(String Select_dateBegin,String Select_dateEnd) {
		  int getCount=0;
		  try {
			pst = con.prepareStatement(selectWantedSQL);
		
		  this.selectWantedSQL = "select count(*) as count from Cost inner join Date on Cost.Date_id=Date.Date_id where Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
		   pst.executeQuery(selectWantedSQL);
		   rs=pst.getResultSet();
		   
		      while(rs.next()) 
		      { 
		     
		    	  getCount=(rs.getInt("count"));  
		      } 
		      } 
		  catch (SQLException e) {
			// TODO Auto-generated catch block
		      System.out.println("InsertDB  CostTable getDataCount Exception :" + e.toString()); 
		}
		  
		  finally{  Close();
		      return getCount;
		    }
	  }
	  
	  // count資料的資料
	  public int CountData() 
		  { 
			int count=0;
		    try 
		    { 
		      int temp=0;
		      stat = con.createStatement(); 
		      rs = stat.executeQuery(selectCountSQL); 
		      
		      while(rs.next()){
		    	    count=rs.getInt("COUNT(*)");
		    	    System.out.println("COUNT(*)="+rs.getInt("COUNT(*)"));	
				  }
		     
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("DropDB CostTable CountData Exception :" + e.toString()); 
		    } 
		    finally 
		    { 
		      Close(); 
		    } 
		     return count;
		  } 
	  // 刪除table內的所有資料
	  public void deleteData() 
		  { 
		    try 
		    { 
		      stat = con.createStatement(); 
		      stat.executeUpdate("DELETE  FROM Cost"); 
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("DropDB CostTable deleteData Exception :" + e.toString()); 
		    } 
		    finally 
		    { 
		      Close(); 
		    } 
		  } 
	  //刪除Table, 
	  public void dropTable() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      stat.executeUpdate(dropdbSQL); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB CostTable dropTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  }
	  public void SelectType(String[] get,String Select_dateBegin,String Select_dateEnd) 
	  { 
	    try 
	    { 
	      int temp=0;
	      pst = con.prepareStatement(selectWantedSQL);
		    
	      this.selectWantedSQL = "select * from Cost inner join Date on Cost.Date_ID=Date.Date_id where Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
	     // pst.executeUpdate(selectWantedSQL); 
	      pst.executeQuery(selectWantedSQL);
	//      System.out.println(selectWantedSQL);   
	      rs=pst.getResultSet();
	      while(rs.next()) 
	      { 
	    	//  System.out.println(rs.getInt("Cost_id")+"\t\t"+ 
	  	     //       rs.getString("Cost_Thing_Name")+"\t\t"+rs.getInt("Cost_Money")); 
            get[temp]=(rs.getString("Cost_Thing_Name"));   
            temp++;
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB CostTable SelectType Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  
	  public void SelectCost(int[] get,String Select_dateBegin,String Select_dateEnd) 
	  { 
	    try 
	    { 
	      int temp=0;
	      pst = con.prepareStatement(selectWantedSQL);
		    
	      this.selectWantedSQL = "select * from Cost inner join Date on Cost.Date_ID =Date.Date_id where Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
	     // pst.executeUpdate(selectWantedSQL); 
	      pst.executeQuery(selectWantedSQL);
	 //     System.out.println(selectWantedSQL);   
	      rs=pst.getResultSet();
	      while(rs.next()) 
	      { 
	    	//  System.out.println(rs.getInt("Cost_id")+"\t\t"+ 
	  	     //       rs.getString("Cost_Thing_Name")+"\t\t"+rs.getInt("Cost_Money")); 
            get[temp]=(rs.getInt("Cost_Money"));   
            temp++;
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB CostTable SelectCost Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  //查詢資料 
	  public void SelectTable() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(selectSQL); 
	      System.out.println("Cost_ID\t\tCost_Thing_Name\t\tCost_Money"); 
	      while(rs.next()) 
	      { 
	        System.out.println(rs.getInt("Cost_id")+"\t\t"+ 
	            rs.getString("Cost_Thing_Name")+"\t\t"+rs.getInt("Cost_Money")); 
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB CostTable SelectTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  	 
}

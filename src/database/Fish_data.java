package database;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Fish_data extends Connect_data {
	 private  SecureRandom randomNumbers = new SecureRandom();
	  private String dropdbSQL = "DROP TABLE Fish "; 	  
	/*  private String createdbSQL = "CREATE TABLE Fish (" + 
			    "    Fish_id           INTEGER " + 
			    "  , Fish_Ename       VARCHAR(20) " + 
			    "  , Fish_CName       VARCHAR(20)"+
			    "  , Fish_Length      INTEGER "+
			    "  , Fish_Satiation   INTEGER "+
			    "  , Fish_Age         INTEGER "+
			    "  , Fish_Lively      INTEGER "+
			    "  , Fish_hurt        INTEGER "+
			    "  , Fish_death       INTEGER "+
			    "  , Date_ID          INTEGER )"
			    ; */
	  private String createdbSQL = "CREATE TABLE Fish (" + 
			    "    Fish_id           VARCHAR(20) CHARACTER SET utf8mb4" + 
			    "  , Fish_CName        VARCHAR(20) CHARACTER SET utf8mb4" + 
			    "  , Fish_Length       DOUBLE "+
			    "  , Fish_Satiation    INTEGER "+
			    "  , Fish_Age          INTEGER "+
			    "  , Fish_Lively       INTEGER "+
			    "  , Fish_status       VARCHAR(20) CHARACTER SET utf8mb4 "+
			    "  , Fish_Healthy    VARCHAR(20) CHARACTER SET utf8mb4"+
			    "  , Date_ID           INTEGER )"
			    ; 
	  private String SelectingData = "";
	  private String SelectingDataName = "";
	
	  // ? SQL ?
	  private String insertdbSQL = "insert into Fish(Fish_id,Fish_CName,Fish_Length,Fish_Satiation,Fish_Age,Fish_Lively,Fish_Status,Fish_Healthy,Date_ID) VALUES(?,?,?,?,?,?,?,?,?)"; 
          private String selectCountSQL = "SELECT COUNT(*) FROM Fish";
      	  private String selectCountFishSQL = "SELECT max(Fish_id) FROM Fish";
          private String selectDiedFishNum = "";
	  private String selectSQL = "select * from Fish "; 
	  private String selectFishSQL = ""; 
	  private String selectWantedSQL = ""; 
	  private String setPrimaryKey="ALTER TABLE Fish ADD PRIMARY KEY(Fish_id,Date_ID)";
	  private String setReferenceKey="ALTER TABLE `fish` ADD FOREIGN KEY (`Date_ID`) REFERENCES `date`(`Date_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;";
	 
	  private double Fish_Length;
	  private int Fish_Satiation;
	  private int Fish_Age;
	  private String Fish_Status;
	  private String Fish_Healthy;
	  
	  
	  public Fish_data()
	  {  
		  connect_data();
	  }
	
	  //
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
	      System.out.println("CreateDB FishTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  
	  public void SetInsert(int lifetime,double length ,int Satiation,String myStatus,String myHealthy) {
		  this.Fish_Age=lifetime;
		  this.Fish_Length=length;
		  this.Fish_Satiation=Satiation;
		  this.Fish_Status=myStatus;
		  this.Fish_Healthy=myHealthy;
		  
	  }
	  
	  //
	  public void insertTable(String id,String C_name,int lively,int DateID) 
	  { 
	    try 
	    { 
	      pst = con.prepareStatement(insertdbSQL); 
	     	      
	      // insert ¶¶§?�¨Ì¦�? Fish_id -- CName - Length - Satiation - 
	      //   Age - Lively - Status- Healthy -DateID 
	      pst.setString(1,id);
	      pst.setString(2, C_name); 
	      pst.setDouble(3, this.Fish_Length); 
	      pst.setInt(4, this.Fish_Satiation); 
	      pst.setInt(5, this.Fish_Age);
	      pst.setInt(6, lively);
	      pst.setString(7, this.Fish_Status);
	      pst.setString(8, this.Fish_Healthy);
	      pst.setInt(9, DateID );
	      
	      pst.executeUpdate(); 
	    
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("InsertDB FishTable  Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
  
	  //
	  public void dropTable() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      stat.executeUpdate(dropdbSQL); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB FishTable  Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  }
	
	  //
	  public void deleteData() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      stat.executeUpdate("DELETE  FROM Fish"); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB FishTable daleteData Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  }
	
	  // count ?
	  public int CountFishNum() 
	  { 
		int count=0;
	    try 
	    { 
	      int temp=0;
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(selectCountFishSQL); 

	      while(rs.next()){
		    count=rs.getInt("max(Fish_id)");
//		    	    System.out.println("COUNT(*)="+rs.getInt("COUNT(*)"));	
			  }

	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB fishTable  CountFishNum Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	     return count;
	  }

	  //cpunt ?
	  public int SelectDiedFishNum() { 
		  int temp=0;
	    try 
	    { 


	       pst = con.prepareStatement(selectDiedFishNum);

	      this.selectDiedFishNum = "SELECT count(DISTINCT Fish_id) as num FROM fish WHERE Fish_status='DEATH'";
	     // pst.executeUpdate(selectWantedSQL); 
	      pst.executeQuery(selectDiedFishNum);
	      System.out.println(selectDiedFishNum);   
	      rs=pst.getResultSet();
	      //System.out.println("ID\t\tName\t\t"+SelectingDataName); 
	      while(rs.next()) 
	      { 
	    temp=(rs.getInt("num"));   
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB FishTable SelectDiedFishNum  Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	      return temp;
	    } 
	  }
	
	  // count ?
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
	      System.out.println("DropDB FishTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	     return count;
	  }
	
	//
	  public void setSelectType(String SelectingData ) {
		  this.SelectingData=SelectingData;	  
	  }
	
	//
	  public void setSelectTypeName(String SelectingData ) {
		if(SelectingData=="Length" ) {
			this.SelectingDataName="Fish_Length";
		}
		else if(SelectingData=="Satiation" ) {
			this.SelectingDataName="Fish_Satiation";
		}
		else if(SelectingData=="Lively" ) {
			this.SelectingDataName="Fish_Lively";
		}
	  }
	
	  //Select ?
	  public void SelectData(int[] a,int fish_id,String Select_dateBegin,String Select_dateEnd) { 
	    try 
	    { 
	      int temp=0;
	
	       pst = con.prepareStatement(selectWantedSQL);
	    
	      this.selectWantedSQL = "select ("+SelectingDataName+") from Fish inner join Date on Fish.Date_id=Date.Date_id where Fish.Fish_id="+fish_id+" and Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
	     // pst.executeUpdate(selectWantedSQL); 
	      pst.executeQuery(selectWantedSQL);
	      System.out.println(selectWantedSQL);   
	      rs=pst.getResultSet();
	      System.out.println("ID\t\tName\t\t"+SelectingDataName); 
	      while(rs.next()) 
	      { 
	     
            a[temp]=(rs.getInt(SelectingDataName));   
            temp++;
	    	
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB FishTable SelectData Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  }
	
	  public void SelectFIshID(String[] a) { 
		    try 
		    { 
		      int temp=0;
		
		       pst = con.prepareStatement(selectFishSQL);		    
		      this.selectFishSQL = "select distinct (Fish_id) as FishID from Fish";
		     // pst.executeUpdate(selectWantedSQL); 
		      pst.executeQuery(selectFishSQL);
		 //     System.out.println(selectFishSQL);   
		      rs=pst.getResultSet();
	//	      System.out.println("ID\t\tName\t\t"+SelectingDataName); 
		      while(rs.next()) 
		      { 
	            a[temp]=rs.getString("FishID");   
	            temp++;
		      } 
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("DropDB FishTable  SelectFIshID Exception :" + e.toString()); 
		    } 
		    finally 
		    { 
		      Close(); 
		    } 
	  }
	
	  //?
	  public int SelectAVGData(int a,int fish_id,String Select_dateBegin,String Select_dateEnd) { 
	     int temp=0;
	     try 
	    { 
	     
	       pst = con.prepareStatement(selectWantedSQL);
	       this.selectWantedSQL = "select AVG("+SelectingDataName+")as avg from Fish inner join Date on Fish.Date_id=Date.Date_id where Fish.Fish_id="+ fish_id+" and Date.Date_time>='"+Select_dateBegin+"' and Date.Date_time<'"+Select_dateEnd+"'"; 
           System.out.println(selectWantedSQL); 
	       pst.executeQuery(selectWantedSQL);
	      
	        rs=pst.getResultSet();
	    //  System.out.println("ID\t\tName\t\t"+SelectingDataName); 
	      while(rs.next()) 
	      { 
	    	  temp = rs.getInt("avg");
	   // 	  System.out.println(temp);
	    	
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB FishTable SelevtAvgData Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close();
	    
	    }   
	    return temp;
	  }
	
	  //Select ?
	  public void SelectTable() 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(selectSQL); 
	      System.out.println("ID\t\tEName\t\tFish_Length\tFish_Satiation\tFish_Death"); 
	      while(rs.next()) 
	      { 
	        System.out.println(rs.getInt("Fish_id")+"\t\t"+ 
	            rs.getString("Fish_CName")+"\t\t"+rs.getInt("Fish_Length")+"\t\t"+rs.getInt("Fish_Satiation")+"\t\t"+rs.getInt("Fish_Lively")); 
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("DropDB  FishTable SelectTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	     Close(); 
	    } 
	  }	
}

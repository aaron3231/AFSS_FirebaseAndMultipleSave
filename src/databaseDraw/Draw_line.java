package databaseDraw;


import database.Fish_data;

/*Draw used import*/
import java.awt.GridLayout;  
import javax.swing.JFrame;  
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;   
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;  
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.date.SerialDate;
import org.jfree.ui.RectangleInsets;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
  
public class Draw_line {  
	public enum ShowChartTime {DAY,MONTH,YEAR};
	// 畫圖表的chanel
	ChartPanel frame1;
    // 查詢的 Fish_id 資料總類 起始日期  終止日期
    private int fish_id;
	private String Select_Datatype;
	private String Select_dateBegin;
	private String Select_dateEnd;
	// 查詢按年或月或日
	int Select_type;
	// 轉換的起始即終止日期
	private Calendar Begin_Time;	
	private Calendar End_Time;
	private long Pass_day=0;
	private long Pass_Hours=0;
	private long Pass_Month=0;
	private ShowChartTime Timetype;
	
	
	// 各自連資料庫的宣告
	private static  Fish_data FishData=new Fish_data();


    public Draw_line() {   	
    }   
    public Draw_line(int fish_id,String Select_Datatype,String Select_dateBegin,String Select_dateEnd){ 
        this.fish_id=fish_id;
    	this.Select_Datatype=Select_Datatype;
    	this.Select_dateBegin=Select_dateBegin;
    	this.Select_dateEnd=Select_dateEnd;
    }
    
    // 把ChartPanel 返還畫面給呼叫的物件
    public ChartPanel getChartPanel_Simple() throws ParseException{  
     
    	System.out.print("++++++++++++    "+fish_id+"  +++++++++++++++++++++++++++++\n");
    	
       Set_Date_Calendar();
       set_show_chart();    
      
       // 建立 XYDATA  XY軸圖表的資料
       XYDataset xydataset = createDataset(fish_id,Select_Datatype,Select_dateBegin,Select_dateEnd);  
 
       JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("魚類", "日期", Select_Datatype,xydataset, true, true, false);  
       XYPlot xyplot = (XYPlot) jfreechart.getPlot();  

       DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();  
       
       switch(String.valueOf(Timetype)) {
          case "YEAR":{
    	 //   System.out.print("Year\n");
       	    dateaxis.setDateFormatOverride(new SimpleDateFormat("YYYY/MM"));
       	    break;
          } 
          case "MONTH": {
    	   // System.out.print("Month\n");
       	    dateaxis.setDateFormatOverride(new SimpleDateFormat("MM/dd")); 
       	    break;
          }
          case "DAY":{
    	//    System.out.print("Day\n");
       	    dateaxis.setDateFormatOverride(new SimpleDateFormat("HH")); 
       	    break;
          }
       }
       	  
       
       frame1=new ChartPanel(jfreechart,true);  
       frame1.setFillZoomRectangle(true);
      // frame1.setMouseWheelEnabled(true);
       
       //水平底部標題
       dateaxis.setLabelFont(new Font("新細明體",Font.BOLD,14));         
       //垂直標題
       dateaxis.setTickLabelFont(new Font("新細明體",Font.BOLD,12));
       //獲取柱狀
       //設置標题字體  
       ValueAxis rangeAxis=xyplot.getRangeAxis();  
       rangeAxis.setLabelFont(new Font("新細明體",Font.BOLD,15));  
       jfreechart.getLegend().setItemFont(new Font("新細明體", Font.BOLD, 15));  
       jfreechart.getTitle().setFont(new Font("新細明體",Font.BOLD,20));
  
   	   return frame1;       
     
    }  
   
    
    
    //	把得到的日期轉換至Calendar
    public void  Set_Date_Calendar() {
    	Begin_Time=Calendar.getInstance();
    	End_Time=Calendar.getInstance();
    	
    	String[] splittedStr0 = Select_dateBegin.split("[/ ]");
 	    String[] splittedStr1 = Select_dateEnd.split("[/ ]");
    	int []date1 =new int [4];
    	int []date2 =new int [4];
 	    for(int i=0;i<3;i++) {
 		   date1[i]=Integer.parseInt(splittedStr0[i]);
 		   date2[i]=Integer.parseInt(splittedStr1[i]);
 		  System.out.println(date1[i]+" ----"+date2[i]+"\n");
 		   
 	   }
 	   Begin_Time.set(date1[0],date1[1]-1,date1[2],0,0,0);		
 	   End_Time.set(date2[0],date2[1]-1,date2[2],0,0,0);	
 	   
 	   
 	  System.out.println(Begin_Time.getTime()+" Begin----\n");
 	 System.out.println(End_Time.getTime()+ " End*****\n");
 	   
    }
    //得到顯示的資料日期基準 (年或月或日)
    // show_type= ={DAY Month Year}
    public void  set_show_chart() throws ParseException {
    	Date beginDate;
		Date endDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		beginDate = sdf.parse(Select_dateBegin);
		endDate = sdf.parse(Select_dateEnd);
		Pass_Hours=TimeUnit.MILLISECONDS.toHours(endDate.getTime()-beginDate.getTime()-1);
		Pass_day=TimeUnit.MILLISECONDS.toDays(endDate.getTime()-beginDate.getTime()-1);
		Pass_Month=TimeUnit.MILLISECONDS.toDays(endDate.getTime()-beginDate.getTime()-1);
		Pass_Month=(Pass_Month/30);
		if(Pass_day>=150) {
			this.Timetype=ShowChartTime.YEAR;
		}
		else if(Pass_day>1) {
			this.Timetype=ShowChartTime.MONTH; 
		}
		else {
			this.Timetype=ShowChartTime.DAY;
		}
		System.out.print("\n"+String.valueOf(Timetype)+"+++\n");	
    }
    //得到顯示的資料 (長度 飢餓度 生命值) 
    public void  set_show_chartData() {
    	int show_type=0;
    	switch(Select_Datatype) {
           case"Length":{
        	  FishData.setSelectType("Length");  
        	  FishData.setSelectTypeName("Length");
        	  break;
           }	
           case"Satiation":{
        	  FishData.setSelectType("Satiation");   
        	  FishData.setSelectTypeName("Satiation");
        	  break;
           }
           case"Lively":{
        	  FishData.setSelectType("Lively");   
        	  FishData.setSelectTypeName("Lively");
        	  break;
           }
         }
    	}
    
    
    // 畫出資料顯示線
     private  XYDataset createDataset(int fish_id,String Select_Datatype,String Select_dateBegin,String Select_dateEnd) throws ParseException {  
    	 
            XYSeries  timeseries  = new XYSeries(Select_Datatype);  
           
            int[] getData=new int[200];
           
            Date beginDate ;
 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    		beginDate = sdf.parse(Select_dateBegin);
    		System.out.print(beginDate+"\n");
    		System.out.print(Pass_Hours+" 經過小時\n");
    		
    		Calendar temp2;
    		temp2=Calendar.getInstance();
    		temp2.setTime(Begin_Time.getTime());
    		
    		switch(String.valueOf(Timetype)) {
            case "DAY":{
            	selectOneDay(getData);
            //	System.out.print(Pass_Hours);
            	 for(int i=0;i<Pass_Hours;i++) {
                   	System.out.print(getData[i]+" ");
                    temp2.add(Calendar.HOUR, 1);
                  	timeseries.add(temp2.getTime().getTime(), getData[i]);
            	 }
            	 break;
            }
            	
            case "MONTH":{
            	selectOneMonth(getData);
            	  for(int i=0;i<=Pass_day;i++) {
            		  System.out.print(getData[i]+" "+temp2.getTime()+"\n");
            		  
                  	timeseries.add(temp2.getTime().getTime(), getData[i]);
            		temp2.add(Calendar.DAY_OF_MONTH, 1);
                  }
            	  break;
            }
            
            case "YEAR":{
            	selectOneYear(getData);
            	System.out.print("+++++ Pass_Month  "+Pass_Month+"+++++\n");
            	for(int i=0;i<=Pass_Month;i++) {
                 	System.out.print(getData[i]+" "+(temp2.getTime().getMonth()+1)+"\n");
                  	timeseries.add(temp2.getTime().getTime(), getData[i]);
                    temp2.add(Calendar.MONTH, 1);  
                                   
            	}
            	break;
            }
            
            }
            
          
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries( timeseries);
         
            return dataset;  
        }
    
     
    // 1.根據不同的選取範圍決定SQL的類型
    // 2.根據不同的範圍得到資料  (  資料陣列 , 魚的ID , 日期的範圍(幾年或幾月或幾日) ) 
    public void selectOneDay(int []a) {
    	// 設定要選取資料欄位的SQL
    	set_show_chartData() ;
        FishData.SelectData(a,this.fish_id,Select_dateBegin,Select_dateEnd);
    }
    public void selectOneMonth(int []a) throws ParseException {
        // 設定要選取資料欄位的SQL
        set_show_chartData() ; 
    	// temp date 的切換 方便呼叫資料庫對應函式
    	String date="";
 	    String date2="";
 	  
		System.out.print(Pass_day+" 經過天數\n");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
	
		Calendar temp2;
		temp2=Calendar.getInstance();
		temp2.setTime(Begin_Time.getTime());
	    
		Calendar temp1;
		temp1=Calendar.getInstance();
		temp1.setTime(Begin_Time.getTime());
		
		date=sdf.format(Begin_Time.getTime());
		date2=sdf.format(temp2.getTime());
		
		int temp=0;
		//執行SQL 抽取每一個日的AVG () DATA
		for(int i=0;i<=Pass_day;i++) {
			
			temp2.setTime(temp1.getTime());
			temp1.add(Calendar.DAY_OF_WEEK,1);
			date=sdf.format(temp1.getTime());
			date2=sdf.format(temp2.getTime());	
			a[i]=FishData.SelectAVGData(temp,this.fish_id,date2,date);
		}
    }
    public void selectOneYear(int []a) {
    	// 設定要選取資料欄位的SQL
    	set_show_chartData() ; 
    	  
 	    String date="";
 	    String date2="";
 	  
		System.out.print(Pass_day+" 經過天數\n");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
    	
		// temp date 的切換 方便呼叫資料庫對應函式

		Calendar temp2;
		temp2=Calendar.getInstance();
		temp2.setTime(Begin_Time.getTime());
		
		Calendar temp1;
		temp1=Calendar.getInstance();
		temp1.setTime(Begin_Time.getTime());
	    
		date=sdf.format(Begin_Time.getTime());
		date2=sdf.format(temp2.getTime());
		
		
		//執行SQL 抽取每一個月的AVG () DATA
		int temp=0;
		for(int i=0;i<=Pass_Month;i++) {
			System.out.print(temp1.getTime().getMonth()+1+"月");

			temp2.setTime( temp1.getTime());
			// 到中止月份時套用到中止日期 
			// 其餘一個月往上推加 
			if(temp1.getTime().getMonth()==End_Time.getTime().getMonth() && temp1.getTime().getYear()==End_Time.getTime().getYear() ) {
				temp1.setTime(End_Time.getTime());
			}
			else {
			temp1.add(Calendar.MONTH,1);
			}
			date=sdf.format( temp1.getTime());
			date2=sdf.format(temp2.getTime());	
			a[i]=FishData.SelectAVGData(temp,this.fish_id,date2,date);
			
			
			
		}
    }

}  

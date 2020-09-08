package timer;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Timerr {
	public enum speedUpTime {DAY,MONTH,YEAR,WEEK,HOUR};
	private Date lastEndTime;	//上次關閉時間
	private Calendar aquariumTime;	//此魚缸經過的總時間
	
	public Timerr() 		//魚缸第一次被創建
	{
		aquariumTime=Calendar.getInstance();
		aquariumTime.set(1,0,1,0,0,0);		//1年1月1日0十0分0秒起始
	}
	
	public long speedUp(speedUpTime speed)throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate;
		Date endDate;
		long hours;
		if(speed==speedUpTime.DAY)
		{
			beginDate = sdf.parse("1/1/1");
			endDate = sdf.parse("1/1/2");
			hours=TimeUnit.MILLISECONDS.toHours(endDate.getTime()-beginDate.getTime());
			return hours;
		}
		else if(speed==speedUpTime.WEEK)
		{
			beginDate = sdf.parse("1/1/1");
			endDate = sdf.parse("1/1/8");
			hours=TimeUnit.MILLISECONDS.toHours(endDate.getTime()-beginDate.getTime());
			return hours;
		}
		else if(speed==speedUpTime.HOUR)
		{
			return 1;
		}
		else if(speed==speedUpTime.MONTH)
		{
			beginDate = sdf.parse("1/1/1");
			endDate = sdf.parse("1/2/1");
			hours=TimeUnit.MILLISECONDS.toHours(endDate.getTime()-beginDate.getTime());
			return hours;
		}
		else if(speed==speedUpTime.YEAR)
		{
			beginDate = sdf.parse("1/1/1");
			endDate = sdf.parse("2/1/1");
			hours=TimeUnit.MILLISECONDS.toHours(endDate.getTime()-beginDate.getTime());
			return hours;
		}
		hours=0;
		return hours;
	}
	
	public Calendar getTimer()
	{
		return aquariumTime;
	}
	
	public void addOneHour()
	{
		aquariumTime.add(Calendar.HOUR, 1);
	}
	
	public boolean afterASecond()	//過一秒鐘，過每過一小時時回傳TRUE
	{
		Calendar temp;
		long hours;
		temp=Calendar.getInstance();
		temp.setTime(aquariumTime.getTime());
		
		
		aquariumTime.add(Calendar.SECOND, 1);
		
		//System.out.println(aquariumTime.getTime());
		System.out.println(toString());
		hours=aquariumTime.get(Calendar.HOUR)-temp.get(Calendar.HOUR);
		if(hours==1 || hours==-11)	//過一小時
		{
			return true;
		}
		return false;
	}
	
	public Date end()	//結束程式前儲存離開時間
	{
		lastEndTime=new Date();
		System.out.println(lastEndTime);
		return lastEndTime;
	}
	
	public String toStringToDB()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
		String str;
		str=sdf.format(aquariumTime.getTime());
		return str;
	}
	
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String str;
		str=sdf.format(aquariumTime.getTime());
		return str;
	}
	
	
	
	
	static public String[] toDateRange(String a,String b,String c,speedUpTime ff)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate = null;
		Calendar temp;
		try {
			beginDate = sdf.parse(a+"/"+b+"/"+c);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		temp=Calendar.getInstance();
		temp.set(Integer.parseInt(a),Integer.parseInt(b)-1,Integer.parseInt(c));
		if(ff==speedUpTime.DAY)
			temp.add(Calendar.HOUR, 24);
		else if(ff==speedUpTime.WEEK)
			temp.add(Calendar.HOUR, 24*7);
		else if(ff==speedUpTime.MONTH)
			temp.add(Calendar.MONTH, 1);
		else if(ff==speedUpTime.YEAR)
			temp.add(Calendar.YEAR, 1);
		String[] range=new String[2];
		range[0]=sdf.format(beginDate);
		range[1]=sdf.format(temp.getTime());
		
		return range;
		
	}
	
	static public String[] toPastDateRange(String a,String b,String c,speedUpTime ff)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate = null;
		Calendar temp;
		try {
			beginDate = sdf.parse(a+"/"+b+"/"+c);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		temp=Calendar.getInstance();
		temp.set(Integer.parseInt(a),Integer.parseInt(b)-1,Integer.parseInt(c));
		if(ff==speedUpTime.DAY)
			temp.add(Calendar.HOUR, -24);
		else if(ff==speedUpTime.WEEK)
			temp.add(Calendar.HOUR, -24*6);
		else if(ff==speedUpTime.MONTH)
			temp.add(Calendar.MONTH, -1);
		else if(ff==speedUpTime.YEAR)
			temp.add(Calendar.YEAR, -1);
		String[] range=new String[2];
		range[0]=sdf.format(temp.getTime());
		range[1]=sdf.format(beginDate);
		
		return range;
		
	}
	
	
	
	
	public void stringToTime(String timeString)
	{
		System.out.print(timeString);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.aquariumTime.setTime(date);
	}
}

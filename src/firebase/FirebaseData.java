package firebase;

public class FirebaseData {

	private String dateAndTime;
	private String cost;
	private String feederbuyer;
	private String filterbuyer;
	private String flashLightbuyer;
	private String heaterbuyer;
	private String inflatorbuyer;
	private String feed;
	private String enviroment;
	private String feedXY;
	private String stoolXY;
	private String fishTankXYZSize;
	private String fish_nums;
	private String landscape_nums;
	private String landscape_pos;
	
	private String fishName;
	private String fishInfo;
	private String nowPosition;
	private String goalPosition;
	private String feedArray;
    
    public FirebaseData(String input, String inputName)
    {
       if(inputName.equals("time"))
    	   this.dateAndTime = input;
       else if(inputName.equals("cost"))
    	   this.cost = input;
       else if(inputName.equals("feederbuyer"))
    	   this.feederbuyer = input;
       else if(inputName.equals("filterbuyer"))
    	   this.filterbuyer = input;
       else if(inputName.equals("flashLightbuyer"))
    	   this.flashLightbuyer = input;
       else if(inputName.equals("heaterbuyer"))
    	   this.heaterbuyer = input;
       else if(inputName.equals("inflatorbuyer"))
    	   this.inflatorbuyer = input;
       else if(inputName.equals("feed"))
    	   this.feed = input;
       else if(inputName.equals("enviroment"))
    	   this.enviroment = input;
       else if(inputName.equals("getFeedXY"))
    	   this.feedXY = input;
       else if(inputName.equals("getStoolXY"))
    	   this.stoolXY = input;
       else if(inputName.equals("FishTankXYZSize"))
    	   this.fishTankXYZSize = input;
       else if(inputName.equals("fish_nums"))
    	   this.fish_nums = input;
       else if(inputName.equals("landscape_nums"))
    	   this.landscape_nums = input;
       else
    	   this.landscape_pos = input;
    }
    
    public FirebaseData(String fishName, String fishInfo, String nowPosition, String goalPosition, String feedArray)
    {
    	this.fishName = fishName;
    	this.fishInfo = fishInfo;
    	this.nowPosition = nowPosition;
    	this.goalPosition = goalPosition;
    	this.feedArray = feedArray;
    }
    
    public String getDateAndTime()
    {
        return dateAndTime;
    }
    
    public void setDateAneTime(String dateAndTime)
    {
        this.dateAndTime = dateAndTime;
    }
    
    public String getCost()
    {
    	return cost;
    }
    
    public void setCost(String cost)
    {
    	this.cost = cost;
    }
    
    public String getFeederbuyer()
    {
    	return feederbuyer;
    }
    
    public void setFeederbuyer(String feederbuyer)
    {
    	this.feederbuyer = feederbuyer;
    }
    
    public String getFilterbuyer()
    {
    	return filterbuyer;
    }
    
    public void setFilterbuyer(String filterbuyer)
    {
    	this.filterbuyer = filterbuyer;
    }
    
    public String getFlashLightbuyer()
    {
    	return flashLightbuyer;
    }
    
    public void setFlashLightbuyer(String flashLightbuyer)
    {
    	this.flashLightbuyer = flashLightbuyer;
    }
    
    public String getHeaterbuyer()
    {
    	return heaterbuyer;
    }
    
    public void setHeaterbuyer(String heaterbuyer)
    {
    	this.heaterbuyer = heaterbuyer;
    }
    
    public String getInflatorbuyer()
    {
    	return inflatorbuyer;
    }
    
    public void setInflatorbuyer(String inflatorbuyer)
    {
    	this.inflatorbuyer = inflatorbuyer;
    }
    
    public String getFeed()
    {
    	return feed;
    }
    
    public void setFeed(String feed)
    {
    	this.feed = feed;
    }
    
    public String getEnviroment()
    {
    	return enviroment;
    }
    
    public void setEnviroment(String enviroment)
    {
    	this.enviroment = enviroment;
    }
    
    public String getFeedXY()
    {
    	return feedXY;
    }
    
    public void setFeedXY(String FeedXY)
    {
    	this.feedXY = FeedXY;
    }
    
    public String getStoolXY()
    {
    	return stoolXY;
    }
    
    public void setStoolXY(String StoolXY)
    {
    	this.stoolXY = StoolXY;
    }
    
    public String getFishTankXYZSize()
    {
    	return fishTankXYZSize;
    }
    
    public void setFishTankXYZSize(String fishTankXYZSize)
    {
    	this.fishTankXYZSize = fishTankXYZSize;
    }
    
    public String getFish_nums()
    {
    	return fish_nums;
    }
    
    public void setFish_nums(String fish_nums)
    {
    	this.fish_nums = fish_nums;
    }
    
    public String getFishName()
    {
    	return fishName;
    }
    
    public void setFishName(String fishName)
    {
    	this.fishName = fishName;
    }
    
    public String getFishInfo()
    {
    	return fishInfo;
    }
    
    public void setFishInfo(String fishInfo)
    {
    	this.fishInfo = fishInfo;
    }
    
    public String getNowPosition()
    {
    	return nowPosition;
    }
    
    public void setNowPosition(String nowPosition)
    {
    	this.nowPosition = nowPosition;
    }
    
    public String getGoalPosition()
    {
    	return goalPosition;
    }
    
    public void setGoalPosition(String goalPosition)
    {
    	this.goalPosition = goalPosition;
    }
    
    public String getFeedArray()
    {
    	return feedArray;
    }
    
    public void setFeedArray(String feedArray)
    {
    	this.feedArray = feedArray;
    }
    
    public String getLandscape_nums()
    {
    	return landscape_nums;
    }
    
    public void setLandscape_nums(String landscape_nums)
    {
    	this.landscape_nums = landscape_nums;
    }
    
    public String getLandscape_pos()
    {
    	return landscape_pos;
    }
    
    public void setLandscape_pos(String landscape_pos)
    {
    	this.landscape_pos = landscape_pos;
    }
    
    @Override
    public String toString()
    {
        return "User{" + "time=" + dateAndTime + ", cost=" + cost + ", feederbuyer=" + feederbuyer + ", filterbuyer=" + filterbuyer
        	           + "flashLightbuyer=" + flashLightbuyer + ", heaterbuyer=" + heaterbuyer + ", inflatorbuyer=" + inflatorbuyer
        	           + "feed=" + feed + ", enviroment=" + enviroment + ", getFeedXY=" + feedXY + ", getStoolXY=" + stoolXY +'}';
    }
}

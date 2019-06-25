package firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import aquarium.Aquarium;
import enviroment.Enviroment.Water;
import fish.Fish;
import fish.FishCataLog;
import fish.Fish.FishHealthly;
import fish.Fish.FishMove;
import fish.Fish.FishStatus;
import loading.Loading;
import userName.UserName;
import aquarium.Aquarium;
import fish.Fish;
import fish.FishCataLog;

public class FirebaseDownload {
	
	private Aquarium aquarium;
	
	private String time;
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
	private ArrayList<FirebaseData> fishs = new ArrayList<FirebaseData>();

	
	public FirebaseDownload() throws IOException
	{
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("src//afss-17907-firebase-adminsdk-s3ka8-90b8963c16.json");
		} catch (FileNotFoundException e1) {
			// TODO 自動產生的 catch 區塊
			System.out.println("Firebase download fail!");
		}

		FirebaseOptions options = null;
		try {
			options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .setDatabaseUrl("https://afss-17907.firebaseio.com")
			  .build();
		} catch (IOException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}

		
		if(FirebaseApp.getApps().isEmpty())
			FirebaseApp.initializeApp(options);
		
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("server/saving-data/fireblog");
		DatabaseReference usersRef = ref.child(UserName.userName);
		//System.out.println(aquarium.getUserName());
		
		usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
		    public void onDataChange(DataSnapshot dataSnapshot) {
		        for (DataSnapshot d : dataSnapshot.getChildren()) {
			        	
		        	String[] enviro_value = null;
			        if(d.getKey().equals("time"))
		        		time = d.child("dateAndTime").getValue().toString();
		            else if(d.getKey().equals("cost"))
		            	cost = d.child("cost").getValue().toString();
		            else if(d.getKey().equals("feederbuyer"))
		            	feederbuyer = d.child("feederbuyer").getValue().toString();
		            else if(d.getKey().equals("filterbuyer"))
		            	filterbuyer = d.child("filterbuyer").getValue().toString();
		            else if(d.getKey().equals("flashLightbuyer"))
		            	flashLightbuyer = d.child("flashLightbuyer").getValue().toString();
		            else if(d.getKey().equals("heaterbuyer"))
		            	heaterbuyer = d.child("heaterbuyer").getValue().toString();
		            else if(d.getKey().equals("inflatorbuyer"))
		            	inflatorbuyer = d.child("inflatorbuyer").getValue().toString();
		            else if(d.getKey().equals("feed"))
		            	feed = d.child("feed").getValue().toString();
		            else if(d.getKey().equals("enviroment"))
		            	enviroment = d.child("enviroment").getValue().toString();
		            else if(d.getKey().equals("getFeedXY"))
		            	feedXY = d.child("feedXY").getValue().toString();
		            else if(d.getKey().equals("getStoolXY"))
		            	stoolXY = d.child("stoolXY").getValue().toString();
		            else if(d.getKey().equals("FishTankXYZSize"))
		            	fishTankXYZSize = d.child("fishTankXYZSize").getValue().toString();
		            else if(d.getKey().equals("fish_nums"))
		            	fish_nums = d.child("fish_nums").getValue().toString();
		            else if(d.getKey().equals("landscape_nums"))
		            	landscape_nums = d.child("landscape_nums").getValue().toString();
		            else if(d.getKey().equals("landscape_pos"))
		            	landscape_pos = d.child("landscape_pos").getValue().toString();
		            else
		            {
		            	fishs.add(new FirebaseData(d.child("fishName").getValue().toString(), d.child("fishInfo").getValue().toString(), d.child("nowPosition").getValue().toString(), d.child("goalPosition").getValue().toString(), d.child("feedArray").getValue().toString()));
		            }
		      }
		}
			    @Override
			    public void onCancelled(DatabaseError databaseError) {
			    }

			});
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Aquarium DownloadData()
	{
		aquarium = new Aquarium();
		
		// set time
		aquarium.getTimer().stringToTime(time);
	 	 
		// set cost
		aquarium.getCost().setTotalCost(Integer.parseInt(cost));
		
		//set enviroment
		String[] enviro_value=enviroment.split(",");
		if(enviro_value[0].equals("OCEAN"))
			aquarium.getEnviroment().setWater(Water.OCEAN );
		else 
			aquarium.getEnviroment().setWater(Water.FRESHWATER );
		 
		aquarium.getEnviroment().setFishTankSize(Integer.parseInt(enviro_value[1]));
	
		aquarium.getEnviroment().setWaterQuality(Double.parseDouble(enviro_value[2]));
		aquarium.getEnviroment().setWaterTemperature(Double.parseDouble(enviro_value[3]));
		aquarium.getEnviroment().setStool(Integer.parseInt(enviro_value[4]));
		aquarium.getEnviroment().setOxygen(Double.parseDouble(enviro_value[5]));
		
		// set feedXY
		String[] getFeedXY_value;
		ArrayList<int[]> feedx=new ArrayList<int[]>();
		getFeedXY_value=feedXY.split(",");
		int [] feedxy_value;
		if(feedXY.length()!=0) {
			 feedxy_value=new int [15];	
			for(int i=0;i<Integer.parseInt(enviro_value[4])*3;i++) {
				feedxy_value[i]=Integer.parseInt( getFeedXY_value[i]);
				
			}
			 feedx.add(feedxy_value);
		}
		aquarium.getEnviroment().setFeedXY(feedx);
		
		// set stoolXY
		String[] getstoolXY_value;
		int [] stoolxy_value=new int [Integer.parseInt(enviro_value[4])*3];
		getstoolXY_value=stoolXY.split(",");
		 
		ArrayList<int[]> stoolx=new ArrayList<int[]>();
		if(stoolXY.length()!=0) {
			for(int i=0;i<Integer.parseInt(enviro_value[4])*3;i++) {
				 stoolxy_value[i]=Integer.parseInt( getstoolXY_value[i]);
				
			}
		}
		stoolx.add(stoolxy_value);
		aquarium.getEnviroment().setStoolXY(stoolx);
		
		// set the device
		aquarium.getDevice().loadFeeder(feederbuyer);
		aquarium.getDevice().loadFilter(filterbuyer);;
		aquarium.getDevice().loadflashLight(flashLightbuyer);;
		aquarium.getDevice().loadheater(heaterbuyer);
		aquarium.getDevice().loadInflator(inflatorbuyer);        
		String []value_feed=feederbuyer.split(",");
		String []value_filter=filterbuyer.split(",");
		String []value_inflat=inflatorbuyer.split(",");
		String []value_flash=flashLightbuyer.split(",");
		String []value_heat=heaterbuyer.split(",");
		aquarium.getDevice().loadtoSelectorData(Integer.parseInt(value_feed[0]),Integer.parseInt(value_filter[0]), Integer.parseInt(value_inflat[0]), Integer.parseInt(value_flash[0]), Integer.parseInt(value_heat[0]));
		 
		
		int count = 0;
		
		for(FirebaseData a: fishs) {
			
			aquarium.addAFish(a.getFishName());
			 
			 String value[] = a.getFishInfo().split(",");
			 System.out.print(value[0]);
		
			 aquarium.getFishs()[count].setFishNO((value[0]));
			 aquarium.getFishs()[count].setLifeTime(Integer.parseInt(value[1]));
			 aquarium.getFishs()[count].setWeight(Integer.parseInt(value[2]));
			 aquarium.getFishs()[count].setLively(Integer.parseInt(value[3]));
			 aquarium.getFishs()[count].setSick(Integer.parseInt(value[4]));
			 aquarium.getFishs()[count].setSatiation(Integer.parseInt(value[5]));
			 aquarium.getFishs()[count].setHurt(Integer.parseInt(value[6]));
			 aquarium.getFishs()[count].setDeath(Integer.parseInt(value[7]));
			 aquarium.getFishs()[count].setFight(Integer.parseInt(value[8]));
			 aquarium.getFishs()[count].setFamiliarity(Integer.parseInt(value[9]));
			 aquarium.getFishs()[count].setMaxSatiation(Integer.parseInt(value[10]));
			 aquarium.getFishs()[count].setSnatch(Integer.parseInt(value[11]));
			 aquarium.getFishs()[count].setFamiliarityAddToken(Integer.parseInt(value[12]));
			 aquarium.getFishs()[count].setWeightAddToken(Integer.parseInt(value[13]));
			 aquarium.getFishs()[count].setNoFight(Integer.parseInt(value[14]));
		    /////////////////////////////////////////////////////////////	 

			 if(value[15].equals("null")) {
				 aquarium.getFishs()[count].setFightTarget(null);
			 }
			 else {
				  int temp_val=0;
				for(int q=0;q<Integer.parseInt(fish_nums);q++)
				{
					if(value[15].equals(aquarium.getFishs()[count].getFishNO())) {
					System.out.print(aquarium.getFishs()[count].getFishNO());
						temp_val=q;}
					}
			 aquarium.getFishs()[count].setFightTarget(aquarium.getFishs()[temp_val]);	 
		    }
			 ////////////////////////////////////////////////////////////
			 ///////////////////////////////////////////////////////////
			 ///////////////////////////////////////////////////////////
			 Boolean max = false;
			 if(value.length >16) {
				 if(value[16].equals("true"))
					 max=true;
			 }
			 else {
				 max=false;
			 }
			 aquarium.getFishs()[count].setAlreadyMaxWeight(max);
		
			 
			 switch(value[17]) {
			 case "DEATH":     aquarium.getFishs()[count].setFishStatus(FishStatus.DEATH);  break;
			 case "DYING":     aquarium.getFishs()[count].setFishStatus(FishStatus.DYING); break;
			 case  "ALIFE":    aquarium.getFishs()[count].setFishStatus(FishStatus.ALIFE);  break;
		
			 }
			 
			 
			 switch(value[18]) {
			 case "HEALTH":    aquarium.getFishs()[count].setFishHealthly(FishHealthly.HEALTH); break;
			 case "SICKNESS":  aquarium.getFishs()[count].setFishHealthly(FishHealthly.SICKNESS); break;
			 case  "HURT":     aquarium.getFishs()[count].setFishHealthly(FishHealthly.HURT); break;
			 case "BOTH":       aquarium.getFishs()[count].setFishHealthly(FishHealthly.BOTH); break;
			 }
        
			 switch(value[19]) {
			 case "NATURAL":   aquarium.getFishs()[count].setMyMove(FishMove.NATURAL); break;
			 case "FIGHTING":  aquarium.getFishs()[count].setMyMove(FishMove.FIGHTING);  break;
			 case  "EATING":   aquarium.getFishs()[count].setMyMove(FishMove.EATING);  break;
			
			 }
			 int []temp_pos_now=new int [3];
			 String value_pos_now[]=a.getNowPosition().split(",");
			 for(int q=0;q<3;q++)
			 temp_pos_now[q]= Integer.parseInt(value_pos_now[q]);
			 aquarium.getFishs()[count].setNowPosition(temp_pos_now);
			 
			 int []temp_pos_goal=new int [3];
			 String[] value_pos_goal=a.getGoalPosition().split(",");
			 if(a.getGoalPosition().length()!=0) {
			 for(int q=0;q<3;q++)
			    temp_pos_goal[q]= Integer.parseInt(value_pos_goal[q]);
			 }
			 aquarium.getFishs()[count].setGoalPosition(temp_pos_goal);
			 
			 ArrayList<int[]> feedArray=new ArrayList<int[]>();
			 String[] value_feedArray=a.getFeedArray().split(",");
			 int [] feedxy_val=new int [10];
			 int [] feed_2;
			  if(feedXY.length()!=0) {
				 for(int q=0;q<value_feedArray.length;q++) {
					 //feedxy_val[count]=Integer.parseInt(value_feedArray[count]);
					
				 }
				 //feedArray.add(feedxy_val);
			 //aquarium.getFishs()[count].setFeedArray(feedArray);

			 count++;
			 
		 }
		}
		
		String[] temp=enviroment.split(",");
        aquarium.getLandSpace().loadtoTableData(landscape_pos, Integer.parseInt(temp[1])+1);;
        aquarium.getLandSpace().loadtoQuantityData(landscape_nums);
        System.out.println("landscape_pos:\n"+aquarium.getLandSpace().savetoTableData());
        System.out.println("landscape_nums:\n"+aquarium.getLandSpace().savetoQuantityData());
		
		return aquarium;
	}
}

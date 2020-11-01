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
import fish.Fish;
import fish.FishCataLog;
import loading.Loading;
import userName.UserName;

public class FirebaseUpload {
	
	public FirebaseUpload(Aquarium aquarium, String inputName) throws IOException
	{
		String userName = UserName.userName;
		if(!inputName.equals(""))
			userName = inputName;
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("src//-file.json-");
		} catch (FileNotFoundException e1) {
			// TODO 自動產生的 catch 區塊
			e1.printStackTrace();
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
				ref.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot dataSnapshot) {
						Object document = dataSnapshot.getValue();
						System.out.println(document);
					}

					@Override
					public void onCancelled(DatabaseError error) {
					}
				});

				DatabaseReference usersRef = ref.child(userName);
		
		Map<String, FirebaseData> user = new HashMap<>();
		user.put("time", new FirebaseData(aquarium.getTimer().toString(), "time"));
		user.put("cost", new FirebaseData(aquarium.getCost().toString(), "cost"));
		user.put("feederbuyer", new FirebaseData(aquarium.getDevice().uploadFeederData(), "feederbuyer"));
		user.put("filterbuyer", new FirebaseData(aquarium.getDevice().uploadFilterData(), "filterbuyer"));
		user.put("flashLightbuyer", new FirebaseData(aquarium.getDevice().uploadFlashLightData(), "flashLightbuyer"));
		user.put("heaterbuyer", new FirebaseData(aquarium.getDevice().uploadHeaterData(), "heaterbuyer"));
		user.put("inflatorbuyer", new FirebaseData(aquarium.getDevice().uploadInflatorData(), "inflatorbuyer"));
		user.put("feed", new FirebaseData(aquarium.getDevice().uploadtoSelectorData(), "feed"));
		user.put("enviroment", new FirebaseData(aquarium.getEnviroment().getWater()+","+aquarium.getEnviroment().toSaveString(), "enviroment"));
		
		String uploadTemp = "";
		ArrayList<int[]> temp_feed=aquarium.getEnviroment().getFeedXY();
		for(int XYZ[]: temp_feed) {
			for(int i:XYZ) {
			System.out.print(i+",");
			uploadTemp += (i+",");
			}
		}
		user.put("getFeedXY", new FirebaseData(uploadTemp, "getFeedXY"));
		
		uploadTemp = "";
		ArrayList<int[]> temp_stool=aquarium.getEnviroment().getStoolXY();
		for(int XYZ[]: temp_stool) {
			for(int i:XYZ) {
			uploadTemp += (i+",");
			}
		}
		user.put("getStoolXY", new FirebaseData(uploadTemp, "getStoolXY"));
		
		user.put("FishTankXYZSize", new FirebaseData(aquarium.getEnviroment().getFishTankXYZSize()[0]+","+aquarium.getEnviroment().getFishTankXYZSize()[1]+","+aquarium.getEnviroment().getFishTankXYZSize()[2], "FishTankXYZSize"));
		user.put("fish_nums", new FirebaseData(aquarium.getnFishs() + "", "fish_nums"));
		
		int temp_fish_nums=0;
		for(Fish i:aquarium.getFishs())
			if(i!=null)
				temp_fish_nums++;
		
		// 存所有魚的數量及資料
		int[] temp_move=new int [100];
		int[] goal_move=new int [100];
		ArrayList<int[]> feed;
		for(int i=0;i<temp_fish_nums;i++) {
			
			String inputFishInfo = "";
			inputFishInfo = aquarium.getFishs()[i].toSaveString()+","+aquarium.getFishs()[i].getFamiliarity()
					  + ","+aquarium.getFishs()[i].getMaxSatiation()+","+aquarium.getFishs()[i].getSnatch()
					  + ","+aquarium.getFishs()[i].getFamiliarityAddToken()
					  + ","+aquarium.getFishs()[i].getWeightAddToken()
					  + ","+aquarium.getFishs()[i].getNoFight()
					  + ",null"
					  + ","+aquarium.getFishs()[i].getAlreadyMaxWeight()
					  + ","+aquarium.getFishs()[i].getFishStatus()
					  + ","+aquarium.getFishs()[i].getFishHealthly()
					  + ","+aquarium.getFishs()[i].getMyMove();
			
			temp_move= aquarium.getFishs()[i].getNowPosition();
			goal_move= aquarium.getFishs()[i].getGoalPosition();
			feed= aquarium.getFishs()[i].getFeedArray();
			
			String inputXYZ = "";
			for(int xyz: temp_move) {
				inputXYZ += (xyz+",");
			}
			
			String inputGoalXYZ = "";
			if(goal_move!=null)
				for(int xyz: goal_move)
					inputGoalXYZ += (xyz+",");
			
			String inputFeedArray = "";
			if(feed!=null)
				 for(int xyz[]: feed)
				 	for(int q:xyz)
				 		inputFeedArray += (q+",");
			
			user.put("Fish," + (i+1), new FirebaseData(FishCataLog.getFishChineseName(aquarium.getFishs()[i]), inputFishInfo, inputXYZ, inputGoalXYZ, inputFeedArray));
		}
		
		user.put("landscape_nums", new FirebaseData(aquarium.getLandSpace().savetoQuantityData(), "landscape_nums"));
		user.put("landscape_pos", new FirebaseData(aquarium.getLandSpace().savetoTableData(), "landscape_pos"));

		usersRef.setValueAsync(user);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

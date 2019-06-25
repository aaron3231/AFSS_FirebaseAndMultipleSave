package ioSave;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import cost.Cost;
import aquarium.Aquarium;
import enviroment.Enviroment.Water;
import fish.Fish.FishHealthly;
import fish.Fish.FishMove;
import fish.Fish.FishStatus;
import userName.UserName;

import java.io.FileReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

public class Load {
	private Aquarium aquarium;
	public Load()
	{

	}
	public  Aquarium LoadData() throws IOException {
		
		aquarium=new Aquarium();
		
		// FileReader fr = new FileReader("test.txt");
		// BufferedReader br = new BufferedReader(fr);
		 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(UserName.userName + ".txt"), "MS950"));
		 
		 String []compare = {"time:","cost:","feederbuyer:","filterbuyer:","flashLightbuyer:","heaterbuyer:","inflatorbuyer:","feed:",
				              "enviroment:","getFeedXY:","getStoolXY:","FishTankXYZSize:","fish_nums:","Fish_Name:","Fish_info:","getNowPosition:","getGoalPosition:","getFeedArray:",
				              "landscape_nums:","landscape_pos:"
		                       };

		 String  str="";	 
		 String  time="";
		 String  cost="";
		 String  feedbuyer="";
		 String  filterbuyer="";
		 String  heaterbuyer="";
		 String  flashLightbuyer="";
		 String  FeedXY="";
		 String  inflatorbuyer="";
		 String  xxxx="";
		 String  enviroment="";
		 String  StoolXY="";
		 String  FishTankXYZSize="";
		 String  fish_nums="";
		 String  landscape_nums="";
		 String  landscape_pos="";
		 String [][]fish_info= new String[30][6];;
		 while (br.ready()) {
		             str=br.readLine();
		             for(String i:compare) {
		             if(str.indexOf(i)!=-1) {
		           // 	 System.out.println(str);
		            	 switch(i){
		            	 case "time:":{   
		            		              time=str;
		            		              time=time.substring(i.length(),time.length());
		            		              System.out.println(time);
		            		              break;
		            	               }
		            	 case "cost:":{  
		            		              cost=str;
   		                                  cost=cost.substring(i.length(),cost.length());
   		                                  System.out.println(cost);
   	                                      break;
		            	               }
		            	 case "feederbuyer:":{  
		            		              feedbuyer=str;
                                          feedbuyer=feedbuyer.substring(i.length(),feedbuyer.length());
                                          System.out.println(feedbuyer);
                                          break;
		            	                  }
		            	 case "filterbuyer:":{  
       		                              filterbuyer=str;
       		                              filterbuyer=filterbuyer.substring(i.length(),filterbuyer.length());
                                          System.out.println(filterbuyer);
                                          break;
       	                                  }
		            	 case "flashLightbuyer:":{  
	                                     flashLightbuyer=str;
	                                     flashLightbuyer=flashLightbuyer.substring(i.length(),flashLightbuyer.length());
                                         System.out.println(flashLightbuyer);
                                         break;
                                }
		            	 case "heaterbuyer:":{  
		            		              heaterbuyer=str;
		            		              heaterbuyer=heaterbuyer.substring(i.length(),heaterbuyer.length());
                                          System.out.println(heaterbuyer);
                                          break;
       	                                  }
		            	 case "inflatorbuyer:":{  
       		                             inflatorbuyer=str;
		            	                 inflatorbuyer=inflatorbuyer.substring(i.length(),inflatorbuyer.length());
                                         System.out.println(inflatorbuyer);
                                         break;
       	                                 }
		            	 case "feed:":{
        		                        xxxx=str;
        		                        xxxx=xxxx.substring(i.length(),xxxx.length());
                                        System.out.println(xxxx);
                                        break;             		 
        	                         }
		            	 case "enviroment:":{
		            		             enviroment=str;
		            		             enviroment=enviroment.substring(i.length(),enviroment.length());
                                         System.out.println(enviroment);
                                         break;             		 
		            	              }
		            	 case "getFeedXY:":{
		            		             FeedXY=str;
		            		              FeedXY=FeedXY.substring(i.length(),FeedXY.length());
                                         System.out.println(FeedXY);
                                         break;             		 
                                      }
		            	 case "getStoolXY:":{
        		                         StoolXY=str;
        		                         StoolXY=StoolXY.substring(i.length(),StoolXY.length());
                                         System.out.println(StoolXY);
                                         break;             		 
        	                            }
		            	
		            	 case "FishTankXYZSize:":{
        		                        FishTankXYZSize=str;
        		                        FishTankXYZSize=FishTankXYZSize.substring(i.length(),FishTankXYZSize.length());
                                        System.out.println(FishTankXYZSize);
                                        break;             		 
        	                            }
		            	 case "fish_nums:":{
        		                       fish_nums=str;
        		                       fish_nums=fish_nums.substring(i.length(),fish_nums.length());
                                       System.out.println(fish_nums);
                                      
                                       if(Integer.parseInt(fish_nums)>=1) {
                                    	   int temp=0;
                                    	   int fish_parse=0;
                                    	  for(fish_parse=0;fish_parse<5*Integer.parseInt(fish_nums);fish_parse++) {
                                    		  temp=fish_parse/5;
                                    		  str=br.readLine(); 
                                    		//  System.out.println(str);
                                    		  if(str.indexOf("Fish_Name:")!=-1) {
         
                                           	   str=str.substring("Fish_Name:".length(),str.length());
                                                //  System.out.println(str);
                                           	   fish_info[temp][fish_parse%5]=str;
                            
                                              }
                                    		  
                                    		  else if(str.indexOf("Fish_info:")!=-1) {
                                        	   	
                                        	   str=str.substring("Fish_info:".length(),str.length());
                                             //  System.out.println(str);
                                        	   fish_info[temp][fish_parse%5]=str;
                                        	 
                                           }
                                              else  if(str.indexOf("getNowPosition:")!=-1) {
                                        	   str=str.substring("getNowPosition:".length(),str.length());
                                        	   fish_info[temp][fish_parse%5]=str;
                                           } 
                                              else if(str.indexOf("getGoalPosition:")!=-1) {
                                        	   str=str.substring("getGoalPosition:".length(),str.length());
                                        	   fish_info[temp][fish_parse%5]=str;
                                           }
                                              else if(str.indexOf("getFeedArray:")!=-1) {
                                            
                                        	   str=str.substring("getFeedArray:".length(),str.length());
                                        	   fish_info[temp][fish_parse%5]=str;
                                           }                                     	  
                                    	  }       	  
                                       }
                                       break;             		 
        	                          }
		        
		            	 case "landscape_nums:":{
        		             landscape_nums=str;
        		             landscape_nums=landscape_nums.substring(i.length(),landscape_nums.length());
                             System.out.println(landscape_nums);
                             break;             		 
        	              }
		            	 case "landscape_pos:":{
        		             landscape_pos=str;
        		             landscape_pos=landscape_pos.substring(i.length(),landscape_pos.length());
                             System.out.println(landscape_pos);
                             break;             		 
        	              }
       	             }
		             }
		             
		             }
		          
		         }
		         for(int i=0;i<Integer.parseInt(fish_nums);i++) {
				        for(int j=0;j<5;j++)
							System.out.println(fish_info[i][j]);
							
					}
		  
		         
		 br.close();
		 /////////////////////////////   all file fetch from the txt finished        
		 String []temp= {""};
		 System.out.print("after set: \n\n\n\n");
		 
		 
		 // set time
		 aquarium.getTimer().stringToTime(time);
		 	 
		 // set cost
	     aquarium.getCost().setTotalCost(Integer.parseInt(cost));    
		 System.out.println("Cost:"+aquarium.getCost());
		 
		 
		 
		 // set enviroment
		 
		 // ������  fishTankSize waterQuality waterTemperature stool oxygen
		 
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
		 
		 String[] getFeedXY_value;
		 ArrayList<int[]> feedx=new ArrayList<int[]>();
		 getFeedXY_value=FeedXY.split(",");
		 int [] feedxy_value;
		 if(FeedXY.length()!=0) {
			 feedxy_value=new int [15];	
			 for(int i=0;i<Integer.parseInt(enviro_value[4])*3;i++) {
				 //feedxy_value[i]=Integer.parseInt( getFeedXY_value[i]);
				
			 }
			 feedx.add(feedxy_value);
		 }
		
		 aquarium.getEnviroment().setFeedXY(feedx);
		 
		 String[] getstoolXY_value;
		 int [] stoolxy_value=new int [Integer.parseInt(enviro_value[4])*3];
		 getstoolXY_value=StoolXY.split(",");
		 
		 ArrayList<int[]> stoolx=new ArrayList<int[]>();
		 if(StoolXY.length()!=0) {
			 for(int i=0;i<Integer.parseInt(enviro_value[4])*3;i++) {
				 stoolxy_value[i]=Integer.parseInt( getstoolXY_value[i]);
				
			 }
		 }
		 stoolx.add(stoolxy_value);
		 aquarium.getEnviroment().setStoolXY(stoolx);
		
		 
		 
		 // set the device
		 aquarium.getDevice().loadFeeder(feedbuyer);
		 aquarium.getDevice().loadFilter(filterbuyer);;
		 aquarium.getDevice().loadflashLight(flashLightbuyer);;
		 aquarium.getDevice().loadheater(heaterbuyer);
		 aquarium.getDevice().loadInflator(inflatorbuyer);        
		 String []value_feed=feedbuyer.split(",");
		 String []value_filter=filterbuyer.split(",");
		 String []value_inflat=inflatorbuyer.split(",");
		 String []value_flash=flashLightbuyer.split(",");
		 String []value_heat=heaterbuyer.split(",");
		 
		 aquarium.getDevice().loadtoSelectorData(Integer.parseInt(value_feed[0]),Integer.parseInt(value_filter[0]), Integer.parseInt(value_inflat[0]), Integer.parseInt(value_flash[0]), Integer.parseInt(value_heat[0]));
		         
		 System.out.println("feeder:"+aquarium.getDevice().saveFeederData()); 
		 System.out.println("filter:"+aquarium.getDevice().saveFilterData());
		 System.out.println("flashlight:"+aquarium.getDevice().saveFlashLightData());
		 System.out.println("heater:"+aquarium.getDevice().saveHeaterData());
		 System.out.println("inflator:"+aquarium.getDevice().saveInflatorData());
		 
		 
		 
		 
		 // set fish
		 
		 for(int i=0;i<Integer.parseInt(fish_nums);i++) {
			 System.out.println(fish_info[i][0]);
			 aquarium.addAFish(fish_info[i][0]);
		//	 System.out.print("\n****"+aquarium.getFishs()[i]+"*****\n");
		 }
	//	 System.out.print(aquarium.getFishs()[0]);
		 
		 // ID lifeTime weight lively sick stiation hurt death fight  familiarity 
		 // �̤j������  snatch getFamiliarityAddToken()  getWeightAddToken() noFight
		 //  FishTarget  alreadyMaxweight myStatus  myHealthly  myMove
		 //  getNowPosition()	  getGoalPosition()	 getFeedArray()		
		 for(int i=0;i<Integer.parseInt(fish_nums);i++) {
			 String value[]=fish_info[i][1].split(",");
			 System.out.print(value[0]);
		
			 aquarium.getFishs()[i].setFishNO((value[0]));
			 aquarium.getFishs()[i].setLifeTime(Integer.parseInt(value[1]));
			 aquarium.getFishs()[i].setWeight(Integer.parseInt(value[2]));
			 aquarium.getFishs()[i].setLively(Integer.parseInt(value[3]));
			 aquarium.getFishs()[i].setSick(Integer.parseInt(value[4]));
			 aquarium.getFishs()[i].setSatiation(Integer.parseInt(value[5]));
			 aquarium.getFishs()[i].setHurt(Integer.parseInt(value[6]));
			 aquarium.getFishs()[i].setDeath(Integer.parseInt(value[7]));
			 aquarium.getFishs()[i].setFight(Integer.parseInt(value[8]));
			 aquarium.getFishs()[i].setFamiliarity(Integer.parseInt(value[9]));
			 aquarium.getFishs()[i].setMaxSatiation(Integer.parseInt(value[10]));
			 aquarium.getFishs()[i].setSnatch(Integer.parseInt(value[11]));
			 aquarium.getFishs()[i].setFamiliarityAddToken(Integer.parseInt(value[12]));
			 aquarium.getFishs()[i].setWeightAddToken(Integer.parseInt(value[13]));
			 aquarium.getFishs()[i].setNoFight(Integer.parseInt(value[14]));
		    /////////////////////////////////////////////////////////////	 
			 
			 
			


			 if(value[15].equals("null")) {
				 aquarium.getFishs()[i].setFightTarget(null);
			 }
			 else {
				  int temp_val=0;
				for(int q=0;q<Integer.parseInt(fish_nums);q++)
				{
					if(value[15].equals(aquarium.getFishs()[i].getFishNO())) {
					System.out.print(aquarium.getFishs()[i].getFishNO());
						temp_val=q;}
					}
			 aquarium.getFishs()[i].setFightTarget(aquarium.getFishs()[temp_val]);	 
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
			 aquarium.getFishs()[i].setAlreadyMaxWeight(max);
		
			 
			 switch(value[17]) {
			 case "DEATH":     aquarium.getFishs()[i].setFishStatus(FishStatus.DEATH);  break;
			 case "DYING":     aquarium.getFishs()[i].setFishStatus(FishStatus.DYING); break;
			 case  "ALIFE":    aquarium.getFishs()[i].setFishStatus(FishStatus.ALIFE);  break;
		
			 }
			 
			 
			 switch(value[18]) {
			 case "HEALTH":    aquarium.getFishs()[i].setFishHealthly(FishHealthly.HEALTH); break;
			 case "SICKNESS":  aquarium.getFishs()[i].setFishHealthly(FishHealthly.SICKNESS); break;
			 case  "HURT":     aquarium.getFishs()[i].setFishHealthly(FishHealthly.HURT); break;
			 case "BOTH":       aquarium.getFishs()[i].setFishHealthly(FishHealthly.BOTH); break;
			 }
         
			 switch(value[19]) {
			 case "NATURAL":   aquarium.getFishs()[i].setMyMove(FishMove.NATURAL); break;
			 case "FIGHTING":  aquarium.getFishs()[i].setMyMove(FishMove.FIGHTING);  break;
			 case  "EATING":   aquarium.getFishs()[i].setMyMove(FishMove.EATING);  break;
			
			 }
			 int []temp_pos_now=new int [3];
			 String value_pos_now[]=fish_info[i][2].split(",");
			 for(int q=0;q<3;q++)
			 temp_pos_now[q]= Integer.parseInt(value_pos_now[q]);
			 aquarium.getFishs()[i].setNowPosition(temp_pos_now);
			 
			 int []temp_pos_goal=new int [3];
			 String[] value_pos_goal=fish_info[i][3].split(",");
			 if(fish_info[i][3].length()!=0) {
			 for(int q=0;q<3;q++)
			    temp_pos_goal[q]= Integer.parseInt(value_pos_goal[q]);
			 }
			 aquarium.getFishs()[i].setGoalPosition(temp_pos_goal);
			 
			 ArrayList<int[]> feedArray=new ArrayList<int[]>();
			 String[] value_feedArray=fish_info[i][4].split(",");
			 int [] feedxy_val=new int [10];
			 int [] feed_2;
			  if(FeedXY.length()!=0) {
				 for(int q=0;q<value_feedArray.length;q++) {
					 //feedxy_val[i]=Integer.parseInt(value_feedArray[i]);
					
				 }
				// feedArray.add(feedxy_val);
			// aquarium.getFishs()[i].setFeedArray(feedArray);

			 }
			 
		 }
		 
		 // set���X����
		 aquarium.setnFishs(Integer.parseInt(fish_nums));
		 
		 
		 
		 //set landscape
		 temp=enviroment.split(",");
         aquarium.getLandSpace().loadtoTableData(landscape_pos, Integer.parseInt(temp[1])+1);;
         aquarium.getLandSpace().loadtoQuantityData(landscape_nums);
         System.out.println("landscape_pos:\n"+aquarium.getLandSpace().savetoTableData());
         System.out.println("landscape_nums:\n"+aquarium.getLandSpace().savetoQuantityData());
		 
		return aquarium;
	}
		
}

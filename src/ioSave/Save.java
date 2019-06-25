package ioSave;
import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;

import javax.swing.JButton;

import aquarium.Aquarium;
import aquarium.AquariumTask;
import aquarium.SpeedTask;
import enterPanel.EnterPanel;
import fish.Fish;
import fish.FishCataLog;
import fishtankPanel.FishtankPanel;
import main.NewDialog;
import multiPanel.DevicePanelChoose;
import multiPanel.DevicePanelModule;
import status.Status;
public class Save {
	private Aquarium aquarium;

	public Save(Aquarium a)
	{
		aquarium = a;
	}
	public  void  SaveData(String a) throws IOException {
		
		FileWriter fw = new FileWriter(a);
		       
		//�s�{�b���ɶ�
		System.out.print("time:"+aquarium.getTimer()+"\n");	
	    fw.write("time:"+aquarium.getTimer());  
	    fw.write("\r\n");
		
	    // �s�᪺��
		System.out.print("cost:"+aquarium.getCost()+"\n");
		fw.write("cost:"+aquarium.getCost());
		fw.write("\r\n");
        
		//�]��:		
		System.out.println(aquarium.getDevice().saveFeederData());
		System.out.println(aquarium.getDevice().saveFilterData());
		System.out.println(aquarium.getDevice().saveFlashLightData());
		System.out.println(aquarium.getDevice().saveHeaterData());
		System.out.println(aquarium.getDevice().saveInflatorData());
		System.out.println(aquarium.getDevice().saveSelectorData());
		
		fw.write(aquarium.getDevice().saveFeederData());  
		fw.write("\r\n");
		fw.write(aquarium.getDevice().saveFilterData());  
		fw.write("\r\n");
		fw.write(aquarium.getDevice().saveFlashLightData());  
		fw.write("\r\n");
		fw.write(aquarium.getDevice().saveHeaterData());  
		fw.write("\r\n");
		fw.write(aquarium.getDevice().saveInflatorData());  
		fw.write("\r\n");
		fw.write(aquarium.getDevice().savetoSelectorData());  
		fw.write("\r\n");
		
		
		// �s�����ܼ�
		// getEnviroment()
		System.out.print("enviroment:"+aquarium.getEnviroment().getWater()+"\n");
		fw.write("enviroment:"+aquarium.getEnviroment().getWater()+","+aquarium.getEnviroment().toSaveString());  
		fw.write("\r\n");
		System.out.print("���Ҷq:"+aquarium.getEnviroment().toSaveString());
		// getFeedXY()
		System.out.print("getFeedXY:");
		fw.write("getFeedXY:");
		ArrayList<int[]> temp_feed=aquarium.getEnviroment().getFeedXY();
		for(int XYZ[]: temp_feed) {
			for(int i:XYZ) {
			System.out.print(i+",");
			fw.write(i+",");
			}
		}
		System.out.print("\n");
		fw.write("\r\n");
		// getStoolXY()
		System.out.print("getStoolXY:");
		fw.write("getStoolXY:");
	    ArrayList<int[]> temp_stool=aquarium.getEnviroment().getStoolXY();
		for(int XYZ[]: temp_stool) {
			for(int i:XYZ) {
			System.out.print(i+",");
			fw.write(i+",");
			}
		}
		System.out.print("\n");
		fw.write("\r\n");
		// getFishTankXYZSize()
		System.out.print("FishTankXYZSize:"+aquarium.getEnviroment().getFishTankXYZSize()[0]+","+aquarium.getEnviroment().getFishTankXYZSize()[1]+","+aquarium.getEnviroment().getFishTankXYZSize()[2]+"\n");
		fw.write("FishTankXYZSize:"+aquarium.getEnviroment().getFishTankXYZSize()[0]+","+aquarium.getEnviroment().getFishTankXYZSize()[1]+","+aquarium.getEnviroment().getFishTankXYZSize()[2]+"\n");
		fw.write("\r\n");
		// �s�Ҧ������ƶq�θ��
		int[] temp_move=new int [100];
		int[] goal_move=new int [100];
		ArrayList<int[]> feed;
		
		System.out.print("���q:"+aquarium.getnFishs()+"\n");
		
		
		int temp_fish_nums=0;
		for(Fish i:aquarium.getFishs()) {
			if(i!=null){
				temp_fish_nums++;
			}
			}
		
		
		///System.out.print("\n*********"+temp_fish_nums+"*************\n");
		
		
		fw.write("fish_nums:"+aquarium.getnFishs());
		fw.write("\r\n");
	//	aquarium.getFishs().length
		for(int i=0;i<temp_fish_nums;i++) {
			temp_move= aquarium.getFishs()[i].getNowPosition();
			goal_move= aquarium.getFishs()[i].getGoalPosition();
			feed= aquarium.getFishs()[i].getFeedArray();
			
			//���W �����s�� ����lifetime weight lively sick stiation hurt death fight familiarity life_end
			System.out.print( aquarium.getFishs()[i].toString()+"\n");
			System.out.print( aquarium.getFishs()[i].getFamiliarity()+","+aquarium.getFishs()[i].getLifeEnd());
			// �����̤j�� �̤j������  snatch
			System.out.print(","+aquarium.getFishs()[i].getMaxWeight()+","+aquarium.getFishs()[i].getMaxSatiation()+","+aquarium.getFishs()[i].getSnatch());
			// getFamiliarityAddToken()
			System.out.print( "FamiliarityAddToken:"+aquarium.getFishs()[i].getFamiliarityAddToken()+"\n");
			// getWeightAddToken()
			System.out.print( "getWeightAddToken:"+aquarium.getFishs()[i].getWeightAddToken()+"\n");
			//  noFight
			System.out.print( "noFight:"+aquarium.getFishs()[i].getNoFight()+"\n");
			// FishTarget
			System.out.print( "FishTarget:"+aquarium.getFishs()[i].getFightTarget()+"\n");
			//  alreadyMaxweight
			System.out.print( "alreadyMaxweight:"+aquarium.getFishs()[i].getAlreadyMaxWeight()+"\n");
			//  myStatus
			System.out.print( "myStatus:"+aquarium.getFishs()[i].getFishStatus()+"\n");
			//  myHealthly
			System.out.print( "myhealth:"+aquarium.getFishs()[i].getFishHealthly()+"\n");
			//   myMove
			System.out.print( "mymove:"+aquarium.getFishs()[i].getMyMove()+"\n");
			
			
	     	String fish_a=FishCataLog.getFishChineseName(aquarium.getFishs()[i]);
		//	System.out.print(a);
	     	fw.write("Fish_Name:"+fish_a);
	     	fw.write("\r\n");

	     	//fw.write("Fish_info:"+aquarium.getFishs()[i].toSaveString()+","+aquarium.getFishs()[i].getFamiliarity()/*+","+aquarium.getFishs()[i].getLifeEnd()*/);
			
			fw.write("Fish_info:"+aquarium.getFishs()[i].toSaveString()+","+aquarium.getFishs()[i].getFamiliarity());

			
			fw.write(","+aquarium.getFishs()[i].getMaxSatiation()+","+aquarium.getFishs()[i].getSnatch());
			fw.write(","+aquarium.getFishs()[i].getFamiliarityAddToken());
			fw.write(","+aquarium.getFishs()[i].getWeightAddToken());
			fw.write(","+aquarium.getFishs()[i].getNoFight());
			//fw.write(","+aquarium.getFishs()[i].getFightTarget());   //�o��ɭPŪ�ҥ���
			fw.write(",null");
			fw.write(","+aquarium.getFishs()[i].getAlreadyMaxWeight());
			fw.write(","+aquarium.getFishs()[i].getFishStatus());
			fw.write(","+aquarium.getFishs()[i].getFishHealthly());
			fw.write(","+aquarium.getFishs()[i].getMyMove());
			fw.write("\r\n");	
			
			// getNowPosition()			
			System.out.print("getNowPosition:");
			fw.write("getNowPosition:");
			for(int xyz: temp_move) {
				System.out.print(xyz+",");
				fw.write(xyz+",");
			}
			System.out.print("\n");
			fw.write("\r\n");
			// getGoalPosition()			
			System.out.print("getGoalPosition:");
			fw.write("getGoalPosition:");
			if(goal_move!=null) {
			for(int xyz: goal_move) {
				System.out.print(xyz+",");
				fw.write(xyz+",");
			}
			}
			
			
			System.out.print("\n");
			fw.write("\r\n");
			// getFeedArray()			
			System.out.print("getFeedArray: ");
			fw.write("getFeedArray: ");
			if(feed!=null) {
			 for(int xyz[]: feed) {
			 	for(int q:xyz) {
			 		System.out.print(q+",");
					fw.write(q+",");
			 	}
		    	}
			}
			System.out.print("\n");
			
			System.out.print("\n");
			fw.write("\r\n");	
			
		}
		
		System.out.print("\n");

		// �s�Ҧ��\�]���ƶq�θ��
		System.out.print("�\�]�q:  "+aquarium.getLandSpace()+"\n");
		fw.write("landscape_nums:"+aquarium.getLandSpace().savetoQuantityData()+"\n");	
		fw.write("\r\n");	
		
		fw.write("landscape_pos:"+aquarium.getLandSpace().savetoTableData());;
		fw.write("\r\n");	
		System.out.print("\n");
		
		
		// �ɮ׼g�J����
	    fw.flush();	   
        fw.close();

	}
}
 
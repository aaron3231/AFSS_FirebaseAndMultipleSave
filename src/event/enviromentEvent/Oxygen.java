package event.enviromentEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import deviceCatalog.DeviceCatalog;
import enviroment.Enviroment;
import event.EnviromentEvent;
import event.Event;
import fish.Fish;
import landScape.LandScape;
import timer.Timerr;

public class Oxygen extends EnviromentEvent {

	@Override
	public void check(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		if(enviroment.getOxygen()>100)
			enviroment.setOxygen(100);
		enviroment.setOxygen(enviroment.getOxygen()-0.1);
		enviroment.setOxygen(enviroment.getOxygen()-nFishs*0.1);	//�u�n�s�b�󳽬��������N�|����0.1���t��q
		
		if(enviroment.getOxygen()<=10)
		{
			for(int i=0;i<nFishs;i++) {
				if(fishs[i].getDeath()<100)
					fishs[i].setDeath(fishs[i].getDeath()+3);
			}
		}
		else if(enviroment.getOxygen()<=25)
		{
			for(int i=0;i<nFishs;i++) {
				if(fishs[i].getDeath()<100)
					fishs[i].setDeath(fishs[i].getDeath()+2);
			}
		}
		else if(enviroment.getOxygen()<=50)
		{
			for(int i=0;i<nFishs;i++) {
				if(fishs[i].getDeath()<100)
					fishs[i].setDeath(fishs[i].getDeath()+1);
			}
		}
		
		if(enviroment.getOxygen()<0)
			enviroment.setOxygen(0);
		if(enviroment.getOxygen()>100)
			enviroment.setOxygen(100);
		
		description(fishs, enviroment, timer, nFishs, landSpace, device,  eventArray, eventArrayDescription, nEvent);
	}

	@Override
	protected void description(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		//�N�ƥ�W�Ǧܸ�Ʈw
		String des="";
		if(enviroment.getOxygen()<=10)
		{
			//�t��q���C
			des="���֯ʮ�Ӧ��F";
			
			eventArray[nEvent[0]]=19;
			eventArrayDescription[nEvent[0]]=des;
			nEvent[0]++;
		}
		else if(enviroment.getOxygen()<=25)
		{
			//�t��q�L�C
			des="�����ǩI�l�x����";
			
			eventArray[nEvent[0]]=18;
			eventArrayDescription[nEvent[0]]=des;
			nEvent[0]++;
		}
		else if(enviroment.getOxygen()<=50)
		{
			//�t��q���C
			des="�ӵ���������F�a";
			
			eventArray[nEvent[0]]=17;
			eventArrayDescription[nEvent[0]]=des;
			nEvent[0]++;
		}
		
		
		if(des!="")
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date beginDate=new Date();
			try {
				beginDate = sdf.parse("1/1/1");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Event.event.insertTable("�t��q",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
		}
	}

}

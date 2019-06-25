package multiPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import aquarium.Aquarium;
import enviroment.Enviroment.Water;
import fish.Fish;
import fish.Fish.FishHealthly;
import imagePanel.ImagePanel;
import fish.FishCataLog;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel
{
	private Aquarium aquarium;
	//魚的詳細資訊.環境資訊
    private JTextArea detailText = new JTextArea(), envT = new JTextArea();
    //20條魚
    private JPanel[] fishlist = { new JPanel(), new JPanel(), new JPanel(), new JPanel(),
    		                      new JPanel(), new JPanel(), new JPanel(), new JPanel(),
    		                      new JPanel(), new JPanel(), new JPanel(), new JPanel(),
    		                      new JPanel(), new JPanel(), new JPanel(), new JPanel(),
    		                      new JPanel(), new JPanel(), new JPanel(), new JPanel(), };
    private JLabel[] fishlistT = { new JLabel(), new JLabel(), new JLabel(), new JLabel(),
    		                       new JLabel(), new JLabel(), new JLabel(), new JLabel(),
    		                       new JLabel(), new JLabel(), new JLabel(), new JLabel(),
    		                       new JLabel(), new JLabel(), new JLabel(), new JLabel(),
    		                       new JLabel(), new JLabel(), new JLabel(), new JLabel(), };
    private ImagePanel[] fishLIs = { new ImagePanel("孔雀魚"), new ImagePanel("孔雀魚"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),
    								 new ImagePanel("Test"), new ImagePanel("Test"),};
    private ImagePanel fishTIs = new ImagePanel("Test");
    //魚的精簡資訊清單
    private JPanel statuslistP = new JPanel()
    {
    	public Component add(Component c)
        {
            c.setBounds(5, getComponentCount() * 55 + 5, 570, 50);
            super.add(c);
            if(getHeight() <= getComponentCount() * 55 + 5)
                setPreferredSize(new Dimension(300, getComponentCount() * 55 + 5));
            return c;
        }
    };
    //清單卷軸
    private JScrollPane statusS = new JScrollPane(statuslistP);
    //清單背景(還原用)
    private JPanel backColor = null;
    //魚的數目
    //private int fishCount = 0;
    //test
    //private int test = 0;
    
	public StatusPanel(Aquarium a)
	{
		aquarium = a;
		setfishCount();
        setstatusS();
        setstatuslistP();
        setdetailT();
        setenvT();
        //insertList();////////////////?
        Listact();
	}
	
	private void setfishCount()
	{
		////////////////////////呼叫, 設定數目
		//////////////////////fishCount = ?;
		//fishCount = 2;
	}
	
	private void setstatusS()
	{
		statusS.setBounds(0, 0, 600, 400);
        statusS.getVerticalScrollBar().setUnitIncrement(16);//滾輪速度
        add(statusS);
	}
	
	private void setstatuslistP()
	{
		statuslistP.setBackground(Color.decode("#48D1CC"));
        statuslistP.setLayout(null);
	}
	
	private void setdetailT()
	{
		setdetailTContent("");
        detailText.setFont(new Font(null, Font.BOLD, 20));
        detailText.setBackground(Color.white);
        detailText.setEditable(false);
        JScrollPane jspC = new JScrollPane(detailText);
        jspC.setBackground(Color.white);
        jspC.setBounds(0, 410, 600, 250);
        add(jspC);
	}
	private void setenvT()
	{
		setenvTContent();
        envT.setFont(new Font(null, Font.BOLD, 20));
        envT.setBackground(Color.white);
        envT.setEditable(false);
        envT.setCaretPosition(0);
        JScrollPane jspE = new JScrollPane(envT);
        jspE.setBackground(Color.white);
        jspE.setBounds(620, 0, 350, 530);
        add(jspE);
	}
	
	private void insertList()////////////////格式
	{
		
		//test++;
		statuslistP.removeAll();
		statuslistP.setVisible(false);
			

		for(int i = 0; i < aquarium.getnFishs(); i++)
        {
			fishlist[i].removeAll();
			fishlist[i].setLayout(null);
			fishlist[i].setBackground(Color.white);
			
			//呼叫 ?
			//呼叫 塞圖
			//呼叫 資料
			fishLIs[i].setBounds(20, 5, 40, 40);
			fishLIs[i].singleImageChange(FishCataLog.getFishChineseName(aquarium.getFishs()[i]));
			fishlist[i].add(fishLIs[i]);
			fishlistT[i].setText("ID:" + aquarium.getFishs()[i].getFishNO() + "           "
								  + "狀態: " + aquarium.getFishs()[i].getFishStatus().toString());
			fishlistT[i].setBounds(130,5,400,35);
			fishlist[i].add(fishlistT[i]);
			///////////////////塞IDname
			fishlist[i].setName(aquarium.getFishs()[i].getFishNO());
			statuslistP.add(fishlist[i]);
        }
		
		Listact();
		statuslistP.setVisible(true);

	}
	
	private void Listact()
	{
		for(int i = 0; i < aquarium.getnFishs(); i++)
		{
			fishlist[i].addMouseListener(listener);
		}
	}
	
    private void setdetailTContent(String a)//ID
    {
    	detailText.removeAll();
    	if(a.equals(""))
    	{
    		detailText.setText("\n\n  選一條魚吧~~");
    	}
    	else if(a.equals("ABC"))
    	{
    		detailText.setText("\n\n  請點擊有資料的項目");
    	}
    	else
    	{

    		int i;
    		for(i = 0; i < aquarium.getnFishs(); i++)
    		{
    			if(a.equals(aquarium.getFishs()[i].getFishNO()))
    				break;
    		}
    		String fishHeathly="";
    		if(aquarium.getFishs()[i].getFishHealthly()==FishHealthly.BOTH)
    			fishHeathly="受傷、生病";
    		else if(aquarium.getFishs()[i].getFishHealthly()==FishHealthly.HURT)
    			fishHeathly="受傷";
    		else if(aquarium.getFishs()[i].getFishHealthly()==FishHealthly.SICKNESS)
    			fishHeathly="生病";
    		else 
    			fishHeathly="健康";
    		Calendar fishAge=Calendar.getInstance();
    		fishAge.set(1,0,1,0,0,0);
    		fishAge.set(Calendar.HOUR_OF_DAY, aquarium.getFishs()[i].getLifetime());
     		detailText.setText("\n    ID: " + aquarium.getFishs()[i].getFishNO() + "\n"
     						 +"    健康狀況:"+fishHeathly + "\n"
    						 + "    身長: " + ((double)aquarium.getFishs()[i].getWeight()/10) + "cm\n"
    						 +"    年齡:" + (fishAge.get(Calendar.YEAR)-1)+"年"+(fishAge.get(Calendar.MONTH))+"月"
    						 +(fishAge.get(Calendar.DAY_OF_MONTH)-1)+"日"+fishAge.get(Calendar.HOUR_OF_DAY)+"小時"+"\n"
    						 + "    活潑度: " + aquarium.getFishs()[i].getLively() + "\n"
    						 + "    飽食度: " + aquarium.getFishs()[i].getSatiationRate() + "%\n"
    						 );
    		fishTIs.setBounds(20, 50, 40, 40);
    		//呼叫
    		//格式
    	}
    	detailText.setCaretPosition(0);
    }
    //改變環境資訊內容
    private void setenvTContent()
    {
    	String[] devices = {"關閉", "關閉", "關閉", "關閉", "關閉"};
    	if(aquarium.getDevice().getFeederbuyer() != 0)
    		if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getModleState())
    			devices[0] = "開啟";
    	if(aquarium.getDevice().getFilterbuyer() != 0)
    		if(aquarium.getDevice().filter[aquarium.getDevice().getFilterbuyer()].getModleState())
    			devices[1] = "開啟";
    	if(aquarium.getDevice().getInflatorbuyer() != 0)
    		if(aquarium.getDevice().inflator[aquarium.getDevice().getInflatorbuyer()].getModleState())
    			devices[2] = "開啟";
    	if(aquarium.getDevice().getFlashLightbuyer() != 0)
    		if(aquarium.getDevice().flashLight[aquarium.getDevice().getFlashLightbuyer()].getModleState())
    			devices[3] = "開啟";
    	if(aquarium.getDevice().getHeaterbuyer() != 0)
    		if(aquarium.getDevice().heater[aquarium.getDevice().getHeaterbuyer()].getModleState())
    			devices[4] = "開啟";
    	String water="";
    	if(aquarium.getEnviroment().getWater()==Water.OCEAN)
    		water="海水";
    	else if(aquarium.getEnviroment().getWater()==Water.FRESHWATER)
    		water="淡水";
    	envT.setText("  環境資訊:\n\n" 
    			 	   
    				   +"   時間:\n   " + aquarium.getTimer().toString()+"\n"
    				   +"   水的種類:"+water+"\n"
    				   + "   水質:" + aquarium.getEnviroment().getWaterQuality() + "%\n"
    				   + "   水溫:" + aquarium.getEnviroment().getWaterTemperature() + "度\n"
    				   + "   含氧量:" + String.format("%.2f", aquarium.getEnviroment().getOxygen()) + "%\n"
    				   + "   糞便量" + aquarium.getEnviroment().getStool() + "\n"
    				   + "   魚缸大小:" + (aquarium.getEnviroment().getFishTankSize() + 1) + "尺缸\n"
    				   + "   餵食器: " + devices[0] + "\n"
    				   + "   過濾器: " + devices[1] + "\n"
    				   + "   氧氣泵: " + devices[2] + "\n"
    				   + "   照明器: " + devices[3] + "\n"
    				   + "   加溫器: " + devices[4]);
    	envT.setCaretPosition(0);
    }
    //還原捲軸
    private void resetstatusS()
    {
    	JScrollBar a = statusS.getVerticalScrollBar();
		a.setValue(a.getMinimum());
    }
    //全部重整
    public void refresh()
    {
    	////////////////改環境內容未寫
    	setfishCount();
    	setdetailTContent("");
    	System.out.println(Fish.totalFish);
    	insertList();
    	resetstatusS();
    	setenvTContent();
    }
    //List點選的事件處理
    private MouseAdapter listener = new MouseAdapter()
    {
        public void mousePressed(MouseEvent e)
        {
        	int i;
            JPanel pnl = (JPanel)e.getSource();
            //將前一次的底色還原
            if(backColor != null)
                backColor.setBackground(Color.white);
            for(i = 0; i < 20; i++)
            {
            	if(fishlist[i].getName().equals(pnl.getName()))
            		break;
            }
            	setdetailTContent(pnl.getName());
            	//設定點選的底色
            	pnl.setBackground(Color.decode("#AFEEEE"));
            System.out.println("click fish list:"+pnl.getName());
            backColor = pnl;//標記起來，作為下一次切換時，還原底色使用
        }
    };
}

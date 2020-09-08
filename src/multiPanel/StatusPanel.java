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
	//�����ԲӸ�T.���Ҹ�T
    private JTextArea detailText = new JTextArea(), envT = new JTextArea();
    //20����
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
    private ImagePanel[] fishLIs = { new ImagePanel("�ճ���"), new ImagePanel("�ճ���"),
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
    //������²��T�M��
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
    //�M����b
    private JScrollPane statusS = new JScrollPane(statuslistP);
    //�M��I��(�٭��)
    private JPanel backColor = null;
    //�����ƥ�
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
		////////////////////////�I�s, �]�w�ƥ�
		//////////////////////fishCount = ?;
		//fishCount = 2;
	}
	
	private void setstatusS()
	{
		statusS.setBounds(0, 0, 600, 400);
        statusS.getVerticalScrollBar().setUnitIncrement(16);//�u���t��
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
	
	private void insertList()////////////////�榡
	{
		
		//test++;
		statuslistP.removeAll();
		statuslistP.setVisible(false);
			

		for(int i = 0; i < aquarium.getnFishs(); i++)
        {
			fishlist[i].removeAll();
			fishlist[i].setLayout(null);
			fishlist[i].setBackground(Color.white);
			
			//�I�s ?
			//�I�s ���
			//�I�s ���
			fishLIs[i].setBounds(20, 5, 40, 40);
			fishLIs[i].singleImageChange(FishCataLog.getFishChineseName(aquarium.getFishs()[i]));
			fishlist[i].add(fishLIs[i]);
			fishlistT[i].setText("ID:" + aquarium.getFishs()[i].getFishNO() + "           "
								  + "���A: " + aquarium.getFishs()[i].getFishStatus().toString());
			fishlistT[i].setBounds(130,5,400,35);
			fishlist[i].add(fishlistT[i]);
			///////////////////��IDname
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
    		detailText.setText("\n\n  ��@�����a~~");
    	}
    	else if(a.equals("ABC"))
    	{
    		detailText.setText("\n\n  ���I������ƪ�����");
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
    			fishHeathly="���ˡB�ͯf";
    		else if(aquarium.getFishs()[i].getFishHealthly()==FishHealthly.HURT)
    			fishHeathly="����";
    		else if(aquarium.getFishs()[i].getFishHealthly()==FishHealthly.SICKNESS)
    			fishHeathly="�ͯf";
    		else 
    			fishHeathly="���d";
    		Calendar fishAge=Calendar.getInstance();
    		fishAge.set(1,0,1,0,0,0);
    		fishAge.set(Calendar.HOUR_OF_DAY, aquarium.getFishs()[i].getLifetime());
     		detailText.setText("\n    ID: " + aquarium.getFishs()[i].getFishNO() + "\n"
     						 +"    ���d���p:"+fishHeathly + "\n"
    						 + "    ����: " + ((double)aquarium.getFishs()[i].getWeight()/10) + "cm\n"
    						 +"    �~��:" + (fishAge.get(Calendar.YEAR)-1)+"�~"+(fishAge.get(Calendar.MONTH))+"��"
    						 +(fishAge.get(Calendar.DAY_OF_MONTH)-1)+"��"+fishAge.get(Calendar.HOUR_OF_DAY)+"�p��"+"\n"
    						 + "    �����: " + aquarium.getFishs()[i].getLively() + "\n"
    						 + "    ������: " + aquarium.getFishs()[i].getSatiationRate() + "%\n"
    						 );
    		fishTIs.setBounds(20, 50, 40, 40);
    		//�I�s
    		//�榡
    	}
    	detailText.setCaretPosition(0);
    }
    //�������Ҹ�T���e
    private void setenvTContent()
    {
    	String[] devices = {"����", "����", "����", "����", "����"};
    	if(aquarium.getDevice().getFeederbuyer() != 0)
    		if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getModleState())
    			devices[0] = "�}��";
    	if(aquarium.getDevice().getFilterbuyer() != 0)
    		if(aquarium.getDevice().filter[aquarium.getDevice().getFilterbuyer()].getModleState())
    			devices[1] = "�}��";
    	if(aquarium.getDevice().getInflatorbuyer() != 0)
    		if(aquarium.getDevice().inflator[aquarium.getDevice().getInflatorbuyer()].getModleState())
    			devices[2] = "�}��";
    	if(aquarium.getDevice().getFlashLightbuyer() != 0)
    		if(aquarium.getDevice().flashLight[aquarium.getDevice().getFlashLightbuyer()].getModleState())
    			devices[3] = "�}��";
    	if(aquarium.getDevice().getHeaterbuyer() != 0)
    		if(aquarium.getDevice().heater[aquarium.getDevice().getHeaterbuyer()].getModleState())
    			devices[4] = "�}��";
    	String water="";
    	if(aquarium.getEnviroment().getWater()==Water.OCEAN)
    		water="����";
    	else if(aquarium.getEnviroment().getWater()==Water.FRESHWATER)
    		water="�H��";
    	envT.setText("  ���Ҹ�T:\n\n" 
    			 	   
    				   +"   �ɶ�:\n   " + aquarium.getTimer().toString()+"\n"
    				   +"   ��������:"+water+"\n"
    				   + "   ����:" + aquarium.getEnviroment().getWaterQuality() + "%\n"
    				   + "   ����:" + aquarium.getEnviroment().getWaterTemperature() + "��\n"
    				   + "   �t��q:" + String.format("%.2f", aquarium.getEnviroment().getOxygen()) + "%\n"
    				   + "   �T�K�q" + aquarium.getEnviroment().getStool() + "\n"
    				   + "   �����j�p:" + (aquarium.getEnviroment().getFishTankSize() + 1) + "�ج�\n"
    				   + "   ������: " + devices[0] + "\n"
    				   + "   �L�o��: " + devices[1] + "\n"
    				   + "   ���: " + devices[2] + "\n"
    				   + "   �ө���: " + devices[3] + "\n"
    				   + "   �[�ž�: " + devices[4]);
    	envT.setCaretPosition(0);
    }
    //�٭챲�b
    private void resetstatusS()
    {
    	JScrollBar a = statusS.getVerticalScrollBar();
		a.setValue(a.getMinimum());
    }
    //��������
    public void refresh()
    {
    	////////////////�����Ҥ��e���g
    	setfishCount();
    	setdetailTContent("");
    	System.out.println(Fish.totalFish);
    	insertList();
    	resetstatusS();
    	setenvTContent();
    }
    //List�I�諸�ƥ�B�z
    private MouseAdapter listener = new MouseAdapter()
    {
        public void mousePressed(MouseEvent e)
        {
        	int i;
            JPanel pnl = (JPanel)e.getSource();
            //�N�e�@���������٭�
            if(backColor != null)
                backColor.setBackground(Color.white);
            for(i = 0; i < 20; i++)
            {
            	if(fishlist[i].getName().equals(pnl.getName()))
            		break;
            }
            	setdetailTContent(pnl.getName());
            	//�]�w�I�諸����
            	pnl.setBackground(Color.decode("#AFEEEE"));
            System.out.println("click fish list:"+pnl.getName());
            backColor = pnl;//�аO�_�ӡA�@���U�@�������ɡA�٭쩳��ϥ�
        }
    };
}

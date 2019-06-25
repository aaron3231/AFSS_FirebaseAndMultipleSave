package main;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.*;

import aquarium.Aquarium;
import aquarium.AquariumTask;
import aquarium.SpeedTask;
import database.Cost_data;
import database.Date_data;
import database.Event_data;
import database.Fish_data;
import databaseNew.DatabaseNew;
import enterPanel.EnterPanel;
import enviroment.Enviroment.Water;
import firebase.FirebaseDownload;
import firebase.FirebaseUpload;
import fish.Fish;
import fishtankPanel.FishtankPanel;
import ioSave.Load;
import ioSave.Save;
import landScape.Decoration;
import sound.AFSSSound;
import sound.ChooseBgmMenu;
import status.Status;
import userName.UserName;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private Timer timer;
	private AFSSSound sound1 = new AFSSSound(1,2); 
	private AFSSSound sound2 = new AFSSSound(1,1); 
	private AFSSSound sound3 = new AFSSSound(2,1);
	
	private AFSSSound backgroundBgm = new AFSSSound(32,3);
	private int nowPlaying = 32;
	private Boolean playing = true;
	
	private Aquarium aquarium = new Aquarium();
    //�إ߶i�J�I��.����
    public EnterPanel enterPanel;
    //�إ߳����I�� 
    public FishtankPanel fishTankPanel;
    //���A�@������
    private Status statusPanel;
    //�\���    
	public JMenuBar menuBar = new JMenuBar();
    //�i�J�e�����s
    private JButton openNewButton = new JButton();
    private JButton resumeButton = new JButton();
    private JButton exitButton = new JButton();
    
    private JButton saveButton = new JButton();
    
    //�\���W���s
    private JButton swiftButton = new JButton();
    private JButton speedUpButton = new JButton();
    private JButton returnEnterPanelButton = new JButton();
    private JButton setBgmButton = new JButton();
    private JButton feedButton = new JButton();
    private JButton changeWaterButton = new JButton();
    private JButton netButton = new JButton();
    //private JButton achievementButton = new JButton();
    //�u��C�Ϊť�
    private JLabel[] menuBarDashLabel = { new JLabel("   "), new JLabel("   "), new JLabel("   "),
    		                  new JLabel("   "), new JLabel("   "), new JLabel("   "),
    		                  new JLabel("   "), new JLabel("   "), new JLabel("   "),
    		                  new JLabel("   "), new JLabel("   "), new JLabel("   ") };

    private final int ENTERBUTTONFONTSIZE = 15;

    private final int LEFT = 0;
    private final int TOP = 0;
    private final int LONGTH = 1280;
    private final int WIDTH = 720;

    private final int INITIALLOCATIONLEFT = 50;
    private final int INITIALLOCATIONTOP = 40;
    
    private final String FILEPATH = "src//image//";
    
    private SpeedTask task;  //�[�t���u�{
    private Thread speedUp;
    
    private Boolean chooseData = false;
    
    public MainFrame()
    {
        super("�����i��-Aquarium Farming Simulation System");
        setFishTank();
        
        Load load =new Load();
		try {
			aquarium=load.LoadData();
			fishTankPanel.loadDb(aquarium);
		} catch (IOException e) {
			System.out.print("MainFrame LoadData fail!");
		}
		
        statusPanel = new Status(aquarium,fishTankPanel);
        System.out.println(statusPanel );
        setWindow();
        setEnterPanel();
        setMenuBar();
        setStatusPanel();
    }
    
    private void start(Boolean choose)
    {
    	loadData(choose);
        statusPanel = new Status(aquarium,fishTankPanel);
        setStatusPanel();
    }
    
    private void startNew()
    {
        statusPanel = new Status(aquarium,fishTankPanel);
        setStatusPanel();
    }
    
    private void loadData(Boolean choose)
    {
    	Load load =new Load();
        
        try {
        	FirebaseDownload download = new FirebaseDownload();
        	if(choose)
        		aquarium = download.DownloadData();
        	else
        		aquarium = load.LoadData();
		} catch (IOException e1) {
			// TODO �۰ʲ��ͪ� catch �϶�
			System.out.println("Load fail!");
		}
		fishTankPanel.loadDb(aquarium);
    }
    
    private void setWindow()
    {
        ImageIcon icon = new ImageIcon(FILEPATH + "WindowIcon.png");
        setIconImage(icon.getImage());
        setLayout(null);
        setSize(LONGTH + 16, WIDTH + 69); //16, 39
        setLocation(INITIALLOCATIONLEFT, INITIALLOCATIONTOP);
        setResizable(false);//������j���s�L�� 
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//DO_NOTHING_ON_CLOSE
    }
    
    private void setEnterPanel()
    {
    	enterPanel = new EnterPanel("Enter");
        enterPanel.setLayout(null);
        enterPanel.setBounds(LEFT, TOP, LONGTH, 750);
        add(enterPanel);
        setEnterPanelButton();
    }
    
    private void setFishTank()
    {
    	fishTankPanel = new FishtankPanel("Fishtank");
    	fishTankPanel.setLayout(null);
        fishTankPanel.setBounds(LEFT, TOP, LONGTH, WIDTH);
        fishTankPanel.setVisible(false);
        add(fishTankPanel);
    }
    
    private void setEnterPanelButton()
    {
    	openNewButton.setText("�إ߷s��");
    	openNewButton.setToolTipText("test");
        openNewButton.setFont(new Font(null, Font.BOLD, ENTERBUTTONFONTSIZE));
        openNewButton.setBounds(550, 320, 100, 50);
        enterPanel.add(openNewButton);
        resumeButton.setText("�~�����");
        resumeButton.setFont(new Font(null, Font.BOLD, ENTERBUTTONFONTSIZE));
        resumeButton.setBounds(550, 380, 100, 50);
        enterPanel.add(resumeButton);
        saveButton.setText("�W��");
        saveButton.setFont(new Font(null, Font.BOLD, ENTERBUTTONFONTSIZE));
        saveButton.setBounds(550, 440, 100, 50);
        enterPanel.add(saveButton);
        exitButton.setText("���}");
        exitButton.setFont(new Font(null, Font.BOLD, ENTERBUTTONFONTSIZE));
        exitButton.setBounds(550, 500, 100, 50);
        enterPanel.add(exitButton);
        //���s�[�J�\��
        openNewButton.addActionListener(bH);
        resumeButton.addActionListener(bH);
        saveButton.addActionListener(bH);
        exitButton.addActionListener(bH);
    }
    
    private void setMenuBar()
    {
    	setJMenuBar(menuBar);
    	menuBar.setVisible(false);        
        //�]�w�u��C�W�����s �[�J�Źj
        swiftButton.setText("�����ܪ��A�@��");
        menuBar.add(swiftButton);
        menuBar.add(menuBarDashLabel[0]);
        speedUpButton.setText("�[�t�ɶ�");
        menuBar.add(speedUpButton);
        menuBar.add(menuBarDashLabel[1]);
        returnEnterPanelButton.setText("�^����");
        menuBar.add(returnEnterPanelButton);
        menuBar.add(menuBarDashLabel[2]);
        menuBar.add(menuBarDashLabel[3]);
        menuBar.add(menuBarDashLabel[4]);
        setBgmButton.setText("�]�w����");
        menuBar.add(setBgmButton);
        menuBar.add(menuBarDashLabel[5]);
        menuBar.add(menuBarDashLabel[6]);
        menuBar.add(menuBarDashLabel[7]);
        menuBar.add(menuBarDashLabel[8]);
        feedButton.setText("���}��");
        menuBar.add(feedButton);
        menuBar.add(menuBarDashLabel[9]);
        changeWaterButton.setText("����");
        menuBar.add(changeWaterButton);
        menuBar.add(menuBarDashLabel[10]);
        netButton.setText("������"); //  �ˬd���N
        menuBar.add(netButton);
        /*achievementButton.setText("���N");
        achievementButton.setVisible(false);
        menuBar.add(achievementButton);*/
        
        //���s�[�J�\��
        swiftButton.addActionListener(bH);
        speedUpButton.addActionListener(bH);
        returnEnterPanelButton.addActionListener(bH);
        setBgmButton.addActionListener(bH);
        feedButton.addActionListener(bH);
        changeWaterButton.addActionListener(bH);
        netButton.addActionListener(bH);
        //achievementButton.addActionListener(bH);
    }
    
    private void setStatusPanel()
    {
    	statusPanel.setLayout(null);
        statusPanel.setBounds(LEFT, TOP, LONGTH, WIDTH);
        this.add(statusPanel);
        statusPanel.setVisible(false);
    }
    
    private void swift()
    {
    	if(statusPanel.isVisible() == false)
    	{
    		swiftButton.setText("�����ܳ���");
    		returnEnterPanelButton.setVisible(false);
    		speedUpButton.setVisible(false);
    		feedButton.setVisible(false);
            changeWaterButton.setVisible(false);
            netButton.setVisible(false);
            //achievementButton.setVisible(true);
            setBgmButton.setVisible(false);
    	}
    	else
    	{
    		swiftButton.setText("�����ܪ��A�@��");
    		returnEnterPanelButton.setVisible(true);
          	speedUpButton.setVisible(true);
          	feedButton.setVisible(true);
            changeWaterButton.setVisible(true);
            netButton.setVisible(true);
           // achievementButton.setVisible(false);
            setBgmButton.setVisible(true);
    	}
    }
    
    private void backgroundBgmPlay()
    {
    	if(backgroundBgm.audioClip != null)
    		backgroundBgm.audioClip.start();
    	else
    		backgroundBgm.play();
    }
    
    private void backgroundBgmStop()
    {
    	if(playing)
    		backgroundBgm.audioClip.stop();
    }
    
    private void changeBgm()
    {
    	if(playing == true)
    		backgroundBgm.audioClip.stop();
		ChooseBgmMenu bgmMenu = new ChooseBgmMenu(playing);
		bgmMenu.setBounds(this.getX() + 300, this.getY() + 300, 300, 150); //300.300
		bgmMenu.setModal(true);
		bgmMenu.setVisible(true);
		bgmMenu.dispose();
		
		System.out.println("now playing:" + nowPlaying);
		if(bgmMenu.isVisible() == false) {
			if(bgmMenu.getStop())
			{
				playing = false;
				System.out.println("stop playing!");
			}
			else if(bgmMenu.getNowPlaying() == 0)
			{
				backgroundBgm.audioClip.start();
				playing = true;
			}
			else if(bgmMenu.getNowPlaying() == nowPlaying)
			{
				backgroundBgm.audioClip.start();
				playing = true;
			}
			else
			{
				nowPlaying = bgmMenu.getNowPlaying();
				System.out.println("Bgm change to:" + nowPlaying);
				backgroundBgm = new AFSSSound(nowPlaying, 3);
				playing = true;
				backgroundBgm.play();
			}
		}
		System.out.println("Bgm set complete!");
    }
    
    private ActionListener bH = new ActionListener()
    {
  		public void actionPerformed(ActionEvent ae)
        {
  			if(ae.getSource() == openNewButton)
  			{
  				NewDialog newDialog = new NewDialog();
  				newDialog.setBounds(INITIALLOCATIONLEFT + 400, INITIALLOCATIONTOP + 225, 500, 400);
  				newDialog.setLayout(null);
  				newDialog.setAlwaysOnTop(true);
  				newDialog.setModal(true);
  				newDialog.setVisible(true);
  				newDialog.dispose();
  					/*if(!newDialog.getName().equals(""))
  						UserName.userName = newDialog.getName();
  					else
  						UserName.userName = "yee";*/
  					UserName.userName = newDialog.getName();
  					
  					DatabaseNew newDatabase = new DatabaseNew();
  					newDatabase.createDatabase();
  					
  					start(false);
  				
	  				Fish_data  fish=new Fish_data();
	  		    	Cost_data  cost=new Cost_data();
	  		    	Event_data event= new Event_data();
	  		    	Date_data  date=new Date_data();
	  		    	
	  		    	fish.dropTable(); 
	  		    	cost.dropTable();
	  		    	event.dropTable();
	  		    	date.dropTable();
	  		    
	  		        date.createTable();
	  		    	fish.createTable();
	  		    	cost.createTable();
	  		    	event.createTable();
  				
  				
  					if(timer!=null) {
  					timer.cancel();
  					timer.purge();
  					}
  					fishTankPanel.reset();
  					int[] set;
  					set=newDialog.getSet();
  					
  					aquarium.aquariumReset(set[0],set[1]);
  					
  					//statusP.setDisplaySize(newD.result());/////////////�I�s�禡
  					/*AquariumTask temp=new AquariumTask(aquarium);
  	  				timer = new Timer();
  	  			    timer.schedule(temp, 0,1000);*/
  					
  	  			    /*menuBar.setVisible(true);
  	  				fishTankPanel.setVisible(true);
  	  				enterPanel.setVisible(false);
  	  				
  	  				sound1.play();
  	  				backgroundBgmPlay();
  				
  				newDialog = null;
  				sound1.play();*/
  			}
  			else if(ae.getSource() == resumeButton)
  			{
  				ResumeDialog resumeDialog = new ResumeDialog();
  				resumeDialog.setBounds(INITIALLOCATIONLEFT + 400, INITIALLOCATIONTOP + 225, 500, 400);
  				resumeDialog.setLayout(null);
  				resumeDialog.setAlwaysOnTop(true);
  				resumeDialog.setModal(true);
  				resumeDialog.setVisible(true);
  				resumeDialog.dispose();
  				
  				if(resumeDialog.getConfirm())
  				{
  					if(!resumeDialog.chooseFirebase())
  					{
  						String userNameTemp = UserName.userName;
  	  					UserName.userName = resumeDialog.getName();
  	  					if(timer!=null) {
  	  						timer.cancel();
  	  						timer.purge();
  	    				}
  	    				fishTankPanel.reset();
  	    				start(chooseData);
  	    				
						AquariumTask temp = new AquariumTask(aquarium);
  	  	  	  			timer = new Timer();
  	  	  	  			timer.schedule(temp, 0,1000);
  	  	  	  				
  	  	  	  			menuBar.setVisible(true);
  	  	  	  			fishTankPanel.setVisible(true);
  	  	  	  			enterPanel.setVisible(false);
  	  	  	  				
  	  	  	  			sound1.play();
  	  	  	  				
  	  	  	  			if(playing)
  	  	  	  				backgroundBgmPlay();
  					}
  					else
  					{
  						if(!UserName.userName.equals(resumeDialog.getName()))
  						{
  							String userNameTemp = UserName.userName;
  	  						UserName.userName = resumeDialog.getName();
  	  						
  	  						DatabaseNew newDatabase = new DatabaseNew();
  	  	  					newDatabase.createDatabase();
  	  	  					
  	  	  				Fish_data  fish=new Fish_data();
  	    		    	Cost_data  cost=new Cost_data();
  	    		    	Event_data event= new Event_data();
  	    		    	Date_data  date=new Date_data();
  	    		    	
  	    		    	fish.dropTable(); 
  	    		    	cost.dropTable();
  	    		    	event.dropTable();
  	    		    	date.dropTable();
  	    		    
  	    		        date.createTable();
  	    		    	fish.createTable();
  	    		    	cost.createTable();
  	    		    	event.createTable();
  	  						
  	  						start(true);
  						}
  						
  						AquariumTask temp = new AquariumTask(aquarium);
  	  	  	  			timer = new Timer();
  	  	  	  			timer.schedule(temp, 0,1000);
  	  	  	  				
  	  	  	  			menuBar.setVisible(true);
  	  	  	  			fishTankPanel.setVisible(true);
  	  	  	  			enterPanel.setVisible(false);
  	  	  	  				
  	  	  	  			sound1.play();
  	  	  	  				
  	  	  	  			if(playing)
  	  	  	  				backgroundBgmPlay();
  					}
  				}
  			}
  			else if(ae.getSource() == saveButton)
  			{
  				UploadDialog saveDialog = new UploadDialog();
  				saveDialog.setBounds(INITIALLOCATIONLEFT + 400, INITIALLOCATIONTOP + 225, 400, 300);
  				saveDialog.setLayout(null);
  				saveDialog.setAlwaysOnTop(true);
  				saveDialog.setModal(true);
  				saveDialog.setVisible(true);
  				saveDialog.dispose();
  				
  				if(saveDialog.getSave())
  				{
  					try {
  						FirebaseUpload upload = new FirebaseUpload(aquarium, saveDialog.getName());
  					} catch (IOException e) {
  						// TODO �۰ʲ��ͪ� catch �϶�
  						e.printStackTrace();
  						System.out.println("Upload fail!");
  					}
  				}
  			}
  			else if(ae.getSource() == exitButton)
  			{
  				Save save=new Save(aquarium);
	                try {
						save.SaveData(UserName.userName + ".txt");
					} catch (Exception e) {
						System.out.println("Save fail!");
					}  	
  				
  				System.exit(0);
  			}
  			else if(ae.getSource() == swiftButton)
            {
  				if(statusPanel.isVisible() == false)
      			{
  					timer.cancel();
  					timer.purge();
  					swift();
  					statusPanel.refresh();
  					statusPanel.setVisible(true);
  					fishTankPanel.setVisible(false);
  					fishTankPanel.setStop(true);
  					sound3.play();
  				
  					backgroundBgmStop();
      			}
                else
                {
                	timer = new Timer();
                	AquariumTask temp=new AquariumTask(aquarium);
      				
      			    timer.schedule(temp, 0,1000);
                	///////////////////////�I�stimer
                	swift();
                	fishTankPanel.removeDisplay();
                	fishTankPanel.addDisplay(aquarium.getLandSpace().getTable(),aquarium.getEnviroment().getFishTankSize());
                	fishTankPanel.setFeedXY(aquarium.getEnviroment().getFeedXY());
                	statusPanel.setVisible(false);
                  	fishTankPanel.setVisible(true);
                  	fishTankPanel.setStop(false);
                  	fishTankPanel.refresh(0);
                  	sound3.play();
                  	
                  	if(playing)
                  	backgroundBgmPlay();
                }
            }
  			else if(ae.getSource() == speedUpButton)
  			{
  				//JOptionPane.showMessageDialog(ftP, "�[�t","�[�t!!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/pic/speedD.png"));
  				//�[�t�禡
  				//System.out.println(Fish.totalFish);
  				///////////////////////�I�stimer
  				if(speedUp==null ||speedUp.getState()!=Thread.State.RUNNABLE) {
  					SpeedDialog test = new SpeedDialog();
  	  				test.setBounds(INITIALLOCATIONLEFT + 400, INITIALLOCATIONTOP + 225, 500, 420);
  	  				//test.setBounds(0, 0, 500, 420); ///////////////////////////////////�n���
  	  				test.setLayout(null);
  	  				test.setAlwaysOnTop(true);
  	  				test.setModal(true);
  	  				test.setVisible(true);
  	  				int[] tests = test.result();
  	  		
  					System.out.println("�[�t�ɶ����: 1:�@�p��  2:�@��  3:�@��§��  0:����\n" + tests[0] + "\n");
  					System.out.println("�����[   �۵M���`  �D�۵M���` " + 
  								   "���ͯf   ���ͪ��̤ܳj�������a�� " + 
  								   "�L�o���a�� ����a�� �ө����a�� " + 
  								   "�[�ž��a�� ���褣�}  �����V�|  " + 
  								   "�ūװ���  �ū׹L��  �ūװ��C  " + 
  								   "�ū׹L�C  �t��q���C �t��q�L�C " + 
  								   "�t��q���C\n");
  					for(int i = 1; i < 20; i++)
  						System.out.print("" + tests[i] + "     ");
  					System.out.println("");
  					if(tests[0] != 0)
  					{
  						timer.cancel();
  						timer.purge();  
  						timer = new Timer();
  		            	AquariumTask temp=new AquariumTask(aquarium);
  						task=new SpeedTask(aquarium,timer,tests);
  		  				speedUp=new Thread(task);
  		  				System.out.println(speedUp.getState());
  		  				speedUp.start();
  		  				System.out.println(speedUp.getState());
  		  				sound2.play();
  					}
  					test = null;
  				}
  			}
  			else if(ae.getSource() == returnEnterPanelButton)
  			{

				timer.cancel();
				timer.purge(); 
				backgroundBgmStop();
				
				menuBar.setVisible(false);
  				enterPanel.setVisible(true);
  				fishTankPanel.setVisible(false);
  				/////////////////////////�I�stimer
  			}
  			/*else if(ae.getSource() == achievementButton)
  			{
  				aquarium.achieve.runCheckAchievement(aquarium.getDevice(), aquarium.getFishs());
  				aquarium.achieve.setVisible(true);
  			}*/
  			else if(ae.getSource() == setBgmButton)
  			{
  				changeBgm();
  			}
  			else if(ae.getSource() == feedButton)
  			{
  				aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].manualFeed(aquarium.getEnviroment());
  				fishTankPanel.setFeedXY(aquarium.getEnviroment().getFeedXY());
  			}
  			else if(ae.getSource() == changeWaterButton)
  			{
  				double quantity = Double.parseDouble(JOptionPane.showInputDialog("��J�����q(0~0.25)"));
  				aquarium.getDevice().changeWater.change(aquarium.getEnviroment(), 1, quantity);
  			}
  			else if(ae.getSource() == netButton)
  			{
  				///////////////////////////�I�s������
  				ArrayList<Fish> removeFish=aquarium.removeAFish();
  				fishTankPanel.removeFish(removeFish);
  			}
        }
    };
        
  	public static void main(String [] args)
    {
		new MainFrame();
    }
}
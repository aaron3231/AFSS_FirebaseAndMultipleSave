package status;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aquarium.Aquarium;
import fishtankPanel.FishtankPanel;
import multiPanel.StatusPanel;
import multiPanel.DevicePanel;
import multiPanel.DisplayPanel;
import multiPanel.EventPanel;
import multiPanel.HistoryPanel;
import multiPanel.NewfishPanel;

@SuppressWarnings("serial")
public class Status extends JPanel
{
	private Aquarium aquarium;
	//互動事件
    private ButtonHandler bH = new ButtonHandler();
	//狀態一覽按鈕
    private JButton statusButton = new JButton(), eventButton = new JButton(), historyButton = new JButton(),
    		        newfishButton = new JButton(), deviceButton = new JButton(), displayButton = new JButton();
    public JLabel MoneyLabel = new JLabel();
    private StatusPanel statusPanel;
    private EventPanel eventPanel;
    private HistoryPanel historyPanel;
    private NewfishPanel newfishPanel;
    private DevicePanel devicePanel;
    private DisplayPanel displayPanel;
    
    //按鈕位置大小常數
    private final int BUTTONLEFT = 30;
    private final int BUTTONTOP = 30;
    private final int BUTTONLENGTH = 200;
    private final int BUTTONWIDTH = 75;
    private final int BUTTONSPACING = 100;
    //字體大小
    private final int BUTTONFONTSIZE = 22;
    //面板位置大小常數
    private final int PANELLEFT = 250;
    private final int PANELTOP = 30;
    private final int PANELLENGTH = 1000;
    private final int PANELWIDTH = 690;
    		
    public Status(Aquarium a,FishtankPanel ftp)
    {
    	aquarium = a;
    	newfishPanel = new NewfishPanel(aquarium,ftp);
    	devicePanel = new DevicePanel(aquarium);
    	displayPanel = new DisplayPanel(aquarium);
    	eventPanel = new EventPanel(aquarium);
    	statusPanel = new StatusPanel(aquarium);
    	historyPanel = new HistoryPanel(aquarium);
    	devicePanel.Money(MoneyLabel);
        displayPanel.Money(MoneyLabel);
        setButton();
        setPanel();
        setMoney();
        
    }
    /*
    public void setDisplaySize(int size)////////////////不需要?
    {
    	displayP.setSize(size);
    }
    */
    private void setButton()
    {
    	//位置.大小.字體
    	statusButton.setText("狀態一覽");
    	statusButton.setForeground(Color.red.brighter());
    	statusButton.setFont(new Font(null, Font.BOLD, BUTTONFONTSIZE));
    	statusButton.setBounds(BUTTONLEFT, BUTTONTOP, BUTTONLENGTH, BUTTONWIDTH);
        add(statusButton);
        eventButton.setText("事件紀錄");
        eventButton.setFont(new Font(null, Font.BOLD, BUTTONFONTSIZE));
        eventButton.setBounds(BUTTONLEFT, BUTTONTOP+BUTTONSPACING*1, BUTTONLENGTH, BUTTONWIDTH);
        add(eventButton);
        historyButton.setText("歷史紀錄");
        historyButton.setFont(new Font(null, Font.BOLD, BUTTONFONTSIZE));
        historyButton.setBounds(BUTTONLEFT, BUTTONTOP+BUTTONSPACING*2, BUTTONLENGTH, BUTTONWIDTH);
        add(historyButton);
        newfishButton.setText("加入新魚");
        newfishButton.setFont(new Font(null, Font.BOLD, BUTTONFONTSIZE));
        newfishButton.setBounds(BUTTONLEFT, BUTTONTOP+BUTTONSPACING*3, BUTTONLENGTH, BUTTONWIDTH);
        add(newfishButton);
        deviceButton.setText("設備目錄");
        deviceButton.setFont(new Font(null, Font.BOLD, BUTTONFONTSIZE));
        deviceButton.setBounds(BUTTONLEFT, BUTTONTOP+BUTTONSPACING*4, BUTTONLENGTH, BUTTONWIDTH);
        add(deviceButton);
        displayButton.setText("擺放設定");
        displayButton.setFont(new Font(null, Font.BOLD, BUTTONFONTSIZE));
        displayButton.setBounds(BUTTONLEFT, BUTTONTOP+BUTTONSPACING*5, BUTTONLENGTH, BUTTONWIDTH);
        add(displayButton);
        //加入功能
        statusButton.addActionListener(bH);
        eventButton.addActionListener(bH);
        historyButton.addActionListener(bH);
        newfishButton.addActionListener(bH);
        deviceButton.addActionListener(bH);
        displayButton.addActionListener(bH);
    }
    
    private void setPanel()
    {
    	//排版.位置
        statusPanel.setLayout(null);
        statusPanel.setBounds(PANELLEFT, PANELTOP, PANELLENGTH, PANELWIDTH);
        add(statusPanel);
        eventPanel.setLayout(null);
        eventPanel.setBounds(PANELLEFT, PANELTOP, PANELLENGTH, PANELWIDTH);
        eventPanel.setVisible(false);
        add(eventPanel);
        historyPanel.setLayout(null);
        historyPanel.setBounds(PANELLEFT, PANELTOP, PANELLENGTH, PANELWIDTH);
        historyPanel.setVisible(false);
        add(historyPanel);
        newfishPanel.setLayout(null);
        newfishPanel.setBounds(PANELLEFT, PANELTOP, PANELLENGTH, PANELWIDTH);
        newfishPanel.setVisible(false);
        add(newfishPanel);
        devicePanel.setLayout(null);
        devicePanel.setBounds(PANELLEFT, PANELTOP, PANELLENGTH, PANELWIDTH);
        devicePanel.setVisible(false);
        add(devicePanel);
        displayPanel.setLayout(null);
        displayPanel.setBounds(PANELLEFT, PANELTOP, PANELLENGTH, PANELWIDTH);
        displayPanel.setVisible(false);
        add(displayPanel);
    }
    
    private void setMoney()
    {
    	MoneyLabel.setText("總花費金額: " + aquarium.getCost().getTotalCost());
    	MoneyLabel.setBounds(25, 640, 200, 20);
    	MoneyLabel.setForeground(Color.decode("#4169E1"));
    	MoneyLabel.setFont(new Font(null, Font.BOLD, 20));
    	add(MoneyLabel);
    }
    
    private void setAllPanelVisibleFalse()
    {
    	statusPanel.setVisible(false);
    	eventPanel.setVisible(false);
    	historyPanel.setVisible(false);
    	newfishPanel.setVisible(false);
    	devicePanel.setVisible(false);
    	displayPanel.setVisible(false);
    }
    
    private void resetAllButtonFontColor()
    {
    	statusButton.setForeground(Color.black);
    	eventButton.setForeground(Color.black);
    	historyButton.setForeground(Color.black);
    	newfishButton.setForeground(Color.black);
    	deviceButton.setForeground(Color.black);
    	displayButton.setForeground(Color.black);
    }
    
    public void refresh()
    {
    	statusPanel.refresh();
    	newfishPanel.refresh();
    	devicePanel.refresh();
    	resetAllButtonFontColor();
    	statusButton.setForeground(Color.red);
    	statusPanel.setVisible(true);
    }
    
  	private class ButtonHandler implements  ActionListener
  	{
  		public void actionPerformed(ActionEvent ae) 
        {
  			if(ae.getSource() == statusButton)
  			{
  				resetAllButtonFontColor();
  				statusButton.setForeground(Color.red.brighter());
  				setAllPanelVisibleFalse();
  				statusPanel.setVisible(true);
  				statusPanel.refresh();
  			}
  		    else if(ae.getSource() == eventButton)
            {
  		    	resetAllButtonFontColor();
  		    	eventButton.setForeground(Color.red.brighter());
  		    	setAllPanelVisibleFalse();
  		    	eventPanel.refresh();
  				eventPanel.setVisible(true);
            }
  			else if(ae.getSource() == historyButton)
  			{
  				resetAllButtonFontColor();
  				historyButton.setForeground(Color.red.brighter());
  				setAllPanelVisibleFalse();
  				historyPanel.refresh();
  				historyPanel.setVisible(true);
  			}
  			else if(ae.getSource() == newfishButton)
  			{
  				resetAllButtonFontColor();
  				newfishButton.setForeground(Color.red.brighter());
  				setAllPanelVisibleFalse();
  				newfishPanel.setVisible(true);
  				newfishPanel.refresh();
  			}
  			else if(ae.getSource() == deviceButton)
  			{
  				resetAllButtonFontColor();
  				deviceButton.setForeground(Color.red.brighter());
  				setAllPanelVisibleFalse();
  				devicePanel.refresh();
  				devicePanel.setVisible(true);
  			}
  			else if(ae.getSource() == displayButton)
  			{
  				resetAllButtonFontColor();
  				displayButton.setForeground(Color.red.brighter());
  				setAllPanelVisibleFalse();
  				displayPanel.refresh();
  				displayPanel.setVisible(true);
  			}
        }
  	}
}

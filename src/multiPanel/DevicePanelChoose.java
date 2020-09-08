package multiPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import aquarium.Aquarium;
import imagePanel.ImagePanel;
import jpanelList.DeviceList;

@SuppressWarnings("serial")
public class DevicePanelChoose extends JPanel
{
	private Aquarium aquarium;
	private String[] set = {"0", "4", "8", "12", "16"};
	private int[] sets = {0, 4, 8, 12, 16};
	private final int boxSize = 130;
	private final int space = 10;
	private final int fontSize = 20;
	private String[] itemsL = { "null", "初級餵食器", "中級餵食器", "高級餵食器",
								"null", "初級過濾器", "中級過濾器", "高級過濾器",
								"null", "初級氧氣泵", "中級氧氣泵", "高級氧氣泵",
								"null", "初級加溫器", "中級加溫器", "高級加溫器",
								"null", "初級照明器", "中級照明器", "高級照明器" };
	private int[] Money = {0, 700, 1000, 1300,
						   0, 1000, 2000, 3000,
						   0, 200, 300, 400,
						   0, 200, 400, 600,
						   0, 200, 500, 800};
	private DeviceList fishfeedList = new DeviceList(boxSize, boxSize, space, "   餵食器", Color.BLUE, fontSize);
    private DeviceList waterfilterList = new DeviceList(boxSize, boxSize, space, "   過濾器", Color.BLUE, fontSize);
    private DeviceList deaeratorList = new DeviceList(boxSize, boxSize, space, "   氧氣泵", Color.BLUE, fontSize);
    private DeviceList warmerList = new DeviceList(boxSize, boxSize, space, "   加溫器", Color.BLUE, fontSize);
    private DeviceList lighterList = new DeviceList(boxSize, boxSize, space, "   照明器", Color.BLUE, fontSize);
	private ImagePanel[] itemsImagePanel = { new ImagePanel(itemsL[0]), new ImagePanel(itemsL[1]), 
            						new ImagePanel(itemsL[2]), new ImagePanel(itemsL[3]),
            						new ImagePanel(itemsL[4]), new ImagePanel(itemsL[5]),
            						new ImagePanel(itemsL[6]), new ImagePanel(itemsL[7]),
            						new ImagePanel(itemsL[8]), new ImagePanel(itemsL[9]),
            						new ImagePanel(itemsL[10]), new ImagePanel(itemsL[11]),
            						new ImagePanel(itemsL[12]), new ImagePanel(itemsL[13]),
            						new ImagePanel(itemsL[14]), new ImagePanel(itemsL[15]),
            						new ImagePanel(itemsL[16]), new ImagePanel(itemsL[17]),
            						new ImagePanel(itemsL[18]), new ImagePanel(itemsL[19]) };
	private JPanel[] itemsImageOutPanel = { new JPanel(), new JPanel(), 
								 new JPanel(), new JPanel(),
								 new JPanel(), new JPanel(),
								 new JPanel(), new JPanel(),
								 new JPanel(), new JPanel(),
								 new JPanel(), new JPanel(),
								 new JPanel(), new JPanel(),
								 new JPanel(), new JPanel(),
								 new JPanel(), new JPanel(),
								 new JPanel(), new JPanel() };
	private JPanel listP = new JPanel()
    {
    	public Component add(Component c)
        {
            c.setBounds(5, getComponentCount() * 160 + 10, 720, 150);
            super.add(c);
            if(getHeight() <= getComponentCount() * 160 + 10)
                setPreferredSize(new Dimension(300, getComponentCount() * 160 + 10));
            return c;
        }
    };
    private JButton changeb = new JButton();
    private JScrollPane listS = new JScrollPane(listP);
	private ImagePanel lastChoose = null;
	private JLabel MoneyL;
    private int lastChooses = -1;
    
	public DevicePanelChoose(Aquarium a)
	{
		aquarium = a;
		setLists();
		setListPandS();
		setButton();
	}
	
	public void Money(JLabel moneyL)
	{
		MoneyL = moneyL;
	}
	
	private void setLists()
	{
		setfishfood();
		setwaterfilter();
		setdeaerator();
		setwarmer();
		setlighter();
	}
	
	private void setListPandS()
	{
		listP.setLayout(null);
		listP.setBackground(Color.white);
		listP.add(fishfeedList);
		listP.add(waterfilterList);
		listP.add(deaeratorList);
		listP.add(warmerList);
		listP.add(lighterList);
		
		listS.setBounds(0, 0, 750, 580);
		listS.getVerticalScrollBar().setUnitIncrement(16);
		add(listS);
	}
	
	private void setButton()
	{
		changeb.setText("更換");
		changeb.setFont(new Font(null, Font.BOLD, 16));
		changeb.setBounds(800, 485, 100, 40);
		changeb.addActionListener(bH);
		add(changeb);
	}
	
	private void setfishfood()
	{
		fishfeedList.setLayout(null);
		for(int i = 0; i < 4; i++)
		{
			itemsImagePanel[i].setName("" + i);
			itemsImagePanel[i].setToolTipText(itemsL[i] + "  NT$" + Money[i]);
			itemsImagePanel[i].setBounds(5, 5, 120, 120);
			itemsImageOutPanel[i].setLayout(null);
			itemsImageOutPanel[i].setBackground(Color.white);
			itemsImageOutPanel[i].add(itemsImagePanel[i]);
			fishfeedList.add(itemsImageOutPanel[i]);
			itemsImagePanel[i].addMouseListener(listener);
		}
	}
	private void setwaterfilter()
	{
		waterfilterList.setLayout(null);
		for(int i = 4; i < 8; i++)
		{
			itemsImagePanel[i].setName("" + i);
			itemsImagePanel[i].setToolTipText(itemsL[i] + "  NT$" + Money[i]);
			itemsImagePanel[i].setBounds(5, 5, 120, 120);
			itemsImageOutPanel[i].setLayout(null);
			itemsImageOutPanel[i].setBackground(Color.white);
			itemsImageOutPanel[i].add(itemsImagePanel[i]);
			waterfilterList.add(itemsImageOutPanel[i]);
			itemsImagePanel[i].addMouseListener(listener);
		}
	}
	private void setdeaerator()
	{
		deaeratorList.setLayout(null);
		for(int i = 8; i < 12; i++)
		{
			itemsImagePanel[i].setName("" + i);
			itemsImagePanel[i].setToolTipText(itemsL[i] + "  NT$" + Money[i]);
			itemsImagePanel[i].setBounds(5, 5, 120, 120);
			itemsImageOutPanel[i].setLayout(null);
			itemsImageOutPanel[i].setBackground(Color.white);
			itemsImageOutPanel[i].add(itemsImagePanel[i]);
			deaeratorList.add(itemsImageOutPanel[i]);
			itemsImagePanel[i].addMouseListener(listener);
		}
	}
	private void setwarmer()
	{
		warmerList.setLayout(null);
		for(int i = 12; i < 16; i++)
		{
			itemsImagePanel[i].setName("" + i);
			itemsImagePanel[i].setToolTipText(itemsL[i] + "  NT$" + Money[i]);
			itemsImagePanel[i].setBounds(5, 5, 120, 120);
			itemsImageOutPanel[i].setLayout(null);
			itemsImageOutPanel[i].setBackground(Color.white);
			itemsImageOutPanel[i].add(itemsImagePanel[i]);
			warmerList.add(itemsImageOutPanel[i]);
			itemsImagePanel[i].addMouseListener(listener);
		}
	}
	private void setlighter()
	{
		lighterList.setLayout(null);
		for(int i = 16; i < 20; i++)
		{
			itemsImagePanel[i].setName("" + i);
			itemsImagePanel[i].setToolTipText(itemsL[i]);
			itemsImagePanel[i].setBounds(5, 5, 120, 120);
			itemsImageOutPanel[i].setLayout(null);
			itemsImageOutPanel[i].setBackground(Color.white);
			itemsImageOutPanel[i].add(itemsImagePanel[i]);
			lighterList.add(itemsImageOutPanel[i]);
			itemsImagePanel[i].addMouseListener(listener);
		}
	}
	
	private void resetImage()
	{
		for(int i = 0; i < 20; i++)
		{
			itemsImageOutPanel[i].setBackground(Color.white);
		}
		for(int i = 0; i < 20; i++)
		{
				if(i == aquarium.getDevice().getFeederbuyer() || i == aquarium.getDevice().getFilterbuyer() + 4 || i == aquarium.getDevice().getInflatorbuyer() + 8 || i == aquarium.getDevice().getHeaterbuyer() + 12 || i == aquarium.getDevice().getFlashLightbuyer() + 16)
					itemsImageOutPanel[i].setBackground(Color.red);
		}
		/*
			for(int j = 0; j < 20; j++)
				if(itemsI[j].getName().equals(set[i]))
				{
					itemsII[j].setBackground(Color.red);
					/////////////////////////////////////////////呼叫choose函式
					break;
				}
		*/
		lastChooses = -1;
		lastChoose = null;
	}
	
	private void resetListS()
	{
		JScrollBar a = listS.getVerticalScrollBar();
		a.setValue(a.getMinimum());
		listS.setVisible(true);
	}
	
	public void refresh()
	{
		resetImage();
		resetListS();
	}
	
	private void money(/*int add*/)
	{
		/*///////////////////呼叫函式       金額 +add
		 * MoneyL.setText("");
		 * 
		 */
		MoneyL.setText("總花費金額: " + aquarium.getCost().getTotalCost());
	}
	
	private MouseAdapter listener = new MouseAdapter()
    {
        public void mousePressed(MouseEvent e)
        {
        	ImagePanel pnl=(ImagePanel)e.getSource();
        	System.out.println("Device click:" + pnl.getName());
        	int i;
        	for(i = 0; i < 20; i++)
        	{
        		if(pnl.getName().equals(itemsImagePanel[i].getName()))
        		{
        			break;
        		}
        	}
        	if(i == aquarium.getDevice().getFeederbuyer() || i == aquarium.getDevice().getFilterbuyer() + 4 || i == aquarium.getDevice().getInflatorbuyer() + 8 || i == aquarium.getDevice().getHeaterbuyer() + 12 || i == aquarium.getDevice().getFlashLightbuyer() + 16)
        		return; //已經選用
        	if(lastChooses != -1)
        		itemsImageOutPanel[lastChooses].setBackground(Color.white);
        	lastChoose = pnl;
        	for(i = 0; i < 20; i++)
        	{
        		if(lastChoose.getName().equals(itemsImagePanel[i].getName()))
        		{
        			lastChooses = i;
        			itemsImageOutPanel[i].setBackground(Color.decode("#1E90FF"));
        			break;
        		}
        	}
        }
    };
    
    private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae)
        {
    		if(ae.getSource() == changeb)
    		{
    			if(lastChooses == -1 )
    			{
    				JOptionPane.showMessageDialog(listS, "請先選擇設備", "警告", JOptionPane.WARNING_MESSAGE);
    				return;
    			}
    			int i = lastChooses / 4;
    			int j = lastChooses % 4;
    			if(i == 0)
    			{
    				itemsImageOutPanel[aquarium.getDevice().getFeederbuyer()].setBackground(Color.white);
    				aquarium.getDevice().buyFeeder(j, aquarium.getCost(), aquarium.getTimer());
    			}
    			else if(i == 1)
    			{
    				itemsImageOutPanel[aquarium.getDevice().getFilterbuyer() + 4].setBackground(Color.white);
    				aquarium.getDevice().buyFilter(j, aquarium.getCost(), aquarium.getTimer());
    			}
    			else if(i == 2)
    			{
    				itemsImageOutPanel[aquarium.getDevice().getInflatorbuyer() + 8].setBackground(Color.white);
    				aquarium.getDevice().buyInflator(j, aquarium.getCost(), aquarium.getTimer());
    			}
    			else if(i == 3)
    			{
    				itemsImageOutPanel[aquarium.getDevice().getHeaterbuyer() + 12].setBackground(Color.white);
    				aquarium.getDevice().buyheater(j, aquarium.getCost(), aquarium.getTimer());
    			}
    			else if(i == 4)
    			{
    				itemsImageOutPanel[aquarium.getDevice().getFlashLightbuyer() + 16].setBackground(Color.white);
    				aquarium.getDevice().buyflashLight(j, aquarium.getCost(), aquarium.getTimer());
    			}
    			//itemsII[sets[i]].setBackground(Color.white);
    			itemsImageOutPanel[lastChooses].setBackground(Color.red);
    			//set[i] = itemsI[lastChooses].getName();//
    			//sets[i] = lastChooses;//
    			lastChoose = null;
    			lastChooses = -1;
    			//JOptionPane.showMessageDialog(listS, "購買後要選擇模式\n否則無法啟用", "警告", JOptionPane.WARNING_MESSAGE);
    			money();
    			//
    			//money(Money[lastChooses]);
    			///////////////////////////////////////////////////函式->改參數 finish
    		}
        }
    };
}
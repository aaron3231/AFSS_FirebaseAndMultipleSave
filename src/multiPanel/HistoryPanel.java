package multiPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;

import aquarium.Aquarium;
import database.Fish_data;
import databaseDraw.Draw_line;
import fish.FishCataLog;
import timer.Timerr;

@SuppressWarnings("serial")
public class HistoryPanel extends JPanel
{
	private Aquarium aquarium;
	private JButton daySB = new JButton();
	private JButton monthSB = new JButton();
	private JButton yearSB = new JButton();
	private JButton periodSB = new JButton();
	
	private JPanel idlistP = new JPanel()
    {
    	public Component add(Component c)
        {
            c.setBounds(5, getComponentCount() * 35 + 5, 265, 30);
            super.add(c);
            if(getHeight() <= getComponentCount() * 35 + 5)
                setPreferredSize(new Dimension(265, getComponentCount() * 35 + 5));
            return c;
        }
    };
    private JScrollPane idlistS = new JScrollPane(idlistP);
    
    private JButton lengthB = new JButton();
    private JButton satiationB = new JButton();
    private JButton livelyB = new JButton();
    
    private JButton searchB = new JButton();
    
    private JPanel lastChoose = null;
    private int time = -1;
    private String[] Time = {"Length", "Satiation", "Lively"};
    private String choose = null;
    private String[] Choose = {"Length", "Satiation", "Lively"};
    
    private JTextField[] date = { new JTextField(), new JTextField(), new JTextField(),
    							  new JTextField(), new JTextField(), new JTextField() };
    private JLabel[] dateL = { new JLabel("年"), new JLabel("月"), new JLabel("日"),
			  				   new JLabel("年"), new JLabel("月"), new JLabel("日") };
    private JLabel dash = new JLabel("|");
    
    private JPanel test = new JPanel();
    private JPanel[] fishid = {new JPanel(), new JPanel(), new JPanel(), new JPanel()};
    JLabel fishtext;
    
    private JComboBox combo = new JComboBox();
    
    //private int idCount;////////////////////////////
    
    private ChartPanel draw;
    
	public HistoryPanel(Aquarium a)
	{
		aquarium = a;
		setButton();
		setlistP();
		setlistS();
		setdate();
		setdraw();
	}
	
	private void setidCount()
	{
		//////////////////////////////////////拿id數
		//idCount = 3;
	}
	
	private void setButton()
	{
		daySB.setBounds(20, 0, 120, 25);
		daySB.setText("歷史單日查詢");
	    daySB.setFont(new Font(null, Font.BOLD, 12));
	    daySB.addActionListener(bH);
	    add(daySB);
	    monthSB.setBounds(160, 0, 120, 25);
	    monthSB.setText("歷史單月查詢");
	    monthSB.setFont(new Font(null, Font.BOLD, 12));
	    monthSB.addActionListener(bH);
	    add(monthSB);
	    yearSB.setBounds(20, 45, 120, 25);
	    yearSB.setText("歷史單年查詢");
	    yearSB.setFont(new Font(null, Font.BOLD, 12));
	    yearSB.addActionListener(bH);
	    add(yearSB);
	    periodSB.setBounds(160, 45, 120, 25);
	    periodSB.setText("特定期間查詢");
	    periodSB.setFont(new Font(null, Font.BOLD, 12));
	    periodSB.addActionListener(bH);
	    add(periodSB);
	    lengthB.setBounds(20, 320, 80, 25);
	    lengthB.setText("身長");
	    lengthB.setFont(new Font(null, Font.BOLD, 12));
	    lengthB.addActionListener(bH);
	    add(lengthB);
	    satiationB.setBounds(110, 320, 80, 25);
	    satiationB.setText("飽食度");
	    satiationB.setFont(new Font(null, Font.BOLD, 12));
	    satiationB.addActionListener(bH);
	    add(satiationB);
	    livelyB.setBounds(200, 320, 80, 25);
	    livelyB.setText("生命力");
	    livelyB.setFont(new Font(null, Font.BOLD, 12));
	    livelyB.addActionListener(bH);
	    add(livelyB);
	    searchB.setBounds(225, 520, 70, 40);
	    searchB.setText("查詢");
	    searchB.setFont(new Font(null, Font.BOLD, 15));
	    searchB.addActionListener(bH);
	    add(searchB);
	}
	
	private void setlistP()
	{
		idlistP.setLayout(null);
		idlistP.setBackground(Color.black);
	}
	
	private void setlistS()
	{
		idlistS.setBounds(20, 100, 280, 200);
		idlistS.getVerticalScrollBar().setUnitIncrement(16);//滾輪速度
        add(idlistS);
	}
	
	private void setdate()
	{
		for(int i = 0; i < 6; i++)
		{
			dateL[i].setBounds(50 + ( i % 3) * 100, 380 + 70*( i / 3), 30, 30);
			date[i].setBounds(( i % 3) * 100 + 20, 380 + 70*( i / 3), 30, 30);
			if(i < 3)
			{
				add(dateL[i]);
				add(date[i]);
			}
		}
		dash.setBounds(132, 415, 30, 30);
	}
	
	private void addSecdate()
	{
		setVisible(false);
		for(int i = 3; i < 6; i++)
		{
			add(dateL[i]);
			add(date[i]);
		}
		add(dash);
		setVisible(true);
	}
	
	private void removeSecdate()
	{
		setVisible(false);
		for(int i = 3; i < 6; i++)
		{
			remove(dateL[i]);
			remove(date[i]);
		}
		remove(dash);
		setVisible(true);
	}
	
	private void setdraw()
	{
		/*/////////////////////////////////////
		 * setBounds();
		 * 
		 */
	}
	
	private void insertList()////////////////格式
	{
		idlistP.removeAll();
		idlistP.setVisible(false);
		Fish_data  fish=new Fish_data();
		String []get_id=new String [fish.CountFishNum()];
		fish.SelectFIshID(get_id);
		for(int i = 0; i < fish.CountFishNum(); i++)
        {
			JPanel idlist = new JPanel();
			idlist.setLayout(null);
			idlist.setBackground(Color.white);
			//呼叫 ?
			//呼叫 塞圖
			//呼叫 資料
			JLabel id = new JLabel();
			id.setBounds(50, 0, 100, 40);
			id.setText("ID:  " + get_id[i]);
			idlist.add(id);
			///////////////////塞IDname
			idlist.setName(get_id[i]);
			idlistP.add(idlist);
			idlist.addMouseListener(mH);
        }
		/*
		for(int i = fishCount; i < 20; i++)
		{
			fishlist[i].removeAll();
			fishlist[i].setLayout(null);
			fishlist[i].setBackground(Color.white);
			//呼叫 塞圖(骨頭)
			fishlistT[i].setText("(空的)");
			fishlistT[i].setBounds(130,5,100,35);
			fishlist[i].add(fishlistT[i]);
			fishlist[i].setName("" + i);
			statuslistP.add(fishlist[i]);
		}
		*/
		idlistP.setVisible(true);
	}
	
	private void resetTimeButton()
	{
		daySB.setForeground(Color.black);
	    monthSB.setForeground(Color.black);
	    yearSB.setForeground(Color.black);
	    periodSB.setForeground(Color.black);
	}
	
	private void resetChooseButton()
	{
		lengthB.setForeground(Color.black);
	    satiationB.setForeground(Color.black);
	    livelyB.setForeground(Color.black);
	}
	
	public void refresh()
	{
		/*
		 * remove(draw);/////?
		 */
		//remove(draw);
		draw = null;
		//setidCount();
		insertList();
		resetTimeButton();
		resetChooseButton();
		removeSecdate();
		lastChoose = null;
		time = -1;
		choose = null;
	}
	
	private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae) 
        {
    		if(ae.getSource() == daySB)
    		{
    			resetTimeButton();
    			removeSecdate();
    			daySB.setForeground(Color.decode("#008B8B"));
    			time = 1;
    		}
    		else if(ae.getSource() == monthSB)
    		{
    			resetTimeButton();
    			removeSecdate();
    			monthSB.setForeground(Color.decode("#008B8B"));
    			time = 2;
    		}
    		else if(ae.getSource() == yearSB)
    		{
    			resetTimeButton();
    			removeSecdate();
    			yearSB.setForeground(Color.decode("#008B8B"));
    			time = 3;
    		}
    		else if(ae.getSource() == periodSB)
    		{
    			resetTimeButton();
    			addSecdate();
    			periodSB.setForeground(Color.decode("#008B8B"));
    			time = 4;
    		}
    		else if(ae.getSource() == lengthB)
    		{
    			resetChooseButton();
    			lengthB.setForeground(Color.decode("#B8860B"));
    			choose = "Length";
    		}
    		else if(ae.getSource() == satiationB)
    		{
    			resetChooseButton();
    			satiationB.setForeground(Color.decode("#B8860B"));
    			choose = "Satiation";
    		}
    		else if(ae.getSource() == livelyB)
    		{
    			resetChooseButton();
    			livelyB.setForeground(Color.decode("#B8860B"));
    			choose = "Lively";
    		}
    		else if(ae.getSource() == searchB)
    		{
    			/*///////////draw = (函式);//////////////////////add(draw);//////沒防爆
    			 *
    			 */

    			//setVisible(false);
    			String[] dates = {"", ""};
    			if(time == 1)
    			{
    				dates[0] = Timerr.toDateRange(date[0].getText(), date[1].getText(), date[2].getText(), Timerr.speedUpTime.DAY)[0];
    				dates[1] = Timerr.toDateRange(date[0].getText(), date[1].getText(), date[2].getText(), Timerr.speedUpTime.DAY)[1];
    			}
    			else if(time == 2)
    			{
    				dates[0] = Timerr.toDateRange(date[0].getText(), date[1].getText(), "1", Timerr.speedUpTime.MONTH)[0];
    				dates[1] = Timerr.toDateRange(date[0].getText(), date[1].getText(), "1", Timerr.speedUpTime.MONTH)[1];
    			}
    			else if(time == 3)
    			{
    				dates[0] = Timerr.toDateRange(date[0].getText(), "1", "1", Timerr.speedUpTime.YEAR)[0];
    				dates[1] = Timerr.toDateRange(date[0].getText(), "1", "1", Timerr.speedUpTime.YEAR)[1];
    			}
    			else if(time == 4)
    			{
    				dates[0] = Timerr.toDateRange(date[0].getText(), date[1].getText(), date[2].getText(), Timerr.speedUpTime.DAY)[0];
    				dates[1] = Timerr.toDateRange(date[3].getText(), date[4].getText(), date[5].getText(), Timerr.speedUpTime.DAY)[0];
    			}
    			System.out.println(Integer.parseInt(lastChoose.getName()) + "," + choose + "," + dates[0] + "," + dates[1]);
    		
    			try {
					draw = new Draw_line(Integer.parseInt(lastChoose.getName()), choose, dates[0], dates[1]).getChartPanel_Simple();
				} catch (ParseException e) {
					// TODO 自動產生的 catch 區塊
					e.printStackTrace();
				}
    			draw.setBounds(10, 10, 600, 500);
    			//add(draw);
    			JDialog dialog = new JDialog();
    			dialog.setLayout(null);
    			dialog.setTitle("");
    			dialog.setBounds(650, 150, 650, 560);
    			dialog.add(draw);
    			dialog.setModal(true);
  				dialog.setVisible(true);
  				dialog.dispose();
    			
    			//setVisible(true);
    		}
        }
    };
    		
    private MouseAdapter mH = new MouseAdapter()
    {
        public void mousePressed(MouseEvent e)
        {
            JPanel pnl = (JPanel)e.getSource();
            //將前一次的底色還原
            if(lastChoose != null)
            	lastChoose.setBackground(Color.white);
            pnl.setBackground(Color.decode("#AFEEEE"));
            System.out.println("click fish id:"+pnl.getName());
            lastChoose = pnl;//標記起來，作為下一次切換時，還原底色使用
        }
    };
}

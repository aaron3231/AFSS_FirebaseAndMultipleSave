package multiPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import aquarium.Aquarium;
import database.Event_data;
import timer.Timerr;
import timer.Timerr.speedUpTime;
import userName.UserName;

@SuppressWarnings("serial")
public class EventPanel extends JPanel
{
	private Aquarium aquarium;
	//建立清單
	private JPanel eventlistP = new JPanel()
    {
    	public Component add(Component c)
        {
            c.setBounds(5,getComponentCount()*55+5,820,50);
            super.add(c);
            if(getHeight()<=getComponentCount()*55+5)
                setPreferredSize(new Dimension(300, getComponentCount()*55+5));
            return c;
        }
    };
    //建立滾軸
    private JScrollPane eventSP = new JScrollPane(eventlistP);
    //
    private static int eventSize = 0;
    //
    
    public EventPanel(Aquarium a)
    {
    	aquarium = a;
    	setScrollpane();
    	setList();
    }
    
    private void setScrollpane()
    {
    	eventSP.setBounds(70, 30 , 845, 580);
        eventSP.getVerticalScrollBar().setUnitIncrement(16);//滾輪速度
        add(eventSP);
    }
    
    private void setList()
    {
    	eventlistP.setBackground(Color.decode("#D2B48C"));
        eventlistP.setLayout(null);
    }
    
    private void insertList()
    {
    	eventlistP.removeAll();
    	eventlistP.setVisible(false);
    	//寫入資料 
    	String[] array = aquarium.getTimer().toStringToDB().split("/");
    	array[2]=array[2].split(" ")[0];
    	/*
    	System.out.println();
    	System.out.println(array[0]);
    	System.out.println(array[1]);
    	System.out.println(array[2]);
    	System.out.println();
    	*/
    	String[] range=Timerr.toPastDateRange(array[0], array[1], array[2], speedUpTime.WEEK);
    	//System.out.println(range[0] + "    "+ range[1]);
    	Event_data event= new Event_data();
    	String[] get_describe_type = new String[500];
 	    String[] get_description = new String[500];
 	    String[] get_date_description= new String[500];
 	    event.SelectType(get_describe_type,range[0],range[1]);
	    event.SelectDescription(get_description,range[0],range[1]);
	    event.SelectDateDescription(get_date_description,range[0],range[1]);
	    System.out.print(get_date_description[0]);
	    int eventCount = event.getDataCount(range[0],range[1]);
    //	System.out.print(range[0] + range[1]);
        for(int i = eventCount-1; i > 0; i--)
        {
        	JPanel pnl=new JPanel();
            pnl.setLayout(null);
            pnl.setBackground(Color.white);
            JLabel lab=new JLabel(get_date_description[i] + "點              " + get_describe_type[i]);
            //   System.out.print(get_describe_type[i] + get_description[i]);
            lab.setBounds(50,5,200,35);//,35
            pnl.add(lab);
            JLabel pn = new JLabel(get_description[i]);
            pn.setBounds(275, 5, 500, 35);
            pnl.add(pn);
            eventlistP.add(pnl);
        }
        eventlistP.setVisible(true);
    }
    
    private void reseteventSP()
    {
    	JScrollBar a = eventSP.getVerticalScrollBar();
		a.setValue(a.getMinimum());
    }
    
    public void refresh()
    {
    	insertList();
    	reseteventSP();
    }
}
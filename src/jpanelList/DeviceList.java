package jpanelList;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DeviceList extends JPanel
{
	private int weight;
	private int height;
	private int interval;
	private String name;
	private Color color;
	private int fontSize;
	
	public DeviceList(int weight, int height, int interval, String name, Color color, int fontSize)
	{
		this.weight = weight;
		this.height = height;
		this.interval = interval;
		this.name = name;
		this.color = color;
		this.fontSize = fontSize;
		setName();
	}
	public Component add(Component c)
    {
        c.setBounds(getComponentCount() * (weight + interval) + interval, interval, weight, height); //getComponentCount()*140+10,10,130,110
        super.add(c);
        if(getWidth() <= getComponentCount() * (weight + interval) + interval)
            setPreferredSize(new Dimension(getComponentCount() * (weight + interval), height + 2 * interval));
        return c;
    }
	
	private void setName()
	{
		JLabel namelabel = new JLabel(name);
		namelabel.setFont(new Font(null, Font.BOLD, fontSize));
		namelabel.setForeground(color);
		add(namelabel);
	}
}

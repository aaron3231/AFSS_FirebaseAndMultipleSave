package multiPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aquarium.Aquarium;

@SuppressWarnings("serial")
public class DevicePanel extends JPanel
{
	private Aquarium aquarium;
	private DevicePanelChoose chooseP;
    private DevicePanelModule moduleP;
    private JButton chooseB = new JButton();
    private JButton moduleB = new JButton();

	public DevicePanel(Aquarium a)
	{
		aquarium = a;
		chooseP = new DevicePanelChoose(aquarium);
		moduleP = new DevicePanelModule(aquarium);
		setPanel();
		setButton();
	}
	
	public void Money(JLabel MoneyL)
	{
		chooseP.Money(MoneyL);
	}
	
	private void setPanel()
	{
		chooseP.setBounds(25, 50, 950, 600);
		chooseP.setLayout(null);
		add(chooseP);
		moduleP.setBounds(25, 50, 950, 600);
		moduleP.setLayout(null);
		moduleP.setVisible(false);
		add(moduleP);
	}
	
	private void setButton()
	{
		chooseB.setText("選擇設備");
		chooseB.setFont(new Font(null, Font.BOLD, 12));
		chooseB.setBounds(25, 0, 100, 30);
		chooseB.addActionListener(bH);
		add(chooseB);
		
		moduleB.setText("選擇模式");
		moduleB.setFont(new Font(null, Font.BOLD, 12));
		moduleB.setBounds(145, 0, 100, 30);
		moduleB.addActionListener(bH);
		add(moduleB);
	}
	
	private void setALLP()
	{
		chooseP.setVisible(false);
		moduleP.setVisible(false);
	}
	
	private void setALLB()
	{
		chooseB.setForeground(Color.black);
    	moduleB.setForeground(Color.black);
	}
	
	public void refresh()
	{
		setALLP();
		setALLB();
		chooseB.setForeground(Color.decode("#FF7F50"));
		chooseP.refresh();
		moduleP.refresh();
		chooseP.setVisible(true);
	}
	
	private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae)
    	{
    		if(ae.getSource() == chooseB)
    		{
    			setALLP();
    			setALLB();
    			chooseP.refresh();
    			chooseB.setForeground(Color.decode("#FF7F50"));
    			chooseP.setVisible(true);
    		}
    		else if(ae.getSource() == moduleB)
    		{
    			setALLP();
    			setALLB();
    			moduleP.refresh();
    			moduleB.setForeground(Color.decode("#FF7F50"));
    			moduleP.setVisible(true);
    		}
        }
    };
}
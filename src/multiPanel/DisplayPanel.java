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
public class DisplayPanel extends JPanel
{	
	private Aquarium aquarium;
	private DisplayPanelChoose chooseP;
	private DisplayPanelBuy buyP;
	private JButton chooseB = new JButton();
	private JButton buyB = new JButton();
	
	public DisplayPanel(Aquarium a)
	{
		aquarium = a;
		chooseP = new DisplayPanelChoose(aquarium);
		buyP = new DisplayPanelBuy(aquarium);
		setPanel();
		setButton();
	}
	/*
	public void setSize(int Size)/////////////////不需要?
	{
		chooseP.setSize(Size);
	}
	*/
	public void Money(JLabel MoneyL)
	{
		buyP.Money(MoneyL);
	}
	
	private void setPanel()
	{
		chooseP.setBounds(25, 50, 950, 600);
		chooseP.setLayout(null);
		add(chooseP);
		buyP.setBounds(25, 50, 950, 600);
		buyP.setLayout(null);
		buyP.setVisible(false);
		add(buyP);
	}
	
	private void setButton()
	{
		chooseB.setText("選擇擺設");
		chooseB.setFont(new Font(null, Font.BOLD, 12));
		chooseB.setBounds(25, 0, 100, 30);
		chooseB.addActionListener(bH);
		add(chooseB);
		
		buyB.setText("購買");
		buyB.setFont(new Font(null, Font.BOLD, 12));
		buyB.setBounds(145, 0, 100, 30);
		buyB.addActionListener(bH);
		add(buyB);
	}
	
	private void setALLP()
	{
		chooseP.setVisible(false);
		buyP.setVisible(false);
	}
	
	private void setALLB()
	{
		chooseB.setForeground(Color.black);
    	buyB.setForeground(Color.black);
	}
	
	public void refresh()
	{
		setALLP();
		setALLB();
		chooseB.setForeground(Color.decode("#D2691E"));
		chooseP.refresh();
		buyP.refresh();
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
  				chooseB.setForeground(Color.decode("#D2691E"));
  				chooseP.refresh();
  				chooseP.setVisible(true);
  			}
  			else if(ae.getSource() == buyB)
  			{
  				setALLP();
  				setALLB();
  				buyB.setForeground(Color.decode("#D2691E"));
  				buyP.refresh();
  				buyP.setVisible(true);
  			}
        }
  	};
}
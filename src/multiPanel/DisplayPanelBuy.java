package multiPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import aquarium.Aquarium;

@SuppressWarnings("serial")
public class DisplayPanelBuy extends JPanel
{
	private Aquarium aquarium;
	private JLabel MoneyL;
	private JTextArea waterplanetT = new JTextArea("水草");
	private JLabel waterplanetL = new JLabel();
	private JButton buywaterplanetB = new JButton("購買");
	private JTextArea stoneT = new JTextArea("稜角石");
	private JLabel stoneL = new JLabel();
	private JButton buystoneB = new JButton("購買");
	private JTextArea coralT = new JTextArea("假珊瑚");
	private JLabel coralL = new JLabel();
	private JButton buycoralB = new JButton("購買");
	private JTextArea woodT = new JTextArea("沉木");
	private JLabel woodL = new JLabel();
	private JButton buywoodB = new JButton("購買");
	private JLabel totalL = new JLabel("目前數量");

	public DisplayPanelBuy(Aquarium a)
	{
		aquarium = a;
		setTextArea();
		setLabel();
		setButton();
		setMount();
	}
	
	public void Money(JLabel moneyL)
	{
		MoneyL = moneyL;
	}
	
	private void setTextArea()
	{
		waterplanetT.setFont(new Font(null, Font.BOLD, 20));
		waterplanetT.setForeground(Color.decode("#483D8B"));
		waterplanetT.setBounds(230, 100, 100, 50);
		waterplanetT.setEditable(false);
		waterplanetT.setBackground(null);
		add(waterplanetT);
		stoneT.setFont(new Font(null, Font.BOLD, 20));
		stoneT.setForeground(Color.decode("#483D8B"));
		stoneT.setBounds(230, 200, 100, 50);
		stoneT.setEditable(false);
		stoneT.setBackground(null);
		add(stoneT);
		coralT.setFont(new Font(null, Font.BOLD, 20));
		coralT.setForeground(Color.decode("#483D8B"));
		coralT.setBounds(230, 300, 100, 50);
		coralT.setEditable(false);
		coralT.setBackground(null);
		add(coralT);
		woodT.setFont(new Font(null, Font.BOLD, 20));
		woodT.setForeground(Color.decode("#483D8B"));
		woodT.setBounds(230, 400, 100, 50);
		woodT.setEditable(false);
		woodT.setBackground(null);
		add(woodT);
	}
	
	private void setLabel()
	{
		waterplanetL.setFont(new Font(null, Font.BOLD, 18));
		waterplanetL.setBounds(425, 65, 100, 100);
		waterplanetL.setBackground(null);
		add(waterplanetL);
		stoneL.setFont(new Font(null, Font.BOLD, 18));
		stoneL.setBounds(425, 165, 100, 100);
		stoneL.setBackground(null);
		add(stoneL);
		coralL.setFont(new Font(null, Font.BOLD, 18));
		coralL.setBounds(420, 265, 100, 100);
		coralL.setBackground(null);
		add(coralL);
		woodL.setFont(new Font(null, Font.BOLD, 18));
		woodL.setBounds(420, 365, 100, 100);
		woodL.setBackground(null);
		add(woodL);
		totalL.setFont(new Font(null, Font.BOLD, 25));
		totalL.setForeground(Color.decode("#8B008B"));
		totalL.setBounds(390, 0, 150, 50);
		totalL.setBackground(null);
		add(totalL);
	}
	
	private void setButton()
	{
		buywaterplanetB.setFont(new Font(null, Font.BOLD, 12));
		buywaterplanetB.setToolTipText("+20元");
		buywaterplanetB.setBounds(600, 100, 100, 25);
		buywaterplanetB.addActionListener(bH);
		add(buywaterplanetB);
		buystoneB.setFont(new Font(null, Font.BOLD, 12));
		buystoneB.setToolTipText("+10元");
		buystoneB.setBounds(600, 200, 100, 25);
		buystoneB.addActionListener(bH);
		add(buystoneB);
		buycoralB.setFont(new Font(null, Font.BOLD, 12));
		buycoralB.setToolTipText("+50元");
		buycoralB.setBounds(600, 300, 100, 25);
		buycoralB.addActionListener(bH);
		add(buycoralB);
		buywoodB.setFont(new Font(null, Font.BOLD, 12));
		buywoodB.setToolTipText("+100元");
		buywoodB.setBounds(600, 400, 100, 25);
		buywoodB.addActionListener(bH);
		add(buywoodB);
	}
	
	private void setMount()
	{
		//////////////////////////////////////呼叫函式
		waterplanetL.setText(aquarium.getLandSpace().getWaterPlantQuantityQuantity()+"");
		stoneL.setText(aquarium.getLandSpace().getStoneQuantity()+"");
		coralL.setText(aquarium.getLandSpace().getCoralQuantityQuantity() + "/3");
		woodL.setText(aquarium.getLandSpace().getShenmuQuantityQuantity() + "/2");
	}
	
	private void money()
	{
		/*///////////////////呼叫函式       金額 +add
		 * MoneyL.setText("");
		 * 
		 * 
		 */
		MoneyL.setText("總花費金額: " + aquarium.getCost().getTotalCost());
	}
	
	public void refresh()
	{
		setMount();
	}
	
	private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae)
        {
    		if(ae.getSource() == buywaterplanetB)
    		{
    			///////////////呼叫函式 +水草數量
    			aquarium.getLandSpace().buyWaterPlant(1, aquarium.getCost(), aquarium.getTimer());
    			setMount();
    			money();
    		}
    		else if(ae.getSource() == buystoneB)
    		{
    			///////////////呼叫函式+石頭數量+
    			aquarium.getLandSpace().buyStone(1, 2, aquarium.getCost(), aquarium.getTimer());
    			setMount();
    			money();
    		}
    		else if(ae.getSource() == buycoralB)
    		{
    			///////////////呼叫函式+珊瑚數量
    			//JOptionPane.showMessageDialog(buycoralB,"超過上限!!!","警告", JOptionPane.WARNING_MESSAGE);
    			aquarium.getLandSpace().buyCoral(1, aquarium.getCost(), aquarium.getTimer());
    			setMount();
    			money();
    		}
    		else  //wood
    		{
    			///////////////呼叫函式+浮木數量
    			//JOptionPane.showMessageDialog(buywoodB,"超過上限!!!","警告", JOptionPane.WARNING_MESSAGE);
    			aquarium.getLandSpace().buyShenmu(1, aquarium.getCost(), aquarium.getTimer());
    			setMount();
    			money();
    		}
        }
    };
}

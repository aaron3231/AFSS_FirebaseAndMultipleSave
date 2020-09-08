package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SpeedDialog extends JDialog
{
	private JLabel speedLabel = new JLabel("��ܥ[�t�ɶ�:");
	
	private JButton hourButton = new JButton("�@�p��");
	private JButton dayButton = new JButton("�@��");
	private JButton weekButton = new JButton("�@��§��");
	
	private JLabel eventLabel = new JLabel("��ܭn���L���ƥ�:");
	
	private JCheckBox[] eventCheckBox = { new JCheckBox("�����["), new JCheckBox("�۵M���`"), new JCheckBox("�D�۵M���`"),
								 new JCheckBox("���ͯf"), new JCheckBox("���ͪ��̤ܳj"), new JCheckBox("�������a��"),
								 new JCheckBox("�L�o���a��"), new JCheckBox("����a��"), new JCheckBox("�ө����a��"),
								 new JCheckBox("�[�ž��a��"), new JCheckBox("���褣�}"), new JCheckBox("�����V�|"),
								 new JCheckBox("�ūװ���"), new JCheckBox("�ū׹L��"), new JCheckBox("�ūװ��C"),
								 new JCheckBox("�ū׹L�C"), new JCheckBox("�t��q���C"), new JCheckBox("�t��q�L�C"),
								 new JCheckBox("�t��q���C"), new JCheckBox("����") };
	
	private JButton confirmButton = new JButton("�T�{");
	private JButton cancelButton = new JButton("����");
	
	private final int LEFT = 80;
	private final int TOP = 130;
	private final int WEIGHT = 120;
	private final int HEIGHT = 20;
	private final int LSPACE = 120;
	private final int TSPACE = 30;
	
	private int[] recordChoose = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			   			 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
 	
	public SpeedDialog()
	{
		setDialog();
		setLabel();
		setTime();
		setEvent();
	}
	
	private void setDialog()
	{
		setTitle("�[�t�ɶ�");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private void setLabel()
	{
		speedLabel.setBounds(30, 15, 100, 25);
		add(speedLabel);
		eventLabel.setBounds(30, 95, 120, 25);
		add(eventLabel);
	}
	
	private void setTime()
	{
		hourButton.setBounds(80, 55, 100, 25);
		hourButton.addActionListener(bH);
		add(hourButton);
		dayButton.setBounds(190, 55, 100, 25);
		dayButton.addActionListener(bH);
		add(dayButton);
		weekButton.setBounds(300, 55, 100, 25);
		weekButton.addActionListener(bH);
		add(weekButton);
		confirmButton.setBounds(270, 340, 75, 25);
		confirmButton.addActionListener(bH);
		add(confirmButton);
		cancelButton.setBounds(370, 340, 75, 25);
		add(cancelButton);
		cancelButton.addActionListener(bH);
	}
	
	private void setEvent()
	{
		for(int i = 0; i < 20; i++)
		{
			eventCheckBox[i].setBounds(LEFT + LSPACE * (i % 3), TOP + TSPACE * (i / 3), WEIGHT, HEIGHT);
			add(eventCheckBox[i]);
			eventCheckBox[i].addActionListener(bH);
		}
		eventCheckBox[19].setForeground(Color.red.brighter());
		eventCheckBox[19].addActionListener(bH);
	}
	
	private void resetTimeButton()
	{
		hourButton.setForeground(Color.black);
		dayButton.setForeground(Color.black);
		weekButton.setForeground(Color.black);
	}
	
	public int[] result()
	{
		return recordChoose;
	}
	
	private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae) 
        {
    		if(ae.getSource() == hourButton)
    		{
    			resetTimeButton();
    			hourButton.setForeground(Color.blue);
    			recordChoose[0] = 1;
    		}
    		else if(ae.getSource() == dayButton)
    		{
    			resetTimeButton();
    			dayButton.setForeground(Color.blue);
    			recordChoose[0] = 2;
    		}
    		else if(ae.getSource() == weekButton)
    		{
    			resetTimeButton();
    			weekButton.setForeground(Color.blue);
    			recordChoose[0] = 3;
    		}
    		else if(ae.getSource() == confirmButton)
    		{
    			for(int i = 0; i < 19; i++)
    			{
    				if(eventCheckBox[i].isSelected())
    					recordChoose[i + 1] = 1;
    			}
    			setVisible(false);
    		}
    		else if(ae.getSource() == cancelButton)
    		{
    			recordChoose[0] = 0;
    			setVisible(false);
    		}
    		else if(ae.getSource() == eventCheckBox[19])
    		{
    			if(eventCheckBox[19].isSelected())
    			{
    				for(int i = 0; i < 19; i++)
    				{
    					eventCheckBox[i].setSelected(true);
    					recordChoose[i + 1] = 1;
    				}
    			}
    			else
    			{
    				for(int i = 0; i < 19; i++)
    				{
    					eventCheckBox[i].setSelected(false);
    					recordChoose[i + 1] = 0;
    				}
    			}
    		}
    		else
    		{
    			for(int i = 0; i < 19; i++)
    			{
    				if(!eventCheckBox[i].isSelected())
    				{
    					eventCheckBox[19].setSelected(false);
    					break;
    				}
    			}
    		}
        }
    };
}

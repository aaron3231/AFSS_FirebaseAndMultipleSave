package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NewDialog extends JDialog
{
	//確認
	JButton confirmButton = new JButton();
	JButton cancelButton = new JButton();
	
	JLabel fishtankLabel = new JLabel();
	JLabel environmentLabel = new JLabel();
	JLabel saveNameLabel = new JLabel();
	
	JLabel fishtankWarningLabel = new JLabel();
	JLabel environmentWarningLabel = new JLabel();
	JLabel nameWarningLabel = new JLabel();
	
	JButton twoInchSizeButton = new JButton();
	JButton threeInchSizeButton = new JButton();
	JButton fourInchSizeButton = new JButton();
	JButton freshwaterButton = new JButton();
	JButton seawaterButton = new JButton();
	
	JTextField saveNameTextField = new JTextField();
	
	private int[] recordChoose = new int[2];
	
	private int fishtankChoose = -1;
	private int environmentChoose = -1;
	
	public NewDialog()
	{
		setDialog();
		setButton();
		setLabel();
		setChoose();
	}
	
	private void setDialog()
	{
		setTitle("開新模擬");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	private void setButton()
	{
		confirmButton.setText("確認");
		cancelButton.setText("取消");
		confirmButton.setFont(new Font(null, Font.BOLD, 12));
		cancelButton.setFont(new Font(null, Font.BOLD, 12));
		confirmButton.setBounds(220, 300, 100, 30); //280, 430, 100, 50
		cancelButton.setBounds(340, 300, 100, 30);
		confirmButton.addActionListener(bH);
		cancelButton.addActionListener(bH);
		add(confirmButton);
		add(cancelButton);
	}
	
	private void setLabel()
	{
		fishtankLabel.setText("選擇魚缸:");
		environmentLabel.setText("選擇水:");
		saveNameLabel.setText("輸入存檔名稱:");
		fishtankLabel.setFont(new Font(null, Font.BOLD, 15));
		environmentLabel.setFont(new Font(null, Font.BOLD, 15));
		saveNameLabel.setFont(new Font(null, Font.BOLD, 15));
		fishtankLabel.setBounds(40, 50, 100, 50);
		environmentLabel.setBounds(40, 120, 100, 50);
		saveNameLabel.setBounds(40, 190, 100, 50);
		add(fishtankLabel);
		add(environmentLabel);
		add(saveNameLabel);
		
		fishtankWarningLabel.setText("尚未選擇魚缸!!");
		environmentWarningLabel.setText("尚未選擇水!!");
		nameWarningLabel.setText("存檔名稱不可為空");
		fishtankWarningLabel.setFont(new Font(null, Font.BOLD, 17));
		environmentWarningLabel.setFont(new Font(null, Font.BOLD, 17));
		nameWarningLabel.setFont(new Font(null, Font.BOLD, 15));
		fishtankWarningLabel.setForeground(Color.red);
		environmentWarningLabel.setForeground(Color.red);
		nameWarningLabel.setForeground(Color.red);
		fishtankWarningLabel.setBounds(60, 260, 150, 25);
		environmentWarningLabel.setBounds(60, 285, 150, 25);
		nameWarningLabel.setBounds(180, 230, 150, 25);
		fishtankWarningLabel.setVisible(false);
		environmentWarningLabel.setVisible(false);
		nameWarningLabel.setVisible(false);
		add(fishtankWarningLabel);
		add(environmentWarningLabel);
		add(nameWarningLabel);
	}
	
	private void setChoose()
	{
		twoInchSizeButton.setText("2尺缸");
		threeInchSizeButton.setText("3尺缸");
		fourInchSizeButton.setText("4尺缸");
		freshwaterButton.setText("淡水");
		seawaterButton.setText("海水");
		twoInchSizeButton.setFont(new Font(null, Font.BOLD, 15));
		threeInchSizeButton.setFont(new Font(null, Font.BOLD, 15));
		fourInchSizeButton.setFont(new Font(null, Font.BOLD, 15));
		freshwaterButton.setFont(new Font(null, Font.BOLD, 15));
		seawaterButton.setFont(new Font(null, Font.BOLD, 15));
		twoInchSizeButton.setBounds(150, 50, 100, 50);
		threeInchSizeButton.setBounds(260, 50, 100, 50);
		fourInchSizeButton.setBounds(370, 50, 100, 50);
		freshwaterButton.setBounds(150, 120, 100, 50);
		seawaterButton.setBounds(260, 120, 100, 50);
		twoInchSizeButton.addActionListener(bH);
		threeInchSizeButton.addActionListener(bH);
		fourInchSizeButton.addActionListener(bH);
		freshwaterButton.addActionListener(bH);
		seawaterButton.addActionListener(bH);
		add(twoInchSizeButton);
		add(threeInchSizeButton);
		add(fourInchSizeButton);
		add(freshwaterButton);
		add(seawaterButton);
		
		saveNameTextField.setText("");
		saveNameTextField.setFont(new Font(null, Font.BOLD, 15));
		saveNameTextField.setBounds(170, 200, 150, 30);
		add(saveNameTextField);
	}
	
	private void resetChoose(int c)
	{
		resetWarning();
		if(c == 1)
		{
			twoInchSizeButton.setForeground(Color.black);
			threeInchSizeButton.setForeground(Color.black);
			fourInchSizeButton.setForeground(Color.black);
		}
		else
		{
			freshwaterButton.setForeground(Color.black);
			seawaterButton.setForeground(Color.black);
		}
	}
	
	private void resetWarning()
	{
		fishtankWarningLabel.setVisible(false);
		environmentWarningLabel.setVisible(false);
		
	}
	
	public int result()
	{
		if(fishtankChoose != -1 && environmentChoose != -1)
			return fishtankChoose;
		else
			return -1;
	}
	
	public int[] getSet() {
		return recordChoose;
	}

	public void setSet(int[] set) {
		this.recordChoose = set;
	}
	
	public String getName()
	{
		return saveNameTextField.getText();
	}

	private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae) 
        {
    		if(ae.getSource() == confirmButton)
    		{
    			if(fishtankChoose != -1 && environmentChoose != -1 && !saveNameTextField.getText().equals(""))
    			{
    				//確認
    				//呼叫
    				setVisible(false);
    			}
    			else
    			{
    				if(fishtankChoose == -1)
    				{
    					fishtankWarningLabel.setVisible(true);
    				}
    				if(environmentChoose == -1)
    				{
    					environmentWarningLabel.setVisible(true);
    				}
    				if(fishtankChoose != -1 && environmentChoose != -1)
    				{
    					nameWarningLabel.setVisible(true);
    				}
    			}
    		}
    		else if(ae.getSource() == cancelButton)
    		{
    			fishtankChoose = -1;
    			setVisible(false);
    		}
    		else if(ae.getSource() == twoInchSizeButton)
    		{
    			resetChoose(1);
    			twoInchSizeButton.setForeground(Color.blue);
    			fishtankChoose = 0;
    			recordChoose[0]=1;
    		}
    		else if(ae.getSource() == threeInchSizeButton)
    		{
    			resetChoose(1);
    			threeInchSizeButton.setForeground(Color.blue);
    			fishtankChoose = 1;
    			recordChoose[0]=2;
    		}
    		else if(ae.getSource() == fourInchSizeButton)
    		{
    			resetChoose(1);
    			fourInchSizeButton.setForeground(Color.blue);
    			fishtankChoose = 2;
    			recordChoose[0]=3;
    		}
    		else if(ae.getSource() == freshwaterButton)
    		{
    			resetChoose(2);
    			freshwaterButton.setForeground(Color.blue);
    			environmentChoose = 0;
    			recordChoose[1]=1;
    		}
    		else if(ae.getSource() == seawaterButton)
    		{
    			resetChoose(2);
    			seawaterButton.setForeground(Color.blue);
    			environmentChoose = 1;
    			recordChoose[1]=2;
    		}
        }
    };
}
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import userName.UserName;

@SuppressWarnings("serial")
public class ResumeDialog extends JDialog
{
	//確認
	JButton confirmButton = new JButton();
	JButton cancelButton = new JButton();
	
	JLabel fishtankLabel = new JLabel();
	JLabel environmentLabel = new JLabel();
	JLabel saveNameLabel = new JLabel();
	
	JLabel chooseWarningLabel = new JLabel();
	JLabel nameWarningLabel = new JLabel();
	
	JButton firebaseButton = new JButton();
	JButton localButton = new JButton();
	
	JTextField saveNameTextField = new JTextField();
	
	private Boolean chooseFirebase = false;
	private Boolean confirm = false;
	
	private int fishtankChoose = -1;
	private int environmentChoose = -1;
	
	public ResumeDialog()
	{
		setDialog();
		setButton();
		setLabel();
		setChoose();
	}
	
	private void setDialog()
	{
		setTitle("繼續模擬");
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
		fishtankLabel.setText("選擇來源:");
		saveNameLabel.setText("輸入存檔名稱:");
		fishtankLabel.setFont(new Font(null, Font.BOLD, 15));
		saveNameLabel.setFont(new Font(null, Font.BOLD, 15));
		fishtankLabel.setBounds(40, 50, 100, 50);
		saveNameLabel.setBounds(40, 120, 100, 50);
		add(fishtankLabel);
		add(saveNameLabel);
		
		chooseWarningLabel.setText("尚未選擇魚缸!!");
		nameWarningLabel.setText("存檔名稱不可為空");
		chooseWarningLabel.setFont(new Font(null, Font.BOLD, 17));
		nameWarningLabel.setFont(new Font(null, Font.BOLD, 15));
		chooseWarningLabel.setForeground(Color.red);
		nameWarningLabel.setForeground(Color.red);
		chooseWarningLabel.setBounds(60, 260, 150, 25);
		nameWarningLabel.setBounds(180, 230, 150, 25);
		chooseWarningLabel.setVisible(false);
		nameWarningLabel.setVisible(false);
		add(chooseWarningLabel);
		add(nameWarningLabel);
	}
	
	private void setChoose()
	{
		firebaseButton.setText("Firebase");
		localButton.setText("本地");
		firebaseButton.setFont(new Font(null, Font.BOLD, 15));
		localButton.setFont(new Font(null, Font.BOLD, 15));
		localButton.setForeground(Color.blue);
		firebaseButton.setBounds(150, 50, 100, 50);
		localButton.setBounds(260, 50, 100, 50);
		firebaseButton.addActionListener(bH);
		localButton.addActionListener(bH);
		add(firebaseButton);
		add(localButton);
		
		saveNameTextField.setText(UserName.userName);
		saveNameTextField.setFont(new Font(null, Font.BOLD, 15));
		saveNameTextField.setBounds(170, 130, 150, 30);
		add(saveNameTextField);
	}
	
	private void resetChoose()
	{
		resetWarning();
		firebaseButton.setForeground(Color.black);
		localButton.setForeground(Color.black);
	}
	
	private void resetWarning()
	{
		chooseWarningLabel.setVisible(false);
	}
	
	public Boolean chooseFirebase() {
		return chooseFirebase;
	}
	
	public String getName()
	{
		return saveNameTextField.getText();
	}
	
	public Boolean getConfirm()
	{
		return confirm;
	}

	private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae) 
        {
    		if(ae.getSource() == confirmButton)
    		{
    			if(!saveNameTextField.getText().equals(""))
    			{
    				confirm = true;
    				setVisible(false);
    			}
    			else
    			{
    				nameWarningLabel.setVisible(true);
    			}
    		}
    		else if(ae.getSource() == cancelButton)
    		{
    			confirm = false;
    			setVisible(false);
    		}
    		else if(ae.getSource() == firebaseButton)
    		{
    			resetChoose();
    			firebaseButton.setForeground(Color.blue);
    			chooseFirebase = true;
    		}
    		else if(ae.getSource() == localButton)
    		{
    			resetChoose();
    			localButton.setForeground(Color.blue);
    			chooseFirebase = false;
    		}
        }
    };
}
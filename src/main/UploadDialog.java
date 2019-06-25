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

public class UploadDialog extends JDialog
{
	//確認
	JButton confirmButton = new JButton();
	JButton cancelButton = new JButton();

	JLabel saveNameLabel = new JLabel();
	JLabel saveNameWarningLabel = new JLabel();
	
	JTextField saveNameTextField = new JTextField();
	
	private Boolean save = false;
	
	public UploadDialog()
	{
		setDialog();
		setButton();
		setLabel();
		setChoose();
	}
	
	private void setDialog()
	{
		setTitle("Firebase 上傳");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	private void setButton()
	{
		confirmButton.setText("確認");
		cancelButton.setText("取消");
		confirmButton.setFont(new Font(null, Font.BOLD, 12));
		cancelButton.setFont(new Font(null, Font.BOLD, 12));
		confirmButton.setBounds(120, 180, 100, 30); //280, 430, 100, 50
		cancelButton.setBounds(240, 180, 100, 30);
		confirmButton.addActionListener(bH);
		cancelButton.addActionListener(bH);
		add(confirmButton);
		add(cancelButton);
	}
	
	private void setLabel()
	{
		saveNameLabel.setText("紀錄名稱:");
		saveNameLabel.setFont(new Font(null, Font.BOLD, 15));
		saveNameLabel.setBounds(20, 50, 100, 50);
		add(saveNameLabel);
		
		saveNameWarningLabel.setText("預設: " + UserName.userName + " ");
		saveNameWarningLabel.setFont(new Font(null, Font.BOLD, 14));
		saveNameWarningLabel.setForeground(Color.gray);
		saveNameWarningLabel.setBounds(160, 90, 150, 25);
		saveNameWarningLabel.setVisible(true);
		add(saveNameWarningLabel);
	}
	
	private void setChoose()
	{
		saveNameTextField.setText("");
		saveNameTextField.setFont(new Font(null, Font.BOLD, 15));
		saveNameTextField.setBounds(160, 60, 180, 30);
		add(saveNameTextField);
	}
	
	public Boolean getSave() {
		return save;
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
    			save = true;
    			setVisible(false);
    		}
    		else if(ae.getSource() == cancelButton)
    		{
    			save = false;
    			setVisible(false);
    		}
        }
    };
}

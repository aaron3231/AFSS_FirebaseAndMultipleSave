package multiPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import aquarium.Aquarium;

@SuppressWarnings("serial")
public class DevicePanelModule extends JPanel
{
	private Aquarium aquarium;
	private JTextArea fishfoodText = new JTextArea("������:");
	private JButton openFeedButton = new JButton("�}");
	private JButton offFeedButton = new JButton("��");
	private JButton herbivorousButton = new JButton("��");
	private JButton meatButton = new JButton("�׭�");
	private JButton bigFeedButton = new JButton("�j");
	private JButton medFeedButton = new JButton("��");
	private JButton smallFeedButton = new JButton("�p");
	private JButton powerFeedButton = new JButton("��");
	private JButton allDayButton = new JButton("����");
	private JButton halfDayB = new JButton("�b��");
	private JTextArea waterfilterText = new JTextArea("�L�o��:");
	private JButton openWaterButton = new JButton("�}");
	private JButton offWaterButton = new JButton("��");
	private JTextArea inflatorText = new JTextArea("���:");
	private JButton openInflatorButton = new JButton("�}");
	private JButton offInflatorButton = new JButton("��");
	private JTextArea lighterText = new JTextArea("�ө���:");
	private JButton openLightButton = new JButton("�}");
	private JButton offlightB = new JButton("��");
	private JButton lightTimeButton = new JButton("�]�w�ɶ�");
	private JTextArea warmerText = new JTextArea("�[�ž�:");
	private JButton openWarmerButton = new JButton("�}");
	private JButton offWarmerButton = new JButton("��");
	private JButton setWarmTempButton = new JButton("�]�w�ū�");
	
	public DevicePanelModule(Aquarium a)
	{
		aquarium = a;
		setTextArea();
		setButton();
	}
	
	private void setTextArea()
	{
		fishfoodText.setFont(new Font(null, Font.BOLD, 20));
		fishfoodText.setForeground(Color.decode("#A52A2A"));
		fishfoodText.setBounds(90, 30, 100, 100);
		fishfoodText.setEditable(false);
		fishfoodText.setBackground(null);
		add(fishfoodText);
		waterfilterText.setFont(new Font(null, Font.BOLD, 20));
		waterfilterText.setForeground(Color.decode("#A52A2A"));
		waterfilterText.setBounds(90, 210, 100, 50);
		waterfilterText.setEditable(false);
		waterfilterText.setBackground(null);
		add(waterfilterText);
		inflatorText.setFont(new Font(null, Font.BOLD, 20));
		inflatorText.setForeground(Color.decode("#A52A2A"));
		inflatorText.setBounds(90, 270, 100, 50);
		inflatorText.setEditable(false);
		inflatorText.setBackground(null);
		add(inflatorText);
		lighterText.setFont(new Font(null, Font.BOLD, 20));
		lighterText.setForeground(Color.decode("#A52A2A"));
		lighterText.setBounds(90, 330, 100, 50);
		lighterText.setEditable(false);
		lighterText.setBackground(null);
		add(lighterText);
		warmerText.setFont(new Font(null, Font.BOLD, 20));
		warmerText.setForeground(Color.decode("#A52A2A"));
		warmerText.setBounds(90, 430, 100, 50);
		warmerText.setEditable(false);
		warmerText.setBackground(null);
		add(warmerText);
	}
	
	private void setButton()
	{
		openFeedButton.setBounds(260, 30, 100, 30);
		openFeedButton.addActionListener(bH);
		add(openFeedButton);
		offFeedButton.setBounds(380, 30, 100, 30);
		offFeedButton.addActionListener(bH);
		add(offFeedButton);
		allDayButton.setBounds(260, 70, 100, 30);
		allDayButton.addActionListener(bH);
		add(allDayButton);
		halfDayB.setBounds(380, 70, 100, 30);
		halfDayB.addActionListener(bH);
		add(halfDayB);
		herbivorousButton.setBounds(260, 110, 100, 30);
		herbivorousButton.addActionListener(bH);
		add(herbivorousButton);
		meatButton.setBounds(380, 110, 100, 30);
		meatButton.addActionListener(bH);
		add(meatButton);
		bigFeedButton.setBounds(260, 150, 100, 30);
		bigFeedButton.addActionListener(bH);
		add(bigFeedButton);
		medFeedButton.setBounds(380, 150, 100, 30);
		medFeedButton.addActionListener(bH);
		add(medFeedButton);
		smallFeedButton.setBounds(500, 150, 100, 30);
		smallFeedButton.addActionListener(bH);
		add(smallFeedButton);
		powerFeedButton.setBounds(620, 150, 100, 30);
		powerFeedButton.addActionListener(bH);
		add(powerFeedButton);
		
		openWaterButton.setBounds(260, 210, 100, 30);
		openWaterButton.addActionListener(bH);
		add(openWaterButton);
		offWaterButton.setBounds(380, 210, 100, 30);
		offWaterButton.addActionListener(bH);
		add(offWaterButton);
		
		openInflatorButton.setBounds(260, 270, 100, 30);
		openInflatorButton.addActionListener(bH);
		add(openInflatorButton);
		offInflatorButton.setBounds(380, 270, 100, 30);
		offInflatorButton.addActionListener(bH);
		add(offInflatorButton);
		
		openLightButton.setBounds(260, 330, 100, 30);
		openLightButton.addActionListener(bH);
		add(openLightButton);
		offlightB.setBounds(380, 330, 100, 30);
		offlightB.addActionListener(bH);
		add(offlightB);
		lightTimeButton.setBounds(320, 370, 100, 30);
		lightTimeButton.addActionListener(bH);
		add(lightTimeButton);
		
		openWarmerButton.setBounds(260, 430, 100, 30);
		openWarmerButton.addActionListener(bH);
		add(openWarmerButton);
		offWarmerButton.setBounds(380, 430, 100, 30);
		offWarmerButton.addActionListener(bH);
		add(offWarmerButton);
		setWarmTempButton.setBounds(320, 470, 100, 30);
		setWarmTempButton.addActionListener(bH);
		add(setWarmTempButton);
	}
	
	private void foodButtonTrue()
	{
		herbivorousButton.setEnabled(true);
		meatButton.setEnabled(true);
		bigFeedButton.setEnabled(true);
		medFeedButton.setEnabled(true);
		smallFeedButton.setEnabled(true);
		powerFeedButton.setEnabled(true);
		allDayButton.setEnabled(true);
		halfDayB.setEnabled(true);
		if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getDietFavorite() == 1)
		{
			meatButton.setForeground(Color.red);
		}
		else
		{
			herbivorousButton.setForeground(Color.decode("#2E8B57"));
		}
		if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getGrain() == 50)
			bigFeedButton.setForeground(Color.red);
		else if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getGrain() == 10)
			medFeedButton.setForeground(Color.red);
		else if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getGrain() == 5)
			smallFeedButton.setForeground(Color.red);
		else if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getGrain() == 1)
			powerFeedButton.setForeground(Color.red);
		if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getRegularTime() == 1)
			halfDayB.setForeground(Color.red);
		else if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getRegularTime() == 2)
			allDayButton.setForeground(Color.red);
	}
	
	private void foodButtonFalse()
	{
		herbivorousButton.setForeground(Color.black);
		herbivorousButton.setEnabled(false);
		meatButton.setForeground(Color.black);
		meatButton.setEnabled(false);
		bigFeedButton.setForeground(Color.black);
		bigFeedButton.setEnabled(false);
		medFeedButton.setForeground(Color.black);
		medFeedButton.setEnabled(false);
		smallFeedButton.setForeground(Color.black);
		smallFeedButton.setEnabled(false);
		powerFeedButton.setForeground(Color.black);
		powerFeedButton.setEnabled(false);
		allDayButton.setForeground(Color.black);
		allDayButton.setEnabled(false);
		halfDayB.setForeground(Color.black);
		halfDayB.setEnabled(false);
	}
	
	private void lightButtonTrue()
	{
		//////////////////////////////////�禡
		lightTimeButton.setEnabled(true);
	}
	
	private void lightButtonFalse()
	{
		lightTimeButton.setEnabled(false);
	}
	
	private void warmerButtonTrue()
	{
		////////////////////////////////�禡
		setWarmTempButton.setEnabled(true);
	}
	
	private void warmerButtonFalse()
	{
		setWarmTempButton.setEnabled(false);
	}
	
	private void resetButtonFalse()
	{
		openFeedButton.setForeground(Color.black);
		openFeedButton.setEnabled(false);
		offFeedButton.setForeground(Color.black);
		offFeedButton.setEnabled(false);
		herbivorousButton.setForeground(Color.black);
		herbivorousButton.setEnabled(false);
		meatButton.setForeground(Color.black);
		meatButton.setEnabled(false);
		bigFeedButton.setForeground(Color.black);
		bigFeedButton.setEnabled(false);
		medFeedButton.setForeground(Color.black);
		medFeedButton.setEnabled(false);
		smallFeedButton.setForeground(Color.black);
		smallFeedButton.setEnabled(false);
		powerFeedButton.setForeground(Color.black);
		powerFeedButton.setEnabled(false);
		allDayButton.setForeground(Color.black);
		allDayButton.setEnabled(false);
		halfDayB.setForeground(Color.black);
		halfDayB.setEnabled(false);
		openWaterButton.setForeground(Color.black);
		openWaterButton.setEnabled(false);
		offWaterButton.setForeground(Color.black);
		offWaterButton.setEnabled(false);
		openInflatorButton.setForeground(Color.black);
		openInflatorButton.setEnabled(false);
		offInflatorButton.setForeground(Color.black);
		offInflatorButton.setEnabled(false);
		openLightButton.setForeground(Color.black);
		openLightButton.setEnabled(false);
		offlightB.setForeground(Color.black);
		offlightB.setEnabled(false);
		lightTimeButton.setEnabled(false);
		openWarmerButton.setForeground(Color.black);
		openWarmerButton.setEnabled(false);
		offWarmerButton.setForeground(Color.black);
		offWarmerButton.setEnabled(false);
		setWarmTempButton.setEnabled(false);
	}
	
	public void setButtonChoose()
	{
		////////////////////////////////�I�s�T�{�禡
		//check 
		/*
		 * if(check��)
		 * getopenoroff
		 * openoroffenabled
		 * if(open)
		 * open.setfore
		 * ButtonTrue()
		 * else
		 * off.setfore
		 */
		if(aquarium.getDevice().getFeederbuyer() != 0)
		{
			openFeedButton.setEnabled(true);
			offFeedButton.setEnabled(true);
			if(aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].getModleState())
			{
				openFeedButton.setForeground(Color.red);
				foodButtonTrue();
			}
			else  // close
			{
				offFeedButton.setForeground(Color.blue);
			}
		}
		if(aquarium.getDevice().getFilterbuyer() != 0)
		{
			openWaterButton.setEnabled(true);
			offWaterButton.setEnabled(true);
			if(aquarium.getDevice().filter[aquarium.getDevice().getFilterbuyer()].getModleState())
			{
				openWaterButton.setForeground(Color.red);
			}
			else  // close
			{
				offWaterButton.setForeground(Color.blue);
			}
		}
		if(aquarium.getDevice().getInflatorbuyer() != 0)
		{
			openInflatorButton.setEnabled(true);
			offInflatorButton.setEnabled(true);
			if(aquarium.getDevice().inflator[aquarium.getDevice().getInflatorbuyer()].getModleState())
			{
				openInflatorButton.setForeground(Color.red);
			}
			else  // close
			{
				offInflatorButton.setForeground(Color.blue);
			}
		}
		if(aquarium.getDevice().getFlashLightbuyer() != 0)
		{
			openLightButton.setEnabled(true);
			offlightB.setEnabled(true);
			if(aquarium.getDevice().flashLight[aquarium.getDevice().getFlashLightbuyer()].getModleState())
			{
				openLightButton.setForeground(Color.red);
			}
			else  // close
			{
				offlightB.setForeground(Color.blue);
			}
		}
		if(aquarium.getDevice().getHeaterbuyer() != 0)
		{
			openWarmerButton.setEnabled(true);
			offWarmerButton.setEnabled(true);
			if(aquarium.getDevice().heater[aquarium.getDevice().getHeaterbuyer()].getModleState())
			{
				openWarmerButton.setForeground(Color.red);
				warmerButtonTrue();
			}
			else  // close
			{
				offWarmerButton.setForeground(Color.blue);
			}
		}
	}
	
	public void refresh()
	{
		resetButtonFalse();
		setButtonChoose();
	}
	
	private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae)
    	{
    		if(ae.getSource() == openFeedButton)
    		{
    			openFeedButton.setForeground(Color.red);
    			offFeedButton.setForeground(Color.black);
    			foodButtonTrue();
    			/////////////////�I�s�禡
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].changeModel(true);
    		}
    		else if(ae.getSource() == offFeedButton)
    		{
    			offFeedButton.setForeground(Color.blue);
    			openFeedButton.setForeground(Color.black);
    			foodButtonFalse();
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].changeModel(false);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == openWaterButton)
    		{
    			openWaterButton.setForeground(Color.red);
    			offWaterButton.setForeground(Color.black);
    			aquarium.getDevice().filter[aquarium.getDevice().getFilterbuyer()].changeModel(true);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == offWaterButton)
    		{
    			offWaterButton.setForeground(Color.blue);
    			openWaterButton.setForeground(Color.black);
    			aquarium.getDevice().filter[aquarium.getDevice().getFilterbuyer()].changeModel(false);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == openInflatorButton)
    		{
    			openInflatorButton.setForeground(Color.red);
    			offInflatorButton.setForeground(Color.black);
    			aquarium.getDevice().inflator[aquarium.getDevice().getInflatorbuyer()].changeModel(true);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == offInflatorButton)
    		{
    			offInflatorButton.setForeground(Color.blue);
    			openInflatorButton.setForeground(Color.black);
    			aquarium.getDevice().inflator[aquarium.getDevice().getInflatorbuyer()].changeModel(false);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == openLightButton)
    		{
    			openLightButton.setForeground(Color.red);
    			offlightB.setForeground(Color.black);
    			aquarium.getDevice().flashLight[aquarium.getDevice().getFlashLightbuyer()].changeModel(true);
    			JOptionPane.showMessageDialog(openLightButton,"�����]�w�ө��ɶ��~�|�Ұ�","����", JOptionPane.INFORMATION_MESSAGE);
    			lightButtonTrue();
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == offlightB)
    		{
    			offlightB.setForeground(Color.blue);
    			openLightButton.setForeground(Color.black);
    			aquarium.getDevice().flashLight[aquarium.getDevice().getFlashLightbuyer()].changeModel(false);
    			lightButtonFalse();
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == openWarmerButton)
    		{
    			openWarmerButton.setForeground(Color.red);
    			offWarmerButton.setForeground(Color.black);
    			aquarium.getDevice().heater[aquarium.getDevice().getHeaterbuyer()].changeModel(true);
    			JOptionPane.showMessageDialog(openWarmerButton,"�����]�w�ūפW���~�|�Ұ�","����", JOptionPane.INFORMATION_MESSAGE);
    			warmerButtonTrue();
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == offWarmerButton)
    		{
    			offWarmerButton.setForeground(Color.blue);
    			openWarmerButton.setForeground(Color.black);
    			aquarium.getDevice().heater[aquarium.getDevice().getHeaterbuyer()].changeModel(false);
    			warmerButtonFalse();
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == herbivorousButton)
    		{
    			herbivorousButton.setForeground(Color.decode("#2E8B57"));
    			meatButton.setForeground(Color.black);
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].setFavorite(2);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == meatButton)
    		{
    			meatButton.setForeground(Color.red);
    			herbivorousButton.setForeground(Color.black);
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].setFavorite(1);
    			/////////////////�I�s�禡
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == bigFeedButton)
    		{
    			bigFeedButton.setForeground(Color.red);
    			medFeedButton.setForeground(Color.black);
    			smallFeedButton.setForeground(Color.black);
    			powerFeedButton.setForeground(Color.black);
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].setGrain(50);
    			/////////////////�I�s�禡
    			////////////////////////�I�s�禡
    		}
    		else if(ae.getSource() == medFeedButton)
    		{
    			bigFeedButton.setForeground(Color.black);
    			medFeedButton.setForeground(Color.red);
    			smallFeedButton.setForeground(Color.black);
    			powerFeedButton.setForeground(Color.black);
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].setGrain(10);
    			///////////////////////�I�s�禡
    		}
    		else if(ae.getSource() == smallFeedButton)
    		{
    			bigFeedButton.setForeground(Color.black);
    			medFeedButton.setForeground(Color.black);
    			smallFeedButton.setForeground(Color.red);
    			powerFeedButton.setForeground(Color.black);
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].setGrain(5);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == powerFeedButton)
    		{
    			bigFeedButton.setForeground(Color.black);
    			medFeedButton.setForeground(Color.black);
    			smallFeedButton.setForeground(Color.black);
    			powerFeedButton.setForeground(Color.red);
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].setGrain(1);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == lightTimeButton)
    		{
    			/////////////////�ө��ɶ�
    			int i = Integer.parseInt(JOptionPane.showInputDialog("��J�ө��ɶ�(�p��)"));
    			aquarium.getDevice().flashLight[aquarium.getDevice().getFlashLightbuyer()].setLightHour(i);
    			/////////////////�I�s�禡���ɶ�
    		}
    		else if(ae.getSource() == setWarmTempButton)
    		{
    			/////////////////�ūפW��
    			int i = Integer.parseInt(JOptionPane.showInputDialog("��J�W���ū�(�J)"));
    			aquarium.getDevice().heater[aquarium.getDevice().getHeaterbuyer()].setMaxTemperature(i);
    			/////////////////�I�s�禡���ū�
    		}
    		else if(ae.getSource() == allDayButton)
    		{
    			allDayButton.setForeground(Color.red);
    			halfDayB.setForeground(Color.black);
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].setRegularTime(2);
    			/////////////////�I�s�禡
    		}
    		else if(ae.getSource() == halfDayB)
    		{
    			halfDayB.setForeground(Color.red);
    			allDayButton.setForeground(Color.black);
    			aquarium.getDevice().feeder[aquarium.getDevice().getFeederbuyer()].setRegularTime(1);
    			/////////////////�I�s�禡
    		}
        }
    };
}
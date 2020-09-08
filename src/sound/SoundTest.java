package sound;

import java.awt.Container;
import java.awt.FlowLayout;
//import java.awt.Image;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SoundTest extends JFrame{
	
	private JButton testButton = new JButton("設定");
	private AFSSSound mySound = new AFSSSound(31, 3);
	
	private int nowPlaying = 0;
	
	public Clip getClip() {
		return mySound.audioClip;
	}
	public SoundTest() {
		//Image frameIcon;
		//frameIcon = Toolkit.getDefaultToolkit().getImage("C:/Settings.png");
		//setIconImage(frameIcon);
		Container container = new Container();
		FlowLayout flowLayout = new FlowLayout();
		container.setLayout(flowLayout);
		
		container.add(testButton);
		add(container);
		
		ButtonHandler handler = new ButtonHandler();
		testButton.addActionListener(handler);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(300, 300, 300, 150);
		
		mySound.run();
	}
	private class ButtonHandler implements ActionListener //按鈕處理
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == testButton)
			{	
				
				mySound.audioClip.stop();
				ChooseBgmMenu myMenu = new ChooseBgmMenu(true);
				myMenu.setBounds(300, 300, 300, 150);
				myMenu.setModal(true);
				myMenu.setVisible(true);
				myMenu.dispose();
				
				//System.out.println("now1:" + nowPlaying);
				if(myMenu.isVisible() == false) {
					nowPlaying = myMenu.getNowPlaying();
					//System.out.println("now2:" + nowPlaying);
					mySound = new AFSSSound(nowPlaying, 3);
					mySound.run();
				}
				//System.out.print("hi");
			}
		}
	}
}

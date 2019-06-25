package sound;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ChooseBgmMenu extends JDialog{
	private final String[] track= {"","歡樂之海&陽光之海","寂寞之海","神秘之海","浪漫之海&晴朗海洋","甜蜜之海","夢幻星空","紅色星空","精靈之海&奇異之海","雨林晨曦","北極冰境","雪世界","神奇聖誕夜","金銀滿地","童話之夜","溢彩聲之夜","夏日海灘","精靈幻境","蒼茫雪夜","悸動太空","秋日暖陽","炫彩冰境","瑪雅黎明","夜色馴鹿","霞光遊樂園","夏日午後","戈壁沙洲","四周年專屬背景","甜蜜糖果","聖誕派對","蠟筆小新","輪舞曲"};
	//private final String[] track= {};
	private JButton setBgmButton = new JButton("確定");
	private JButton tryBgmButton = new JButton("試聽");
	private JButton stopBgmButton = new JButton("暫停");
	private int nowPlaying; //按確定後會播放的曲目
	private int tryPlaying = 0; //試聽的曲目
	private Boolean stopPlaying = false;
	private AFSSSound menuSound; //設定自己的音樂(試聽用)
	private JComboBox<String> combox1 = new JComboBox<String>(track); //選擇音樂的地方
	
	private final String path = "src//image//";
	
	public ChooseBgmMenu(Boolean playing) {
		
		setTitle("設定背景音樂");
		//設定layout
		GridLayout mainGridLayout = new GridLayout(2, 1);
		setLayout(mainGridLayout);
		//設定panel
		JPanel upPanel = new JPanel();
		FlowLayout upGridLayout = new FlowLayout();
		upPanel.setLayout(upGridLayout);
		Border border;
		border = BorderFactory.createLineBorder(Color.black);
		upPanel.setBorder(border);
		JLabel soundLabel = new JLabel("背景音樂種類");
		Font font = new Font("微軟正黑體", Font.BOLD, 15);
		soundLabel.setFont(font);
		soundLabel.setForeground(Color.BLUE);
		upPanel.add(soundLabel);
		//設定combobox
		combox1.setFont(font);
		upPanel.add(combox1);
		add(upPanel);
		combox1.addItemListener( //加入事件
				new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
				{
					tryPlaying = combox1.getSelectedIndex(); //把選的曲目設定為試聽的曲目
					if(tryPlaying == 0) {
						tryPlaying = 31; //預設背景音樂
					}
				}
			}	
		});
		//設定panel
		JPanel downPanel = new JPanel();
		FlowLayout downGridLayout = new FlowLayout();
		downPanel.setLayout(downGridLayout);
		downPanel.setBorder(border);
		//設定試聽按鈕
		tryBgmButton.setSize(100, 60);
		tryBgmButton.setFont(font);
		tryBgmButton.setBackground(Color.LIGHT_GRAY);
		downPanel.add(tryBgmButton);
		//設定確定按鈕
		setBgmButton.setSize(100, 60);
		setBgmButton.setFont(font);
		setBgmButton.setBackground(Color.LIGHT_GRAY);
		downPanel.add(setBgmButton);
		//設定暫停按鈕
		stopBgmButton.setSize(100, 60);
		if(!playing)
		{
			stopBgmButton.setText("繼續撥放");
			stopBgmButton.setFont(font);
			stopBgmButton.setBackground(Color.LIGHT_GRAY);
			stopPlaying = true;
		}
		else
		{
			stopBgmButton.setFont(font);
			stopBgmButton.setBackground(Color.LIGHT_GRAY);
			stopPlaying = false;
		}
		downPanel.add(stopBgmButton);
		add(downPanel);
		//事件處理
		ButtonHandler handler = new ButtonHandler();
		setBgmButton.addActionListener(handler);
		tryBgmButton.addActionListener(handler);
		stopBgmButton.addActionListener(handler);
		//設定圖標
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);//設定為要按下確定才能關閉
		Image frameIcon;
		frameIcon = Toolkit.getDefaultToolkit().getImage(path + "if_multimedia.png"); //相對路徑，設定左上角圖標
		setIconImage(frameIcon);
	}
	
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == setBgmButton) { //按下確定
				nowPlaying = combox1.getSelectedIndex(); //把確定要的曲目設定進去
				stopPlaying = false;
				setVisible(false);//關閉此選單
			}
			if(event.getSource() == tryBgmButton) { //按下試聽
				menuSound = new AFSSSound(tryPlaying, 3); //設定試聽曲目
				menuSound.run(); //播放
				try { //試聽5秒
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					//TODO 自動產生的 catch 區塊
					e.printStackTrace();
				}
				menuSound.audioClip.stop(); //停止
				menuSound = new AFSSSound();//清空
			}
			if(event.getSource() == stopBgmButton)
			{
				if(stopPlaying)
				{
					stopPlaying = false;
					setVisible(false);
				}
				else
				{
					stopPlaying = true;
					setVisible(false);
				}
			}
		}
	}
	
	public Boolean getStop() {
		return stopPlaying;
	}
	
	public int getNowPlaying() { //回傳設定選單中使用者要更換成的曲目
		return nowPlaying;
	}
}

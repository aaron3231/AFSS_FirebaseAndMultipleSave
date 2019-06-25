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
	private final String[] track= {"","�w�֤���&��������","�I�椧��","��������","��������&���Ԯ��v","���e����","�ڤ۬P��","����P��","���F����&�_������","�B�L���f","�_���B��","���@��","���_�t�ϩ]","���Ⱥ��a","���ܤ��]","���m�n���]","�L����y","���F�۹�","�a���]","���ʤӪ�","���x��","���m�B��","��������","�]�⹥��","�����C�ֶ�","�L��ȫ�","����F�w","�|�P�~�M�ݭI��","���e�}�G","�t�Ϭ���","�����p�s","���R��"};
	//private final String[] track= {};
	private JButton setBgmButton = new JButton("�T�w");
	private JButton tryBgmButton = new JButton("��ť");
	private JButton stopBgmButton = new JButton("�Ȱ�");
	private int nowPlaying; //���T�w��|���񪺦���
	private int tryPlaying = 0; //��ť������
	private Boolean stopPlaying = false;
	private AFSSSound menuSound; //�]�w�ۤv������(��ť��)
	private JComboBox<String> combox1 = new JComboBox<String>(track); //��ܭ��֪��a��
	
	private final String path = "src//image//";
	
	public ChooseBgmMenu(Boolean playing) {
		
		setTitle("�]�w�I������");
		//�]�wlayout
		GridLayout mainGridLayout = new GridLayout(2, 1);
		setLayout(mainGridLayout);
		//�]�wpanel
		JPanel upPanel = new JPanel();
		FlowLayout upGridLayout = new FlowLayout();
		upPanel.setLayout(upGridLayout);
		Border border;
		border = BorderFactory.createLineBorder(Color.black);
		upPanel.setBorder(border);
		JLabel soundLabel = new JLabel("�I�����ֺ���");
		Font font = new Font("�L�n������", Font.BOLD, 15);
		soundLabel.setFont(font);
		soundLabel.setForeground(Color.BLUE);
		upPanel.add(soundLabel);
		//�]�wcombobox
		combox1.setFont(font);
		upPanel.add(combox1);
		add(upPanel);
		combox1.addItemListener( //�[�J�ƥ�
				new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
				{
					tryPlaying = combox1.getSelectedIndex(); //��諸���س]�w����ť������
					if(tryPlaying == 0) {
						tryPlaying = 31; //�w�]�I������
					}
				}
			}	
		});
		//�]�wpanel
		JPanel downPanel = new JPanel();
		FlowLayout downGridLayout = new FlowLayout();
		downPanel.setLayout(downGridLayout);
		downPanel.setBorder(border);
		//�]�w��ť���s
		tryBgmButton.setSize(100, 60);
		tryBgmButton.setFont(font);
		tryBgmButton.setBackground(Color.LIGHT_GRAY);
		downPanel.add(tryBgmButton);
		//�]�w�T�w���s
		setBgmButton.setSize(100, 60);
		setBgmButton.setFont(font);
		setBgmButton.setBackground(Color.LIGHT_GRAY);
		downPanel.add(setBgmButton);
		//�]�w�Ȱ����s
		stopBgmButton.setSize(100, 60);
		if(!playing)
		{
			stopBgmButton.setText("�~�򼷩�");
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
		//�ƥ�B�z
		ButtonHandler handler = new ButtonHandler();
		setBgmButton.addActionListener(handler);
		tryBgmButton.addActionListener(handler);
		stopBgmButton.addActionListener(handler);
		//�]�w�ϼ�
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);//�]�w���n���U�T�w�~������
		Image frameIcon;
		frameIcon = Toolkit.getDefaultToolkit().getImage(path + "if_multimedia.png"); //�۹���|�A�]�w���W���ϼ�
		setIconImage(frameIcon);
	}
	
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == setBgmButton) { //���U�T�w
				nowPlaying = combox1.getSelectedIndex(); //��T�w�n�����س]�w�i�h
				stopPlaying = false;
				setVisible(false);//���������
			}
			if(event.getSource() == tryBgmButton) { //���U��ť
				menuSound = new AFSSSound(tryPlaying, 3); //�]�w��ť����
				menuSound.run(); //����
				try { //��ť5��
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					//TODO �۰ʲ��ͪ� catch �϶�
					e.printStackTrace();
				}
				menuSound.audioClip.stop(); //����
				menuSound = new AFSSSound();//�M��
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
	
	public int getNowPlaying() { //�^�ǳ]�w��椤�ϥΪ̭n�󴫦�������
		return nowPlaying;
	}
}

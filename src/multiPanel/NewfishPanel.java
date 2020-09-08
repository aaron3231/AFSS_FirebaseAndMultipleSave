package multiPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import aquarium.Aquarium;
import fishtankPanel.FishtankPanel;
import imagePanel.ImagePanel;

@SuppressWarnings("serial")
public class NewfishPanel extends JPanel
{
	private Aquarium aquarium;
	private JPanel fishListP = new JPanel()
    {
    	public Component add(Component c)
        {
            c.setBounds(getComponentCount() * 140 + 10, 10, 130, 130);
            super.add(c);
            if(getWidth() <= getComponentCount() * 140 + 10)
                setPreferredSize(new Dimension(getComponentCount() * 140 + 10, 150));
            return c;
        }
    };
    private String[] fishs = {"�ճ���", "����O", "���ԧB���P��", "���{�q", "���갫��",
    		                  "������", "�\������", "�ŭ˦Q", "���Y��", "���]�����u"};
    private String space = "                    ";
    private String[] fishsDes= { space + "�ճ���: Poecilia reticulata\n" + space + "����: �H����\n" + space + "�ةR: 1~2�~\n" + space + "�̤j���: 2.5~3.5 cm\n" + space + "����: ������, Ħ��, ���ͩ���, �����H�h\n" + space + "�A�X�ū�: 18~28�J\n" + space + "����: �²H��, �Ԩ��O��, ���y�t�קC\n", 
    							 space +  "����O: Paracheirodon innesi\n" + space + "����: �H����\n" + space + "�ةR: 1~2�~\n" + space + "�̤j���: 3~4 cm\n" + space + "����: ������, �B��ͪ�, �p����, ���D\n" + space + "�A�X�ū�: 20~26�J\n" + space + "����: �ݭní�w, ����v�T���誺�Ʊ����|�e���ͯf\n", 
    							 space + "���ԧB���P��: Pomacanthus asfur\n" + space + "����: ������\n" + space + "�ةR: 10~12�~\n" + space + "�̤j���: 40cm\n" + space + "����: �׭���, Ħ��, ����, ����, �p���ʪ�\n" + space + "�A�X�ū�: 26�J\n" + space + "����: ���q����\n",
    							 space + "���{�q: Centropyge ferrugata\n" + space + "����: ������\n" + space + "�ةR: 3~5�~\n" + space + "�̤j���: 7~10cm\n" + space + "����: ��Ħ, ������, �n��ʪ�, ������\n" + space + "�A�X�ū�: 24~27�J\n" + space + "����: ���q����\n", 
    							 space + "���갫��: Betta splendens\n" + space + "����: �H����\n" + space + "�ةR: 1.5~2�~\n" + space + "�̤j���: 6~7 cm\n" + space + "����: ������, �B��ͪ�, ���ͩ��Υ���(�m�n)\n" + space + "�A�X�ū�: 22~24�J, ���i�C��20\n" + space + "����: ���q\n",
    							 space + "������: Dario rerio\n" + space + "����: �H����\n" + space + "�ةR: 2~3 �~\n" + space + "�̤j���: 4~6 cm\n" + space + "����: ������, �B��ͪ�, ����, �p���Ҵ���\n" + space + "�A�X�ū�: 25~26�J\n" + space + "����: ��L���ӭn�D, ���n�M��\n", 
    							 space + "�m��(�\������): Macropodus opercularis\n" + space + "����: �H����\n" + space + "�ةR: �۩�2~3�~, ����3~6�~\n" + space + "�̤j���: 5~6 cm\n" + space + "����: ������, �B��ͪ�, ���ͩ��Υ���(�m�n), Ħ��\n" + space + "�A�X�ū�: 20~27�J\n" + space + "����: �²H��\n", 
    							 space + "�ŭ˦Q: Paracanthurus hepatus\n" + space + "����: ������\n" + space + "�ةR: 5~7�~\n" + space + "�̤j���: 25~28cm\n" + space + "����: �B��ͪ�, ��(����/��Ħ), Ħ��\n" + space + "�A�X�ū�: 24~26�J\n" + space + "����: �ӷP/���y�����L�h\n", 
    							 space + "���Y��: Chaetodon kleinii\n" + space + "����: ������\n" + space + "�ةR: 10~12�~\n" + space + "�̤j���: 10~13cm\n" + space + "����: ������, �p���L��հʪ�, ������, �B��ʪ�, Ħ���H��\n" + space + "�A�X�ū�: 25�J\n" + space + "����: �ӷP, ���n���b, ���Ÿ��t�����W�L2��\n", 
    							 space + "���]�����u: Odunus niger\n" + space + "����: ������\n" + space + "�ةR: 5~7 �~\n" + space + "�̤j���: 33~37cm\n" + space + "����: �B��ͪ�, �׭�, ����\n" + space + "�A�X�ū�: 24~27�J\n" + space + "����: ���q����\n" };
    
    private JPanel[] fishII = { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
    							new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel() };
	private ImagePanel[] fishI = { new ImagePanel(fishs[0]), new ImagePanel(fishs[1]), 
			                       new ImagePanel(fishs[2]), new ImagePanel(fishs[3]),
			                       new ImagePanel(fishs[4]), new ImagePanel(fishs[5]),
			                       new ImagePanel(fishs[6]), new ImagePanel(fishs[7]),
			                       new ImagePanel(fishs[8]), new ImagePanel(fishs[9]) };
	private JScrollPane fishListS = new JScrollPane(fishListP);
	private int lastchoose = -1;
	//������T
	private JTextArea fishInformationT = new JTextArea();
	private JScrollPane fishInformationS = new JScrollPane(fishInformationT);
	private ImagePanel fishsI = new ImagePanel("Test");
	//�T�{���s
	private JButton confirmB = new JButton();
	//���
	private String chooseFish = null;
	private int chooseI;
	private JLabel countL = new JLabel();
	/*
	private int fishcount = 0;
	private int fishmax = 3;
	*/
	private static int FISHTYPE = 10;
	private FishtankPanel ftp;
	//�ǤJ�����e��(��J���Ϥ�)
	public NewfishPanel(Aquarium a,FishtankPanel ftp)
	{
		aquarium = a;
		this.ftp=ftp;
		setfishListS();
		setfishListP();
		setfishImage();
		setfishInformationS();
		setfishInformation();
		setfishInformationPicture();
		setconfirmB();
		setcountL();
	}
	
	private void setfishListS()
	{
		fishListS.setBounds(25, 0, 950, 170);
		fishListS.getHorizontalScrollBar().setUnitIncrement(16);//�u���t��
		add(fishListS);
	}
	
	private void setfishListP()
	{
		fishListP.setLayout(null);
	}
	
	private void setfishImage()
	{
		for(int i = 0; i < FISHTYPE; i++)
		{
			fishI[i].setName(fishs[i]);
			fishI[i].setBounds(5, 5, 120, 120);
			fishII[i].setLayout(null);
			fishII[i].add(fishI[i]);
			fishII[i].setBackground(Color.white);
			fishListP.add(fishII[i]);
			fishI[i].addMouseListener(mP);
		}
	}
	
	private void setfishInformationS()
	{
		fishInformationS.setBounds(25, 200, 650, 425);
		add(fishInformationS);
	}
	
	private void setfishInformation()
	{
		fishInformationT.setEditable(false);
		fishInformationT.setLayout(null);
		setfishInformationContent();
	}
	
	private void setconfirmB()
	{
		confirmB.setBounds(750, 550, 100, 40);
		confirmB.setText("�ʶR");
		confirmB.setFont(new Font(null, Font.BOLD, 16));
		confirmB.addActionListener(bH);
		add(confirmB);
	}
	
	private void setcountL()
	{
		countL.setBounds(720, 230, 200, 50);
		countL.setFont(new Font(null, Font.BOLD, 20));
		countL.setText("�ثe�����ƶq: " + aquarium.getnFishs() + "/" + Aquarium.maxFishsCount);
		///////////////////////////////////////////////////////////�I�s�ƶq finish
		add(countL);
	}
	
	private void setfishInformationContent()
	{
		if(chooseFish == null)
			fishInformationT.setText("\n\n\n\n                      ��@�����a");
		else {
			for(int i=0;i<10;i++) {
				if(chooseFish==fishs[i]) {
		        	chooseI=i;
					break;
				}
			}
			fishInformationT.setText("\n\n\n\n\n\n\n" + fishsDes[chooseI]);
		}
		//////////////////////////////////////////////////////////////////////�ɸԲӻ���
		fishInformationT.setFont(new Font(null, Font.BOLD, 15));
	}
	
	private void setfishInformationPicture()
	{
		fishsI.setBounds(200, 20, 100, 100);
		fishInformationT.add(fishsI);
	}
	
	private void fishInformationPictureChange()
	{
		int i;
		for(i = 0; i < FISHTYPE; i++)
		{
			if (chooseFish.equals(fishs[i]))
			{
				fishsI.fishChangeImage(i);
				fishsI.setVisible(true);
				break;
			}
		}
		fishsI.setVisible(true);
		//////////////////////////////////////////////////�ק�榡
	}
	
	private void refreshcountL()
	{
		countL.setText("�ثe�����ƶq: " + aquarium.getnFishs() + "/" + Aquarium.maxFishsCount);
		
	}
	
	private void refreshfishImage()
	{
		for(int i = 0; i < FISHTYPE; i++)
		{
			fishII[i].setBackground(Color.white);
		}
		chooseFish = null;
	}
	
	private void refreshListS()
	{
		JScrollBar a = fishListS.getHorizontalScrollBar();
		a.setValue(a.getMinimum());
		fishInformationT.setCaretPosition(0);
	}
	
	public void refresh()
	{
		///////////////////////////////////////////////////�I�s ���o�ƶq count and max ??
		
		refreshfishImage();
		setfishInformationContent();
		fishsI.setVisible(false);
		refreshListS();
		refreshcountL();
		lastchoose = -1;
	}
	
	private MouseAdapter mP = new MouseAdapter()
    {
        public void mousePressed(MouseEvent e)
        {
        	int i;
        	ImagePanel pnl=(ImagePanel)e.getSource();
        	chooseFish = pnl.getName();
        	setfishInformationContent();
        	fishInformationPictureChange();
        	for(i = 0; i < 10; i++)
        	{
        		if(fishs[i].equals(chooseFish))
        			break;
        	}
        	if(lastchoose != -1)
        	{
        		fishII[lastchoose].setBackground(Color.white);
        	}
        	/*
        	if(lastChoose != null)
        	{
        		lastChoose.resetImage();
        		lastChoose.repaint();
        	}
        	*/
        	/*
        	pnl.fishClickImage();
        	pnl.repaint();
        	*/
        	fishII[i].setBackground(Color.blue);
        	//lastChoose = pnl;
        	lastchoose = i;
        }
    };
    
    private ActionListener bH = new ActionListener()
    {
    	public void actionPerformed(ActionEvent ae) 
        {
    		if(aquarium.getnFishs() < Aquarium.maxFishsCount)/////////////////////////////
    		{
    			
    			if(lastchoose != -1)
    			{
    				//////////////////////////////////////////////////////�I�s++ finish
    				System.out.println(chooseFish);
    				aquarium.addAFish(chooseFish);
    				ftp.addFishTankThing(aquarium.getFishs()[aquarium.getnFishs()-1]);
    				//fishcount++;
    				JOptionPane.showMessageDialog(fishInformationT,"�ʶR���\!!!","�q��", JOptionPane.INFORMATION_MESSAGE);
    				lastchoose = -1;
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(fishInformationT,"����ܳ���!!!","ĵ�i", JOptionPane.WARNING_MESSAGE);
    			}
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(fishInformationT,"�������F!!!","ĵ�i", JOptionPane.WARNING_MESSAGE);
    		}
    		refresh();
        }
    };
}

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
    private String[] fishs = {"孔雀魚", "日光燈", "阿拉伯神仙魚", "紅閃電", "泰國鬥魚",
    		                  "斑馬魚", "蓋斑鬥魚", "藍倒吊", "藍頭蝶", "藍魔鬼砲彈"};
    private String space = "                    ";
    private String[] fishsDes= { space + "孔雀魚: Poecilia reticulata\n" + space + "魚種: 淡水魚\n" + space + "壽命: 1~2年\n" + space + "最大體長: 2.5~3.5 cm\n" + space + "食物: 雜食性, 藻類, 水生昆蟲, 有機碎屑\n" + space + "適合溫度: 18~28℃\n" + space + "水質: 純淡水, 忍受力高, 水流速度低\n", 
    							 space +  "日光燈: Paracheirodon innesi\n" + space + "魚種: 淡水魚\n" + space + "壽命: 1~2年\n" + space + "最大體長: 3~4 cm\n" + space + "食物: 雜食性, 浮游生物, 小昆蟲, 水蚤\n" + space + "適合溫度: 20~26℃\n" + space + "水質: 需要穩定, 任何影響水質的事情都會容易生病\n", 
    							 space + "阿拉伯神仙魚: Pomacanthus asfur\n" + space + "魚種: 海水魚\n" + space + "壽命: 10~12年\n" + space + "最大體長: 40cm\n" + space + "食物: 肉食性, 藻類, 海綿, 珊瑚, 小型動物\n" + space + "適合溫度: 26℃\n" + space + "水質: 普通海水\n",
    							 space + "紅閃電: Centropyge ferrugata\n" + space + "魚種: 海水魚\n" + space + "壽命: 3~5年\n" + space + "最大體長: 7~10cm\n" + space + "食物: 海藻, 珊瑚蟲, 軟體動物, 雜食性\n" + space + "適合溫度: 24~27℃\n" + space + "水質: 普通海水\n", 
    							 space + "泰國鬥魚: Betta splendens\n" + space + "魚種: 淡水魚\n" + space + "壽命: 1.5~2年\n" + space + "最大體長: 6~7 cm\n" + space + "食物: 雜食性, 浮游生物, 水生昆蟲幼蟲(孑孓)\n" + space + "適合溫度: 22~24℃, 不可低於20\n" + space + "水質: 普通\n",
    							 space + "斑馬魚: Dario rerio\n" + space + "魚種: 淡水魚\n" + space + "壽命: 2~3 年\n" + space + "最大體長: 4~6 cm\n" + space + "食物: 雜食性, 浮游生物, 昆蟲, 小型甲殼類\n" + space + "適合溫度: 25~26℃\n" + space + "水質: 其他不太要求, 但要清澈\n", 
    							 space + "彩兔(蓋斑鬥魚): Macropodus opercularis\n" + space + "魚種: 淡水魚\n" + space + "壽命: 雌性2~3年, 雄性3~6年\n" + space + "最大體長: 5~6 cm\n" + space + "食物: 雜食性, 浮游生物, 水生昆蟲幼蟲(孑孓), 藻類\n" + space + "適合溫度: 20~27℃\n" + space + "水質: 純淡水\n", 
    							 space + "藍倒吊: Paracanthurus hepatus\n" + space + "魚種: 海水魚\n" + space + "壽命: 5~7年\n" + space + "最大體長: 25~28cm\n" + space + "食物: 浮游生物, 草食(海草/海藻), 藻類\n" + space + "適合溫度: 24~26℃\n" + space + "水質: 敏感/不宜換水過多\n", 
    							 space + "藍頭蝶: Chaetodon kleinii\n" + space + "魚種: 海水魚\n" + space + "壽命: 10~12年\n" + space + "最大體長: 10~13cm\n" + space + "食物: 雜食性, 小型無脊椎動物, 珊瑚蟲, 浮游動物, 藻類碎片\n" + space + "適合溫度: 25℃\n" + space + "水質: 敏感, 水要乾淨, 水溫落差不應超過2度\n", 
    							 space + "藍魔鬼砲彈: Odunus niger\n" + space + "魚種: 海水魚\n" + space + "壽命: 5~7 年\n" + space + "最大體長: 33~37cm\n" + space + "食物: 浮游生物, 肉食, 海綿\n" + space + "適合溫度: 24~27℃\n" + space + "水質: 普通海水\n" };
    
    private JPanel[] fishII = { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
    							new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel() };
	private ImagePanel[] fishI = { new ImagePanel(fishs[0]), new ImagePanel(fishs[1]), 
			                       new ImagePanel(fishs[2]), new ImagePanel(fishs[3]),
			                       new ImagePanel(fishs[4]), new ImagePanel(fishs[5]),
			                       new ImagePanel(fishs[6]), new ImagePanel(fishs[7]),
			                       new ImagePanel(fishs[8]), new ImagePanel(fishs[9]) };
	private JScrollPane fishListS = new JScrollPane(fishListP);
	private int lastchoose = -1;
	//魚的資訊
	private JTextArea fishInformationT = new JTextArea();
	private JScrollPane fishInformationS = new JScrollPane(fishInformationT);
	private ImagePanel fishsI = new ImagePanel("Test");
	//確認按鈕
	private JButton confirmB = new JButton();
	//選擇
	private String chooseFish = null;
	private int chooseI;
	private JLabel countL = new JLabel();
	/*
	private int fishcount = 0;
	private int fishmax = 3;
	*/
	private static int FISHTYPE = 10;
	private FishtankPanel ftp;
	//傳入魚的畫面(放入魚圖片)
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
		fishListS.getHorizontalScrollBar().setUnitIncrement(16);//滾輪速度
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
		confirmB.setText("購買");
		confirmB.setFont(new Font(null, Font.BOLD, 16));
		confirmB.addActionListener(bH);
		add(confirmB);
	}
	
	private void setcountL()
	{
		countL.setBounds(720, 230, 200, 50);
		countL.setFont(new Font(null, Font.BOLD, 20));
		countL.setText("目前魚的數量: " + aquarium.getnFishs() + "/" + Aquarium.maxFishsCount);
		///////////////////////////////////////////////////////////呼叫數量 finish
		add(countL);
	}
	
	private void setfishInformationContent()
	{
		if(chooseFish == null)
			fishInformationT.setText("\n\n\n\n                      選一隻魚吧");
		else {
			for(int i=0;i<10;i++) {
				if(chooseFish==fishs[i]) {
		        	chooseI=i;
					break;
				}
			}
			fishInformationT.setText("\n\n\n\n\n\n\n" + fishsDes[chooseI]);
		}
		//////////////////////////////////////////////////////////////////////補詳細說明
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
		//////////////////////////////////////////////////修改格式
	}
	
	private void refreshcountL()
	{
		countL.setText("目前魚的數量: " + aquarium.getnFishs() + "/" + Aquarium.maxFishsCount);
		
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
		///////////////////////////////////////////////////呼叫 取得數量 count and max ??
		
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
    				//////////////////////////////////////////////////////呼叫++ finish
    				System.out.println(chooseFish);
    				aquarium.addAFish(chooseFish);
    				ftp.addFishTankThing(aquarium.getFishs()[aquarium.getnFishs()-1]);
    				//fishcount++;
    				JOptionPane.showMessageDialog(fishInformationT,"購買成功!!!","通知", JOptionPane.INFORMATION_MESSAGE);
    				lastchoose = -1;
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(fishInformationT,"先選擇魚種!!!","警告", JOptionPane.WARNING_MESSAGE);
    			}
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(fishInformationT,"魚缸滿了!!!","警告", JOptionPane.WARNING_MESSAGE);
    		}
    		refresh();
        }
    };
}

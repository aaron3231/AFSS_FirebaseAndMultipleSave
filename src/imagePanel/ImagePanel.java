package imagePanel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel
{
	private Image im;
	private int set = -1;
	private static final String[] iml = {"Enter", "Fishtank", "Test", "white", "", "",
			                             "孔雀魚", "日光燈", "阿拉伯神仙魚", "紅閃電", "泰國鬥魚", "斑馬魚",
			                             "蓋斑鬥魚", "藍倒吊", "藍頭蝶", "藍魔鬼砲彈", "null", "初級餵食器",
			                             "中級餵食器", "高級餵食器", "初級過濾器", "中級過濾器", "高級過濾器", "初級氧氣泵",
			                             "中級氧氣泵", "高級氧氣泵", "初級加溫器", "中級加溫器", "高級加溫器", "初級照明器",
			                             "中級照明器", "高級照明器", "1x1", "2x1.1", "2x1.2", "2x2.1",
			                             "2x2.2", "2x2.3", "2x2.4", "3x2.1", "3x2.2", "3x2.3",
			                             "3x2.4", "3x2.5", "3x2.6", "水草", "石頭", "珊瑚",
			                             "浮木"};
	private final static String path = "src//image//";
	private static final Image[] ims = { (new ImageIcon(path + "enter.jpg")).getImage(), (new ImageIcon(path + "desk.jpg")).getImage(),
                                         (new ImageIcon(path + "test.png")).getImage(), (new ImageIcon(path + "white.png")).getImage(),
                                         (new ImageIcon(path + "red.png")).getImage(), (new ImageIcon(path + "blue.png")).getImage(),
                                         (new ImageIcon(path + "123.456")).getImage(), (new ImageIcon(path + "日光燈魚3.jpg")).getImage(), // 6-15
                                         (new ImageIcon(path + "阿拉伯神仙魚4.jpg")).getImage(), (new ImageIcon(path + "紅閃電4.jpg")).getImage(),
                                         (new ImageIcon(path + "泰國鬥魚3.jpg")).getImage(), (new ImageIcon(path + "斑馬魚1.jpg")).getImage(),
                                         (new ImageIcon(path + "蓋斑鬥魚4.jpg")).getImage(), (new ImageIcon(path + "藍倒吊1.jpg")).getImage(),
                                         (new ImageIcon(path + "藍頭蝶2.jpg")).getImage(), (new ImageIcon(path + "藍魔鬼砲彈4.jpg")).getImage(),
                                         (new ImageIcon(path + "null.png")).getImage(), (new ImageIcon(path + "餵食器1.jpg")).getImage(),
                                         (new ImageIcon(path + "餵食器2.jpg")).getImage(), (new ImageIcon(path + "餵食器3.jpg")).getImage(),
                                         (new ImageIcon(path + "過濾器1.jpg")).getImage(), (new ImageIcon(path + "過濾器2.jpg")).getImage(),
                                         (new ImageIcon(path + "過濾器3.jpg")).getImage(), (new ImageIcon(path + "打氣機1.jpg")).getImage(),
                                         (new ImageIcon(path + "打氣機2.jpg")).getImage(), (new ImageIcon(path + "打氣機3.jpg")).getImage(),
                                         (new ImageIcon(path + "加溫器1.jpg")).getImage(), (new ImageIcon(path + "加溫器2.jpg")).getImage(),
                                         (new ImageIcon(path + "加溫器3.jpg")).getImage(), (new ImageIcon(path + "照明器1.jpg")).getImage(),
                                         (new ImageIcon(path + "照明器2.jpg")).getImage(), (new ImageIcon(path + "照明器3.jpg")).getImage(),
                                         (new ImageIcon(path + "水草.jpg")).getImage(), (new ImageIcon(path + "石頭1.jpg")).getImage(), //32-44
                                         (new ImageIcon(path + "石頭2.jpg")).getImage(), (new ImageIcon(path + "珊瑚1.jpg")).getImage(),
                                         (new ImageIcon(path + "珊瑚2.jpg")).getImage(), (new ImageIcon(path + "珊瑚3.jpg")).getImage(),
                                         (new ImageIcon(path + "珊瑚4.jpg")).getImage(), (new ImageIcon(path + "浮木1.jpg")).getImage(),
                                         (new ImageIcon(path + "浮木2.jpg")).getImage(), (new ImageIcon(path + "浮木3.jpg")).getImage(),
                                         (new ImageIcon(path + "浮木4.jpg")).getImage(), (new ImageIcon(path + "浮木5.jpg")).getImage(),
                                         (new ImageIcon(path + "浮木6.jpg")).getImage(), (new ImageIcon(path + "水草.jpg")).getImage(),
                                         (new ImageIcon(path + "石頭.jpg")).getImage(), (new ImageIcon(path + "珊瑚.jpg")).getImage(),
                                         (new ImageIcon(path + "浮木.jpg")).getImage() };
	
    public ImagePanel(Image im)
    {
        this.im = im;
        this.setOpaque(true);
    }
    
    public ImagePanel(String im)
    {
    	for(int i = 0; i < iml.length + 1; i++)
    		if(im.equals(iml[i]))
    		{
    			this.im = ims[i];
    			set = i;
    			break;
    		}
    	this.setOpaque(true);
    }
    
    //Draw the back ground
    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    public void fishClickImage()//newfish
    {
    	this.im = ims[5];//////////要改
    	repaint();
    }
    
    public void fishChangeImage(int a)
    {
    	//if*9 換圖/////////////////////////////確認魚種起始位置
    	this.im = ims[6 + a];
    	repaint();
    }
    
    public void deviceSetImage()//device
    {
    	this.im = ims[4];
    	repaint();
    }
    
    public void deviceClickImage()//device
    {
    	this.im = ims[5];
    	repaint();
    }
    
    public void singleImageChange(String im)//status.newfish
    {
    	for(int i = 0; i < iml.length + 1; i++)
    		if(im.equals(iml[i]))
    		{
    			this.im = ims[i];
    			break;
    		}
    	repaint();
    }
    
    public void displayImageChange(String im)//display
    {
    	for(int i = 0; i < iml.length + 1; i++)
    		if(im.equals(iml[i]))
    		{
    			this.im = ims[i];
    			break;
    		}
    	repaint();
    }
    
    public void resetImage()//all
    {
    	this.im = ims[set];
    	repaint();
    }
}
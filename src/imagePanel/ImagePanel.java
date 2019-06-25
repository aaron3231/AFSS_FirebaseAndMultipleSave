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
			                             "�ճ���", "����O", "���ԧB���P��", "���{�q", "���갫��", "������",
			                             "�\������", "�ŭ˦Q", "���Y��", "���]�����u", "null", "���������",
			                             "����������", "����������", "��ŹL�o��", "���ŹL�o��", "���ŹL�o��", "��Ů��",
			                             "���Ů��", "���Ů��", "��ť[�ž�", "���ť[�ž�", "���ť[�ž�", "��ŷө���",
			                             "���ŷө���", "���ŷө���", "1x1", "2x1.1", "2x1.2", "2x2.1",
			                             "2x2.2", "2x2.3", "2x2.4", "3x2.1", "3x2.2", "3x2.3",
			                             "3x2.4", "3x2.5", "3x2.6", "����", "���Y", "����",
			                             "�B��"};
	private final static String path = "src//image//";
	private static final Image[] ims = { (new ImageIcon(path + "enter.jpg")).getImage(), (new ImageIcon(path + "desk.jpg")).getImage(),
                                         (new ImageIcon(path + "test.png")).getImage(), (new ImageIcon(path + "white.png")).getImage(),
                                         (new ImageIcon(path + "red.png")).getImage(), (new ImageIcon(path + "blue.png")).getImage(),
                                         (new ImageIcon(path + "123.456")).getImage(), (new ImageIcon(path + "����O��3.jpg")).getImage(), // 6-15
                                         (new ImageIcon(path + "���ԧB���P��4.jpg")).getImage(), (new ImageIcon(path + "���{�q4.jpg")).getImage(),
                                         (new ImageIcon(path + "���갫��3.jpg")).getImage(), (new ImageIcon(path + "������1.jpg")).getImage(),
                                         (new ImageIcon(path + "�\������4.jpg")).getImage(), (new ImageIcon(path + "�ŭ˦Q1.jpg")).getImage(),
                                         (new ImageIcon(path + "���Y��2.jpg")).getImage(), (new ImageIcon(path + "���]�����u4.jpg")).getImage(),
                                         (new ImageIcon(path + "null.png")).getImage(), (new ImageIcon(path + "������1.jpg")).getImage(),
                                         (new ImageIcon(path + "������2.jpg")).getImage(), (new ImageIcon(path + "������3.jpg")).getImage(),
                                         (new ImageIcon(path + "�L�o��1.jpg")).getImage(), (new ImageIcon(path + "�L�o��2.jpg")).getImage(),
                                         (new ImageIcon(path + "�L�o��3.jpg")).getImage(), (new ImageIcon(path + "�����1.jpg")).getImage(),
                                         (new ImageIcon(path + "�����2.jpg")).getImage(), (new ImageIcon(path + "�����3.jpg")).getImage(),
                                         (new ImageIcon(path + "�[�ž�1.jpg")).getImage(), (new ImageIcon(path + "�[�ž�2.jpg")).getImage(),
                                         (new ImageIcon(path + "�[�ž�3.jpg")).getImage(), (new ImageIcon(path + "�ө���1.jpg")).getImage(),
                                         (new ImageIcon(path + "�ө���2.jpg")).getImage(), (new ImageIcon(path + "�ө���3.jpg")).getImage(),
                                         (new ImageIcon(path + "����.jpg")).getImage(), (new ImageIcon(path + "���Y1.jpg")).getImage(), //32-44
                                         (new ImageIcon(path + "���Y2.jpg")).getImage(), (new ImageIcon(path + "����1.jpg")).getImage(),
                                         (new ImageIcon(path + "����2.jpg")).getImage(), (new ImageIcon(path + "����3.jpg")).getImage(),
                                         (new ImageIcon(path + "����4.jpg")).getImage(), (new ImageIcon(path + "�B��1.jpg")).getImage(),
                                         (new ImageIcon(path + "�B��2.jpg")).getImage(), (new ImageIcon(path + "�B��3.jpg")).getImage(),
                                         (new ImageIcon(path + "�B��4.jpg")).getImage(), (new ImageIcon(path + "�B��5.jpg")).getImage(),
                                         (new ImageIcon(path + "�B��6.jpg")).getImage(), (new ImageIcon(path + "����.jpg")).getImage(),
                                         (new ImageIcon(path + "���Y.jpg")).getImage(), (new ImageIcon(path + "����.jpg")).getImage(),
                                         (new ImageIcon(path + "�B��.jpg")).getImage() };
	
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
    	this.im = ims[5];//////////�n��
    	repaint();
    }
    
    public void fishChangeImage(int a)
    {
    	//if*9 ����/////////////////////////////�T�{���ذ_�l��m
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
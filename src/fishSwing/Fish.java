package fishSwing;

import java.awt.Image;

import imagePanel.ImagePanel;

@SuppressWarnings("serial")
public class Fish extends ImagePanel
{
	//private Image[] fishIms = ;
	
    public Fish(Image im)  
    {
        super(im);
    }
    
    public Fish(String im)
    {
    	super(im);
    }
}
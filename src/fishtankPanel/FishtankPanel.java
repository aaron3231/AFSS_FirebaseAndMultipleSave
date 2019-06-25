package fishtankPanel;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;

import aquarium.Aquarium;
import fishSwing.fishtankThing;
import imagePanel.ImagePanel;
import landScape.Decoration;
import sound.AFSSSound;
import sound.ChooseBgmMenu;
import fish.Fish;
import fish.Fish.FishStatus;
import fish.FishCataLog;

@SuppressWarnings("serial")
public class FishtankPanel extends ImagePanel implements Runnable
{
	
	private ArrayList<fishtankThing> fishs=new ArrayList<fishtankThing>();
	private ArrayList<fishtankThing> displays=new ArrayList<fishtankThing>();
	private ArrayList<fishtankThing> feeds=new ArrayList<fishtankThing>();
	private Boolean [] feedLastIsVisible = new Boolean[50];
	private ArrayList<int[]> feedXY=new ArrayList<int[]>();
	private Boolean stop=false;
	public Boolean getStop() {
		return stop;
	}

	public void setStop(Boolean stop) {
		this.stop = stop;
	}

	Thread Move;
	public Thread getMove() {
		return Move;
	}

	public void setMove(Thread move) {
		Move = move;
	}

	Decoration table[][];
	int sizeX=0,sizeY=0;
	
	private final String imageFilePath = "src//image//";
	
    public FishtankPanel(String im)
    {
    	super(im);
    	set();
    	for(int i=0;i<50;i++) {
    		feeds.add(new fishtankThing(new ImageIcon(imageFilePath + "feed" + ".png").getImage()));
    		feedLastIsVisible[i]=false;
    	}
    	setFeed();
    }
    
    public void addFishTankThing(Fish fish)
    {
    	this.fishs.add(new fishtankThing(fish));
    	fishs.get(fishs.size()-1).setBounds(0, fish.getNowPosition()[1]*12, fish.getWeight(), fish.getWeight());
    	System.out.println(this.Move.getState());
    	add(fishs.get(fishs.size()-1));
    }
    
    public void addDisplay(Decoration table[][],int size)
    {
    	System.out.println(size);
    	int sizeX=0,sizeY=0;
    	if(size==1)
    	{
    		sizeX=2;sizeY=4;
    	}
    	else if(size==2)
    	{
    		sizeX=3;sizeY=6;
    	}
    	else if (size==3)
    	{
    		sizeX=3;sizeY=8;
    	}
    	for(int i=0;i<sizeX;i++)
    	{
    		for(int y=0;y<sizeY;y++)
    		{
    			if(table[i][y]!=null &&(table[i][y].getName().equals("假珊瑚") ||
    					table[i][y].getName().equals("稜角石") ||
    	    			table[i][y].getName().equals("水草") ||
    	    			table[i][y].getName().equals("沉木")))
    			{
    				System.out.println(table[i][y].getName());
    				displays.add(new fishtankThing(new ImageIcon(imageFilePath + table[i][y].getName() + ".png").getImage()));
    				displays.get(displays.size()-1).setDisplayX(y*15);
    				displays.get(displays.size()-1).setDisplayZ(i*15);
    				/////////////////////////////////////////////////////這邊我猜是Size沒設定
    				if(table[i][y].getName().equals("假珊瑚"))
    				{
    					displays.get(displays.size()-1).setBounds(120+y*200-2*100, 600+i*45+45+10-2*200, 2*200, 2*200);
    					System.out.println("add 珊瑚");
    				}
    				else if(table[i][y].getName().equals("沉木"))
    				{
    					displays.get(displays.size()-1).setBounds(120+y*200-2*100, 600+i*45+45+10-3*100, 3*150, 2*150);
    					System.out.println("add 沉木");
    				}
    				else if(table[i][y].getName().equals("水草"))
    				{
    					displays.get(displays.size()-1).setBounds(120+y*200-1*100, 600+i*45+10-1*200, 1*200, 1*200);
    					System.out.println("add 水草");
    				}
    				else
    				{
    					displays.get(displays.size()-1).setBounds(120+y*200-2*100, 600+i*45+10-1*200, 2*200, 1*200);
    					System.out.println("add 石頭");
    				}
    				add(displays.get(displays.size()-1));
    			    //System.out.println("yeeeeeeeeeeeeeeeeeee");
    			}
    			//System.out.println(table[i][y].getName());
    		}
    	}
    	
 
    	this.table=table;
    	this.sizeX=sizeX;
    	this.sizeY=sizeY;
    	
    }
    public void setFeedXY(ArrayList<int[]> feedXY)
    {
    	this.feedXY=feedXY;
    	setFeed();
    	System.out.println("feedssize: "+ feeds.size() + "feesXYsize: "+ feedXY.size());
    }
    
    public void setFeed() 
    {
    	for(int i=0;i<50;i++) {
    		feeds.get(feeds.size()-1).setSize(50,50);
    		feeds.get(feeds.size()-1).setVisible(false);
    	}
    }
    
    private void set()
    {
       Move = new Thread(this);
       Move.start();
    }
    //tempArray[0] = 1(魚) 2(擺設) 3(飼料)
    public void refresh(int i)
    {
    	this.removeAll();
    	ArrayList<int[]> temp=new ArrayList<int[]>();
    	int y=0;
    	for(fishtankThing fish:fishs)
    	{
    		int[] tempArray=new int[4];
    		tempArray[0]=1;
    		tempArray[1]=y;
    		tempArray[2]=fish.getFish().getNowPosition()[0];
    		tempArray[3]=fish.getFish().getNowPosition()[2];
    		temp.add(tempArray);
    		y++;
    	}
    	y=0;
    	for(fishtankThing display:displays)
    	{
    		int[] tempArray=new int[4];
    		tempArray[0]=2;
    		tempArray[1]=y;
    		tempArray[2]=display.getDisplayX();
    		tempArray[3]=display.getDisplayZ();
    		temp.add(tempArray);
    		y++;
    	}
    	
    	
    	Collections.sort(temp, new Comparator<int[]>(){
    		 //@Override
    		 public int compare(int[] o1, int[] o2) {
    		  return o2[3]-o1[3];
    		 }   
    		});
    	
    	for(int[] temp1:temp)
    	{
    		if(temp1[0]==1)
    		{
    			this.add(fishs.get(temp1[1]));
    		}
    		else if(temp1[0]==2)
    		{
    			this.add(displays.get(temp1[1]));
    			System.out.println(displays.get(temp1[1]).getDisplayX());
    		}
    	}
    	for(fishtankThing feed : feeds) {
    		if(feed.isVisible()) {
    			this.add(feed);
    		}
    	}
    }
    
    public void removeFish(ArrayList<Fish> removeFish)
    {
    	for(Fish fish:removeFish)
    	{
    		for(int i=0;i<fishs.size();i++)
    		{
    			if(fishs.get(i).getFish().getFishStatus()==FishStatus.DEATH && fishs.get(i).getFish().getFishNO()==fish.getFishNO()) {
    				fishs.remove(fishs.get(i));
    			}
    		}
    	}
    	refresh(0);
    	this.repaint();
    }
    
    public void reset()
    {
    	this.removeAll();
    	fishs.clear();
    	setFeed();
    	displays.clear();
    	this.repaint();
    	
    }
    
    public void removeDisplay()
    {
    	displays.clear();
    }
    
    public void loadDb(Aquarium aquarium)
    {
    	for(int i=0;i<aquarium.getnFishs();i++)
    	{
    		addFishTankThing(aquarium.getFishs()[i]);
    	}
    	addDisplay(aquarium.getLandSpace().getTable(),aquarium.getEnviroment().getFishTankSize());
    	
    	refresh(0);
    }
    
    public void run()
    {
    	while(stop) {
    		try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	 {
	    	refresh(0);
	  	  	int i;
	  	  	int k=0;
	  	  	
	  	  	//計算move
	  	  	ArrayList<int[]> moveXY=new ArrayList<int[]>();
		  	if(fishs.size()!=0) {
				  for(fishtankThing moveFish:fishs)
				  {
					  int [] move=new int[3];
					  int moveX,moveY,moveZ;
					  moveZ=(moveFish.getFish().getNowPosition()[2]/6)*4-
							  (moveFish.getSize().height-moveFish.getFish().getWeight());
		    		  moveX=moveFish.getFish().getNowPosition()[0]*14
		    				  -moveFish.getX();
		    		  moveY=moveFish.getFish().getNowPosition()[1]*12
		    				  -moveFish.getY();
		    		  move[0]=moveX;
		    		  move[1]=moveY;
		    		  move[2]=moveZ;
		    		  moveXY.add(move);
		    		 
				  }
			}
		  	System.out.println("movesize "+moveXY.size()+" "+fishs.size());
		  	for(i = 0; i < 100; i++)
	        {
	      	  //
		      	  try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	  if(fishs.size()!=0 && moveXY.size()!=0 && fishs.size()==moveXY.size()) {
	        		  int tmp=0;
	        		  for(fishtankThing moveFish:fishs)
	        		  {
	        			  int moveX,moveY,moveZ;
	        			  int size;	//Z軸放大縮小多少
	        			  moveZ=moveXY.get(tmp)[2];
	        			  size=moveFish.getSize().height+
	        					  (int)((((double)moveZ/100))*((1)));
	        			  moveFish.setSize(size,size);
	            		  moveX=moveXY.get(tmp)[0];
	            		  moveY=moveXY.get(tmp)[1];
	            		  moveFish.turn(moveX);
	            		
	            		  moveFish.setLocation(moveFish.getX()+moveX/100,
	            				moveFish.getY()+moveY/100);
	            		  tmp++;
	        		  }
	        	  }
	        	 
	        	 
	        	  //設定沒被吃的飼料
	      		  for(k=0;k<feedXY.size();k++)
	      		  {
	      			  feeds.get(k).setVisible(true);
	      			  feeds.get(k).setLocation(feedXY.get(k)[0]*14,
	      					  feedXY.get(k)[1]*12);
	      			  feedLastIsVisible[k]=true;
	      			  feeds.get(k).setSize(50,50);
	      		  }
	      		  //設定被吃的飼料
	      		  for(fishtankThing fish:fishs)
	      		  {
	      			  int fishFeeds=0;
	      			  for(fishFeeds = 0;fishFeeds<fish.getFish().getFeedArray().size();fishFeeds++)
	      			  {
	      				  feeds.get(k+fishFeeds).setVisible(true);
	      				  feeds.get(k+fishFeeds).setLocation(fish.getFish().getFeedArray().get(fishFeeds)[0]*14,
	      						  fish.getFish().getFeedArray().get(fishFeeds)[1]*12);
	      				  feeds.get(k+fishFeeds).setSize(50,50);
	      				  feedLastIsVisible[k+fishFeeds]=true;
	      			  }
	      			  k+=fishFeeds;
	      		  }
	      		  
	        }
	      //刪掉多餘的飼料
  		  for(int feedSize=k;feedSize<feeds.size();feedSize++)
  		  {
  			  if(feedLastIsVisible[feedSize]) {
  				feedLastIsVisible[feedSize]=false;
  			  }
  			  else
  				  feeds.get(feedSize).setVisible(false);
  		  }
    	}
    	this.run();
    }
}
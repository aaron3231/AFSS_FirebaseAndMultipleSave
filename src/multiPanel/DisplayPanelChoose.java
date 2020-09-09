package multiPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aquarium.Aquarium;
import imagePanel.ImagePanel;
import landScape.Decoration;

@SuppressWarnings("serial")
public class DisplayPanelChoose extends JPanel
{
	private Aquarium aquarium;
	private JPanel littleP = new JPanel();
	private JPanel midP = new JPanel();
	private JPanel bigP = new JPanel();
	
	private JButton putB = new JButton("放置");
	private JButton removeB = new JButton("移除");
	
	private ImagePanel[] lattice = { new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"),
			                         new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"),
			                         new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"),
			                         new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"),
			                         new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"),
			                         new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white"), new ImagePanel("white")};
	private int[] latticeI = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	private JPanel[] lineh = { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
			                   new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
			                   new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
			                   new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
			                   new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
			                   new JPanel(), new JPanel() };
	private JPanel[] linev = { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
            				   new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
            				   new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
            				   new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
            				   new JPanel(), new JPanel(), new JPanel() };
	
	private JPanel[] thingss = { new JPanel(), new JPanel(), new JPanel(), new JPanel() };
	private ImagePanel[] things = { new ImagePanel("水草"), new ImagePanel("石頭"), new ImagePanel("珊瑚"), new ImagePanel("浮木") };
	private JLabel[] thingsL = { new JLabel(), new JLabel(), new JLabel(), new JLabel() };
	
	private final int[] latticetotal = {8, 18, 24};
	private final int[] col = {4, 6, 8};
	private final int[] linetotalh = {12, 24, 32};
	private final int[] linetotalv = {10, 21, 27};
	private final int[] coll = {5, 7, 9};
	private final int big = 100;
	private final int space = 3;
	private final int plus = big + space;
	
	private int size = 2;
	private int choose = -1;
	private int putlegal = -1;
	private int removelegal = -1;
	private int click = -1;
	
	public DisplayPanelChoose(Aquarium a)
	{
		aquarium = a;
		setButton();
		setList();
		count();
	}
	
	private void redraw()
	{
		/////////////呼叫函式
		////////////////拿陣列儲存
		/*
		 * Map()
		 * 20 10 50 100
		 */
		size = aquarium.getEnviroment().getFishTankSize() - 1;
		clean();
		Decoration[][] table = aquarium.getLandSpace().getTable();//拿到table[][]
		
		  for(int i = 0; i < table.length; i++)
			for(int j = 0; j < table[0].length; j++)
			{
				if(table[i][j].getPrice() == 10)
				{
					latticeI[i * table[0].length + j] = 10;
				}
				else if(table[i][j].getPrice() == 20)
				{
					latticeI[i * table[0].length + j] = 20;
				}
				else if(table[i][j].getPrice() == 50)
				{
					latticeI[i * table[0].length + j] = 50;
				}
				else if(table[i][j].getPrice() == 100)
				{
					latticeI[i * table[0].length + j] = 100;
				}
			}
			
		for(int i = 0; i < latticetotal[size]; i++)
		{
			if(latticeI[i] == 10)
				latticeI[i + 1] = 1;
			else if(latticeI[i] == 100)
			{
				latticeI[i + 1] = 2;
				latticeI[i + col[size]] = 3;
				latticeI[i + col[size] + 1] = 4;
			}
			else if(latticeI[i] == 50)
			{
				latticeI[i + 1] = 5;
				latticeI[i + 2] = 6;
				latticeI[i + col[size]] = 7;
				latticeI[i + col[size] + 1] = 8;
				latticeI[i + col[size] + 2] = 9;
			}
		}
		//
		removeAll();
		setButton();
		setList();
		setLabel();
		count();
		bigP.removeAll();
		midP.removeAll();
		littleP.removeAll();
		if(size == 2)
		{
			bigP.setBounds(50, 50, 827, 312);
			bigP.setBackground(Color.decode("#008080"));
			bigP.setLayout(null);
			add(bigP);
		}
		else if(size == 1)
		{
			midP.setBounds(100, 50, 621, 312);
			midP.setBackground(Color.decode("#008080"));
			midP.setLayout(null);
			add(midP);
		}
		else
		{
			littleP.setBounds(200, 100, 415, 209);
			littleP.setBackground(Color.decode("#008080"));
			littleP.setLayout(null);
			add(littleP);
		}
		for(int i = 0; i < latticetotal[size]; i++)
		{
			lattice[i].setBounds(space + plus * (i % col[size]), space + plus * (i / col[size]), big, big);
			lattice[i].setName(" " + i);
			lattice[i].addMouseListener(mP);
			if(size == 2)
				bigP.add(lattice[i]);
			else if(size == 1)
				midP.add(lattice[i]);
			else
				littleP.add(lattice[i]);
		}
		for(int i = 0; i < linetotalh[size]; i++)
		{
			lineh[i].setBounds(space + plus * (i % col[size]), plus * (i / col[size]), big, space);
			lineh[i].setName("" + i);
			if(size == 2)
				bigP.add(lineh[i]);
			else if(size == 1)
				midP.add(lineh[i]);
			else
				littleP.add(lineh[i]);
		}
		for(int i = 0; i < linetotalv[size]; i++)
		{
			linev[i].setBounds(plus * (i % coll[size]), space + plus * (i / coll[size]), space, big);
			linev[i].setName("" + i);
			if(size == 2)
				bigP.add(linev[i]);
			else if(size == 1)
				midP.add(linev[i]);
			else
				littleP.add(linev[i]);
		}
	}
	
	private void setButton()
	{
		putB.setBounds(300, 375, 100, 30);
		putB.addActionListener(bH);
		add(putB);
		removeB.setBounds(450, 375, 100, 30);
		removeB.addActionListener(bH);
		add(removeB);
	}
	
	private void setList()
	{
		for(int i = 0; i < 4; i++)
		{
			thingss[i].removeAll();
			thingss[i].setBounds(80 + 200 * i, 425, 150, 150);
			thingss[i].setBackground(Color.decode("#D2691E"));
			thingss[i].setLayout(null);
			add(thingss[i]);
		}
		for(int i = 0; i < 4; i++)
		{
			things[i].setBounds(5, 5, 140, 140);
			things[i].addMouseListener(mP);
			if(i == 0)
				things[i].setName("-1");
			else if(i == 1)
				things[i].setName("-2");
			else if(i == 2)
				things[i].setName("-4");
			else // i == 3
				things[i].setName("-6");
			thingss[i].add(things[i]);
		}
	}
	
	private void setLabel()
	{
		for(int i = 0; i < 4; i++)
		{
			thingsL[i].setBounds(240 + 200 * i, 425, 50, 50);
			add(thingsL[i]);
		}
	}
	
	private void resetButton()
	{
		for(int i = 0; i < 4; i++)
			thingss[i].setBackground(Color.decode("#D2691E"));
		choose = -1;
	}
	
	private void count()
	{
		for(int i = 0; i < 4; i++)
		{
			thingsL[i].setText("x1");
			if(i == 0)
				thingsL[i].setText("x" + aquarium.getLandSpace().getWaterPlantQuantityQuantity());
			else if(i == 1)
				thingsL[i].setText("x" + aquarium.getLandSpace().getStoneQuantity());
			else if(i == 2)
				thingsL[i].setText("x" + aquarium.getLandSpace().getCoralQuantityQuantity());
			else if(i == 3)
				thingsL[i].setText("x" + aquarium.getLandSpace().getShenmuQuantityQuantity());
			//////////////////////////////////////////呼叫函式
		}
	}
	
	private void resetline()
	{
		for(int i = 0; i < linetotalh[size]; i++)
			lineh[i].setBackground(null);
		for(int i = 0; i < linetotalv[size]; i++)
			linev[i].setBackground(null);
	}
	
	private void Map()
	{
		for(int i = 0; i < latticetotal[size]; i++)
		{
			if(latticeI[i] == 20)
				lattice[i].displayImageChange("1x1");
			else if(latticeI[i] == 10)
				lattice[i].displayImageChange("2x1.1");
			else if(latticeI[i] == 1)
				lattice[i].displayImageChange("2x1.2");
			else if(latticeI[i] == 100)
				lattice[i].displayImageChange("2x2.1");
			else if(latticeI[i] == 2)
				lattice[i].displayImageChange("2x2.2");
			else if(latticeI[i] == 3)
				lattice[i].displayImageChange("2x2.3");
			else if(latticeI[i] == 4)
				lattice[i].displayImageChange("2x2.4");
			else if(latticeI[i] == 50)
				lattice[i].displayImageChange("3x2.1");
			else if(latticeI[i] == 5)
				lattice[i].displayImageChange("3x2.2");
			else if(latticeI[i] == 6)
				lattice[i].displayImageChange("3x2.3");
			else if(latticeI[i] == 7)
				lattice[i].displayImageChange("3x2.4");
			else if(latticeI[i] == 8)
				lattice[i].displayImageChange("3x2.5");
			else if(latticeI[i] == 9)
				lattice[i].displayImageChange("3x2.6");
			else
				lattice[i].displayImageChange("white");
		}
	}
	
	private void clean()
	{
		for(int i = 0; i < latticetotal[size]; i++)
		{
			latticeI[i] = 0;
			lattice[i].resetImage();
		}
	}
	
	public void refresh()
	{
		resetline();
		resetButton();
		redraw();
		//test();
		Map();
		count();
		choose = -1;
		putlegal = -1;
		removelegal = -1;
	}
	
	private int check(int i)
	{
		if(choose == 1)
		{
			if(i < latticetotal[size])
			{
				if(latticeI[i] == 0)
					return 1;
				return 0;
			}
			return -1;
		}
		else if(choose == 2)
		{
			if((i < latticetotal[size]) && ((i + 1) < latticetotal[size]))
			{
				if(i / col[size] != (i + 1) / col[size])
					return -1;
				if((latticeI[i] == 0) && (latticeI[i + 1] == 0))
					return 1;
				return 0;
			}
			return -1;
		}
		else if(choose == 4)
		{
			if((i < latticetotal[size]) && ((i + 1) < latticetotal[size]) &&
			   ((i + col[size]) < latticetotal[size]) && ((i + col[size] + 1) < latticetotal[size]))
			{
				if(i / col[size] != (i + 1) / col[size])
					return -1;
				if((latticeI[i] == 0) && (latticeI[i + 1] == 0) &&
				   (latticeI[i + col[size]] == 0) && (latticeI[i + col[size] + 1] == 0))
					return 1;
				return 0;
			}
			return -1;
		}
		else if(choose == 6)
		{
			if((i < latticetotal[size]) && ((i + 1) < latticetotal[size]) && ((i + 2) < latticetotal[size]) &&
			   ((i + col[size]) < latticetotal[size]) && ((i + col[size] + 1) < latticetotal[size]) && ((i + col[size] + 2) < latticetotal[size]))
			{
				if(i / col[size] != (i + 1) / col[size] || i / col[size] != (i + 2) / col[size])
					return -1;
				if((latticeI[i] == 0) && (latticeI[i + 1] == 0) && (latticeI[i + 2] == 0) &&
				   (latticeI[i + col[size]] == 0) && (latticeI[i + col[size] + 1] == 0) && (latticeI[i + col[size] + 2] == 0))
					return 1;
				return 0;
			}
			return -1;
		}
		else // == -1
		{
			return -2;
		}
	}
	
	private void circle()
	{
		if(choose == 1)
		{
			redlineh(click);
			redlineh(click + col[size]);
			redlinev(click + click / col[size]);
			redlinev(click + click / col[size] + 1);
		}
		else if(choose == 2)
		{
			redlineh(click);
			redlineh(click + col[size]);
			redlinev(click + click / col[size]);
			redlineh(click + 1);
			redlineh((click + 1) + col[size]);
			redlinev((click + 1) + (click + 1) / col[size] + 1);
		}
		else if(choose == 4)
		{
			redlineh(click);
			redlinev(click + click / col[size]);
			redlineh(click + 1);
			redlinev((click + 1) +  (click + 1) / col[size] + 1);
			redlineh((click + col[size]) + col[size]);
			redlinev((click + col[size]) + (click + col[size]) / col[size]);
			redlineh((click + col[size] + 1) + col[size]);
			redlinev((click + col[size] + 1) + (click + col[size] + 1) / col[size] + 1);
		}
		else if(choose == 6)
		{
			redlineh(click);
			redlinev(click + click / col[size]);
			redlineh(click + 1);
			redlineh(click + 2);
			redlinev((click + 2) + (click + 2) / col[size] + 1);
			redlineh((click + col[size]) + col[size]);
			redlinev((click + col[size]) + (click + col[size]) / col[size]);
			redlineh((click + col[size] + 1) + col[size]);
			redlineh((click + col[size] + 2) + col[size]);
			redlinev((click + col[size] + 2) + (click + col[size] + 2) / col[size] + 1);
		}
	}
	
	private int findpoint()
	{
		int clickn = latticeI[click]; //click裡的價格
		if(clickn >= 10)
		{
			if(clickn == 20)
				choose = 1;
			else if(clickn == 10)
				choose = 2;
			else if(clickn == 50)
				choose = 6;
			else // == 100
				choose = 4;
			return click;
		}
		else if(clickn == 1 || clickn == 2 || clickn == 5)
		{
			if(clickn == 1)
				choose = 2;
			else if(clickn == 2)
				choose = 4;
			else // == 5
				choose = 6;
			return click - 1;
		}
		else if(latticeI[click] == 3 || latticeI[click] == 7)
		{
			if(clickn == 3)
				choose = 4;
			else // == 7
				choose = 6;
			return click - col[size];
		}
			
		else if(latticeI[click] == 4 || latticeI[click] == 8)
		{
			if(clickn == 4)
				choose = 4;
			else // == 8
				choose = 6;
			return click - col[size] - 1;
		}
			
		else if(latticeI[click] == 6)
		{
			choose = 6;
			return click - 2;
		}
		else  // == 9
		{
			choose = 6;
			return click - col[size] -2;
		}
	}
	
	private void redlineh(int i)
	{
		lineh[i].setBackground(Color.red);
	}
	
	private void redlinev(int i)
	{
		linev[i].setBackground(Color.red);
	}
	
	private void put()
	{
		if(choose == 1)
		{
			latticeI[click] = 20;
			lattice[click].displayImageChange("1x1");
		}
		else if(choose == 2)
		{
			latticeI[click] = 10;
			lattice[click].displayImageChange("2x1.1");
			latticeI[click + 1] = 1;
			lattice[click + 1].displayImageChange("2x1.2");
		}
		else if(choose == 4)
		{
			latticeI[click] = 100;
			lattice[click].displayImageChange("2x2.1");
			latticeI[click + 1] = 2;
			lattice[click + 1].displayImageChange("2x2.2");
			latticeI[click + col[size]] = 3;
			lattice[click + col[size]].displayImageChange("2x2.3");
			latticeI[click + col[size] + 1] = 4;
			lattice[click + col[size] + 1].displayImageChange("2x2.4");
		}
		else if(choose == 6)
		{
			latticeI[click] = 50;
			lattice[click].displayImageChange("3x2.1");
			latticeI[click + 1] = 5;
			lattice[click + 1].displayImageChange("3x2.2");
			latticeI[click + 2] = 6;
			lattice[click + 2].displayImageChange("3x2.3");
			latticeI[click + col[size]] = 7;
			lattice[click + col[size]].displayImageChange("3x2.4");
			latticeI[click + col[size] + 1] = 8;
			lattice[click + col[size] + 1].displayImageChange("3x2.5");
			latticeI[click + col[size] + 2] = 9;
			lattice[click + col[size] + 2].displayImageChange("3x2.6");
		}
	}
	
	private void removething()
	{
		if(choose == 1)
		{
			latticeI[click] = 0;
			lattice[click].resetImage();
		}
		else if(choose == 2)
		{
			latticeI[click] = 0;
			lattice[click].resetImage();
			latticeI[click + 1] = 0;
			lattice[click + 1].resetImage();
		}
		else if(choose == 4)
		{
			latticeI[click] = 0;
			lattice[click].resetImage();
			latticeI[click + 1] = 0;
			lattice[click + 1].resetImage();
			latticeI[click + col[size]] = 0;
			lattice[click + col[size]].resetImage();
			latticeI[click + col[size] + 1] = 0;
			lattice[click + col[size] + 1].resetImage();
		}
		else if(choose == 6)
		{
			latticeI[click] = 0;
			lattice[click].resetImage();
			latticeI[click + 1] = 0;
			lattice[click + 1].resetImage();
			latticeI[click + 2] = 0;
			lattice[click + 2].resetImage();
			latticeI[click + col[size]] = 0;
			lattice[click + col[size]].resetImage();
			latticeI[click + col[size] + 1] = 0;
			lattice[click + col[size] + 1].resetImage();
			latticeI[click + col[size] + 2] = 0;
			lattice[click + col[size] + 2].resetImage();
		}
	}
	
	private MouseAdapter mP = new MouseAdapter()
    {
        public void mousePressed(MouseEvent e)
        {
        	ImagePanel pnl=(ImagePanel)e.getSource();
        	resetline();
        	if(pnl.getName().equals("-1"))
        	{
        		resetButton();
        		thingss[0].setBackground(Color.red);
  				choose = 1;
  				removelegal = -1;
        	}
        	else if(pnl.getName().equals("-2"))
        	{
        		resetButton();
        		thingss[1].setBackground(Color.red);
  				choose = 2;
  				removelegal = -1;
        	}
        	else if(pnl.getName().equals("-4"))
        	{
        		resetButton();
        		thingss[2].setBackground(Color.red);
  				choose = 4;
  				removelegal = -1;
        	}
        	else if(pnl.getName().equals("-6"))
        	{
        		resetButton();
        		thingss[3].setBackground(Color.red);
  				choose = 6;
  				removelegal = -1;
        	}
        	else
        	{
        		putlegal = -1;
            	//removelegal = -1;
            	for(click = 0; click < latticetotal[size]; click++)
            	{
            		if(lattice[click].getName().equals(pnl.getName()))
            			break;
            	}
            	if(latticeI[click] != 0) //點到物品
            	{
            		resetButton();
            		click = findpoint();
            		removelegal = 1;
            		circle();
            	}
            	else  //沒點到物品
            	{
            		if(removelegal == 1)
            		{
            			choose = -1;
            			System.out.println("選擇擺飾或是點擊擺飾");
            			return;
            		}
            		if(check(click) == 1) 
            		{
            			putlegal = 1;
            			circle();
            			
            		}
            		else if(check(click) == 0) // 可圈 但有物品
            		{
            			circle();
            			//System.out.println("無法放置");
            			////////////////////////////////////////
            		}
            		else if(check(click) == -1) // 超過
            		{
            			System.out.println("選擇有效範圍");
            			///////////////////////////////無法放置
            		}
            		else // == -2 未選
            		{
            			System.out.println("選擇擺飾或是點擊擺飾");
            			//////////////////////////////請先選擇想要放置的擺飾
            		}
            	}
        	}
        }
    };
	
	private ActionListener bH = new ActionListener()
  	{
  		public void actionPerformed(ActionEvent ae) 
        {
  			if(ae.getSource() == putB)
  			{
  				if(putlegal == 1)
  				{
  					//if  //////////////////////////數量判斷
  					if(choose == 1 && aquarium.getLandSpace().getWaterPlantQuantityQuantity() > 0)
  					{
  						put();
  						aquarium.getLandSpace().setWaterPlantInTable(click / col[size], click % col[size]);
  						count();
  					}
  					else if(choose == 2 && aquarium.getLandSpace().getStoneQuantity() > 0)
  					{
  						put();
  						aquarium.getLandSpace().setStoneInTable(click / col[size], click % col[size], 2);
  						count();
  					}
  					else if(choose == 4 && aquarium.getLandSpace().getCoralQuantityQuantity() > 0)
  					{
  						put();
  						aquarium.getLandSpace().setCoralInTable(click / col[size], click % col[size]);
  						count();
  					}
  					else if(choose == 6 && aquarium.getLandSpace().getShenmuQuantityQuantity() > 0)
  					{
  						put();
  						aquarium.getLandSpace().setShenmuInTable(click / col[size], click % col[size]);
  						count();
  					}
  					////////////////////////////////////呼叫函式
  				}
  					
  				else if(check(click) == 0)
  					System.out.println("範圍內有其他擺設");
  				else
  					System.out.println("P點擊無效");
  				resetline();
				resetButton();
  				choose = -1;
				putlegal = -1;
				click = -1;
  			}
  			else if(ae.getSource() == removeB)
  			{
  				if(removelegal == 1)
  				{
  					if(choose == 1)
  					{
  						removething();
  						aquarium.getLandSpace().removeWaterPlant(click / col[size], click % col[size]);
  					}
  					else if(choose == 2)
  					{
  						removething();
  						aquarium.getLandSpace().removeStone(click / col[size], click % col[size]);
  					}
  					else if(choose == 4)
  					{
  						removething();
  						aquarium.getLandSpace().removeCoral(click / col[size], click % col[size]);
  					}
  					else if(choose == 6)
  					{
  						removething();
  						aquarium.getLandSpace().removeShenmu(click / col[size], click % col[size]);
  					}
  					
  				}
  				else
  					System.out.println("R點擊無效");
  				resetButton();
  				resetline();
  				choose = -1;
				removelegal = -1;
				click = -1;
  			}
        }
  	};
}
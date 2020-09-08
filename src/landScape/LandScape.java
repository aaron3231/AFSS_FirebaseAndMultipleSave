package landScape;
import java.util.Scanner;

import cost.Cost;
import database.Cost_data;
import database.Date_data;
import timer.Timerr;

public class LandScape {
	Scanner scanner = new Scanner(System.in);
	
	//種類代號
	private static final int STONE = 1; //石頭
	private static final int SHENMU = 2; //沉木
	private static final int CORAL = 3; //假珊瑚
	private static final int WATERPLANT = 4; //水草
	//最大容量常數
	private static final int MAXITEM_STONE = 24; //石頭最大容納量
	private static final int MAXITEM_SHENMU = 2; //沉木最大容納量
	private static final int MAXITEM_CORAL = 3; //假珊瑚最大容納量
	private static final int MAXITEM_WATERPLANT = 24; //水草最大容納量
	//岩石種類
	public static final int COBBLE = 1; //鵝卵石
	public static final int PRISM = 2; //稜角石 
	
	//LandScape內部變數
	private Decoration table[][]; //魚缸底部做方格化設計
	//物品剩餘數量
	private int stoneQuantity;
	private int cobbleStoneQuantity;
	private int prismStoneQuantity;
	private int shenmuQuantity;
	private int coralQuantity;
	private int waterPlantQuantity;
	
	//傳回資料庫需要使用
	private Cost_data costConnect= new Cost_data();
	private Date_data dateConnect = new Date_data();
	//拋棄式變數
	int i, j, x, y;
	//建構元
	public LandScape() 
	{
		//初始化所有最大容量
		this.stoneQuantity = 0;
		this.cobbleStoneQuantity = 0;
		this.prismStoneQuantity = 0;
		this.shenmuQuantity = 0;
		this.coralQuantity = 0;
		this.waterPlantQuantity = 0;
	}
	//set function
	//依照魚缸大小設定 2D平面 方格 => 用於擺放的二維平面
	public void setTableSize(int fishTankSize) 
	{
		switch(fishTankSize) 
		{
			case 2: //二尺缸
				table = new Decoration[2][4]; //宣告大小
				for(i=0;i<2;i++) 
					for(j=0;j<4;j++) 
						table[i][j] = new Decoration(); //實際new() => 空物件
				System.out.printf("擺設平面大小：2 x 4%n");
				break;
			case 3: //三尺缸
				table = new Decoration[3][6];
				for(i=0;i<3;i++) 
					for(j=0;j<6;j++) 
						table[i][j] = new Decoration();
				System.out.printf("擺設平面大小：3 x 6%n");
				break;
			case 4: //四尺缸
				table = new Decoration[3][8];
				for(i=0;i<3;i++) 
					for(j=0;j<8;j++) 
						table[i][j] = new Decoration();
				System.out.printf("擺設平面大小：3 x 8%n");
				break;
			default:
				System.out.printf("方格輸入錯誤!");
		}
	}
	//設定石頭數量
	public void setStoneQuantity(int input) 
	{
		this.stoneQuantity = input;
	}
	//設定鵝卵石數量
	public void setCobbleStoneQuantity(int input) 
	{
		this.cobbleStoneQuantity = input;
	}
	//設定稜角石數量
	public void setPrismStoneQuantity(int input) 
	{
		this.prismStoneQuantity = input;
	}
	//設定沉木數量
	public void setShenmuQuantityQuantity(int input) 
	{
		this.shenmuQuantity = input;
	}
	//設定珊瑚數量
	public void setCoralQuantityQuantity(int input) 
	{
		this.coralQuantity = input;
	}
	//設定水草數量
	public void setWaterPlantQuantityQuantity(int input) 
	{
		this.waterPlantQuantity = input;
	}
	
	//get function
	//回傳table
	public Decoration[][] getTable()
	{
		return this.table;
	}
	//回傳石頭數量
	public int getStoneQuantity() 
	{
		return this.stoneQuantity;
	}
	//回傳鵝卵石數量
	public int getCobbleStoneQuantity() 
	{
		return this.cobbleStoneQuantity;
	}
	//回傳稜角石數量
	public int getPrismStoneQuantity() 
	{
		return this.prismStoneQuantity;
	}
	//回傳沉木數量
	public int getShenmuQuantityQuantity() 
	{
		return this.shenmuQuantity;
	}
	//回傳珊瑚數量
	public int getCoralQuantityQuantity() 
	{
		return this.coralQuantity;
	}
	//回傳水草數量
	public int getWaterPlantQuantityQuantity() 
	{
		return this.waterPlantQuantity;
	}
	//買石頭
	public void buyStone(int quantity, int category, Cost totalCost, Timerr timer) 
	{
		if(stoneQuantity + quantity <= MAXITEM_STONE)
		{
			
			if(category == COBBLE)
			{
				//將購買資料送到資料庫
				costConnect.insertTable("鵝卵石", 10, dateConnect.SelectDate(timer.toStringToDB()));
				this.cobbleStoneQuantity++;
			}
			else if(category == PRISM)
			{
				//將購買資料送到資料庫
				costConnect.insertTable("稜角石", 10, dateConnect.SelectDate(timer.toStringToDB()));
				this.prismStoneQuantity++;
			}
			stoneQuantity ++;
			totalCost.addnewCost(10);
		}
		else 
			System.out.println("買太多石頭了啦幹");
	}
	//買沉木
	public void buyShenmu(int quantity, Cost totalCost, Timerr timer) 
	{
		if(shenmuQuantity + quantity <= MAXITEM_SHENMU)
		{
			//將購買資料送到資料庫
			costConnect.insertTable("沉木", 50, dateConnect.SelectDate(timer.toStringToDB()));
			shenmuQuantity ++;
			totalCost.addnewCost(50);
		}
		else 
			System.out.println("買太多沉木了啦幹");
	}
	//買假珊瑚
	public void buyCoral(int quantity, Cost totalCost, Timerr timer) 
	{
		if(coralQuantity + quantity <= MAXITEM_CORAL)
		{
			//將購買資料送到資料庫
			costConnect.insertTable("假珊瑚", 100, dateConnect.SelectDate(timer.toStringToDB()));
			coralQuantity ++;
			totalCost.addnewCost(100);
		}
		else 
			System.out.println("買太多珊瑚了啦幹");
	}
	//買水草
	public void buyWaterPlant(int quantity, Cost totalCost, Timerr timer) 
	{
		if(waterPlantQuantity + quantity <= MAXITEM_WATERPLANT)
		{
			//將購買資料送到資料庫
			costConnect.insertTable("水草", 20, dateConnect.SelectDate(timer.toStringToDB()));
			waterPlantQuantity ++;
			totalCost.addnewCost(20);
		}
		else 
			System.out.println("買太多水草了啦幹");
	}
	
	//放置石頭
	public void setStoneInTable(int x, int y, int category) 
	{
		if(stoneQuantity > 0) 
		{
			//放鵝卵石
			if(category == COBBLE)
			{
				if(this.cobbleStoneQuantity > 0)
				{
					if(checkTableSpace(x, y, new Stone(1)))
					{
						System.out.println("x,y: "+ (x+i) + "   " +(y+j));
						table[x][y] = new Stone(COBBLE);
						this.cobbleStoneQuantity--;
						stoneQuantity--;
					}
				}
				else
					System.out.println("鵝卵石不夠");
			}
			//放稜角石
			else if(category == PRISM) 
			{
				if(this.prismStoneQuantity > 0)
				{
					if(checkTableSpace(x, y, new Stone(1)))
					{
						System.out.println("x,y: "+ (x+i) + "   " +(y+j));
						table[x][y] = new Stone(PRISM);
						this.prismStoneQuantity--;
						stoneQuantity--;
					}
				}
				else
					System.out.println("稜角石不夠");
			}
			
		}
		else 
			System.out.println("石頭不夠啦幹");
		
		setDecorationRange(x, y); //設定物件設置範圍
	}
	//放置沉木
	public void setShenmuInTable(int x, int y) 
	{
		if(shenmuQuantity > 0) 
		{
			if(checkTableSpace(x, y,new Shenmu()))
			{
				System.out.println("x,y: "+ (x+i) + "   " +(y+j));
				table[x][y] = new Shenmu();
				shenmuQuantity--;
			} 
		}
		else 
			System.out.println("沉木不夠啦幹");
		setDecorationRange(x, y);
	}
	//放置假珊瑚
	public void setCoralInTable(int x, int y) 
	{
		if(coralQuantity > 0) 
		{
			if(checkTableSpace(x, y,new Coral()))
			{
				System.out.println("x,y: "+ (x+i) + "   " +(y+j));
				table[x][y] = new Coral();
				coralQuantity--;
			}
		}
		else 
			System.out.println("珊瑚不夠啦幹");
		setDecorationRange(x, y);
	}
	//放置水草
	public void setWaterPlantInTable(int x, int y) 
	{
		if(waterPlantQuantity > 0) 
		{
			if(checkTableSpace(x, y,new WaterPlant()))
			{
				System.out.println("x,y: "+ (x+i) + "   " +(y+j));
				table[x][y] = new WaterPlant();
				waterPlantQuantity--;
			}
		}
		else 
			System.out.println("草不夠啦幹");
		setDecorationRange(x, y);
	}

	//設定物件設置範圍  setInTable 時呼叫
	private void setDecorationRange(int x, int y) 
	{
		int width, length;
		width = table[x][y].getSizeX();
		length = table[x][y].getSizeY();
		for(i=0;i<width;i++) 
		{
			for(j=0;j<length;j++) 
			{
				System.out.println("x,y: "+ (x+i) + "   " +(y+j));
				//基準點不做assign -1
				if(i!=0 || j!=0)
					table[x+i][y+j].setPrice(-1);
			}
		}
	}

	//確認物件可以放上去，且放置範圍沒有東西
	private boolean checkTableSpace(int x, int y, Decoration item) 
	{
		//用price 判定 table 上有沒有放物品 => 正常物品放在上面 物品有價格
		//price = 0 空格
		//price = -1 代表是物件使用中 最左上為基準點
		//price other 代表該物件基準點
		
		int width, length;
		width = item.getSizeX();//物件寬
		length = item.getSizeY();//物件長

		//檢查想要放置格子
		for(i=0;i<width;i++) 
		{
			for(j=0;j<length;j++) 
			{
				//放置範圍有東西，回傳false
				if(table[x+i][y+j].getPrice()!= 0) 
				{
					System.out.println("不給你設，人家設上面了");
					return false;
				}
			}
		}
		return true;
	}
	
	//移除石頭
	public void removeStone(int x, int y) 
	{
		//用價格判斷這地方是不是石頭
		if(table[x][y].getPrice() == 10) 
		{
			int width, length;
			width = table[x][y].getSizeX();
			length = table[x][y].getSizeY();
			for(i=0;i<width;i++) 
			{
				for(j=0;j<length;j++) 
				{
					System.out.println("x,y: "+ (x+i) + "   " +(y+j));
					table[x+i][y+j].setPrice(0);
				}
			}
			table[x][y] = new Decoration(); //變成甚麼都沒有的空物件
		}
	}
	//移除沉木
	public void removeShenmu(int x, int y) 
	{
		//用價格判斷這地方是不是石頭
		if(table[x][y].getPrice() == 50) 
		{
			int width, length;
			width = table[x][y].getSizeX();
			length = table[x][y].getSizeY();
			for(i=0;i<width;i++) 
			{
				for(j=0;j<length;j++) 
				{
					System.out.println("x,y: "+ (x+i) + "   " +(y+j));
					table[x+i][y+j].setPrice(0);
				}
			}
			table[x][y] = new Decoration(); //變成甚麼都沒有的空物件
		}
	}
	//移除珊瑚
	public void removeCoral(int x, int y) 
	{
		//用價格判斷這地方是不是石頭
		if(table[x][y].getPrice() == 100) 
		{
			int width, length;
			width = table[x][y].getSizeX();
			length = table[x][y].getSizeY();
			for(i=0;i<width;i++) 
			{
				for(j=0;j<length;j++) 
				{
					System.out.println("x,y: "+ (x+i) + "   " +(y+j));
					table[x+i][y+j].setPrice(0);
				}
			}
			table[x][y] = new Decoration(); //變成甚麼都沒有的空物件
		}
	}
	//移除水草
	public void removeWaterPlant(int x, int y) 
	{
		//用價格判斷這地方是不是石頭
		if(table[x][y].getPrice() == 20) 
		{
			int width, length;
			width = table[x][y].getSizeX();
			length = table[x][y].getSizeY();
			for(i=0;i<width;i++) 
			{
				for(j=0;j<length;j++) 
				{
					System.out.println("x,y: "+ (x+i) + "   " +(y+j));
					table[x+i][y+j].setPrice(0);
				}
			}
			table[x][y] = new Decoration(); //變成甚麼都沒有的空物件
		}
	}
	
	//移動石頭
	public void moveStone(int nowx, int nowy, int newx, int newy) 
	{
		//使用者角度設置 初始格是1 1 但是陣列是0 0
		nowx -= 1;
		nowy -= 1;
		newx -= 1;
		newy -= 1;
		//暫時存放好
		int tempPrice = table[nowx][nowy].getPrice();
		//用價格判斷這地方是不是石頭
		if(table[nowx][nowy].getPrice() == 10) 
		{
			dragUp(nowx, nowy);
			//判斷想要換的方向可不可以放
			if(checkTableSpace(newx, newy, table[nowx][nowy])) 
			{
				dragDown(nowx, nowy, newx, newy, tempPrice);
				setDecorationRange(newx,newy);
			}
			else 
			{
				table[nowx][nowy].setPrice(tempPrice);
			}
		}
	}
	//移動沉木
	public void moveShenmu(int nowx, int nowy, int newx, int newy) 
	{
		//使用者角度設置 初始格是1 1 但是陣列是0 0
		nowx -= 1;
		nowy -= 1;
		newx -= 1;
		newy -= 1;
		//暫時存放好
		int tempPrice = table[nowx][nowy].getPrice();
		//用價格判斷這地方是不是石頭
		if(table[nowx][nowy].getPrice() == 50) 
		{
			dragUp(nowx, nowy);
			//判斷想要換的方向可不可以放
			if(checkTableSpace(newx, newy, table[nowx][nowy])) 
			{
				dragDown(nowx, nowy, newx, newy, tempPrice);
				setDecorationRange(newx,newy);
			}
			else 
			{
				table[nowx][nowy].setPrice(tempPrice);
			}
		}
	}
	//移動珊瑚
	public void moveCoral(int nowx, int nowy, int newx, int newy) 
	{
		//使用者角度設置 初始格是1 1 但是陣列是0 0
		nowx -= 1;
		nowy -= 1;
		newx -= 1;
		newy -= 1;
		//暫時存放好
		int tempPrice = table[nowx][nowy].getPrice();
		//用價格判斷這地方是不是石頭
		if(table[nowx][nowy].getPrice() == 100) 
		{
			dragUp(nowx, nowy);
			//判斷想要換的方向可不可以放
			if(checkTableSpace(newx, newy, table[nowx][nowy])) 
			{
				dragDown(nowx, nowy, newx, newy, tempPrice);
				setDecorationRange(newx,newy);
			}
			else 
			{
				table[nowx][nowy].setPrice(tempPrice);
			}
		}
	}
	//移動水草
	public void moveWaterPlant(int nowx, int nowy, int newx, int newy) 
	{
		//使用者角度設置 初始格是1 1 但是陣列是0 0
		nowx -= 1;
		nowy -= 1;
		newx -= 1;
		newy -= 1;
		//暫時存放好
		int tempPrice = table[nowx][nowy].getPrice();
		//用價格判斷這地方是不是石頭
		if(table[nowx][nowy].getPrice() == 20) 
		{
			dragUp(nowx, nowy);
			//判斷想要換的方向可不可以放
			if(checkTableSpace(newx, newy, table[nowx][nowy])) 
			{
				dragDown(nowx, nowy, newx, newy, tempPrice);
				setDecorationRange(newx,newy);
			}
			else 
			{
				table[nowx][nowy].setPrice(tempPrice);
			}
		}
	}
	
	//假設拿起來 該物件應該不再表格內
	private void dragUp(int x,int y) 
	{
		int width, length;
		width = table[x][y].getSizeX(); //物品寬
		length = table[x][y].getSizeY(); //物品長
	
		for(i=0;i<width;i++) 
		{
			for(j=0;j<length;j++) 
			{
				if(table[x+i][y+j].getPrice()!= 0) 
				{
					table[x+i][y+j].setPrice(0); //暫時先設成0 為了判斷
				}
			}
		}
	}
	//成功放到新位置
	public void dragDown(int nowx, int nowy, int newx, int newy, int price) 
	{
		table[newx][newy] = table[nowx][nowy];
		table[newx][newy].setPrice(price);
		table[nowx][nowy] = new Decoration();
		//System.out.println(table[nowx][nowy]);
		//System.out.println(table[newx][newy]);
	}
	

	//存物品數量
	public String saveQuantityData() 
	{
		String quantityStr;
		quantityStr = String.format("stoneQuantity:%d,shenmuQuantity:%d,coralQuantity:%d,waterPlantQuantity:%d,", this.stoneQuantity, this.shenmuQuantity, this.coralQuantity, this.waterPlantQuantity);
		return quantityStr;
	}
	
	//¦nÀx¦sª©
		public String savetoQuantityData() 
		{
			String quantityStr;
			quantityStr = String.format("%d,%d,%d,%d,", this.stoneQuantity, this.shenmuQuantity, this.coralQuantity, this.waterPlantQuantity);
			return quantityStr;
		}
		public void loadtoQuantityData(String all) 
		{
			String[] value=all.split(",");
			this.setStoneQuantity(Integer.parseInt(value[0]));
			this.setPrismStoneQuantity(Integer.parseInt(value[0]));
			this.setShenmuQuantityQuantity(Integer.parseInt(value[1]));
			this.setCoralQuantityQuantity(Integer.parseInt(value[2]));
			this.setWaterPlantQuantityQuantity(Integer.parseInt(value[3]));
		}
	
	
	
	//存table內容 sizex/sizey/table[][]內容price,耗損度
	public String saveTableData() 
	{
		String tableStr;
		tableStr = String.format("tablelongx:%d,tablelongy:%d", table.length, table[0].length);
		for(i=0;i<table.length;i++) 
		{
			for(j=0;j<table[0].length;j++) 
			{
				tableStr += String.format(",%d,%f", table[i][j].getPrice(), table[i][j].getDamageRateNow());
			}
		}
		return tableStr;
	}
	//¦nÀx¦sª©
		public String savetoTableData() 
		{
			String tableStr;
			tableStr = String.format("%d,%d", table.length, table[0].length);
			for(i=0;i<table.length;i++) 
			{
				for(j=0;j<table[0].length;j++) 
				{
					tableStr += String.format(",%s,%d,%f", table[i][j].getName(),table[i][j].getPrice(), table[i][j].getDamageRateNow());
				}
			}
			return tableStr;
		}
		//¦nÀx¦sª©
			public void loadtoTableData(String all,int fishtankSize) 
			{
				
				String tableStr;
				
				String value[]=all.split(",");
				this.setTableSize(fishtankSize);
				
				int temp=2;
				//tableStr = String.format("%d,%d", table.length, table[0].length);
				//System.out.println("tableSize: "+ table.length+" " +table[0].length);
				for(i=0;i<table.length;i++) 
				{
					for(j=0;j<table[0].length;j++) 
					{
					table[i][j].setName(value[temp]);
					temp++;
					table[i][j].setPrice(Integer.parseInt(value[temp]));
					temp++;
					table[i][j].setDamageRateNow(Double.parseDouble(value[temp]));
			        temp++;		
			        System.out.println("sssss: " +i+","+j+"   " + table[i][j].getName() + "   " +table[i][j].getPrice());
					}
				}
			
			}
		
		
	//在方格上放入東西 for test -------不用重構-------
	public void setInTable() 
	{
		//問使用者想設哪裡
		System.out.println("設在哪個X?");
		x = scanner.nextInt()-1;
		
		System.out.println("設在哪個Y?");
		y = scanner.nextInt()-1;
	
		//用price 判定 table 上有沒有放物品 => 正常物品放在上面 物品有價格
		//price = 0 空格
		//price = -1 代表是物件使用中 最左上為基準點
		//price other 代表該物件基準點
		//menu
		System.out.println("設哪種");
		System.out.println("1.石頭");
		System.out.println("2.沉木");
		System.out.println("3.珊瑚");
		System.out.println("4.水草");
		
		switch(scanner.nextInt()) 
		{
			case STONE:
				System.out.println("設哪種石頭");
				System.out.println("1.鵝卵石");
				System.out.println("2.稜角石");
				setStoneInTable(x,y,scanner.nextInt());
				break;
			case SHENMU:
				setShenmuInTable(x,y);
				break;
			case CORAL:
				setCoralInTable(x,y);
				break;
			case WATERPLANT:
				setWaterPlantInTable(x,y);
				break;
		}

	}
	
	//印出表格上所有東西 for test -------不用重構-------
	public void printTable() 
	{
		int i, j;
		for(i=0;i<table.length;i++) 
		{
			for(j=0;j<table[0].length;j++) 
			{
				// 印price比較好判斷
				System.out.printf("%d  ",table[i][j].getPrice());
			}
			System.out.println();
		}
	}
	
	//購買介面 for test  -------不用重構-------
	public void buy(Cost cost,Timerr timer) 
	{
		//購買列表
		System.out.println("買三小?");
		System.out.println("1.石頭");
		System.out.println("2.沉木");
		System.out.println("3.珊瑚");
		System.out.println("4.水草");
		
		switch(scanner.nextInt()) 
		{
			case STONE:
				System.out.println("買哪種石頭?");
				System.out.println("1.鵝卵石");
				System.out.println("2.稜角石");
				buyStone(1,scanner.nextInt(),cost,timer);
				break;	
			case SHENMU:
				buyShenmu(1,cost,timer);
				break;
			case CORAL:
				buyCoral(1,cost,timer);
				break;
			case WATERPLANT:
				buyWaterPlant(1,cost,timer);
				break;
		}
	}
	
	//移除介面 for test  -------不用重構-------
	public void remove() 
	{
		int nowx, nowy;
		System.out.println("想要刪除的位置??");
		nowx = scanner.nextInt()-1;
		nowy = scanner.nextInt()-1;
		System.out.println("想要刪除哪種物件");
		System.out.println("1.石頭");
		System.out.println("2.沉木");
		System.out.println("3.珊瑚");
		System.out.println("4.水草");
		switch(scanner.nextInt()) 
		{
			case STONE:
				removeStone(nowx, nowy);
				break;
			case SHENMU:
				removeShenmu(nowx, nowy);
				break;
			case CORAL:
				removeCoral(nowx, nowy);
				break;
			case WATERPLANT:
				removeWaterPlant(nowx, nowy);
				break;
		}
	}
	
	//移動介面 for test  -------不用重構-------
	public void move() 
	{
		int nowx, nowy, newx, newy;
		
		System.out.println("輸入舊位置 X 舊位置 Y 新位置 X 新位置 Y");
		nowx = scanner.nextInt();
		nowy = scanner.nextInt();
		newx = scanner.nextInt();
		newy = scanner.nextInt();
		
		System.out.println("想要移動哪種物件");
		System.out.println("1.石頭");
		System.out.println("2.沉木");
		System.out.println("3.珊瑚");
		System.out.println("4.水草");
		switch(scanner.nextInt()) 
		{
			case STONE:
				moveStone(nowx, nowy, newx, newy);
				break;
			case SHENMU:
				moveShenmu(nowx, nowy, newx, newy);
				break;
			case CORAL:
				moveCoral(nowx, nowy, newx, newy);
				break;
			case WATERPLANT:
				moveWaterPlant(nowx, nowy, newx, newy);
				break;
		}
	}
	@Override
	public String toString() 
	{
		String str;
		str =  String.format("石頭數量：%d     沉木數量：%d     珊瑚數量：%d     水草數量：%d %n", this.stoneQuantity, this.shenmuQuantity, this.coralQuantity, this.waterPlantQuantity);
		return str;
	}
}

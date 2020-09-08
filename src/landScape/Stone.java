package landScape;

public class Stone extends Decoration {

		private int category;//岩石種類
		
		//建構元
		public Stone(int category) 
		{
			super();
			this.setPrice(10);
			//在function內一併設定 設備名稱 大小 設備敘述
			this.setCategory(category);
		}
		
		//set function
		public void setCategory(int category) 
		{
			this.category = category;
			//鵝卵石
			if(this.category == COBBLE)
			{
				this.setName("鵝卵石");
				this.setStatement("鵝卵石適合用在裝飾小魚較多的水族箱");
				//設定鵝卵石物件佔表格大小
				//大小 1x1
				this.setSizeX(1);
				this.setSizeY(1);
			}
			//稜角石
			else if(this.category == PRISM)
			{
				this.setName("稜角石");
				this.setStatement("稜角石可泛用於大多數的魚缸內");
				//設定稜角石物件佔表格大小
				//大小 1x2
				this.setSizeX(1);
				this.setSizeY(2);
			}
		}
		
		//get function
		public int getCategory() 
		{
			return this.category;
		}
		
		@Override
		public String toString() 
		{
			String forword = super.toString();
			String str;
			str =  String.format("石頭種類：%s %n", this.category == COBBLE?"鵝卵石":"稜角石");
			return forword + str;
		}
}

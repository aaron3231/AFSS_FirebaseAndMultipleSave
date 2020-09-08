package deviceCatalog;

import fish.Fish;
import aquarium.Aquarium;

public class FishNet extends Device {
	
	public FishNet(String statement) 
	{
		//初始設定好 商品名稱 價格 操作模式
		super("網子", 100, statement, MANUAL);
	}

}

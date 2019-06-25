package fish;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fish.fishClass.oceanFish.ParacanthurusHepatus;

class FishTestCase {

	@Test
	void testFishName() {
		int a;
		
		
		Fish fish=new ParacanthurusHepatus(5,5,5,5,5);
		Fish fish1=new ParacanthurusHepatus(5,5,5,5,5);
		ArrayList<Fish> aliveFish=new ArrayList<Fish>();
		aliveFish.add(fish);
		aliveFish.add(fish1);
		System.out.println(aliveFish.get(0).toString());
		assertEquals(fish.getWeight(),5);
		fish.setWeight(100);
		System.out.println(aliveFish.get(0).toString());
		aliveFish.remove(fish);
		System.out.println(aliveFish.get(0).toString());
		System.out.println(fish.toString());
		
		
		System.out.println(fish.getClass().getName());
	}

}

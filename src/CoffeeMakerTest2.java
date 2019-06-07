import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc326.coffeemaker.*;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;

public class CoffeeMakerTest2 {
	private CoffeeMaker cm;
	private Inventory it;
	private Recipe r1,r2,r3,r4;
	@Before
	public void setup() throws Exception
	{
		cm = new CoffeeMaker();
		it = new Inventory();
		
		r1 = new Recipe();
		r1.setName("IceAmericano");
		r1.setPrice("200");
		r1.setAmtCoffee("2");
		r1.setAmtMilk("2");
		r1.setAmtSugar("2");
		r1.setAmtChocolate("2");
		
		r2 = new Recipe();
		r2.setName("HotAmericano");
		r2.setPrice("300");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("3");
		r2.setAmtSugar("3");
		r2.setAmtChocolate("3");
		
		r3 = new Recipe();
		r3.setName("CaffeLatte");
		r3.setPrice("400");
		r3.setAmtCoffee("4");
		r3.setAmtMilk("4");
		r3.setAmtSugar("4");
		r3.setAmtChocolate("4");
		
		r4 = new Recipe();
		r4.setName("HotChoco");
		r4.setPrice("500");
		r4.setAmtCoffee("5");
		r4.setAmtMilk("5");
		r4.setAmtSugar("5");
		r4.setAmtChocolate("5");
		
	}

	@Test
	public void testAddRecipe() {
		
		assertEquals(true,cm.addRecipe(r1));
		assertEquals(true,cm.addRecipe(r2));
		assertEquals(true,cm.addRecipe(r3));
		assertEquals(false,cm.addRecipe(r4));//3개의 레시피만 추가할 수 있어서 4번째 레시피를 추가하면 실패해야됨.
		
		assertEquals("IceAmericano",cm.getRecipes()[0].getName());//addRecipe가 잘 실행되었는지 확인하기 위해서
		assertEquals("HotAmericano",cm.getRecipes()[1].getName());//레시피의 이름과 테스트를 해보았다.
		assertEquals("CaffeLatte",cm.getRecipes()[2].getName());
		assertEquals("HotChoco",cm.getRecipes()[3].getName());
		
		
	}
	@Test
	public void testAddRecipe2() { //레시피 이름 중복에 대한 테스트.
		
		assertEquals(true,cm.addRecipe(r1));
		r2.setName("IceAmericano");
		assertEquals(false,cm.addRecipe(r2));
		assertEquals("IceAmericano",cm.getRecipes()[0].getName());//레시피의 이름과 테스트를 해보았다.
	
		
		
	}

	@Test
	public void testDeleteRecipe() {
		assertTrue(cm.addRecipe(r1));
		assertTrue(cm.addRecipe(r2));
		assertTrue(cm.addRecipe(r3));
		assertTrue(cm.addRecipe(r4));
		
		assertEquals("IceAmericano",cm.deleteRecipe(0));//첫번째로 넣은 IceAmericano delete을 성공했다.
	}

	@Test //기존의 r1레시피를 r3레시피로의 변경을 테스트함.
	public void testEditRecipe() {
		assertTrue(cm.addRecipe(r1));
		assertTrue(cm.addRecipe(r2));
		cm.editRecipe(0, r3);//r1의 레시피를 r3의 내용으로 수정한다.
		assertEquals("IceAmericano",cm.getRecipes()[0].getName());//이름은 변경하면 안되서 기존 r1의 이름이 그대로 나와야 한다.
		assertEquals("4",cm.getRecipes()[0].getAmtChocolate());//r3의 레시피들
		assertEquals("4",cm.getRecipes()[0].getAmtCoffee());
		assertEquals("4",cm.getRecipes()[0].getAmtMilk());
		assertEquals("4",cm.getRecipes()[0].getAmtSugar());
		assertEquals("400",cm.getRecipes()[0].getPrice());
	}

	@Test
	public void testAddInventory() throws InventoryException {
		String amtCoffee="15";
		String amtMilk="15";
		String amtSugar="15";
		String amtChocolate="15";
		
		try {
			cm.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);
		} catch (InventoryException e) {
			System.out.println("error");//addInventory상의 addSugar메소드의 함수에러로 인해 catch문으로 진입.
		}
		assertEquals(30, it.getCoffee());
		assertEquals(30, it.getMilk());
		assertEquals(30, it.getSugar()); 
		assertEquals(30, it.getChocolate()); //에러발생으로 인해 테스트 불가능.
		
	}
	
	@Test
	public void testCheckInventory() {
		assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n",it.toString());
	}
    
	@Test
	public void testMakeCoffee() {
		assertTrue(cm.addRecipe(r1));
		//r1의 IceAmericano는 200원.
		assertEquals(100,cm.makeCoffee(0, 300));
		assertEquals(13, it.getCoffee()); //13이나와야 하는데  Inventory.coffee += r.getAmtCoffee(); 코드 에러로 인해 Fail.
		assertEquals(13, it.getMilk());
		assertEquals(13, it.getSugar());
		assertEquals(13, it.getChocolate());
	}

}

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
		assertEquals(false,cm.addRecipe(r4));//3���� �����Ǹ� �߰��� �� �־ 4��° �����Ǹ� �߰��ϸ� �����ؾߵ�.
		
		assertEquals("IceAmericano",cm.getRecipes()[0].getName());//addRecipe�� �� ����Ǿ����� Ȯ���ϱ� ���ؼ�
		assertEquals("HotAmericano",cm.getRecipes()[1].getName());//�������� �̸��� �׽�Ʈ�� �غ��Ҵ�.
		assertEquals("CaffeLatte",cm.getRecipes()[2].getName());
		assertEquals("HotChoco",cm.getRecipes()[3].getName());
		
		
	}
	@Test
	public void testAddRecipe2() { //������ �̸� �ߺ��� ���� �׽�Ʈ.
		
		assertEquals(true,cm.addRecipe(r1));
		r2.setName("IceAmericano");
		assertEquals(false,cm.addRecipe(r2));
		assertEquals("IceAmericano",cm.getRecipes()[0].getName());//�������� �̸��� �׽�Ʈ�� �غ��Ҵ�.
	
		
		
	}

	@Test
	public void testDeleteRecipe() {
		assertTrue(cm.addRecipe(r1));
		assertTrue(cm.addRecipe(r2));
		assertTrue(cm.addRecipe(r3));
		assertTrue(cm.addRecipe(r4));
		
		assertEquals("IceAmericano",cm.deleteRecipe(0));//ù��°�� ���� IceAmericano delete�� �����ߴ�.
	}

	@Test //������ r1�����Ǹ� r3�����Ƿ��� ������ �׽�Ʈ��.
	public void testEditRecipe() {
		assertTrue(cm.addRecipe(r1));
		assertTrue(cm.addRecipe(r2));
		cm.editRecipe(0, r3);//r1�� �����Ǹ� r3�� �������� �����Ѵ�.
		assertEquals("IceAmericano",cm.getRecipes()[0].getName());//�̸��� �����ϸ� �ȵǼ� ���� r1�� �̸��� �״�� ���;� �Ѵ�.
		assertEquals("4",cm.getRecipes()[0].getAmtChocolate());//r3�� �����ǵ�
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
			System.out.println("error");//addInventory���� addSugar�޼ҵ��� �Լ������� ���� catch������ ����.
		}
		assertEquals(30, it.getCoffee());
		assertEquals(30, it.getMilk());
		assertEquals(30, it.getSugar()); 
		assertEquals(30, it.getChocolate()); //�����߻����� ���� �׽�Ʈ �Ұ���.
		
	}
	
	@Test
	public void testCheckInventory() {
		assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n",it.toString());
	}
    
	@Test
	public void testMakeCoffee() {
		assertTrue(cm.addRecipe(r1));
		//r1�� IceAmericano�� 200��.
		assertEquals(100,cm.makeCoffee(0, 300));
		assertEquals(13, it.getCoffee()); //13�̳��;� �ϴµ�  Inventory.coffee += r.getAmtCoffee(); �ڵ� ������ ���� Fail.
		assertEquals(13, it.getMilk());
		assertEquals(13, it.getSugar());
		assertEquals(13, it.getChocolate());
	}

}

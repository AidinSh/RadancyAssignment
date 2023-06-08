package RadancyAssignment;

import RadancyAssignment.api.Controller.UserController;
import RadancyAssignment.api.model.User;
import RadancyAssignment.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


@SpringBootTest
class ApiTests {

	UserController userController;

	public ApiTests(){
		userController = new UserController();
	}

	@Test
	public void createTwoUserWithSameName(){
		userController.setUser(new User(3, "Aidin", 1000.0));
		userController.setUser(new User(4, "Aidin", 1500.0));
		Assert.assertEquals(UserService.userList.get(2).getName(), UserService.userList.get(3).getName());
	}

	@Test
	public void createUser(){
		userController.setUser(new User(3, "New User", 1500.0));
		Assert.assertEquals(UserService.userList.get(2).getName(), "New User");
	}

	@Test
	public void deleteUser(){
		userController.deleteUser(1);
		Assert.assertNotEquals(UserService.userList.get(0).getId(), 1);
	}

	@Test
	public void deposit(){
		userController.deposit(2, 500.0);
		Assert.assertEquals(UserService.userList.get(1).getBalance(), 2050.0);
	}

	@Test
	public void withdraw(){
		userController.withdraw(2, 500.0);
		Assert.assertEquals(UserService.userList.get(1).getBalance(), 1050.0);
	}

	@Test
	public void createUserWithLowBalance(){
		String message = userController.setUser(new User(3, "Aidin", 50.0));
		Assert.assertEquals(message, "The balance can't be less than 100$!");
		try{
			Assert.assertNull(UserService.userList.get(2));
		}catch (IndexOutOfBoundsException exception){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void withdrawWithLowBalance(){
		userController.setUser(new User(3,"Aidin", 105.0));
		String message = userController.withdraw(3,50.0);
		Assert.assertEquals(message, "User must have at least 100$ balance !");
		Assert.assertEquals(UserService.userList.get(2).getBalance(), 105.0);
	}

	@Test
	public void withdrawMoreThan90Percent(){
		userController.setUser(new User(3,"Aidin", 1100.0));
		String message = userController.withdraw(3,1000.0);
		Assert.assertEquals(message, "Withdraw must be less than 90% of the balance !");
		Assert.assertEquals(UserService.userList.get(2).getBalance(), 1100.0);
	}

	@Test
	public void depositMoreThanLimit(){
		String message = userController.deposit(0, 11000.0);
		Assert.assertEquals(message, "You can't deposit more than 10,000$ !");
		Assert.assertEquals(UserService.userList.get(0).getBalance(), 1000.0);
	}
}

package services;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import services.v1.user.UserController;
import services.v1.user.UserService;
import utils.model.ServiceResponseModel;

@SpringBootTest
class UserServiceTests {

	UserService userService = new UserService();

	@Test
	void contextLoads() {
		try {
			ServiceResponseModel list = userService.getUsers();
			String json = new Gson().toJson(list);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void encrypt(){
		try{
			String abb = new UserController().encrypt();
		}catch(Exception e){

		}
	}


}

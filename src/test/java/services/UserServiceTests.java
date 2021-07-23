package services;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import services.v1.user.UserController;
import services.v1.user.model.UserModel;
import utils.DBUtil;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class UserServiceTests {

	UserController userController = new UserController();

	@Test
	void contextLoads() {
		try {
			List<UserModel> list = userController.getUsers();
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

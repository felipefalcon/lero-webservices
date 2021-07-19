package services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import services.v1.user.model.UserModel;
import utils.DBConn;

import java.util.List;

@SpringBootTest
class UserServiceTests {

	@Test
	void contextLoads() {
		List<UserModel> list = null;
		try {
			list = new DBConn("DB1", "SELECT username, password FROM tb_user;", UserModel.class).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String abb = "abb";
	}

}

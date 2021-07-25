package services;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import services.v1.auth.user.AuthService;
import utils.model.ServiceResponseModel;


@SpringBootTest
class AuthServiceTests {

	AuthService authService = new AuthService();

	@Test
	void contextLoads() {
		try {
			ServiceResponseModel returnService = authService.login("felipea@gmail.com", "c4ca4238a0b923820dcc509a6f75849b");
			String json = new Gson().toJson(returnService);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

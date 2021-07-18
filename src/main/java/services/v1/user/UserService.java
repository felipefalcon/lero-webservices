package services.v1.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.v1.user.model.UserModel;
import utils.DBConn;
import utils.ServiceResponse;

@SpringBootApplication
@RestController
public class UserService {

	UserController userController = new UserController();

	@GetMapping("/hello")
	public ServiceResponse hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		ServiceResponse response = new ServiceResponse();
		try{
			response.result = new DBConn("dadn1cddkhm83j", "SELECT * FROM tb_user;", UserModel.class).execute();
			return response;
		}catch (Exception e){
			response.success = false;
			response.stacktrace = e.getStackTrace().toString();
			return response;
		}
	}
}

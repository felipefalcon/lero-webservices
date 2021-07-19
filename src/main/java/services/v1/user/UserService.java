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

	private UserController userController = new UserController();

	@GetMapping("/get-users")
	public ServiceResponse getUsers() {
		ServiceResponse response = new ServiceResponse();
		try{
			response.result = userController.getUsers();
			return response;
		}catch (Exception e){
			response.success = false;
			response.stacktrace = e.getStackTrace().toString();
			return response;
		}
	}

// Deixando de exemplo pra usar futurto
//	@GetMapping("/get-users")
//	public ServiceResponse getUsers(@RequestParam(value = "name", defaultValue = "World") String name) {
//		ServiceResponse response = new ServiceResponse();
//		try{
//			response.result = userController.getUsers();
//			return response;
//		}catch (Exception e){
//			response.success = false;
//			response.stacktrace = e.getStackTrace().toString();
//			return response;
//		}
//	}

}

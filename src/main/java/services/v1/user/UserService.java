package services.v1.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.model.ServiceResponseModel;

@SpringBootApplication
@RestController
public class UserService {

	private UserController userController = new UserController();

	@GetMapping("/get-users")
	public ServiceResponseModel getUsers() {
		ServiceResponseModel response = new ServiceResponseModel();
		try{
			response.result = userController.getUsers();
		}catch (Exception e){
			response.success = false;
			response.stacktrace = e.getLocalizedMessage();
		}
		return response;
	}

}

package services.v1.auth.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.model.ServiceResponseModel;

@SpringBootApplication
@RestController
public class AuthService {

	private AuthController authController = new AuthController();

	@GetMapping("/login")
	public ServiceResponseModel login(String email, String password) {
		ServiceResponseModel response = new ServiceResponseModel();
		try{
			response.result = authController.login(email, password);
			return response;
		}catch (Exception e){
			response.success = false;
			response.stacktrace = e.getStackTrace().toString();
			return response;
		}
	}

//	@GetMapping("/encrypt")
//	public ServiceResponseModel encrypt() {
//		ServiceResponseModel response = new ServiceResponseModel();
//		try{
//			response.result = userController.encrypt();
//			return response;
//		}catch (Exception e){
//			response.success = false;
//			response.stacktrace = e.getStackTrace().toString();
//			return response;
//		}
//	}

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

package services.v1.auth.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import utils.model.ServiceResponseModel;

@SpringBootApplication
@RestController
public class AuthService {

	private AuthController authController = new AuthController();

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ServiceResponseModel login(String email, String password) {
		ServiceResponseModel response = new ServiceResponseModel();
		try{
			response.result = authController.login(email, password);
		}catch (Exception e){
			response.success = false;
			response.stacktrace = e.getLocalizedMessage();
		}
		return response;
	}

}

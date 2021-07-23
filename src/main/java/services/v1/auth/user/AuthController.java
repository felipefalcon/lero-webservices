package services.v1.auth.user;

import org.springframework.stereotype.Component;
import services.v1.user.UserController;
import services.v1.user.model.UserModel;
import utils.AESUtil;

@Component
public class AuthController {

	private AuthDAO authDAO = new AuthDAO();
	private UserController userController = new UserController();

	public UserModel login(String email, String password) throws Exception {
		String encryptedPassword = AESUtil.encrypt(password);
		return userController.getUserByAuth(email, encryptedPassword).get(0);
	}

}

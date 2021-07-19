package services.v1.user;

import org.springframework.stereotype.Component;
import services.v1.user.model.UserModel;
import utils.DBConn;
import java.util.List;

@Component
public class UserController {

	private UserDAO userDAO = new UserDAO();

	public List<UserModel> getUsers() throws Exception {
		return userDAO.getUsers();
	}

}

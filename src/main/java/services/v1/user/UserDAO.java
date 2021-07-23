package services.v1.user;

import services.v1.user.model.UserModel;
import utils.DBUtil;

import java.util.HashMap;
import java.util.List;

public class UserDAO {

    public List<UserModel> getUsers() throws Exception {
        try {
            return new DBUtil(
                    "DB1",
                    "SELECT * FROM tb_user;",
                    UserModel.class
            ).execute();
        } catch (Exception e) {
            throw new Exception(e.getStackTrace().toString());
        }
    }

    public List<UserModel> getUserByAuth(String email, String encryptedPassword) throws Exception {
        try {
            HashMap<Integer, Object> paramsQuery = new HashMap<>();
            paramsQuery.put(1, email);
            paramsQuery.put(2, encryptedPassword);
            return new DBUtil(
                    "DB1",
                    "SELECT * FROM tb_user WHERE email = ? AND password = ?;",
                    paramsQuery,
                    UserModel.class
            ).execute();
        } catch (Exception e) {
            throw new Exception(e.getStackTrace().toString());
        }
    }

}

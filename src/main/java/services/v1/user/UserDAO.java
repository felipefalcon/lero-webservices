package services.v1.user;

import services.v1.user.model.UserModel;
import utils.DBConn;

import java.util.List;

public class UserDAO {

    public List<UserModel> getUsers() throws Exception {
        List<UserModel> list = null;
        try {
            list = new DBConn("DB1", "SELECT * FROM tb_user;", UserModel.class).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

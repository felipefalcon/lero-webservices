package services.v1.user;

import org.springframework.stereotype.Component;
import services.v1.user.model.UserModel;
import utils.AESUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Component
public class UserController {

	private UserDAO userDAO = new UserDAO();

	public List<UserModel> getUsers() throws Exception {
		return userDAO.getUsers();
	}

	public List<UserModel> getUserByAuth(String email, String encryptedPassword) throws Exception {
		return userDAO.getUserByAuth(email, encryptedPassword);
	}

	public String encrypt() throws Exception {

		String originalString = "howtodoinjava.com";
		String encryptedString = AESUtil.encrypt(originalString) ;
		// Gmp964ioDY2e5Fi+U0HjPFb5u2Mq4PsAsMuaLWGvwb4=
		String decryptedString = AESUtil.decrypt(encryptedString) ;

		System.out.println(originalString);
		System.out.println(encryptedString);
		System.out.println(decryptedString);
		return decryptedString;
	}

}

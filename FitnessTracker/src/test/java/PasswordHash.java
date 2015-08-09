import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import junit.framework.TestCase;

public class PasswordHash extends TestCase {
	
	public void testBCryptHash(){
		String password = "password";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		
	}
	
	public void testMD5Hash() {
		String password = "password";   // 5f4dcc3b5aa765d61d8327deb882cf99
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String hashedPassword = passwordEncoder.encodePassword(password, null);
		System.out.println(hashedPassword);
	}
}

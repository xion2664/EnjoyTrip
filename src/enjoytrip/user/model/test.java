package enjoytrip.user.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class test {
	public static void main(String[] args) {
		String pw = "testpw123!";
		
		String encrypted = BCrypt.hashpw(pw, BCrypt.gensalt());
		System.out.println("암호화된 pw : " + encrypted);
		
		// 맞는 비번을 넣는다면?
		System.out.println(BCrypt.checkpw(pw, encrypted));
		// 틀린 비번을 넣는다면?
		System.out.println(BCrypt.checkpw(pw + "히히", encrypted));
	}
}

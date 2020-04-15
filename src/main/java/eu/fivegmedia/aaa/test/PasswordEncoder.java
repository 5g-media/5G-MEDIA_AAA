package eu.fivegmedia.aaa.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		System.out.println("Original user is  $2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
		System.out.println("Encrypted user is " + new BCryptPasswordEncoder().encode(args[0]));

	}

}

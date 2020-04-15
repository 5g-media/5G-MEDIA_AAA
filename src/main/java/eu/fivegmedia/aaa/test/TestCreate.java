package eu.fivegmedia.aaa.test;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TestCreate {

	public static void main(String[] args) throws Exception {
		Date validity = new Date();
		String token1 = Jwts.builder()
	    .setSubject("subject1")
	    .claim("claim", "authorities")
	    .claim("ms_create", System.currentTimeMillis())
	    .signWith(SignatureAlgorithm.HS512, "mySecretKey")
	    .setExpiration(validity)
	    .compact();
		
		System.out.println(token1);
		
		String token2 = Jwts.builder()
	    .setSubject("subject1")
	    .claim("claim", "authorities")
	    .claim("ms_create", System.currentTimeMillis())
	    .signWith(SignatureAlgorithm.HS512, "mySecretKey")
	    .setExpiration(validity)
	    .compact();
		
		System.out.println(token2);

		System.out.println(token1.equals(token2));

	}

}

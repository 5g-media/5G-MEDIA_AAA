package eu.fivegmedia.aaa.test;

import io.jsonwebtoken.Jwts;

public class TestJWT {

	//chop off the signature to allow jjwt to parse the content of the token
	public static void main(String[] args) throws Exception{
		String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTUzMTg0MjAwOX0.";//7TqFd6hYdk84_yy05x_HltZSJfiQDcITVZScxS1JLIq9GZKJMkYwPBMZMSjqpycgrMicTar-y0V6Eb7JYJus_A";
		
		System.out.println(jwt);
		System.out.println(Jwts.parser().parse(jwt).getBody());
	}

}

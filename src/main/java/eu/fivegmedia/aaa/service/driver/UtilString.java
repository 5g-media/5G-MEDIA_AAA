package eu.fivegmedia.aaa.service.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UtilString {

	public static Properties getPropertiesFromString(String s) {
		Properties properties = new Properties();
		for(String prop : s.split(",")) {
			String[] propNameValue = prop.split("=");
			properties.setProperty(propNameValue[0], propNameValue[1]);
		}
		
		return properties;
	}
	
	public static Properties getPropertiesFromJson(String s) {
		Properties properties = new Properties();

		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(s).getAsJsonObject();
		for(String key : jsonObject.keySet()) {
			properties.setProperty(key, jsonObject.get(key).getAsString());
		}
		
		return properties;
	}
	
	public static Properties getPropertiesFromLocalPath2(String path) {
		Properties properties = new Properties();

		try(FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/" + path)) {
			properties.load(fis);
			
			return properties;
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}

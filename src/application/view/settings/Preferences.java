package application.view.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Preferences {

	int nDaysUserWithoutFine;
	float finePerDay;
	String username;
	String password;
	
	
	public Preferences(){
		nDaysUserWithoutFine= 14;
		finePerDay = 2;
		username = "admin";
		setPassword("admin");
		password = getPassword();
	}
	
	public Preferences(int nDaysUserWithoutFine, float finePerDay, String username, String password) {
		this.nDaysUserWithoutFine = nDaysUserWithoutFine;
		this.finePerDay = finePerDay;
		this.username = username;
		this.password = DigestUtils.shaHex(password);
	}



	public int getnDaysUserWithoutFine() {
		return nDaysUserWithoutFine;
	}

	public void setnDaysUserWithoutFine(int nDaysUserWithoutFine) {
		this.nDaysUserWithoutFine = nDaysUserWithoutFine;
	}

	public float getFinePerDay() {
		return finePerDay;
	}

	public void setFinePerDay(float finePerDay) {
		this.finePerDay = finePerDay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.shaHex(password);
	}
	
	public static void createConfig() {
		File file = new File("E:/@WorkSpace Programming/workspace_ProjetoCadastroComFX/X_LibraryAssistant2018/config.txt");
		try {
			if(!file.exists()) {
				Preferences preference = new Preferences();
				Writer writer = new FileWriter("config.txt");
				Gson gson = new Gson();
				gson.toJson(preference, writer);
				writer.close();
			}
		} catch (IOException e) {
			createConfig();
		} 
	}
	
	public static Preferences readConfig() {
		Preferences preference = new Preferences();
		try {
			Gson gson = new Gson();
			preference = gson.fromJson(new FileReader("config.txt"), Preferences.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return preference;
	}
	
	
	
	
}

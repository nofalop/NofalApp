package nofal.nofapp.MainForm;

import java.io.File;

public class User {
	private String name;
	private String password;
	private String email;
	private String gender;
	private String userpfp;
	private boolean isOnline;


	public User(){

	}

	public User(String name, String password, String email, String gender, String userpfp, boolean isOnline){
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.userpfp = userpfp;
		this.isOnline = isOnline;
	}

	public String getName(){ return name;}
	public void setName(String name) {this.name = name;}

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }

	public String getUserPfp(){return userpfp.replace("\\", "/");}
	public void setUserPfp(String userpfp){this.userpfp = userpfp; }

	public boolean getUserStatus(){return isOnline;}
	public void setUserStatus(boolean isOnline){this.isOnline = isOnline; }

}

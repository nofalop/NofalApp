package nofal.nofapp.MainForm;

public class User {
	private String name;
	private String password;
	private String email;
	private String gender;


	public User(){

	}

	public User(String name, String password, String email, String gender){
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}

	public String getName(){ return name;}
	public void setName(String name) {this.name = name;}

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }

}

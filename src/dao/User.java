package dao;

public class User {
	
	public String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean delete_flag;
	
	public String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail_adress() {
		return mail_adress;
	}

	public void setMail_adress(String mail_adress) {
		this.mail_adress = mail_adress;
	}

	public String getbirthday() {
		return birthday;
	}

	public void setbirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String password;
	
	public String mail_adress;
	
	public String birthday;
	
	public String sex;
}

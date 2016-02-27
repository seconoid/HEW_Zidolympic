package user;

public class User {
	
	public int member_no; // １意のID
	public String id; // ユーザID
	public String name; // ユーザ名
	public String password; // パスワード
	public String mail_adress; // メールアドレス
	public String birthday; // 誕生日
	public String sex; // 性別
	public int point; // 所有ポイント
	public boolean delete_flag; // 削除判定（true で凍結アカウント）
	
	// getter & setter
	public int getNo() {
		return member_no;
	}
	
	public void setNo(int member_no) {
		this.member_no = member_no;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
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
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}

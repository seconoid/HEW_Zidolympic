package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDao{
	/**
	 * 接続オブジェクトを生成して返す
	 * @return 接続オブジェクト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection()
		throws ClassNotFoundException
						, SQLException{
		
		//// properties より環境変数を取得する
		// propertiesより読み込み
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle("path");
		}catch (MissingResourceException e) {
			e.printStackTrace();
		}
		// パスを取得
		String db = bundle.getString("db");
		String url = "";
	
		// 正規表現で抜き取り(""が入り込んでくるため）
		Pattern p = Pattern.compile("^\"(.+)\"$");
		Matcher m = p.matcher(db);
		if (m.find()){
			System.out.println(m.group(1));
			url = m.group(1);
		}
		
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				url, "root", "");
	}
	
	// ユーザ照会
	public User select(String id, String password){
		// 戻り値
		User user = null;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						// SQL
						"select * from Member where id = ? and password = ? ");
						){
					// ? を置き換え
					ps.setString(1, id);
					ps.setString(2, password);
					
					// 結果を代入
					ResultSet rs = ps.executeQuery();
					if(rs.next()){
						user = new User();
						user.setNo(rs.getInt("member_no"));
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
					}
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}
		return user;
	}
	
	// 新規ユーザ登録
	public int insert(String id, String name, String password, String mail_adress ,String birthday, String sex,boolean delete_flag){
		int count = 0;
		try(
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into Member(id, name, password, Mail_adress, birthday, sex, delete_flag) values(?,?,?,?,?,?,?)");
				){
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, password);
			ps.setString(4, mail_adress);
			ps.setString(5, birthday);
			ps.setString(6, sex);
			ps.setBoolean(7, delete_flag);
			count = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	// 認証
	public User auth(String id, String password){
		// 戻り値
		User user = null;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						// SQL
						"select * from Member where id = ? and password = ? ");
						){
					// ? を置き換え
					ps.setString(1, id);
					ps.setString(2, password);
					
					// 結果を代入
					ResultSet rs = ps.executeQuery();
					if(rs.next()){
						user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setMail_adress(rs.getString("mail_adress"));
						user.setbirthday(rs.getString("birthday"));
					}
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}
		return user;
	}
	
	public int update(String id, String name, String password, String mail_adress ,String birthday){
		// 更新件数
		int count = 0;
		
		try(
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					// SQL
					"update member set id = ?, name = ?, mail_adress = ?, birthday = ?  where id = ? and password = ?");
					){
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, mail_adress);
			ps.setString(4, birthday);
			ps.setString(5, id);
			ps.setString(6, password);
			count = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
}
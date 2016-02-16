package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/hew?characterEncoding=utf8"
				, "root"
				, "");
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
	public int insert(String id, String name, String password, String Mail_adress ,String birthday, String sex,boolean delete_flag){
		int count = 0;
		try(
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into Member(id, name, password, Mail_adress, birthday, sex, delete_flag) values(?,?,?,?,?,?,?)");
				){
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, password);
			ps.setString(4, Mail_adress);
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
	
}
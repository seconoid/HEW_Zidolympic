package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ZIdolyDao{
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
				"jdbc:mysql://localhost/hew2016?characterEncoding=utf8"
				, "root"
				, "");
	}
	
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
			return count;
		}

		return count;
	}
	
	
}
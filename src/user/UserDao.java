package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utility.AbstractDAO;

public class UserDao extends AbstractDAO{
	// ユーザ照会
	public User select(String id, String password){
		// 戻り値
		User user = null;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						// SQL
						"select member.member_no, id, name, possession.point "
						+ "from member, possession "
						+ "where member.member_no = possession.member_no "
						+ "and id = ? "
						+ "and password = ? ");
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
						user.setPoint(rs.getInt("point"));
					}
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}
		return user;
	}
	
	// 会員番号のみ取得
	public int selectNo(String id, String password){
		int no = 0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						// SQL
						"select member_no from member where id = ? and password = ?");
						){
					// ? を置き換え
					ps.setString(1, id);
					ps.setString(2, password);
					
					// 結果を代入
					ResultSet rs = ps.executeQuery();
					if(rs.next()){
						no = rs.getInt("member_no");
					}
				}catch(Exception e){
					e.printStackTrace();
					return 0;
				}
		return no;
	}
	
	// 新規ユーザ登録
	public int insert(String id, String name, String password, String mail_adress ,String birthday, String sex,boolean delete_flag){
		int count = 0;
		// 会員を追加
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
						user.setNo(rs.getInt("member_no"));
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
	
	// ユーザ情報更新
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
	
	// 初期ポイントを付与
	public int pointInsert(int no, int point){
		// 更新件数
		int count = 0;
		
		try(
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into possession values(?,?)");
				){
			ps.setInt(1, no);
			ps.setInt(2, point);
			
			count = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
}
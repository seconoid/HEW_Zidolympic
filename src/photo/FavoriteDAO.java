package photo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FavoriteDAO {

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

	
	public int select(String name,int con_id){
		int member_no=0;
		int count=0;
		try(
				Connection con = getConnection();
						
				PreparedStatement ps = con.prepareStatement(
						"select * from Member where name=?");
				){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				member_no=rs.getInt("member_no");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"insert into Favorite values(?,?)");
				){
			ps.setInt(1, con_id);
			ps.setInt(2, member_no);
			
			count=ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		
		return count;
	}
	
	
	
	
	public int delete(String name,int con_id){
		int member_no=0;
		int count=0;
		try(
				Connection con = getConnection();
						
				PreparedStatement ps = con.prepareStatement(
						"select * from Member where name=?");
				){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				member_no=rs.getInt("member_no");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
				try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"delete from Favorite where contribution_id=? and menber_no~?");
				){
			ps.setInt(1, con_id);
			ps.setInt(2, member_no);
			
			count=ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
}

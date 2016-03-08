package photo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

	
	public int select(int user_no,int con_id){
		int count=0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select * from Favorite where contribution_id=? and member_no=?");
				){
			ps.setInt(1, con_id);
			ps.setInt(2, user_no);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
			count++;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
	
	
	
	public int insert(int user_no,int con_id){
		int count=0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"insert into Favorite values(?,?)");
				){
			ps.setInt(1, con_id);
			ps.setInt(2, user_no);
			count=ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
	
	public int delete(int user_no,int con_id){
		int count=0;
	
				try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"delete from Favorite where contribution_id=? and member_no=? ");
				){
			ps.setInt(1, con_id);
			ps.setInt(2, user_no);
			count=ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		
		return count;
	}
	
	
	
	public List<Fov> com_select(int con_id){
		List<Fov> com=new ArrayList<Fov>();
		
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select a.name,b.contribution_timestamp,b.comment,e.name from competition a,contribution b,contribution_details c,title d,member e where b.contribution_id=c.contribution_id and c.title_id=d.title_id and d.competition_id=a.competition_id and b.member_no=e.member_no and b.contribution_id=?");
				){
			ps.setInt(1, con_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				Fov f=new Fov();
				f.setComname(rs.getString("a.name"));
				f.setDate(rs.getDate("b.contribution_timestamp"));
				f.setComment(rs.getString("b.comment"));
				f.setName(rs.getString("e.name"));
				com.add(f);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return com;
	}
	
	
	
	
	public List<Fov> com2_select(int con_id){
		List<Fov> com2=new ArrayList<Fov>();
		
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select * from contribution_details where contribution_id=?");
				){
			ps.setInt(1, con_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Fov f=new Fov();
				f.setImg_pass(rs.getString("img_pass"));
				System.out.println(rs.getString("img_pass"));
				com2.add(f);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return com2;
	}
	
	
	
	
}

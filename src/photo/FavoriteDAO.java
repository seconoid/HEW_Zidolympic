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
						"delete from Favorite where contribution_id=? and member_no=?");
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
						"select x.name,b.contribution_timestamp from competition x,title y,contribution_details z,tag a,contribution b "
						+ "where a.contribution_id=z.contribution_id and z.title_id=y.title_id and "
						+ "x.competition_id=y.competition_id and z.contribution_id=?");
				){
			ps.setInt(1, con_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				Fov f=new Fov();
				f.setComname(rs.getString("x.name"));
				f.setDate(rs.getDate("b.contribution_timestamp"));
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
	
	
	
	
	
	
	
	
	
}

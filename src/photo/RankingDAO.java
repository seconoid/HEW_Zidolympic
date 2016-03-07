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

public class RankingDAO {

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

	
	
	public List<Competition> try_select(){
		List<Competition> trylist=new ArrayList<Competition>();
		int a=0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select a.name,b.name,c.contribution_id,c.img_pass,d.score from Competition a ,title b,contribution_details c,contribution d where a.Competition_id=1 and c.contribution_id=d.contribution_id order by d.score asc");
				){
			
			ResultSet rs = ps.executeQuery();
			
			for(int c=0;rs.next()&&c<3;c++){
				Competition t=new Competition();
				if(rs.getInt("c.contribution_id")!=a){
					t.setComname(rs.getString("a.name"));
					t.setTitlename(rs.getString("b.name"));
					t.setContribution_id(rs.getInt("c.contribution_id"));
					t.setImg_pass(rs.getString("c.img_pass"));
					t.setScore(rs.getDouble("d.score"));
					a=rs.getInt("c.contribution_id");
					trylist.add(t);
				}else{
					c--;
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return trylist;
	}
	
	
	
	
	
	
	public List<Competition> synchro_select(){
		List<Competition> synchrolist=new ArrayList<Competition>();
		int a=0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select a.name,b.name,c.contribution_id,c.img_pass,d.score from Competition a ,title b,contribution_details c,contribution d where a.Competition_id=2 and c.contribution_id=d.contribution_id order by d.score desc");
				){
			
			ResultSet rs = ps.executeQuery();
			
			for(int c=0;rs.next()&&c<3;c++){
				Competition t=new Competition();
				if(rs.getInt("c.contribution_id")!=a){
					t.setComname(rs.getString("a.name"));
					t.setTitlename(rs.getString("b.name"));
					t.setContribution_id(rs.getInt("c.contribution_id"));
					t.setImg_pass(rs.getString("c.img_pass"));
					t.setScore(rs.getDouble("d.score"));
					a=rs.getInt("c.contribution_id");
					synchrolist.add(t);
				}else{
					c--;
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return synchrolist;
	}
	
	
	
	
	public List<Competition> splatorch_select(){
		List<Competition> splatorchlist=new ArrayList<Competition>();
		int a=0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select a.name,b.name,c.contribution_id,c.img_pass,d.score from Competition a ,title b,contribution_details c,contribution d where a.Competition_id=3 and c.contribution_id=d.contribution_id order by d.score desc");
				){
			
			ResultSet rs = ps.executeQuery();
			
			for(int c=0;rs.next()&&c<3;c++){
				Competition t=new Competition();
				if(rs.getInt("c.contribution_id")!=a){
					t.setComname(rs.getString("a.name"));
					t.setTitlename(rs.getString("b.name"));
					t.setContribution_id(rs.getInt("c.contribution_id"));
					t.setImg_pass(rs.getString("c.img_pass"));
					t.setScore(rs.getDouble("d.score"));
					a=rs.getInt("c.contribution_id");
					splatorchlist.add(t);
				}else{
					c--;
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return splatorchlist;
	}
	
	
	
	
	
}

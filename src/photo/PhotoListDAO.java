
package photo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhotoListDAO {

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

	
	public ArrayList<PhotoList> select(){
		ArrayList<PhotoList> list = new ArrayList<PhotoList>();
		int a=0;
		//tryの()内に書くと必要に応じてクローズしてくれる
		try(
				Connection con = getConnection();
						//select * from Contribution_details order by contribution_id desc
				PreparedStatement ps = con.prepareStatement("SELECT contribution_details.contribution_id, contribution_details.title_id, contribution_details.img_pass, img_title, count, member_no FROM contribution INNER JOIN contribution_details ON contribution.contribution_id = contribution_details.contribution_id WHERE contribution.contribution_id NOT IN (SELECT favorite.contribution_id FROM favorite) order by contribution_details.contribution_id desc");
				
				){
			//SQL実行と結果セットの受け取り
			ResultSet rs = ps.executeQuery();
			//結果セットからオブジェクトにデータを入れる
			while(rs.next()){
				//次のデータが存在したらオブジェクトを生成する
				PhotoList p = new PhotoList();
				if(rs.getInt("contribution_details.contribution_id")!=a){
					p.setContribution_id(rs.getInt("contribution_details.contribution_id"));
					p.setImg_pass(rs.getString("contribution_details.img_pass"));
					p.setImg_title(rs.getString("contribution_details.img_title"));
					a=rs.getInt("contribution_details.contribution_id");
					list.add(p);
				}
				//生成したオブジェクトにデータをセットする
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		return list;
	}

	
	public ArrayList<PhotoList> tag_select(String tag){
		ArrayList<PhotoList> list = new ArrayList<PhotoList>();
		
		//tryの()内に書くと必要に応じてクローズしてくれる
		try(
				Connection con = getConnection();
						
				PreparedStatement ps = con.prepareStatement(""
						+ "select * from Contribution_details x,contribution y,tag z"
						+ " where x.contribution_id=y.contribution_id and z.contribution_id=y.contribution_id"
						+ " and z.name=? order by x.contribution_id desc");
				
				){
			//SQL実行と結果セットの受け取り
			ps.setString(1, tag);
			ResultSet rs = ps.executeQuery();
			//結果セットからオブジェクトにデータを入れる
			while(rs.next()){
				//次のデータが存在したらオブジェクトを生成する
				PhotoList p = new PhotoList();
				//生成したオブジェクトにデータをセットする
				p.setContribution_id(rs.getInt("x.contribution_id"));
				System.out.println("きてるっぽい"+rs.getInt("x.contribution_id"));
				p.setImg_pass(rs.getString("x.img_pass"));
				p.setImg_title(rs.getString("x.img_title"));
				//生成したオブジェクトをリストに追加
					list.add(p);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		return list;
	}
///////////////////////
	public ArrayList<PhotoList> Search_select(String search,String url,int co){
		ArrayList<PhotoList> list = new ArrayList<PhotoList>();
		int a=0;
		//tryの()内に書くと必要に応じてクローズしてくれる
		try(
				Connection con = getConnection();
						
				PreparedStatement ps = con.prepareStatement(url);
				
				){
			System.out.println("SQL終わり");
			//SQL実行と結果セットの受け取り
				ps.setString(1, "%"+search+"%");
				ps.setString(2, "%"+search+"%");
				ps.setString(3, "%"+search+"%");
				if(co==5){
					ps.setString(4, "%"+search+"%");
					System.out.println("<><>"+url);
				}
			ResultSet rs = ps.executeQuery();
			//結果セットからオブジェクトにデータを入れる
			while(rs.next()){
				//次のデータが存在したらオブジェクトを生成する
				PhotoList p = new PhotoList();
				//生成したオブジェクトにデータをセットする
				if(rs.getInt("contribution_details.contribution_id")!=a){
					p.setContribution_id(rs.getInt("contribution_details.contribution_id"));
					p.setImg_pass(rs.getString("contribution_details.img_pass"));
					p.setImg_title(rs.getString("contribution_details.img_title"));
					a=rs.getInt("contribution_details.contribution_id");
					list.add(p);
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		return list;
	}

///////////////////////
	
	
	public ArrayList<PhotoList> mypageSelect(int no){
		
		ArrayList<PhotoList> list = new ArrayList<PhotoList>();
		
		///// tryの()内に書くと必要に応じてクローズしてくれる
		try(
				Connection con = getConnection();
						
				// ログインしているユーザが投稿した写真を取得
				PreparedStatement ps = con.prepareStatement(
						"select cd.contribution_id, img_pass, img_title "
						+ "from contribution as c, contribution_details as cd "
						+ "where c.contribution_id = cd.contribution_id "
						+ "and member_no = ? order by contribution_id desc;"
						);
				){
			// ?の置換
			ps.setInt(1, no);
			//SQL実行と結果セットの受け取り
			ResultSet rs = ps.executeQuery();
			//結果セットからオブジェクトにデータを入れる
			while(rs.next()){
				//次のデータが存在したらオブジェクトを生成する
				PhotoList p = new PhotoList();
				//生成したオブジェクトにデータをセットする
				p.setImg_pass(rs.getString("img_pass"));
				p.setImg_title(rs.getString("img_title"));
				//生成したオブジェクトをリストに追加
					list.add(p);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		return list;
	
}
	
public ArrayList<PhotoList> mypageFovSelect(int no){
		int a=0;
		ArrayList<PhotoList> favList = new ArrayList<PhotoList>();
		
		///// tryの()内に書くと必要に応じてクローズしてくれる
		try(
				Connection con = getConnection();
						
				// ログインしているユーザが投稿した写真を取得
				PreparedStatement ps = con.prepareStatement(
						"select x.img_pass,x.img_title,x.contribution_id from Favorite w,contribution_details x,contribution y,member z where z.member_no=w.member_no and y.member_no=z.member_no and y.contribution_id=w.contribution_id and x.contribution_id=y.contribution_id and z.member_no=?"
						);
				){
			// ?の置換
			ps.setInt(1, no);
			//SQL実行と結果セットの受け取り
			ResultSet rs = ps.executeQuery();
			//結果セットからオブジェクトにデータを入れる
			while(rs.next()){
				//次のデータが存在したらオブジェクトを生成する
				PhotoList p = new PhotoList();
				if(rs.getInt("x.contribution_id")!=a){
					p.setContribution_id(rs.getInt("x.contribution_id"));
					p.setImg_pass(rs.getString("x.img_pass"));
					p.setImg_title(rs.getString("x.img_title"));
					a=rs.getInt("x.contribution_id");
					favList.add(p);

				}
				//生成したオブジェクトにデータをセットする
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		return favList;
	
}
	
	
	
public String prof_select(int no){
		
		//int count=0; 
		String profimg="";
		
		//DB接続
		try(
				Connection con=getConnection();
				PreparedStatement ps=con.prepareStatement(
						"select profimg from member where member_no=?");
				){
			
			ps.setInt(1, no);
			//SQL実行(更新系のSQLはexecuteUpdateで実行)
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
			profimg=rs.getString("profimg");
			}else{
				profimg=null;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return profimg;
		
	}
	
}
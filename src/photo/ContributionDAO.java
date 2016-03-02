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

public class ContributionDAO {
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

	
	
	//ファイル名取得の為
	public String select(){
		
		//int count=0; 
		String filename="";
		
		//DB接続
		try(
				Connection con=getConnection();
				PreparedStatement ps=con.prepareStatement(
						"select img_pass from Contribution_details order by contribution_id DESC");
				){
			

			//SQL実行(更新系のSQLはexecuteUpdateで実行)
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
			filename=rs.getString("img_pass");
				System.out.println(filename);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return filename;
		
	}
		
	// 写真を登録
	public int insert(int no,int score ,int title_id ,String filename,String img_title){
		int count=0; //更新件数（上手くいけば1件）
		
		//DB接続
		try(
				Connection con=getConnection();
				PreparedStatement ps=con.prepareStatement(
						"insert into contribution(member_no,score,point,exhibition_status,status_update_admin_id)"
						+ " value(?,?,100,0,?) ");
				){
			ps.setInt(1,no);
			ps.setInt(2, score);
			ps.setString(3,"suto");

			//SQL実行(更新系のSQLはexecuteUpdateで実行)
			count=ps.executeUpdate();//戻り値は実行件数
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 投稿テーブルにレコードを登録できた場合
		if(count>0){
			int contribution_id=0;
			try(
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(
			"select contribution_id from contribution where status_update_admin_id=? order by contribution_id desc");
					){
				ps.setString(1,"suto");
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					contribution_id=rs.getInt("contribution_id");
				}
				// SQLエラー
				}catch(SQLException e){
				e.printStackTrace();
				// 参照エラー
				}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			
			if(contribution_id>0){
				count=0;
				try(
					Connection con=getConnection();
					PreparedStatement ps=con.prepareStatement(
							// 投稿テーブルに新規レコード
							"insert into contribution_details(contribution_id, title_id, img_pass, img_title) value(?,?,?,?) ");
						){
					
					//？を置き換える
					ps.setInt(1, contribution_id);
					ps.setInt(2, 300);
					ps.setString(3, filename);
					ps.setString(4, img_title);
					//SQL実行(更新系のSQLはexecuteUpdateで実行)
					count=ps.executeUpdate();//戻り値は実行件数 基本的には１
					}catch(SQLException e){
						e.printStackTrace();
					}catch(ClassNotFoundException e){
							e.printStackTrace();
					}
				}
			}
		return count;
		}
	}
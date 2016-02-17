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
		
		//tryの()内に書くと必要に応じてクローズしてくれる
		try(
				Connection con = getConnection();
						
				PreparedStatement ps = con.prepareStatement("select * from Contribution_details order by contribution_id desc");
				
				){
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

	
	
	
	
	
	
	
	
}

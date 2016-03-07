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

public class TitleDAO {

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

	
	public List<Title> random_select(){
		List<Title> titlelist = new ArrayList<Title>();
		
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select * from title ");
				){
			//SQL実行と結果セットの受け取り
			ResultSet rs = ps.executeQuery();
			//結果セットからオブジェクトにデータを入れる
			while(rs.next()){
				//次のデータが存在したらオブジェクトを生成する
				Title t = new Title();
				//生成したオブジェクトにデータをセットする
				t.setTitle_id(rs.getInt("title_id"));
				t.setName(rs.getString("name"));
					titlelist.add(t);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		
		
		
		return titlelist;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

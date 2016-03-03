package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractDAO {
	protected Connection getConnection()
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
			//System.out.println(m.group(1));
			url = m.group(1);
		}
		
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				url, "root", "");
	}
}

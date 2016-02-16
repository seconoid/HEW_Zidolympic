package photo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PhotoListDAO {

	private Connection getConnection() 
			throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/hew?useUnicode=true&characterEncoding=utf8",
				"root",
				""
				);
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

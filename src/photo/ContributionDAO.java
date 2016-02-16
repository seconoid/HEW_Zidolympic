package photo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContributionDAO {
	private Connection getConnection() 
			throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/hew2016?useUnicode=true&characterEncoding=utf8",
				"root",
				""
				);
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

	
	
	
	
	
	
	
	
	
	//ファイル名を入れる
	public int insert(String filename,String img_title){
		int count2=0; //更新件数（上手くいけば1件）
		
		//DB接続
		try(
				Connection con=getConnection();
				PreparedStatement ps=con.prepareStatement(
						"insert into contribution(member_no,score,point,exhibition_status,status_update_admin_id)"
						+ " value(1,1000,100,0,?) ");
				){
			
			ps.setString(1,"mikan");

			//SQL実行(更新系のSQLはexecuteUpdateで実行)
			count2=ps.executeUpdate();//戻り値は実行件数
			
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
			
			
			
			
			if(count2>0){
				int contribution_id=0;
				try(
				Connection con=getConnection();
				PreparedStatement ps=con.prepareStatement(
				"select contribution_id from contribution where status_update_admin_id=? order by contribution_id desc");
						){
						
				ps.setString(1,"mikan");
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					contribution_id=rs.getInt("contribution_id");
				}
				}catch(SQLException e){
					e.printStackTrace();
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
				
			if(contribution_id>0){
				count2=0;
			
				try(
					Connection con=getConnection();
					PreparedStatement ps=con.prepareStatement(
							"insert into contribution_details(contribution_id,title_id,img_pass,img_title) value(?,?,?,?) ");
						){
				//？を置き換える
				ps.setInt(1,contribution_id);
				ps.setInt(2,1);
				ps.setString(3,filename);
				ps.setString(4,img_title);
				//SQL実行(更新系のSQLはexecuteUpdateで実行)
				count2=ps.executeUpdate();//戻り値は実行件数
				}catch(SQLException e){
					e.printStackTrace();
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
			}
		return count2;
	}

	
	
	
	
	
	
	
		
	

}

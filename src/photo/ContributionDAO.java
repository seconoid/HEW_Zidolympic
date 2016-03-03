package photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utility.AbstractDAO;

public class ContributionDAO extends AbstractDAO{
	//ファイル名取得の為
	public String select(){
		
		//int count=0; 
		String filename="";
		
		//DB接続
		try(
				Connection con=getConnection();
				PreparedStatement ps=con.prepareStatement(
						"select img_pass from Contribution_details order by count DESC");
				){
			

			//SQL実行(更新系のSQLはexecuteUpdateで実行)
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
			filename=rs.getString("img_pass");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return filename;
		
	}
		
	// 写真を登録
	public int insert(int no, double score, String filename,String img_title,int title_id,int sortcount){
		int count=0; //更新件数（上手くいけば1件）
		
		//DB接続
		try(
				Connection con=getConnection();
				PreparedStatement ps=con.prepareStatement(
						"insert into contribution(member_no,score,point,exhibition_status,status_update_admin_id)"
						+ " value(?,?,100,0,?) ");
				){
			ps.setInt(1,no);
			ps.setDouble(2, score);
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
							"insert into contribution_details(contribution_id,title_id,img_pass,img_title,count) value(?,?,?,?,?) ");
						){
					//？を置き換える
					ps.setInt(1,contribution_id);
					ps.setInt(2,title_id);
					ps.setString(3,filename);
					ps.setString(4,img_title);
					ps.setInt(5, sortcount);
					//SQL実行(更新系のSQLはexecuteUpdateで実行)
					count=ps.executeUpdate();//戻り値は実行件数 基本的には１
					}catch(SQLException e){
						e.printStackTrace();
					}catch(ClassNotFoundException e){
							e.printStackTrace();
					}
				}
			}
		System.out.println("1DAOのとこ"+filename);
		return count;
		}
	
/////////nullじゃない場合
	public int nninsert(int no,int con_id, String filename,String img_title,int title_id,int sortcount){
		int count=0; //更新件数（上手くいけば1件）
						try(
					Connection con=getConnection();
					PreparedStatement ps=con.prepareStatement(
							// 投稿テーブルに新規レコード
							"insert into contribution_details(contribution_id,title_id,img_pass,img_title,count) value(?,?,?,?,?) ");
						){
							System.out.println(title_id+"タイトルID");
					//？を置き換える
					ps.setInt(1,con_id);
					ps.setInt(2,title_id);
					ps.setString(3,filename);
					ps.setString(4,img_title);
					ps.setInt(5,sortcount);
					//SQL実行(更新系のSQLはexecuteUpdateで実行)
					count=ps.executeUpdate();//戻り値は実行件数 基本的には１
					}catch(SQLException e){
						e.printStackTrace();
					}catch(ClassNotFoundException e){
							e.printStackTrace();
					}
						System.out.println(filename+"更新件数");
		return count;
		}

	
	
	
	///////////////////////////////////////////////////////////////////////////
public int no_select(int no){
		
		int id_count=0; 
		try(
				Connection con=getConnection();
				PreparedStatement ps=con.prepareStatement(
						"select contribution_id from Contribution where member_no=? order by contribution_id DESC");
				){
			

			ps.setInt(1, no);
			//SQL実行(更新系のSQLはexecuteUpdateで実行)
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
			id_count=rs.getInt("contribution_id");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace(); 
		}
		return id_count;
		
	}

	
	
	
	
	
public int sort_select(){
	int count=0; 
	
	//DB接続
	try(
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(
					"select count from Contribution_details order by count DESC");
			){
		

		//SQL実行(更新系のSQLはexecuteUpdateで実行)
		ResultSet rs = ps.executeQuery();
		String strcount="";
		if(rs.next()){
		strcount=rs.getString("count");
		try{
			count=Integer.parseInt(strcount);
			count++;
		}catch(NumberFormatException e){}
		}else{
			count=0;
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	return count;
	
}
	
	
public int score_update(int con_id,int score){
	int count=0; //更新件数（上手くいけば1件）
		try(
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement(
		"update contribution set score=? where contribution_id=?");
				){
			System.out.println("きききき"+con_id);
			System.out.println("きききき"+score);
			ps.setDouble(1,score);
			ps.setInt(2,con_id);
			
			count=ps.executeUpdate();
			if(count!=0){
				System.err.println("更新できてる");
			}else{
				System.out.println("更新できてない");
			}
			// SQLエラー
			}catch(SQLException e){
			e.printStackTrace();
			// 参照エラー
			}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	
		
	return count;
	}
	
	
	
	
	}
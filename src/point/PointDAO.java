package point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utility.AbstractDAO;

public class PointDAO extends AbstractDAO{
	// ポイントを取得
	public int select(int member_no){
		int point = 0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"select point from possession where member_no = ?");
						){
			ps.setInt(1, member_no);
			
			// 結果を代入
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				point = rs.getInt("point");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return point;
	}
	// 購入テーブルに追加
	public int insert(int member_no, int point, String card_no){
		int count = 0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"insert into point_buy(member_no, buy_point, card_no) values(?, ?, ?)");
					){
				ps.setInt(1, member_no);
				ps.setInt(2, point);
				ps.setString(3, card_no);
				count = ps.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
		return count;
	}
	
	// 所持ポイントを更新
	public int update(int member_no, int point){
		int count = 0;
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"update possession set point = (point + ?) where member_no = ?");
					){
				ps.setInt(1, point);
				ps.setInt(2, member_no);
				count = ps.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
		return count;
	}
}

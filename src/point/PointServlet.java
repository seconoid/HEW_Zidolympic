package point;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;

/**
 * Servlet implementation class PointServlet
 */
@WebServlet("/PointServlet")
public class PointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int count = 0; // 更新件数
		
		String strNo = request.getParameter("member_no");
		String strPoint = request.getParameter("point");
		String cardNo1 = request.getParameter("cardNo1");
		String cardNo2 = request.getParameter("cardNo2");
		String cardNo3 = request.getParameter("cardNo3");
		String cardNo4 = request.getParameter("cardNo4");
		String strCard = request.getParameter("card");
		
		int no = 0;
		int point = 0;
		String cardNo = "";
		boolean  isErr = false;
		
		/*** エラー処理 ***/
		if(strNo == null ||  strNo.isEmpty()){
			request.setAttribute("loginErr", "ログインが必要です");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		if(strPoint == null ||  strPoint.isEmpty() || strPoint == "0" ){
			request.setAttribute("pointErr", "ポイントを入力してください");
			isErr = true;
		}
		
		cardNo = cardNo1 + cardNo2 + cardNo3 + cardNo4;
		if(cardNo.length() != 16){
			request.setAttribute("pointErr", "カード番号は16桁です");
			isErr = true;
		}
		if(!(cardNo.substring(0,4) == "AAAA" || cardNo.substring(0,4) == "ZZZZ" || cardNo.substring(0,4) != "0000")){
			System.out.println(cardNo);
			request.setAttribute("pointErr", "カード番号が不正です");
			isErr = true;
		}
		if( Integer.parseInt(strCard) < Integer.parseInt(strPoint) ){
			request.setAttribute("pointErr", "カード残高が不足しています");
			isErr = true;
		}
		
		// エラーが無かったら
		if(!isErr){
			no = Integer.parseInt(strNo);
			point = Integer.parseInt(strPoint);
			
			PointDAO dao = new PointDAO();
			count += dao.insert(no, point, cardNo);
			count += dao.update(no, point);
			System.out.println("更新件数：" + count);
			
			if(count == 2){
				// userセッションの更新
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("user");
				user.setPoint(dao.select(no));
				session.setAttribute("user", user);
				
				System.out.println("正常に更新");
				request.setAttribute("successMes", "ポイントを購入しました");
			}else{
				System.out.println("DBエラー");
				request.setAttribute("pointErr", "正常に購入できませんでした");
			}
		}
		request.getRequestDispatcher("MypageServlet").forward(request, response);
	}
}

package point;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.SHAGenerator;
import user.User;
import user.UserDao;

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
		
		String strId = request.getParameter("member_no");
		String strPoint = request.getParameter("money");
		String cardNo1 = request.getParameter("cardNo1");
		String cardNo2 = request.getParameter("cardNo2");
		String cardNo3 = request.getParameter("cardNo3");
		String cardNo4 = request.getParameter("cardNo4");
		String card = request.getParameter("card");
		
		int id = 0;
		int point = 0;
		String cardNo = "";
		boolean  isErr = false;
		
		/*** エラー処理 ***/
		if(strId == null ||  strId.isEmpty()){
			request.setAttribute("loginErr", "ログインが必要です");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		if(strPoint == null ||  strPoint.isEmpty()){
			request.setAttribute("pointErr", "ポイントを入力してください");
			isErr = true;
		}
		
		cardNo = cardNo1 + cardNo2 + cardNo3 + cardNo4;
		if(cardNo.length() != 16){
			request.setAttribute("pointErr", "カード番号は16桁です");
			isErr = true;
		}
		if(cardNo.substring(0,1) != "A" || cardNo.substring(0,1) != "Z" || cardNo.substring(0,1) != "0"){
			request.setAttribute("pointErr", "カード番号が不正です");
			isErr = true;
		}
		
		
		
		// エラーが無かったら
		if(!isErr){
			id = Integer.parseInt(strId);
			point = Integer.parseInt(strPoint);
			
			
		}
	}
}

package user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photo.PhotoList;
import photo.PhotoListDAO;

/**
 * Servlet implementation class MypageServlet
 */
@WebServlet("/MypageServlet")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		
		// セッションよりIDを取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int no = 0;
		if(user != null){
			no = user.getNo();
		}
		
		PhotoListDAO dao=new PhotoListDAO();
		// 自分が投稿した写真を取得
		ArrayList<PhotoList> list=dao.mypageSelect(no);
		// 自分がお気に入りした写真を取得
		//		ArrayList<PhotoList> favList = dao.mypageSelect(no);
		
		request.setAttribute("photolist", list);
		//     request.setAttribute("favList", favList);
		
		request.getRequestDispatcher("mypage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

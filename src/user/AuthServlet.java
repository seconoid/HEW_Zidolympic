package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
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
		// エンコーディング
				request.setCharacterEncoding("utf-8");
				
				String id = request.getParameter("id");
				String pass = request.getParameter("password");
				
				boolean isErr = false;
				// ハッシュ計算
				String hash = SHAGenerator.getStretchedPassword(id, pass);
				
				// ID(メールアドレス）かパスワードが空だった場合
				if( id == null ||  id.isEmpty() || pass == null || pass.isEmpty() ){
					request.setAttribute("idErr", "ユーザIDもしくはパスワードに誤りがあります。");
					isErr = true;
				}
				
				// データベースにアクセス
				UserDao dao = new UserDao();
				User user = dao.auth(id, hash);

				// 存在チェック
				if(!isErr){
					// 存在チェック
					if(user == null){
						request.setAttribute("AuthErr", "認証エラー" );
						request.getRequestDispatcher("mypage.jsp").forward(request, response);
					}			
				}
				
				// エラーチェック
				if(isErr){
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					// セッションに登録
					HttpSession session = request.getSession();
					session.setAttribute("auth", user);
					
					// 遷移
					response.sendRedirect("userinfo_update.jsp");
				}
	}

}

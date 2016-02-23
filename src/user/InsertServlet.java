package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
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
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("password");
		String mail_adress = request.getParameter("mail_adress");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		boolean delete_flag = false;

		boolean  isErr = false;

		// ハッシュ計算
		String hash = SHAGenerator.getStretchedPassword(id, pass);
		
		if(id == null ||  id.isEmpty()){
			request.setAttribute("idErr", "IDを入力してください。");
			isErr = true;
		}
		
		if(name == null ||  name.isEmpty()){
			request.setAttribute("nameErr", "名前を入力してください。");
			isErr = true;
		}
		
		if(pass == null ||  pass.isEmpty()){
			request.setAttribute("passErr", "パスワードを入力してください");
			isErr = true;
		}
		
		// エラーが無かったら
		if(!isErr){
			UserDao dao = new UserDao();
			int count = dao.insert(id, name, hash, mail_adress, birthday, sex, delete_flag);
			
			if(count <= 0){
				request.setAttribute("mes", "データが更新されてない");
			}else{
				// セッションに登録
				User user = dao.select(id, hash);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				// マイページに遷移
				request.setAttribute("mes", "データを更新しました");
				request.getRequestDispatcher("mypage.jsp").forward(request, response);
			}
		}
	}
}

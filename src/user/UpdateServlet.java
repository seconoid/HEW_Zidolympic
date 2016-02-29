package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		
		boolean  isErr = false;
		
		
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
		
		
		if(!isErr){
			UserDao dao = new UserDao();
			int count = dao.update(id, name, pass, mail_adress, birthday);
			
			if(count <= 0){
				request.setAttribute("mes", "データが更新されてない");
			}else{
				request.setAttribute("mes", "データを更新しました");
				System.out.println("更新件数：" + count);
			}
		}
		request.getRequestDispatcher("mypage.jsp").forward(request, response);
	}
}
package photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;

/**
 * Servlet implementation class SplatorchUpdata
 */
@WebServlet("/SplatorchUpdata")
public class SplatorchUpdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SplatorchUpdata() {
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
//		ｺﾒﾝﾄの更新
		request.setCharacterEncoding("utf8");
		String comment = request.getParameter("commnet");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int no = user.getNo();

		System.out.println("No:" + no);
		
		ContributionDAO dao=new ContributionDAO();
		int contribution_id = dao.no_select(no);
		System.out.println(contribution_id);
		String textarea=request.getParameter("textarea");
		if(textarea==null||textarea.equals("")){
			textarea="no comment";
	
			int spUpdata = dao.updata(comment, contribution_id);
			if(spUpdata==0){
				request.setAttribute("mes","<h1>投稿ができませんでした！</h1>");
			}
			if(spUpdata>0){
				request.setAttribute("mes","<h1>投稿が完了しました</h1>");
				String binary=request.getParameter("binary");
				String filename=request.getParameter("filename");
				request.setAttribute("binary", binary);
				request.setAttribute("filename", filename);
			}
			
			request.getRequestDispatcher("confirm.jsp").forward(request, response);
		}
	}
}

package photo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;

/**
 * Servlet implementation class TryUpdateServlet
 */
@WebServlet("/TryUpdateServlet")
public class TryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TryUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf8");
		
		String img_title=request.getParameter("img_title");
		String comment=request.getParameter("comment");
		if(img_title==null||img_title.equals("")){
			img_title="無題";
		}
		if(comment==null||comment.equals("")){
			comment="no comment";
		}
		
		HttpSession page_out_session = request.getSession(true);
		ArrayList<PageOut> page_session = (ArrayList<PageOut>)page_out_session.getAttribute("page_out");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int no=user.getNo();
		PageOut p=(PageOut)page_session.get(0);
		int contribution_id=p.getC_id();
		
		ContributionDAO dao=new ContributionDAO();
		int count=dao.try_update(img_title,comment,contribution_id);
		if(count==0){
			System.out.println("更新できませんでした");
		}else{
			System.out.println("更新されませんでした");
			page_session.clear();
			Random random=new Random();
			TitleDAO adao=new TitleDAO();
			List<Title> titlelist=adao.random_select();
			int i=random.nextInt(titlelist.size());
			Title a=(Title)titlelist.get(i);
			request.setAttribute("titlename", a.getName());
			request.setAttribute("title_id", a.getTitle_id());
		}
		request.getRequestDispatcher("try_confirm.jsp").forward(request, response);
	}

}

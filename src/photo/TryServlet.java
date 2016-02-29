package photo;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TryServlet
 */
@WebServlet("/TryServlet")
public class TryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf8");
		
		
		TitleDAO dao=new TitleDAO();
		List<Title> titlelist=dao.random_select();
		
		
		Random random=new Random();
		System.out.println(titlelist.size());
		int i=random.nextInt(titlelist.size());
		Title p=(Title)titlelist.get(i);
		int title_id=p.getTitle_id();
		String titlename=p.getName();
		request.setAttribute("titlename", titlename);
		request.setAttribute("title_id", title_id);
		
		request.getRequestDispatcher("try.jsp").forward(request, response);

		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}

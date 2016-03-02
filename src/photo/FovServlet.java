package photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FovServlet
 */
@WebServlet("/FovServlet")
public class FovServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FovServlet() {
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
		String img_pass=request.getParameter("img_pass");
		String conid=request.getParameter("con_id");
		String name=request.getParameter("name");
		System.out.println(name+"ここ");
		
		int con_id=0;
		try{
			con_id=Integer.parseInt(conid);
		}catch(NumberFormatException e){}
		
		request.setAttribute("con_id", con_id);
		request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite_none.png onclick=Change() id=fov_none>");
		request.setAttribute("fov_val", "0");
		request.setAttribute("img_pass", img_pass);
		request.setAttribute("name", name);
		request.getRequestDispatcher("photodetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf8");
		String img_pass=request.getParameter("img_pass");
		String a=request.getParameter("fov_val");
		String name=request.getParameter("name");
		String conid=request.getParameter("con_id");
		
		System.out.println(img_pass);
		System.out.println(a);
		System.out.println(name);
		System.out.println(conid);
		
		
		int con_id=0;
		try{
			con_id=Integer.parseInt(conid);
		}catch(NumberFormatException e){}
		
		FavoriteDAO fov=new FavoriteDAO();
		
		if(a.equals("0")){
			
			//int count=fov.select(name,con_id);
			//if(count!=0){
			request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite.png onclick=Change() id=fov_none>");
			request.setAttribute("fov_val", "1");
			//}
		}else if(a.equals("1")){
			//int count=fov.delete(name,con_id);
			//if(count!=0){
			request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite_none.png onclick=Change() id=fov_none>");
			request.setAttribute("fov_val", "0");
			//}
		}
		
		request.setAttribute("con_id", con_id);
		request.setAttribute("img_pass", img_pass);
		request.getRequestDispatcher("photodetails.jsp").forward(request, response);
	}

}

package photo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;


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
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String img_pass=request.getParameter("img_pass");
		String conid=request.getParameter("con_id");
		boolean Err=false;
		FavoriteDAO fov=new FavoriteDAO();
		int con_id=0;
		try{
			con_id=Integer.parseInt(conid);
		}catch(NumberFormatException e){}
		
		if(user!=null){
			
			if(user.getName().equals("")){
				request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite_none.png>");
				request.setAttribute("fov_val", "0");
			Err=true;
			}
			
			if(!Err){
				
			
			int user_no=user.getNo();
			
		
		int count=fov.select(user_no,con_id);
		if(count==0){
			System.out.println("000000000");
			request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite_none.png onclick=Change() id=fov_none>");
			request.setAttribute("fov_val", "0");
		}else{
			System.out.println("1111111111");
			request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite.png onclick=Change() id=fov_none>");
			request.setAttribute("fov_val", "1");
		}
		
		List<Fov> com=fov.com_select(con_id);
		request.setAttribute("com", com);
//		List<Fov> com2=fov.com2_select(con_id);
//		request.setAttribute("com2", com2);
		}
		}
		if(user==null){
			request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite_none.png>");
			request.setAttribute("fov_val", "0");
			
		}
		List<Fov> com2=fov.com2_select(con_id);
		request.setAttribute("com2", com2);
		
		request.setAttribute("con_id", con_id);
		request.setAttribute("img_pass", img_pass);
		request.getRequestDispatcher("photodetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf8");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String img_pass=request.getParameter("img_pass");
		String a=request.getParameter("fov_val");
		String conid=request.getParameter("con_id");
		int user_no=user.getNo();
		
		
		int con_id=0;
		try{
			con_id=Integer.parseInt(conid);
		}catch(NumberFormatException e){}
		
			if(user.getName().equals("")){
				request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite_none.png>");
				request.setAttribute("fov_val", "0");
			}
		
		
		FavoriteDAO fov=new FavoriteDAO();
		
		if(a.equals("0")){
			
			int count=fov.insert(user_no,con_id);
			if(count!=0){
			request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite.png onclick=Change() id=fov_none>");
			request.setAttribute("fov_val", "1");
			}
		}else if(a.equals("1")){
			int count=fov.delete(user_no,con_id);
			if(count!=0){
			request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite_none.png onclick=Change() id=fov_none>");
			request.setAttribute("fov_val", "0");
			}
		}
		List<Fov> com=fov.com_select(con_id);
		request.setAttribute("com", com);
		
		List<Fov> com2=fov.com2_select(con_id);
		request.setAttribute("com2", com2);
		
			
			request.setAttribute("fov_out", "<img src=/HEW_Zidolympic/images/favorite_none.png>");
			request.setAttribute("fov_val", "0");
		
		request.setAttribute("con_id", con_id);
		request.setAttribute("img_pass", img_pass);
		request.getRequestDispatcher("photodetails.jsp").forward(request, response);
	}

}

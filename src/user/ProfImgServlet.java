package user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import photo.PhotoList;
import photo.PhotoListDAO;


/**
 * Servlet implementation class ProfImgServlet
 */
@MultipartConfig(location = "", maxFileSize = 20000 * 20000 * 2)
@WebServlet("/ProfImgServlet")
public class ProfImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfImgServlet() {
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
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int no=user.getNo();
		
		Part part = request.getPart("filename");
		
		// HTTPヘッダの値を取得
		String contentDisposition = part.getHeader("content-disposition");

		/* アップロードしたファイル名の取得 */
		// 変数contentDispositionから"filename="以降を抜き出す
		int filenamePosition = contentDisposition.indexOf("filename=");
		String filename = contentDisposition.substring(filenamePosition);
		// "filename="と"を除く
		filename = filename.replace("filename=", "");
		filename = filename.replace("\"", "");
		// 絶対パスからファイル名のみ取り出す
		filename = new File(filename).getName();

		// 画像ファイルをpath+filenameとして保存
		part.write("C://Users/mikan.shelty/Documents/workspace/HEW_Zidolympic/WebContent/profimg" + "/" + filename);
		
		UserDao dao=new UserDao();
		int count=dao.profimg_update(no,filename);
		if(count==0){
			request.setAttribute("imgmes", "更新できませんでした");
		}else{
			request.setAttribute("imgmes", "少々お待ちください");
		}
		
		PhotoListDAO pdao=new PhotoListDAO();
		// 自分が投稿した写真を取得
		ArrayList<PhotoList> list=pdao.mypageSelect(no);
		request.setAttribute("photolist", list);
		PhotoListDAO dao2=new PhotoListDAO();
		ArrayList<PhotoList> favList = dao2.mypageFovSelect(no);
		request.setAttribute("favList", favList);
		request.getRequestDispatcher("mypage.jsp").forward(request, response);
		
	}

}

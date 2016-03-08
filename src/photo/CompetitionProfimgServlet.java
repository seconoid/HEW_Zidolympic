package photo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;

import user.User;
import user.UserDao;

/**
 * Servlet implementation class CompetitionProfimgServlet
 */
@WebServlet("/CompetitionProfimgServlet")
public class CompetitionProfimgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompetitionProfimgServlet() {
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
		
		String binary=request.getParameter("binary");
		String filename=request.getParameter("filename");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int no = user.getNo();
		
		
		try{
		//そのまま受け取るとキャパオーバーするから配列で受け取る（変換）+正規表現
		byte[] bytes = Base64.decodeBase64(binary.substring(22).getBytes());
		
		//バイナリ配列をインプットストリームでデータにする（プログラム上）
		ByteArrayInputStream input = new ByteArrayInputStream(bytes);
		
		//データをプログラム上で読み取る
		BufferedImage image = ImageIO.read(input);
		
		//読み取ったデータをserver側に出力する
		//// *******   pathはpropertiesより読み込み *********** ///
		// propertiesより読み込み
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle("path");
		}catch (MissingResourceException e) {
			e.printStackTrace();
		}
		// パスを取得
		String path = bundle.getString("mypageuploadPath");
		String url = "";
		
		// 正規表現で抜き取り(""が入り込んでくるため）
		Pattern p = Pattern.compile("^\"(.+)\"$");
		Matcher m = p.matcher(path);
		if (m.find()){
			System.out.println(m.group(1));
			url = m.group(1);
		}
		
		FileOutputStream output = new FileOutputStream(url+filename); 
		ImageIO.write(image, "png", output); 
		input.close();
		output.close();
		
		System.out.println("保存できました");
		}catch(IOException e){
		//変換できなかった場合
		System.out.println("保存できませんでした");
		}		
		UserDao dao=new UserDao();
		int count=dao.profimg_update(no,filename);
		
		
		if(count==0){
			request.setAttribute("mes","<h2>アップロード出来ませんでした</h2>");
		}
		if(count>0){
			request.setAttribute("mes","<h2>アップロード出来ました</h2>");
		}
		request.getRequestDispatcher("synchroj.jsp").forward(request,response);
		
		
		
		
		
		
		
	}

}

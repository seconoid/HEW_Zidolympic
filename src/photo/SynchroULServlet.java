package photo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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

/**
 * Servlet implementation class SynchroULServlet
 */
@WebServlet("/SynchroULServlet")
public class SynchroULServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SynchroULServlet() {
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
		// HttpSession のオブジェクトを取得
		
		String tag=request.getParameter("tag");
		
		String textarea=request.getParameter("textarea");
		if(textarea==null||textarea.equals("")){
			textarea="no comment";
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int no = user.getNo();
		
		/*
		 * バイナリを見たくてもSystem.out.println(binary)で出さないこと
		 * キャパオーバーする(コンソール壊れる)
		 */
		int count=0;
		String name="test";
		
		ContributionDAO dao=new ContributionDAO();
		String filename=dao.select();
		
		if(!filename.equals("")){
			filename=filename.replace("test", "");
			filename=filename.replace(".png", "");
			
			try{
				count=Integer.parseInt(filename);
				System.out.println(count+"カウント");
				count=count+1;
				filename=name+String.valueOf(count)+".png";
			}catch(NumberFormatException e){
				filename=name+"0.png";
			}
			
		}else{
			filename=name+"0.png";
		}
		
//////////////////////////////////////////////バイナリ始まり
		String img_title=request.getParameter("title");
		//バイナリ受け取り
	String binary=request.getParameter("h");
	binary.replace(" ", "+");
	
	try{
	//そのまま受け取るとキャパオーバーするから配列で受け取る（変換）
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
	String path = bundle.getString("uploadPath");
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
	
/////////////////////////////////////////////////////////////バイナリ終わり
	
	int sortcount=dao.sort_select();
	
int score=0;
		if(img_title.equals("") || img_title==null){
			img_title="無題";
		}
		int count2=dao.insert(no, score, filename,img_title, 19, sortcount,textarea);
		if(count2==0){
			request.setAttribute("mes","<h2>アップロード出来ませんでした</h2>");
		}
		if(count2>0){
			request.setAttribute("mes","<h2>アップロード出来ました</h2>");
			request.setAttribute("title", img_title);
			request.setAttribute("pass", filename);
			
			
			if(tag==null||tag.equals("")){
				tag="#notag";
			}else{
				if(!tag.contains("#")){
					tag="#"+tag;
					}
			}
				int id_count=dao.no_select(no);
				dao.tag_insert(id_count,tag);
			List<Tag> tag_ranking=dao.ranking_select();
			if(tag_ranking.size()!=0){
				boolean check=false;
			for(int tcon=0;tag_ranking.size()>tcon&&!check;tcon++){
				Tag t=tag_ranking.get(tcon);
				if(t.getName().equals(tag)){
					request.setAttribute("rank", t.getRank());
					check=true;
				}
			}
			
			
			
			}
			String outtag=tag.replace("#", "");
			request.setAttribute("tag","<a href=/HEW_Zidolympic/PhotoListServlet?check=1&tag="+outtag+">"+tag+"</a>");
		}
		try{
			Thread.sleep(1500);
		}catch(InterruptedException e){}
		
		request.getRequestDispatcher("synchroj.jsp").forward(request,response);

	}
}

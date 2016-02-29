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

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/spUp")
public class spUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public spUp() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
		System.loadLibrary("opencv_java300");
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
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
		System.out.println(filename);
		
//////////////////////////////////////////////バイナリ始まり
		String img_title=request.getParameter("title");
		//バイナリ受け取り
	String binary=request.getParameter("h");
	binary.replace(" ", "+");
	System.out.println(binary.length());
	
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
	
	//////////////////openCVｸﾗｽを使用
	int cv = CvCheakPix.a(filename);
	System.out.println(cv);
	int score = cv;
	///////////////////

		if(img_title.equals("") || img_title==null){
			img_title="無題";
		}
		int count2=dao.insert(score, filename,img_title);
		if(count2==0){
			request.setAttribute("mes","<h1>アップロード出来ませんでした</h1>");
		}
		if(count2>0){
			request.setAttribute("mes","<h2>アップロード出来ました</h2>");
		}


		request.setAttribute("grayCount", cv);
		request.getRequestDispatcher("splatorch.jsp").forward(request,response);

	}
}
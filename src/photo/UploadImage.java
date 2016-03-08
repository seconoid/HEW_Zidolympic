package photo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;
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
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
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
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf8");
		// HttpSession のオブジェクトを取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		TitleDAO adao=new TitleDAO();
		List<Title> titlelist=adao.random_select();
		
		String titleid=request.getParameter("titleid");
		int title_id=0;
		try{
			title_id=Integer.parseInt(titleid);
		}catch(NumberFormatException e){}
		int no = user.getNo();
		//int no=8;
		//System.out.println("ユーザ番号"+user.getNo());
		
		/*
		 * バイナリを見たくてもSystem.out.println(binary)で出さないこと
		 * キャパオーバーする(コンソール壊れる)
		 */
		
		String title_name=adao.select(title_id);
		
		HttpSession page_out_session = request.getSession(true);
		ArrayList<PageOut> page_session = (ArrayList<PageOut>)page_out_session.getAttribute("page_out");
		ContributionDAO dao=new ContributionDAO();
		String filename="";
		int count=0;
		String name="test";
		if(page_session==null){
		filename=dao.select();
		}else{
			if(page_session.size()!=0){
			PageOut c=(PageOut)page_session.get(page_session.size()-1);
			filename=c.getPass();
			}else{
				filename=dao.select();
			}
		}
		if(!filename.equals("")){
			filename=filename.replace("test", "");
			filename=filename.replace(".png", "");
			try{
				count=Integer.parseInt(filename);
				count=count+1;
				filename=name+String.valueOf(count)+".png";
			}catch(NumberFormatException e){
				filename=name+"0.png";
			}
			
		}else{
			filename=name+"0.png";
		}
		int sortcount=dao.sort_select();
		
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
	int c_id=0;
	int score=0;
	String s="";
		if(img_title==null){
			img_title="無題";
		}
		

		ArrayList<PageOut> mikan=new ArrayList<PageOut>();
		
		
		
		
		int con_id=0;
		
		PageOut a=new PageOut();
		a.setTitle(img_title);
		a.setPass(filename);
		a.setTitle_name(title_name);
		///////////////////////////////////////////
		
		
		if(page_session==null||page_session.equals("")){
			c_id=dao.no_select(no); 
			a.setC_id(c_id+1);//なぞ
		}else{
			if(page_session.size()!=0){
			PageOut c=(PageOut)page_session.get(page_session.size()-1);
			con_id=c.getC_id();
			a.setC_id(con_id);
			}else{
				c_id=dao.no_select(no); 
				a.setC_id(c_id+1);//なぞ
			}
		}
		a.setTitleid(title_id);
		mikan.add(a);
		int count2=0;
		long start =0;
		long end =0;
		String textarea="";
		
		if(page_session==null){
			textarea=request.getParameter("textarea");
			if(textarea==null||textarea.equals("")){
				textarea="no comment";
			}
			int defaultscore=0;
			count2=dao.insert(no,defaultscore,filename,img_title,title_id,sortcount,textarea);

			page_out_session.setAttribute("page_out", mikan);
			start = System.nanoTime();
		}else{
			System.out.println(page_session.size()+"ページセッション");
			if(page_session.size()==0){
				textarea=request.getParameter("textarea");
				if(textarea==null||textarea.equals("")){
					textarea="no comment";
				}
				int defaultscore=0;
				count2=dao.insert(no,defaultscore,filename,img_title,title_id,sortcount,textarea);

				page_out_session.setAttribute("page_out", mikan);
				start = System.nanoTime();
			}else{
				textarea=request.getParameter("comment");
			count2=dao.nninsert(no,con_id,filename,img_title,title_id,sortcount);
			page_session.addAll(mikan);
			//時間計測///////////////////////////////////////////
			if(page_session.size()==3){
				end = System.nanoTime();

				String s1=String.valueOf(start);
				String s2=String.valueOf(end);
				Double s3=Double.parseDouble(s1);
				Double s4=Double.parseDouble(s2);
				
				Double sco=(double) (end - start);
				s=String.valueOf(sco);
				System.err.println(s.replace(".","")+"リプレイス");
				s=s.replace(".","");
				s = s.substring(0, 9);
				

				System.out.println(s+"スコアString");
				
				score=Integer.parseInt(s);
				score=score/1000000;
				System.out.println(score);
				int score_count=dao.score_update(con_id,score);
				System.out.println("Time:" + score + "ms");
			}
			page_out_session.setAttribute("page_out", page_session);
		}
		if(page_session!=null&&!page_session.equals("")&&
				page_session.size()!=3&&page_session.size()!=0){
			if(page_session.size()!=0){
			request.setAttribute("save","<button id=hozon onClick=kakunin()>ほぞん</button>");
			}
		}else if(page_session!=null&&!page_session.equals("")&&
				page_session.size()==3){
			request.setAttribute("save","<button id=hozon >もう保存は出来ません</button>");
			request.setAttribute("revenge", " <a href=/HEW_Zidolympic/TryServlet>"
					+ "<p class=text_box>もう1回チャレンジ</p></a>");
		}
		
		if(count2==0){
			System.out.println("アップロード出来ませんでした");
		}
		if(count2>0){
			System.out.println("アップロード出来ました");
		}
		
		///////////////////////
		
		//二回目以降の同ID検索削除
		if(page_session!=null){
			if(page_session.size()!=0){
		boolean flg=false;
		for(int k=0;!flg&&page_session.size()>k;k++){
			PageOut adf=(PageOut)page_session.get(k);
			
			for(int h=0;titlelist.size()>h;h++){
				Title adc=(Title)titlelist.get(h);
				if(adf.getTitleid()==adc.getTitle_id()){
					titlelist.remove(h);
				}
			}
		}
			}
			if(page_session.size()==3){
				try{
					Thread.sleep(1500);
				}catch(InterruptedException e){}
				request.setAttribute("score", score);
			}
		}///////s.substring(0, 3)
		
		
				/////////////////////
		
		
		
		 
		}
		Random random=new Random();
		int i=random.nextInt(titlelist.size());
		
		Title p=(Title)titlelist.get(i);

		request.setAttribute("titlename", p.getName());
		request.setAttribute("title_id", p.getTitle_id());
		
		request.setAttribute("comment", textarea);
		
		
		
		request.getRequestDispatcher("try.jsp").forward(request, response);

	}
	
	

}
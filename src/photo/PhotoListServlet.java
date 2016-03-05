package photo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PhotoListServlet
 */
@WebServlet("/PhotoListServlet")
public class PhotoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoListServlet() {
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
		
		String tag=request.getParameter("tag");
		
		String check=request.getParameter("check");
		ArrayList<PhotoList> list=new ArrayList<PhotoList>();
		PhotoListDAO dao=new PhotoListDAO();
		if(tag==null||tag.equals("")){
		list=dao.select();
		}else if(tag!=null&&!tag.equals("")){
			tag="#"+tag;
			list=dao.tag_select(tag);
		}
		//下の（1 2 3 4 5 ）ページ数を出す処理
		String page="";
		int pgs = list.size() / 16;
		if (list.size() % 16 != 0) {
			pgs = pgs + 1;
			}
		for(int i=1;i<=pgs;i++){
			if(Integer.parseInt(check)==i){
				page=page+"<font color=#fff>"+Integer.toString(i)+"</font>"+"　";
			}else{
				if(tag==null||tag.equals("")){
					page=page+"<a href=/HEW_Zidolympic/PhotoListServlet?check="+i+">"+Integer.toString(i)+"</a>"+"　";
				}else if(tag!=null&&!tag.equals("")){
					String outtag=tag.replace("#","");
					page=page+"<a href=/HEW_Zidolympic/PhotoListServlet?check="+i+"&tag="+outtag+">"+Integer.toString(i)+"</a>"+"　";					
				}
			
			}
		}
		request.setAttribute("page",page);

		
		
		
		
		if(check!=null){
			String a=request.getParameter("check");
			int num=0;
			try{
				num=Integer.parseInt(a);
			}catch(NumberFormatException e){
			}
			// <<⇇一つ前のページに戻すボタン処理
			if(num>=2){
				int k=num-1;
				if(tag==null||tag.equals("")){
					request.setAttribute("back", "<a href=/HEW_Zidolympic/PhotoListServlet?check="+k+">＜＜</a>");	
				}else if(tag!=null&&!tag.equals("")){
					String outtag=tag.replace("#","");
					request.setAttribute("back", "<a href=/HEW_Zidolympic/PhotoListServlet?check="+k+"&tag="+outtag+">＜＜</a>");
				}
				
			}
			
			
			
			
			
			int page_end=num*15+num;//１６件ずつ出したいからnum×１５＋ページ数をする
			int page_start=page_end-16;//出す最初の値を知りたいから―１６する
			
			ArrayList<PhotoList> list2=new ArrayList<PhotoList>();
			
			if(tag==null||tag.equals("")){
				list2=dao.select();//ここでBETWEENを使わないのは途中でIDの空きが出た場合の為
				}else if(tag!=null&&!tag.equals("")){
					list2=dao.tag_select(tag);
				}
			list.clear();//初期化
			int i=0;
			if(list2.size()>0){
				boolean ERR=false;
				while(page_start<page_end&&!ERR){
					if(page_start==list2.size()){
						ERR=true;
					}
					if(!ERR){
				PhotoList p=list2.get(page_start);
				PhotoList g = new PhotoList();
				
				g.setContribution_id(p.getContribution_id());
				g.setImg_pass(p.getImg_pass());
				g.setImg_title(p.getImg_title());
				
				list.add(g);
				i++;
				page_start++;
					}
			}
			}
			
			
			
			
			// <<⇇一つ次のページに進むボタン処理
			int l=list2.size()-1;
			int L=list.size()-1;
			PhotoList p=list2.get(l);
			PhotoList h=list.get(L);
			if(p.getContribution_id()!=h.getContribution_id()){
				int k=num+1;
				if(tag==null||tag.equals("")){
					request.setAttribute("next", "<a href=/HEW_Zidolympic/PhotoListServlet?check="+k+">＞＞</a>");
				}else if(tag!=null&&!tag.equals("")){
					String outtag=tag.replace("#", "");
					request.setAttribute("next", "<a href=/HEW_Zidolympic/PhotoListServlet?check="+k+"&tag="+outtag+">＞＞</a>");
					request.setAttribute("texttag", tag);
				}
			}
			
			
		}
		
		request.setAttribute("photolist", list);
		
		request.getRequestDispatcher("photolist.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}

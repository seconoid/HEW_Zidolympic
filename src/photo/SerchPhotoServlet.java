package photo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SerchPhotoServlet
 */
@WebServlet("/SerchPhotoServlet")
public class SerchPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerchPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");

		
		String check=request.getParameter("check");
		//GETでの日本語変換
		String search = new String (request.getParameter("search").getBytes("ISO-8859-1"));
		
		System.out.println("Searchゲット"+search);
		System.out.println("ちぇｃｋゲット"+check);
		
		boolean flg=false;
		String sql="select * from Competition a,title b,contribution_details c,contribution d where d.contribution_id=c.contribution_id and c.title_id=b.title_id and a.Competition_id=b.Competition_id and (a.name like ? or b.name like ? or c.img_title like ?) order by c.contribution_id desc";
		int co=0;
		
		if(search==null||search.equals("")){
			flg=true;
		}
		List<PhotoList> list=new ArrayList<PhotoList>();
		PhotoListDAO dao=new PhotoListDAO();
		if(flg){
		list=dao.select();
		}else if(!flg){
		list=dao.Search_select(search, sql, co);
		if(list==null||list.size()==0){
			co=5;
			sql="select * from Competition a,title b,contribution_details c,contribution d,tag e where e.contribution_id=d.contribution_id and d.contribution_id=c.contribution_id and c.title_id=b.title_id and a.Competition_id=b.Competition_id and (a.name like ? or b.name like ? or c.img_title like ? or e.name like ?) order by c.contribution_id desc";
			list=dao.Search_select(search, sql, co);
		}
		if(list==null||list.size()==0){
			list=dao.select();
		}
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
				page=page+"<a href=/HEW_Zidolympic/SerchPhotoServlet?check="+i+">"+Integer.toString(i)+"</a>"+"　";
			}
		}
		request.setAttribute("page",page);

		
		
		///////////////////////////
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
						request.setAttribute("back", "<a href=/HEW_Zidolympic/SerchPhotoServlet?check="+k+">＜＜</a>");	
				}
				
			
			
			
			
			
			
			int page_end=num*15+num;//１６件ずつ出したいからnum×１５＋ページ数をする
			int page_start=page_end-16;//出す最初の値を知りたいから―１６する
			
			ArrayList<PhotoList> list2=new ArrayList<PhotoList>();
			
			if(flg){
				list2=dao.select();
				}else if(!flg){
				list2=dao.Search_select(search, sql, co);
				if(list2==null||list2.size()==0){
					co=5;
					sql="select * from Competition a,title b,contribution_details c,contribution d,tag e where e.contribution_id=d.contribution_id and d.contribution_id=c.contribution_id and c.title_id=b.title_id and a.Competition_id=b.Competition_id and (a.name like ? or b.name like ? or c.img_title like ? or e.name like ?) order by c.contribution_id desc";
					list2=dao.Search_select(search, sql, co);
				}
				
				
				}
				
			
			if(list2==null||list2.size()==0){
				list2=dao.select();
				request.setAttribute("mes", "その商品はありませんでした");
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
				g.setTimestamp(p.getTimestamp());
				
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
						request.setAttribute("next", "<a href=/HEW_Zidolympic/SerchPhotoServlet?check="+k+">＞＞</a>");
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
request.setCharacterEncoding("utf-8");
		
		String search=request.getParameter("search");
		String check=request.getParameter("check");
		
		System.out.println("Searchadhfosahfoiah"+search);
		
		boolean flg=false;
		String sql="select * from Competition a,title b,contribution_details c,contribution d where d.contribution_id=c.contribution_id and c.title_id=b.title_id and a.Competition_id=b.Competition_id and (a.name like ? or b.name like ? or c.img_title like ?) order by c.contribution_id desc";
		int co=0;
		
		if(search==null||search.equals("")){
			flg=true;
		}
		List<PhotoList> list=new ArrayList<PhotoList>();
		PhotoListDAO dao=new PhotoListDAO();
		if(flg){
		list=dao.select();
		}else if(!flg){
		list=dao.Search_select(search, sql, co);
		if(list==null||list.size()==0){
			co=5;
			sql="select * from Competition a,title b,contribution_details c,contribution d,tag e where e.contribution_id=d.contribution_id and d.contribution_id=c.contribution_id and c.title_id=b.title_id and a.Competition_id=b.Competition_id and (a.name like ? or b.name like ? or c.img_title like ? or e.name like ?) order by c.contribution_id desc";
			list=dao.Search_select(search, sql, co);
		}
		if(list==null||list.size()==0){
			list=dao.select();
		}
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
				page=page+"<a href=/HEW_Zidolympic/SerchPhotoServlet?check="+i+"&search="+search+">"+Integer.toString(i)+"</a>"+"　";
			}
		}
		request.setAttribute("page",page);

		
		
		///////////////////////////
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
						request.setAttribute("back", "<a href=/HEW_Zidolympic/SerchPhotoServlet?check="+k+"&search="+search+">＜＜</a>");	
				}
				
			
			
			
			
			
			
			int page_end=num*15+num;//１６件ずつ出したいからnum×１５＋ページ数をする
			int page_start=page_end-16;//出す最初の値を知りたいから―１６する
			
			ArrayList<PhotoList> list2=new ArrayList<PhotoList>();
			
			if(flg){
				list2=dao.select();
				}else if(!flg){
				list2=dao.Search_select(search, sql, co);
				if(list2==null||list2.size()==0){
					co=5;
					sql="select * from Competition a,title b,contribution_details c,contribution d,tag e where e.contribution_id=d.contribution_id and d.contribution_id=c.contribution_id and c.title_id=b.title_id and a.Competition_id=b.Competition_id and (a.name like ? or b.name like ? or c.img_title like ? or e.name like ?) order by c.contribution_id desc";
					list2=dao.Search_select(search, sql, co);
				}
				if(list2==null||list2.size()==0){
					list2=dao.select();
					request.setAttribute("mes", "その商品はありませんでした");
				}
				}
					
			list.clear();//初期化
			int i=0;
			int aaa=0;
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
				g.setTimestamp(p.getTimestamp());
				aaa=p.getContribution_id();
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
						request.setAttribute("next", "<a href=/HEW_Zidolympic/SerchPhotoServlet?check="+k+"&search="+search+">＞＞</a>");
			}
			
			
		}
		
		request.setAttribute("photolist", list);
		
		request.getRequestDispatcher("photolist.jsp").forward(request, response);		
	
		
		
		
		
	}

}

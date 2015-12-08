package photo;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
@MultipartConfig(location = "", maxFileSize = 1024 * 1024 * 2)
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
	
	
		request.setCharacterEncoding("UTF-8");

		
		
		// パラメータ"filename"のマルチパートデータ値を取得
		Part part = request.getPart("filename");

		// HTTPヘッダの値を取得
		String contentDisposition = part.getHeader("content-disposition");
		String contentType = part.getHeader("content-type");
		// ファイルサイズの取得
		long size = part.getSize();

		// uploadフォルダの絶対パスを調べる
		String path = getServletContext().getRealPath("upload");

		/* アップロードしたファイル名の取得 */
		// 変数contentDispositionから"filename="以降を抜き出す
		int filenamePosition = contentDisposition.indexOf("filename=");
		String filename = contentDisposition.substring(filenamePosition);
		// "filename="と"を除く
		filename = filename.replace("filename=", "");
		filename = filename.replace("\"", "");
		// 絶対パスからファイル名のみ取り出す
		filename = new File(filename).getName();

		boolean isJpegFile = false;
		// JPEG形式のチェック
		if ((contentType.equals("image/jpeg"))
				|| (contentType.equals("image/pjpeg"))) {
			// 画像ファイルをpath+filenameとして保存
//			part.write(path + "/" + filename);
			part.write("C://Users/yusuke-01/workspace_JV11/HEW_Zidolympic/WebContent/upload" + "/" + filename);
			isJpegFile = true;
			
			// サムネール画像の作成
			createThumbnail(path + "/" + filename, path + "/small/" + filename, 120);
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		if (isJpegFile) {
			out.println("アップロードファイルを保存しました。<br><br>");
			out.println("■ファイル情報<br><br>");
			out.println("HTTPヘッダ: " + contentDisposition);
			out.println("<br><br>MIMEタイプ: " + contentType);
			out.println("<br><br>ファイルサイズ: " + size);
//			out.println("<br><br>保存場所: " + path + "/" + filename);
			out.println("<br><br>保存場所:C://Users/mikan.shelty/Documents/workspace/photo/WebContent/upload/" +filename);
			out.println("<br><br>");
			out.println("<img src=\"upload/" + filename + "\">");
		} else {
			out.println("JPEG形式の画像をアップロードしてください。");
		}
		out.println("<br><br>");
		out.println("</body></html>");
	}

	private void createThumbnail(String originFile, String thumbFile, int width) {
		try {
			// 元画像の読み込み
			BufferedImage image = ImageIO.read(new File(originFile));
			// 元画像の情報を取得
			int originWidth = image.getWidth();
			int originHeight = image.getHeight();
			int type = image.getType();
			// 縮小画像の高さを計算
			int height = originHeight * width / originWidth;
			
			//縮小画像の作成
			BufferedImage smallImage = new BufferedImage(width, height, type);
			Graphics2D g2 = smallImage.createGraphics();
			
			// 描画アルゴリズムの設定(品質優先、アンチエイリアスON)
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,  RenderingHints.VALUE_RENDER_DEFAULT);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);

			// 元画像の縮小&保存
			g2.drawImage(image, 0, 0, width, height, null);
			ImageIO.write(smallImage, "jpeg", new File(thumbFile));
		} catch (Exception e) {
			log("画像の縮小に失敗: " + e);
		}		
	}
}
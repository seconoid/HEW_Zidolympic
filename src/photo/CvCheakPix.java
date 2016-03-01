package photo;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CvCheakPix {
	public static int a(String filename){
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

		Mat im = Imgcodecs.imread(url + filename);											// 入力画像の取得
		Mat hsv = new Mat();
		Mat mask = new Mat();
		Mat im2 = new Mat();
		Imgproc.cvtColor(im, hsv, Imgproc.COLOR_BGR2HSV);						// HSV色空間に変換
		Core.inRange(hsv, new Scalar(60,100,100), new Scalar(120,255,255), mask);	// 青色領域のマスク作成
		im.copyTo(im2, mask);												// マスクを 用いて入力画像から緑色領域を抽出
		Imgproc.cvtColor(im2, im2, Imgproc.COLOR_RGB2GRAY);				//グレイスケールに変換
		Imgcodecs.imwrite(url + "cvTest100.png", im2);					//cvtest100.jpgとして書き出し
		int grayCount = Core.countNonZero(im2);							//ｉｍ２内の０でない値をカウント
		System.out.println("Gray：" + grayCount);

		return grayCount;
		    }
	}


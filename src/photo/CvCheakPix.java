package photo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CvCheakPix {
	public static int a(String filename){

		Mat im = Imgcodecs.imread("C:\\Users\\yusuke-01\\workspace_JV11\\HEW_Zidolympic\\WebContent\\UploadImages\\"+filename);											// 入力画像の取得
		Mat hsv = new Mat();
		Mat mask = new Mat();
		Mat im2 = new Mat();
		Imgproc.cvtColor(im, hsv, Imgproc.COLOR_BGR2HSV);						// HSV色空間に変換
<<<<<<< HEAD
		Core.inRange(hsv, new Scalar(60,100,100), new Scalar(120,255,255), mask);	// 青色領域のマスク作成
=======
		Core.inRange(hsv, new Scalar(60,100,100), new Scalar(120,255,255), mask);	// 緑色領域のマスク作成
>>>>>>> remotes/origin/branch-c
		im.copyTo(im2, mask);												// マスクを 用いて入力画像から緑色領域を抽出
		Imgproc.cvtColor(im2, im2, Imgproc.COLOR_RGB2GRAY);				//グレイスケールに変換
		Imgcodecs.imwrite("C:\\Users\\yusuke-01\\workspace_JV11\\HEW_Zidolympic\\WebContent\\UploadImages\\cvTest100.png", im2);					//cvtest100.jpgとして書き出し
		int grayCount = Core.countNonZero(im2);							//ｉｍ２内の０でない値をカウント
		
		System.out.println("Gray：" + grayCount);
		System.out.println("画像：：："+ filename);

		return grayCount;
		
		    }
	}


package photo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CvCheakPix {
	public static void main(String[] args){
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat im = Imgcodecs.imread("./images/test8.png");											// 入力画像の取得
		Mat hsv = new Mat();
		Mat mask = new Mat();
		Mat im2 = new Mat();
		Imgproc.cvtColor(im, hsv, Imgproc.COLOR_BGR2HSV);						// HSV色空間に変換
		Core.inRange(hsv, new Scalar(0,1,0), new Scalar(80,15,255), mask);	// 緑色領域のマスク作成
		im.copyTo(im2, mask);												// マスクを 用いて入力画像から緑色領域を抽出
		Imgproc.cvtColor(im2, im2, Imgproc.COLOR_RGB2GRAY);				//グレイスケールに変換
		Imgcodecs.imwrite("./images/test2.jpg", im2);					//test2.jpgとして書き出し
		int grayCount = Core.countNonZero(im2);							//ｉｍ２内の０でない値をカウント
		System.out.println("Gray：" + grayCount);
		    }
		
		
	}


package photo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class CvCheakPix {
	public static void main(String[] args){
		
	       double[] data = new double[3];
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat A = Imgcodecs.imread("./images/color.jpg");	// 入力画像の取得
		Mat C = A.clone();
		for (int i = 0; i < A.rows(); i++){
		    for (int j = 0; j < A.cols(); j++) {
		        data = A.get(i, j);
		        data[0] = data[0];
		        data[1] = data[1];
		        data[2] = data[2];
		        C.put(i, j, data);
		        
				System.out.println("Blue：" + data[0]);
				System.out.println("Green：" + data[1]);
				System.out.println("Red：" + data[2]);
		    
		    }
		}
		    }
		
		
	}


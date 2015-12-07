package photo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class JKASD {
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat im = Imgcodecs.imread("9k=.png");	// 入力画像の取得
        System.out.println(im.size().width + " : " + im.size().height);
        Imgcodecs.imwrite("test.jpg",im);			// 画像データをJPG形式で保存
    }
}

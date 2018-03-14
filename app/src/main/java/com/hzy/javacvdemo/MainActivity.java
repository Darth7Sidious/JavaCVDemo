package com.hzy.javacvdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import org.bytedeco.javacpp.opencv_core.IplImage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.bd_logo1);
        // 实例化控件
        ImageView img = findViewById(R.id.imageView2);
        // 创建图像, p4是随便找的一张图像，这里可以自己随便找一张图片代替
        Drawable drawable = idToDrawable(R.drawable.bd_logo1);
        Bitmap bitmap = this.drawableToBitmap(drawable);
        // 将Bitmap转化为IplImage
        IplImage iplImage = this.bitmapToIplImage(bitmap);
        // 处理图像，比如旋转图像
        cvFlip(iplImage, iplImage, 0);
        // 再将IplImage转化为Bitmap
        bitmap = this.IplImageToBitmap(iplImage);
        img.setImageBitmap(bitmap);


    }

    /**
     * IplImage转化为Bitmap
     * @param iplImage
     * @return
     */
    public Bitmap IplImageToBitmap(IplImage iplImage) {
        Bitmap bitmap = null;
        bitmap = Bitmap.createBitmap(iplImage.width(), iplImage.height(),
                Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(iplImage.getByteBuffer());
        return bitmap;
    }

    /**
     * Bitmap转化为IplImage
     * @param bitmap
     * @return
     */
    public IplImage bitmapToIplImage(Bitmap bitmap) {
        IplImage iplImage;
        iplImage = IplImage.create(bitmap.getWidth(), bitmap.getHeight(),
                IPL_DEPTH_8U, 4);
        bitmap.copyPixelsToBuffer(iplImage.getByteBuffer());
        return iplImage;
    }

    /**
     * 将资源ID转化为Drawable
     * @param id
     * @return
     */
    public Drawable idToDrawable(int id) {
        return this.getResources().getDrawable(R.drawable.bd_logo1);
    }

    /**
     * 将Drawable转化为Bitmap
     * @param drawable
     * @return
     */
    public Bitmap drawableToBitmap(Drawable drawable) {
        if(drawable == null)
            return null;
        return ((BitmapDrawable)drawable).getBitmap();
    }

}

package in.co.magmacoin;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.TranslateAnimation;

public class Util {

	public static Bitmap getBitmapFromAsset(Context context, String strName)
    {
		Bitmap bitmap = null;
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            e.printStackTrace();
            //Log
        }
        return bitmap;
    }
	
	public static void animateViewToLeft(View view) {
		animate(view, 0, -view.getMeasuredWidth(), 0, 0);
	}
	
	public static void animateViewToRight(View view) {
		animate(view, 0, view.getMeasuredWidth(), 0, 0);
	}
	
	public static void animateViewToDown(View view) {
		animate(view, 0, 0 , 0, view.getMeasuredHeight() );
	}
	
	private static void animate(View view, float fromX, float toX, float fromY, float toY) {
	    TranslateAnimation anim = new TranslateAnimation( fromX, toX , fromY, toY );
	    anim.setDuration(500);
	    anim.setFillAfter( false);
	    anim.setFillBefore(false);
	    view.startAnimation(anim);
	}
	
	public static void closeSilently(Closeable c){
		try {
			c.close();
		}catch(Exception e) {
			//Silently
		}
	}
}

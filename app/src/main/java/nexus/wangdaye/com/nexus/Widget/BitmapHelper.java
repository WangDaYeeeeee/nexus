package nexus.wangdaye.com.nexus.Widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Helper us use bitmap image.
 * */

public class BitmapHelper {

    public static int getSize(int standardSize, int pixels) {
        Log.d("BitmapHelper", String.valueOf(pixels));
        return (int) (standardSize * (pixels / 1080.0));
    }

    public static Bitmap readBitMap(Context context, int resId, float width, float height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        options.inSampleSize = BitmapHelper.calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, float width, float height) {
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        int inSampleSize = 1;

        if (originalWidth > width || originalHeight > height) {
            int halfWidth = originalWidth / 2;
            int halfHeight = originalHeight / 2;
            while ((halfWidth / inSampleSize > width) &&(halfHeight / inSampleSize > height)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}

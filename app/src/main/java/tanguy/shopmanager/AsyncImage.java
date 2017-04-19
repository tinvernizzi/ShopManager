package tanguy.shopmanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AsyncImage extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;
    private Bitmap bitmap;

    public AsyncImage(ImageView view) {
        this.imageView = view;
    }

    @Override
    protected Bitmap doInBackground(String[] params) {
        Bitmap loadedBitmap=null;
        try {
            InputStream is = (InputStream) new URL(params[0]).getContent();
            loadedBitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap = loadedBitmap;
        return loadedBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap o) {
        super.onPostExecute(o);

        imageView.setImageBitmap(bitmap);
    }
}

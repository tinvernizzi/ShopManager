package tanguy.shopmanager;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = (TextView) findViewById(R.id.descriptionTextView);
        textView.setText("Description du produit : " + extras.getString("productDescription"));

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        collapsingToolbarLayout.setTitle(extras.getString("productName"));

        ImageView imageView = (ImageView) findViewById(R.id.productImageView);
        Picasso.with(this.getApplicationContext()).load("https://dummyimage.com/250x250/dddddd/000000.bmp").into(imageView);

        TextView priceView = (TextView) findViewById(R.id.priceTextView);
        priceView.setText(extras.getFloat("productPrice") + " €");

        TextView stockView = (TextView) findViewById(R.id.stockTextView);
        if (extras.getInt("incoming") > 0) {
            stockView.setText(extras.getInt("stock") + " produit(s) en stock,\n" +
                    extras.getInt("incoming") + " produit(s) en cours de livraison,\n" +
                    "livraison prévue le " + extras.getString("deliveryDate"));
        }
        else
            stockView.setText(extras.getInt("stock") + " produit(s) en stock");

    }
}

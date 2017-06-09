package tanguy.shopmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import tanguy.shopmanager.charts.TimeSpentChartActivity;
import tanguy.shopmanager.database.ProductsDatabaseHelper;
import tanguy.shopmanager.model.Article;
import tanguy.shopmanager.productTable.ArticleTableDataAdapter;
import tanguy.shopmanager.productTable.SortableArticleTableView;

public class TableActivity extends AppCompatActivity {

    List<Article> articleList = new ArrayList<Article>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        ProductsDatabaseHelper databaseHelper = new ProductsDatabaseHelper(this.getApplicationContext());
        try {
            databaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            databaseHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        articleList = databaseHelper.getAllArticles();

        final SortableArticleTableView articleTableView = (SortableArticleTableView) findViewById(R.id.tableView);

        if (articleTableView != null) {
            final ArticleTableDataAdapter articleTableDataAdapter = new ArticleTableDataAdapter(this, articleList, articleTableView);
            articleTableView.setDataAdapter(articleTableDataAdapter);
            articleTableView.addDataClickListener(new ArticleClickListener());
            articleTableView.addDataLongClickListener(new ArticleLongClickListener());
            articleTableView.setSwipeToRefreshEnabled(true);
            articleTableView.setSwipeToRefreshListener(new SwipeToRefreshListener() {
                @Override
                public void onRefresh(final RefreshIndicator refreshIndicator) {
                    articleTableView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final Article randomArticle = getRandomArticle();
                            articleTableDataAdapter.getData().add(randomArticle);
                            articleTableDataAdapter.notifyDataSetChanged();
                            refreshIndicator.hide();
                            Toast.makeText(TableActivity.this, "Base de donnée mise à jour", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);
                }
            });
        }
    }

    private Article getRandomArticle() {
        final int randomArticleIndex = Math.abs(new Random().nextInt() % articleList.size());
        return articleList.get(randomArticleIndex);
    }

    private class ArticleClickListener implements TableDataClickListener<Article> {

        @Override
        public void onDataClicked(final int rowIndex, final Article clickedData) {
            //final String articleString = "Click: " + clickedData.getName() + " " + clickedData.getName();
            //Toast.makeText(TableActivity.this, articleString, Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(getApplicationContext(), ProductActivity.class);
            myIntent.putExtra("productName", clickedData.getName());
            myIntent.putExtra("productDescription", clickedData.getDescription());
            myIntent.putExtra("productPrice", clickedData.getPrice());
            if (clickedData.getName().equals("SINGULAIR")) {
                myIntent.putExtra("productImage", "https://www.topcanadianpharmacy.org/wp-content/uploads/2016/08/Singulair-package.jpg");
            }
            else {
                myIntent.putExtra("productImage", "https://dummyimage.com/250x250/dddddd/000000.bmp");
            }
            myIntent.putExtra("deliveryDate", clickedData.getDeliveryDate());
            myIntent.putExtra("stock", clickedData.getStock());
            myIntent.putExtra("incoming", clickedData.getIncoming());
            getApplicationContext().startActivity(myIntent);
        }
    }

    private class ArticleLongClickListener implements TableDataLongClickListener<Article> {

        @Override
        public boolean onDataLongClicked(final int rowIndex, final Article clickedData) {
            //final String articleString = "Long Click: " + clickedData.getName() + " " + clickedData.getName();
            //Toast.makeText(TableActivity.this, articleString, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}

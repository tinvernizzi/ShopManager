package tanguy.shopmanager.productTable;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.LongPressAwareTableDataAdapter;
import tanguy.shopmanager.model.Article;


public class ArticleTableDataAdapter extends LongPressAwareTableDataAdapter<Article> {

    private static final int TEXT_SIZE = 14;
    private static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();


    public ArticleTableDataAdapter(final Context context, final List<Article> data, final TableView<Article> tableView) {
        super(context, data, tableView);
    }

    @Override
    public View getDefaultCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final Article article = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderId(article);
                break;
            case 1:
                renderedView = renderName(article);
                break;
            case 2:
                renderedView = renderStock(article);
                break;
            case 3:
                renderedView = renderIncoming(article);
                break;
        }

        return renderedView;
    }

    @Override
    public View getLongPressCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final Article article = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 1:
                renderedView = renderEditableCatName(article);
                break;
            default:
                renderedView = getDefaultCellView(rowIndex, columnIndex, parentView);
        }

        return renderedView;
    }

    private View renderEditableCatName(final Article article) {
        final EditText editText = new EditText(getContext());
        editText.setText(article.getName());
        editText.setPadding(20, 10, 20, 10);
        editText.setTextSize(TEXT_SIZE);
        editText.setSingleLine();
        editText.addTextChangedListener(new ArticleNameUpdater(article));
        return editText;
    }

    private View renderPrice(final Article article) {
        final String priceString = PRICE_FORMATTER.format(article.getPrice()) + " €";

        final TextView textView = new TextView(getContext());
        textView.setText(priceString);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);

        if (article.getPrice() < 10) {
            textView.setTextColor(0xFF2E7D32);
        } else if (article.getPrice() > 100) {
            textView.setTextColor(0xFFC62828);
        }

        return textView;
    }

    private View renderId(final Article article) {
        return renderString(((Integer)article.getID()).toString());
    }

    private View renderName(final Article article) {
        return renderString(article.getName());
    }

    private View renderStock(final Article article) {
        return renderString(((Integer)article.getStock()).toString());
    }

    private View renderIncoming(final Article article) {
        return renderString(((Integer)article.getIncoming()).toString());
    }


    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 10, 20, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    private static class ArticleNameUpdater implements TextWatcher {

        private Article articleToUpdate;

        public ArticleNameUpdater(Article articleToUpdate) {
            this.articleToUpdate = articleToUpdate;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // no used
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // not used
        }

        @Override
        public void afterTextChanged(Editable s) {
            articleToUpdate.setName(s.toString());
        }
    }

}

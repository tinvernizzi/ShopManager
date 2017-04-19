package tanguy.shopmanager.productTable;

import java.util.Comparator;

import tanguy.shopmanager.model.Article;


public final class ArticleComparators {

    private ArticleComparators() {
        //no instance
    }

    public static Comparator<Article> getArticleStockComparator() {
        return new ArticleStockComparator();
    }

    public static Comparator<Article> getArticleIDComparator() {
        return new ArticleIDComparator();
    }

    public static Comparator<Article> getArticleNameComparator() {
        return new ArticleNameComparator();
    }

    public static Comparator<Article> getArticleIncomingComparator() {
        return new ArticleIncomingComparator();
    }


    private static class ArticleNameComparator implements Comparator<Article> {

        @Override
        public int compare(final Article article1, final Article article2) {
            return article1.getName().compareTo(article2.getName());
        }
    }

    private static class ArticleStockComparator implements Comparator<Article> {

        @Override
        public int compare(final Article article1, final Article article2) {
            return article1.getStock() - article2.getStock();
        }
    }

    private static class ArticleIncomingComparator implements Comparator<Article> {

        @Override
        public int compare(final Article article1, final Article article2) {
            return article1.getIncoming() - article2.getIncoming();
        }
    }

    private static class ArticleIDComparator implements Comparator<Article> {

        @Override
        public int compare(final Article article1, final Article article2) {
            return article1.getID() - article2.getID();
        }
    }

}

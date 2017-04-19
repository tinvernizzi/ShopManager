package tanguy.shopmanager.model;

/**
 * Created by tanguy on 19/04/17.
 */

public class Article {

    int     id;
    String  name;
    int     stock;
    int     incoming;
    int     price;

    public Article(int id, String name, int stock, int incoming) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.incoming = incoming;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public int getIncoming() {
        return incoming;
    }

    public int getID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
}

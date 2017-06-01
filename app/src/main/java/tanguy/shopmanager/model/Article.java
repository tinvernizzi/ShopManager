package tanguy.shopmanager.model;

import android.util.Log;

public class Article {

    private String  web_adress;
    private String  last_name;
    private String  phone;
    private String  department;
    private String  first_name;
    private String  description;
    private String  deliveryDate;
    private String  image;
    private int     id;
    private String  name;
    private int     stock;
    private int     incoming;
    private Float   price;

    public Article(int id, String name, int stock, int incoming, String deliveryDate, String image, String price, String description, String department, String first_name, String last_name, String phone_number, String web_adress) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.incoming = incoming;
        this.deliveryDate = deliveryDate.substring(0, 10);
        this.image = image.replaceAll(".bmp","").concat(".bmp");
        this.image = this.image.concat("&text=" + name.replaceAll("\\\\s+", "+"));
        Log.d("IMAGE", image);
        price = price.replaceAll("€", "0").replaceAll(",",".");
        this.price = Float.parseFloat(price);
        this.description = description;
        this.department = department; //TODO : Enum
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone_number;
        this.web_adress = web_adress;
    }

    public String getWeb_adress() {
        return web_adress;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDepartment() {
        return department;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getDescription() {
        return description;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public int getIncoming() {
        return incoming;
    }

    public Float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }
}

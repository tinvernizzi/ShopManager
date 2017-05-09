package tanguy.shopmanager.model;

import android.util.Log;

public class Day {
    private int meanMoneySpent;
    private Integer meanTimeSpentInShop; //in minutes
    private Integer meanTimeSpentInShoppingMall; //in minutes
    private int numberOfSellers;
    private int temperatureCelsius;

    public Day(int meanMoneySpent, String meanTimeSpentInShop, String meanTimeSpentInShoppingMall, int numberOfSellers, int temperatureCelsius) {
        this.meanMoneySpent = meanMoneySpent;
        Log.d("shop", "Day: " + meanTimeSpentInShop);
        String[] shopSplit = meanTimeSpentInShop.split(":");
        this.meanTimeSpentInShop = Integer.parseInt(shopSplit[0]) * 60 + Integer.parseInt(shopSplit[1]);
        String[] mallSplit = meanTimeSpentInShoppingMall.split(":");
        this.meanTimeSpentInShoppingMall = Integer.parseInt(mallSplit[0]) * 60 + Integer.parseInt(mallSplit[1]);
        this.numberOfSellers = numberOfSellers;
        this.temperatureCelsius = temperatureCelsius;
    }

    public int getMeanMoneySpent() {
        return meanMoneySpent;
    }

    public int getMeanTimeSpentInShop() {
        return meanTimeSpentInShop;
    }

    public int getMeanTimeSpentInShoppingMall() {
        return meanTimeSpentInShoppingMall;
    }

    public int getNumberOfSellers() {
        return numberOfSellers;
    }

    public int getTemperatureCelsius() {
        return temperatureCelsius;
    }
}

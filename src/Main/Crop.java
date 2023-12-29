package Main;
import Character.MerchantNPC;

public class Crop implements MerchantPrice {
    private String cropName;
    private double purchasePrice;
    private double sellPrice;
    private int daysToGrow;
    private int daysGrown;
    public Crop(String name, double initPurchasePrice, double initSellPrice, int initDaysToGrow) {
        cropName = name;
        purchasePrice = initPurchasePrice;
        sellPrice = initSellPrice;
        daysGrown = initDaysToGrow;
    }
    public Crop(Crop crop) {
        cropName = crop.getName();
        purchasePrice = crop.getPurchasePrice();
        sellPrice = crop.getSellPrice();
        daysToGrow = crop.getDaysToGrow();
        daysGrown = 0;
    }
    public boolean canHarvest() {
        if (daysGrown >= daysToGrow) {
            return true;
        }
        return false;
    }
    public void grow() {
        if (getDaysLeftToGrow() > 0) {
            daysGrown++;
        }
    }
    public void tend(double daysToIncrease) {
        daysGrown += daysToIncrease;
        if (getDaysLeftToGrow() < 0) {
            daysGrown = daysToGrow;
        }
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public double getSellPrice() {
        return sellPrice;
    }
    public String getName() {
        return cropName;
    }
    public int getDaysToGrow() {
        return daysToGrow;
    }
    public int getDaysLeftToGrow() {
        return daysToGrow - daysGrown;
    }
}

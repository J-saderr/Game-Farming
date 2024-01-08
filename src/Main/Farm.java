package Main;

import java.util.ArrayList;

public class Farm {
    private ArrayList<Crop> crops = new ArrayList<Crop>();
    private double money;
    private double initMoney;
    public void increaseMoney(double alpha)
    {
        money += alpha;
    }
    public void decreaseMoney(double alpha)
    {
        money -= alpha;
    }
    public boolean canHarvestCrops()
    {
        for(Crop crop: crops)
        {
            if (crop.canHarvest())
            {
                return true;
            }
        }
        return false;
    }
    public double harvestAvailableCrops()
    {
        double moneyMade = 0;

        ArrayList<Crop> cropsToRemove = new ArrayList<Crop>();

        for(Crop crop: crops)
        {
            if (crop.canHarvest())
            {
                cropsToRemove.add(crop);
                moneyMade += crop.getSellPrice();
            }
        }

        for (Crop crop: cropsToRemove)
        {
            crops.remove(crops.indexOf(crop));
        }

        return moneyMade;
    }
    public void growCrops()
    {
        for(Crop crop: crops)
        {
            crop.grow();
            //crop.setDaysGrown();
        }
    }
    public void tendSpecificCrops(String cropName, double daysToIncrease)
    {
        for(Crop crop: crops)
        {
            if (crop.getName() == cropName)
            {
                crop.tend(daysToIncrease);
            }
        }
    }
    public void increaseCrops(Crop crop)
    {
        crops.add(new Crop(crop));
        money -= crop.getPurchasePrice();
    }
    public String returnCropsString(String cropsString, ArrayList<Crop> crops)
    {
        int index = 0;
        for(Crop crop: crops)
        {
            index++;
            cropsString += index + ". " + crop.getName() + "\n";
        }
        return cropsString;
    }
    public double getMoney()
    {
        return money;
    }
    public double getProfit()
    {
        return money - initMoney;
    }
    public ArrayList<Crop> getCrops()
    {
        return crops;
    }
}

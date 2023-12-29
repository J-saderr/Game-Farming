package Objects.Plants;

import java.util.Scanner;

enum VegetableType {
    CARROT, POTATO, SPINACH
}
public class Plant {
    VegetableType type;
    int stage;
    public Plant(VegetableType type) {
        this.type = type;
        this.stage = 0;
    }
    public void grow() {
        if (stage < 4) {
            stage++;
        }
    }
    public String getStatus() {
        switch (stage) {
            case 0: return type + " - Seed";
            case 1: return type + " - Sprout";
            case 2: return type + " - Small Plant";
            case 3: return type + " - Mature Plant";
            case 4: return type + " - Ready to Harvest";
            default: return "Unknown";
        }
    }
}

package ItemSystem.Entities.Tools;

import ItemSystem.Entities.Tool;

public class WateringCan extends Tool {
    private final static String WATERING_CAN_DESCRIPTION = "";
    private int waterLevel;

    public WateringCan() {
        this("WateringCan", WATERING_CAN_DESCRIPTION, 60);
    }

    public WateringCan(String name, String description, int mainMaxStat) {
        super(name, description, mainMaxStat);
    }

    public WateringCan(String name, String description, float sellPrice, float buyPrice, int mainMaxStat) {
        super(name, description, sellPrice, buyPrice, mainMaxStat);
    }

    @Override
    public void execute() {}

    public int getWaterLevel() { return waterLevel; }
    public void setWaterLevel(int waterLevel) { this.waterLevel = waterLevel; }
}
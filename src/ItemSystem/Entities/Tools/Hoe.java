package ItemSystem.Entities.Tools;

import ItemSystem.Entities.Tool;

public class Hoe extends Tool {
    private final static String HOE_DESCRIPTION = "";

    public Hoe() {
        this("Hoe", HOE_DESCRIPTION, 10);
    }

    public Hoe(String name, String description, int mainMaxStat) {
        super(name, description, mainMaxStat);
    }

    public Hoe(String name, String description, float sellPrice, float buyPrice, int mainMaxStat) {
        super(name, description, sellPrice, buyPrice, mainMaxStat);
    }

    @Override
    public void execute() {

    }
}

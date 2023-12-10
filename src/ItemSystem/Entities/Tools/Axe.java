package ItemSystem.Entities.Tools;

import ItemSystem.Entities.Tool;

public class Axe extends Tool {
    private final static String AXE_DESCRIPTION = "";

    public Axe() {
        this("Axe", AXE_DESCRIPTION, 10);
    }

    public Axe(String name, String description, int mainMaxStat) {
        super(name, description, mainMaxStat);
    }

    public Axe(String name, String description, float sellPrice, float buyPrice, int mainMaxStat) {
        super(name, description, sellPrice, buyPrice, mainMaxStat);
    }

    @Override
    public void execute() {

    }
}

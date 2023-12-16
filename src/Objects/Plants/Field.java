package Objects.Plants;
class Field {
    Plant plant;
    public void plantSeed(VegetableType type) {
        plant = new Plant(type);
    }
    public void waterAndGrow() {
        if (plant != null) {
            plant.grow();
        }
    }
    public Plant harvest() {
        if (plant != null && plant.stage == 4) {
            Plant harvested = plant;
            plant = null;
            return harvested;
        }
        return null;
    }
}


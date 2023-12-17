package Objects;
// Enum representing the type of the vegetable.
public enum VegetableType {
    CARROT,
    POTATO,
    CABBAGE
}
enum GrowthStage {
    SEED,
    SPROUT,
    YOUNG_PLANT,
    MATURE_PLANT
}
// Class representing a plant.
class Plant {
    private final VegetableType type;
    private GrowthStage stage;
    public Plant(VegetableType type) {
        this.type = type;
        this.stage = GrowthStage.SEED; // Initially, each plant is a seed.
    }
    public void grow() {
        switch (stage) {
            case SEED:
                stage = GrowthStage.SPROUT;
                break;
            case SPROUT:
                stage = GrowthStage.YOUNG_PLANT;
                break;
            case YOUNG_PLANT:
                stage = GrowthStage.MATURE_PLANT;
                break;
            case MATURE_PLANT:
                break;
        }
    }
    public VegetableType getType() {
        return type;
    }
    public GrowthStage getStage() {
        return stage;
    }
    // Here you'd have a method or logic to get UI representation of the stage.
    // For the sake of this example, we just return a string description.
    public String getStageUI() {
        switch (stage) {
            case SEED:
                return "/res/Plants/7.png"; // Replace these with actual path to your images or image objects.
            case SPROUT:
                return "/res/Plants/10.png";
            case YOUNG_PLANT:
                return "/res/Plants/13.png";
            case MATURE_PLANT:
                return "/res/Plants/14.png";
            default:
                return null;
        }
    }
}
// Class representing a plot of soil.
class SoilPlot {
    private boolean isWatered;
    public boolean isWatered() {
        return isWatered;
    }
    public void water() {
        isWatered = true;
    }
    public void dryOut() {
        isWatered = false;
    }
}
// Your main program would then create soil plots, seeds, and plants and manage the growth stages.

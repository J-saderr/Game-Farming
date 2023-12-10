package ItemSystem;

public class InventorySlot {
    private Entity entity; 

    public Entity getEntity() { return this.entity; }
    public void setEntity(Entity entity) { this.entity = entity; }

    public InventorySlot(Entity e) {
        this.entity = e;
    }

    public boolean isEmpty() {
        if (entity == null) return true;
        else return false;
    }

}
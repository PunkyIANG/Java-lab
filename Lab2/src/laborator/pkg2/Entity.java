package laborator.pkg2;

public abstract class Entity {
    public int hitPoints;
    
    public Entity() {
        hitPoints = 20 + Helper.randomizer.nextInt(20);
    }
    
    public Entity(int hitPoints) {
        this.hitPoints = hitPoints;
    }
    
    public abstract void Attack(Entity target);
    
    public void Damage(int damage) {
        hitPoints -= damage;
    }
    
    public boolean InstaKill() {
        hitPoints = 0;
        return true;
    }
    
    public boolean IsAlive() {
        return hitPoints > 0 ? true : false;
    }
    
    public String Serialize() {
        return Helper.gson.toJson(this);
    }
    
    public void KbInput() {
        Helper.print("Hit points: ");
        hitPoints = Helper.InputIntLimit(0);
    }
    
    public void PrintStats() {
        Helper.print("Entity " + hitPoints + " hp ");
    }
}

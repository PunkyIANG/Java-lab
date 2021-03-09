package laborator.pkg2;

public abstract class Sentinel extends Entity implements IMelee, IRanged {

    public Sentinel() {
        super();
        
        hitPoints = 60 + Helper.randomizer.nextInt(20);
    }
    
    public Sentinel(int hitPoints) {
        super(hitPoints);
    }
    
    public void Attack(Entity target) {
        float distance = Helper.randomizer.nextFloat() * 20;

        if (distance < 5f) {
            MeleeAttack(target);
        } else {
            RangedAttack(target);
        }
    }

    public final boolean InstaKill() {
        //immune to instakill
        return false;
    }
    
    public void PrintStats() {
        super.PrintStats();
        
        Helper.print(" sentinel ");
    }
}

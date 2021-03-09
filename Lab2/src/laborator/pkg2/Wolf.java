package laborator.pkg2;

public class Wolf extends Entity implements IMelee {
    
    int nullHowlCount;
    
    public Wolf() {
        super();
        nullHowlCount = 1 + Helper.randomizer.nextInt(3);
    }
    
    public Wolf(int hitPoints, int nullHowlCount) {
        super(hitPoints);
        
        this.nullHowlCount = nullHowlCount;
    }
    
    public void Attack(Entity target) {
        MeleeAttack(target);
    }
    
    public void MeleeAttack(Entity target) {
        target.Damage(20);
    }
    
    public void Damage(int damage) {
        float distance = Helper.randomizer.nextFloat() * 20;

        if (distance < 5f) {
            hitPoints -= damage;
        } else {
            if (Helper.randomizer.nextBoolean()) {
                hitPoints -= damage;
            }
            
            //if enemy is too far then the wolf has a chance to dodge 
        }
    }
    
    public void ProtectMarauder(int damage) {
        if (nullHowlCount > 0) {
            nullHowlCount--;
        } else {
            Damage(damage);
        }
        
        //the wolf can nullify some attacks, then it sacrifices itself to protect
    }
    
    public void KbInput() {
        super.KbInput();
        
        Helper.print("Null howl count: ");
        nullHowlCount = Helper.InputIntLimit(0);
    }
    
    public void PrintStats() {
        super.PrintStats();
        Helper.print("Wolf " + nullHowlCount + " null howl(s) ");
    }
    
    
}

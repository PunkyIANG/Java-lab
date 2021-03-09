package laborator.pkg2;

public class Marauder extends Sentinel {

    public Wolf summonedWolf;

    public Marauder() {
        super();

        if (Helper.randomizer.nextBoolean()) {
            SummonWolf();
        }
    }
    
    public Marauder(int hitPoints, Wolf summonedWolf) {
        super(hitPoints);
        
        this.summonedWolf = summonedWolf;
    }

    public void MeleeAttack(Entity target) {
        //axe
        target.Damage(20);
    }

    public void RangedAttack(Entity target) {
        target.Damage(15);
    }

    public void Damage(int damage) {
        float distance = Helper.randomizer.nextFloat() * 20;

        if (distance < 5f) {
            if (summonedWolf.IsAlive()) {
                summonedWolf.ProtectMarauder(damage);
            } else {
                hitPoints -= damage;
            }
        }

        //if enemy is too far then shield is triggered
    }

    public void SummonWolf() {

        if (summonedWolf == null
                || !summonedWolf.IsAlive()) {
            summonedWolf = new Wolf();
        }
    }

    public void KbInput() {
        super.KbInput();

        Helper.print("Initialize wolf? true/false: ");
        boolean initWolf = Boolean.valueOf(Helper.InputString());

        if (initWolf) {
            summonedWolf = new Wolf();
            summonedWolf.KbInput();
        }
    }
    
    public void PrintStats() {
        super.PrintStats();
        
        Helper.print(" marauder ");
        
        if (summonedWolf != null) {
            summonedWolf.PrintStats();
        }
    }

}

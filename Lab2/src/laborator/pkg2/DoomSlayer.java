package laborator.pkg2;

public final class DoomSlayer extends Sentinel {

    int ammo;
    
    public DoomSlayer() {
        super();
        
        ammo = 15 + Helper.randomizer.nextInt(10);
    }
    
    public DoomSlayer(int hitPoints, int ammo) {
        super(hitPoints);
        
        this.ammo = ammo;
    }

    public void MeleeAttack(Entity target) {
        
        if (hitPoints < 50) {
            BloodPunch(target);
        } else if (ammo < 10) {
            //saw
            Chainsaw(target);
        } else {
            //crucible
            target.InstaKill();
        }
    }

    public void RangedAttack(Entity target) {
        target.Damage(15);
        ammo--;
    }

    public void BloodPunch(Entity target) {
        target.Damage(20);
        this.hitPoints += 30;
    }

    public void Chainsaw(Entity target) {
        if (target.InstaKill()) {
            ammo += 10;
        }
    }
    
    public void KbInput() {
        super.KbInput();
        
        Helper.print("Ammo: ");
        ammo = Helper.InputIntLimit(0);
    }
    
    public void PrintStats() {
        super.PrintStats();
        
        Helper.print(" doom slayer " + ammo + " bullets");
    }

}

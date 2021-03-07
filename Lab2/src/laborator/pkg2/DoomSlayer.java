
package laborator.pkg2;

public final class DoomSlayer extends Sentinel {

    int ammo = 20;

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
        this.hitPoints += 20;
    }

    public void Chainsaw(Entity target) {
        if (target.InstaKill()) {
            ammo += 10;
        }
    }

}

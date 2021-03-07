/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laborator.pkg2;

/**
 *
 * @author Professional
 */
public abstract class Sentinel extends Entity implements IMelee, IRanged {

    public Sentinel() {
        hitPoints = 60 + Helper.randomizer.nextInt(20);
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
}

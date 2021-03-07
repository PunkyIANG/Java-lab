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
public class Marauder extends Sentinel {

    public Wolf summonedWolf;
    
    public Marauder() {
        super();
        
        if (Helper.randomizer.nextBoolean()) {
            SummonWolf();
        }
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

    public Wolf SummonWolf() {

        if (summonedWolf == null
                || !summonedWolf.IsAlive()) {
            summonedWolf = new Wolf();
            return summonedWolf;
        } else {
            return null;
        }

    }

}

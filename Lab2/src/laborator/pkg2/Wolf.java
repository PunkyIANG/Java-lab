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
public class Wolf extends Entity implements IMelee {
    
    int nullHowlCount;
    
    public Wolf() {
        hitPoints = 20 + Helper.randomizer.nextInt(20);
        nullHowlCount = 1 + Helper.randomizer.nextInt(3);
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
}

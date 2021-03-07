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
public abstract class Entity {
    public int hitPoints;
    
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
}

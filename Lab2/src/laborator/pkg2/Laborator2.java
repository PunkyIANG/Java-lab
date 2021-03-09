package laborator.pkg2;

public class Laborator2 {
    public static void main(String[] args) {
        Entity[] entities = new Entity[] {
            new DoomSlayer(),
            new Marauder(60, null),
            new Wolf(),
            new Marauder()
        };
        
        entities[0].KbInput();
        // entities[3] = Helper.gson.fromJson(entities[0].Serialize(), DoomSlayer.class);
        entities[3] = Helper.gson.fromJson(Helper.FileRead("0.json"), DoomSlayer.class);
        
        for (int i = 0; i < entities.length; i++) {
            entities[i].PrintStats();
            Helper.println("");
        }
        
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] instanceof Marauder && ((Marauder)entities[i]).summonedWolf == null) {
                ((Marauder)entities[i]).SummonWolf();
            }
        }
        
        Helper.println("");
        
        for (int i = 0; i < entities.length; i++) {
            entities[i].PrintStats();
            Helper.println("");
        }
        
        IRanged[] ranged = new IRanged[entities.length];
        Helper.println("");
        
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] instanceof IRanged) {
                ranged[i] = ((IRanged)entities[i]);
            } else {
                ranged[i] = new Marauder();
            }
            
            if (ranged[i] instanceof DoomSlayer) {
                ((DoomSlayer)ranged[i]).BloodPunch((DoomSlayer)ranged[i]);
                ((DoomSlayer)ranged[i]).PrintStats();
                Helper.println("");
            } else if (ranged[i] instanceof Marauder) {
                ((Marauder)ranged[i]).SummonWolf();
                ((Marauder)ranged[i]).PrintStats();
                Helper.println("");
            }
        }
        
        for (int i = 0; i < entities.length; i++) {
            Helper.FileWrite(i + ".json", entities[i].Serialize());
        }
        
        
        
        
    }
    
}

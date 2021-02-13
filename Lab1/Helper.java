import java.io.*;

public class Helper {
    public static String InputString() {
        BufferedReader box = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        try {
            str = box.readLine();
        } catch (Exception e) {
            System.out.println("Shit's fucked man");
            System.out.println(e.getMessage());
        }

        return str;
    }

    //add some validation over here

    public static int InputInt() { 
        return (Integer.valueOf(InputString())).intValue();
    }

    public static float InputFloat() { 
        return (Float.valueOf(InputString())).floatValue();
    }
}

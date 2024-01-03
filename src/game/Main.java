package game;

public class Main {

    public static void main(String[] args) {
        boolean debug=false;
        int level;
        if(args[0].equals("true"))
            debug = true;
        new Game(debug,Integer.parseInt(args[1]));
    }

}

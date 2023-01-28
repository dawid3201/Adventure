/*
    Only add/edit code where it is stated in the description.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private Map<Integer, Location> locations = new HashMap<Integer, Location>();
    private Map<String,String> vocabulary = new HashMap<>();
    public static void main(String[] args){
        Main main = new Main();
        main.command();
    }

    public Main() {
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of a hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("WEST","W");
        vocabulary.put("EAST","E");
        vocabulary.put("QUIT","Q");
    }


    public void command() {
        Scanner txt = new Scanner(System.in);// take the input
        boolean flag = true;//if false then Quit the loop
        String direction = "";//to store direction
        int currentLocation = 1;//our current location we start at 1

        System.out.println(locations.get(currentLocation).getDescription());//print where we are
        System.out.print("Available exits are: ");

        while(flag){//if the boolean is true, the loop runs
            for(String goMaps : locations.get(currentLocation).getExits().keySet()){//we get our options || keySet() - returns all the KEYS present in the entires of the hashmap
                System.out.print(goMaps + ", ");
            }
            System.out.println();//space

            String choice = txt.nextLine();//take input
            if(choice.length() > 1){//if input contains at least one letter
                String [] split = choice.split(" ");//we split our String
                for(int i = 0; i< split.length;i++){//go through splited String
                    if(vocabulary.containsKey(split[i].toUpperCase())){//if vocabulary contains capital letter of Q, W, S, E or N set direction to this letter
                        direction = vocabulary.get(split[i].toUpperCase());//set direction here
                    }
                }
            }else{
                direction = choice.toUpperCase();
            }

            if(direction.equals("Q")){//if Q then exit
                System.out.println(locations.get(0).getDescription());
                flag = false;
                txt.close();
            }else if(locations.get(currentLocation).getExits().containsKey(direction)){//if Locations contains Direction then change current location
                currentLocation = locations.get(currentLocation).getExits().get(direction);//we change current location here
                System.out.println(locations.get(currentLocation).getDescription());
                System.out.print("Available exits are: ");
            }else{
                System.out.println("You cannot go in that direction.");
                System.out.println(locations.get(currentLocation).getDescription());
                System.out.print("Available exits are: ");
            }

        }

    }
}


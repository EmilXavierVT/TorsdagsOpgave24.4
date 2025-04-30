package game.util;

import game.board.Map;
import game.logic.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    private UserInterface ui =new UserInterface();

    public void saveData(ArrayList<String> list, String path, String header){
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(header+"\n");
            for (String s : list) {
                writer.write(s+"\n");
            }
            writer.close();
        }catch (IOException e) {
            System.out.println("problem: "+ e.getMessage());
        }
    }

    public void appendData(String object, String path){
        try {
            FileWriter writer = new FileWriter(path, true); // overloaded FileWriter, true activates append mode
            writer.write(object.stripTrailing() + System.lineSeparator());
            writer.close();
        }catch (IOException e) {
            System.out.println("problem: "+ e.getMessage());
        }
    }

    public ArrayList<String> readData(String path) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();   //  "tess, 0"
                data.add(line);
            }
        } catch (FileNotFoundException e) {
        }
        return data;
    }

    public ArrayList<String> readusercsvData(String path) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();   //  "tess, 0"
                data.add(line);
            }
        } catch (FileNotFoundException e) {
        }
        return data;
    }


    public void savePlayer(Player player, String user) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/userdata/"+user+".txt"))) {
            out.writeObject(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player loadPlayer(String user) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/userdata/"+user+".txt"))) {
            return (Player) in.readObject();
        } catch (Exception e) {
            ui.showHelp();
            return null;
        }
    }
    public void saveMap(Map map, String user) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/userdata/map"+user+".txt"))) {
            out.writeObject(map);
        } catch (Exception e) {
            System.out.println("New User!");;
        }
    }

    public Map loadMap(String user) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/userdata/map"+user+".txt"))) {
            return (Map) in.readObject();
        } catch (Exception e) {
            System.out.println("\n"+"Welcome to the game");;
            return null;
        }
    }




}

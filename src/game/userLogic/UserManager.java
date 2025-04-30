package game.userLogic;

import game.util.FileIO;
import game.util.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class UserManager {
    static FileIO io;
    static UserInterface ui;
    private HashMap<String, User> accounts;
    private String path = "data/users.csv";

    public UserManager() {
        this.accounts = new HashMap<>();
        this.io = new FileIO();
        this.ui = new UserInterface();
        loadUserData();
        
    }

    public void loadUserData() {
        ArrayList<String> userdata = io.readData(this.path);

        for (String s : userdata) {
            String[] values = s.split(",");
            String name = values[0].trim();
            String password = values[1].trim();



            this.accounts.put(name, new User(name,password));
        }
    }
    
    public User createUser(){
        String name = " ";
        boolean isUserNameTaken = true;

        while (isUserNameTaken) {

            name = ui.promptText("please enter your name");
            //Hvis bruger giver tomt input
            if (name == null || name.trim().isEmpty()) {
                ui.printMessage("Please enter a name");
                continue;

            }
            if (isUserInSystem(name)) {
                ui.printMessage("There already is a user with that name!");

            } else {
                isUserNameTaken = false;
            }
        }
        boolean validPassword = false;
        String password = "";
        while (!validPassword) {
            password = ui.promptText("Please enter a password!:\nSpecifications: min. 6 char, min 1 uppercase, min 1. lowercase, min 1 number:");
            validPassword = isValidPassword(password);
        }
        User user =new User(name,password);
       appendUserData(user);
       return user;
    }
    public boolean isUserInSystem(String username) {
        return this.accounts.containsKey(username);
    }
    
    
    private boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            ui.printMessage("Password kan ikke være tomt.");
            return false;
        }
        if (password.length() < 6) {
            ui.printMessage("Password skal være mindst 6 tegn langt.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            ui.printMessage("Password skal indeholde mindst et stort bogstav.");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            ui.printMessage("Password skal indeholde mindst et lille bogstav.");
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            ui.printMessage("Password skal indeholde mindst et tal.");
            return false;
        }
        return true;
    }
    public User login() {
        boolean isPasswordCorrect = false;

        while (!isPasswordCorrect) {
            String userName = ui.promptText("Indtast dit brugernavn: ");
            String password = ui.promptText("Indtast password: ");
            if (isUserNameAndPasswordCorrect(userName, password)) {
                User user = getUser(userName);
                return user;

            } else {
                ui.printMessage("Brugernavn eller password forkert" + "\n" + "Prøv igen");
                System.out.println("");
            }
        }
        return null;
    }

    public User getUser(String userName) {
        return this.accounts.get(userName);
    }
    public boolean isUserNameAndPasswordCorrect(String userName, String password) {
        if (this.accounts.containsKey(userName)) {
            User user = getUser(userName);
            boolean equals = user.getPassword().equals(password);
            return equals;
        }
        return false;
    }

    public User loginProcess() {

        ui.printMessage("Welcome you must login or create an account to play !");

        boolean isChoosen = true;

        while (isChoosen) {
            int choice = ui.promptInteger("1) Create an account" + "\n" + "2) login with existing account " + "\n" + "3) Terminate program ");

            if (choice == 1) {
                return createUser();


            } else if (choice == 2) {
                return login();


            } else if (choice == 3) {
                endProgram();

            } else {
                ui.printMessage("Choose a valid choice please");
            }
        }
        return null;
    }
    public void appendUserData(User user) {
        io.appendData(user.toString(),"data/users.csv");
    }

    public void endProgram(){
        // Save user state to CSV
        // manager.saveUserState(path)
        ui.printMessage("Until next time ! 🎉 👋");
        System.exit(0);
    }
}

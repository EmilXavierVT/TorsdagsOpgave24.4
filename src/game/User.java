package game;

import game.logic.Player;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String password;


    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//      Safety matters... but who wil know :)
    @Override
    public String toString(){
        String s =  this.name + ", " + this.password ;
        return s;
    }
}

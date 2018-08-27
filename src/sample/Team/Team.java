package sample.Team;

import sample.Person.Player;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Team implements Serializable{
    private int points;
    private String teamName;

    public Team() {
        teamName = " ** ";
        points = 0;
    }


    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPoints(){ return points; }

    public void addPoints(int points){
        this.points += points;
    }

    public void setPoints(int points){ this.points = points; }

}

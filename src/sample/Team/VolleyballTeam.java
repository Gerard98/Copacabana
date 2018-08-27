package sample.Team;

import sample.Person.Player;

import java.io.Serializable;
import java.util.List;
import java.util.LinkedList;

public class VolleyballTeam extends Team{
    private List<Player> playersList;

    public VolleyballTeam() {
        super();
        playersList = new LinkedList<Player>();
    }

    public VolleyballTeam(Player player1, Player player2, String teamName) {
        super(teamName);
        playersList = new LinkedList<Player>();
        ((LinkedList) playersList).add(player1);
        ((LinkedList) playersList).add(player2);
    }

    public void addPlayer(Player player){
        if(playersList.size() < 2){
            playersList.add(player);
        }
    }

    public String getName(int index){
        return playersList.get(index).getName();
    }
    public String getSurname(int index){
        return playersList.get(index).getSurname();
    }


    public boolean equals(VolleyballTeam team2){
        if (this == team2)
            return true;
        if (team2 == null)
            return false;
        if (getClass() != team2.getClass())
            return false;
        if(this.getTeamName().equals(team2.getTeamName())) return true;
        return false;
    }



}

package sample.Team;

import sample.Person.Player;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class DodgeballTeam extends Team {
    private List<Player> playersList;

    public DodgeballTeam(){
        super();
        playersList = new LinkedList<Player>();
    }

    public DodgeballTeam(Player player1, Player player2, Player player3, Player player4, Player player5, String teamName) {
        super(teamName);
        playersList = new LinkedList<Player>();
        ((LinkedList) playersList).add(player1);
        ((LinkedList) playersList).add(player2);
        ((LinkedList) playersList).add(player3);
        ((LinkedList) playersList).add(player4);
        ((LinkedList) playersList).add(player5);
    }

    public void addPlayer(Player player) {
        if (playersList.size() < 5) {
            playersList.add(player);
        }
    }

    public String getName(int index){
        return playersList.get(index).getName();
    }

    public String getSurname(int index) {
        return playersList.get(index).getSurname();
    }
}

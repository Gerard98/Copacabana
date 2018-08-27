package sample.Team;

import sample.Person.Player;

import java.util.LinkedList;
import java.util.List;

public class TugOfWarTeam extends Team {
    private List<Player> playerList;

    public TugOfWarTeam(){
        super();
        playerList = new LinkedList<>();
    }

    public TugOfWarTeam(String teamName, Player player1, Player player2, Player player3, Player player4, Player player5){
        super(teamName);
        playerList = new LinkedList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        playerList.add(player5);
    }

    public String getName(int index){
        return playerList.get(index).getName();
    }
    public String getSurname(int index){
        return playerList.get(index).getSurname();
    }

    public boolean equals(TugOfWarTeam team2){
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

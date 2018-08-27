package sample.Match;

import sample.Person.MainJudge;
import sample.Team.Team;
import sample.Team.TugOfWarTeam;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TugOfWarMatch extends Match implements Serializable {
    private MainJudge mainJudge;
    private TugOfWarTeam team1;
    private TugOfWarTeam team2;


    public TugOfWarMatch(TugOfWarTeam team1, TugOfWarTeam team2){
        this.team1 = team1;
        this.team2 = team2;
    }


    @Override
    public String toString(){
        return team1.getTeamName() + " vs " + team2.getTeamName();
    }

    @Override
    public TugOfWarTeam getTeam1() {
        return team1;
    }

    public void setTugOfWarTeam1(TugOfWarTeam team1) {
        this.team1 = team1;
    }

    @Override
    public MainJudge getMainJudge() {
        return mainJudge;
    }

    @Override
    public void setMainJudge(MainJudge mainJudge) {
        this.mainJudge = mainJudge;
    }

    @Override
    public TugOfWarTeam getTeam2() {
        return team2;
    }

    public void setTugOfWarTeam2(TugOfWarTeam team2) {
        this.team2 = team2;
    }


    @Override
    public void setTeam1(Team team1){}
    @Override
    public void setTeam2(Team team2){}

}

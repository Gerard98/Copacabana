package sample.Match;

import sample.Person.MainJudge;
import sample.Team.Team;

import java.io.Serializable;

public class DodgeballMatch extends Match implements Serializable {
    private MainJudge mainJudge;
    private Team team1;
    private Team team2;

    public DodgeballMatch(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }

    @Override
    public String toString(){
        return team1.getTeamName() + " vs " + team2.getTeamName();
    }

    @Override
    public Team getTeam1() {
        return team1;
    }

    @Override
    public void setTeam1(Team team1) {
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
    public Team getTeam2() {
        return team2;
    }

    @Override
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }





}

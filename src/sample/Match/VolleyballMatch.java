package sample.Match;

import sample.Person.Linesman;
import sample.Person.MainJudge;

import sample.Team.Team;
import sample.Team.VolleyballTeam;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class VolleyballMatch extends Match implements Serializable {
    private MainJudge mainJudge;
    private VolleyballTeam team1;
    private VolleyballTeam team2;
    private List<Linesman> linesmenList;

    public VolleyballMatch(VolleyballTeam team1, VolleyballTeam team2){
        this.team1 = team1;
        this.team2 = team2;
    }

    public List<Linesman> getLinesmenList() {
        return linesmenList;
    }

    public void addLinesman(Linesman linesman) {
        if(linesmenList == null)
            linesmenList = new LinkedList<Linesman>();

        linesmenList.add(linesman);
    }

    @Override
    public String toString(){
        return team1.getTeamName() + " vs " + team2.getTeamName();
    }

    @Override
    public VolleyballTeam getTeam1() {
        return team1;
    }

    public void setVolleyballTeam1(VolleyballTeam team1) {
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
    public VolleyballTeam getTeam2() {
        return team2;
    }

    public void setVolleyballTeam2(VolleyballTeam team2) {
        this.team2 = team2;
    }


    @Override
    public void setTeam1(Team team1){}
    @Override
    public void setTeam2(Team team2){}

}

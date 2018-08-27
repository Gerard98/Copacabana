package sample.Match;

import sample.Person.Linesman;
import sample.Person.MainJudge;
import sample.Team.Team;
import sample.Team.VolleyballTeam;

import java.io.Serializable;
import java.util.List;

public abstract class Match {


    public abstract Team getTeam1();

    public abstract void setTeam1(Team team1);

    public abstract Team getTeam2();

    public abstract void setTeam2(Team team2);

    public abstract MainJudge getMainJudge();

    public abstract void setMainJudge(MainJudge mainJudge);



}

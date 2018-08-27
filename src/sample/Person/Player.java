package sample.Person;
        import sample.Team.Team;

        import java.io.Serializable;

public class Player extends Person{
    private int TeamID;

    public Player(){
        super();
    }

    public Player(String Name, String Surname){
        super(Name, Surname);
    }

    public void addToTeam(Team team){};
    public void deleteFromTeam(){};


}

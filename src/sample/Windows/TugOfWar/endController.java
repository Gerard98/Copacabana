package sample.Windows.TugOfWar;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Team.TugOfWarTeam;
import sample.Team.VolleyballTeam;

import java.io.*;

public class endController {
    @FXML
    private Label teamName;
    @FXML
    private Label player1;
    @FXML
    private Label player2;
    @FXML
    private Label player3;
    @FXML
    private Label player4;
    @FXML
    private Label player5;

    @FXML
    public void initialize(){
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("TugOfWarTeams.ser"));
            try{
                TugOfWarTeam team =(TugOfWarTeam) input.readObject();
                teamName.setText(team.getTeamName());
                player1.setText(team.getName(0) + " " + team.getSurname(0));
                player2.setText(team.getName(1) + " " + team.getSurname(1));
                player3.setText(team.getName(2) + " " + team.getSurname(2));
                player4.setText(team.getName(3) + " " + team.getSurname(3));
                player5.setText(team.getName(4) + " " + team.getSurname(4));
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void endAction(){
        Stage stage = (Stage) teamName.getScene().getWindow();
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("TugOfWarTeams.ser"));
            output.reset();
            output.close();
            ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("TugOfWarJudges.ser"));
            output2.reset();
            output2.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        stage.close();
    }
}

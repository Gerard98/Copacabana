package sample.Windows.Dodgeball;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Team.DodgeballTeam;

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
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("DodgeballTeams.ser"));
            try{
                DodgeballTeam team =(DodgeballTeam) input.readObject();
                teamName.setText(team.getTeamName());
                player1.setText(team.getName(0) + " " + team.getSurname(0));
                player2.setText(team.getName(1) + " " + team.getSurname(1));
                player3.setText(team.getName(2) + " " + team.getSurname(2));
                player4.setText(team.getName(3) + " " + team.getSurname(3));
                player5.setText(team.getName(4) + " " + team.getSurname(4));
            }
            catch(ClassNotFoundException ex){

            }
        }
        catch(IOException ex){

        }
    }

    @FXML
    public void endAction(){
        Stage stage = (Stage) teamName.getScene().getWindow();
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballTeams.ser"));
            output.reset();
            output.close();
            ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("DodgeballMainJudges.ser"));
            output2.reset();
            output2.close();
        }
        catch(IOException ex){

        }
        stage.close();
    }
}

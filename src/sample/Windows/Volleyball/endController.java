package sample.Windows.Volleyball;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
    public void initialize(){
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("VolleyballTeams.ser"));
            try{
                VolleyballTeam team =(VolleyballTeam) input.readObject();
                teamName.setText(team.getTeamName());
                player1.setText(team.getName(0) + " " + team.getSurname(0));
                player2.setText(team.getName(1) + " " + team.getSurname(1));
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
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballTeams.ser"));
            output.reset();
            output.close();
            ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("VolleyballLinesmen.ser"));
            output2.reset();
            output2.close();
            ObjectOutputStream output3 = new ObjectOutputStream(new FileOutputStream("VolleyballMainJudges.ser"));
            output2.reset();
            output2.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        stage.close();
    }
}

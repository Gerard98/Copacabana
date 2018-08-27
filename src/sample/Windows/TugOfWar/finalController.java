package sample.Windows.TugOfWar;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Person.MainJudge;
import sample.Team.TugOfWarTeam;
import sample.Team.VolleyballTeam;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class finalController {

    @FXML
    private Button confirmButton;
    @FXML
    private Label label;
    @FXML
    private Label judge;
    @FXML
    private ListView listView1;

    private List<TugOfWarTeam> teams;
    private List<MainJudge> mainJudges;


    @FXML
    public void initialize(){
        teams = new LinkedList<>();
        mainJudges = new LinkedList<>();

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("TugOfWarTeams.ser"));
            ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("TugOfWarJudges.ser"));
            try{
                try {
                    while (!(input == null)) teams.add((TugOfWarTeam) input.readObject());
                }
                catch(EOFException e){
                    System.out.println(e.getMessage());
                }
                try {
                    while (!(input2 == null)) mainJudges.add((MainJudge) input2.readObject());
                }
                catch(EOFException e){
                    System.out.println(e.getMessage());
                }
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }

            input.close();
            input2.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        Random random = new Random();
        int i = random.nextInt(mainJudges.size());
        judge.setText(mainJudges.get(i).getName() + " " + mainJudges.get(i).getSurname());
        mainJudges.clear();

        label.setText(teams.get(0).getTeamName() + " vs " + teams.get(1).getTeamName());
        ObservableList<String> obsTeams = FXCollections.observableArrayList();
        obsTeams.addAll(teams.get(0).getTeamName());
        obsTeams.addAll(teams.get(1).getTeamName());
        listView1.setItems(obsTeams);
    }

    @FXML
    public void confirmAction() {
        if(listView1.getSelectionModel().getSelectedIndex() == 0) end(teams.get(0));
        else end(teams.get(1));
    }

    public void end(TugOfWarTeam winner){
        Stage stage = (Stage) confirmButton.getScene().getWindow();

        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("TugOfWarTeams.ser"));
            output.writeObject((TugOfWarTeam) winner);
            output.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("end.fxml"));
            Stage tournament = new Stage();
            tournament.setTitle("End!");
            tournament.setScene(new Scene(root, 400, 450));
            Platform.setImplicitExit(false);
            tournament.setOnCloseRequest( e -> e.consume());
            tournament.show();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        stage.close();
    }

}

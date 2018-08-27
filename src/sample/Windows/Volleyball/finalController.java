package sample.Windows.Volleyball;

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
import sample.Person.Linesman;
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
    private ListView listView1;
    @FXML
    private ListView listView2;
    @FXML
    private ListView listView3;
    @FXML
    private Label linesman1;
    @FXML
    private Label linesman2;
    @FXML
    private Label mainJudge;


    private List<VolleyballTeam> teams;
    private List<Linesman> linesmen;
    private List<MainJudge> mainJudges;

    @FXML
    public void initialize(){
        teams = new LinkedList<>();
        linesmen = new LinkedList<>();
        mainJudges = new LinkedList<>();

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("VolleyballTeams.ser"));
            ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("VolleyballLinesmen.ser"));
            ObjectInputStream input3 = new ObjectInputStream(new FileInputStream("VolleyballMainJudges.ser"));
            try{
                try {
                    while (!(input == null)) teams.add((VolleyballTeam) input.readObject());
                }
                catch(EOFException ex){
                    System.out.println("End Of File!");
                }
                try {
                    while (!(input2 == null)) linesmen.add((Linesman) input2.readObject());
                }
                catch(EOFException ex){
                    System.out.println("End Of File!");
                }
                try {
                    while (!(input3 == null)) mainJudges.add((MainJudge) input3.readObject());
                }
                catch(EOFException ex){
                    System.out.println("End Of File!");
                }
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }

            input.close();
            input2.close();
            input3.close();

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        Random random = new Random();
        int i = random.nextInt(mainJudges.size());
        mainJudge.setText(mainJudges.get(i).getName() + " " + mainJudges.get(i).getSurname());
        i = random.nextInt(linesmen.size());
        linesman1.setText(linesmen.get(i).getName() + " " + linesmen.get(i).getSurname());
        linesmen.remove(i);
        i = random.nextInt(linesmen.size());
        linesman2.setText(linesmen.get(i).getName() + " " + linesmen.get(i).getSurname());
        mainJudges.clear();
        linesmen.clear();

        ObservableList<String> obsTeams = FXCollections.observableArrayList();
        obsTeams.addAll(teams.get(0).getTeamName());
        obsTeams.addAll(teams.get(1).getTeamName());
        listView1.setItems(obsTeams);
        listView2.setItems(obsTeams);
        listView3.setItems(obsTeams);


    }


    @FXML
    public void confirmAction() {
        boolean end2 = false;
        if (listView1.getSelectionModel().getSelectedIndex() != listView2.getSelectionModel().getSelectedIndex() && !listView3.isDisable()) {
            int winner = listView3.getSelectionModel().getSelectedIndex();
            if (winner == 0) end(teams.get(0));
            else end(teams.get(1));
            end2 = true;
        }
        if (end2 == false) {
            if (listView1.getSelectionModel().getSelectedIndex() != listView2.getSelectionModel().getSelectedIndex() && listView3.isDisable()) {
                listView3.setDisable(false);
            }
        }
        if(listView1.getSelectionModel().getSelectedIndex() == listView2.getSelectionModel().getSelectedIndex()){
            int winner = listView1.getSelectionModel().getSelectedIndex();
            if(winner == 0) end(teams.get(0));
            else end(teams.get(1));
        }

    }

    public void end(VolleyballTeam winner){
        Stage stage = (Stage) confirmButton.getScene().getWindow();

        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballTeams.ser"));
            output.writeObject((VolleyballTeam) winner);
            output.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("end.fxml"));
            Stage tournament = new Stage();
            tournament.setTitle("End!");
            tournament.setScene(new Scene(root, 400, 400));
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

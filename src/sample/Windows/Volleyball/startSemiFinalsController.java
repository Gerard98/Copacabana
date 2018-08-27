package sample.Windows.Volleyball;

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
import sample.Team.VolleyballTeam;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class startSemiFinalsController {

    @FXML
    private Button confirmButton1;
    @FXML
    private Button confirmButton2;
    @FXML
    private ListView listView1;
    @FXML
    private ListView listView2;
    @FXML
    private ListView listView3;
    @FXML
    private ListView listView4;
    @FXML
    private ListView listView5;
    @FXML
    private ListView listView6;
    @FXML
    private Label linesman1;
    @FXML
    private Label linesman2;
    @FXML
    private Label linesman3;
    @FXML
    private Label linesman4;
    @FXML
    private Label mainJudge1;
    @FXML
    private Label mainJudge2;

    private List<VolleyballTeam> teams;


    @FXML
    public void initialize() {
        teams = new LinkedList<>();
        List<Linesman> linesmen = new LinkedList<>();
        List<MainJudge> mainJudges = new LinkedList<>();
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

        randomJudges(linesmen, mainJudges);

        linesmen.clear();
        mainJudges.clear();

        for(int i=0;i< teams.size();i++)teams.get(i).setPoints(0);

        ObservableList<String> obsTeams = FXCollections.observableArrayList();
        obsTeams.addAll(teams.get(0).getTeamName());
        obsTeams.addAll(teams.get(3).getTeamName());
        listView1.setItems(obsTeams);
        listView2.setItems(obsTeams);
        listView3.setItems(obsTeams);
        ObservableList<String> obsTeam2 = FXCollections.observableArrayList();
        obsTeam2.addAll(teams.get(1).getTeamName());
        obsTeam2.addAll(teams.get(2).getTeamName());
        listView4.setItems(obsTeam2);
        listView5.setItems(obsTeam2);
        listView6.setItems(obsTeam2);

    }

    public void randomJudges(List<Linesman> linesmen, List<MainJudge> mainJudges){
        Random random = new Random();
        int i = random.nextInt(linesmen.size());

        linesman1.setText(linesmen.get(i).getName() + " " + linesmen.get(i).getSurname() );
        Linesman tmp = linesmen.get(i);
        linesmen.remove(i);
        i = random.nextInt(linesmen.size());
        linesman2.setText(linesmen.get(i).getName() + " " + linesmen.get(i).getSurname());
        linesmen.add(tmp);
        i = random.nextInt(linesmen.size());
        linesman3.setText(linesmen.get(i).getName() + " " + linesmen.get(i).getSurname() );
        linesmen.remove(i);
        i = random.nextInt(linesmen.size());
        linesman4.setText(linesmen.get(i).getName() + " " + linesmen.get(i).getSurname());
        i = random.nextInt(mainJudges.size());
        mainJudge1.setText(mainJudges.get(i).getName() + " " + mainJudges.get(i).getSurname());
        i = random.nextInt(mainJudges.size());
        mainJudge2.setText(mainJudges.get(i).getName() + " " + mainJudges.get(i).getSurname());

    }

    @FXML
    public void confirmAction1(){
        boolean end = false;
        if(listView1.getSelectionModel().getSelectedIndex() != listView2.getSelectionModel().getSelectedIndex() && listView3.isDisable() == false){
            int winner = listView3.getSelectionModel().getSelectedIndex();
            if(winner == 0){
                teams.get(0).setPoints(3);
            }
            else teams.get(3).setPoints(3);

            end = true;
            listView1.setDisable(true);
            listView2.setDisable(true);
            listView3.setDisable(true);
            confirmButton1.setDisable(true);
        }

        if(end == false) {
            if (listView1.getSelectionModel().getSelectedIndex() != listView2.getSelectionModel().getSelectedIndex() && listView3.isDisable() == true) {
                listView3.setDisable(false);
            }
        }

        if(listView1.getSelectionModel().getSelectedIndex() == listView2.getSelectionModel().getSelectedIndex()){
            int winner = listView1.getSelectionModel().getSelectedIndex();
            if(winner == 0){
                teams.get(0).setPoints(3);
            }
            else teams.get(3).setPoints(3);

            confirmButton1.setDisable(true);
            listView1.setDisable(true);
            listView2.setDisable(true);
        }
    }

    @FXML
    public void confirmAction2(){
        boolean end = false;
        if(listView4.getSelectionModel().getSelectedIndex() != listView5.getSelectionModel().getSelectedIndex() && listView6.isDisable() == false){
            int winner = listView6.getSelectionModel().getSelectedIndex();
            if(winner == 0){
                teams.get(1).setPoints(3);
            }
            else teams.get(2).setPoints(3);

            listView4.setDisable(true);
            listView5.setDisable(true);
            listView6.setDisable(true);
            confirmButton2.setDisable(true);
            end = true;
        }
        if(end == false) {
            if (listView4.getSelectionModel().getSelectedIndex() != listView5.getSelectionModel().getSelectedIndex() && listView6.isDisable() == true) {
                listView6.setDisable(false);
            }
        }

        if(listView4.getSelectionModel().getSelectedIndex() == listView5.getSelectionModel().getSelectedIndex()){
            int winner = listView4.getSelectionModel().getSelectedIndex();
            if(winner == 0){
                teams.get(1).setPoints(3);
            }
            else teams.get(2).setPoints(3);
            listView4.setDisable(true);
            listView5.setDisable(true);
            confirmButton2.setDisable(true);
        }
    }

    @FXML
    public void startFinalAction(){
        if(confirmButton1.isDisable() && confirmButton2.isDisable()){
            Stage stage =(Stage) confirmButton1.getScene().getWindow();
            try {
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballTeams.ser"));
                for(int i=0;i<4;i++){
                    if(teams.get(i).getPoints() == 3) output.writeObject((VolleyballTeam) teams.get(i));
                }
                output.close();
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }

            try {
                Parent root = FXMLLoader.load(getClass().getResource("final.fxml"));
                Stage tournament = new Stage();
                tournament.setTitle("Final!");
                tournament.setScene(new Scene(root, 400, 400));
                tournament.show();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

            stage.close();
        }
    }

}

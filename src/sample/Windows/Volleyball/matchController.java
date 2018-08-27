package sample.Windows.Volleyball;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Match.VolleyballMatch;
import sample.Person.Linesman;
import sample.Person.MainJudge;
import sample.Team.VolleyballTeam;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class matchController {

    private VolleyballMatch match;
    @FXML
    private Label label;
    @FXML
    private ListView listView1;
    @FXML
    private ListView listView2;
    @FXML
    private ListView listView3;
    @FXML
    private Button confirmButton;
    @FXML
    private Label mainJudge;
    @FXML
    private Label linesman1;
    @FXML
    private Label linesman2;


    @FXML
    public void initialize() throws IOException {


        ObjectInputStream input = new ObjectInputStream(new FileInputStream("VolleyballMatches.ser"));
        try {
            match =(VolleyballMatch) input.readObject();
            label.setText(match.toString());

            ObservableList<String> obs = FXCollections.observableArrayList();
            obs.addAll(match.getTeam1().getTeamName(), match.getTeam2().getTeamName());
            listView1.setItems(obs);
            listView2.setItems(obs);
            listView3.setItems(obs);

        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        input.close();
        LinkedList<Linesman> linesmen = new LinkedList<>();
        LinkedList<MainJudge> mainJudges = new LinkedList<>();
        ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("VolleyballMainJudges.ser"));
        ObjectInputStream input3 = new ObjectInputStream(new FileInputStream("VolleyballLinesmen.ser"));
        try{
            try {
                while (!(input2 == null)) mainJudges.add((MainJudge) input2.readObject());
            }
            catch(EOFException ex){
                System.out.println("End Of File!");
            }
            try {
                while (!(input3 == null)) linesmen.add((Linesman) input3.readObject());
            }
            catch(EOFException ex){
                System.out.println("End Of File!");
            }
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        input2.close();
        input3.close();

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
    }

    @FXML
    public void confirmAction() throws IOException{
        Stage stage =(Stage) confirmButton.getScene().getWindow();
        boolean end = true;
        if(listView1.getSelectionModel().getSelectedIndex() != listView2.getSelectionModel().getSelectedIndex() && listView3.isDisable() == false){
            int winner = listView3.getSelectionModel().getSelectedIndex();
            if(winner == 0){
                addPointsToWinner(match.getTeam1(), match.getTeam2());
            }
            else{
                addPointsToWinner(match.getTeam2(), match.getTeam1());
            }
            end = true;
        }

        if (listView1.getSelectionModel().getSelectedIndex() != listView2.getSelectionModel().getSelectedIndex() && listView3.isDisable() == true){
            listView3.setDisable(false);
            end = false;

        }
        if(listView1.getSelectionModel().getSelectedIndex() == listView2.getSelectionModel().getSelectedIndex()){
            int winner = listView1.getSelectionModel().getSelectedIndex();
            if (winner == 0) {
                addPointsToWinner(match.getTeam1());
            } else {
                addPointsToWinner(match.getTeam2());
            }
            end = true;
        }


        if(end == true) stage.close();

    }

    public void addPointsToWinner(VolleyballTeam winner) throws IOException{
        List<VolleyballTeam> teams = new LinkedList<>();
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("VolleyballTeams.ser"));
            try {
                while(!(input == null))teams.add((VolleyballTeam) input.readObject());

            } catch (EOFException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            input.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        for(int i=0;i<teams.size();i++){
            if(teams.get(i).equals(winner)) teams.get(i).addPoints(3);
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballTeams.ser"));
        while(!teams.isEmpty()){
            output.writeObject(((LinkedList<VolleyballTeam>) teams).getFirst());
            ((LinkedList<VolleyballTeam>) teams).removeFirst();
        }
        output.flush();
        output.close();
        teams.clear();
    }

    public void addPointsToWinner(VolleyballTeam winner, VolleyballTeam looser) throws IOException{
        List<VolleyballTeam> teams = new LinkedList<>();
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("VolleyballTeams.ser"));
            try {
                while(!(input == null))teams.add((VolleyballTeam) input.readObject());

            } catch (EOFException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            input.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        for(int i=0;i<teams.size();i++){
            if(teams.get(i).equals(winner)) teams.get(i).addPoints(2);
            if(teams.get(i).equals(looser)) teams.get(i).addPoints(1);
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballTeams.ser"));
        while(!teams.isEmpty()){
            output.writeObject(((LinkedList<VolleyballTeam>) teams).getFirst());
            ((LinkedList<VolleyballTeam>) teams).removeFirst();
        }
        output.flush();
        output.close();
        teams.clear();
    }

}

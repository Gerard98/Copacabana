package sample.Windows.TugOfWar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Match.TugOfWarMatch;
import sample.Match.VolleyballMatch;
import sample.Person.MainJudge;
import sample.Team.TugOfWarTeam;
import sample.Team.VolleyballTeam;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class matchController {

    private TugOfWarMatch match;
    @FXML
    private Label label;
    @FXML
    private ListView listView1;
    @FXML
    private Button confirmButton;
    @FXML
    private Label judge;

    private List<MainJudge> mainJudges;

    @FXML
    public void initialize() throws IOException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("TugOfWarMatches.ser"));
        ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("TugOfWarJudges.ser"));
        try {
            match = (TugOfWarMatch) input.readObject();
            label.setText(match.toString());

            ObservableList<String> obs = FXCollections.observableArrayList();
            obs.addAll(match.getTeam1().getTeamName(), match.getTeam2().getTeamName());
            listView1.setItems(obs);

            mainJudges = new LinkedList<>();
            try{
                while(!(input2 == null)) mainJudges.add((MainJudge) input2.readObject());
            }
            catch(EOFException ex){
                System.out.println("End Of File!");
            }
            Random random = new Random();
            int i = random.nextInt(mainJudges.size());
            judge.setText(mainJudges.get(i).getName() + " " + mainJudges.get(i).getSurname());
            mainJudges.clear();

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        input.close();

    }

    @FXML
    public void confirmAction() throws IOException {
        if (listView1.getSelectionModel().getSelectedIndex() == 0 || listView1.getSelectionModel().getSelectedIndex() == 1) {
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            int winner = listView1.getSelectionModel().getSelectedIndex();
            if (winner == 0) {
                addPointsToWinner(match.getTeam1());
            } else {
                addPointsToWinner(match.getTeam2());
            }

            stage.close();
        }
    }

    public void addPointsToWinner(TugOfWarTeam winner) throws IOException {
        List<TugOfWarTeam> teams = new LinkedList<>();
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("TugOfWarTeams.ser"));
            try {
                while (!(input == null)) teams.add((TugOfWarTeam) input.readObject());

            } catch (EOFException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).equals(winner)) teams.get(i).addPoints(3);
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("TugOfWarTeams.ser"));
        while (!teams.isEmpty()) {
            output.writeObject(((LinkedList<TugOfWarTeam>) teams).getFirst());
            ((LinkedList<TugOfWarTeam>) teams).removeFirst();
        }
        output.flush();
        output.close();
        teams.clear();
    }


}

package sample.Windows.Dodgeball;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Match.DodgeballMatch;
import sample.Match.TugOfWarMatch;
import sample.Person.MainJudge;
import sample.Team.DodgeballTeam;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class matchController {

    private DodgeballMatch match;

    @FXML
    private Label label;
    @FXML
    private ListView listView;
    @FXML
    private Button confirmButton;
    @FXML
    private Label judge;

    private List<MainJudge> mainJudges;

    @FXML
    public void initialize() throws IOException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("DodgeballMatches.ser"));
        ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("DodgeballMainJudges.ser"));
        try {
            match = (DodgeballMatch) input.readObject();
            label.setText(match.toString());

            ObservableList<String> obs = FXCollections.observableArrayList();
            obs.addAll(match.getTeam1().getTeamName(), match.getTeam2().getTeamName());
            listView.setItems(obs);

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
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        boolean end = true;
        int winner = listView.getSelectionModel().getSelectedIndex();
        if (winner == 0) {
            addPointsToWinner((DodgeballTeam) match.getTeam1());
            end = true;
        } else {
            addPointsToWinner((DodgeballTeam) match.getTeam2());
            end = true;
        }
        if (end == true) stage.close();
    }

    public void addPointsToWinner(DodgeballTeam winner) throws IOException {
        List<DodgeballTeam> teams = new LinkedList<>();
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("DodgeballTeams.ser"));
            try {
                while (!(input == null)) teams.add((DodgeballTeam) input.readObject());

            } catch (EOFException e) {

            } catch (ClassNotFoundException e) {

            }
            input.close();
        } catch (FileNotFoundException e) {
        }

        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamName().equals(winner.getTeamName()))
                teams.get(i).addPoints(3);
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballTeams.ser"));
        while (!teams.isEmpty()) {
            output.writeObject(((LinkedList<DodgeballTeam>) teams).getFirst());
            ((LinkedList<DodgeballTeam>) teams).removeFirst();
        }
        output.flush();
        output.close();
        teams.clear();
    }

}

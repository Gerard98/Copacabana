package sample.Windows.Volleyball;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Team.VolleyballTeam;

import java.io.*;
import java.util.*;


public class deleteTeamController {
    @FXML
    private Button confirmButton;
    @FXML
    private ListView listView;

    private List<VolleyballTeam> teams;

    @FXML
    public void initialize(){
        teams = new LinkedList<>();
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("VolleyballTeams.ser")));
            try {
                while (!(input == null)) {
                    teams.add((VolleyballTeam) input.readObject());
                }
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
            input.close();

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        ObservableList<String> obsTeams = FXCollections.observableArrayList();
        for(int i=0;i<teams.size();i++) obsTeams.addAll(teams.get(i).getTeamName());
        listView.setItems(obsTeams);
    }

    public void confrimAction() {
        teams.remove(listView.getSelectionModel().getSelectedIndex());
        Stage stage = (Stage) confirmButton.getScene().getWindow();

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballTeams.ser"));
            while (!teams.isEmpty()) {
                output.writeObject(((LinkedList<VolleyballTeam>) teams).getFirst());
                ((LinkedList<VolleyballTeam>) teams).removeFirst();
            }
            output.flush();
            output.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        stage.close();
    }
}


package sample.Windows.TugOfWar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Person.MainJudge;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class deleteMainJudgeController {
    @FXML
    private ListView listView;
    @FXML
    private Button confirmButton;
    private List<MainJudge> judges;

    @FXML
    public void initialize(){
        judges = new LinkedList<>();
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("TugOfWarJudges.ser")));
            try {
                while (!(input == null)) {
                    judges.add((MainJudge) input.readObject());
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
        for(int i=0;i<judges.size();i++) obsTeams.addAll(judges.get(i).getName() + " " + judges.get(i).getSurname());
        listView.setItems(obsTeams);
    }


    public void confirmAction(){
        judges.remove(listView.getSelectionModel().getSelectedIndex());
        Stage stage = (Stage) confirmButton.getScene().getWindow();

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("TugOfWarJudges.ser"));
            while (!judges.isEmpty()) {
                output.writeObject(((LinkedList<MainJudge>) judges).getFirst());
                ((LinkedList<MainJudge>) judges).removeFirst();
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

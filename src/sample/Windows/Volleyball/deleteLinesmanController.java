package sample.Windows.Volleyball;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Person.Linesman;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class deleteLinesmanController {

    @FXML
    private ListView listView;
    @FXML
    private Button confirmButton;
    private List<Linesman> linesmen;

    @FXML
    public void initialize(){
        linesmen = new LinkedList<>();
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("VolleyballLinesmen.ser")));
            try {
                while (!(input == null)) {
                    linesmen.add((Linesman) input.readObject());
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
        for(int i = 0; i< linesmen.size(); i++) obsTeams.addAll(linesmen.get(i).getName() + " " + linesmen.get(i).getSurname());
        listView.setItems(obsTeams);
    }


    public void confirmAction(){
        linesmen.remove(listView.getSelectionModel().getSelectedIndex());
        Stage stage = (Stage) confirmButton.getScene().getWindow();

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballLinesmen.ser"));
            while (!linesmen.isEmpty()) {
                output.writeObject(((LinkedList<Linesman>) linesmen).getFirst());
                ((LinkedList<Linesman>) linesmen).removeFirst();
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

package sample.Windows.Volleyball;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Person.Player;
import sample.Team.VolleyballTeam;
import javafx.scene.control.TextField;
import sample.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import javafx.scene.text.Text;

import java.io.*;

public class addTeamController {

    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private Button confirmButton;
    @FXML
    private Text error1;
    @FXML
    private Text error2;
    @FXML
    private Text error3;
    @FXML
    private Text error4;
    @FXML
    private Text error5;


    private List<Text> errorFields = new ArrayList<>();
    private List<TextField> inputFields = new ArrayList<>();


    public void confirmAction() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        Errors error = new Errors();
        List<Boolean> improperInputData = new ArrayList<>();
        errorFields.add(error1);
        errorFields.add(error2);
        errorFields.add(error3);
        errorFields.add(error4);

        for(Text errorFields: errorFields){
            errorFields.setVisible(false);
        }

        inputFields.add(textField1);
        inputFields.add(textField2);
        inputFields.add(textField3);
        inputFields.add(textField4);

        for(TextField inputField : inputFields) {
            improperInputData.add(error.onlyLetter(inputField.getText()));
        }


        if (improperInputData.stream().anyMatch(x -> !x)) {
            for (int i = 0; i < improperInputData.size(); i++) {
                if (!improperInputData.get(i)) {
                    errorFields.get(i).setVisible(true);
                }
            }
        }
        else {
            if(textField5.getText().length() > 3) {
                Player player1 = new Player(textField1.getText(), textField2.getText());
                Player player2 = new Player(textField3.getText(), textField4.getText());
                VolleyballTeam team = new VolleyballTeam(player1, player2, textField5.getText());
                try {
                    addToFile(team);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                stage.close();
            }
            else error5.setVisible(true);
        }



    }


    public void addToFile(VolleyballTeam team) throws IOException {
        List<VolleyballTeam> teams = new LinkedList<>();
        teams.add(team);
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("VolleyballTeams.ser"));
            try {
                while (!(input == null)) teams.add((VolleyballTeam) input.readObject());

            } catch (EOFException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballTeams.ser"));
        while (!teams.isEmpty()) {
            output.writeObject(((LinkedList<VolleyballTeam>) teams).getFirst());
            ((LinkedList<VolleyballTeam>) teams).removeFirst();
        }
        output.flush();
        output.close();
    }


}

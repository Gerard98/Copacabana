package sample.Windows.Dodgeball;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Errors;
import sample.Person.Player;
import sample.Team.DodgeballTeam;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    private TextField textField6;
    @FXML
    private TextField textField7;
    @FXML
    private TextField textField8;
    @FXML
    private TextField textField9;
    @FXML
    private TextField textField10;
    @FXML
    private TextField textField11;
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
    @FXML
    private Text error6;
    @FXML
    private Text error7;
    @FXML
    private Text error8;
    @FXML
    private Text error9;
    @FXML
    private Text error10;
    @FXML
    private Text error11;

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
        errorFields.add(error5);
        errorFields.add(error6);
        errorFields.add(error7);
        errorFields.add(error8);
        errorFields.add(error9);
        errorFields.add(error10);

        for (Text errorFields : errorFields) {
            errorFields.setVisible(false);
        }

        inputFields.add(textField1);
        inputFields.add(textField2);
        inputFields.add(textField3);
        inputFields.add(textField4);
        inputFields.add(textField5);
        inputFields.add(textField6);
        inputFields.add(textField7);
        inputFields.add(textField8);
        inputFields.add(textField9);
        inputFields.add(textField10);

        for (TextField inputField : inputFields) {
            improperInputData.add(error.onlyLetter(inputField.getText()));
        }

        if (improperInputData.stream().anyMatch(x -> !x)) {
            for (int i = 0; i < improperInputData.size(); i++) {
                if (!improperInputData.get(i)) {
                    errorFields.get(i).setVisible(true);
                }
            }
        } else {
            if (textField11.getText().length() > 3) {
                Player player1 = new Player(textField1.getText(), textField2.getText());
                Player player2 = new Player(textField3.getText(), textField4.getText());
                Player player3 = new Player(textField5.getText(), textField6.getText());
                Player player4 = new Player(textField7.getText(), textField8.getText());
                Player player5 = new Player(textField9.getText(), textField10.getText());
                DodgeballTeam team = new DodgeballTeam(player1, player2, player3, player4, player5, textField11.getText());
                try {
                    addToFile(team);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                stage.close();
            } else error11.setVisible(true);
        }
    }

    public void addToFile(DodgeballTeam team) throws IOException {
        List<DodgeballTeam> teams = new LinkedList<>();
        teams.add(team);
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("DodgeballTeams.ser"));
            try {
                while (!(input == null)) teams.add((DodgeballTeam) input.readObject());

            } catch (EOFException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballTeams.ser"));
        while (!teams.isEmpty()) {
            output.writeObject(((LinkedList<DodgeballTeam>) teams).getFirst());
            ((LinkedList<DodgeballTeam>) teams).removeFirst();
        }
        output.flush();
        output.close();
    }
}

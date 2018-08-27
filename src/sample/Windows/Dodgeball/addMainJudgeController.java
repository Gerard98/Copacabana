package sample.Windows.Dodgeball;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Errors;
import sample.Person.MainJudge;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class addMainJudgeController {
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private Button confirmButton;
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    @FXML
    public void confirmAction(){
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        boolean close = true;
        Errors error = new Errors();
        if(error.onlyLetter(textField1.getText())){
            if(error.onlyLetter(textField2.getText())){
                try{
                    addJudge(new MainJudge(textField1.getText(),textField2.getText()));
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
                close = true;
            }
            else{
                label2.setVisible(true);
                close = false;
            }
        }
        else{
            label1.setVisible(true);
            close = false;
        }
        if(close == true) stage.close();
    }

    public void addJudge(MainJudge mainJudge) throws IOException{
        List<MainJudge> mainJudges = new LinkedList<>();
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("DodgeballMainJudges.ser"));
            try {
                while(!(input == null))mainJudges.add((MainJudge) input.readObject());

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

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballMainJudges.ser"));
        output.writeObject((MainJudge) mainJudge);
        while(!mainJudges.isEmpty()){
            output.writeObject(((LinkedList<MainJudge>) mainJudges).getFirst());
            ((LinkedList<MainJudge>) mainJudges).removeFirst();
        }
        output.flush();
        output.close();
    }
}

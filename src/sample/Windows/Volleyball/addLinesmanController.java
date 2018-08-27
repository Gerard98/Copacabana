package sample.Windows.Volleyball;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Errors;
import sample.Person.Linesman;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class addLinesmanController {

    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Button confirmButton;

    public void confirmAction(){
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        boolean close = true;
        Errors error = new Errors();
        if(error.onlyLetter(textField1.getText())){
            if(error.onlyLetter(textField2.getText())){
                try{
                    addLinesman(new Linesman(textField1.getText(),textField2.getText()));
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

    public void addLinesman(Linesman linesman) throws IOException{
        List<Linesman> linesmans = new LinkedList<>();
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("VolleyballLinesmen.ser"));
            try {
                while(!(input == null))linesmans.add((Linesman) input.readObject());

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

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballLinesmen.ser"));
        output.writeObject((Linesman) linesman);
        while(!linesmans.isEmpty()){
            output.writeObject(((LinkedList<Linesman>) linesmans).getFirst());
            ((LinkedList<Linesman>) linesmans).removeFirst();
        }
        output.flush();
        output.close();
    }


}



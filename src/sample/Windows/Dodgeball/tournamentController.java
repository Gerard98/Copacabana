package sample.Windows.Dodgeball;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Match.DodgeballMatch;
import sample.Team.DodgeballTeam;

import java.io.*;
import java.util.*;

public class tournamentController {

    private List<DodgeballMatch> matches;
    private List<DodgeballTeam> teams;


    @FXML
    private TextArea textArea1;
    @FXML
    private Button generateButton1;
    @FXML
    private Button generateButton2;
    @FXML
    private Label label;
    @FXML
    private TextArea textArea2;
    @FXML
    private Button playTheMatchButton;
    @FXML
    private Button startFinalsButton;


    @FXML
    public void initialize() throws IOException {
        matches = new LinkedList<>();
        teams = new LinkedList<>();

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("DodgeballTeams.ser"));
            try {
                while(!(input == null))teams.add((DodgeballTeam) input.readObject());

            } catch (EOFException e) {
                System.out.println(e.getMessage());
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
            input.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballTeams.ser"));
        for(int i=0;i<teams.size();i++){
            teams.get(i).setPoints(0);
            output.writeObject(teams.get(i));
        }
        output.close();

        for(int i=0;i<teams.size();i++){
            int j=i+1;
            teams.get(i).setPoints(0);
            while(j<teams.size()){
                matches.add(new DodgeballMatch(teams.get(i), teams.get(j)));
                j++;
            }
        }

        label.setText("Next Match: " + matches.get(0).toString());
    }
    @FXML
    public void playMatchAction() throws IOException{
        generateButton1.setDisable(true);
        generateButton2.setDisable(true);

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballMatches.ser"));
        output.writeObject(matches.get(0));
        output.close();

        matches.remove(0);

        Parent root = FXMLLoader.load(getClass().getResource("match.fxml"));
        Stage match = new Stage();
        match.setTitle("Match");
        match.setScene(new Scene(root, 300, 300));

        Platform.setImplicitExit(false);
        match.setOnCloseRequest( e -> e.consume());

        match.show();


        if(!matches.isEmpty()) {
            label.setText("Next Match: " + matches.get(0).toString());
        }
        else{
            playTheMatchButton.setDisable(true);
            startFinalsButton.setDisable(false);
            startFinalsButton.setVisible(true);
        }

    }
    @FXML
    public void showMatchesAction(){
        textArea1.setText("");
        for(int i=0;i<matches.size();i++) textArea1.appendText( i+1 + ") " + matches.get(i).getTeam1().getTeamName() + " vs " + matches.get(i).getTeam2().getTeamName() +"\n");
    }
    @FXML
    public void showScoreboardAction() {
        textArea2.setText("");
        teams.clear();
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("DodgeballTeams.ser"));
            try {
                while(!(input == null))teams.add((DodgeballTeam) input.readObject());

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
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        Collections.sort(teams, new Comparator<DodgeballTeam>(){
            @Override
            public int compare(DodgeballTeam t1, DodgeballTeam t2){
                return t2.getPoints() - t1.getPoints();
            }
        });

        for(int i=0;i<teams.size();i++){
            textArea2.appendText(teams.get(i).getTeamName() + ": " + teams.get(i).getPoints() + " Points" + "\n");
        }

    }
    @FXML
    public void startFinalsAction(){
        playTheMatchButton.setDisable(false);
        Collections.sort(teams, new Comparator<DodgeballTeam>(){
            @Override
            public int compare(DodgeballTeam t1, DodgeballTeam t2){
                return t2.getPoints() - t1.getPoints();
            }
        });
        for(int i=4;i<teams.size();i++){
            teams.remove(i);
        }
        matches.add(new DodgeballMatch(teams.get(0), teams.get(3)));
        matches.add(new DodgeballMatch(teams.get(1),teams.get(2)));

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballTeams.ser"));
            for(int i=0;i<teams.size();i++) output.writeObject(teams.get(i));
            output.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("startsemifinals.fxml"));
            Stage tournament = new Stage();
            tournament.setTitle("Finals!");
            tournament.setScene(new Scene(root, 700, 400));
            tournament.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        Stage stage = (Stage) playTheMatchButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void generateSemiFinalsAction(){
        Stage stage = (Stage) generateButton1.getScene().getWindow();
        Random random = new Random();
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballTeams.ser"));
            for(int i=0;i<4;i++) {
                int winner = random.nextInt(teams.size());
                output.writeObject(teams.get(winner));
                teams.remove(winner);
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("startsemifinals.fxml"));
            Stage tournament = new Stage();
            tournament.setTitle("Finals!");
            tournament.setScene(new Scene(root, 700, 400));
            tournament.show();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        stage.close();
    }
    @FXML
    public void generateFinalAction(){
        Stage stage = (Stage) generateButton1.getScene().getWindow();
        Random random = new Random();
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballTeams.ser"));
            for(int i=0;i<2;i++) {
                int winner = random.nextInt(teams.size());
                output.writeObject(teams.get(winner));
                teams.remove(winner);
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("final.fxml"));
            Stage tournament = new Stage();
            tournament.setTitle("Finals!");
            tournament.setScene(new Scene(root, 300, 300));
            tournament.show();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }


        stage.close();
    }

}


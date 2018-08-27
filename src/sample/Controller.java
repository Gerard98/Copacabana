package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Person.Linesman;
import sample.Person.MainJudge;
import sample.Team.TugOfWarTeam;
import sample.Team.VolleyballTeam;
import sample.Team.DodgeballTeam;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Controller{
    private List<VolleyballTeam> teamsVolleyball;
    private List<MainJudge> judgesVolleyball;
    private List<Linesman> linesmenVolleyball;
    private List<DodgeballTeam> teamsDodgeball;
    private List<MainJudge> judgesDodgeball;
    private List<TugOfWarTeam> teamsTugOfWar;
    private List<MainJudge> judgesTugOfWar;

    @FXML
    private TextArea textArea1;
    @FXML
    private TextArea textArea2;
    @FXML
    private TextArea textAreaTugOfWar1;
    @FXML
    private TextArea textAreaTugOfWar2;
    @FXML
    private TextArea textAreaVolleyball1;
    @FXML
    private TextArea textAreaVolleyball2;
    @FXML
    private TextArea textAreaVolleyball3;
    @FXML
    private Tab volleyballTab;
    @FXML
    private Tab dodgeballTab;
    @FXML
    private Tab tugOfWarTab;
    @FXML
    private Label error2;
    @FXML
    private Label errorTugOfWar1;
    @FXML
    private Label errorTugOfWar2;
    @FXML
    private Label errorVolleyball1;
    @FXML
    private Label errorVolleyball2;
    @FXML
    private Label errorVolleyball3;

    @FXML
    public void initialize(){
        teamsVolleyball = new LinkedList<>();
        judgesVolleyball = new LinkedList<>();
        linesmenVolleyball = new LinkedList<>();
        teamsDodgeball = new LinkedList<>();
        judgesDodgeball = new LinkedList<>();
        teamsTugOfWar = new LinkedList<>();
        judgesTugOfWar = new LinkedList<>();
    }


    // Voleyball !!!


    @FXML
    public void addteamActionVoleyball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Volleyball//addteam.fxml"));
        Stage addTeamStage = new Stage();
        addTeamStage.setTitle("Add Team");
        addTeamStage.setScene(new Scene(root, 300, 425));
        addTeamStage.show();
    }
    @FXML
    public void deleteteamActionVolleyball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Volleyball//deleteteam.fxml"));
        Stage deleteTeamStage = new Stage();
        deleteTeamStage.setTitle("Delete Team");
        deleteTeamStage.setScene(new Scene(root, 400, 400));
        deleteTeamStage.show();
    }
    @FXML
    public void addMainJudgeActionVolleyball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Volleyball//addmainjudge.fxml"));
        Stage addMainJudgeStage = new Stage();
        addMainJudgeStage.setTitle("Add Main Judge");
        addMainJudgeStage.setScene(new Scene(root, 300, 250));
        addMainJudgeStage.show();
    }
    @FXML
    public void deleteMainJudgeActionVolleyball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Volleyball//deletemainjudge.fxml"));
        Stage deleteMainJudge = new Stage();
        deleteMainJudge.setTitle("Delete Main Judge");
        deleteMainJudge.setScene(new Scene(root, 275, 400));
        deleteMainJudge.show();

    }
    @FXML
    public void addLinesmanActionVolleyball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Volleyball//addlinesman.fxml"));
        Stage addLinesman = new Stage();
        addLinesman.setTitle("Add Linesman");
        addLinesman.setScene(new Scene(root, 300, 250));
        addLinesman.show();
    }
    @FXML
    public void deleteLinesmanActionVolleyball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Volleyball//deletelinesman.fxml"));
        Stage deleteLinesman = new Stage();
        deleteLinesman.setTitle("Delete Linesman");
        deleteLinesman.setScene(new Scene(root, 275, 400));
        deleteLinesman.show();
    }
    @FXML
    public void startTournamentActionVolleyball() throws Exception{
        teamsVolleyball.clear();
        judgesVolleyball.clear();
        linesmenVolleyball.clear();
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("VolleyballTeams.ser")));
            ObjectInputStream input2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream("VolleyballLinesmen.ser")));
            ObjectInputStream input3 = new ObjectInputStream(new BufferedInputStream(new FileInputStream("VolleyballMainJudges.ser")));
            try{
                try {
                    while (!(input == null)) {
                        teamsVolleyball.add((VolleyballTeam) input.readObject());
                    }
                }
                catch(EOFException ex){
                    System.out.println("End of File!");
                }
                try {
                    while (!(input2 == null)) {
                        linesmenVolleyball.add((Linesman) input2.readObject());
                    }
                }
                catch(EOFException ex){
                    System.out.println("End of File!");
                }
                try {
                    while (!(input3 == null)) {
                        judgesVolleyball.add((MainJudge) input3.readObject());
                    }
                }
                catch(EOFException ex){
                    System.out.println("End Of File!");
                }
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
            input.close();
            input2.close();
            input3.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        ArrayList<Boolean> error = new ArrayList<>();
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(errorVolleyball1);
        labels.add(errorVolleyball2);
        labels.add(errorVolleyball3);

        for(Label label : labels){
            label.setVisible(false);
        }

        error.add(teamsVolleyball.size() < 4);
        error.add(linesmenVolleyball.size() < 2);
        error.add(judgesVolleyball.size() < 1);

        if(!error.get(0) && !error.get(1) && !error.get(2)) {
            Parent root = FXMLLoader.load(getClass().getResource("Windows//Volleyball//tournament.fxml"));
            Stage tournament = new Stage();
            tournament.setTitle("Tournament");
            tournament.setScene(new Scene(root, 500, 500));
            tournament.show();
            volleyballTab.setDisable(true);
        }
        else{
            for(int i=0;i<3;i++){
                if(error.get(i)) labels.get(i).setVisible(true);
            }
        }
        teamsVolleyball.clear();
        judgesVolleyball.clear();
        linesmenVolleyball.clear();
    }


    @FXML
    public void showTeamActionVolleyball(){
        textAreaVolleyball1.setText("Teams: " + "\n");
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("VolleyballTeams.ser")));
            try{
                while(input != null) {
                    teamsVolleyball.add((VolleyballTeam) input.readObject());
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
        for(int i = 0; i< teamsVolleyball.size(); i++){
            textAreaVolleyball1.appendText("Team name: " + teamsVolleyball.get(i).getTeamName());
            textAreaVolleyball1.appendText(" Player 1: " + teamsVolleyball.get(i).getName(0) + " " + teamsVolleyball.get(i).getSurname(0));
            textAreaVolleyball1.appendText(" Player 2: " + teamsVolleyball.get(i).getName(1) + " " + teamsVolleyball.get(i).getSurname(1));
            textAreaVolleyball1.appendText("\n");

        }
        teamsVolleyball.clear();

    }

    @FXML
    public void showMainJudgesActionVolleyball(){
        textAreaVolleyball2.setText("Main Judges: ");
        textAreaVolleyball2.appendText("\n");
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("VolleyballMainJudges.ser")));
            try{
                while(!(input == null)) {
                    judgesVolleyball.add((MainJudge) input.readObject());
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
        for(int i = 0; i< judgesVolleyball.size(); i++){
            textAreaVolleyball2.appendText(judgesVolleyball.get(i).getName() + " " + judgesVolleyball.get(i).getSurname());
            textAreaVolleyball2.appendText("\n");

        }
        judgesVolleyball.clear();
    }

    @FXML
    public void showLinesmenActionVolleyball(){
        textAreaVolleyball3.setText("Linesmen: ");
        textAreaVolleyball3.appendText("\n");
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("VolleyballLinesmen.ser")));
            try{
                while(!(input == null)) {
                    linesmenVolleyball.add((Linesman) input.readObject());
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
        for(int i = 0; i< linesmenVolleyball.size(); i++){
            textAreaVolleyball3.appendText(linesmenVolleyball.get(i).getName() + " " + linesmenVolleyball.get(i).getSurname());
            textAreaVolleyball3.appendText("\n");

        }
        linesmenVolleyball.clear();
    }


    // Dodgeball !!!


    @FXML
    public void addteamActionDodgeball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Dodgeball//addteam.fxml"));
        Stage addTeamStage = new Stage();
        addTeamStage.setTitle("Add Team");
        addTeamStage.setScene(new Scene(root, 575, 425));
        addTeamStage.show();
    }
    @FXML
    public void deleteteamActionDodgeball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Dodgeball//deleteteam.fxml"));
        Stage deleteTeamStage = new Stage();
        deleteTeamStage.setTitle("Delete Team");
        deleteTeamStage.setScene(new Scene(root, 400, 400));
        deleteTeamStage.show();
    }
    @FXML
    public void addMainJudgeActionDodgeball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Dodgeball//addmainjudge.fxml"));
        Stage addMainJudgeStage = new Stage();
        addMainJudgeStage.setTitle("Add Main Judge");
        addMainJudgeStage.setScene(new Scene(root, 300, 250));
        addMainJudgeStage.show();
    }
    @FXML
    public void deleteMainJudgeActionDodgeball() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//Dodgeball//deletemainjudge.fxml"));
        Stage deleteMainJudge = new Stage();
        deleteMainJudge.setTitle("Delete Main Judge");
        deleteMainJudge.setScene(new Scene(root, 275, 400));
        deleteMainJudge.show();
    }
    @FXML
    public void startTournamentActionDodgeball() throws Exception{
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("DodgeballTeams.ser")));
            try{
                while(!(input == null)) {
                    teamsDodgeball.add((DodgeballTeam) input.readObject());
                }
            }
            catch(ClassNotFoundException e){

            }
            input.close();
        }
        catch(IOException e){

        }

        if(teamsDodgeball.size() > 3) {
            Parent root = FXMLLoader.load(getClass().getResource("Windows//Dodgeball//tournament.fxml"));
            Stage tournament = new Stage();
            tournament.setTitle("Tournament");
            tournament.setScene(new Scene(root, 500, 500));
            tournament.show();
            dodgeballTab.setDisable(true);
        }
        else{
            error2.setVisible(true);
        }
        teamsDodgeball.clear();
    }


    @FXML
    public void showTeamActionDodgeball(){
        textArea1.setText("Teams: " + "\n");
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("DodgeballTeams.ser")));
            try{
                while(input != null) {
                    teamsDodgeball.add((DodgeballTeam) input.readObject());
                }
            }
            catch(ClassNotFoundException e){

            }
            input.close();
        }
        catch(IOException e){

        }
        for(int i = 0; i< teamsDodgeball.size(); i++){
            textArea1.appendText("Team name: " + teamsDodgeball.get(i).getTeamName());
            textArea1.appendText(" Player 1: " + teamsDodgeball.get(i).getName(0) + " " + teamsDodgeball.get(i).getSurname(0));
            textArea1.appendText(" Player 2: " + teamsDodgeball.get(i).getName(1) + " " + teamsDodgeball.get(i).getSurname(1));
            textArea1.appendText(" Player 3: " + teamsDodgeball.get(i).getName(2) + " " + teamsDodgeball.get(i).getSurname(2));
            textArea1.appendText(" Player 4: " + teamsDodgeball.get(i).getName(3) + " " + teamsDodgeball.get(i).getSurname(3));
            textArea1.appendText(" Player 5: " + teamsDodgeball.get(i).getName(4) + " " + teamsDodgeball.get(i).getSurname(4));
            textArea1.appendText("\n");

        }
        teamsDodgeball.clear();
    }
    @FXML
    public void showMainJudgesActionDodgeball() {
        textArea2.setText("Main Judges: ");
        textArea2.appendText("\n");
        try {
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("DodgeballMainJudges.ser")));
            try {
                while (!(input == null)) {
                    judgesDodgeball.add((MainJudge) input.readObject());
                }
            } catch (ClassNotFoundException e) {

            }
            input.close();
        } catch (IOException e) {

        }
        for (int i = 0; i < judgesDodgeball.size(); i++) {
            textArea2.appendText(judgesDodgeball.get(i).getName() + " " + judgesDodgeball.get(i).getSurname());
            textArea2.appendText("\n");

        }
        judgesDodgeball.clear();
    }




    // TUG OF WAR



    @FXML
    public void addTeamActionTugOfWar() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//TugOfWar//addteam.fxml"));
        Stage addTeamStage = new Stage();
        addTeamStage.setTitle("Add Team");
        addTeamStage.setScene(new Scene(root, 575, 425));
        addTeamStage.show();
    }
    @FXML
    public void deleteTeamActionTugOfWar() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//TugOfWar//deleteteam.fxml"));
        Stage deleteTeamStage = new Stage();
        deleteTeamStage.setTitle("Delete Team");
        deleteTeamStage.setScene(new Scene(root, 400, 400));
        deleteTeamStage.show();
    }
    @FXML
    public void addJudgeActionTugOfWar() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//TugOfWar//addmainjudge.fxml"));
        Stage addMainJudgeStage = new Stage();
        addMainJudgeStage.setTitle("Add Judge");
        addMainJudgeStage.setScene(new Scene(root, 300, 250));
        addMainJudgeStage.show();
    }
    @FXML
    public void deleteJudgeActionTugOfWar() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Windows//TugOfWar//deletemainjudge.fxml"));
        Stage deleteMainJudge = new Stage();
        deleteMainJudge.setTitle("Delete Judge");
        deleteMainJudge.setScene(new Scene(root, 275, 400));
        deleteMainJudge.show();

    }

    @FXML
    public void showTeamActionTugOfWar(){
        textAreaTugOfWar1.setText("Teams: " + "\n");
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("TugOfWarTeams.ser")));
            try{
                while(input != null) {
                    teamsTugOfWar.add((TugOfWarTeam) input.readObject());
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
        for(int i = 0; i< teamsTugOfWar.size(); i++){
            textAreaTugOfWar1.appendText("Team name: " + teamsTugOfWar.get(i).getTeamName());
            textAreaTugOfWar1.appendText(" Player 1: " + teamsTugOfWar.get(i).getName(0) + " " + teamsTugOfWar.get(i).getSurname(0));
            textAreaTugOfWar1.appendText(" Player 2: " + teamsTugOfWar.get(i).getName(1) + " " + teamsTugOfWar.get(i).getSurname(1));
            textAreaTugOfWar1.appendText(" Player 3: " + teamsTugOfWar.get(i).getName(2) + " " + teamsTugOfWar.get(i).getSurname(2));
            textAreaTugOfWar1.appendText(" Player 4: " + teamsTugOfWar.get(i).getName(3) + " " + teamsTugOfWar.get(i).getSurname(3));
            textAreaTugOfWar1.appendText(" Player 5: " + teamsTugOfWar.get(i).getName(4) + " " + teamsTugOfWar.get(i).getSurname(4));
            textAreaTugOfWar1.appendText("\n");

        }
        teamsTugOfWar.clear();
    }

    @FXML
    public void showJudgesActionTugOfWar() {
        textAreaTugOfWar2.setText("Judges: ");
        textAreaTugOfWar2.appendText("\n");
        try {
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("TugOfWarJudges.ser")));
            try {
                while (!(input == null)) {
                    judgesTugOfWar.add((MainJudge) input.readObject());
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            input.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < judgesTugOfWar.size(); i++) {
            textAreaTugOfWar2.appendText(judgesTugOfWar.get(i).getName() + " " + judgesTugOfWar.get(i).getSurname());
            textAreaTugOfWar2.appendText("\n");

        }
        judgesTugOfWar.clear();
    }

    @FXML
    public void startTournamentActionTugOfWar() throws Exception{
        try{
            ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("TugOfWarTeams.ser")));
            ObjectInputStream input2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream("TugOfWarJudges.ser")));
            try{
                try {
                    while (!(input == null)) {
                        teamsTugOfWar.add((TugOfWarTeam) input.readObject());
                    }
                }
                catch(EOFException ex){
                    System.out.println("End Of File!");
                }
                try {
                    while (!(input2 == null)) {
                        judgesTugOfWar.add((MainJudge) input2.readObject());
                    }
                }
                catch(EOFException ex){
                    System.out.println("End Of File!");
                }
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
            input.close();
            input2.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        errorTugOfWar1.setVisible(false);
        errorTugOfWar2.setVisible(false);

        if(teamsTugOfWar.size() > 3) {
            if(judgesTugOfWar.size() > 0) {
                Parent root = FXMLLoader.load(getClass().getResource("Windows//TugOfWar//tournament.fxml"));
                Stage tournament = new Stage();
                tournament.setTitle("Tournament");
                tournament.setScene(new Scene(root, 500, 500));
                tournament.show();
                tugOfWarTab.setDisable(true);
            }
            else{
                errorTugOfWar2.setVisible(true);
            }
        }
        else{
            errorTugOfWar1.setVisible(true);
        }

        teamsTugOfWar.clear();
        judgesTugOfWar.clear();

    }



    // RESETS !!!

    @FXML
    public void resetVolleyball(){

        volleyballTab.setDisable(false);

        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("VolleyballTeams.ser"));
            output.reset();
            output.close();
            ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("VolleyballLinesmen.ser"));
            output2.reset();
            output2.close();
            ObjectOutputStream output3 = new ObjectOutputStream(new FileOutputStream("VolleyballMainJudges.ser"));
            output2.reset();
            output2.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void resetDodgeball(){
        dodgeballTab.setDisable(false);

        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("DodgeballTeams.ser"));
            output.reset();
            output.close();
            ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("DodgeballMainJudges.ser"));
            output2.reset();
            output2.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void resetTugofwar(){
        tugOfWarTab.setDisable(false);

        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("TugOfWarTeams.ser"));
            output.reset();
            output.close();
            ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("TugOfWarJudges.ser"));
            output2.reset();
            output2.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }



}

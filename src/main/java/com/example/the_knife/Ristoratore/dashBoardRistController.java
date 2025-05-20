package com.example.the_knife.Ristoratore;

import com.example.the_knife.Utente.SessionManager;
import com.example.the_knife.loginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class dashBoardRistController extends loginController {

    @FXML
    private Label welcomeLabel;

    SessionManager session = SessionManager.getInstance();
    private String user = session.getUsername();
    private int id = session.getUserId();
    private String ruolo = session.getRuolo();

    public void handleLogOut(ActionEvent event) {
        SessionManager.getInstance().logout();//cancello i dati dalla sessione
        try {
            super.goTo(event, "loginPage.fxml");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void closeProgram(ActionEvent event) {
        super.closeProgram(event);
    }

    @FXML
    public void initialize() {
        welcomeLabel.setText("Benvenuto, " + user + "");
        System.out.println("Utente: "+user+ "Id: "+id+"Ruolo: "+ruolo);
    }

    @FXML
    private void onProfileClick(ActionEvent event) throws IOException {
        goTo(event,"profilePageRist.fxml");
    }
    @FXML
    private void onRecensioniClick(ActionEvent event) throws IOException {
        goTo(event,"recensioniRist.fxml");
    }
    @FXML
    private void onRistorantiClick(ActionEvent event) throws IOException {
        goTo(event,"ristorantiRist.fxml");
    }
}

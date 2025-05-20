package com.example.the_knife.Cliente;

import com.example.the_knife.Utente.SessionManager;
import com.example.the_knife.loginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class dashBoardClientController extends loginController {



    @FXML
    private Label welcomeLabel;

    SessionManager session = SessionManager.getInstance();
    private String user = session.getUsername();
    private int id = session.getUserId();
    private String ruolo = session.getRuolo();

    public void handleLogOut(ActionEvent event) throws IOException {
        SessionManager.getInstance().logout();//cancello i dati dalla sessione
        System.out.println("Logout effettuato");
            super.goTo(event, "loginPage.fxml");
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
    private void goToProfile(ActionEvent event) throws IOException {
        System.out.println("Stai andando al profilo");
        goTo(event, "profilePageClient.fxml");
    }
}

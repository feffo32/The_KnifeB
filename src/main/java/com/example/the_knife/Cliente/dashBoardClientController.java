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

    public void handleLogOut(ActionEvent event) {
        SessionManager.getInstance().logout();//cancello i dati dalla sessione
        try {
            super.goTo(event, "/com/example/the_knife/loginPage.fxml");
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
    protected void onProfileClick(ActionEvent event) throws IOException {
        goTo(event,"profilePageClient.fxml");
    }
    @FXML
    protected void onRecensioniClick(ActionEvent event) throws IOException {
        goTo(event,"recensioniClient.fxml");
    }
    @FXML
    protected void onRistorantiClick(ActionEvent event) throws IOException {
        goTo(event,"ristorantiClient.fxml");
    }
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        super.goTo(event, "dashBoardClient.fxml");
    }
}

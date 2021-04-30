package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label labelSaida;

    @FXML
    private TextField txtEntrada;

    @FXML
    private void acaoDoBotao(ActionEvent event){
        System.out.println("a");
        labelSaida.setText(Tradutor.tradutor(txtEntrada.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }
}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Mini Teste 1");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

// ∀, ∃, ¬, ∧, ∨, ->, <->

/*

Nenhum homem é extraterrestre

Há uma rena com um focinho vermelho

Scrooge não ama nada que é estranho

Todo mundo que ama Papai Noel gosta de renas

Qualquer um que monta qualquer moto Harley é um cara durão

Todo motociclista pilota uma Harley ou uma BMW

Qualquer pessoa que monta um BMW é um yuppie

Todo yuppie é advogado

Qualquer garota legal não namora alguém que é durao

Mary é uma garota legal e John é um motociclista

John não é um advogado, entao Mary não namora John

Qualquer coisa com um focinho vermelho é um palhaço ou estranho

 */
package com.example.javabd;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class HelloApplication extends Application{

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    private TextArea textArea;

    Line line1;
    Line line2;
    Text text;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Projet IFT2935");
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 680, 410);

        text = new Text("Projet du cours");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTranslateY(-170);

        line1 = new Line();
        line1.setStartX(-300.0);
        line1.setStartY(150.0);
        line1.setEndX(500.0);
        line1.setEndY(150.0);
        line1.setTranslateY(-130);

        line2 = new Line();
        line2.setStartY(100.0);
        line2.setStartY(300.0);
        line2.setEndY(500.0);
        line2.setEndY(-150.0);
        line2.setTranslateX(-230);
        line2.setTranslateY(95);



        button1 = new Button();
        button1.setText("Question 1");
        button1.setTranslateX(-290);
        button1.setTranslateY(-50);
        button1.setPrefHeight(40);
        button1.setPrefWidth(90);

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
                textArea.appendText("Question 1: L’équipe de quel pays a gagné la première édition de la coupe du " +
                        "monde et avec combien de buts au total? \n\n");
                textArea.appendText(Arrays.deepToString(JavaPostgreSql.readFromDatabase(1).toArray()));
            }
        });

        button2 = new Button();
        button2.setText("Question 2");
        button2.setTranslateX(-290);
        button2.setTranslateY(20);
        button2.setPrefHeight(40);
        button2.setPrefWidth(90);

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
                textArea.appendText("Question 2: Trouvez le nom du gardien avec le plus de buts dans tous les matchs\n\n");
                textArea.appendText(Arrays.deepToString(JavaPostgreSql.readFromDatabase(2).toArray()));
            }
        });

        button3 = new Button();
        button3.setText("Question 3");
        button3.setTranslateX(-290);
        button3.setTranslateY(90);
        button3.setPrefHeight(40);
        button3.setPrefWidth(90);

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
                textArea.appendText("Question 3: Trouvez tous les joueurs avec leur prenom, leur nom ainsi que le " +
                        "nombre de passes pour chacun d’eux\n\n");
                textArea.appendText(Arrays.deepToString(JavaPostgreSql.readFromDatabase(3).toArray()));
            }
        });

        button4 = new Button();
        button4.setText("Question 4");
        button4.setTranslateX(-290);
        button4.setTranslateY(160);
        button4.setPrefHeight(40);
        button4.setPrefWidth(90);

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
                textArea.appendText("Question 4: Comment se nomme l'arbitre semblant avoir une dent contre David Garcia " +
                        "et lui a donné le plus grand nombre de cartons de pénalité (à travers toutes les éditions de la coupe du monde)?\n\n");
                textArea.appendText(Arrays.deepToString(JavaPostgreSql.readFromDatabase(4).toArray()));

            }
        });


        TextInputDialog td = new TextInputDialog();
        td.setTitle("DataBase Name");
        td.setHeaderText("DataBase Name");
        td.setContentText("DataBase name");


        Optional<String> database = td.showAndWait();
        String data = "";
        if (database.isPresent()){
            data = database.get();
        }

        TextInputDialog td2 = new TextInputDialog();
        td2.setTitle("user name");
        td2.setHeaderText("user name");
        td2.setContentText("user name");

        Optional<String> username = td2.showAndWait();
        String user = "";
        if (username.isPresent()){
            user = username.get();
        }

        TextInputDialog td3 = new TextInputDialog();
        td3.setTitle("password");
        td3.setHeaderText("password");
        td3.setContentText("password");

        Optional<String> password = td3.showAndWait();
        String pass = "";
        if (password.isPresent()){
            pass = password.get();
        }

        JavaPostgreSql.connection(data,user,pass);


        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setMaxHeight(320);
        textArea.setMaxWidth(550);
        textArea.setTranslateX(60);
        textArea.setTranslateY(35);




        layout.getChildren().add(textArea);
        layout.getChildren().add(text);
        layout.getChildren().add(line1);
        layout.getChildren().add(line2);
        layout.getChildren().add(button1);
        layout.getChildren().add(button2);
        layout.getChildren().add(button3);
        layout.getChildren().add(button4);

        stage.setScene(scene);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
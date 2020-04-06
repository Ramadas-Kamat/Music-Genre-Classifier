package sample;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.image.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static javafx.scene.paint.Color.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Group root = new Group();

        primaryStage.setTitle("Music Classifier");

        Scene s= new Scene(root, 850, 800,YELLOW);
        primaryStage.setScene(s);

        final Text text1 = new Text(275, 150, "WELCOME");
        text1.setFill(GREEN);
        text1.setFont(Font.font(java.awt.Font.MONOSPACED, 70));

        TextField textField = new TextField();
        textField.setLayoutX(175);
        textField.setLayoutY(550);
        textField.setPromptText("Enter file name");
        textField.setAlignment(Pos.TOP_LEFT);
        textField.setPrefSize(500,20);

        Label response= new Label();
        response.setText("Upload the song to know its genre");
        response.setStyle("-fx-background-color: pink");
        response.setLayoutX(180);
        response.setLayoutY(450);
        response.setPrefWidth(485);
        response.setFont(new Font(28));

        Button upload = new Button("Upload");
        upload.setLayoutX(350);
        upload.setLayoutY(600);
        upload.setStyle("-fx-background-color: Red");
        upload.setPrefSize(150,50);

        upload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                Label response1=new Label();
                response1.setStyle("-fx-background-color: pink");
                response1.setLayoutX(330);
                response1.setLayoutY(450);
                response1.setPrefWidth(490);
                response1.setFont(new Font(30));


                Text text= new Text(295,400,"");
                text.setFont(Font.font(java.awt.Font.SERIF, 40));
                text.setFill(RED);

                Text text1 = new Text();
                text1.setText(textField.getText());
                text1.setFill(BLACK);
                text1.setFont(Font.font(java.awt.Font.MONOSPACED, 30));
                text1.setLayoutX(205);
                text1.setLayoutY(400);
                try
                {
                    if(!(textField.getText().equals(""))) {
                        String sourcePath = "/home/ramadas/Python/Source/lcn/" + textField.getText();
                        String targetPath = "/home/ramadas/Python/Resource/TestFiles/genre.au";
                        try {

                            Files.move(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
                            //response1.setText("File upload successful");
                            String s;
                            Process p;
                            try {
                                p = Runtime.getRuntime().exec("./script.sh");
                                BufferedReader br = new BufferedReader(
                                        new InputStreamReader(p.getInputStream()));
                                while ((s = br.readLine()) != null) {
                                    System.out.println(s);
                                    //response1.setText(s);
                                    text.setText(s);
                                }
                                response1.setText(" Thank You ");
                                response1.setLayoutX(230);
                                response1.setLayoutY(550);
                                response1.setPrefWidth(200);
                                text1.setText("");
                                p.waitFor();

                                System.out.println("exit: " + p.exitValue());
                                p.destroy();

                            } catch (Exception e) {
                                System.out.println(e);
                            }

                        } catch (Exception e) {

                            System.out.println(e);
                            response1.setText("File not found");
                            response1.setPrefWidth(210);
                            text1.setText("");
                        }
                    }
                    else
                        throw new Exception();
                }
                catch (Exception e)
                {
                    response1.setText("Fatal error");
                    response1.setPrefWidth(180);
                    text1.setText("File name cannot be empty");

                }
                finally {

                    Group root2 = new Group();
                    Scene s2=new Scene(root2,850,800,WHEAT);
                    primaryStage.setScene(s2);
                    root2.getChildren().addAll(text,response1,text1);
                    primaryStage.show();
                }
            }
        });

        Image music = new Image("https://pre00.deviantart.net/3d6b/th/pre/i/2013/291/0/9/yellow_music_by_ja12coo-d6qx28r.jpg");
        ImageView mus = new ImageView(music);
        mus.setY(190);
        mus.setX(220);
        mus.setFitWidth(350);
        mus.setFitHeight(200);
        mus.setSmooth(true);

        root.getChildren().addAll(upload,textField,response,text1,mus);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

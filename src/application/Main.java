package application;

import java.io.File;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Main extends Application { 
    Player player;
    FileChooser fileChooser;

    public void start(final Stage primaryStage) {
        // Setting up the stages
        MenuItem open = new MenuItem("Open");
        Menu file = new Menu("File");
        MenuBar menu = new MenuBar();

        // Connecting the above three
        file.getItems().add(open);
        menu.getMenus().add(file);

        // Adding functionality to switch to different videos
        fileChooser = new FileChooser();
        open.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // Pausing the video while switching
                if (player != null) {
                    player.player.pause();
                }
                File file = fileChooser.showOpenDialog(primaryStage);

                // Choosing the file to play
                if (file != null) {
                    try {
                        player = new Player(file.toURI().toURL().toExternalForm());
                        Scene scene = new Scene(player, 720, 535, Color.BLACK);
                        primaryStage.setScene(scene);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // Initialize player with a default video
        player = new Player("D:\\Downloads\\snake.mp4");

        // Setting the menu at the top
        BorderPane root = new BorderPane();
        root.setTop(menu);
        root.setCenter(player);

        // Adding player to the Scene
        Scene scene = new Scene(root, 720, 535, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Video Player");
        primaryStage.show();
    }

    // Main function to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}

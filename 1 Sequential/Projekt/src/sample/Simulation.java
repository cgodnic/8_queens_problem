package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Simulation extends Application { //implements ChangeListener
    public int width;
    public int height;
    public int N;
    public double clickX;
    public double clickY;
    public double frameX;
    public double frameY;

    @Override
    public void start(Stage primaryStage) {
        width = 600;
        height = 800;

        Main main = new Main();
        this.N = main.getQueens();

        Zoom zoom = new Zoom();
        ChessBoard board = new ChessBoard(width, height, N);
        StackPane root = new StackPane();
        root.getChildren().add(board);

        board.widthProperty().bind(
                root.widthProperty());
        board.heightProperty().bind(
                root.heightProperty());

        primaryStage.getIcons().add(new Image("file:slike/kraljica_bela.jpg"));
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setTitle("Queens");
        primaryStage.show();

       root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                clickX = mouseEvent.getX();
                clickY = mouseEvent.getY();
                frameX = root.getLayoutX();
                frameY = root.getLayoutY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {


            @Override
            public void handle(MouseEvent mouseEvent) {
               Timeline animation = new Timeline(60);
                double Xk;
                double Yk;
                Xk = clickX - mouseEvent.getX();
                Yk = clickY - mouseEvent.getY();
                animation.getKeyFrames().clear();
                animation.getKeyFrames().addAll(
                        new KeyFrame(Duration.millis(250), new KeyValue(root.translateXProperty(), root.getTranslateX() - Xk )),
                        new KeyFrame(Duration.millis(250), new KeyValue(root.translateYProperty(), root.getTranslateY() - Yk))
                );
               animation.play();
            }
        });


        root.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double zoomFactor = 1.5;
                if (event.getDeltaY() <= 0) {
                    zoomFactor = 1 / zoomFactor;
                }
                zoom.zoom(root, zoomFactor, event.getSceneX(), event.getSceneY());
            }
        });

    }
}

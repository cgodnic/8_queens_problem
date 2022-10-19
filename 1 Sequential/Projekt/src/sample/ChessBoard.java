package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class ChessBoard extends Canvas {
    private Timeline animation;
    public int n;
    public ChessField[][] grid;
    public int sizeh;
    public int sizew;
    public GraphicsContext gc;
    public int[][] tabela;
    public int[][][] tabela3d;
    public int k = 0;
    public int velikost;
    public int flag = 0;
    public int preveriK = 0;
    Image image = new Image("file:slike/unnamed.png");




    public ChessBoard(int width, int height, int n){
        super(height,width);
        this.n = n;
        setVelikost();
        Normal normal = new Normal();


        long startTime = System.currentTimeMillis();
        normal.solveQueens(n, velikost);
        long stopTime = System.currentTimeMillis();
        System.out.println(n+"x"+n + "  " + (stopTime - startTime) + "ms. Vseh rezultatov je: " +normal.getK());

        this.tabela3d = normal.getVrstaTabel();

        this.sizeh = width / n;
        this.sizew = height / n;
        this.gc = this.getGraphicsContext2D();

        setOnMouseClicked(event -> {
            if (flag%2 == 0) {
                animation.pause();
                flag ++;
            }else {
                animation.play();
                flag ++;
            }
        });


        zacniBarvat();
    }


    public void setVelikost(){
        switch (n) {
            case 4:
                velikost =  2;
                break;
            case 5:
                velikost = 10;
                break;
            case 6:
                velikost = 4;
                break;
            case 7:
                velikost = 40;
                break;
            case 8:
                velikost = 92;
                break;
            case 9:
                velikost = 352;
                break;
            case 10:
                velikost = 724;
                break;
            case 11:
                velikost = 2680;
                break;
            case 12:
                velikost = 14200;
                break;
            case 13:
                velikost = 73712;
                break;
            case 14:
                velikost = 365596;
                break;
            case 15:
                velikost = 2279184;
                break;
            case 16:
                velikost = 14772512;
                break;
        }
    }


    private void zacniBarvat(){
       if (k < velikost) {
           animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> pobarvaj()));
           animation.setCycleCount(velikost);
           animation.play();
       }else {
           animation.stop();
       }
    }


    private void pobarvaj(){
        velikost--;
        this.sizew = ((int) getWidth())/n;
        this.sizeh = ((int) getHeight())/n;

        preveriK++;
        //System.out.println(preveriK);

        grid = initializeGrid();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gc.clearRect(i*(sizew),j*(sizeh), sizew, sizeh);
                gc.setFill(grid[i][j].getColor());
                gc.fillRect(i*(sizew),j*(sizeh), sizew, sizeh);
                if(grid[i][j].getNum() == 1){
                    gc.drawImage(image, i*sizew, j*(sizeh), sizew, sizeh);
                }
            }
        }
    }


    private ChessField[][] initializeGrid(){
        ChessField[][] grid = new ChessField[n][n];
        getTabela();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    if(tabela[i][j] == 1){
                        grid[i][j] = new ChessField(1);;
                    }else{
                        grid[i][j] = new ChessField(0);
                    }
                } else {
                    if(tabela[i][j] == 1){
                        grid[i][j] = new ChessField(3);;
                    }else {
                        grid[i][j] = new ChessField(2);
                    }
                }
            }
        }
        return grid;
    }

    public void getTabela(){
        tabela = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tabela[i][j] = tabela3d[k][i][j];
            }
        }
        k++;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

}

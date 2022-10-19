package sample;

import javafx.scene.paint.Color;

public class ChessField {
    private int barva;
    private Color color;
    private boolean fixed;
    public int num;


    public ChessField(int barva) {


        this.barva = barva;

        if (barva == 0) {
            this.color = Color.DARKRED;
        } else if (barva == 1) {
            this.color = Color.DARKRED;
            this.num = 1;
        } else if (barva == 2) {
            this.color = Color.LEMONCHIFFON;
        } else {
            this.color = Color.LEMONCHIFFON;
            this.num = 1;
        }

        this.fixed = fixed;
    }

    public Color getColor() {
        return color;
    }

    public int getNum() { return num;}
}


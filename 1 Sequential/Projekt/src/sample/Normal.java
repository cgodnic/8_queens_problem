package sample;

public class Normal {
    int N;
    int k = 0;
    int velikost;
    public int[][][] vrstaTabel;


    public void solveQueens (int N, int velikost){
        this.N = N;
        int board[][] = new int[N][N];
        this.velikost=velikost;
        this.vrstaTabel =new int [velikost][N][N];

        solveQueensU(board, 0);
    }



    public boolean solveQueensU (int board[][], int col){ //hodi naprej po stolpcih

        //System.out.println("Tukaj sem " + board[0][0] +"  "+ board[1][0] +"  "+ board[2][0] +"    "+ board[3][0]);

        if (col == N){
            if (velikost == 0){
                izpisi(board);
            }

           if (velikost != 0){
               saveBoards(board);
           }
           return true;
        }
        boolean res = false;
        for (int i = 0; i < N; i++) {
            if (canPlace(board, i, col)){
                board[i][col] = 1;
                res = solveQueensU(board, col+1) || res;
                board[i][col] = 0;
            }
        }
        return res;
    }

    public boolean canPlace (int board[][], int row, int col) {

        int i, j;

        //poglej vrstico levo
        for (i=0; i<col; i++){
            if (board[row][i] != 0)
                return false;
        }

        //poglej diagonalo levo gor
        for ( i = row,  j = col; i >= 0 && j>= 0; i--, j--) {
            if (board[i][j]!=0){
                return false;
            }
        }

        //poglej diagonalo levo dol
        for (i = row, j = col; i<N && j >= 0; i++, j--) {
            if (board[i][j]!=0){
                return false;
            }
        }

        return true;
    }


    public void saveBoards(int board[][]){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                vrstaTabel[k][i][j] = board[i][j];
            }
        }
        k++;
    }

    public void  izpisi (int board[][]){
       /* for (int row = 0; row < board.length; row++)//Cycles through rows
        {
            for (int col = 0; col < board[row].length; col++)//Cycles through columns
            {
                System.out.printf("%3d", board[row][col]); //change the %5d to however much space you want
            }
            System.out.println(); //Makes a new row
        }
        System.out.println("");
        System.out.println("");*/
        k++;
    }

    public int getK (){
        return k;
    }

    public int[][][] getVrstaTabel() {
        return vrstaTabel;
    }

}

package com.company;

public class Delaj {

    int N;
    int k = 0;
    int intNow;
    public int[][][] vrstaTabel;


    public void solveQueens (int velikost, int intNow, int kdo, int max){
        this.N = velikost;
        this.intNow = intNow;
        int board[][] = new int[N][N];
        board[intNow][0] = 1;
        //this.vrstaTabel =new int [92][N][N];

        solveQueensU(board, 1);

       /* if(intNow == max-1) {
            System.out.println(" kdo " + kdo + " trenutni i: " + intNow + " Št: " + k);
        }*/
    }

    public boolean solveQueensU (int board[][], int col){ //hodi naprej po stolpcih

        //System.out.println("Tukaj sem " + board[0][0] +"  "+ board[1][0] +"  "+ board[2][0] +"    "+ board[3][0]);

        if (col == N){
            izpisi(board);
            //saveBoards(board);
            return true;
        }
        boolean res = false;
        for (int i = 0; i < N; i++) {
            if (canPlace(board, i, col)){
                if(col==0){ //smo pršli na začetno pozicijo
                    break;
                }else {
                    board[i][col] = 1;
                    res = solveQueensU(board, col + 1) || res;
                    board[i][col] = 0;
                }
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


   /* public void saveBoards(int board[][]){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                vrstaTabel[k][i][j] = board[i][j];
            }
        }
        k++;
    }*/

    public void  izpisi (int board[][]){
        /*for (int row = 0; row < board.length; row++)//Cycles through rows
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
        //System.out.println(k);

    }





    public int getK (){
        return k;
    }
/*
    public int[][][] getVrstaTabel() {
        return vrstaTabel;
    }*/




}

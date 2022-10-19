package sample;

import javafx.application.Application;

public class Main {

    private static int queens;

    public static void main(String[] args) {

        //Program deluje le za 8*8 polje in manjša, ker je 3d tabela v razredu Normal nastavljena na velikost 92 (kolikor je tudi vseh rešitev za 8*8)
        //če kliknete na animacijo (levi miškin klik) se ta ustavi, če ponovno kliknete teče naprej
        //scroll na miški se uporablja za zoom in/out
        //če miško držiš in premikaš se premika šahovnica
        //če spremenimo nrOfQueens se spremeni šahovnica

        // run = 0 -> čas
        // run = 1 -> simulacija

        int run = 0;
        int nrOfQueens = 8;
        queens= nrOfQueens;

        if(run == 0){
            long startTime = System.currentTimeMillis();
            Normal nor = new Normal();
            nor.solveQueens(nrOfQueens, 0);
            long endTime = System.currentTimeMillis();
            long timeCost2 = endTime - startTime;
            System.out.println("Čas: " + timeCost2);
            System.out.println("Število vseh rezultatov: " + nor.getK());
        }else if(run == 1){
            Application.launch(Simulation.class, args);
        }



        //int start = 4;
        //int end = 20;

        //for (int i = start; i < end; i++) {
          //  System.out.println("Smo pri matriki: " +i+"x"+i);
            //for (int j = 0; j < 3; j++) {
                /*long startTime = System.currentTimeMillis();
                Normal nor = new Normal();
                nor.solveQueens(8);
                long endTime = System.currentTimeMillis();
                long timeCost2 = endTime - startTime;
                System.out.println("Čas: " + timeCost2);
                System.out.println("Število vseh rezultatov: " + nor.getK());*/
          //  }
            //System.out.println("------------------------------------------------------------------");
        //}



        //int bor[][] = new int[5][5];
        //bor[0][3] = 5;
       // bor[3][4] = 2;
    }

    public int getQueens (){
        return queens;
    }
}

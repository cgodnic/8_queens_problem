package com.company;
import mpi.*;


public class Main {


   // public static int velikost = 8; //velikost šahovnice

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //int reducedData = 0;
        int[]Buf;
        Delaj delaj = new Delaj();
        int velikost = 8;


                MPI.Init(args);

                double t0 = System.currentTimeMillis();


                int me = MPI.COMM_WORLD.Rank();
                int size = MPI.COMM_WORLD.Size();
                Buf = new int[size];
                int [] reducedData = new int [1];

                int doKje = velikost / size; //pove št na kateremu i-ju konča gledat

                long startTime = System.currentTimeMillis();

                if (me == (size - 1)) {
                    for (int i = (doKje * me); i < velikost; i++) {
                        //System.out.println("Sem proces " + me + " od " + size + " mam za delat od: " + (doKje * me) + " do: " + (doKje * (me + 1)) + " trenutni i: " + i);
                        delaj.solveQueens(velikost, i, me, velikost);

                        if (i == velikost-1){
                            int stevilo = delaj.getK();
                            Buf[0] = stevilo;

                            MPI.COMM_WORLD.Reduce(Buf, 0, reducedData, 0, 1, MPI.INT, MPI.SUM, 0);
                        }

                    }
                } else {
                    for (int i = (doKje * me); i < (doKje * (me + 1)); i++) {
                        //System.out.println("Sem proces " + me + " od " + size + " mam za delat od: " + (doKje * me) + " do: " + (doKje * (me + 1)) + " trenutni i: " + i);
                        delaj.solveQueens(velikost, i, me, (doKje * (me + 1)));

                        if (i == (doKje * (me + 1) - 1)) {
                            int stevilo = delaj.getK();
                            Buf[0] = stevilo;

                            MPI.COMM_WORLD.Reduce(Buf, 0, reducedData, 0, 1, MPI.INT, MPI.SUM, 0);
                        }
                        if (me == 0 && i == (doKje * (me + 1)-1)){
                            long endTime = System.currentTimeMillis();
                            long timeCost2 = endTime - startTime;
                            System.out.println("Skupni cas je:  " + timeCost2);
                            System.out.println("Vseh rezultatov je: " + reducedData[0]);
                        }
                    }
                }
                MPI.Finalize();




        }
    }












package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        //Če nastavimo retr na 0 vrne število vseh rezultatov, če pa na 1 vrne čas
        //velikost -> velikost šahovnice

        int retr = 1;

        //int start = 4;
        int velikost = 8; //neka velikost šahovnice



                int cores = Runtime.getRuntime().availableProcessors()-1;
                ExecutorService executorService = Executors.newFixedThreadPool(cores); //kolko niti imam na razpolago
                int cas = 0; //spremenljivka, ki nam pove maximalni čas izvajanja niti

                List<Future> allFutures = new ArrayList<>(); //naredi seznam da bomo ustavljali notr rezultate
                for (int i = 0; i < velikost; i++) {
                    //Delaj d = new Delaj(velikost, i);
                    Future<Integer> future = executorService.submit(new Delaj(velikost, i, retr)); //daj delat vsaki niti svoje in dobi nazaj int in ga daj u zgornji seznam
                    allFutures.add(future);
                    //executorService.submit(new Delaj(velikost, i));
                }
                executorService.shutdown();
                for(int i=0; i<velikost; i++) { //za usak rezultat steče preverjanje za največji čas (čas izvajanja)
                    Future<Integer> future = allFutures.get(i);
                    try {
                        Integer result = future.get();
                        if (retr == 1){
                            if (result>cas){  //dobi maksimalni čas
                                cas = result;
                            }
                        }else {
                            cas += result;  //dobi rezultat kolko je useh  rešitev
                        }
                        //executorService.awaitTermination(1000, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }

        if (retr == 1){
            System.out.println("Čas računanja je: " + cas);
        }else {
            System.out.println("Skupno število retultatov je: " + cas);
        }


    }
}

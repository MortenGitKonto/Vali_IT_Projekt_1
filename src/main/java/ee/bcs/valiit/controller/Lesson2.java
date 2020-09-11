package ee.bcs.valiit.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lesson2 {

    public static void main(String[] args) throws FileNotFoundException { //OK
        //exercise1();
        //exercise2(8);
        //exercise3(2, 5);
        /*int fibResult = fibonacci(6);
        System.out.println("Fibonacci tagastab selle parameetriga tulemuse: " + fibResult);*/
        //int fibResult2 = fibonacci(6);
        //System.out.println("Fibonacci tagastab selle parameetriga tulemuse: " + fibResult2);
        //exercise5();


        //exercise6();

        //exercise7();
        //exercise8();
        exercise9();
    }

    public static void exercise1() { //OK
        // TODO loo 10 elemendile täisarvude massiv
        int[] m = new int[10];

        // TODO loe sisse konsoolist 10 täisarvu
        Scanner scanner = new Scanner(System.in);
        System.out.println("Palun kirjuta sisse 10 täisarvu");

        for (int i = 0; i < 10; i++) { //OK
            int scanNr = scanner.nextInt();
            m[i] = scanNr;
            System.out.println(i);
        }
        System.out.println(Arrays.toString(m));


        // TODO trüki arvud välja vastupidises järiekorras
        for (int i = 9; i > 0; i--) { //OK
            System.out.println(m[i]);
        }
    }

    public static void exercise2(int x) { //OK
        // TODO prindi välja x esimest paaris arvu

        for (int i = 1; i <= (x * 2); i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

    }

    public static void exercise3(int x, int y) { //OK
        // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
        int[][] tabel = new int[x][y];

        for (int rida = 0; rida < x; rida++) {

            System.out.println();//vahetan printimisel rida

            for (int veerg = 0; veerg < y; veerg++) {

                if (rida == 0) {
                    tabel[rida][veerg] = veerg + 1;
                    System.out.print(tabel[rida][veerg] + " ");
                }

                if (rida == 1) {
                    tabel[rida][veerg] = (rida + 1) * (veerg + 1);
                    System.out.print(tabel[rida][veerg] + " ");
                }
                if (rida == 2) {
                    tabel[rida][veerg] = (rida + 1) * (veerg + 1);
                    System.out.print(tabel[rida][veerg] + " ");
                }
            }
            // TODO näiteks x = 3 y = 3
            // TODO väljund
            // 1 2 3
            // 2 4 6
            // 3 6 9
        }
    }

    public static int fibonacci(int n) { //OK
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);

        int[] a = new int[n];
        a[0] = 0;
        a[1] = 1;
        int element = 0;
        for (int i = 0; i < n; i++) {
            if (i > 1) {
                a[i] = a[i - 2] + a[i - 1];
                element = a[i];
                //System.out.println("New a[i] " + a[i]);

            }


        }
        return element;
    }


        /*int previousElementTwo = 0;
        int previousElementOne = 0;
        int currentElement = 0;

        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                previousElementTwo = 0;
                previousElementOne = 0;
                currentElement = 0;
                //System.out.println(currentElement); CHECK
            } else if (i == 1) {
                previousElementTwo = 0;
                previousElementOne = 1;
                currentElement = 1;
            } else if (i == 2) {
                currentElement = previousElementTwo + previousElementOne;
                //System.out.println("Current " + currentElement);//CHECK
                previousElementTwo=previousElementOne;
                //System.out.println("PrevTwo " + previousElementTwo); //CHECK
                previousElementOne=currentElement;
                //System.out.println(("PrevOne " + previousElementOne)); //CHECK
            } else if (i > 2) {
                currentElement = previousElementTwo + previousElementOne;
                //System.out.println("Current " + currentElement);//CHECK
                previousElementTwo=previousElementOne;
                //System.out.println("PrevTwo " + previousElementTwo); //CHECK
                previousElementOne=currentElement;
                //System.out.println(("PrevOne " + previousElementOne)); //CHECK
            }


    }
        return currentElement*/

    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element


    public static void exercise5() { //OK
        int longestCycle = 0;
        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36
        for (int i = 10; i < 20; i++) {
            int n = i;
            int count = 0;
            while (n != 1) {
                //System.out.println(n);
                count++;
                if (n % 2 != 0) {
                    n = 3 * n + 1;
                } else {
                    n = n / 2;
                }
            }
            //System.out.println(n);
            count++;
            //System.out.println("Count of this nr is " + count);
            if (count > longestCycle) {
                longestCycle = count;
            }
            //System.out.println("LongestCount " + longestCycle);
        }
        System.out.println("LongestCount " + longestCycle);
    }


    public static void exercise6() throws FileNotFoundException {//OK
        /*
            Kirjutada Java programm, mis loeb failist visits.txt sisse looduspargi külastajad erinevatel jaanuari päevadel ning
            a) sorteerib külastuspäevad külastajate arvu järgi kasvavalt ning prindib tulemuse konsoolile;
            b) prindib konsoolile päeva, mil külastajaid oli kõige rohkem.
            Faili asukoht tuleb programmile ette anda käsurea parameetrina.
         */
        File file = new File("C:\\Users\\opilane\\IdeaProjects\\vali-it\\Vali-IT\\resources\\visits.txt");
        Scanner lineReader = new Scanner(file);

        int visitors = 0;
        String date = "";

        List<Integer> list = new ArrayList();
        List<String> listKPV = new ArrayList();

        while (lineReader.hasNextLine()) {

            String line = lineReader.nextLine();
            //System.out.println(line);
            //System.out.println(line.substring(12,15));
            int currentVisitors = 0;
            if (line.length() == 16) {
                currentVisitors = Integer.parseInt(line.substring(12, 16));
            } else {
                currentVisitors = Integer.parseInt(line.substring(12, 15));
            }


            String currentDate = line.substring(0, 10);

            //System.out.println(currentVisitors);

            list.add(currentVisitors);
            listKPV.add(currentDate);
        }

        System.out.println("Algne list : " + list);
        System.out.println("Algne listKPV : " + listKPV);

        //System.out.println(list.get(0));
        //System.out.println(list.get(1));

        int sizeList = list.size();
        //System.out.println("SizeList"+sizeList);

        int temp = 0;
        String tempKPV = "";

        for (int i = 1; i < sizeList; i++) {
            for (int j = i; j > 0; j--) {
                if (list.get(j) < list.get(j - 1)) {
                    temp = list.get(j);
                    tempKPV = listKPV.get(j);
                    //System.out.println("TEMP"+temp);
                    list.set(j, list.get(j - 1));
                    listKPV.set(j, listKPV.get(j - 1));
                    list.set(j - 1, temp);
                    listKPV.set(j - 1, tempKPV);
                }
            }
        }
        System.out.println();
        System.out.println("Lõplik sorteeritud list : " + list);
        System.out.println("Lõplik sorteeritud list : " + listKPV);

        System.out.println();

        System.out.println("Kõige rohkem külastajaid kuupäeval: " + listKPV.get(sizeList - 1));
    }

    public static void exercise7() { //OK
        // TODO arvuta kasutades BigDecimali 1.89 * ((394486820340 / 15 ) - 4 )


        BigDecimal a = new BigDecimal("1.89");
        System.out.println(a);
        BigDecimal b = new BigDecimal("394486820345");
        System.out.println(b);
        BigDecimal c = new BigDecimal("15");
        System.out.println(c);
        BigDecimal d = new BigDecimal("4");
        System.out.println(d);

        BigDecimal result = a.multiply((b.divide(c, RoundingMode.HALF_UP)).subtract(d));
        System.out.println(result);

        //LAHKU KIRJUTATULT

        /*BigDecimal division = b.divide(c, RoundingMode.HALF_UP);
        System.out.println(division);
        BigDecimal subtraction = division.subtract(d);
        System.out.println(subtraction);
        BigDecimal multiplication = subtraction.multiply(a);
        System.out.println(multiplication);*/


    }

    public static void exercise8() throws FileNotFoundException {//OK
        /*
        Failis nums.txt on üksteise all 150 60-kohalist numbrit.

        Kirjuta programm, mis loeks antud numbrid failist sisse ja liidaks need arvud kokku ning kuvaks ekraanil summa.
        Faili nimi tuleb programmile ette anda käsurea parameetrina.*/

        File file = new File("C:\\Users\\opilane\\IdeaProjects\\vali-it\\Vali-IT\\resources\\nums.txt");
        Scanner lineReader = new Scanner(file);
        BigInteger summa = new BigInteger("0");
        while (lineReader.hasNextLine()) {

            String line = lineReader.nextLine();
            BigInteger lineNr = new BigInteger(line);
            summa = summa.add(lineNr);

            //System.out.println(line);
            //System.out.println("Integerina: " + lineNr);
            //System.out.println(line.substring(12,15));
        }
        System.out.println("Kõigi summa on " + summa);

        //VASTUS:
        //Õige summa: 77378062799264987173249634924670947389130820063105651135266574

    }

    public static void exercise9() throws FileNotFoundException {
        /* TODO
        Sama mis eelmises ülesandes aga ära kasuta BigInt ega BigDecimal klassi
         */

        File file = new File("C:\\Users\\opilane\\IdeaProjects\\vali-it\\Vali-IT\\resources\\nums.txt");
        Scanner lineReader = new Scanner(file);
        int[][] table = new int[150][60];
        int count = 0;
        while (lineReader.hasNextLine()) {
            String wholeLine = lineReader.nextLine(); //JÄRGMINE LINE STRINGIKS
            count++;
            //System.out.println(wholeLine.length());
            System.out.println(); //ET RIDADEL VAHE OLEKS VÄLJA PRINTIDES
            for (int j = 0; j < wholeLine.length(); j++) {
                String elementInLineSTR = wholeLine.substring(j, j + 1);
                //System.out.println(elementInLineSTR);
                int elementInLineINT = Integer.parseInt(elementInLineSTR);

                table[count - 1][j] = elementInLineINT;
                System.out.print(table[count - 1][j]);
            }



        }

        //System.out.println();
        //System.out.println(table[0][3]);
        //System.out.println(table.toString());
        //System.out.println("Integerina: " + lineNr);
        //System.out.println(line.substring(12,15));
    }

    //VASTUS:
    //Õige summa: 77378062799264987173249634924670947389130820063105651135266574

}



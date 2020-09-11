package ee.bcs.valiit.controller;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Enne kui seda tegema hakkad tee ära Lesson 2 (välja arvatud ülesanded 6, 8, 9)
public class Lesson3Hard {
    public static void main(String[] args) {
        //int evenFiboSum = evenFibonacci(13);
        //System.out.println(evenFiboSum);


        //randomGame();

        String morsekoodis = morseCode("morten");



    }

    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x

        int[] a = new int[x];
        a[0] = 0;
        a[1] = 1;
        int sumEvenFib = 0;
        for (int i = 2; i < x; i++) {
            a[i] = a[i - 2] + a[i - 1];
            //System.out.println("New a[i] " + a[i]);
            if (a[i] % 2 == 0) {
                sumEvenFib = sumEvenFib + a[i];

            }
            //System.out.println(sumEvenFib);
        }

        return sumEvenFib;
    }


    public static void randomGame() {
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära arvamise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks

        Random random = new Random();
        int arvRandom = random.nextInt(100);
        //System.out.println(arv);

        Scanner scanner = new Scanner(System.in);

        int count = 0;
        for (int i = 1; i > 0; i++) {
            count++;
            int katse = scanner.nextInt();
            if (katse == arvRandom) {
                System.out.println("Õige! See oli " + count + ". katse :)");
                break;
            } else if (katse < arvRandom) {
                System.out.println("Vale, proovi suuremat!");
            } else {
                System.out.println("Vale, proovi väiksemat!");
            }
        }
    }


    public static String morseCode(String text) {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -

        String morseText;
        morseText = "";
        int textLength = text.length();

        HashMap<String, String> keel = new HashMap<>();
        keel.put("a", "· −");
        keel.put("b", "-···");
        keel.put("e", "·");
        keel.put("m", "— —");
        keel.put("o", "— — —");
        keel.put("r", "· — ·");
        keel.put("t", "—");
        keel.put("j", "· — — —");
        keel.put("n", "— ·");

        for (int i = 1; i <= textLength; i++) {
            morseText = morseText + "  " + keel.get(text.substring(i - 1, i));
            //System.out.println(text.substring(i - 1, i));
            //System.out.println(morseText);
        }
        System.out.println("Tekst \"" + text + "\" on morse koodis " + morseText);
        return morseText;
    }
}

package ee.bcs.valiit.controller;

public class Lesson3 {


    public static void main(String[] args) {

        //ESIMENE

        /*int sumInt = sum(4,5);
        //System.out.println(sumInt);*/

        //TEINE HARD-CODE

        /*int[] m = {1,4,7,10};
        int sumMassiiv = sum(m);
        System.out.println("Massiivi summa on :"+sumMassiiv);*/

        //TEINE SCANNITUD MASSIIVI SUURUS JA TEMAS SISALDUVAD VÄÄRTUSED

        /*Scanner scanner = new Scanner(System.in);

        System.out.println("Sisesta massiivi pikkus");
        int massiiviSuurus = scanner.nextInt(); // MASSIIVI PIKKUS SIIT

        int[] m;
        m =new int[massiiviSuurus];

        System.out.println("Sisesta massiivi väärtused 0st kuni n elemendini");

        for (int i = 0; i < massiiviSuurus; i++) {
            System.out.println("Palun sisesta järgmine massiivi väärtus");
            int väärtus = scanner.nextInt();
            m[i] = väärtus;
        }
        int sumMassiiv = sum(m);
        System.out.println("Massiivi summa on :"+sumMassiiv);*/


        //KOLMAS

        // int faktoriaal = factorial(12);
        //System.out.println("Faktoriaal on : " + faktoriaal);

        //NELJAS

        /* int[] m = {2, 6, 6, 4, 2, 10, 110, 8, 1};

       System.out.println("Massiiv on: " + Arrays.toString(m));
        int[] result = sort(m);
        System.out.println("Sorteeritud massiiv on: " + Arrays.toString(result));*/

        //VIIES

        /*String s = reverseString("Vali IT");
        System.out.println(s);*/

        //KUUES

        boolean prime = isPrime(1);
        System.out.println("True or false: " + prime);

    }


    public static int sum(int x, int y) { //OK
        // TODO liida kokku ja tagasta x ja y väärtus
        return x + y;
    }

    public static int sum(int[] x) { //OK
        // Todo liida kokku kõik numbrid massivis x
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum = sum + x[i];
            //System.out.println("Summa on: "+sum);
        }
        return sum;
    }


    public static int factorial(int x) {
        // TODO tagasta x faktoriaal.
        int korrutis = 0;
        for (int i = x - 1; i > 0; i--) {
            if (korrutis == 0) {
                korrutis = i * x;
                System.out.println(korrutis);
            } else {
                korrutis = korrutis * i;
                System.out.println(korrutis);
            }
        }
        return korrutis;
    }


    public static int[] sort(int[] a) {
        // TODO sorteeri massiiv suuruse järgi
        int temp;
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        return a;
    }


    public static String reverseString(String a) {
        // TODO tagasta string tagurpidi
        String tagurpidi = "";
        String currentLetter = "";
        int wordLength = a.length();
        //System.out.println(wordLength);

        for (int i = a.length() - 1; i >= 0; i--) {
            currentLetter = a.substring(i, i + 1);
            //System.out.println(currentLetter);
            tagurpidi = tagurpidi + currentLetter;

        }
        System.out.println(tagurpidi);
        return tagurpidi;
    }

    public static boolean isPrime(int x) {
        // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)

        for (int i = x - 1; i > 1; i--) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }


}



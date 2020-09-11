package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*@RestController
public class TestController {
    @GetMapping()
public Integer test(){
//@RequestParam("fibonacciNth")Integer id
return 3;
    }*/

@RestController
public class TestController {

    /*public int getNthOfFibonacci() {
        int element = 5;
        return fibonacci(element);
    }*/


    private final List<Employee> employees = new ArrayList();

    ///////FIBONACCI/////////11111111111111
    //Fibonacci meetod parameetriga n
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

    ///////2ST ARVUST VÄIKSEM/////////222222222222222222222222
    public static int min(int a, int b) {
        // TODO tagasta a ja b väikseim väärtus
        int min = 0;
        if (a < b) {
            min = a;
        } else {
            min = b;
        }
        return min;
    }

    ///////2ST ARVUST SUUREM/////////
    public static int max(int a, int b) {
        // TODO tagasta a ja b suurim väärtus
        int max = 0;
        if (a < b) {
            max = b;
        } else {
            max = a;
        }

        return max;
    }

    ///////ABSOLUUT/////////
    public static int abs(int a) {
        // TODO tagasta a absoluut arv

        if (a < 0) {
            return -(a);
        } else {
            return a;
        }
    }

    ///////PAARIS VÕI EI/////////
    public static boolean isEven(int a) {
        // TODO tagasta true, kui a on paaris arv
        // tagasta false kui a on paaritu arv
        return a % 2 == 0;
    }

    ///////3ST ARVUST VÄIKSEM/////////
    public static int min(int a, int b, int c) {
        // TODO tagasta a, b ja c väikseim väärtus
        return min(a, min(b, c));
    }

    ///////3ST ARVUST SUUREM/////////
    public static int max(int a, int b, int c) {
        // TODO tagasta a, b ja c suurim väärtus
        return max(a, max(b, c));

    }

    ///////PRIME ARVUD/////////
    public static boolean isPrime(int x) {
        // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)

        for (int i = x - 1; i > 1; i--) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }


/////////////TEE massiiv integeridest///////////////////

    ///////TAGURPIDI STRING/////////
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

    /////////////LIIDA massiivi elemendid///////////////////

    public static int[] makeArray(int a, int b, int c) {

        int[] m = {a, b, c};
        return (m);
    }


/////    GET MAPPINGUD   ///////

    public static int sum(int[] x) {
        // Todo liida kokku kõik numbrid massivis x
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum = sum + x[i];
            //System.out.println("Summa on: "+sum);
        }
        return sum;

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
            //System.out.println(a[i]);
        }
        return a;
    }

    //Esimese osa mõtlen välja, see on fikseeritud. Teine osa {Fibonacci} on sissekirjutatav, et muuta lehe sisu
    @GetMapping("/Fiboarvutus/{Fibonacci}")
    //PathVariable peab olema sama mis {Fibonacci} getmappingus. Integer x deklareeritakse kui sissekirjutatud asi,
    public int test(@PathVariable("Fibonacci") int x) {

        //@RequestParam("fibonacciNth")Integer id
        return fibonacci(x); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/min/{a}/{b}")
    public int miinimum(@PathVariable("a") int x, @PathVariable("b") int y) {

        //@RequestParam("fibonacciNth")Integer id
        return min(x, y); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/max/{a}/{b}")
    public int maksimum(@PathVariable("a") int x, @PathVariable("b") int y) {

        //@RequestParam("fibonacciNth")Integer id
        return max(x, y); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/abs/{a}")
    public int absoluut(@PathVariable("a") int x) {

        //@RequestParam("fibonacciNth")Integer id
        return abs(x); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/paaris/{a}")
    public boolean paaris(@PathVariable("a") int x) {

        //@RequestParam("fibonacciNth")Integer id
        return isEven(x); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/minkolmest/{a}/{b}/{c}")
    public int miinimumKolmest(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {

        //@RequestParam("fibonacciNth")Integer id
        return min(x, y, z); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/maxkolmest/{a}/{b}/{c}")
    public int maksimumKolmest(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {

        //@RequestParam("fibonacciNth")Integer id
        return max(x, y, z); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/prime/{a}")
    public boolean prime(@PathVariable("a") int x) {

        //@RequestParam("fibonacciNth")Integer id
        return isPrime(x); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/tagurpidi/{a}")
    public String prime(@PathVariable("a") String x) {

        //@RequestParam("fibonacciNth")Integer id
        return reverseString(x); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/3x1massiivigeneraator/{a}/{b}/{c}")
    public int[] massiivigener(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {

        //@RequestParam("fibonacciNth")Integer id
        return makeArray(x, y, z);
    }

    @GetMapping("/3x1massiivisumma/{a}/{b}/{c}")
    public int massiivisumma(@PathVariable("a") int x, @PathVariable("b") int y, @PathVariable("c") int z) {

        int[] m = makeArray(x, y, z);
        return sum(m);
    }

    @GetMapping("/faktoriaal/{a}") //SARNASELT EELMISTELE
    public int massiivisumma(@PathVariable("a") int x) {


        return Lesson3.factorial(x);


    }

    @GetMapping("/faktoriaal2")//REQUEST PARAM-IGA EELMISTELE
    public int faktoriaal2(@RequestParam("nr") int x) {

        return Lesson3.factorial(x);

    }

    @GetMapping("/morse")//REQUEST PARAM-IGA EELMISTELE
    public String morse(@RequestParam("sõna") String x) {

        return Lesson3Hard.morseCode(x);

    }

    @GetMapping("/paarisFibonacciLiidetud")
    public int evenFibon(@RequestParam("kuni") int x) {

        return Lesson3Hard.evenFibonacci(x);

    }

    public List<Employee> employeeDtoTest() {
        return employees;
    }

    /*@PostMapping("employees")
    public void employeeDtoTest (@RequestBody Employee employee) {

        /*employee.setAge(25);
        employee.setName("Vello");
        employee.setAddress("kesklinn");*/

        /*System.out.println(employee.getAge());
        System.out.println(employee.getAddress());
        System.out.println(employee.getName());*/

    /*{
        "name": "Vello",
        "age": 25,
        "address": "Paide"
    }*/


    //return employee;

    //Vaata listi
//Loo elemente listi
    @PostMapping("/addemployee")
    public void addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
    }


    /*@PostMapping("/addemployeeindex/{a}")
    public void addEmployee(@RequestBody Employee employee, @PathVariable("a") int x) {
        employees.add(x, employee);
        //System.out.println(employees);

    }*/

    @PostMapping("/addemployeeindex/{a}")
    public void addEmployee(@RequestBody Employee employee, @PathVariable("a") int x) {
        employees.add(x, employee);
        //System.out.println(employees);
    }

//Vaata listi indexil ...
        @GetMapping("/employee/{a}")
        public Employee employeeInfo ( @PathVariable("a") int x){
            return employees.get(x);
        }
//Muuda listi indexil...
        @PutMapping("/setemployee/{a}")
        public void replaceInfo (@RequestBody Employee employee,@PathVariable("a") int x){
            employees.set(x, employee);
        }
//Võta listist ära element indexil...
        @DeleteMapping("/deleteemployee/{a}")
        public void deleteInfo ( @PathVariable("a") int x){
            employees.remove(x);
        }


    }











package edu.touro.las.mcon364.func_prog.exercises;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Functional Interface Practice
 * <p>
 * In this assignment you will:
 * - Create and return different functional interfaces
 * - Apply them
 * - Practice chaining where appropriate
 * <p>
 * IMPORTANT:
 * - Use lambdas
 * - Do NOT use anonymous classes
 */
public class FunctionalInterfaceExercises {

    // =========================================================
    // PART 1 — SUPPLIERS
    // =========================================================

    /**
     * 1) Create a Supplier that returns the current year.
     * <p>
     * Hint:
     * You can get the current date using:
     * LocalDate.now()
     * <p>
     * Then extract the year using:
     * getYear()
     * <p>
     * Example (not the solution):
     *
     */
    public static Supplier<Integer> currentYearSupplier() {
        // TODO
        return () -> LocalDate.now().getYear();
    }

    /**
     * 2) Create a Supplier that generates a random number
     * between 1 and 100.
     */
    public static Supplier<Integer> randomScoreSupplier() {
        // TODO
        return () -> ThreadLocalRandom.current().nextInt(1, 100);
    }

    // =========================================================
    // PART 2 — PREDICATES
    // =========================================================

    /**
     * 3) Create a Predicate that checks whether
     * a string is all uppercase.
     */
    public static Predicate<String> isAllUpperCase() {
        return s -> Objects.equals(s, s.toUpperCase());
    }

    /**
     * 4) Create a Predicate that checks whether
     * a number is positive AND divisible by 5.
     * <p>
     * Hint: consider chaining.
     */
    public static Predicate<Integer> positiveAndDivisibleByFive() {
        // TODO
        return num -> (num > 0 && num % 5 == 0);
    }

    // =========================================================
    // PART 3 — FUNCTIONS
    // =========================================================

    /**
     * 5) Create a Function that converts
     * a temperature in Celsius to Fahrenheit.
     * <p>
     * Formula: F = C * 9/5 + 32
     */
    public static Function<Double, Double> celsiusToFahrenheit() {
        // TODO

        return temp -> (temp * (9.0 / 5.0) + 32);
    }

    /**
     * 6) Create a Function that takes a String
     * and returns the number of vowels in it.
     * <p>
     * Bonus: Make it case-insensitive.
     */
    public static Function<String, Integer> countVowels() {
        // TODO

        return (String s) -> {
            s = s.toLowerCase();
            String vowels = "aeiou";
            int ctr = 0;
            for (int i = 0; i < s.length(); i++) {
                String c = String.valueOf(s.charAt(i));
                if (vowels.contains(c))
                    ctr++;
            }

            return ctr;
        };
    }

    // =========================================================
    // PART 4 — CONSUMERS
    // =========================================================

    /**
     * 7) Create a Consumer that prints a value
     * surrounded by "***"
     * <p>
     * Example output:
     * *** Hello ***
     */
    public static Consumer<String> starPrinter() {
        // TODO
        return (value) -> System.out.println("*** " + value + " ***");
    }

    /**
     * 8) Create a Consumer that prints the square
     * of an integer.
     */
    public static Consumer<Integer> printSquare() {
        // TODO
        return (num) -> System.out.println(num * num);
    }

    // =========================================================
    // PART 5 — APPLYING FUNCTIONAL INTERFACES
    // =========================================================

    /**
     * 9) Apply:
     * - A Predicate
     * - A Function
     * - A Consumer
     * <p>
     * Process the list as follows:
     * - Keep only strings longer than 3 characters
     * - Convert them to lowercase
     * - Print them
     */
    public static void processStrings(List<String> values) {
        // TODO

        Predicate<String> stringsOfThree = (s) -> s.length() > 3;
        Function<String, String> lowerCase = String::toLowerCase;
        Consumer<List<String>> printS = v-> {
            for (String str :v) {
                if (stringsOfThree.test(str)) {
                    str = lowerCase.apply(str);
                    System.out.println(str);
                }
            }
        };
        printS.accept(values);
    }




    /**
     * 10) Apply:
     * - A Supplier
     * - A Predicate
     * - A Consumer
     * <p>
     * Generate 5 random scores.
     * Print only those above 70.
     */
    public static void generateAndFilterScores() {
        // TODO
        Supplier<List<Integer>> numGenerator= ()-> {
            List<Integer>list= new ArrayList<>();
            Random random = new Random();
            for(int i=0;i<4;i++){
            list.add(random.nextInt(101));
            }
            return list;};
        Predicate<Integer> over70= i->i>70;
        Consumer<List<Integer>> printAbove70= list->{
            for(Integer integer: list){
                if(over70.test(integer))
                    System.out.println(integer);
            }
        };
        printAbove70.accept(numGenerator.get());
    }
}


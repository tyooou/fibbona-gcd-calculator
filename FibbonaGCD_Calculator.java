import java.util.Scanner;

public class FibbonaGCD_Calculator {

  public static void main(String[] args) { // User interface.
    // Initialise scanner object to read user inputs.
    Scanner scanner = new Scanner(System.in);

    // Initialise base values.
    int intA = 0;
    int intB = 0;

    // Prompt user with purpose of program.
    System.out.println("\u001B[1m\u001B[32mFibbonaGCD Calculator\u001B[0m");
    System.out.println(
        "This program calculates the greatest common divisor (GCD) of the sum of the first A"
            + " Fibonacci numbers and the sum of the first B Fibonacci numbers, where A and B are"
            + " two positive integers. Zero is considered as the first Fibonacci number.\n");

    // Prompt user input for integer A.
    System.out.print("Enter the first positive integer A: ");

    do { // Ensure that integer A is a positive integer, prompting another user input if initially
      // invalid.
      String inputA = scanner.nextLine().trim();
      if (inputA.isEmpty()
          || !inputA.matches("\\d+(\\.0*)?")) { // Only allow integers to be inputted.
        System.out.print(
            "\u001B[31mError: That is not a positive integer.\u001B[0m\n\n"
                + "Please enter the first positive integer A: ");
      } else {
        intA = (int) Double.parseDouble(inputA);
        if (intA <= 0) { // Only allow positive integers to be inputted.
          System.out.print(
              "\u001B[31mError: That is not a positive integer.\u001B[0m\n\n"
                  + "Please enter the first positive integer A: ");
        } else if (intA > 45) { // Only allow positive integers less than 47 to be inputted.
          System.out.print(
              "\u001B[31mError: This positive integer will result in an overflow due to limitations"
                  + " with the int type.\u001B[0m\n\n"
                  + "Please enter the first positive integer A: ");
          intA = 0;
        }
      }
    } while (intA <= 0);

    // Prompt user input for integer B.
    System.out.print("\nEnter the second positive integer B: ");

    do { // Ensure that integer B is a positive integer, prompting another user input if initially
      // invalid.
      String inputB = scanner.nextLine().trim();
      if (inputB.isEmpty()
          || !inputB.matches("\\d+(\\.0*)?")) { // Only allow integers to be inputted.
        System.out.print(
            "\u001B[31mError: That is not a positive integer.\u001B[0m\n\n"
                + "Please enter the second positive integer B: ");
      } else {
        intB = (int) Double.parseDouble(inputB);
        if (intB <= 0) { // Only allow positive integers to be inputted.
          System.out.print(
              "\u001B[31mError: That is not a positive integer.\u001B[0m\n\n"
                  + "Please enter the second positive integer B: ");
        } else if (intB > 45) { // Only allow positive integers less than 47 to be inputted.
          System.out.print(
              "\u001B[31mError: This positive integer will result in an overflow due to limitations"
                  + " with the int type.\u001B[0m\n\n"
                  + "Please enter the second positive integer A: ");
          intB = 0;
        }
      }
    } while (intB <= 0);

    // Close scanner object for optimisation.
    scanner.close();

    // Perform calculation.
    int gcd = fibonacciGCD(intA, intB);

    // Print result.
    System.out.println(
        "\nThe greatest common divisor of the sum of the first "
            + intA
            + " Fibonacci numbers and the sum of the first "
            + intB
            + " Fibonacci numbers is "
            + gcd
            + ".\n");
    System.exit(0);
  }

  public static int fibonacciGCD(int intA, int intB) { // Calculates the GCD of two Fibonacci sums.
    // Initialise Fibonacci sums of A and B.
    int fSumA = fibonacciSum(intA);
    int fSumB;

    if (intB == intA) { // Set the Fibonacci sum of A and B to be the same if A is equal to B for
      // optimisation.
      fSumB = fSumA;
    } else { // Calculate Fibonacci sum of B.
      fSumB = fibonacciSum(intB);
    }

    // Find GCD of the two Fibonacci sums.
    System.out.println(
        "\n\nUsing the Euclidean algorithm to find the greatest common divisor of "
            + fSumA
            + " and "
            + fSumB
            + ": \n");
    return EuclidsGCD(fSumA, fSumB);
  }

  public static int EuclidsGCD(int intA, int intB) { // Calculates the GCD of two integers.
    if (intB == 0) { // Return GCD.
      return intA;
    }

    // Print working.
    int multiplier = (intA - (intA % intB)) / intB;
    System.out.println(intA + " = " + intB + " x " + multiplier + " + " + intA % intB);
    return EuclidsGCD(intB, intA % intB);
  }

  public static int fibonacciSum(
      int amount) { // Calculates the sum of the first <amount> Fibonacci numbers.
    // Initialise sum.
    int sum = 0;

    // Initialise overflow for elliptical construction.
    Boolean overflow = false;
    int printAmount = amount;
    String sumPrint = "\nThe sum of the first " + amount + " Fibonacci numbers: { ";

    // Enable overflow if the amount of numbers being added is greater than 10.
    if (amount > 10) {
      overflow = true;
      printAmount = 3 + 1;
    }

    // Sum Fibonacci numbers.
    for (int i = 1; i < amount + 1; i++) {
      sum += fibonacci(i);

      // Print and format Fibonacci numbers.
      if (i < printAmount) {
        sumPrint += fibonacci(i) + ", ";
      } else if (i == amount) {
        if (overflow) {
          sumPrint += "... , " + fibonacci(i) + " }";
        } else {
          sumPrint += fibonacci(i) + " }";
        }
      }
    }
    System.out.print(sumPrint + " is " + sum + ".");
    return sum;
  }

  public static int fibonacci(int number) { // Returns the nth number of the Fibonacci sequence.
    if (number <= 1) { // Return zero as the first Fibonacci number. Any invalid numbers (eg. zero,
      // negative numbers) are treated as zero.
      return 0;
    } else if (number == 2) { // Return one as the second Fibonacci number.
      return 1;
    } else { // Recursively find the nth number of the Fibonacci sequence.
      return fibonacci(number - 1) + fibonacci(number - 2);
    }
  }
}

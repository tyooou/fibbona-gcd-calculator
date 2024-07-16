# FibbonaGCD Calculator

_FibbonaGCD Calculator_ is a simple program that calculates the greatest common divisor (GCD) of the sum of the first A Fibonacci numbers and the sum of the first B Fibonacci numbers, where A and B are two positive integers.

**The first Fibbonacci number is defined as zero**.

> **NOTE:** _FibbonaGCD Calculator_ can only calculate the sum of up to and including the **first 45 numbers of the Fibonacci sequence** due to the fundamental limitations of an integer type in Java. Overflow will occur with amounts greater than 45, thus an incorrect answer will be returned. The user will be prompted with an error message if they enter a number greater than 45.

## Compilation

1. Ensure you have **Java Development Kit (JDK)** installed on your system. You can download and install it from the [official Oracle website](https://www.oracle.com/java/technologies/downloads/#java22) or use **OpenJDK**, which is available through package managers like _apt_, _yum_, or _Homebrew_.

2. Copy the provided Java code into a text editor and save it with the `.java` extension. For example, you can save it as `FibbonaGCD_Calculator.java`.

3. Open a terminal or command prompt. Navigate to the directory where you saved the Java file using the cd command. Compile the Java file using the `javac` command followed by the filename with the `.java` extension.

   ```
   javac FibbonaGCD_Calculator.java
   ```

   If the error,

   ```
   javac" is not recognized as an internal or external command, operable program or batch file
   ```

   occurs, this is because the `java` path is not set in your system. You can resolve this setting the path before compilation.

   ### Set Path in Windows:

   Open command prompt (`cmd`), go to the place where you have installed `java` on your system and locate the bin directory, copy the complete path and write it in the command like this.

   ```
    set path=C:\Program Files\Java\jdk1.8.0_121\bin
   ```

   **Note:** Your `jdk` version may be different. The command assumes that `java version 1.8.0_121` is installed on the system.

   ### Set Path in Mac OS X:

   Open Terminal, type the following command and hit return.

   ```
    export JAVA_HOME=/Library/Java/Home
   ```

   Type the following command on terminal to confirm the path.

   ```
    echo $JAVA_HOME
   ```

   > The steps above are for setting up the path **temporarily** which means when you close the command prompt or terminal, the path settings will be lost and you will have to set the path again next time you use it.

4. After successful compilation, you'll find a `.class` file generated in the same directory. Execute the compiled Java program using the `java` command followed by the class name (without the .class extension).

   ```
   java FibbonaGCD_Calculator
   ```

5. Follow the prompts displayed by the program in the terminal to input the values for integers A and B. Refer to the [Instructions](#instructions) section below.

6. The program will calculate the greatest common divisor (GCD) based on the inputs and display the result in the terminal.
7. After viewing the result, the program will exit automatically.

## Instructions

To use _FibbonaGCD Calculator_:

1. Enter a positive integer representing A after the following prompt:

```
Enter the first positive integer A:
```

_FibbonaGCD Calculator_ will prompt you with one of the following error messages if the input is not a valid positive integer.

```
Error: That is not a positive integer.
Error: This positive integer will result in an overflow due to limitations with the int type.
```

2. Enter a positive integer representing B after the following prompt:

```
Enter the first positive integer B:
```

3. The result will be printed.

## Solution

The function of _FibbonaGCD_ can be broken down into three core components.

- **A function that returns the nth number of the Fibonacci sequence.** The nth number of the Fibonacci sequence is found recursively using the following formula.
  $$f_n = f_{n-1} + f_{n-2}\text{ where }n\in\mathbb{Z}_{\geq0}\text{ and }f_0=0,\space f_1=1$$
  ```java
  public static int fibonacci(int number) { // Returns the nth number of the Fibonacci sequence.
      if (number <= 1) { // Return zero as the first Fibonacci number. Any invalid numbers (eg. zero, negative numbers) are treated as zero.
          return 0;
      } else if (number == 2) { // Return one as the second Fibonacci number.
          return 1;
      } else { //  Recursively find the nth number of the Fibonacci sequence.
          return fibonacci(number - 1) + fibonacci(number - 2);
      }
  }
  ```
- **A function that returns the sum of numbers.** The numbers are formatted into a set (`{x0, x1, x2, ... xn}`) to be printed to console. If the total numbers exceeds ten, elliptical construction is used from the third number until the last number.

  ```java
  public static int fibonacciSum(int amount) { // Calculates the sum of the first <amount> Fibonacci numbers.
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
  ```

- **A function that returns the greatest common divisor (GCD) between two integers.** The _Euclidean algorithm_ is used to find the greatest common divisor between two integers. Working is formatted and printed to console.

  ```java
  public static int EuclidsGCD(int intA, int intB) { // Calculates the GCD of two integers.
      if (intB == 0) { // Return GCD.
      return intA;
      }

      // Print working.
      int multiplier = (intA - (intA % intB)) / intB;
      System.out.println(intA + " = " + intB + " x " + multiplier + " + " + intA % intB);
      return EuclidsGCD(intB, intA % intB);
  }
  ```

### Testing and Debugging:

The user input is managed by a `Scanner` object. All inputs are trimmed before put against conditional checks, thus whitespaces appended at the start or end of inputs are ignored. There are **seven possible cases** of user inputs:

- **A positive integer.**
  If a positive integer is inputted by the user, the program progresses as expected. Note that for large numbers, the computation time will be exceedingly long.

  ```
  Enter the first positive integer A: 13

  Enter the second positive integer B: 6

  The sum of the first 13 Fibonacci numbers: { 0, 1, 1, ... , 144 } is 376.
  The sum of the first 6 Fibonacci numbers: { 0, 1, 1, 2, 3, 5 } is 12.

  Using the Euclidean algorithm to find the greatest common divisor of 376 and 12:

  376 = 12 x 31 + 4
  12 = 4 x 3 + 0

  The greatest common divisor of the sum of the first 13 Fibonacci numbers and the sum of the first 6 Fibonacci numbers is 4.
  ```

  If the integer is **greater than 45**, a error message will be printed.

  ```
  Error: This positive integer will result in an overflow due to limitations with the int type.
  ```

  This is due to the `int` type in Java only being only signed 32-bits, thus overflow will occur with any value summation of Fibonacci numbers over th 45th number (as seen below):

  ```
  The sum of the first 46 Fibonacci numbers: { 0, 1, 1, ... , 1134903170 } is -1323752224.
  ```

  This clearly is incorrect, as a negative value cannot be the result of the summation of positive values.

- **A negative integer.**
  If a negative integer is inputted by the user, the program immediately prompts the user with the following error message and asks for another input.

  ```
  Enter the first positive integer A: -5
  Error: That is not a positive integer.

  Please enter the first positive integer A:
  ```

- **Zero.**
  If zero is inputted by the user, the program immediately prompts the user with the following error message and asks for another input.

  ```
  Enter the first positive integer A: 0
  Error: That is not a positive integer.

  Please enter the first positive integer A:
  ```

- **A decimal.**
  If a decimal that cannot be expressed as an integer, is inputted by the user, the program immediately prompts the user with the following error message and asks for another input. Otherwise, the program progresses as expected. Note that for large numbers, the computation time will be exceedingly long.

  ```
  Enter the first positive integer A: 2.55
  Error: That is not a positive integer.

  Please enter the first positive integer A: 13

  Enter the second positive integer B: 6.000000

  The sum of the first 13 Fibonacci numbers: { 0, 1, 1, ... , 144 } is 376.
  The sum of the first 6 Fibonacci numbers: { 0, 1, 1, 2, 3, 5 } is 12.

  Using the Euclidean algorithm to find the greatest common divisor of 376 and 12:

  376 = 12 x 31 + 4
  12 = 4 x 3 + 0

  The greatest common divisor of the sum of the first 13 Fibonacci numbers and the sum of the first 6 Fibonacci numbers is 4.
  ```

- **Not an integer.**
  If anything but an integer (e.g. a string) is inputted by the user, the program immediately prompts the user with the following error message and asks for another input.

  ```
  Enter the first positive integer A: SOFTENG
  Error: That is not a positive integer.

  Please enter the first positive integer A:
  ```

- **A mix of integers and not integers.**

  If a mix of integers and not integers are inputted by the user, the program immediately prompts the user with the following error message and asks for another input.

  ```
  Enter the first positive integer A: SOFTENG 282
  Error: That is not a positive integer.

  Please enter the first positive integer A:
  ```

- **Nothing.**
  If nothing is inputted by the user, the program immediately prompts the user with the following error message and asks for another input.

  ```
  Enter the first positive integer A:
  Error: That is not a positive integer.

  Please enter the first positive integer A:
  ```

Completed for SOFTENG 282: Software Engineering Theory.

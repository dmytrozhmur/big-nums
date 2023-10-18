package org.example;

import org.example.nums.BigNum;
import org.example.operations.Operation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        App.start();
    }

    private static void start() {
        Operation operation;
        do {
            operation = readOperation();
        } while (operation != null && readParamsAndPrintResult(operation));
    }

    private static boolean readParamsAndPrintResult(Operation operation) {
        if (operation.equals(Operation.EXIT)) {
            return false;
        }

        BigNum firstNum = new BigNum();
        BigNum secondNum = new BigNum();
        BigNum thirdNum;

        System.out.println("Enter first hex number: ");
        String firstHex = scanner.nextLine();
        firstNum.setHex(firstHex);
        System.out.println("Enter second hexNumber");
        String secondHex = scanner.nextLine();
        secondNum.setHex(secondHex);

        thirdNum = operation.apply(firstNum, secondNum);

        System.out.printf("Result is:\n%s\n", thirdNum.getHex());
        return true;
    }

    private static Operation readOperation() {
        System.out.println("Enter the operation " +
                "(ADD/SUB/MOD):");
        String input = scanner.nextLine();
        return Operation.valueOf(input.toUpperCase());
    }
}

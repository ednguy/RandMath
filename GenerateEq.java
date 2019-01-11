package com.frizzlefox.randmath;

public class GenerateEq {
    private int first, second, answer;
    private String eq;
    private final int MAX = 100;
    private final int MIN = 1;

    public GenerateEq() {
        createEquation();
    }

    private int createRandomNumber() {
        int a;
        a = (int) (Math.random() * (MAX - MIN) + MIN);
        return a;
    }

    private int createRandomOperation() {
        int a;
        a = (int) (Math.random() * ((3 - 1) + 1)) + 1;
        return a;
    }

    private String createEquation() {
        first = createRandomNumber();
        second = createRandomNumber();

        switch (createRandomOperation()) {
            case 1:
                eq = first + " + " + second + " = ";
                answer = first + second;
                break;
            case 2:
                eq = first + " - " + second + " = ";
                answer = first - second;
                break;
            case 3:
                eq = first + " * " + second + " = ";
                answer = first * second;
                break;
            default:
                eq = "Error: issue generating equation.";
                answer = -1;
        }

        return eq;
    }

    public int getAnswer() {
        return answer;
    }

    public String getEq() {
        return eq;
    }
}

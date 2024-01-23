import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Name:
 * Class Group:
 */
public class CA3_Question8 {

    /*
        Reads in an equation from the user
     */
    public static void main(String[] args) {
        String equation;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter equation");
        equation = in.nextLine().trim();

        Deque<Integer> numberStack = new ArrayDeque<>();
        Deque<String> operatorStack = new ArrayDeque<>();
        boolean lastCharWasInt = false;
        for (int i = 0; i < equation.length(); i++) {
            String currentChar = equation.substring(i, i + 1);
            int asciiValue = currentChar.charAt(0);
            if (asciiValue > 47 && asciiValue < 58) {
                if (lastCharWasInt){
                    String concatNum = String.valueOf(numberStack.pop())+currentChar;
                    numberStack.push(Integer.parseInt(concatNum));
                } else {
                    numberStack.push(Integer.parseInt(currentChar));
                }
                lastCharWasInt = true;
            } else if (currentChar.equals("(")) {
                operatorStack.push(currentChar);
                lastCharWasInt = false;
            } else if (currentChar.equals("+") || currentChar.equals("-") || currentChar.equals("x") || currentChar.equals("/")) {
                lastCharWasInt = false;
                int operatorPrecedenceTopOfStack;
                int operatorPrecedenceNotOnStack;
                if (!operatorStack.isEmpty()) {
                    String operatorTopOfStack = operatorStack.peek();
                    if (operatorTopOfStack.equals("x") || operatorTopOfStack.equals("/")) {
                        operatorPrecedenceTopOfStack = 2;
                    } else {
                        operatorPrecedenceTopOfStack = 1;
                    }
                    if (currentChar.equals("x") || currentChar.equals("/")) {
                        operatorPrecedenceNotOnStack = 2;
                    } else {
                        operatorPrecedenceNotOnStack = 1;
                    }
                    if (operatorPrecedenceTopOfStack > operatorPrecedenceNotOnStack) {
                        evaluateTop(numberStack, operatorStack);
                    }
                }
                operatorStack.push(currentChar);

            } else if (currentChar.equals(")")) {
                if (!operatorStack.peek().equals("(")) {
                    evaluateTop(numberStack, operatorStack);
                }
                lastCharWasInt = false;
                operatorStack.pop();
            }
            if (i == equation.length() - 1)
                while (!operatorStack.isEmpty()) {
                    evaluateTop(numberStack, operatorStack);
                }
        }
        System.out.println("Final number is = " + numberStack.pop());
    }

    public static void evaluateTop(Deque<Integer> numberStack, Deque<String> operatorStack) {
        int numToGoOnStack;
        int first = numberStack.pop();
        int second = numberStack.pop();
        String operator = operatorStack.pop();
        numToGoOnStack = switch (operator) {
            case "+" -> first + second;
            case "-" -> second - first;
            case "x" -> first * second;
            default -> second / first;
        };
        numberStack.push(numToGoOnStack);
    }
}

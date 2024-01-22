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
        Deque<Character> operatorStack = new ArrayDeque<>();

        for (int i = 0; i < equation.length(); i++) {
            char currentChar = equation.charAt(i);
            int intValueOfChar = currentChar;
            if (intValueOfChar > 47 && intValueOfChar < 58) {
                numberStack.push(intValueOfChar);
            } else if (currentChar == '(') {
                operatorStack.push(currentChar);
            } else if (currentChar == '+' || currentChar == '-' || currentChar == 'x' || currentChar == '/') {
                int operatorPrecedenceTopOfStack;
                int operatorPrecedenceNotOnStack;
                if (!operatorStack.isEmpty()){
                    char operatorTopOfStack = operatorStack.peek();
                    if (operatorTopOfStack == 'x' || operatorTopOfStack == '/') {
                        operatorPrecedenceTopOfStack = 2;
                    } else {
                        operatorPrecedenceTopOfStack = 1;
                    }
                    if (currentChar == 'x' || currentChar == '/') {
                        operatorPrecedenceNotOnStack = 2;
                    } else {
                        operatorPrecedenceNotOnStack = 1;
                    }

                    if (operatorPrecedenceTopOfStack > operatorPrecedenceNotOnStack) {
                        evaluateTop(numberStack,operatorStack);
                    }
                }

            } else if (currentChar==')'){
                if (operatorStack.peek()!='('){
                    evaluateTop(numberStack,operatorStack);
                }
                operatorStack.pop();
            }
        }
        while (!operatorStack.isEmpty()){
            evaluateTop(numberStack,operatorStack);
        }

        System.out.println("Final number is = "+numberStack.pop());
    }
    public static void evaluateTop(Deque<Integer> numberStack,Deque<Character> operatorStack){
        int numToGoOnStack;
        int first = numberStack.pop();
        int second = numberStack.pop();
        char operator = operatorStack.pop();
        if (operator == '+') {
            numToGoOnStack = first + second;
        } else if (operator == '-') {
            numToGoOnStack = second - first;
        } else if (operator == 'x') {
            numToGoOnStack = first * second;
        } else {
            numToGoOnStack = second / first;
        }
        numberStack.push(numToGoOnStack);
    }
}

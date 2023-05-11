package PostFix;

import java.util.Scanner;

/**
 *  Postfix
 *  A driver class for the PostFixEvaluator class.
 *  @author Anthony Ung
 */
public class PostFix {
    
    /*  input is global so that all methods have a common Scanner object. */
    private final static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        /*  expression is the postfix expression inputted by the user.
            again is the flag that is set by the user input.
            postfix instantiates the PostFixEvaluator.  */
        String expression;
        boolean again;
        
        do {
            expression = getExpression();
            evaluate(expression);
            again = setAgain();
        } while(again);
        input.close();
    }
    
    /*  Displays a prompt to the user and returns the input to the caller. */
    public static String getExpression() {
        System.out.println("Enter a postfix expression: ");
        return input.nextLine();
    }
    
    /*  Takes the postfix expression and then evaluates it using the postfix
            object initialized. 
        A new PostFixEvaluator is created every time this method is called
            so that any extra operands from a previous evaluation do not remain.
            Example: Attempting to evaluate "3 4 5 +" would result in a single
                "3" remaining on the stack. */
    public static void evaluate(String expression) {
        int result;
        PostFixEvaluator postfix = new PostFixEvaluator();
        
        try {
            result = postfix.evaluate(expression);
            System.out.println("That expression equals " + result);
        }
        catch(Exception ex) 
            { System.out.println(ex.getMessage()); }
    }
    
    /*  Sets a flag depending on whether the user wishes to evaluate another
            expression. */
    public static boolean setAgain() {
        String again;
        
        /*  Intentional nested do-while loops to separate the length
                check from the check for certain enumerated values. */
        do {
            do { 
                System.out.print("Evaluate another expression {Y/N}? ");
                again = input.nextLine(); 
            } while(again.length() != 1);
        } while(!(again.equals("y")) && !(again.equals("Y"))
            && !(again.equals("n")) && !(again.equals("N")));
        
        /*  Empty line to separate different runs. */
        System.out.println("");
        return (again.equals("Y") || again.equals("y"));
    }
}

package PostFix;

import Stack.LinkedStack;
import java.util.StringTokenizer;

/**
 *
 * @author Anthony Ung
 */
public class PostFixEvaluator {
    
    /*  Constants for different operands. */
    private final static char ADD = '+';
    private final static char SUBTRACT = '-';
    private final static char MULTIPLY = '*';
    private final static char DIVIDE = '/';
    private final static char MOD = '%';
    private final static char EXPONENT = '^';
    
    /*  Each PostFixEvaluator has a stack.*/
    private Stack.LinkedStack<Integer> stack;
    
    /*  Sets up the evaluator by creating a new stack. */
    public PostFixEvaluator() 
    { stack = new LinkedStack<Integer>(); }
    
    /*  
        Evaluates the specified postfix expression. 
        If an operand is encountered, it is pushed onto the stack. 
        If an operator is encountered, two operands are popped, 
            the operation is evaluated, and the result is pushed onto the stack.
    */
    public int evaluate(String expr) throws Exception {
        int op1, op2, result;
        char operator;
        String token;
        StringTokenizer tokenizer = new StringTokenizer(expr);
        
        /*  If an empty line was received, it throws an expression that is
                propogated back to the driver.*/
        if(!(tokenizer.hasMoreTokens())) 
            {   throw new PostFixException("emptyLineReceived"); }
        
        while(tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            
            if(isOperand(token))
            {   stack.push(Integer.parseInt(token)); }
            
            else if(isOperator(token)) {
                try {
                    op2 = stack.pop();
                    op1 = stack.pop();
                
                    operator = token.charAt(0);
                    result = evalSingleOp(operator, op1, op2);
                    stack.push(result);
                }
                /*  Catches attempts to do an operation with one or zero
                        operands. (i. e. "1 +" or "-" */
                catch(NullPointerException nullPtr) 
                    {   throw new PostFixException ("noMoreInts"); }
            }
            /*  If the token is not an integer and it is not a symbol, 
                    then it throws an exception.
                This exception propogates back to the driver
                    and its message is displayed.
            */
            else 
                { throw new PostFixException ("illegalToken"); }
        }
        
        return returnNum();
    }
    
    /*  Checks whether there is exactly one integer remaining on the stack. 
        Returns the final number that is evaluated from a valid postfix
            expression. */
    private int returnNum() throws Exception {
        if(stack.size() > 1) 
            {   throw new PostFixException ("manyOperands"); }
        else if(stack.size() == 0) 
            {   throw new PostFixException ("fewOperands"); }

        return stack.pop();
    }
    
    /*  Evaluates a single operation with two operands.
        Propogates exceptions back to the drver when an 
            unusual operation is attempted. */
    private int evalSingleOp(char operation, int op1, int op2) throws Exception {
        switch(operation) {
            case ADD ->         { return PostFixOperations.add(op1, op2); }
            case SUBTRACT ->    { return PostFixOperations.subtract(op1, op2); }
            case MULTIPLY ->    { return PostFixOperations.multiply(op1, op2); }
            case DIVIDE ->      { return PostFixOperations.divide(op1, op2); }
            case MOD ->         { return PostFixOperations.mod(op1, op2); }
            case EXPONENT ->    { return PostFixOperations.power(op1, op2); }
        }
        /*  Unreachable since operation is assumed to be a valid operation.
            However, NetBeans complains if I do not have this.*/
        return 0;
    }
    
    /*  Returns true if Integer.parseInt() can be evaluated without 
            throwing any exceptions
    */
    private boolean isOperand(String token) {
        /*  I originally wanted to check the token length first, but I realize
                that this would only allow operands of a single digit length.
        */
        try {
            Integer.parseInt(token);
            return true;
        }
        catch (Exception ex) 
        {   return false; }
    }
    
    /*  Returns whether the token is an operator. */
    private boolean isOperator(String token) {
        char symbol;
        
        /* If the token length is not equal to 1, then it is NOT an operator. */
        if(token.length() != 1) { return false; }
        
        else {
            symbol = token.charAt(0);
            return (symbol == ADD || symbol == SUBTRACT || symbol == MULTIPLY 
                || symbol == DIVIDE || symbol == MOD || symbol == EXPONENT);
        }
    }
}

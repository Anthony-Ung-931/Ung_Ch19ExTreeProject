package PostFix;

/**
 *  PostFixOperations
 *  Contains the operations for the PostFixEvaluator.
 *  Throws appropriate exceptions whenever an unusual operation is attempted.
 *      (i.e. Overflow/Underflow, divide by zero, etc.)
 * 
 *  @author Anthony Ung
 */
public class PostFixOperations {
    
    /*  Adds two operands. Catches overflow and underflow. */
    public static int add(int op1, int op2) throws Exception {
        int result = op1 + op2;
        
        /*  Checks whether overflow or underflow happened.
            Case 1: Adding two positive numbers and getting a negative number. 
            Case 2: Adding two negative numbers and getting a positive number.*/
        if((op1 > 0) && (op2 > 0) && (result < 0)) 
        {   throw new PostFixException("addOverflow"); }
        
        else if((op1 < 0) && (op2 < 0) && (result > 0)) 
        {   throw new PostFixException("addUnderflow"); }
        
        return result;
    }
    
    /*  I created a function to subtract the second operand from the first.
        This method catches overflow and underflow. */
    public static int subtract(int op1, int op2) throws Exception {
        int result = op1 - op2;
        
        /*  Checks whether overflow or underflow happened.
            Case 1: Subtracting a negative operand from a positive operand and 
                        getting a negative value.
                        Example: (2000000000 - (- 1000000000)) will overflow
            Case 2: Subtracting a positive operand from a negative operand and 
                        getting a positive value.
                        Example: (-2000000000 - (1000000000)) will underflow
        */
        if((op1 > 0) && (op2 < 0) && (result < 0)) 
        {   throw new PostFixException("subOverflow"); }
        
        else if((op1 < 0) && (op2 > 0) && (result > 0)) 
        {   throw new PostFixException("subUnderflow"); }
        
        return result;
    }
    
    /*  I created a function to subtract the second operand from the first.
        This method will detect overflow. */
    public static int multiply(int op1, int op2) throws Exception {
        int result = op1 * op2;
        
        /*  Checks whether overflow has happened. */
        if(result / op2 != op1) 
        {   throw new PostFixException("multOverflow"); }
        
        return result;
    }
    
    /*  I created a function to divide the first operand by the second.
            This uses integer division. (i. e. 5/2 = 2)
        This method detects attempting to divide by zero. */
    public static int divide(int op1, int op2) throws Exception {
        try { return op1 / op2; }
        
        catch(ArithmeticException ArithExcep) 
        {   throw new PostFixException("divZero"); }
    }
    
    /*  I created a function to mod the first operand by the second.
        This method detects attempting to mod by zero.*/
    public static int mod(int op1, int op2) throws Exception {
        try { return op1 % op2; }
        
        catch(ArithmeticException ArithExcep) 
        {   throw new PostFixException("modZero"); }
    }
    
    /*  Returns op1 raised to the power of op2.
        op1 and op2 are both integers.
        No support for fractional powers (i. e. negative exponents.)
            since this will result in a fraction.
    */
    public static int power(int op1, int op2) throws Exception {
        /*  Check whether you are attempting to evaluate 1^0.*/
        if((op1 == 0) && (op2 == 0)) 
        { throw new PostFixException("unDefExp"); }
        
        /*  Check whether the exponent is zero.
            Packed onto a single line because that is not the typical case.*/
        if(op2 == 0) { return 1; }
        
        else if(op2 > 0) {
            int result = 1;     // Stores the result. (op1)^0 = 1.
            int prev;           // Used to detect overflow.
            int counter = 1;    // Identiies which power is currently evaluated.
            
            for(; counter <= op2; counter++) {
                prev = result;
                result *= op1;
                
                if(result / op1 != prev) /*  Checks for overflow. */
                { throw new PostFixException("expOverflow"); }
            }
            
            return result;
        }
        /*  If op2 is negative, then a fractional exponent will require a
                double or a float to be returned to the caller.
        */
        else{ throw new PostFixException ("negExp"); }
    }
}

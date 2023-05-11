package PostFix;

/**
 *  Represents the possible exceptions that are thrown by PostFixEvaluator.
 * 
 *  Whenever an unexpected operation is encountered, 
 *      a PostFixException is thrown with the appropriate mode.
 *      Different modes cause a generic exception to be thrown with a different
 *          message that propagates back to the evaluate method in
 *          the driver class.
 *  @author Anthony Ung
 */
public class PostFixException extends RuntimeException {
    
    /**
     * Sets up this exception with an appropriate message.
     */
    public PostFixException(String mode) throws Exception
    {
        switch(mode) {
            /*  These are the exceptions that can be thrown by the 
                    PostFixEvaluator itself. */
            case("emptyLineReceived"):
                throw new Exception
                   ("""
                    The Empty Line was received.""");
            
            case("noMoreInts"):
                throw new Exception
                   ("""
                    Ran out of integers.""");
            
            case("illegalToken"):
                throw new Exception
                   ("""
                    Invalid symbol identified.""");
                
            case("manyOperands"):
                throw new Exception
                   ("""
                    Too many operands received.""");
            
            case("fewOperands"):
                throw new Exception
                   ("""
                    Too few operands received.""");
                
            /*  These are the exceptions that are thrown whenever a unusual
                    operation is identified. */
            case("addOverflow"):
                throw new Exception
                   ("""
                    Adding resulted in overflow.""");
                
            case("addUnderflow"):
                throw new Exception
                   ("""
                    Adding resulted in underflow.""");
                
            case("subOverflow"):
                throw new Exception
                   ("""
                    Subtracting resulted in overflow.""");
                
            case("subUnderflow"):
                throw new Exception
                   ("""
                    Subtracting resulted in underflow.""");
                
            case("multOverflow"):
                throw new Exception
                   ("""
                    Multiplication resulted in overflow or underflow.""");
                
            case("divZero"):
                throw new Exception
                   ("""
                    Illegal Operation encountered.
                    Divide by zero attempted.""");
                
            case("modZero"):
                throw new Exception
                   ("""
                    Illegal Operation encountered.
                    Mod by zero attempted.""");
            
            case("unDefExp"):
                throw new Exception
                   ("""
                    Illegal Operation encountered.
                    You are trying to evaluate 0^0.
                    This is mathematically undefined.""");
            
            case("expOverflow"):
                throw new Exception
                   ("""
                    Exponentiation resulted in overflow.""");
                
            case("negExp"):
                throw new Exception
                   ("""
                    Illegal Operation encountered.
                    Your postfix expression has a negative exponent.
                    Will evaluate to a fraction that is unusable.""");
        }
    }
}

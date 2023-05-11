package Stack;

/**
 * Represents the situation in which a collection is empty.
 *
 * 11 Nov 2022 - 1 PM
 * This file is unchanged from the Source Code zip.
 * 
 * @author Java Foundations
 * @version 4.0
 */
public class EmptyCollectionException extends RuntimeException
{
    /**
     * Sets up this exception with an appropriate message.
     * @param collection the name of the collection
     */
    public EmptyCollectionException(String collection)
    {
        super("The " + collection + " is empty.");
    }
}

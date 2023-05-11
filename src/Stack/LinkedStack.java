package Stack;

import Stack.EmptyCollectionException;

/**
 * Represents a linked implementation of a stack.
 *
 * I have reused my implementation from the earlier Linked Stack project.
 * 
 * @author Java Foundations 
 * @version 4.0
 */
public class LinkedStack<T> implements StackADT<T>
{
    /*  count       - Number of elements currently on stack.
        LinearNode  - The top of the stack. */
    private int count;
    private LinearNode<T> top; 

    /**
     * Creates an empty stack.
     */
    public LinkedStack()
    {
        count = 0;
        top = null;
    }

    /**
     * Adds the specified element to the top of this stack.
     * @param element element to be pushed on stack
     */
    public void push(T element)
    {
        LinearNode<T> temp = new LinearNode<T>(element);

        temp.setNext(top);
        top = temp;
        count++;
    }

    /**
     * Removes the element at the top of this stack and returns a
     * reference to it. 
     * @return element from top of stack
     * @throws EmptyCollectionException if the stack is empty
     */
    public T pop() throws EmptyCollectionException
    {
        try {
            if (isEmpty())
                throw new EmptyCollectionException("stack");

            T result = top.getElement();
            top = top.getNext();
            count--;
            return result;
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack.  
     * @return element on top of stack
     * @throws EmptyCollectionException if the stack is empty  
     */
    public T peek() throws EmptyCollectionException
    {
        try {
            if(isEmpty()) {
                throw new EmptyCollectionException("stack");
            }
            T peeked = top.getElement();
            return peeked;
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns true if this stack is empty and false otherwise. 
     * @return true if stack is empty
     */
    public boolean isEmpty()
    {
        if(top == null) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the number of elements in this stack.
     * @return number of elements in the stack
     */
    public int size()
    {
        return count;
    }

    /**
     * Returns a string representation of this stack. 
     * Displays the contents of the stack from top to bottom.
     * 
     * @return string representation of the stack
     */
    public String toString()
    {
        try {
            if(isEmpty()) {
                throw new EmptyCollectionException("stack");
            }
            LinearNode<T> currentT = top;
            String temp = "";
            do {
                temp += currentT.getElement().toString() + " ";
                currentT = currentT.getNext();
            } while (currentT != null);
            return temp;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
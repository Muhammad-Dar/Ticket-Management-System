/**
 * The QueueADT interface defines the essential operations for a generic queue abstract data type.
 * This interface provides a blueprint for implementing a queue structure, which is a fundamental
 * data structure in computer science and software development. A queue operates on the principle
 * of 'First In, First Out' (FIFO), where elements are added to the rear and removed from the front.
 *
 * This interface is generic, allowing for the creation of queues that can hold any type of object.
 * The key operations defined in this interface include:
 * - enqueue: Adds an element to the back of the queue.
 * - dequeue: Removes and returns the element at the front of the queue.
 * - peek: Retrieves, without removing, the element at the front of the queue.
 * - isEmpty: Checks if the queue is empty.
 * - size: Returns the current number of elements in the queue.
 *
 * Implementing this interface allows for the creation of various types of queues (e.g., linked
 * queue, array queue) while maintaining a consistent set of operations. This interface is
 * particularly useful in scenarios where a specific order of processing is required, such as
 * task scheduling, resource management, and data buffering.
 *
 * @param <T> the data type of the elements the queue contains
 * @author Muhammad Dar
 */


import java.util.NoSuchElementException;


public interface QueueADT<T> {
  /**
   * Inserts an element at the back of the queue
   *
   * @param newObject element to add at the back (end) of the queue
   */
  public void enqueue(T newObject);

  /**
   * Removes and returns element at the front of the queue.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public T dequeue();

  /**
   * Returns without removing element at the front of the queue.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public T peek();

  /**
   * Checks whether the queue is empty.
   *
   * @return true if the queue is empty, false otherwise
   */
  public boolean isEmpty();

  /**
   * Reports the current size of the queue.
   *
   * @return the number of elements in the queue
   */

  public int size();
}


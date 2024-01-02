/**
 * The TicketQueueIterator class serves as an iterator specifically designed for traversing
 * through a TicketQueue, a custom queue data structure managing TicketSiteUser objects. The
 * primary function of this iterator is to enable sequential access to the users in the queue
 * without altering the state of the original TicketQueue. This is achieved by operating on a
 * deep copy of the TicketQueue, ensuring that the iteration process is isolated from the original
 * queue, thereby preventing any modifications to it during iteration.
 *
 * Features and functionalities of the TicketQueueIterator include:
 * - Safe Iteration: Iterates over a deep copy of the original TicketQueue, preserving the
 *   integrity and state of the original queue.
 * - Standard Iterator Operations: Implements the standard hasNext() and next() methods as defined
 *   in the Iterator interface, providing a familiar and intuitive interface for iteration.
 * - Exception Handling: Includes robust handling of common iterator-related exceptions, such as
 *   attempting to access an element when no more elements are present in the queue.
 *
 * This iterator is particularly useful in scenarios where it is necessary to traverse a queue
 * for processing or inspection purposes without impacting the queue's original state. Such
 * scenarios might include analytics, reporting, or other read-only operations on the queue where
 * the sequence and state of the users in the queue must remain unchanged.
 *
 * Usage Note: This class should be used when there is a need to iterate over the TicketQueue
 * without modifying its contents, as it creates a separate copy of the queue for iteration.
 *
 * @author Muhammad Dar
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TicketQueueIterator implements Iterator<TicketSiteUser> {

  // deep copy of a TicketQueue
  private TicketQueue userQueue;

  /**
   * Constructor for a TicketQueueIterator that sets the data field to be a deep copy of the given
   * queue.
   * @param userQueue the queue to iterate through
   * @throws  IllegalArgumentException if the given queue is null
   * */
  public TicketQueueIterator(TicketQueue userQueue) {
    if (userQueue == null) {
      throw new IllegalArgumentException("Queue cannot be null");
    }
    this.userQueue = userQueue.deepCopy();
  }

  /**
   * Determines whether there is another TicketSiteUser in the queue.
   * @return true if there are more TicketSiteUsers in the queue, false otherwise
   */
  public boolean hasNext() {
    return !userQueue.isEmpty();
  }

  /**
   * Returns the next TicketSiteUser in the queue, based on the order from front to back.
   * @return the next TicketSiteUser in the queue
   * @throws NoSuchElementException if there are no more TicketSiteUsers in the queue
   */
  public TicketSiteUser next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more users in queue");
    }
    return userQueue.dequeue();
  }
}

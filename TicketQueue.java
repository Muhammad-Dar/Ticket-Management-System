/**
 * The TicketQueue class implements a queue data structure specifically tailored for managing
 * TicketSiteUser objects in a ticket purchasing system. It is built upon a linked list structure
 * to efficiently handle dynamic queue operations such as enqueueing (adding), dequeueing
 * (removing), and peeking (viewing the next element). The class is designed to manage user
 * access in a first-come-first-served manner, which is fundamental in ticket sale scenarios.
 *
 * Key features of this class include:
 * - Capacity Management: The queue has a maximum capacity to control the number of users it can
 *   hold at a time, preventing overloading in high-demand ticket sale scenarios.
 * - Deep Copy Functionality: The class provides a method to create a deep copy of the queue,
 *   which is essential for maintaining the integrity of the original queue in operations
 *   requiring a duplicate queue.
 * - Iterable Interface Implementation: It implements the Iterable interface, enabling easy
 *   traversal of users in the queue.
 * - QueueADT Implementation: Adhering to the Queue Abstract Data Type (QueueADT), the class
 *   ensures compliance with fundamental queue operations and principles.
 * - Exception Handling: The class is designed to handle common queue-related exceptions, such as
 *   trying to dequeue from an empty queue or enqueuing into a full queue.
 * - String Representation: Provides a clear and concise string representation of the queue's
 *   current state, aiding in debugging and monitoring.
 *
 * This class is particularly useful in systems where managing a fair and efficient processing
 * order for users is critical, such as in online ticket sales platforms. Its implementation
 * ensures that user processing follows the standard queue behavior, aligning with the expectations
 * of both the system and its users.
 *
 * @author Muhammad Dar
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TicketQueue implements QueueADT<TicketSiteUser>, Iterable<TicketSiteUser> {
  // the linked node at the front of the queue
  private LinkedNode<TicketSiteUser> front;

  // the linked node at the back of the queue
  private LinkedNode<TicketSiteUser> back;

  // the number of TicketSiteUsers in the queue
  private int size;

  // the MAXIMUM number of TicketSiteUsers that the queue can hold
  private int capacity;

  /**
   * Creates an empty queue of TicketSiteUsers with the given capacity.
   * @throws IllegalArgumentException if the capacity is less than 1
   */
  public TicketQueue(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("Capacity must be at least 1");
    }
    this.capacity = capacity;
  }

  /**
   * Reports whether this queue is full.
   *
   * @return true is the number of TicketSiteUsers is the same or more of the capacity, false
   * otherwise
   */
  public boolean isFull() {
    return size >= capacity;
  }

  /**
   * Reports the capacity of the queue.
   *
   * @return the capacity of the queue
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Changes the capacity of the queue to the new capacity. If the capacity is lowered, DO NOT
   * remove any elements. It will be considered full until enough TicketSiteUsers are dequeued by
   * the application.
   *
   * @param newCapacity the new MAXIMUM number of TicketSiteUsers this queue can hold
   * @throws IllegalArgumentException if the new capacity is less than 1
   */
  public void setCapacity(int newCapacity) {
    if (newCapacity < 1) {
      throw new IllegalArgumentException("Capacity must be at least 1");
    }
    capacity = newCapacity;
  }

  /**
   * Reports if this queue is empty.
   *
   * @return true if the queue has no TicketSiteUsers in it, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Reports the current size of the queue.
   *
   * @return the number of TicketSiteUsers in the queue
   */
  @Override
  public int size() {
    return size;
  }


  /**
   * Adds the given TicketSiteUser to the back of the queue.
   *
   * @param newObject element to add at the back (end) of the queue
   * @throws IllegalStateException    if the queue is full
   * @throws IllegalArgumentException if the TicketSite user is not able to buy a ticket.
   */
  @Override
  public void enqueue(TicketSiteUser newObject) {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    if (!newObject.canBuyTicket()) {
      throw new IllegalArgumentException("User cannot buy a ticket");
    }
    if (isEmpty()) {
      front = new LinkedNode<>(newObject);
      back = front;
    } else {
      back.setNext(new LinkedNode<>(newObject));
      back = back.getNext();
    }
    size++;
  }

  /**
   * Removes and returns the TicketSiteUser at the front of the queue.
   *
   * @return the TicketSiteUser at the front of the queu
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public TicketSiteUser dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    TicketSiteUser removed = front.getData();
    front = front.getNext();
    if (front == null) {
      back = null;
    }
    size--;
    return removed;
  }

  /**
   * Returns the TicketSiteUser from the front of the queue without removing it.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public TicketSiteUser peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return front.getData();
  }

  /**
   * Creates and returns and instance of a TicketQueueIterator for this queue.
   *
   * @return a TicketQueueIterator for this queue
   */
  @Override
  public Iterator<TicketSiteUser> iterator() {
    return new TicketQueueIterator(this);
  }

  /**
   * Creates and returns a deep copy of this TicketQueue.
   *
   * @return a deep copy of this TicketQueue
   */
  public TicketQueue deepCopy() {
    TicketQueue copy = new TicketQueue(capacity);
    LinkedNode<TicketSiteUser> current = front;
    while (current != null) {
      copy.enqueue(current.getData());
      current = current.getNext();
    }
    return copy;
  }

  /**
   * Returns a string representation of this TicketQueue in the following format: [TicketSiteUser1]
   * -> [TicketSiteUser2] -> ... -> [TicketSiteUserN] -> END
   *
   * @return the string representation of this TicketQueue
   */
  @Override
  public String toString() {
    String s = "";
    LinkedNode<TicketSiteUser> runner = this.front;
    while (runner != null) {
      s += runner.getData() + "\n";
      runner = runner.getNext();
    }
    return s;
  }
}

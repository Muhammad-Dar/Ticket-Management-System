/**
 * The LinkedNode class represents a generic node in a singly linked list structure. This class is
 * a fundamental building block for creating linked data structures, where each node maintains a
 * reference to its successor in the list. The generic nature of the class allows it to store
 * data of any specified type, making it versatile and adaptable to various use cases.
 *
 * Key characteristics of the LinkedNode class include:
 * - Data Storage: Each node holds data of type <T>, which can be any object type, allowing for
 *   flexibility in the types of data that can be stored in a linked list.
 * - Link to Next Node: Every node maintains a reference to the next node in the list, enabling
 *   the traversal and linking of nodes in a sequential manner.
 * - Constructors: Provides constructors to create a node with just data (with 'next' set to null)
 *   and another constructor to initialize a node with both data and a reference to the next node.
 *
 * Example Usage: LinkedNode<String> node1 = new LinkedNode<>("Data1", node2);
 *
 * The class also overrides the toString() method to provide a string representation of the
 * node's data and its link to the next node, aiding in debugging and visualization of the
 * structure of the list.
 *
 * @param <T> the data type that the node will store
 * @author Muhammad Dar
 */

public class LinkedNode <T> {
  /** the data of this LinkedNode*/
  private T data;
  /** the reference to the LinkedNode that comes after this LinkedNode*/
  private LinkedNode<T> next;

  /**
   * Constructor that creates a new LinkedNode object containing the given data. This node's next
   * is null.
   * @param data the data to store in this LinkedNode
   */
  public LinkedNode(T data) {this.data = data;}

  /** Constructor that creates a new LinkedNode object containing the given data and whose next is
   * the given LinkedNode.
   * @param data the data to store in this LinkedNode
   * @param next the LinkedNode that comes after this LinkedNode
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this(data);
    this.next = next;
  }

  /**
   * Getter for the LinkedNode's data.
   * @return the data of this LinkedNode
   */
  public T getData() {return this.data;}

  /**
   * Getter for the LinkedNode that follows this one.
   * @return the reference to the next LinkedNode
   */
  public LinkedNode<T> getNext() {return this.next;}

  /**
   * Sets the LinkedNode that comes next to the given LinkedNode.
   * @param next the reference to the next LinkedNode
   */
  public void setNext(LinkedNode<T> next) {this.next = next;}

  /**
   * Returns a string representation of this LinkedNode. The format of String is the data's
   * toString() value  + " ->" if there is a next OR " -> END" if there is no next.
   *
   * Ex. " nodeData ->" for a node that has a next
   *
   * @return the string representation of this LinkedNode
   */
  @Override
  public String toString() {
    return data.toString() + (this.next != null ? " -> " : " -> END");
  }
}

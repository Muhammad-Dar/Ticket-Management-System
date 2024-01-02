/**
 * The TicketQueueTester class is designed to comprehensively test the functionality of the
 * TicketQueue class and its associated operations. This includes verifying the correct
 * implementation of queue operations such as enqueue(), dequeue(), and peek(), as well as
 * ensuring the proper functioning of the TicketQueue's constructor. Additionally, this tester
 * class evaluates the behavior of the TicketQueueIterator, ensuring it accurately iterates over
 * the elements of a TicketQueue without altering its state.
 *
 * The testing methodology encompasses various scenarios to ensure robustness:
 * - Constructor Testing: Verifies that the TicketQueue is instantiated correctly, including
 *   handling of edge cases and exceptional conditions.
 * - Enqueue Operation: Assesses the ability of the TicketQueue to add new TicketSiteUser
 *   objects correctly, including checks for queue capacity limits and user eligibility.
 * - Dequeue Operation: Tests the removal of TicketSiteUser objects from the queue, ensuring
 *   the correct order of elements and handling of underflow conditions.
 * - Peek Operation: Confirms that the peek functionality provides access to the front element
 *   of the queue without removing it.
 * - Iterator Functionality: Tests the TicketQueueIterator to verify its correctness in
 *   iterating over a queue's elements, ensuring that the original queue remains unmodified.
 *
 * @author Muhammad Dar
 */

import java.util.NoSuchElementException;

public class TicketQueueTester {

  public static void main(String[] args) {
    System.out.println(runAllTests() ? "All tests passed!" : "Some tests failed.");
  }

  public static boolean runAllTests() {
    return testPeek() && testEnqueue() && testDequeue() && testConstructor() && testIterator();
  }

  /**
   * Checks the correctness of the peek() method implemented in the TicketQueue class.
   *
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testPeek() {
    boolean test1Result = false;
    boolean test2Result = false;

    // Test peek on an empty queue
    {
      TicketQueue testQueue = new TicketQueue(5);
      try {
        testQueue.peek();
      } catch (NoSuchElementException e) {
        test1Result = true;  // Passes if NoSuchElementException is thrown
      }
      System.out.println("testPeek() test1Result: " + (test1Result ? "pass" : "fail"));
    }

    // Test peek on a queue with multiple elements
    {
      TicketQueue testQueue = new TicketQueue(5);
      TicketSiteUser testUser1 = new TicketSiteUser("Michelle", "iluvtaylor",
          "2511634695123088");
      TicketSiteUser testUser2 = new TicketSiteUser("Taylor", "iluvmichelle",
          "4331802252850117");
      testUser1.login("Michelle", "iluvtaylor");
      testUser2.login("Taylor", "iluvmichelle");

      testQueue.enqueue(testUser1);
      testQueue.enqueue(testUser2);
      int sizeBeforePeek = testQueue.size(); // Check size before peek

      try {
        TicketSiteUser peekedUser = testQueue.peek();
        int sizeAfterPeek = testQueue.size(); // Check size after peek

        // Ensure it's the first user and queue size remains unchanged
        if (peekedUser.equals(testUser1) && sizeBeforePeek == sizeAfterPeek) {
          test2Result = true;
        }
      } catch (NoSuchElementException e) {
        test2Result = false;
      }
      System.out.println("testPeek() test2Result: " + (test2Result ? "pass" : "fail") + "\n");
    }

    return test1Result && test2Result;
  }

  /**
   * Checks the correctness of the enqueue() method implemented in the TicketQueue class.
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testEnqueue() {
    boolean test1Result = false;
    boolean test2Result = false;
    boolean test3Result = false;
    boolean test4Result = false;

    // Test Enqueue with Full Capacity
    {
      TicketQueue testQueue = new TicketQueue(1);
      TicketSiteUser testUser1 = new TicketSiteUser("Michelle", "iluvtaylor",
          "2511634695123088");
      testUser1.login("Michelle", "iluvtaylor");
      testQueue.enqueue(testUser1);

      if (testQueue.size() != 1) {
        return false;
      }

      try {
        TicketSiteUser testUser2 = new TicketSiteUser("Taylor", "iluvmichelle",
            "4331802252850117");
        testQueue.enqueue(testUser2);
      } catch (IllegalStateException e) {
        test1Result = true;
      }
      System.out.println("testEnqueue() test1Result: " + (test1Result ? "pass" : "fail"));
    }

    // Test Enqueue with User That Can't Buy Ticket
    {
      TicketQueue testQueue = new TicketQueue(5);
      TicketSiteUser testUser = new TicketSiteUser("Michelle", "iluvtaylor",
          "2511634695123088");
      try {
        testQueue.enqueue(testUser);
      } catch (IllegalArgumentException e) {
        test2Result = true;
      }
      System.out.println("testEnqueue() test2Result: " + (test2Result ? "pass" : "fail"));
    }

    // Test Enqueue with Multiple Elements
    {
      TicketQueue testQueue = new TicketQueue(5);
      TicketSiteUser testUser1 = new TicketSiteUser("Michelle", "iluvtaylor",
          "2511634695123088");
      TicketSiteUser testUser2 = new TicketSiteUser("Taylor", "iluvmichelle",
          "4331802252850117");
      TicketSiteUser testUser3 = new TicketSiteUser("Mark", "iluvtaylorswift",
          "6817151091969428");

      testUser1.login("Michelle", "iluvtaylor");
      testUser2.login("Taylor", "iluvmichelle");
      testUser3.login("Mark", "iluvtaylorswift");

      testQueue.enqueue(testUser1);
      testQueue.enqueue(testUser2);
      testQueue.enqueue(testUser3);

      if (testQueue.size() == 3 && testQueue.peek().equals(testUser1) && testQueue.dequeue()
          .equals(testUser1)) {
        test3Result = true;
      }
      System.out.println("testEnqueue() test3Result: " + (test3Result ? "pass" : "fail"));
    }

    // Test enqueue into an empty queue
    {
      TicketQueue testQueue = new TicketQueue(5);
      TicketSiteUser testUser = new TicketSiteUser("Michelle", "iluvtaylor",
          "2511634695123088");
      testUser.login("Michelle", "iluvtaylor");

      testQueue.enqueue(testUser);

      if (testQueue.size() == 1 && testQueue.peek().equals(testUser)) {
        test4Result = true;
      }
      System.out.println("testEnqueue() test4Result: " + (test4Result ? "pass\n" : "fail"));
    }

    return test1Result && test2Result && test3Result && test4Result;
  }

  /**
   * Checks the correctness of the dequeue() method implemented in the TicketQueue class.
   *
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testDequeue() {
    boolean test1Result = false;
    boolean test2Result = false;
    boolean test3Result = false;

    // test dequeue on empty queue
    {
      try {
        TicketQueue testQueue = new TicketQueue(5);
        testQueue.dequeue();
      } catch (NoSuchElementException e) {
        test1Result = true;
        System.out.println("testDequeue() test1Result: " + (test1Result ? "pass" : "fail"));
      }
    }

    // test dequeue on queue with one element
    {
      TicketQueue testQueue = new TicketQueue(5);
      TicketSiteUser testUser = new TicketSiteUser("Michelle", "iluvtaylor",
          "2511634695123088");
      testUser.login("Michelle", "iluvtaylor");

      testQueue.enqueue(testUser);
      if (testQueue.dequeue().equals(testUser) && testQueue.isEmpty()) {
        test2Result = true;
        System.out.println("testDequeue() test2Result: " + (test2Result ? "pass" : "fail"));
      }
    }

    // test dequeue on queue with multiple elements
    {
      TicketQueue testQueue = new TicketQueue(5);
      TicketSiteUser testUser1 = new TicketSiteUser("Michelle", "iluvtaylor",
          "2511634695123088");
      TicketSiteUser testUser2 = new TicketSiteUser("Taylor", "iluvmichelle",
          "4331802252850117");
      TicketSiteUser testUser3 = new TicketSiteUser("Mark", "iluvtaylorswift",
          "6817151091969428");
      testUser1.login("Michelle", "iluvtaylor");
      testUser2.login("Taylor", "iluvmichelle");
      testUser3.login("Mark", "iluvtaylorswift");

      testQueue.enqueue(testUser1);
      testQueue.enqueue(testUser2);
      testQueue.enqueue(testUser3);

      if (testQueue.dequeue().equals(testUser1) && testQueue.peek().equals(testUser2)) {
        test3Result = true;
        System.out.println("testDequeue() test3Result: " + (test3Result ? "pass\n" : "fail"));
      }
    }

    return test1Result && test2Result && test3Result;
  }

  /**
   * Checks the correctness of the TicketQueue's constructor, including case(s) where it should
   * throw exceptions. Also checks the correctness of isEmpty(), isFull(), size(), capacity(), and
   * toString() on a newly created TicketQueue.
   *
   * @return true if the tests passed, false otherwise
   */

  public static boolean testConstructor() {
    boolean test1Result = false;
    boolean test2Result = false;

    // test constructor with zero capacity
    {
      try {
        TicketQueue testQueue = new TicketQueue(0);
      } catch (IllegalArgumentException e) {
        test1Result = true;
        System.out.println("testConstructor() test1Result: " + (test1Result ? "pass" : "fail"));
      }
    }

    // test constructor with normal input
    {
      TicketQueue testQueue = new TicketQueue(5);
      if (testQueue.capacity() == 5 && testQueue.isEmpty() && !testQueue.isFull() &&
          testQueue.size() == 0 && testQueue.toString()
          .equals("")) {
        test2Result = true;
        System.out.println("testConstructor() test2Result: " + (test2Result ? "pass\n" : "fail"));
      }
    }
    return test1Result && test2Result;
  }

  /**
   * Checks the correctness of the TicketQueueIterator method(s) and iterating through a
   * TicketQueue. See write-up for more details on how to write this test. You DO NOT need to test
   * if the TicketQueueIterator constructor throws an exception when the queue parameter is null.
   *
   * @return true if the tests passed, false otherwise
   */
  public static boolean testIterator() {
    boolean iteratorTestPassed = true;
    int numberOfIterations = 0;

    TicketQueue testQueue = new TicketQueue(5);
    TicketSiteUser testUser1 = new TicketSiteUser("Michelle", "iluvtaylor",
        "2511634695123088");
    TicketSiteUser testUser2 = new TicketSiteUser("Taylor", "iluvmichelle",
        "4331802252850117");
    TicketSiteUser testUser3 = new TicketSiteUser("Mark", "iluvtaylorswift",
        "6817151091969428");
    testUser1.login("Michelle", "iluvtaylor");
    testUser2.login("Taylor", "iluvmichelle");
    testUser3.login("Mark", "iluvtaylorswift");

    testQueue.enqueue(testUser1);
    testQueue.enqueue(testUser2);
    testQueue.enqueue(testUser3);

    TicketSiteUser[] expectedUsers = {testUser1, testUser2, testUser3};

    int i = 0;
    for (TicketSiteUser user : testQueue) {
      if (!user.equals(expectedUsers[i])) {
        iteratorTestPassed = false;
        break;
      }
      i++;
      numberOfIterations++;
    }

    if (numberOfIterations != testQueue.size()) {
      iteratorTestPassed = false;
    }

    String expectedString = "Michelle: *\n" + "Taylor: *\n" + "Mark: *\n";

    if (!testQueue.toString().equals(expectedString)) {
      iteratorTestPassed = false;
    }

    System.out.println("testIterator() testResult: " + (iteratorTestPassed ? "pass\n" : "fail"));

    return iteratorTestPassed;
  }


}

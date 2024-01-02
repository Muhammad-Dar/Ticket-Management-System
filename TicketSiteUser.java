/**
 * The TicketSiteUser class represents a user within a ticket buying platform. It encapsulates
 * key details about the user, including their login credentials, ticket ownership, and payment
 * information. This class provides essential functionality for user authentication, ticket
 * purchasing, and user state management within the context of a ticket selling website.
 *
 * Key features of the TicketSiteUser class include:
 * - Credential Management: Manages user credentials, including username and password, with
 *   passwords securely hashed for enhanced security.
 * - Ticket Handling: Maintains information about the user's ticket, allowing them to purchase
 *   and retrieve ticket details.
 * - Login Status: Tracks whether the user is currently logged in, ensuring secure and
 *   authorized access to functionalities like ticket purchasing.
 * - Payment Information: Securely stores the user's card number in a hashed form for
 *   transactions while maintaining privacy and security.
 *
 * This class provides crucial methods for user-related actions, such as logging in and out,
 * checking ticket ownership, and buying tickets. The implementation ensures that sensitive
 * information like passwords and card numbers are securely handled using SHA-256 hashing,
 * reflecting best practices in secure software design.
 *
 * The class is designed to be utilized in systems that require a robust and secure user
 * management mechanism, particularly in online ticket selling platforms. It upholds the
 * principles of data encapsulation and security, making it a reliable component in handling
 * user interactions in ticket purchasing scenarios.
 *
 * Usage Note: It is essential to ensure that the card number provided is 16 digits long as
 * per the constructor's requirement. Also, the class assumes the existence of a Ticket class
 * for managing ticket information.
 *
 * @author Muhammad Dar
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
public class TicketSiteUser {
  private String password; // the password for this user
  //not stored in plaintext, we hash it for security reasons
  private String username; //the username of this user
  private boolean isLoggedIn; //the status of if they are logged in or not
  private Ticket ticket;  // the ticket that belongs to this site user
  private String cardNumber; //the card number the user uses for purchases
  //not stored in plaintext, we hash it for security reasons

  /**
   * Creates a new TicketSiteUser with the given username, password, and card number. They do
   * NOT have a ticket and are NOT logged in.
   * @param username the username of this user
   * @param password the password of this user
   * @param cardNumber the card number for this user to use for purchases
   * @throws IllegalArgumentException if the card number is not 16 digits long
   */
  public TicketSiteUser(String username, String password, String cardNumber) {
    if(cardNumber.length() != 16)
      throw new IllegalArgumentException("Card number must be 16 digits long.");
    this.username = username;
    this.password = hashString(password);
    this.cardNumber = hashString(cardNumber);
  }


  /**
   * Applies the SHA-256 hashing protocol to encrypt the given string.
   * @param str the String to hash
   * @return the cipher text of the String
   */
  //code adapted from //https://www.geeksforgeeks.org/sha-256-hash-in-java/
  private String hashString(String str) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256"); // get the type of hash protocol
      byte[] hash = md.digest(str.getBytes()); // apply the SHA-256 protocol to the string
      BigInteger num = new BigInteger(1, hash);
      str = num.toString(16); // convert it to be represented in hexadecimal

      // pad out the hashedPassword with zeros if it is too short
      while (str.length() < 32) {
        str = "0" + str;
      }
    } catch (NoSuchAlgorithmException e) { // if "SHA-256" isn't a supported algorithm, catch the
      // exception, it is a checked one
      System.out.println("not a valid hash algorithm!");
    }

    return str;
  }

  //changes logged in status of user if "entered" username and password match
  /**
   * Logs in the user if the given username and password match.
   * @param username the username to try to login with
   * @param password the password to try to login with
   * @return true if the username and password are correct, false otherwise
   */
  public boolean login(String username, String password){
    if(!this.password.equals(hashString(password)) || !this.username.equals(username))
      return false;

    this.isLoggedIn = true;
    return true;
  }

  /**
   * Reports whether or not this user already has purchased a ticket.
   * @return true if the user has a ticket, false otherwise
   */
  private boolean hasTicket() {return this.ticket != null;}

  /**
   * Reports if the user is allowed to buy a ticket.
   * @return true if they don't have a ticket AND are logged-in, false otherwise
   */
  public boolean canBuyTicket() {
    return !hasTicket() && this.isLoggedIn;
  }

  /**
   * Logs out the user from the site.
   */
  public void logout() {this.isLoggedIn = false;}

  /**Gets the ticket that this TicketSiteUser has
   *
   * @return the ticket that belongs to this user
   */
  public Ticket getTicket() {return this.ticket;}

  /**
   * Gives the user the ticket they just bought.
   * @param ticket the ticket they bought.
   */
  public void buyTicket(Ticket ticket) {this.ticket = ticket;}

  /**
   * Returns a string representation of this TicketSiteUser in the following format:
   *   If the user has no Ticket - [username] + ": *"
   *   If the user has a Ticket - [username] + ": " + [ticket.toString()]
   *
   * Ex. "taylorswiftluver43: *"
   */
  @Override
  public String toString() {
    return this.username + ": " + (this.ticket == null ? "*" : ticket.toString());
  }
}

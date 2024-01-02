/**
 * The Ticket class represents an individual ticket for a concert or event, encapsulating
 * information about the event, venue, seating, and price. Each ticket instance corresponds
 * to a specific seat at a particular event, thereby making it a crucial element in the
 * management of event attendance and seating arrangements.
 *
 * Core attributes of the Ticket class include:
 * - Event Information: Stores the name of the event, providing clear identification of the
 *   concert or show the ticket is associated with.
 * - Venue Details: Specifies the venue or location where the event is held, helping in
 *   navigating the event space.
 * - Seating Information: Contains the section and seat number, indicating the exact location
 *   of the seat within the venue.
 * - Pricing: Records the cost of the ticket, which is crucial for transaction and billing
 *   purposes.
 *
 * Through its constructor, the Ticket class allows for the creation of ticket objects with
 * comprehensive details, making it a fundamental component in ticketing systems, particularly
 * for concerts, theaters, and similar events. The class also provides a neatly formatted string
 * representation of the ticket, combining all its details, which can be used for display,
 * receipt generation, or logging purposes.
 *
 * This class plays a vital role in systems where event management and ticketing are core
 * functionalities, such as in online ticket booking platforms, event management software, or
 * venue reservation systems. Its design focuses on capturing all relevant details about a
 * ticket in a concise and organized manner.
 *
 * Example Usage: Ticket concertTicket = new Ticket("Taylor Swift Eras Tour", "Madison Square
 * Garden", "A", "5", 425.46);
 *
 * @author Muhammad Dar
 */
public class Ticket {
  private String eventName; //the name of the event this ticket is for
  private String venue; //the venue (location) where the ticket is for
  private String section; //the section name for the seat the ticket is for
  private String seatNumber; //the number for the seat the ticket is for
  private double price; //the cost of the ticket

  /**
   * Constructor for a new Ticket object. Assigns the given values to their respective data fields.
   * @param eventName the event that this ticket is for
   * @param venue the venue where the event is for this ticket
   * @param section the section name for where the seat is
   * @param seatNumber the number for the ticket's seat
   * @param price the price of the ticket
   */
  public Ticket(String eventName, String venue, String section, String seatNumber, double price) {
    this.eventName = eventName;
    this.venue = venue;
    this.section = section;
    this.seatNumber = seatNumber;
    this.price = price;
  }

  /**
   * Returns a string representation of this Ticket. The format of String is
   * [eventName] + " @" + [venue] + " " + [section] + ":" + [seatNumber] + " - $" + [price]
   *
   * Ex. "Taylor Swift Eras Tour @Madison Square Garden A:5 - $425.46"
   *
   * @return the string representation of this Ticket
   */
  @Override
  public String toString() {
    return eventName + " @" + venue + " " + section +":" + seatNumber + " - $" + price;
  }


}


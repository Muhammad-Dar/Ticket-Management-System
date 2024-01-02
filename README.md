# Ticket Management System

## Project Overview

This project is a comprehensive ticket management system designed for concert and event ticketing platforms. It encapsulates various classes and interfaces to handle user management, ticketing, and queue operations. This system is ideal for managing user access, ticket purchasing, and user interactions in an online ticketing environment.

### Key Components

- **TicketQueue**: A custom queue implementation managing `TicketSiteUser` objects, designed to handle user access in a first-come-first-served manner. It includes features like capacity management and deep copy functionality, ensuring efficient and fair user processing.
  
- **TicketQueueIterator**: An iterator for safely iterating through the `TicketQueue` without modifying the original queue. This component is essential for traversing the queue for read-only operations like analytics and reporting.

- **TicketSiteUser**: Represents a user of the ticketing system. This class manages user login credentials (with password hashing for security), ticket ownership, and payment information. It also includes methods for user authentication, ticket purchase, and status checks.

- **Ticket**: An object representing an individual ticket, containing details such as event name, venue, seating information, and price. Each ticket instance corresponds to a specific seat at an event, crucial in event attendance and seating management.

- **QueueADT**: A generic interface defining essential operations for a queue abstract data type. It provides a blueprint for implementing different types of queues while ensuring a consistent set of operations.

- **LinkedNode**: A generic class representing a node in a singly linked list. This class is fundamental for creating dynamic data structures like stacks, queues, and linked lists.

### Features

- Efficient user management for ticketing systems.
- Secure handling of user credentials and payment information.
- Dynamic queue system for fair and orderly processing of ticket purchases.
- Custom iterator for safe traversal of user queues.
- Generic queue and linked node implementations for versatile use in various data structures.

### Usage

This system is particularly useful in online ticket selling platforms, event management software, or venue reservation systems. Its robust implementation of user and ticket management functionalities makes it a reliable and secure choice for handling ticketing operations.


# T3Online

### Application/Problem Statement

I have been thinking about how games work over IP, and I have always been interested in creating an activity
that two people can participate in real time from different locations (or at least different hosts).
Hence, I  chose to implement a simple networked TictacToe game as a basis for this activity. In this activity,
a player an start a TicTacToe and be matched randomly with  remote opponent.

In the future , I hope to expand this activity to a more complex one. As a result I am looking at making the architecture as flexible and scalable as I can.

![Home ScreenShots]

![Game ScreenShots]

### Project Technologies/Techniques 
* Security/Authentication
  * All users have to register/login to be aple to start or particpate in game
  * Any one can view the leader boards
  * JSTL form and spring validation to validate form entries

* Database (MySQL and Hibernate)
  * Store users and roles
  * Store game info/status
  
* Web Services or APIs
  * PayPal APIs for users willing to donate
  * Jersey Rest SSE ApiS for sending and receiving game date between players asynchronously


* Logging
  * All info, warning and errors to be logged using log4j libraries
  
* Site and database  to be hosted on Digital Ocean

* Unit Testing
  * JUnit tests to achieve 70% code coverage
  * Test Driven Developent as much as possible
  
* Independent Research Topics
  * Spring MVC
  * JSTL Forms and Data Binding
 
### Design

* [Screen Design] (images/)
* [Application Flow] (images/T3FlowChart.pdf)
* [Database Design] (images/TicDB.pdf)



### [Time Log](TimeLog.md)

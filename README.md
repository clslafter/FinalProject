# Interview Level-up

## Final Team Project for Skill Distillery

### Team members and roles:

* Ethan Lauzon (Developer, DBA)
* Josh Ingram (Developer, Scrum Master)
* Celicia Slafter (Developer, Repo Owner)

## Overview
This web based application provides users access to job interview questions and answers.   

## Description  
  * Without being logged in, the user can:
      * access the welcome page
      * create an account

  * Once logged in, the user can:
      * create a question
      * update their questions
      * read answers
      * create an answer
      * update their account and profile details
      * up/down vote answers
      * comment on answers
      * logout to complete their session
      * delete their account
      ADD MORE HERE


  * Question information includes:
      * description
      * username of the creator
      * date created
      * date last edited
      * category(s)
      * companies that have asked this question

  * Answer information includes:
      * the answer text
      * the username of the creator
      * date created
      * date last edited
      * the current rating

  * Company information includes:
      * name
      * description
      * logo
      * address
      * a list of questions


  * A user's personal profile page shows account details including:
      * about me
      * date created
      * questions created, with links
      * answers created, with links

  * Users with administrator roles can:
      * disable users
      * reinstate users
      * remove user submitted content (questions, answers, comments, companies, etc.)

Welcome Page:
![Welcome page](https://github.com/clslafter/FinalProject/blob/main/Welcome%20page%20screen%20shot.png)

Sample Question Detail Page:
![Sample question detail page](image link here)

## Implementation

We began this project by putting our ideas into a Trello board in the form of discreet user stories following a CRUD outline and then adding bullet points for each user story into a checklist. Concurrently, we also used a Figma wireframe to outline the individual web page functions and connections.

Following that, we created and populated the database tables and relationships through MySql Workbench and built the foundation of the project with Beans using test-driven development.

After the foundation was created, we alternated between splitting the work and swarming to develop the CRUD for the Question, Answer, User, and Answer Comment entities.  To prevent overwhelming the site with user-created check box options, entities such as Question Category and Company Industry have limited CRUD from the user's perspective.  

Once we had a minimum viable product in terms of functionality, we implemented a few stretch goals, then designed and styled the individual pages using CSS and Bootstrap.

ER DIAGRAM:
![Photo of ER diagram](https://github.com/clslafter/FinalProject/blob/main/DB/interviewquestion.png)

## Technologies and Methodologies Used:
### Technologies/Frameworks:
  * Java
  * MySQL
  * HTML
  * Angular
  * CSS
  * SQL
  * MySQL Workbench
  * Atom
  * Bootstrap
  * Unix Terminal
  * Spring
  * Spring Boot
  * Spring Data
  * JPA
  * Spring Tools Suite
  * Visual Studio Code
  * Chrome
  * git/gitHub
  * Slack
  * Zoom  
  * Trello
  * Figma

### Methodologies:
  * Pair Programming
  * Agile
  * Kanban
  * git collaboration

## Lessons Learned:

//Fill this out

## Stretch Goals Implemented:
  * // Add stretch goals here
  *

## How to Download and Run
Go to:
// link to an EC2 deployment

Guests can create their own account or log in as:
* username: // give user name
* password: // give password

Have a look around!

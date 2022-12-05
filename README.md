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
      * create, update and delete a question
      * create, update and delete an answer
      * create, update and delete a comment
      * create and update a company
      * read answers and comments from other users
      * update their account and profile details
      * up/down vote answers
      * logout to complete their session
      * delete their account

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

  * Answer Comments:
      * the comment text
      * the username of the creator
      * date created

  * Company information includes:
      * name, description, address, industry
      * logo
      * a list of questions
      * Google map displaying its location


  * A user's personal profile page shows account details including:
      * personal details such as about me and date created
      * questions created, with links
      * answers created, with links

  * Users with administrator roles can:
      * disable users
      * reinstate users
      * remove user submitted content (questions, answers, comments, companies, etc.)

Welcome Page:
![Welcome page](https://github.com/clslafter/FinalProject/blob/main/Welcome%20page%20screen%20shot.png)

Sample Question List Page:
![Sample question list page](https://github.com/clslafter/FinalProject/blob/main/Question%20list%20screen%20shot.png)

## Implementation

We began this project by putting our ideas into a Trello board in the form of discreet user stories following a CRUD outline and then adding bullet points for each user story into a checklist. Concurrently, we also used a Figma wireframe to outline the individual web page functions and connections.

Following that, we created and populated the database tables and relationships through MySql Workbench and built the foundation of the project with Beans using test-driven development.

After the foundation was created, we mostly split the work to develop the CRUD for the Question, Answer, User, Answer Comment, and Company entities.  To prevent overwhelming the site with user-created check box options, entities such as Question Category and Company Industry have limited CRUD from the user's perspective.  

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
  * Bootstrap/BootstrapNG
  * Unix Terminal
  * Spring
  * Spring Boot
  * Spring Data
  * Postman
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
  * Ranking Tracker required use of nested ternaries  
  * To edit Many-to-Many relationships with checkboxes required an array of all of one entity and another boolean array of the same size.
  * Authenication, making use of AntMatchers to control access to REST endpoints
  * Use comments at beginning and end of complicated code sections
  * Documentation is your friend - but be sure it's the correct version
  * Composite Key tables require an ID entity that serves as composite key entity's ID
  * Join and inverse join tables - the ID of the current entity is the join table, the other entity is the inverse join
  * JsonIgnoreProperties can have multiple properties
  * Have an end design/layout in mind in the beginning

## Stretch Goals Implemented:
  * Answer Comment
  * Companies have industries
  * Keyword searches on questions and companies
  * Companies and Users have avatars/logos
  * Google map integration


## How to Download and Run
Go to: http://54.201.192.182:8080/InterviewQuestions/

Guests can create their own account or log in as:
* username: user
* password: visitor

Have a look around!

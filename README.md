# GameRate

## Project Overview

GameRate is a web application created for a university Software Engineering course. It has the functionality to allow users to rate, review, and view a repository of video games.  

The application is developed in Java using Spring Framework. The relational database system used is PostgreSQL, and the frontend is built with HTML and Bootstrap classes. The project is developed in the MVC (Model-View-Controller) architectural style. Functionalities include user login, browsing a collection of games by platform or genre, and interacting with the users' individual profile pages that contain his or her game reviews.

### Contributors

-  Justin Tom
-  Angel Bravo
-  Matthew Rivera

## Itemized Summary of Contributions

### Implementation:

-  Backend: Manages routing, logic, and all CRUD operations on user and game entities using Java and the Spring Framework.
-  Database: Game information, user accounts, reviews, ratings, and list management are stored in PostgreSQL.
-  Frontend: Responsive design with mobile friendly features is accomplished using HTML with Bootstrap. Non-static components such as navigation, filter dropdowns, pagination, and other elements are added.
-  User registration, login/logout, password hashing, and session persistence are controlled.
-  Custom reviews alongside ratings from 1 to 10 are provided by users on a relational basis.
-  Personal game lists and ratings are dynamically rendered in profile pages.

### Features Implemented

-  Authenticating the User (creating user sessions, validations included)
-  Browse Games Directory (filter games by genres and platforms)
-  View Information of Particular Game (display game information such as the title, genres, and platforms the game is playable on)
-  Review and Rate System (users can rate and review the game; this is linked to user and the game) 
- Profile Pages Listing Users Rating History

## Next Phase: Prioritized Tasks

-  Add sophisticated admin tools and content moderation for flagging
-  Incorporate user notification features that activate upon specific user interactions 
-  Integrate Google to allow OAuth2 for login authentication


## Future Development

-  Incorporate analytics dashboards for admin accounts (user acquisition, review dynamics).
-  Consider techniques that would incorporate gamification (achievements) into the system to boost user participation.
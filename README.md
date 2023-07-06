# Video Game Reviews Platform

This project is basically a social network (Metacritic Clone) for people who like games where users can upload reviews of their favorite games, rate this games or
comment and interact with other users. Also everyone who visit the page can search for games information.

## Stack used

Backend : 
* Spring Boot 3.0
* Python Scrapy
* MySQL

Frontend: React (soon...)

### Explanation of the process for making the project

I used Python Scrapy to scrape information about all the video games from the Best Games of all time section of Metacritic's website.
I then manipulated this data to generate a JSON file that is used to insert all the games information into a database using Springboot Rest API.
After getting the database filled with this information and created request to get this information i set up using Spring Boot Security an account service
so people can create their profiles and use all the features provided in the application.

### Features
* Get Games information paginated and filtered 
* Register and login Users using JWT and encripted passwords
* Modify your own Profile so people get to know who you are and what games you like
* Creating Reviews of your favorite games
* Make comments about other people reviews and start conversations
* Like reviews that seems interesting for you
* Admin control for future features


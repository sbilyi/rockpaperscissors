Background

You are a senior developer at ACME Games an online casual gaming company. The company has more than 1 million daily active users playing games online. The site however does not have a game of rock, paper, scissors. This is now the most requested game by the users of the site. The CTO of the company wants you to develop the game and deploy it to production.

Task

Write a program that plays Rock, Paper, Scissors against a human. Try to exploit that humans are very bad at generating random numbers.
You are only required to code the server-side components.

Deliverable

1.	The assignment should be delivered as a web application that allows the user to start the game, make moves, terminate the game and observe the statistics. A user interface is not expected.

2.	This is an open assignment in terms of how you structure the solution. You will be judged on the overall quality of the code (simplicity, presentation, performance).


```sh
$  curl -X POST http://localhost:8080/api/game/sergii/PAPER
  {  
     "id":4,
     "userId":"sergii",
     "userMove":"PAPER",
     "systemMove":"PAPER",
     "winner":"NONE"
  }
$  curl -X GET http://localhost:8080/api/game/sergii
  {  
     "gamesNumber":1,
     "userWins":0.0,
     "systemWins":0.0,
     "noneWins":1.0,
     "games":[  
        {  
           "id":1,
           "userId":"sergii",
           "userMove":"ROCK",
           "systemMove":"ROCK",
           "winner":"NONE"
        }
     ]
  }
```

To access db we need to enter mysql image via docker execute command
```sh
$ docker exec -it rock-paper-scissors-mysql "/bin/bash"
$ docker exec -it rock-paper-scissors-mysql sh -c 'exec mysql -uroot -p"$MYSQL_ROOT_PASSWORD" rock-paper-scissors'
```

To run with in memory db
```
$ java -jar -Dspring.config.location=classpath:inmemory-application.properties target/rock-paper-scissors-1.0-SNAPSHOT.jar
```
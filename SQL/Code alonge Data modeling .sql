SELECT 
  CONCAT(Actor.FirstName, ' ', Actor.LastName) AS ActorName,
  Movie.Title AS MovieTitle,
  MovieActor.Role
FROM MovieActor
JOIN Actor ON MovieActor.ActorID = Actor.ActorID
JOIN Movie ON MovieActor.MovieID = Movie.MovieID;



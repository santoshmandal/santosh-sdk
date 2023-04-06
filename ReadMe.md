## How to build
Go to project root folder and run the maven command
    
    mvn clean install 
    
    To run locally
    java -jar target/movies-test-0.0.1-SNAPSHOT.jar

## Docker Image Build
    docker build --tag=movies-test:latest .

## Docker Image Run
    docker run -p 8081:8081 movies-test:latest

## API Usage
    # Access the API using following urls     
    curl --location --request GET 'http://localhost:8081/moviestore/v1/movie'
    GET localhost:8081/moviestore/v1/movie
    Response: Return list of Lord of the ring movies.

    curl --location --request GET 'http://localhost:8081/moviestore/v1/movie/{id}'
    GET localhost:8081/moviestore/v1/movie/{id}
    Query Parameter: Need to send as part of the URL
    Response: 
        1) For correct movieId sends the complete response
        2) For missing or incorrect movie ID  it sends 404 status code

    curl --location --request GET 'http://localhost:8081/moviestore/v1/movie/{id}/quote'
    GET localhost:8081/moviestore/v1/movie/{id}/quote
    Query Parameter: Need to send as part of the URL
    Response:
    1) For correct movieId sends the quote lists
    2) For missing or incorrect movie ID  it sends 404 status code

## SDK Usage
    Access the MoviesRestClient in your code and perform the above mentioned API operations
    Available metods
    1) List<Movie> getAllMovies() - Returns all movies
    2) Movie getMovieById(String movieId) throws MovieRestClientException - Return movie if available otherwise through exception with status code 404
    3) List<Quote> getAllQuotesByMovie(String movieId) throws MovieRestClientException - Return movie quotes if available otherwise through exception with status code 404

## H2 database access
    URL: http://localhost:8081/h2-console/
    Username: sa password: (empty)
    JDBC Url: jdbc:h2:mem:moviesdb
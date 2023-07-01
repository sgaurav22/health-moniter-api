# health-moniter-api is java based REST API 
# You should install Java, Maven and Docker on system 
#To use this api do the following steps.

1. Your docker Postgres database should be running. 
2. The app used Redis cahche and to start it use folowing command.
    cmd => docker run -p 6379:6379 -p 8001:8001 redis/redis-stack
3. Use maven to create a jar use => mvn clean install
  by going to health-moniter-api folder.
4. To start REST API app use java -jar clipboard-api-0.0.1-SNAPSHOT.jar 
5. Once app is up use below url to access swagger page to see the endpoints.
  URL : http://localhost:8080/swagger-ui/index.html
6. This url needed to be authenticated using
  user : clipboard & pwd : health

7. I have added some performance snapshots of app its available in resources/performance folder.
8. I have written a write up to further improve the performnce in resources/performance/performnce_impove_readme.md file.

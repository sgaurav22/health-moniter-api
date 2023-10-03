# To further improve the application performance we can use following steps. 

1. Use microservice architecture and spin up multiple instance of the application. 
2. Use load balancer to handle the request follow towards the application.
3. We can create database as master-slave architecture. The master will be used for new records entry
    and slave nodes will be used for get queries with replica in place.
4. Distributed cache mechanism Redis is used here to improve the throughput and latency.
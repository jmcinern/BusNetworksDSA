																												CSU22012 Algorithms and Data Structures Design Document
																																			Joseph McInerney

Introduction
	
	-In this document I will go through the decisions  that I made along the way in doing this project. I will discuss the various data structures and algorithms that 
I used in my attempt to provide the desired functionality.

Desired Functionality
	
	-There were three core functions that were to be implemented for this assignment. The first being functionality that allows a client to search between two bus stops 
	in the network and have the shortest path between them returned to them. The second piece of functionality to be implemented in this assignment was to allow the client 
	to search for a stop by the stops name. And the final function to be implemented was a search function that would allow the client to search for stop information based
	on arrival time.

Shortest Path between Two Stops

-For this part of the assignment, I attempted to implement a shortest path algorithm that would find the shortest path between to stops in the bus network. 
The type of graph that I had to navigate was a directed edge weighted graph with cycles.
Choosing Dijkstra was a process of elimination of the other shortest path algorithms that are availible. I couldn’t use topological sort as the graph contained cycles. 
I couldn’t use any of the algorithms that required heuristics as I didn’t have access to one. BFS doesn’t account for cost and DFS doesn’t guarantee shortest path.
This meant I was left to choose between Floyd-Warshall and Dijkstra’s algorithms. I chose Dijkstra as it was the more optimal solution. Floyd Warshall requires more 
memory space for computation as it computes the shortest path between all pairs of vertices whereas Dijkstra is just from one vertex to the rest. I also decided to 
implement a priority queue for efficiency purposes.

Stop Name Search Functionality

-In this part of the assignment, used a ternary search tree (TST) as was required in the assignment specification. In my TST is used the stop ID as the key and the stop 
name as the value. I then created a Hashmap with a key value pair for each stop, The key value pair was the stop ID and the other information pertaining to the stop. 
Think about this like a game of dominoes. The stop name is connected to the stop ID and the stop ID is also connected to the stop info. 
This allowed me to return all relevant stop information just from the users input.

Arrival Time Search

-In this part of the system, the client inputs a time of the day, and the program must output all of the trips that arrive at that time. The method I used to complete 
this was not very efficient . I essentially ran through the whole file that had stop information and arrival times, and if the arrival time of the current stop 
matched the time that the user inputted. Then I printed to the screen all relevant information pertaining to the stop.

User Interface
-Another specification of the design was to implement an interface for the client to test the programs. I did this in a simple way, allowing the client to pick which 
part of the program to test or to exit. It also delt with input errors.




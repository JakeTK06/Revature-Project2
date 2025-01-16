# Sprint Retrospective  
##### (Kevin Cabral, David Ho, Jacob Kurkowski, Harsh Patel) 
## What went well 
#### Steadiness: 
We had no issues with falling behind in our work and kept a good pace with building our integration API and unit testing. We accomplished this by engaging in pair programming to aid each other when an individual’s assigned duty was completed. By utilizing GitHub, we were able to quickly push each person’s work effectively and without conflicts. In our meetings we went through what was completed the day prior and what still needed to be done to streamline the allocation of tasks. 
#### Proactive: 
We kept the call going for the entire duration of the work period so we could quickly address any issues that sprung up and made sure everybody was on the same page. By sharing our screens on Teams, we can directly see and solve the problem much faster than messaging or just announcing it vocally. If an issue could not be resolved by the team, we contacted our shareholder promptly for guidance. 
#### Competency: 
In general, our team had a good understanding of how to implement and write the tests needed.  
We were able to figure out and use Newman to generate a test report and add API tests to Jira. 

## What can be improved for the next Sprint? 
While our team was cohesive and well organized throughout the project, we did not make use of Jira to its full potential. We could have used Jira to assign tasks to individuals and keep everything sorted accordingly. Had there been some conflicts, we would have been able to resolve them much more efficiently with the use of Jira.  

Originally, we had planned that the API testing in postman would be a simple task, but we ended up spending much more time than we had planned. We kept running into issues with authentication and making sure we logged in before sending a request to avoid receiving a 500-client-side error. Thankfully, we had budgeted extra time and did not fall behind. In the future, we should do a quick run-through of each layer to ensure we are adequately prepared to handle such issues.  

## What work still needs to be done for next Sprint
There are defects that still need fixing. Some HTTP responses are returning the wrong status codes. The service layer implementation for the planet and moon returns the wrong data type when testing according to the MVP requirements. There are still many SQLite constraints check exceptions thrown both in the API and unit testing.  

Story points need updating and knowning what we know now about how the repoistory layer defects tend to bubble up we would place a weight on story points assigned at lower levels.

 We would also like to perform some more exploratory testing to find more edge cases, because we know testing only reveals defects not their absence. 

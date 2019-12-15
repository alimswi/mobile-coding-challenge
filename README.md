# mobile-coding-challenge

The application loads the most trending github repositories for the last 30 days.
Github api is consumed using FUEL library
Github users images are loaded using picasso library
Coroutines are used in order to handle the async call of the github api and to load data and display them in the application
Each repository line ( card view) contains Repository name and description,Owner Image and name, and the number of star ratings 
for the repository.
In later stage, paging will be added in order to enhance the application data loading and reduce broadband consumption

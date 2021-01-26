# Meta DataPoint API for Getting Hourly and Location Specific Data Feeds

 Introduction: <br />
  This repository contains the scenarios to access the MetaData API's for generating hourly and location specific forecast feeds.
 
 Pre-requisites : <br />
   1. Register for an authorisation token from https://www.metoffice.gov.uk/services/data/datapoint/getting-started and once you redister successfully a key will be generated 
      under MyAccount section
   2. Copy the api-key under "apikey.properties" file located under project src directory (/test/resources)
   
 Get the Code : 
  - //provide the repository path
  
Techonologies : 
  Java with Serenity BDD framework

Versions and libraries supporting are : 
  1. Serenity 2.3.12
  2. Cucumber 6.9.1
  3. Java 1.8 and higher
  4. Serenity Rest Assured - 2.3.12

Feature: <br />
   Validate the Meta DataPoint API for hourly and location specific forecast data feeds 
  Scenarios :
    1. Get valid 200 response code when user invokes sitelist API end point for 3hourly and daily forecast
    2. Confirm the location name is "LOCATION NAME" when invoke with specific location ID
    
Starter of the Project : <br />
   The best place to start the project is download or clone the project on Github (provide github link ). This aloows you to install all the jars specified above along with the    supporting classes. 

The project directory structure: <br />
  The project has build scripts for both Maven and Gradle, and follows the standard directory structure used in most Serenity projects:
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ![ScreenShot](https://i.postimg.cc/3J6qLQsY/Project-Structure-Window.png)
        

          
         

   
   
    
 



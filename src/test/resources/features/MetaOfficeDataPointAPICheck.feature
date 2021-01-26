Feature: Invoke Met Office DataPoint API For forecast data feeds
          * As a user when I invoke 'sitelist' end point for daily and 3 hourly forecast data feeds it should see data feeds for all locations
          * As a user when I invoke 'forecast' end point with location as parameter then I should see the forecast for that location
          
      Pre-Requisites: 
          * Generate the Token from https://www.metoffice.gov.uk/services/data/datapoint website by registering , you will find API Key under MyAccount
          * Provide the resource for the service you want to validate under Examples section
          * Provide a valid Json Path and location Name as parameters if you want to validate any specific validation , example as provided to validate name for the LocationID given in the resource

  Scenario Outline: Get forecast feeds for hourly and daily from the 'sitelist' endpoint
    Given client uses the following url parameters:
      | res | <forecast> |
    When the client invokes "Endpoint" with resource as "<resource>"
    Then the client should get 200 response code

    Examples: 
      | forecast | resource                     |
      | 3hours   | /val/wxfcs/all/json/sitelist |
      | daily    | /val/wxfcs/all/json/sitelist |
  
  @Skip
  Scenario Outline: Get Forecast feeds for specific LocationID
    Given client uses the following url parameters:
      | res | <forecast> |
    When the client invokes "Endpoint" with resource as "<resource>"
    Then the client should see forecast feeds for "<locationName>" location in response for "<JsonPath>"

    Examples: 
      | forecast | resource                   | JsonPath                 | locationName                       |
      | 3hourly  | /val/wxfcs/all/json/324152 | SiteRep.DV.Location.name | CROYDON                            |
      | 3hourly  | /val/wxfcs/all/json/354636 | SiteRep.DV.Location.name | BOURNEMOUTH - DURLEY CHINE (BEACH) |

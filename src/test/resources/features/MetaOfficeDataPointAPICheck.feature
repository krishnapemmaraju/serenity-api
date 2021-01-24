Feature: Invoke Met Office DataPoint API For forecast data feeds
          * As a user when I invoke 'sitelist' end point for daily and 3 hourly forecast data feeds it should see data feeds for all locations
          * As a user when I invoke 'forecast' end point with location as parameter then I should see the forecast for that location

  Scenario Outline: Should receive valid response code when sitelist endpoint is invoked with 3 hourly and daily forecast feeds
    Given client uses the following url parameters:
      | res | <forecast> |
    When the client invokes "Endpoint" endpoint with resource as "<resource>"
    Then the client should receive an HTTP 200 response code

    Examples: 
      | forecast | resource     |
      | 3hours   | sitelist     |
      | daily    | sitelist     |
      | 5hours   | sitelist     |
      | 3hourly   | capabilities |

  Scenario Outline: Should receive valid location name when endpoint is invoked with 3 hourly and locationId
    Given client uses the following url parameters:
      | res | <forecast> |
    When the client invokes "Endpoint" endpoint with resource as "<resource>"
    Then the client should see forecast feeds for 'Cryodon' location in response

    Examples: 
      | forecast | resource |
      | 3hourly  |   324152 |

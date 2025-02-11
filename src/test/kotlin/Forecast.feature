Feature: Forecast Searching 

Data Forecast
| Name        | Default   | Datetype           | Notes               |
| Day         | 1/1/2025  | Date               |                     |
| Time        | 12:00 am  | Time               |                     |
| High        | 100       | Temperature        | High Temperature    |
| Low         | 0         | Temperature        | Low Temperature     |
| Rain        | 0         | Percentage         | Chance of Rain      |
| Wind Speed  | 0         | Speed              |                     |
| Direction   | N         | CompassDirection   |                     |
| Condition   | Clear     | WeatherCondition   | Cloudy, Rain, etc.  |
 
Scenario: Search Forecast
Given forecast is # ListOfObject Forecast
| Day       | Time      | High  | Low  | Rain  | Wind Speed  | Direction  | Condition  |
| 1/1/2025  | 12:00 am  | 70    | 60   | 0     | 1           | N          | Clear      |
| 1/3/2025  | 12:00 am  | 60    | 40   | 10    | 5           | S          | Cloudy     |
# And many more  (or read from CSV file)
When searching for # ListOfObject # ForecastSearchCriteria 
| Field        | Relationship  | Value  |
| High         | >             | 65     |
| Wind Speed   | <             | 5      |
Then results are # ListOfObject Forecast 
| Day       | Condition  |
| 1/1/2025  | Clear      |



import java.io.InputStream;

/**
 * Author: Vicki Chen
 * CS8B Login: cs8bwamh
 * Date: 1/26/19
 * File: Main.java
 * Sources of Help: PA2 write up, Piazza, CSE8B tutors
 *
 * This file contains is used to test methods from ParseJSON.java
 * and receives weather data points from GetJSON.java
 * Displays the different properties of the weather.
 **/

/**
 * This class contains methods to that checks if methods from ParseJSON.java
 * is correctly manipulating the json objects from GetJSON.java
 * to get the correct data and display them.
 * Has the instance variables testString to do simple tests
 * */
public class Main {
    public static final String testString = "[" +
        "{\"min_temp\": 10.0, \"max_temp\": 20.0, " + 
        "\"weather_state_name\":\"Light Rain\"}, " + 
        "{\"min_temp\": 11.0, \"max_temp\": 21.0, " + 
        "\"weather_state_name\":\"Light Rain\"}, " + 
        "{\"min_temp\": 12.0, \"max_temp\": 22.0, " + 
        "\"weather_state_name\":\"Light Rain\"}]";
    
    // Names of properties within objects returned by metaweather.com
    public static final String KEY_MIN_TEMP = "min_temp";
    public static final String KEY_MAX_TEMP = "max_temp";
    public static final String KEY_STATE = "weather_state_name";
    
    /**
     * Tests simple cases of json objects and prints a 3-day forecast
     * from of a certain area from MetaWeather
     * @param args Arguments being passed through the command line
     * @return     none.
     * */
    public static void main (String[] args) {
        // Run our offline tests 
        testFromFile("samplePretty.json");
        testFromFile("sampleUgly.json");

        // TODO: uncomment below when your implementation is ready for
        //       online testing
        
        // Show a 3-day forecast
        for (int day = 16; day <= 18; day++) {
            printForecast(2019, 1, day);
        }
                
        
    }
    /**
     * Loads data files and calls to check if data is parsed correctly
     * @param filename Data file name
     * @return         none.
     **/
    public static void testFromFile(String filename) {
        System.out.println("Running tests on " + filename);
        
        // Load json from the given filename
        ParseJSON parser = null;
        try {
            parser = new ParseJSON(GetJSON.fromFile(filename));
        } catch (Exception e) {
            System.out.println("Failed to load and parse " + 
                filename + ", got exception:");
            System.out.println(e.toString());
            return;
        }
        
        // Run some basic tests
        testParser(parser, 0, "id", "418759");
        testParser(parser, 0, "predictability", "70");

        // TODO: look at sample.json and add more test calls like
        //       the 2 example above

        testParser(parser, 1, "air_pressure", "1012");
        testParser(parser, 2, KEY_STATE, "Light Cloud");
        testParser(parser, 0, "air_pressure", "null");
        testParser(parser, 0, "degree", null);
        testParser(parser, 4, "id", null);
        testParser(parser, 0, "predictability", "70");
        testParser(parser, 1, null, null);
        testParser(parser, 0, "null", null);
        testParser(parser, 0, "Light Cloud", null);

    }
    
    /**
     * Says success if data is being parsed correctly and extracting the
     * correct values at the indicated places, else says failed
     * @param parser File being parsed
     * @param index Index of json object in list
     * @param key   Type of data to extract
     * @param expectedValue Supposed value at indicated key
     * @return       none.
     * */
    public static void testParser(ParseJSON parser, int index, 
                                  String key, String expectedValue) {
        // This is a helper method for unit testing

        // See what getValue() returns so we can compare it to expectedValue
        String actualValue = parser.getValue(index, key);
        
        if (actualValue == null && expectedValue == actualValue)
        {
           System.out.println("SUCCESS: key=\"" + key + "\", value=\"" + 
                actualValue + "\"");           
        }
        else if (expectedValue.equals(actualValue)) {
            System.out.println("SUCCESS: key=\"" + key + "\", value=\"" + 
                actualValue + "\"");
        } else {
            System.out.println("FAILED: key=\"" + key + "\", value=\"" + 
                actualValue + "\" (expected \"" + expectedValue + "\")");
        }
    }
    
    /**
     * Prints out a weather forcast report for 3 days
     * @param year Given year
     * @param month Given month
     * @param day Given day
     * @return    none.
     * */
    public static void printForecast(int year, int month, int day) {
        // Print a one-line forecast for the given date
        // Formatting examples:
        // Forecast for 2019/01/19: Min 10.0, Max 20.1, Light Rain
        // Forecast for 2019/01/19: Min 9.5, Max 20.8, Heavy Clouds
        // TODO: get the stream from Meta Weather API and then get values
        //       from the keys (see KEY_MIN_TEMP, KEY_MAX_TEMP, KEY_STATE)
        //       and print the forecast

        /* ADD CODE HERE */
        // Load json from the given filename        
        ParseJSON forecast = null;
        try {
            InputStream data = GetJSON.fromMetaWeather(year, month, day);
            forecast = new ParseJSON(data);         
           
        } catch (Exception e) {
            System.out.println("Failed to load and parse " + 
                forecast + ", got exception:");
            System.out.println(e.toString());
            return;
        }
        
        //print out forecast of min and max temp and state in specified format
        System.out.println("Forcast for " + year + "/" + month + "/" + day
                + ": Min " + forecast.getValue(0, KEY_MIN_TEMP) + ", Max "
                + forecast.getValue(0 , KEY_MAX_TEMP) + ", " 
                + forecast.getValue(0, KEY_STATE));
        
    }
}

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*; 

/**
 * Author: Vicki Chen
 * CS8B Login: cs8bwamh
 * Date: 1/26/19
 * File: ParseJSON.java
 * Sources of Help: PA2 write up, Piazza, CSE8B tutors
 *
 * This file contains the class ParseJSON.
 * It takes in data from a server and inputs it into an arraylist
 * where it can manipulate the objects and its data within the arraylist
 * */

/**
 * This class contains methods that can parse objects into an arraylist
 * and extract certain data points within the arraylist of objects.
 * It has the instance variable List where the objects will be parsed.
 * */
public class ParseJSON {
    protected ArrayList<String> list;     //arraylist storing the JSON data
    private static char startCurly = '{';
    private static String endCurlyString = "}";
    private static char endCurlyChar = '}';
    private static char comma = ',';
    private static char colon = ':';
    private static char quote = '"';
    private static String colonString = ":";
    private static String escapeLine = "\n";
    private static String whiteSpace = " ";
    /**
     * Intializes an arraylist of objects from a string in JSON format
     * @param json Given string input with data
     * @return     none.
     * */
    public ParseJSON (String json) {
        // TODO: set Arraylist and fill it with objects 
        //       (see helper method parse below)

        list = parse(json);
    }

    /**
     * Intializes an arraylist of objects from a stream in JSON format
     * @param stream Data from outside source
     * @return       none.
     * */
    public ParseJSON (InputStream stream) {
        // TODO: Take the input stream and read in the lines
        //       and create Arraylist and fill it with objects
        //       (see helper method streamToString below)
        
        list = parse(streamToString(stream));
        
    }
    
    /**
     * Adds objects to an arraylist in JSON format
     * @param json String of json objects
     * @return     ArrayList of all json objects
     * */
    protected static ArrayList<String> parse(String json) {

        // TODO: Use the json string to fill an Arraylist with json 
        //       object strings.
        //       json objects start with { and end with }
        //       It's ok to assume that strings will not contain '}'
        
        ArrayList<String> jsonObjectsArray = new ArrayList<>();

        if (json == null)
        {
            return null;
        }
        
        //iterate through string to seperate each object by its curly brackets
        for (int index = 0; index < json.length(); index++)
        {
            if (json.charAt(index) == startCurly)
            {  
                //substring between {} then moving onto the next set of {}
                jsonObjectsArray.add(json.substring(index, 
                            json.indexOf(endCurlyString, index)+1));
            }        
        }

        return jsonObjectsArray;
    }
    
    /**
     * Finds the value of an object at a certain key with helper methods
     * findKey() and extractValue()
     * @param arrayIndex Index of selected json object
     * @param keyToFind  The type of data value
     * @return           The data at selected key and json object
     * */
    public String getValue (int arrayIndex, String keyToFind) {

        // TODO: Use arrayIndex to find the object in Arraylist. Then use the 
        //       given key to find corresponding value and return it. Use the
        //       two helper methods findKey() and extractValue()
        String object = "";
        String value = "";

        if (keyToFind == null)
        {
            return null;
        }

        //checking if index is in arraylist, if so get that one object
        if (arrayIndex < 0 || arrayIndex > list.size()-1)
        {
            return null;
        }
        else
        {
            object = list.get(arrayIndex);
        }
        int keyIndex = findKey(keyToFind, object);
        //checking if there is such key in object, if so extract data
        if (keyIndex == -1)
        {
            return null;
        }
        else
        {
            value = extractValue(keyIndex, object);
        }

        return value;
    }

    /**
     * Finds the index of the quotation mark after the given key in
     * an object
     * @param keyToFind Given key to find index of
     * @param jsonObject The object to find the index of key
     * @return          The index after the end of the key
     * */
    protected int findKey (String keyToFind, String jsonObject) {

        // This is a helper method used by getValue()
        // TODO: Return the index of the ending " of the first
        //       matching key, or -1 if keyToFind is not in the jsonObject
        //       For example, searching for "the_key_we_want" in
        //       {"some_other_key": -1.4, "the_key_we_want": "some value"}
        //       would return the index of this quotation ^

        // Note: It's ok to assume that keys and values in jsonObject do 
        //       not contain ',' or '}'
        
       String checkColon = "";
       int quotation = -1; 
       
       if (!(jsonObject.contains(keyToFind)))
       {
            return -1;
       }
       else
       {
           //iterating through object to look for keyToFind
           for (int index = 0; index < jsonObject.length()-keyToFind.length(); index++)
           {   
               //compare keyword's length to see if substring matches with key
               if (jsonObject.substring(index, 
                           index+keyToFind.length()).equals(keyToFind))
               {
                    quotation = index + keyToFind.length();
                    //create fragment from quote to :
                    checkColon = jsonObject.substring(quotation+1, 
                            jsonObject.indexOf(colonString, quotation)+1);
                    //get rid of possible whitespaces between " and :
                    checkColon = checkColon.replaceAll(whiteSpace, "");
                    
                    //check if there is colon after quote
                    if (checkColon.equals(colonString))
                    {
                        return quotation;
                    }
                    else
                    {
                        quotation = -1;
                    }
               }
           }
       }
       return quotation;
    
    }
    
    /**
     * Get the value at a certain key in an object
     * @param keyStringIndex Index of the key
     * @param jsonObject     Given object to find data value in
     * @return               Value of the data at a key in a json object
     * */
    protected String extractValue (int keyStringIndex, String jsonObject) {

        // This is a helper method used by getValue()
        // TODO: the parameter keyStringIndex indicates the index of the
        //       ending quote of a key - return the value following that key.
        // 
        // For example:
        //              if keyStringIndex points here --V
        //     {"some_other_key": -1.4, "the_key_we_want": "some value"}
        // ...then it returns "some value"
        //                     V-- if keyStringIndex points here...
        //     {"some_other_key": -1.4, "the_key_we_want": "some value"}
        // ...then it returns "-1.4"

        // Note: It's ok to assume that keys and values in jsonObject do 
        //       not contain ',' or '}'
        
        /* ADD CODE HERE */
        String value = "";

        if (jsonObject == null)
        {
            return null;
        }

        if (keyStringIndex < 0 || 
                keyStringIndex > jsonObject.length())
        {
            return null;
        }
        
        //iterating to cut a fragment containing the data value
        //from key index to a comma or end curly brackets
        for (int index = keyStringIndex; index < jsonObject.length(); index++)
        {
            if (jsonObject.charAt(index) == comma || 
                    jsonObject.charAt(index) == endCurlyChar)
            {
                jsonObject = jsonObject.substring(keyStringIndex+1, index);
            }
        }
        
        //get rid of any white spaces in the before : and after data value
        jsonObject = jsonObject.trim();
        
        //get string from after : to end of data value
        //then get rid of white spaces between : and start of value
        value = jsonObject.substring(jsonObject.indexOf(colon)+1, 
                jsonObject.length()).trim();
        
        //deleting quotes from string values
        if (value.charAt(0) == quote)
        {
            value = value.substring(1, value.length()-1);
        }
    
        return value;

    } 

    /**
     * Create a string of all the lines from an input of stream
     * @param stream Lines of data from a source
     * @return       String of all lines of data from stream
     * */
    protected static String streamToString (InputStream stream) {
        // This is a helper method to be used by the second constructor
        // TODO: read in the lines from stream and create a string with all
        //       the lines and return it

        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        String line = "";
        String streamString = "";
        
        //put each line of text into one string until no more lines to read
        try {
            while ((line = reader.readLine()) != null)
            {
                streamString = streamString + line + escapeLine;
            }
              
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    
        return streamString;

    }
}

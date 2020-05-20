import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.io.IOException;

public class GetJSON {
    //protected static final String metaWeatherLocation = "44418"; // London
    protected static final String metaWeatherLocation = "2487889"; // San Diego

    public static FileInputStream fromFile(String filename) 
        throws FileNotFoundException
    {
        // Load an InputStream from a JSON file
        return new FileInputStream(new File(filename));
    }
    
    public static InputStream fromMetaWeather(int year, int month, int day) 
        throws MalformedURLException, IOException
    {
        // Load an InputStream from metaweather.com

        String uri = "https://www.metaweather.com/api/location/" + 
            metaWeatherLocation + "/" + year + "/" + month + "/" + day + "/";
        URL url = new URL(uri);

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        // give it 15 seconds to respond
        connection.setReadTimeout(15 * 1000);
        connection.connect();

        return connection.getInputStream();
    }
}

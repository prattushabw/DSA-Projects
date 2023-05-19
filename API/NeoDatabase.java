import big.data.DataSource;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The NeoDatabase class manages a collection of NearEarthObject objects obtained from the NASA API.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public class NeoDatabase{

    /**
     *The list of NearEarthObject objects stored in this database.
     */
    ArrayList<NearEarthObject> database= new ArrayList<>();

    /*
    The API key required to access the NASA API.
     */
    public static final String API_KEY = "9uviRQuCfS0PRNsaHLxIvARxW6ISyOxgQlw7eqs0";

    /**
     * The root URL for the NASA API.
     */
    public static final String API_ROOT = "https://api.nasa.gov/neo/rest/v1/neo/browse?";
    Comparator<NearEarthObject> comp;

    /**
     * Constructs an empty NeoDatabase object.
     */
    public NeoDatabase(){}

    /**
     * Builds and returns the query URL for the NASA API based on the given page number.
     * @param pageNumber the page number of the API to query
     * @return the query URL for the API
     * @throws IllegalArgumentException if the page number is less than 0 or greater than 715
     */
    public String buildQueryURL(int pageNumber) throws IllegalArgumentException{
        if(pageNumber<0 || pageNumber>715){
            throw new IllegalArgumentException();
        }
        return API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY;
    }

    /**
     * Retrieves NearEarthObject objects from the NASA API based on the given query URL and adds them to the database.
     * @param queryURL the query URL for the API
     * @throws IllegalArgumentException if the query URL is null or cannot be resolved
     * @throws IOException if an error occurs while connecting to or reading from the API
     */
    public void addAll(String queryURL) throws IllegalArgumentException, IOException {
        if(queryURL==null && isURLResolved(queryURL)){
            throw new IllegalArgumentException();
        }
        DataSource ds = DataSource.connectJSON(queryURL);
        ds.load();

        ArrayList<NearEarthObject> fetch = ds.fetchList(
                "NearEarthObject",
                "near_earth_objects/neo_reference_id",
                "near_earth_objects/name",
                "near_earth_objects/absolute_magnitude_h",
                "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min",
                "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max",
                "near_earth_objects/is_potentially_hazardous_asteroid",
                "near_earth_objects/close_approach_data/epoch_date_close_approach",
                "near_earth_objects/close_approach_data/miss_distance/kilometers",
                "near_earth_objects/close_approach_data/orbiting_body"
        );

        database.addAll(fetch);

    }

    /**
     * Determines whether the given URL can be resolved
     * @param queryURL the URL to check
     * @return true if the URL can be resolved, false otherwise
     * @throws IOException if an error occurs while connecting to the URL
     */
    private boolean isURLResolved(String queryURL) throws IOException {
        URL url = new URL(queryURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();
        return (responseCode != HttpURLConnection.HTTP_NOT_FOUND);
    }


    /**
     * Sorts the list of NearEarthObjects in the database using the given comparator.
     * Throws an IllegalArgumentException if the comparator is null.
     * @param comp the comparator used to sort the database
     * @throws IllegalArgumentException if the comparator is null
     */

    public void sort(Comparator<NearEarthObject> comp) throws IllegalArgumentException{
        if (comp == null)
            throw new IllegalArgumentException();
        this.comp = comp;
        Collections.sort(database,comp);
    }

    /**
     * Prints the contents of the NearEarthObject database as a formatted table.
     */
    public void printTable(){
       System.out.printf("%s|%s|%s|%s|%s|%s|%s|%s%n%s%n",
                "    ID    ","               Name               "," Mag. "," Diameter "," Danger "," Close Date "," Miss Dist "," Orbits ",
                "=".repeat(107)
        );

        for(int i = 0; i < database.size(); ++i) {
            System.out.printf(" %-8d%34.26s%7.01f%10.03f%9b%7tm-%<td-%<tY%13.0f%9s%n",database.get(i).getReferenceID(),database.get(i).getName(),database.get(i).getAbsoluteMagnitude(),database.get(i).getAverageDiameter(),database.get(i).isDangerous(),database.get(i).getClosestApproachDate(),database.get(i).getMissDistance(),database.get(i).getOrbitingBody());
        }
    }
}


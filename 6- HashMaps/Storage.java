/**
 * A class representing a storage box with an ID, a client name, and contents.
 * Implements the Serializable interface for object serialization and deserialization.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
import java.io.Serializable;

public class Storage implements Serializable{

    private int id;

    private String client;

    private String contents;

    /**
     * Constructs a default Storage object with an ID of 0, an empty client name,
     * and an empty contents string.
     */
    Storage (){
        id=0;
        client="";
        contents="";
    }

    /**
     * Constructs a Storage object with the given ID, client name, and contents.
     * @param id the ID of the storage box
     * @param client the name of the client who owns the storage box
     * @param contents the contents of the storage box
     */
    Storage(int id, String client,String contents){
        this.id = id;
        this.client = client;
        this.contents = contents;
    }

    /**
     * Returns the ID of the storage box.
     * @return the ID of the storage box
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the client who owns the storage box.
     * @return the name of the client who owns the storage box
     */
    public String getClient() {
        return client;
    }

    /**
     * Returns the contents of the storage box.
     * @return the contents of the storage box
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets the ID of the storage box to the given ID if it is not 0.
     * @param id the new ID of the storage box
     */
    public void setId(int id) {
        if(id!=0)
            this.id = id;
    }

    /**
     * Sets the name of the client who owns the storage box to the given name
     * if it is not an empty string.
     * @param client the new name of the client who owns the storage box
     */
    public void setClient(String client) {
        if (!client.equals(""))
            this.client = client;
    }

    /**
     * Sets the contents of the storage box to the given contents if the client name
     * is not an empty string.
     * @param contents the new contents of the storage box
     */
    public void setContents(String contents) {
        if (!client.equals(""))
            this.contents = contents;
    }
}

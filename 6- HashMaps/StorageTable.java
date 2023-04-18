/**
 * This class extends HashMap<Integer, Storage> and implements Serializable interface.
 * It represents a storage table that can hold Storage objects.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

import java.io.Serializable;
import java.util.HashMap;


public class StorageTable extends HashMap<Integer, Storage> implements Serializable {

    /**
     * This method adds a new storage box to the table.
     * It takes in two parameters - an int representing the storage ID and a Storage object.
     * If the storage ID is negative or the storage object is null, it throws an IllegalArgumentException.
     *
     * @param storageId - the storage ID to add to the table.
     * @param storage - the Storage object to add to the table.
     * @throws IllegalArgumentException if storageId is negative or storage object is null.
     */
    public void putStorage(int storageId, Storage storage) throws IllegalArgumentException {
        if (storageId < 0 && storage == null) {
            throw new IllegalArgumentException();
        }
        put(storageId, storage);
    }

    /**
     * This method retrieves the storage box with the given storage ID from the table.
     * If the table does not contain the given storage ID, it returns null.
     *
     * @param storageID - the storage ID to retrieve from the table.
     * @return the Storage object associated with the given storage ID, or null if not found.
     */
    public Storage getStorage(int storageID) {
        if(!containsKey(storageID))
            return null;
        else
            return get(storageID);

    }

    /**
     * This method returns a String representation of the StorageTable object.
     * It returns a table with columns "Box#", "Contents" and "Owner" for each storage box in the table.
     *
     * @return a String representation of the StorageTable object.
     */
    public String toString(){
        String format="";
        StringBuilder list= new StringBuilder();
        format+="Box#          Contents                       Owner\n" +
                "----------------------------------------------------------------\n";
        for( Entry<Integer, Storage> entry : entrySet() ){
             list.append(String.format("%-15d%-30s%s\n", entry.getKey(), entry.getValue().getContents(), entry.getValue().getClient()));
        }
        format+=list;

        return format;
    }
}

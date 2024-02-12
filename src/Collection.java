package collectionmanager;
/**
 * This class represents a collection of albums
 * Array of album objects represents the albums in a collection
 * @author Joon Song, Connor Powell
 */
public class Collection {
    private Album [] albums;
    private int size;

    private static final int NOT_FOUND = -1;

    /**
     * Default constructor for Collection
     */
    public Collection() {
        int size = 4;
        albums = new Album[size];
    }

    /**
     * Searches for the album in the collection and returns its index
     * @param album to search for.
     * @return the index of the album if present, -1 otherwise
     */
    private int find(Album album) {
        for(int i = 0; i < size && albums[i] != null; i++) {
            if(albums[i].equals(album))
                return i;
        }
        return NOT_FOUND;
    }

    /**
     * Increases the capacity of the collection by 4
     */
    private void grow() {
        size += 4;
        Album [] temp = new Album[size];
        for (int i = 0; i < size - 4; i++) {
            temp[i] = albums[i];
        }
        albums = temp;
    }

    /**
     * Figures out if the specified album is in the collection
     * @param album to search for
     * @return true if collection contains album, false otherwise
     */
    public boolean contains(Album album) {
        return find(album) != NOT_FOUND;
    }

    /**
     * Adds the album into the collection
     * @param album to add
     * @return false if the album is already in the collection, true otherwise
     */
    public boolean add (Album album) {
        if(contains(album))
            return false;
        for(int i = 0; i < size; i++) {
            if(albums[i] == null) {
                albums[i] = album;
                return true;
            }
        }
        int temp = size;
        grow();
        albums[temp] = album;
        return true;
    }

    /**
     * Removes specified album from the collection
     * @param album to remove
     * @return false if album is not in the collections, true otherwise
     */
    public boolean remove(Album album) {
        if(!contains(album))
            return false;
        int i = find(album);
        for(;i < size; i++){
            if(i == size - 1) {
                albums[i] = null;
            }
            else {
                albums[i] = albums[i + 1];
            }
        }
        return true;
    }

    /**
     * Gives the specified album a star rating out of 5
     * @param album to be given a rating
     * @param rating that the album will be given
     */
    public void rate (Album album, int rating) {
        album.rate(rating);
    }

    /**
     * Prints the collection first by date, then title
     */
    public void printByDate () {
        if(albums[0] == null) {
            System.out.println("Collection is empty.");
            return;
        }
        System.out.println("* Collection sorted by Release Date/Title *");
        Album [] temp = albums;
        for (int i = 0; i < size && temp[i] != null; i++) {
            int min = i;
            for (int j = i + 1; j < size && temp[j] != null; j++) {
                if (temp[j].getReleased().compareTo(temp[min].getReleased()) > 0) {
                    min = j;
                }
                else if(temp[j].getReleased().compareTo(temp[min].getReleased()) == 0 && temp[j].getTitle().compareTo(temp[min].getTitle()) > 0) {
                    min = j;
                }
            }
            Album t = temp[min];
            temp[min] = temp[i];
            temp[i] = t;
        }
        for(int i = 0; i < size && temp[i] != null; i++) {
            System.out.println(temp[i].toString());
        }
        System.out.println("* end of list *");
    }

    /**
     * Prints the collection first by genre, then artist
     */
    public void printByGenre() {
        if(albums[0] == null) {
            System.out.println("Collection is empty.");
            return;
        }
        System.out.println("* Collection sorted by Genre/Artist *");
        Album [] temp = albums;
        for (int i = 0; i < size && temp[i] != null ; i++) {
            int min = i;
            for (int j = i + 1; j < size && temp[j] != null; j++) {
                if (temp[j].getGenre().ordinal() < temp[min].getGenre().ordinal()) {
                    min = j;
                }
                else if(temp[j].getGenre().ordinal() == temp[min].getGenre().ordinal() && temp[j].getArtist().compareTo(temp[min].getArtist()) > 0) {
                    min = j;
                }
            }
            Album t = temp[min];
            temp[min] = temp[i];
            temp[i] = t;
        }
        for(int i = 0; i < size && temp[i] != null; i++) {
            System.out.println(temp[i].toString());
        }
        System.out.println("* end of list *");
    }
    /**
     * Prints collection first by average rating, then title
     */
    public void printByRating() {
        if(albums[0] == null) {
            System.out.println("Collection is empty.");
            return;
        }
        System.out.println("* Collection sorted by Rating/Title *");
        Album [] temp = albums;
        for (int i = 0; i < size && temp[i] != null; i++) {
            int min = i;
            for (int j = i + 1; j < size && temp[j] != null; j++) {
                if (temp[j].avgRatings() > temp[min].avgRatings()) {
                    min = j;
                }
                else if(temp[j].avgRatings() > temp[min].avgRatings() && temp[j].getTitle().compareTo(temp[min].getTitle()) > 0) {
                    min = j;
                }
            }
            Album t = temp[min];
            temp[min] = temp[i];
            temp[i] = t;
        }
        for(int i = 0; i < size && temp[i] != null; i++) {
            System.out.println(temp[i].toString());
        }
        System.out.println("* end of list *");
    }

    /**
     * Getter method to get the albums in the collection
     * @return the array that contains the albums in the collection.
     */
    public Album[] getAlbums() {
        return albums;
    }
}

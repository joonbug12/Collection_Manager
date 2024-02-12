import java.util.Scanner;
/**
 *This class implements the functionalities of the other classes to correctly create each album instance.
 * Uses input to help run the entire project
 * @author Joon Song, Connor Powell
 */
public class CollectionManager {
    private static Collection collection;

    /**
     * constructor
     */
    public CollectionManager() {
        collection = new Collection();
    }

    /**
     * Helper method that creates an album from the input given
     * @param input used to create the album
     * @return the album that has been created
     */
    private static Album createAlbum(String input){
        String [] inputs = new String[5];
        for(int i = 0; i < 5; i++) {
            if(input.indexOf(',') != -1) {
                inputs[i] = input.substring(0, input.indexOf(","));
                input = input.substring(input.indexOf(",") + 1);
            }
            else {
                inputs[i] = input;
            }
        }
        Date dob = createDate(inputs[2]);
        Date release = createDate(inputs[4]);
        Genre genre = createGenre(inputs[3]);
        Artist artist = new Artist(inputs[1],dob);
        Album tr = new Album(inputs[0],artist,genre,release,null);
        return tr;
    }

    /**
     * Helper method that creates a date from the input given
     * @param input used to create the date
     * @return the date that is created
     */
    private static Date createDate(String input) {
        int [] inputs = new int[3];
        for(int i = 0; i < 3; i++) {
            if(input.indexOf('/') != -1) {
                inputs[i] = Integer.parseInt(input.substring(0, input.indexOf("/")));
                input = input.substring(input.indexOf("/") + 1);
            }
            else {
                inputs[i] = Integer.parseInt(input);
            }
        }
        Date tr = new Date(inputs[0],inputs[1],inputs[2]);
        return tr;
    }

    /**
     * Helper method to create a genre from the given input
     * @param input used to create the genre
     * @return the genre that has been created
     */
    private static Genre createGenre(String input) {
        input = input.toLowerCase();
        if(input.equals("pop"))
            return Genre.Pop;
        if(input.equals("country"))
            return Genre.Country;
        if(input.equals("classical"))
            return Genre.Classical;
        if(input.equals("jazz"))
            return Genre.Jazz;
        return Genre.Unknown;
    }

    /**
     * Helper method that checks whether or not an album is valid to add
     * @param album to be tested if it is valid
     * @return true if the album is valid, false otherwise
     */
    private static boolean isValidAlbum(Album album) {
        if(!album.getReleased().isValid()) {
            System.out.println("Date Released: " + album.getReleased().toString() + " is invalid.");
            return false;
        }
        if(!album.getArtist().getBorn().isValid()) {
            System.out.println("Artist DOB: " + album.getArtist().getBorn().toString() + " is invalid.");
            return false;
        }
        return true;
    }

    /**
     * Helper method that creates a rating for an album based on the input.
     * @param input to create the rating from
     */
    private static void createRating(String input) {
        String [] inputs = new String[4];
        for(int i = 0; i < 4; i++) {
            if(input.indexOf(',') != -1) {
                inputs[i] = input.substring(0, input.indexOf(","));
                input = input.substring(input.indexOf(",") + 1);
            }
            else {
                inputs[i] = input;
            }
        }
        Date dob = createDate(inputs[2]);
        Artist artist = new Artist(inputs[1],dob);
        Album temp = new Album(inputs[0],artist, null, null,null);
        int rating = Integer.parseInt(inputs[3]);
        if(rating < 1 || rating > 5) {
            System.out.println("Invalid rating, rating scale is 1 to 5.");
        }
        else if(collection.contains(temp)) {
            Album [] albums = collection.getAlbums();
            for(int i = 0; i < albums.length && albums[i] != null; i++) {
                if(albums[i].equals(temp)) {
                    collection.rate(albums[i],rating);
                    System.out.println("You rated " + rating + " for " + albums[i].getTitle() + ": " + albums[i].getReleased() + "(" + artist.getName() + ")");
                    break;
                }
            }
        }
        else
            System.out.println(temp.getTitle() + " (" + artist.toString() + ") is not in the collection.");
    }

    /**
     * Helper method to remove album based on input
     * @param input used to find album to remove
     */
    private static void removeAlbum(String input) {
        String [] inputs = new String[3];
        for(int i = 0; i < 3; i++) {
            if(input.indexOf(',') != -1) {
                inputs[i] = input.substring(0, input.indexOf(","));
                input = input.substring(input.indexOf(",") + 1);
            }
            else {
                inputs[i] = input;
            }
        }
        Date dob = createDate(inputs[2]);
        Artist artist = new Artist(inputs[1],dob);
        Album album = new Album(inputs[0], artist, null, null, null);
        if(collection.remove(album))
            System.out.println(album.getTitle() + " (" + artist.toString() + ") removed from collection.");
        else
            System.out.println(album.getTitle() + " (" + artist.toString() + ") is not in the collection.");

    }

    /**
     * Method to run collection manager. Different command letters to run different functionalities of collection manager.
     * A(add album), R(add rate 1-5), D(remove album), PD(print by release date, then by title lexicographical order if date is same)
     * PG(print by genre, then by artists if genre is same, and if genre and artist is same, then release date)
     * PR(print by average rating, and if same average rating, then lexicographical order of the titles). Q(terminate collection manager).
     */
    public void run () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Collection Manager is up and running.");
        while(true) {
            String input = scan.nextLine();
            String command = "";
            if(input.indexOf(',') != -1) {
                command = input.substring(0, input.indexOf(","));
                input = input.substring(input.indexOf(",") + 1);
            }
            else
                command = input;
            //System.out.println(command);
            if(command.equals("D")) {
                removeAlbum(input);
            }
            else if(command.equals("A")) {
                Album album = createAlbum(input);
                if(isValidAlbum(album)) {
                    if (collection.add(album))
                        System.out.println(album.getTitle() + " (" + album.getArtist().toString() + ") added to the collection.");
                    else
                        System.out.println(album.getTitle() + " (" + album.getArtist().toString() + ") is already in the collection.");
                }
            }
            else if(command.equals("R")) {
                createRating(input);
            }
            else if(command.equals("PD"))
                collection.printByDate();
            else if(command.equals("PG"))
                collection.printByGenre();
            else if(command.equals("PR"))
                collection.printByRating();
            else if(command.equals("Q"))
                break;
            else
                System.out.println("Invalid Command!");
        }
        scan.close();
        System.out.println("Collection Manager terminated.");
    }
}

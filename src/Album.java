package collectionmanager;
/**
 * This class is used to create multiple album objects giving it a title, artist, genre, release, date, and rating
 * Implements the rating of each album onto a linked list. Average rating can be taken by traversing the rating ll.
 * @author Joon Song, Connor Powell
 */
public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; //a linked list of ratings

    /**
     * Constructor for the album class
     * @param title of the album
     * @param artist that made the album
     * @param genre of the album
     * @param released date of the album.
     * @param ratings any ratings the album has
     */
    public Album(String title, Artist artist, Genre genre, Date released, Rating ratings) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.released = released;
        this.ratings = ratings;
    }

    /**
     * Creates a new rating for the album
     * @param star rating for the album
     */
    public void rate(int star) {
        Rating temp = this.ratings;
        Rating rating = new Rating(star,temp);
        this.ratings = rating;
//        if(temp == null)
//            this.ratings = rating;
//        else {
//            temp.setNext(this.ratings);
//            this.ratings = temp;
//        }
    }

    /**
     * Generates the average rating for the album
     * @return the average ratings for the album
     */
    public double avgRatings() {
        double tr = 0.0;
        Rating temp = this.ratings;
        double n = 0;
        if(temp == null)
            return -1;
        while(temp != null) {
            tr += temp.getRating();
            n += 1;
            if(temp.getNext() == null)
                break;
            temp = temp.getNext();
        }
        tr = tr / n;
        return tr;
    }

    /**
     * Checks to see if to albums are the same.
     * @param obj is the object to be compared
     * @return true if the two albums are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Album) {
            Album album = (Album) obj;
            return this.artist.equals(album.artist) && this.title.toLowerCase().equals(album.title.toLowerCase());
        }
       return false;
    }

    /**
     * getter method
     * @return title
     */
    public String getTitle() {
        return title;
    }
    /**
     * getter method
     * @return artist
     */
    public Artist getArtist() {
        return artist;
    }
    /**
     * getter method
     * @return genre
     */
    public Genre getGenre() {
        return genre;
    }
    /**
     * getter method
     * @return date released
     */
    public Date getReleased() {
        return released;
    }
    /**
     * getter method
     * @return rating
     */
    public Rating getRatings() {
        return ratings;
    }

    /**
     * Generates the string for the album specified by the assignment
     * @return the string for the album
     */
    @Override
    public String toString() {
        String tr = "";
        tr = tr + "[" + this.title + "] ";
        tr = tr + "Released " + this.released.toString();
        tr = tr + " [" + this.artist.getName() + ": " + this.artist.getBorn().toString()+ "]";
        tr = tr + " ["+ this.genre + "]";
        Rating temp = this.ratings;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        while(temp != null) {
            if(temp.getRating() == 1)
                one++;
            if(temp.getRating() == 2)
                two++;
            if(temp.getRating() == 3)
                three++;
            if(temp.getRating() == 4)
                four++;
            if(temp.getRating() == 5)
                five++;
            if(temp.getNext() == null)
                break;
            temp = temp.getNext();
        }
        tr += " Rating: ";
        if(this.ratings == null)
            tr += "none";
        else
            tr += "*(" + one + ")**(" + two + ")***(" + three + ")****(" + four + ")*****(" + five +
                    ")(average rating: " + this.avgRatings() + ")";
        return tr;
    }
}

package collectionmanager;
/**
 * This class represents an artist with a date of birth and a name
 * @author Joon Song, Connor Powell
 */
public class Artist implements Comparable <Artist> {
    private String name;
    private Date born;

    /**
     * Constructor for artist class
     *
     * @param name of the artist
     * @param born date of birth of the artist
     */
    public Artist(String name, Date born) {
        this.name = name;
        this.born = born;
    }

    /**
     * Compares two artists to each other
     *
     * @param artist to be compared.
     * @return 1 if this artist is greater than the other artist, -1 if it is less than, 0 if they are the same
     */
    @Override
    public int compareTo(Artist artist) {
        if (this.name.toUpperCase().compareTo(artist.name.toUpperCase()) > 0) {
            return 1;
        }
        if (this.name.toUpperCase().compareTo(artist.name.toUpperCase()) < 0) {
            return -1;
        }
        if (this.born.compareTo(artist.born) > 0) {
            return 1;
        }
        if (this.born.compareTo(artist.born) < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * Determines whether two artists are the same artist
     *
     * @param obj is the artist to be compared to
     * @return true if they are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Artist) {
            Artist artist = (Artist) obj;
            return this.compareTo(artist) == 0;
        }
        return false;
    }

    /**
     * getter method
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * converts input date to a string
     * @return date as a string
     */
    @Override
    public String toString() {
        return this.getName() + " " + this.born.getMonth() + "/" + this.born.getDay() + "/" + this.born.getYear();
    }
    /**
     * setter method
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * getter method
     * @return dob
     */
    public Date getBorn() {
        return born;
    }

    /**
     * setter method
     * @param born new dob
     */
    public void setBorn(Date born) {
        this.born = born;
    }

    /**
     * testbed main
     * @param args the input
     */
    public static void main(String [] args) {
        letterCompare();
        lastLetterAndCaseSensitivity();
        sameNameFirstBornBeforeSecond();
        sameNameFirstBornAfterSecond();
        sameArtist();
    }

    /**
     * test1
     */
    private static void letterCompare(){
        Artist artist = new Artist("Taylor",new Date(12,13,1989));
        Artist artist2 = new Artist("Bruno",new Date(10,8,1985));
        int expected = 1;
        int actual = artist.compareTo(artist2);
        System.out.println("Test case 1: T is greater than B");
        testResult(artist,artist2, expected, actual);
    }
    /**
     * test1
     */
    private static void lastLetterAndCaseSensitivity(){
        Artist artist = new Artist("JohNNi",new Date(6,6,1998));
        Artist artist2 = new Artist("jOhNNY",new Date(9,17,1997));
        int expected = -1;
        int actual = artist.compareTo(artist2);
        System.out.println("Test case 2: Case sensitivity and i is less than y");
        testResult(artist,artist2, expected, actual);
    }
    /**
     * test2
     */
    private static void sameNameFirstBornBeforeSecond(){
        Artist artist = new Artist("Joon",new Date(12,12,2003));
        Artist artist2 = new Artist("Joon",new Date(12,13,2003));
        int expected = 1;
        int actual = artist.compareTo(artist2);
        System.out.println("Test case 3: Joon and Joon have the same name but the first one is born the day before");
        testResult(artist,artist2, expected, actual);
    }
    /**
     * test3
     */
    private static void sameNameFirstBornAfterSecond(){
        Artist artist = new Artist("CoNNor",new Date(12,12,2002));
        Artist artist2 = new Artist("COnnor",new Date(10,13,2001));
        int expected = -1;
        int actual = artist.compareTo(artist2);
        System.out.println("Test case 4: Connor and Connor have the same name but the first one is born after");
        testResult(artist,artist2, expected, actual);
    }
    /**
     * test4
     */
    private static void sameArtist(){
        Artist artist = new Artist("BEyoNcE",new Date(9,4,1981));
        Artist artist2 = new Artist("bEyONce",new Date(9,4,1981));
        int expected = 0;
        int actual = artist.compareTo(artist2);
        System.out.println("Test case 5: the two artists above are the same");
        testResult(artist,artist2, expected, actual);
    }

    /**
     * test5
     */
    private static void testResult(Artist artist,Artist artist2, int expected, int actual) {
        System.out.println("Artist input 1: " + artist.toString());
        System.out.println("Artist input 2: " + artist2.toString());
        System.out.println("Expected output: " + expected);
        System.out.println("Actual output: " + actual);
        if (expected != actual) {
            System.out.println("fail\n");
        } else {
            System.out.println("pass\n");
        }
    }
}
package collectionmanager;
/**
 * This class is a node class(Rating) where star is the data within the node and next refers to the next node of the ll.
 * @author Joon Song, Connor Powell
 */
public class Rating {
    private int star;
    private Rating next;

    /**
     * default constructor
     * @param star star rating 1-5
     * @param next next rating
     */
    public Rating(int star, Rating next){
        this.star = star;
        this.next=next;
    }


    /**
     * getter method
     * @return star rating
     */
    public int getRating() {
        return this.star;
    }

    /**
     * gets the next rating in the list
     * @return next rating
     */
    public Rating getNext() {
        return this.next;
    }

    /**
     * setter method
     * @param next next rating in the list
     */
    public void setNext(Rating next) {
        this.next = next;
    }

}
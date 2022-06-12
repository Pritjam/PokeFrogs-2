import java.util.Random;

/*
 * The interface for all Gene types.
 * All Genes must implement the applyGenetics method.
 */



public interface Gene {

    /**
     * Function that takes in another gene, and calculates a new gene resulting from the crossing of this gene and the other gene.
     * The algorithm used to determine the new gene can be anything, and does not have to conform to mendelian monogenetic processes.
     * @param other The other gene to calculate genetics from
     * @return The new gene calculated from this gene and the other gene.
     */
    public Gene applyGenetics(Gene other); // function returns a new Gene based on the other gene, using the genetics algorithm for this gene.
    public static Random random = new Random();
}

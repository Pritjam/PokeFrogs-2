import java.util.Random;

public interface Gene {
    public Gene applyGenetics(Gene other); // function returns a new Gene based on the other gene, using the genetics algorithm for this gene.
    public int[] getAlleles(); // TODO: Replace with colorGene
    public static Random random = new Random();

    public class MendelianMonogenetic implements Gene {
        private int[] alleles; 

        public MendelianMonogenetic(int[] alleles) {
            this.alleles = alleles;
        }

        public Gene applyGenetics(Gene other) {
            if(!(other instanceof MendelianMonogenetic)) {
                return null; //TODO: Error?
            }
            MendelianMonogenetic otherGene = (MendelianMonogenetic) other;
            if(otherGene.alleles.length != this.alleles.length) {
                return null; //TODO: Error?
            }
            int[] newAlleles = new int[this.alleles.length];
            for(int i = 0; i < this.alleles.length; i++) {
                newAlleles[i] = random.nextBoolean() ? this.alleles[i] : otherGene.alleles[i];
            }

            return new MendelianMonogenetic(newAlleles);
        }

        public int[] getAlleles() { //TODO: remove this and implement colorGene
            return alleles;
        }

        public String toString() {
            return "string";
            //TODO: Implement color gene
        }
        
    }

    // public class ColorGene implements Gene {
    //     private int[] alleles;
    //     private static String[][] phenotypes = {
	// 		//0:red  1:ylw  2:blu  3:blk  4:wht
	// 		{ "RED", "ORN", "PRP", "BLK", "RED" }, // red
	// 		{ "ORN", "YLW", "GRN", "BLK", "YLW" }, // ylw
	// 		{ "PRP", "GRN", "BLU", "BLK", "BLU" }, // blu
	// 		{ "BLK", "BLK", "BLK", "BLK", "GLS" }, // blk
	// 		{ "RED", "YLW", "BLU", "GLS", "WHT" }  // wht
	//     };

    //     public ColorGene(int[] alleles) {
    //         this.alleles = alleles;
    //     }

    //     public Gene applyGenetics(Gene other) {
    //         if(!(other instanceof ColorGene)) {
    //             return null; //TODO: Error?
    //         }

    //         ColorGene otherColor = (ColorGene) other;
    //         if(otherColor.alleles.length != alleles.length) {
    //             return null; //TODO: Error?
    //         }


    //     }
    // }
}

package genomesimulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class chromossome
 * 
 * @author jpaulo
 */
public class Chromossome {
    
    
    //List of genes composing the chromossome
    private List<Gene> lgenes;
    //Identifier of the chromossome
    private String name;
    //size of the chromossome
    private int size;
            
    public Chromossome(String name, int size) {
    
        this.name = name;
        this.size = size;
        this.lgenes = new ArrayList<>();               
    }
    
    public void insertGenes (Gene g) {
        this.lgenes.add(g);
    }
    
    public List<Gene> getGenesList () {
        return this.lgenes;
    }

    public String getName() {
        return this.name;
    }
    
    public int getSize() {
        return this.size;
    }
   
}

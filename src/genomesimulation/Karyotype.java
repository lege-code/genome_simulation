
package genomesimulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Karyotype {

    private List<Chromossome> lchromossome;
    private Set<String> listOfChromossomes = new HashSet<>();
    
    public Karyotype() {

        this.lchromossome = new ArrayList<>();

    }

    public void insertChromossomes(Chromossome c) {
        this.lchromossome.add(c);
    }

    public List<Chromossome> getListChromossomes() {
        return this.lchromossome;
    }
    
    public void setListChromossomes (List<Chromossome> listChromossome) {
        this.lchromossome = listChromossome;
    }
    
    public void clear() {
        this.lchromossome.clear();
    }
    
    public Set<String> getSetOfChromossomes() {
     
        for (Chromossome c : this.lchromossome) {
            this.listOfChromossomes.add(c.getName());         
        }  

        return listOfChromossomes;
    }
    

}

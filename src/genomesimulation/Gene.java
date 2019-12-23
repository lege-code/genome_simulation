/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genomesimulation;

/**
 *
 * @author jpaulo
 */
public class Gene {

    private int positionStart;
    private int positonStop;
    private Enum status;
    private String chromossome;
    private Gene parent;
    private String identifier;

    public Gene(String identifier, String chromossome, int positionStart, int positonStop, Enum status, Gene gene) {
        this.identifier = identifier;
        this.positionStart = positionStart;
        this.positonStop = positonStop;
        this.status = status;
        this.chromossome = chromossome;
        this.parent = gene;
    }

    public boolean isSameChromossome() {
        return (this.parent.chromossome.equals(this.chromossome));
    }

    public boolean isInactive() {
        return (State.ACTIVE == this.status);
    }
    

    public Gene getGene() {
        return this;
    }

    public Gene getParent() {
        return parent;
    }
    
    public String getIdentifier () {
        return this.identifier;
    }

    public void setParent(Gene parent) {
        this.parent = parent;
    }
    
    public void setInactive() {
        this.status = State.INACTIVE;        
    }
    
    public void setActive() {
        this.status = State.INACTIVE;
    }
    
    public Enum getStatus () {
        return this.status;
    }

    public void setStatus(State p) {
        this.status = p;
    }
    
    public boolean isSon() {
        return this.parent != null;
    }

    public String getChromossome() {
        return chromossome;
    }

    public String [] getCoordinates () {
        
        String [] coordinates = new String[3];
        
        coordinates[0] = this.chromossome;
        coordinates[1] = Integer.toString(positionStart);
        coordinates[2] = Integer.toString(positonStop);
        
        return coordinates;
    }
}

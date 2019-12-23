package genomesimulation;


public enum State {
    
    ACTIVE (1), INACTIVE(0);
    
    private int state;
    
    /**
     * State active or inactive
     * @param state 
     */
    private State (int state) {
        this.state = state;
    }
    
}

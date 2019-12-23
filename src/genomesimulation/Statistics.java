package genomesimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Statistics {

    private Karyotype k;

    public Statistics(Karyotype p) {
        this.k = p;
    }

    public HashMap chromossomeStatistics() {

        List<Chromossome> lchromossomes = k.getListChromossomes();
        HashMap<String, float[]> results = new HashMap<>();
        float TotalPseudos = 0;
        float TotalParents = 0;
        float ActiveSameCromo = 0;
        float ActiveDifCromo = 0;
        float InactiveSameCromo = 0;
        float InactiveDifCromo = 0;

        float[] vvalues = new float[4];

        int contgenes = 0; 
        
        for (Chromossome c : lchromossomes) {

            TotalPseudos = 0;
            TotalParents = 0;
            ActiveSameCromo = 0;
            ActiveDifCromo = 0;
            InactiveDifCromo = 0;
            InactiveSameCromo = 0;

            vvalues = new float[6];

            List<Gene> lgenes = c.getGenesList();

            contgenes = contgenes + lgenes.size();
            
            for (Gene g : lgenes) {

                if (g.isSon()) {
                    TotalPseudos++;
                }

                if (!g.isSon()) {
                    TotalParents++;
                }

                if (g.isSon() && (g.getStatus() == State.ACTIVE) && (g.isSameChromossome())) {
                    ActiveSameCromo++;

                }

                if (g.isSon() && (g.getStatus() == State.ACTIVE) && (!g.isSameChromossome())) {
                    ActiveDifCromo++;

                }

                if (g.isSon() && (g.getStatus() == State.INACTIVE) && (g.isSameChromossome())) {
                    InactiveSameCromo++;
                }
                if (g.isSon() && (g.getStatus() == State.INACTIVE) && (!g.isSameChromossome())) {
                    InactiveDifCromo++;
                }

            }
       
            vvalues[0] = ActiveSameCromo;
            vvalues[1] = InactiveSameCromo;
            //lvalues.add((ActiveSameCromo / (ActiveSameCromo + InactiveSameCromo)));

            vvalues[2] = ActiveDifCromo;
            vvalues[3] = InactiveDifCromo;
            //lvalues.add((ActiveDifCromo / (ActiveDifCromo + InactiveDifCromo)));

            vvalues[4] = TotalPseudos;
            vvalues[5] = TotalParents;
           
            results.put(c.getName(), vvalues);
        }

        
        return results;

    }

}

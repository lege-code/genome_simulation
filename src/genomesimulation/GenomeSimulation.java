package genomesimulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GenomeSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        String[] vline;
        String line;
        

        //read file with pseudogenes
        List<String> lFile = new ArrayList<>();
        Scanner filePseudo = new Scanner(new File("pseudo_genes.txt"));
        while (filePseudo.hasNext()) {
            line = filePseudo.nextLine();
            lFile.add(line);
        }
        filePseudo.close();

        //Generating random activation      
        for (int i = 0; i < 100; i++) {

            int actives = 663;
            
            Karyotype testKaryotype = new Karyotype();

            //new karyotype
            Scanner karyotypeFile = new Scanner(new File("karyotype.txt"));
            while (karyotypeFile.hasNext()) {
                line = karyotypeFile.nextLine();
                vline = line.split(";");
                Chromossome c = new Chromossome(vline[1], Integer.parseInt(vline[3]));
                testKaryotype.insertChromossomes(c);
            }
            karyotypeFile.close();

            List<Chromossome> lchromotest = testKaryotype.getListChromossomes();

            //Inserting parental genes
            Scanner parentalGenesFile = new Scanner(new File("parental_genes.txt"));
            while (parentalGenesFile.hasNext()) {
                line = parentalGenesFile.nextLine();
                vline = line.split("\t");
                Gene g = new Gene(vline[0], vline[1].replace("hs", ""), Integer.parseInt(vline[2]), Integer.parseInt(vline[3]), State.ACTIVE, null);

                for (Chromossome c : lchromotest) {
                    String name = c.getName();
                    if (vline[1].replace("hs", "").equals(name)) {
                        c.insertGenes(g);
                    }
                }
            }
            parentalGenesFile.close();

            //Shuffle
            long seed = System.nanoTime();
            Collections.shuffle(lFile, new Random(seed));

            for (String a : lFile) {

                vline = a.split("\t");
                Gene comp = null;

                Chromossome insert = null;

                boolean flag = false;

                lchromotest = testKaryotype.getListChromossomes();

                search:
                for (Chromossome c : lchromotest) {
                    List<Gene> lgenes = c.getGenesList();

                    insert = c;

                    for (Gene g : lgenes) {
                        String geneIden = g.getIdentifier();
                        if (geneIden.equalsIgnoreCase(vline[0])) {
                            flag = true;
                            comp = g;
                            break search;

                        }
                    }
                }
                if (flag) {
                    Gene son;

                    if (actives > 0) {
                        son = new Gene(vline[0], vline[1].replace("hs", ""), Integer.parseInt(vline[2]), Integer.parseInt(vline[3]), State.ACTIVE, comp);
                        actives--;
                        insert.insertGenes(son);
                    } else {
                        son = new Gene(vline[0], vline[1].replace("hs", ""), Integer.parseInt(vline[2]), Integer.parseInt(vline[3]), State.INACTIVE, comp);
                        insert.insertGenes(son);
                    }

                }
            }

            Statistics stat = new Statistics(testKaryotype);
            HashMap<String, float[]> results = stat.chromossomeStatistics();

            float[] vec = new float[4];

            for (Map.Entry<String, float[]> entry : results.entrySet()) {

                float[] vresults = entry.getValue();

                for (int j = 0; j < vresults.length; j++) {

                    if (j == 0) {
                        vec[0] = vec[0] + vresults[j];
                      
                    }

                    if (j == 1) {
                        vec[1] = vec[1] + vresults[j];
                    }

                    if (j == 2) {
                        vec[2] = vec[2] + vresults[j];
                    }

                    if (j == 3) {
                        vec[3] = vec[3] + vresults[j];
                    }

                }
            }
            float activosSamePer = (float) vec[0] / (vec[0] + vec[1]);
            float activosDifPer = (float)vec[2] / (vec[2] + vec[3]);

            System.out.println("Run" + (i + 1) + "\t" + activosSamePer + "\t" + (vec[0] + vec[1]) + "\t" + activosDifPer + "\t" + (vec[2] + vec[3]));

            testKaryotype.clear();

        }

    }

}

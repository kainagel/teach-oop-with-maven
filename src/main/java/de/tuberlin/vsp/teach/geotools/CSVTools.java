package de.tuberlin.vsp.teach.geotools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class CSVTools {


    static void printCSV(List<String> list, String outputFileName) {
        File outputFile = new File(outputFileName); // Insert Path to Output File Here!

        try (
                BufferedWriter out = new BufferedWriter(new FileWriter(outputFile))
        ) {
            for (String line : list){
                out.write(line);
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

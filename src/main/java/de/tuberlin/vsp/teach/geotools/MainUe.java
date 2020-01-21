package de.tuberlin.vsp.teach.geotools;

import org.geotools.data.FeatureReader;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class MainUe {

        public static void main(String[] args) throws IOException {

                final String pathname = "data/shapefiles/thueringen-kreise.shp";

                FileDataStore store = FileDataStoreFinder.getDataStore(new File(pathname));

                FeatureReader<SimpleFeatureType, SimpleFeature> reader = store.getFeatureReader();

                List<SimpleFeature> features = new ArrayList<>();
                for (; reader.hasNext(); ) {

                        SimpleFeature result = reader.next();
                        features.add(result);
                }

                reader.close();

                System.out.println(features.size());


                List<String> pointsString = new ArrayList<>();
                pointsString.add("x coord, y coord");


                for (SimpleFeature feature : features) {
                        double minX = feature.getBounds().getMinX();
                        double maxX = feature.getBounds().getMaxX();
                        double minY = feature.getBounds().getMinY();
                        double maxY = feature.getBounds().getMaxY();

                        for (int i = 0; i < 5; i++) {
                                double xNew = minX + (maxX-minX)*Math.random();
                                double yNew = minY + (maxY-minY)*Math.random();
                                pointsString.add(xNew + "," + yNew);
                        }

                }


                CSVTools.printCSV(pointsString, "data/coords.csv");
        }
}
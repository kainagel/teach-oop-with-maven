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
//                        System.out.println(result.toString());
//                        List<Object> attributes = result.getAttributes();
//                        for (Object o : result.getAttributes()) {
//                                System.out.println(o.toString());
//                        }
                }

                reader.close();

                System.out.println(features.size());

                List<String> pointsString = new ArrayList<>();
                pointsString.add("x coord, y coord");
//                pointsString.add(xNew + "," + yNew);

                CSVTools.printCSV(pointsString, "data/coords.csv");
        }
}

//                Point point = new GeometryFactory().createPoint(new Coordinate(xNew, yNew));



//
//
//                for (SimpleFeature feature : features) {
//                        BoundingBox bounds = feature.getBounds();
//                        Geometry polygon = (Geometry) feature.getAttributes().get(0);
//                        DirectPosition directPosition = new DirectPosition1D();
//                        if (polygon.contains(directPosition))
//
//                }



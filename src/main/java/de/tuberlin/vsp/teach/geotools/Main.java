package de.tuberlin.vsp.teach.geotools;

import org.geotools.data.FeatureReader;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.geometry.DirectPosition1D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.BoundingBox;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Geometry;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class Main{

        public static void main( String[] args ) throws IOException {

                final String pathname = "data/shapefiles/thueringen-kreise.shp";

                FileDataStore store = FileDataStoreFinder.getDataStore( new File( pathname ) );

                FeatureReader<SimpleFeatureType, SimpleFeature> reader = store.getFeatureReader();

                List<SimpleFeature> features = new ArrayList<>();
                for( ; reader.hasNext();){

                        SimpleFeature result = reader.next();
                        features.add(result);
//                        System.out.println(result.toString());
//
//                        List<Object> attributes = result.getAttributes();
//                        for (Object o : result.getAttributes()) {
//                                System.out.println(o.toString());
//                        }

                }

                reader.close();

                System.out.println(features.size());

                SimpleFeature feature = features.get(5);
                double minX = feature.getBounds().getMinX();
                double maxX = feature.getBounds().getMaxX();
                double minY = feature.getBounds().getMinY();
                double maxY = feature.getBounds().getMaxY();

                double xNew = (minX + maxX)/2 ;
                double yNew = (minY + maxY)/2 ;

                List<String> pointsString = new ArrayList<>();
                pointsString.add("x coord, y coord");
                pointsString.add(xNew + "," + yNew);

                File outputFile = new File("data/coords.csv"); // Insert Path to Output File Here!

                try (
                        BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
                ) {
                        for (String line : pointsString){
                                out.write(line);
                                out.newLine();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
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


        }



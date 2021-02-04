package de.tuberlin.vsp.teach.geotools;

import org.geotools.data.FeatureReader;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.util.ListenerList;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.*;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.BoundingBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Main {

        public static void main( String[] args ) throws IOException{

        final String pathname = "data/shapefiles/thueringen-kreise.shp";

                FileDataStore store = FileDataStoreFinder.getDataStore( new File( pathname ) );

                FeatureReader<SimpleFeatureType, SimpleFeature> reader = store.getFeatureReader();

                List<SimpleFeature> features = new ArrayList<>();
                for ( ; reader.hasNext(); ) {
                        SimpleFeature result = reader.next();
                        features.add(  result );
                }

                reader.close();

                for( SimpleFeature feature : features ){
                        BoundingBox bounds = feature.getBounds();
                        Geometry polygon = (Geometry) feature.getAttributes().get( 0 );
                }

                SimpleFeature aLandkreis = features.get(0);
                BoundingBox bb = aLandkreis.getBounds();
                Geometry polygon = (Geometry) aLandkreis.getAttributes().get( 0 );

                final GeometryFactory gf = JTSFactoryFinder.getGeometryFactory();
                FileWriter out = new FileWriter( new File( "/Users/kainagel/out2.csv" ) );
                out.write( "X;Y\n" );
                for ( int ii=0 ; ii<1000 ; ii++ ) {
                        double xx = bb.getMinX() + Math.random() * ( bb.getMaxX() - bb.getMinX() );
                        double yy = bb.getMinY() + Math.random() * ( bb.getMaxY() - bb.getMinY() );
                        final Point point = gf.createPoint( new Coordinate( xx, yy ) );
                        if ( polygon.contains( point ) ){
                                out.write( xx + ";" + yy + ";\n" );
                        }
                }
                out.close();



    }
}

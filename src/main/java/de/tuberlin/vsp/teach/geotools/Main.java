package de.tuberlin.vsp.teach.geotools;

import com.sun.media.jai.util.Rational;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Main {

        public static void main( String[] args ) throws IOException{

        final String pathname = "data/shapefiles/thueringen-kreise.shp";

                FileDataStore store = FileDataStoreFinder.getDataStore( new File( pathname ) );

                FeatureReader<SimpleFeatureType, SimpleFeature> reader = store.getFeatureReader();

                List<SimpleFeature> features = new ArrayList<>();
                while( reader.hasNext() ){
                        SimpleFeature result = reader.next();
                        features.add(  result );
                }
                reader.close();

                SimpleFeature feature = features.get( 0 );

                Geometry polygon = (Geometry) feature.getAttributes().get( 0 );

                FileWriter out = new FileWriter( new File( "./output/out12.csv" ) );

                final GeometryFactory gf = JTSFactoryFinder.getGeometryFactory();

                polygon = polygon.buffer( 2000. );

                Envelope env = polygon.getEnvelopeInternal();

                out.write( "X;Y\n" );
                for ( int ii=0 ; ii<1000 ; ii++ ) {
                        double xx = env.getMinX() + Math.random() * ( env.getMaxX() - env.getMinX() );
                        double yy = env.getMinY() + Math.random() * ( env.getMaxY() - env.getMinY() );
                        final Point point = gf.createPoint( new Coordinate( xx, yy ) );
                        if ( polygon.contains( point ) ){
                                out.write( xx + ";" + yy + ";\n" );
                        }
                }
                out.close();


    }
}

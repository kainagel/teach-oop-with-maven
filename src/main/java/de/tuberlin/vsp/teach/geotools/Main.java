package de.tuberlin.vsp.teach.geotools;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;

import java.io.File;
import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {

        final String pathname = "data/shapefiles/thueringen-kreise.shp";

        FileDataStore store = FileDataStoreFinder.getDataStore(new File(pathname));

    }
}

package de.tuberlin.vsp.teach.htmlParsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

class Main{

	public static void main( String[] args ) throws IOException {

		// prepare the visitor, i.e. what will be done while walking the file tree:
		FileVisitor<? super Path> visitor = new FileVisitor<Path>(){
			@Override public FileVisitResult preVisitDirectory( Path dir, BasicFileAttributes attrs ) {
				return FileVisitResult.CONTINUE;
				// (= do nothing)
			}
			@Override public FileVisitResult visitFile( Path path, BasicFileAttributes attrs ) throws IOException{
				// this is what will be done with "path".

				if ( Files.isDirectory( path ) ) {
					return FileVisitResult.CONTINUE;
					// (we do not do anything if path is a directory)
				}
				if ( ! path.toString().endsWith( ".html" ) ) {
					return FileVisitResult.CONTINUE;
					// (we are only interested in html files)
				}
				try{
					extractContent( path.toString() );
					// (this calls my own method written below)
					// (I do not really understand why the try/catch is needed here.)
				} catch( ParseException e ){
					throw new RuntimeException( e );
				}
				return FileVisitResult.CONTINUE;
				// (= if we are done with _this_ file, we want to do the next one)
			}
			@Override public FileVisitResult visitFileFailed( Path file, IOException exc ) {
				return FileVisitResult.TERMINATE;
				// (= we terminate if we have a file that fails.  I.e. we will not attempt to continue with files that come later.)
			}
			@Override public FileVisitResult postVisitDirectory( Path dir, IOException exc ) {
				return FileVisitResult.CONTINUE;
				// (= do nothing)
			}
		};

		Path start = Paths.get("/Users/kainagel/shared-svn/studies/countries/de/prins-bvwp-2016b/bvwp-projekte.de/strasse/");

		int maxDepth = 2;
		// (I think that we have one directory per "main" project, and we need depth=2 to access those.  The sub-projects would (presumably) be
		// depth=3, but we do not want them.)

		Set<FileVisitOption> options = Collections.emptySet();
		// (I don't know what can be used here; I used an empty set and it worked.)

		// walk the file tree, with the above preparations:
		Files.walkFileTree( start, options, maxDepth, visitor );

	}
	private static void extractContent( String pathname ) throws IOException, ParseException{
		Document doc = Jsoup.parse( new File( pathname ), "UTF-8", "" );
		// (jsoup command for files.  Also possible for URLs, but slightly different syntax.)

		// get the project ID and the project label by brute force:
		Elements lists = doc.select( "li" ); // select all <li> elements
		String projectId = lists.get(0).text(); // get number zero of those for the project id
		String projectLabel = lists.get(1).text();  // get number one of those for the project label

		// all the material that we are interested in (Mengen, Bewertungen) sind in Tabellen.  So go through all tables:
		for( Element table : doc.select( "table" ) ) {

			if ( !table.attr( "class" ).equals( "table_wirkung_strasse" ) ) {
				continue;
			}
			// (wir interessieren uns erstmal nur für die Tabelle table_wirkung_strasse; da sind die Mengen-(= Wirkungs-)Gerüste drin.

			// go through all table rows <tr>:
			for( Element tableRow : table.select( "tr" ) ){

				Elements tableEntries = tableRow.select( "td" );
				// (these are the table data cells in the table row)

				if( tableEntries.size() == 0 ){
					continue;
				}
				// (hedge against rows that do not contain material)

				if( tableEntries.get( 0 ).text().contains( "Kohlendioxid" ) ){
					// (this searches for the "Kohlendioxid" entry)

					// we need to set the number format so it deals with the German decimal comma:
					NumberFormat format = NumberFormat.getInstance( Locale.GERMANY );
					// there are three entries for Kohlendioxid: Pkw, Lkw, Sum.  This is already one of the more difficult cases:
					double value = format.parse(tableEntries.get(3).text() ).doubleValue();
					// I was only interested in projects with a large CO2 footprint:
					if ( value > 15000 ){
						System.out.printf( "%20s\t%5.0f\t%20s\n", projectId, value, projectLabel );
					}
				}

			}
		}
	}

}

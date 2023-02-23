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

//		Document doc = Jsoup.connect("http://example.com" ).get();

		Path start = Paths.get("/Users/kainagel/shared-svn/studies/countries/de/prins-bvwp-2016b/bvwp-projekte.de/strasse/");
		int maxDepth = 2;
		Set<FileVisitOption> options = Collections.emptySet();
		FileVisitor<? super Path> visitor = new FileVisitor<Path>(){
			@Override public FileVisitResult preVisitDirectory( Path dir, BasicFileAttributes attrs ) {
				return FileVisitResult.CONTINUE;
			}
			@Override public FileVisitResult visitFile( Path file, BasicFileAttributes attrs ) throws IOException{
				if ( Files.isDirectory( file ) ) {
					return FileVisitResult.CONTINUE;
				}
				if ( ! file.toString().endsWith( ".html" ) ) {
					return FileVisitResult.CONTINUE;
				}
//				System.out.println( file );
				try{
					extractContent( file.toString() );
				} catch( ParseException e ){
					throw new RuntimeException( e );
				}
				return FileVisitResult.CONTINUE;
			}
			@Override public FileVisitResult visitFileFailed( Path file, IOException exc ) {
				return FileVisitResult.TERMINATE;
			}
			@Override public FileVisitResult postVisitDirectory( Path dir, IOException exc ) {
				return FileVisitResult.CONTINUE;
			}
		};
		Files.walkFileTree( start, options, maxDepth, visitor );

	}
	private static void extractContent( String pathname ) throws IOException, ParseException{
		Document doc = Jsoup.parse( new File( pathname ), "UTF-8", "" );

		Elements lists = doc.select( "li" );
		String projectId = lists.get(0).text();
		String projectLabel = lists.get(1).text();

		Elements tables = doc.select( "table" );
		for( Element table : tables ) {

			if ( !table.attr( "class" ).equals( "table_wirkung_strasse" ) ) {
				continue;
			}

			Elements tableRows = table.select( "tr" );
			for( Element tableRow : tableRows ){

				Elements tableEntries = tableRow.select( "td" );

				if( tableEntries.size() == 0 ){
					continue;
				}

				if( tableEntries.get( 0 ).text().contains( "Kohlendioxid" ) ){
//					for( Element tableEntry : tableEntries ){
//						System.out.println( "tableEntry=" + tableEntry.text() );
//					}


					NumberFormat format = NumberFormat.getInstance( Locale.GERMANY );
					double value = format.parse(tableEntries.get(3).text() ).doubleValue();
					if ( value > 15000 ){
						System.out.printf( "%20s\t%5.0f\t%20s\n", projectId, value, projectLabel );
					}
				}

			}
		}
	}

}

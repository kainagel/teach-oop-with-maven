package de.tuberlin.vsp.teach.validatingStaxParser;

import org.codehaus.stax2.XMLInputFactory2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;

public class MyXMLReader {

	private static int indent = 0 ;
	
	public static void main( String[] args ) {

		final String root = "src/main/java/de/tuberlin/vsp/teach/validatingStaxParser/";
		File file = new File( root + "plans.xml" );

		try ( FileInputStream stream = new FileInputStream(file) ; ) {
			XMLInputFactory factory = XMLInputFactory2.newFactory();
			factory.setProperty(XMLInputFactory.IS_VALIDATING, true); // does not work with java standard XMLInputFactory.
			XMLStreamReader in = factory.createXMLStreamReader( stream );

			// stream of events:
//			while (in.hasNext()) {
//				int eventTypeAsInt = in.next();
//				System.out.println( " eventType: " + eventTypeAsInt ) ;
//				printEventType(eventTypeAsInt) ;
//			}
				
			while ( in.hasNext() ) {
				in.next() ; // advance one step; we don't need the result

				if ( in.isStartElement() ) {
					printAndIncrementIndent() ;
					String elementName = in.getLocalName();
					System.out.print( elementName + ": " ) ;
					for ( int ii=0 ; ii<in.getAttributeCount() ; ii++ ) {
						String key = in.getAttributeLocalName(ii ) ;
						String value = in.getAttributeValue(ii ) ;
						System.out.print( key + "=" + value + " " ) ;
					}
					//text aus dem element rausholen ist einfacher als via isCharacters()
					if ("route".equalsIgnoreCase(in.getLocalName())) {
						System.out.print(in.getElementText() );
						indent-- ; // (swallows route end element)
					}
					System.out.println() ;
				} else if ( in.isEndElement() ) {
					decrementAndPrintIndent() ;
					System.out.println( "end of: " + in.getLocalName() ) ;
//				} else if ( in.isCharacters() ) {
//										System.out.print( in.getText() ) ;
//					// text is a bit messy to parse; I would avoid it as long as the document is not
//					// a true text document.
				}
			}

		} catch ( Exception e) {
			throw new RuntimeException(e) ;
		}

	}


	private static void printAndIncrementIndent() {
		for ( int ii=0 ; ii<indent ; ii++ ) {
			System.out.print("   " ) ;
		}
		indent++ ;
	}
	private static void decrementAndPrintIndent() {
		indent-- ;
		for ( int ii=0 ; ii<indent ; ii++ ) {
			System.out.print("   " ) ;
		}
	}

	/**
	 * Returns the String representation of the given integer constant.
	 *
	 * @param eventType Type of event.
	 * @return String representation of the event
	 */    
	private final static String getEventTypeString( int eventType ) {
		switch (eventType){
		case XMLEvent.START_ELEMENT:
			return "START_ELEMENT";
		case XMLEvent.END_ELEMENT:
			return "END_ELEMENT";
		case XMLEvent.PROCESSING_INSTRUCTION:
			return "PROCESSING_INSTRUCTION";
		case XMLEvent.CHARACTERS:
			return "CHARACTERS";
		case XMLEvent.COMMENT:
			return "COMMENT";
		case XMLEvent.START_DOCUMENT:
			return "START_DOCUMENT";
		case XMLEvent.END_DOCUMENT:
			return "END_DOCUMENT";
		case XMLEvent.ENTITY_REFERENCE:
			return "ENTITY_REFERENCE";
		case XMLEvent.ATTRIBUTE:
			return "ATTRIBUTE";
		case XMLEvent.DTD:
			return "DTD";
		case XMLEvent.CDATA:
			return "CDATA";
		case XMLEvent.SPACE:
			return "SPACE";
		}
		return "UNKNOWN_EVENT_TYPE , " + eventType;
	}

	private static void printEventType(int eventType) {        
		System.out.println("EVENT TYPE("+eventType+") = " + getEventTypeString(eventType ) );
	}


}

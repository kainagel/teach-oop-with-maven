package de.tuberlin.vsp.teach.sandbox;

class Main{

	int count;

	static void aMethod( Person person ) {
		System.out.println("static person method");
	}
	static boolean aMethod( Child child ) {
		System.out.println("static child method");
		return false;
	}
	public static void main( String[] args ){

		Person person = new Child() ;

		extracted( person );

	}
	private static void extracted( Person person ){
		aMethod( person );

		person.aMethod();
	}

}

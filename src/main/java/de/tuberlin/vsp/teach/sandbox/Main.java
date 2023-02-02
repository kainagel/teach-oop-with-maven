package de.tuberlin.vsp.teach.sandbox;

class Main{

	static class Person {
		void aMethod() {
			System.out.println("dynamic person method");
		}
	}

	static class Child extends Person {
		@Override void aMethod() {
			System.out.println("dynamic child method");
		}
	}

	static void aMethod( Person person ) {
		System.out.println("static person method");
	}
	static void aMethod( Child child ) {
		System.out.println("static child method");
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

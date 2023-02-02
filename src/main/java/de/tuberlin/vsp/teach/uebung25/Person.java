package de.tuberlin.vsp.teach.uebung25;

class Person{

	private final double age;
	private final String name;
	private final boolean hasDrivingLicense;

	Person( double age, String name, boolean hasDrivingLicense ){
		this.age = age;
		this.name = name;
		this.hasDrivingLicense = hasDrivingLicense;
	}
	public double getAge(){
		return age;
	}
	public String getName(){
		return name;
	}
	public boolean isHasDrivingLicense(){
		return hasDrivingLicense;
	}
}

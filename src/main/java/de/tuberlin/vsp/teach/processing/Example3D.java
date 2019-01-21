package de.tuberlin.vsp.teach.processing;

import processing.core.PApplet;

public class Example3D extends PApplet {
	private final int xsize=600, ysize=600 ;

	private double xx=xsize/2 ;
	private double yy=ysize/2 ;
	private double dx=Math.random() ;
	private double dy=Math.random() ;

	public static void main( String[] args ) {
		PApplet.main( new String[] { "--present", "de.tuberlin.vsp.teach.processing.Example3D"}  );
	}

	@Override
	public void draw() {
		background(0) ;
		lights() ;
		
		noStroke();

		pushMatrix();
		translate( (float) xx, (float) yy, 0);
		rotateY(1.25f);
		rotateX(-0.4f);
		box(100);
		popMatrix();

		xx += dx ;
		yy += dy ;

		if ( xx >= xsize ) {
			dx = - 10.*pow( (float) Math.random(),2 ) - 1 ;
		}
		if ( xx <=0 ) {
			dx = 10.*pow( (float) Math.random(), 2 ) + 1 ;
		}
		if ( yy >= ysize ) {
			dy = - 10.*pow( (float) Math.random(), 2 ) - 1 ;
		}
		if ( yy <= 0 ) {
			dy = 10.* pow( (float) Math.random(), 2 ) + 1 ;
		}

	}
	
	@Override
	public void settings() { // setup does not work here when not using the PDE
		size(xsize,ysize, P3D );
	}


}

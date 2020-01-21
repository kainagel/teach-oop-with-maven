//package de.tuberlin.vsp.teach.processing;
//
//import processing.core.PApplet;
//
//public class Example3D extends PApplet {
//	private double xx=10. ;
//	private double yy=10. ;
//	private double dx=Math.random() + 10. ;
//	private double dy=Math.random() + 10. ;
//
//	public static void main( String[] args ) {
//		PApplet.main( new String[] { "--present", "de.tuberlin.vsp.teach.processing.Example3D"}  );
//	}
//
//	float ii  = 0 ;
//	final double radius = 50. ;
//
//	@Override
//	public void draw() {
//		ii+=1;
//
//		background(127) ;
//		lights() ;
//
//		noStroke();
//		this.fill(255, 123, 0, 255);
//
////		ortho(-width/2, width/2, -height/2, height/2);
//
//		pushMatrix();
//		translate( (float) xx, (float) yy, -ii);
////		rotateY(1.25f);
////		rotateX(-0.4f);
////		rotateZ(ii) ;
//		box(100);
////		this.sphere((float) radius);
//		popMatrix();
//
//		xx += dx ;
//		yy += dy ;
//
//		if ( xx >= width-radius ) {
//			dx = - 10.*Math.pow( Math.random(),2 ) - 1 ;
//		}
//		if ( xx <=0+radius ) {
//			dx = 10.*Math.pow( Math.random(), 2 ) + 1 ;
//		}
//		if ( yy >= height-radius ) {
//			dy = - 10.*Math.pow( Math.random(), 2 ) - 1 ;
//		}
//		if ( yy <= 0+radius ) {
//			dy = 10.* Math.pow( Math.random(), 2 ) + 1 ;
//		}
//
//	}
//
//	@Override
//	public void settings() { // setup does not work here when not using the PDE
//		this.fullScreen(P3D);
//	}
//
//
//}

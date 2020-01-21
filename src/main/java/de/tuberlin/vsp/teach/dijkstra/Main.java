//package de.tuberlin.vsp.teach.dijkstra;
//
//import org.matsim.api.core.v01.Id;
//import org.matsim.api.core.v01.network.Link;
//import org.matsim.api.core.v01.network.Network;
//import org.matsim.api.core.v01.network.Node;
//
//import java.util.*;
//
//class Main{
//
//	public static void main( String[] args ){
//
//		Network network = null ;
//
//		Queue<DNode> pendingNodes = new PriorityQueue<>( new Comparator<DNode>(){
//			@Override public int compare( DNode o1, DNode o2 ){
//				return Double.compare( o1.getCost(), o2.getCost() ) ;
//			}
//		} ) ;
//
//		Map<Id<Node>,DNode> dnodes = new LinkedHashMap<>() ;
//		for( Node node : network.getNodes().values() ){
//			DNode dNode = new DNode( node ) ;
//			dNode.setCost( Double.POSITIVE_INFINITY );
//			dnodes.put(  node.getId(), dNode ) ;
//			pendingNodes.add(  dNode ) ;
//		}
//
//		Node startNode = null ;
//		Node destNode = null ;
//		dnodes.get(  startNode.getId() ).setCost( 0 );
//		// yyyy can we change sorting after insertion??
//
//		while (true){
//			DNode currentNode = pendingNodes.remove();
//			if ( currentNode.getNode().getId().equals( destNode.getId() ) ) {
//				break ;
//			}
//			double currentCost = currentNode.getCost();
//			for( Link link : currentNode.getNode().getOutLinks().values() ){
//				DNode nextDNode = dnodes.get( link.getToNode().getId() );
//				;
//				final double possibleCost = currentCost + link.getLength() / link.getFreespeed();
//				if( possibleCost < nextDNode.getCost() ){
//					nextDNode.setCost( possibleCost );
//					nextDNode.setPrevNode( currentNode );
//				}
//			}
//		}
//
//	}
//
//	static class DNode {
//		private final Node node;
//		private double cost ;
//		private DNode prevNode;
//		DNode( Node node ) {
//			this.node = node ;
//		}
//		Node getNode(){
//			return node;
//		}
//		double getCost(){
//			return cost;
//		}
//		void setCost( double cost ){
//			this.cost = cost;
//		}
//		void setPrevNode( DNode prevNode ){
//			this.prevNode = prevNode;
//		}
//		public DNode getPrevNode(){
//			return prevNode;
//		}
//	}
//
//}

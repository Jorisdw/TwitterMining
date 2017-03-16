import java.util.ArrayList;
import java.util.Set;

import org.jgraph.graph.*;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;




public class ExtractDenseSubgraphs {

	//private SimpleWeightedGraph<String, DefaultWeightedEdge> graph;
	
	public ExtractDenseSubgraphs(){
		//this.graph = graph;
	}

	public ArrayList<String> getLowestDensityVertex(SimpleWeightedGraph<String, DefaultWeightedEdge> graph){
		Set<String> verteces = graph.vertexSet();
		ArrayList<String> selected = new ArrayList<String>();
		int min = Integer.MAX_VALUE;
		for(String vertex : verteces){
			int t = getVertexDegree(vertex, graph);
			if(t < min){
				min = t;
				selected.clear();
			}
			if(t == min){
				selected.add(vertex);
			}
		}
		return selected;
	}
	
	public void deleteLowestDensityVertex(SimpleWeightedGraph<String, DefaultWeightedEdge> gr) {
		gr.removeAllVertices(getLowestDensityVertex(gr));
	}
	
	public double getAverageDegreeDensity(SimpleWeightedGraph<String, DefaultWeightedEdge> gr){
		if(gr == null) return Integer.MAX_VALUE;
		double deg = 0;
		Set<DefaultWeightedEdge > edgs = gr.edgeSet();
		for (DefaultWeightedEdge edg : edgs)
		{
			deg += gr.getEdgeWeight(edg);
		}
		return deg/gr.vertexSet().size();
	}
	
	public double getAverageDegreeDensity1(SimpleWeightedGraph<String, DefaultWeightedEdge> gr){
		if(gr == null) return Integer.MAX_VALUE;
		return gr.edgeSet().size()/gr.vertexSet().size();
	}
	
	public SimpleWeightedGraph<String, DefaultWeightedEdge> densestSubgraphAlgorithmLinear(SimpleWeightedGraph<String, DefaultWeightedEdge> gr){
		double bestAverageDegreeDensity = getAverageDegreeDensity(gr);
		SimpleWeightedGraph<String, DefaultWeightedEdge> H = (SimpleWeightedGraph<String, DefaultWeightedEdge>) gr.clone();
		System.out.println(bestAverageDegreeDensity);
		while(!gr.edgeSet().isEmpty()){
			//System.out.println(gr.edgeSet().size());
			deleteLowestDensityVertex(gr);
			double d1 = getAverageDegreeDensity(gr);
			double d2 = getAverageDegreeDensity(H);
			//System.out.println("d1 gr = " + d1 + "           d2 H= " + d2);
			if(d1 > d2){
				//System.out.println("d1 gr = " + d1 + "           d2 H= " + d2);
				
				H = (SimpleWeightedGraph<String, DefaultWeightedEdge>) gr.clone();
				bestAverageDegreeDensity = d1;
			}
		}
		System.out.println(bestAverageDegreeDensity);
		
		return H;
	}
	
	public SimpleWeightedGraph<String, DefaultWeightedEdge> densestSubgraphAlgorithmLinearBtwn210(SimpleWeightedGraph<String, DefaultWeightedEdge> gr){
		double bestAverageDegreeDensity = getAverageDegreeDensity(gr);
		SimpleWeightedGraph<String, DefaultWeightedEdge> H = (SimpleWeightedGraph<String, DefaultWeightedEdge>) gr.clone();
		System.out.println(bestAverageDegreeDensity);
		while(!gr.edgeSet().isEmpty()){
			//System.out.println(gr.edgeSet().size());
			deleteLowestDensityVertex(gr);
			while(gr.edgeSet().size() > 11) {
				System.out.println(gr.edgeSet().size());
				deleteLowestDensityVertex(gr);
			}
			double d1 = getAverageDegreeDensity(gr);
			double d2 = getAverageDegreeDensity(H);
			if((d1 > d2) && (gr.vertexSet().size()  < 11)&& (gr.vertexSet().size() > 1) ){
				H = (SimpleWeightedGraph<String, DefaultWeightedEdge>) gr.clone();
				bestAverageDegreeDensity = d1;
			}
		}
		System.out.println(bestAverageDegreeDensity);
		
		return H;
	}
	
	public int getVertexDegree(String vertex, SimpleWeightedGraph< String , DefaultWeightedEdge> graph)
	{
		int degree = 0;
		Set <DefaultWeightedEdge> edges = graph.edgesOf(vertex);
		for(DefaultWeightedEdge edg : edges)
			degree += graph.getEdgeWeight(edg); 
		return degree;
	}
	
	public SimpleWeightedGraph<String, DefaultWeightedEdge> deleteSubgraphEdges(SimpleWeightedGraph<String, DefaultWeightedEdge> subgraph, 
													SimpleWeightedGraph<String, DefaultWeightedEdge> graph){
		graph.removeAllEdges(subgraph.edgeSet());
		return graph;
	}
	
	public SimpleWeightedGraph<String, DefaultWeightedEdge> deleteSubgraphVertexes(SimpleWeightedGraph<String, DefaultWeightedEdge> subgraph, 
													SimpleWeightedGraph<String, DefaultWeightedEdge> graph){
			graph.removeAllVertices(subgraph.vertexSet());
			return graph;
}
	
}

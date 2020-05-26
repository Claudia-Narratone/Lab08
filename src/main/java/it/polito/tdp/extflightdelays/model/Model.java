package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMap;
	private ExtFlightDelaysDAO dao;
	
	public Model() {
		idMap=new HashMap<Integer, Airport>();
		dao=new ExtFlightDelaysDAO();
		dao.loadAllAirports(idMap);
	}
	
	
	public void creaGrafo(int distanzaMedia) {
		this.grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		for(Rotta r:dao.getRotte(idMap, distanzaMedia)) {
			//controllo che non esista ancora l'arco, se esiste aggiorno peso
			DefaultWeightedEdge edge=grafo.getEdge(r.getOriginAirport(), r.getDestinationAirport());
			if(edge==null)
				Graphs.addEdge(grafo, r.getOriginAirport(), r.getDestinationAirport(), r.getDistanza());
			else {
				double peso = grafo.getEdgeWeight(edge);
				double newPeso = (peso + r.getDistanza())/2;
				grafo.setEdgeWeight(edge, newPeso);
			}
		}
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Rotta> getRotte(){
		List<Rotta> rotteList=new ArrayList<Rotta>();
		for(DefaultWeightedEdge e: this.grafo.edgeSet()) {
			rotteList.add(new Rotta(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e), this.grafo.getEdgeWeight(e)));
		}
		return rotteList;
	}
}

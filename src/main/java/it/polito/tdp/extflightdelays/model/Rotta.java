package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	private Airport originAirport;
	private Airport destinationAirport;
	private double distanza;
	
	public Rotta(Airport originAirport, Airport destinationAirport, double distanza) {
		super();
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.distanza = distanza;
	}

	public Airport getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public double getDistanza() {
		return distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}

	@Override
	public String toString() {
		return "Rotta [originAirport=" + originAirport + ", destinationAirport=" + destinationAirport + ", distanza="
				+ distanza + "]";
	}
	
	

}

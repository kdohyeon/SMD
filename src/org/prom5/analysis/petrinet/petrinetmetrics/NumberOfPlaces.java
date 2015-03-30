package org.prom5.analysis.petrinet.petrinetmetrics;

import org.prom5.framework.models.petrinet.PetriNet;

public class NumberOfPlaces implements ICalculator {
	private PetriNet net;
	private final static String TYPE = "Size";
	private final static String NAME = "Places";


	public NumberOfPlaces(PetriNet net) {
		super();
		this.net = net;
	}

	public String Calculate() {
		int result = net.getPlaces().size();
		String output = ""+result;
		return output;
	}

	public String getName() {
		return this.NAME;
	}

	public String getType() {
		return this.TYPE;
	}

	public String VerifyBasicRequirements() {
		return ".";
	}

}
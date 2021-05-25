package me.noahvdaa.ubalance.model;

public class TargetServer {

	private String name;
	private int weight;
	private boolean requireOnline;
	private boolean requireSlotsAvailable;

	public TargetServer(String name, int weight, boolean requireOnline, boolean requireSlotsAvailable) {
		this.name = name;
		this.weight = weight;
		this.requireOnline = requireOnline;
		this.requireSlotsAvailable = requireSlotsAvailable;
	}

	public String getName() {
		return this.name;
	}

	public int getWeight() {
		return this.weight;
	}

	public boolean getRequireOnline() {
		return this.requireOnline;
	}

	public boolean getRequireSlotsAvailable() {
		return this.requireSlotsAvailable;
	}

}

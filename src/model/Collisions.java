package model;

import physics.*;

public class Collisions {
	private double tuc;
	private Vect velo;

	public Collisions(double t, Vect v) {
		tuc = t;
		velo = v;
	}

	public double getTuc() {
		return tuc;
	}
	
	public Vect getVelo() {
		return velo;
	}
}

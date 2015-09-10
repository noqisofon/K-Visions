package org.kvisions.groups;

import org.kvisions.groups.SL2C;
import org.kvisions.schottky.figure.Circle;

public interface SchottkyGroup {
	public SL2C[] getGens();
	public Circle[] getCircles();
}

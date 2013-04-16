package com.github.tbrien.gentest.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolverTest {

	@Test
	public void test() {
		Solver s = new Solver(1, -1, 0);
		s.calcDelta();
		double d = s.calcFirstSol() - s.calcSecondSol();
		assert(d==0);
		
	}

}

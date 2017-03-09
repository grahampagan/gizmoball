package main;

import static org.junit.Assert.*;

import org.junit.Test;
import model.*;

public class MainTesting {
	
	board b = new board();

	@Test
	public void testAddGizmos() {
		Square s=new Square(10, 10, 30, 30, "s", 5, 5);
		gizmo sg = new squareGizmo(s, 30, 30, "sq");
		//assertTrue(b.addGizmo(sg, 12, 5));
		
		
	}

}

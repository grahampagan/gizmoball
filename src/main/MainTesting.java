package main;

import model.*;



public class MainTesting {
	
	//STILL TO DO ASSERT STATEMENTS
	//AND CHECK IF THINGS ARE BEING
	//ADDED CORRECTLY
	
	//RUN MODE TESTS
	
	Model b = new Model();

	@Test
	public void testAddSquare() {
		
		//add a square to the board
		Square s=new Square(10, 10, 3, 3, "s", 5, 5);
		b.addSquare(s);
		assertEquals(b.containsName("s"), true);
		
	}
	
	@Test
	public void testAddCircles(){
		
		
		//add a circle to the board
		circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);	
		b.addCircle(c);
		assertEquals(b.containsName("Circle"), true);
	}	
	
	@Test
	public void testAddTriangles(){
		//add a triangle to the board
		Triangle t = new Triangle(5,5, 2,10, 10, 10, "t", 13,13);
		b.addTriangle(t);
		assertEquals(b.containsName("t"), true);
	}
	
	@Test
	public void testAddBall(){
		//add a ball to the board
		Ball ball = new Ball(3,3,200,200,"b");	
		//b.addBall(ball);
		//assertEquals(b.atPosition(3,3), true);
	}
	
	@Test
	public void testAddAbsorber(){
		//add an absorber to the board
		Absorber a = new Absorber(18, 18, 5, 2, "ab");
		b.addAbsorber(a);
		assertEquals(b.containsName("ab"), true);
	}
	
	@Test
	public void testAddFlipper(){
		//add a flipper to the board
		Flipper f = new Flipper();
		//b.addFlipper(f);
		//assertEquals(b.containsName("f"), true);
		
	}
	
	@Test 
	public void testRotate(){
		//rotate a triangle
		Triangle t = new Triangle(3,3,1,8,8,8,"tri", 12,12);
		b.addTriangle(t);
		b.rotate("tri");
	}
	
	@Test
	public void testRemove(){
		Square s = new Square(10,10,3,3,"s",5,5);
		b.addSquare(s);
		
		//b.addGizmo(sq, 10, 10);
		//assertTrue(b.removeGizmo(10,10));
		
		
	}
	
	@Test
	public void testDelete(){
		Square s = new Square(10,10,5,5,"s",8,8);
		b.addSquare(s);
		b.delete("sg");
		assertEquals(b.containsName("sg"), false);
		
	}
	
	@Test
	public void testMove(){	
	Square s = new Square(10,10,5,5,"s",8,8);
	b.addSquare(s);
	b.move(10,10,15,15);
	//assertFalse(b.atPosition(9, 9));
	}
	
	@Test
	public void testClear(){
		Square s = new Square(10,10,5,5,"s",8,8);
		b.addSquare(s);
		
		Triangle t = new Triangle(3,3,1,8,8,8,"tri", 12,12);
		b.addTriangle(t);
		
		b.clearBoard();
		
		assertEquals((b.containsName("s")||b.containsName("tri")), false);
		
	}
	
	@Test
	public void testFriction(){
		
	}
	
	@Test
	public void testGravity(){
		b.setGravity(11.2);
		assertTrue(b.getGraivty()==11.2);
	}
	
	@Test
	public void testConnect(){
		
	}
	
	@Test
	public void testDisconnect(){
		
	}
	
	@Test
	public void testKeyConnect(){
		
	}
	
	@Test
	public void testKeyDisconnect(){
		
	}
	
	@Test
	public void testLoadBuild(){
		
	}
	
	@Test
	public void testReloadBuild(){
		
	}
	
	@Test
	public void testRunMode(){
		b.setBuildMode(true);
		assertEquals(b.getBuildMode(), true);
	}
	
	//BUILD MODE TESTS
	
	@Test
	public void testStart(){
		
	}
	
	@Test
	public void testStop(){
		
	}
	
	@Test
	public void testTick(){
		
	}
	
	@Test
	public void testLoadRun(){
		
	}
	
	@Test
	public void testReloadRun(){
		
	}
	
	@Test
	public void testBuild(){
		
	}

}



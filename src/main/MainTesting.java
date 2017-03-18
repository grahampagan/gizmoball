package main;

import static org.junit.Assert.*;
import model.*;
import java.io.IOException;

import org.junit.Test;

public class MainTesting{

		
		//STILL TO DO ASSERT STATEMENTS
		//AND CHECK IF THINGS ARE BEING
		//ADDED CORRECTLY
		
		//RUN MODE TESTS
		
		Model b = new Model();

		@Test
		public void testAddSquare() throws IOException{
			
			//add a square to the board
			Square s=new Square(10, 10, 3, 3, "s", 5, 5);
			b.addSquare(s);
			assertEquals(b.containsName("s"), true);
			assertEquals(s.getX(), 10);
			assertEquals(s.getY(), 10);
			
		}
		
		@Test
		public void testAddCircles(){
			
			
			//add a circle to the board
			circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);	
			b.addCircle(c);
			assertEquals(b.containsName("Circle"), true);
			assertEquals(c.getPositionX(), 12);
			assertEquals(c.getPositionY(), 12);
			
		}	
		
		@Test
		public void testAddTriangles(){
			//add a triangle to the board
			Triangle t = new Triangle(5,5, 2,10, 10, 10, "t", 13,13);
			b.addTriangle(t);
			assertEquals(b.containsName("t"), true);
			assertEquals(t.getPositionX(), 13);
			assertEquals(t.getPositionY(), 13);
		}
		
		@Test
		public void testAddBall(){
			//add a ball to the board
			Ball ball = new Ball(3,3,200,200,"b");	
			//b.addBall(ball);
			//b.addBall(ball);
			assertEquals(b.containsName("b"), true);
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
			Flipper f = new Flipper(5,4);
			b.addFlipper(f);
			assertEquals(b.containsName("f"), true);
			assertEquals(f.getPositionX(), 5);
			assertEquals(f.getPositionY(), 4);
			
		}
		
		@Test 
		public void testRotate(){
			//rotate a triangle
			Triangle t = new Triangle(3,3,1,8,8,8,"tri", 12,12);
			b.addTriangle(t);
			assertEquals(t.getRotations(), 0);
			b.rotate("tri");
			assertEquals(t.getRotations(), 1);
			b.rotate("tri");
			assertEquals(t.getRotations(), 2);
			
		}
		
		//not sure of the difference between this and delete
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
		assertEquals(s.getX(), 10);
		assertEquals(s.getY(), 10);	
		b.move(10,10,15,12);
		assertEquals(s.getX(), 15);
		assertEquals(s.getY(), 12);
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
			b.setMu(12.21);
			b.setMu2(13.32);
			assertEquals(b.getMu(), 12.21);
			assertEquals(b.getMu2(), 13);
		}
		
		@Test
		public void testGravity(){
			b.setGravity(11.2);
			assertTrue(b.getGraivty()==11.2);
		}
		
		@Test
		public void testConnect(){
			Square s = new Square(10,10,5,5,"s",8,8);
			circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);
			assertEquals(s.isConnected(), false);
			s.setConnected("Circle");
			assertEquals(s.isConnected(), true);
			//assertEquals(c.isConnected(), true);
		}
		
		@Test
		public void testDisconnect(){
			Square s = new Square(10,10,5,5,"s",8,8);
			circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);
			s.setConnected("Circle");
			assertEquals(s.isConnected(), true);
			s.setConnected("Circle");
			//assertEquals(s.isConnected(), false);
		}
		
		public void testTrigger(){
			circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);
			b.addCircle(c);
			
			assertEquals(b.triggerCircle("Circle"), true);
			
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
			Ball ball = new Ball(2,2,100,100,"ball");
			//b.add(ball);
			ball.stop();
			ball.start();
			assertEquals(ball.stopped(), false);
		}
		
		@Test
		public void testStop(){
			Ball ball = new Ball(5,5,50,50,"ball");
			//b.add(ball);
			ball.start();
			ball.stop();
			assertEquals(ball.stopped(), true);
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
		public void testBuildMode(){
			b.setBuildMode(false);
			assertEquals(b.getBuildMode(), false);
		}
		
		//TEST THAT SHOULD FAIL	
		//THESE SHOULD ASSERT TO FALSE
		@Test
		public void testAddSquareFail(){
			Square sf = new Square(100, 100, 100, 100, "sf", 100, 100);
			b.addSquare(sf);
			assertEquals(b.containsName("sf"), false);
		}
		
		@Test
		public void testAddCircleFail(){
			circle cf = new circle(100.0, 100.0, 100.0, "Circlef", 120, 120);
			b.addCircle(cf);
			assertEquals(b.containsName("Circlef"), false);
		}
		
		@Test
		public void testAddTriangleFail(){
			Triangle tf = new Triangle(50,50, 20,100, 100, 100, "tf", 130,130);
			b.addTriangle(tf);
			assertEquals(b.containsName("tf"), false);
		}
		
		@Test
		public void testAddAbsorberFail(){
			Absorber a = new Absorber(100, 100, 100, 100, "A");
			b.addAbsorber(a);
			assertEquals(b.containsName("A"), false);
		}
		
		@Test
		public void testAddFlipperFail(){
			Flipper ff = new Flipper(60,30);
			b.addFlipper(ff);
			assertEquals(b.containsName("ff"), false);
			
		}
		
		
		
		
		

	}






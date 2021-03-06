package main;

import static org.junit.Assert.*;
import model.*;
import java.io.*;

import org.junit.Test;

public class MainTesting{

		
		//STILL TO DO ASSERT STATEMENTS
		//AND CHECK IF THINGS ARE BEING
		//ADDED CORRECTLY
	
	//COVERAGE MODEL TESTS
	
	@Test
	public void testMoveBall(){
		
		Ball ball = new Ball(3,3,200,200,"b");
		b.moveBallForTime(ball, 5);
	}
	
	@Test
	public void testGetId(){
		Square s=new Square(10, 6, 3, 3, "s", 5, 5);
		circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 10);
		Triangle t = new Triangle(5,5, 2,10, 10, 10, "t", 13,2);
		
		b.addSquare(s);
		b.addCircle(c);
		b.addTriangle(t);
		
		assertEquals(b.getIdOfPosition(5,5), "s");
		assertEquals(b.getIdOfPosition(12,10), "Circle");
		assertEquals(b.getIdOfPosition(13,2), "t");
	}
	

		
		//RUN MODE TESTS
		
		Model b = new Model();

		@Test
		public void testAddSquare() throws IOException{
			Square s=new Square(10, 10, 3, 3, "s", 5, 5);
			b.addSquare(s);
			assertEquals(b.containsName("s"), true);
			assertEquals(s.getX(), 10);
			assertEquals(s.getY(), 10);
			assertEquals(b.hasAtPosition(5, 5), true);
			
		}
		
		@Test
		public void testAddCircles(){
			circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);	
			b.addCircle(c);
			assertEquals(b.containsName("Circle"), true);
			assertEquals(c.getPositionX(), 12);
			assertEquals(c.getPositionY(), 12);
			assertEquals(b.hasAtPosition(12, 12), true);
			
		}	
		
		@Test
		public void testAddTriangles(){
			Triangle t = new Triangle(5,5, 2,10, 10, 10, "t", 13,13);
			b.addTriangle(t);
			assertEquals(b.containsName("t"), true);
			assertEquals(t.getPositionX(), 13);
			assertEquals(t.getPositionY(), 13);
			assertEquals(b.hasAtPosition(13,13), true);
		}
		
		@Test
		public void testAddBall(){
			Ball ball = new Ball(3,3,200,200,"b");	

			b.setBall(ball);
			
			b.moveBall();
			
			assertEquals(ball.getExactX()==3, false);
			assertEquals(ball.getExactY()==3, false);
		}
		
		@Test
		public void testAddAbsorber(){
			Absorber a = new Absorber(10, 10, 4, 1, "ab");
			b.addAbsorber(a);
		
			assertEquals(b.containsName("a"), true);
			assertEquals(b.hasAtPosition(18, 18), true);
		}
		
		@Test
		public void testAddLine(){
			Line l = new Line(3,12,5,"line");
			b.addLine(l);
			
			assertEquals(b.containsName("line"), true);
			
		}
		
		@Test
		public void testAddFlipper(){
			assertEquals(b.containsName("f"), false);
			flipper3 ff = new flipper3("ff",3,3);
			b.addFlipper3(ff);
			assertEquals(b.containsName("ff"), true);
			
		}
		
		@Test 
		public void testRotate(){
			Triangle t = new Triangle(3,3,1,8,8,8,"tri", 12,12);
			b.addTriangle(t);
			assertEquals(t.getRotations(), 0);
			b.rotate("tri");
			assertEquals(t.getRotations(), 1);
			b.rotate("tri");
			assertEquals(t.getRotations(), 2);
			b.rotate("tri");
			assertEquals(t.getRotations(), 3);
			b.rotate("tri");
			assertEquals(t.getRotations(), 4);
		}
		
		@Test
		public void testDelete(){
			Square s1 = new Square(10,10,5,5,"s1",8,8);
			Square s2 = new Square(10,10,5,5,"s2",12,6);
			
			circle c1 = new circle(10.0, 10.0, 10.0, "c1", 12, 12);
			circle c2 = new circle(10.0, 10.0, 10.0, "c2", 1, 3);
			
			Triangle t1 = new Triangle(5,5, 2,10, 10, 10, "t1", 13, 13);
			Triangle t2 = new Triangle(5,5, 2,10, 10, 10, "t2", 2, 9);
			
			b.addSquare(s1);
			b.addSquare(s2);
			
			b.addCircle(c1);
			b.addCircle(c2);
			
			b.addTriangle(t1);
			b.addTriangle(t2);
			
			assertEquals(b.containsName("s1"), true);
			assertEquals(b.containsName("s2"), true);
			assertEquals(b.containsName("c1"), true);
			assertEquals(b.containsName("c2"), true);
			assertEquals(b.containsName("t1"), true);
			assertEquals(b.containsName("t2"), true);
			
			b.delete("s1");
			b.delete("s2");
			b.delete("c1");
			b.delete("c2");
			b.delete("t1");
			b.delete("t2");
			
			assertEquals(b.containsName("s1"), false);
			assertEquals(b.containsName("s2"), false);
			assertEquals(b.containsName("c1"), false);
			assertEquals(b.containsName("c2"), false);
			assertEquals(b.containsName("t1"), false);
			assertEquals(b.containsName("t2"), false);
			
		}
		
		@Test
		public void testMove(){	
		Square s = new Square(10,2,5,5,"s",8,8);
		circle c = new circle(10.0, 10.0, 10.0, "c2", 1, 3);
		Triangle t = new Triangle(5,5, 2,10, 10, 10, "t1", 13, 13);
		
		b.addSquare(s);
		b.addCircle(c);
		b.addTriangle(t);
		
		assertEquals(s.getX(), 10);
		assertEquals(s.getY(), 2);	
		assertEquals(c.getPositionX(), 1);
		assertEquals(c.getPositionY(), 3);
		assertEquals(t.getPositionX(), 13);
		assertEquals(t.getPositionY(), 13);
		
		b.move(8,8,15,12);
		b.move(1, 3, 3, 1);
		b.move(13,13,9,4);
		
		assertEquals(c.getPositionX(), 3);
		assertEquals(c.getPositionY(), 1);
		
		assertEquals(s.getX(), 15);
		assertEquals(s.getY(), 12);
		
		assertEquals(b.hasAtPosition(15, 12), true);
		}
		
		@Test
		public void testClear(){
			Square s = new Square(10,10,5,5,"s",8,8);
			b.addSquare(s);
			
			Triangle t = new Triangle(3,3,1,8,8,8,"tri", 12,12);
			b.addTriangle(t);
			
			b.clearBoard();
			
			assertEquals(
					b.containsName("s"), false);
			assertEquals(b.containsName("tri"), false);
			
			assertEquals(b.hasAtPosition(8, 8), false);
			assertEquals(b.hasAtPosition(12, 12), false);
		}
		
		@Test
		public void testFriction(){
			b.setMu(12.21);
			b.setMu2(13.32);
			assertTrue(b.getMu()== 12.21);
			assertTrue(b.getMu2()==13.32);
		}
		
		@Test
		public void testGravity(){
			b.setGravity(11.2);
			assertTrue(b.getGravity()==11.2);
		}
		
		@Test
		public void testConnect(){
			Square s = new Square(10,10,5,5,"s",8,8);
			circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);
			assertEquals(s.isConnected(), false);
			s.setConnected("Circle");
			assertEquals(s.isConnected(), true);
		}
		
		@Test
		public void testDisconnect(){
			Square s = new Square(10,10,5,5,"s",8,8);
			circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);
			s.setConnected("Circle");
			assertEquals(s.isConnected(), true);
			s.setConnected("Circle");
		}
		
		@Test
		public void testTrigger(){
			circle c = new circle(10.0, 10.0, 10.0, "Circle", 12, 12);
			b.addCircle(c);
			
			assertEquals(b.triggerCircle("Circle"), true);
			
		}
		
		@Test
		public void testKeyConnect(){
			b.getKeyPress();
			assertEquals(b.getKey(), "d");
		}
		
		@Test
		public void testKeyDisconnect(){
			
		}
		
		@Test
		public void testLoad(){
			
			FileParser f = null;
			
			try{
			f = new FileParser(b);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			try{
			f.run(true);
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
		
		@Test
		public void testReload(){
			
		}
		
		@Test
		public void testSave(){
			Square s = new Square(1,1,5,5,"s",1,1);
			circle c = new circle(2.0, 2.0, 2.0, "c2", 8, 1);
			Triangle t = new Triangle(5,5, 2,10, 10, 10, "t1", 13, 13);
			Absorber a = new Absorber(10, 10, 4, 1, "ab");
			Ball ball = new Ball(2,2,100,100,"ball");
			
			b.addSquare(s);
			b.addCircle(c);
			b.addTriangle(t);
			b.addAbsorber(a);
			b.setBall(ball);
			
			b.rotate("t1");
			
			b.saveToFile();
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
			ball.stop();
			ball.start();
			assertEquals(ball.stopped(), false);
		}
		
		@Test
		public void testStop(){
			Ball ball = new Ball(5,5,50,50,"ball");
			ball.start();
			ball.stop();
			assertEquals(ball.stopped(), true);
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
			assertEquals(b.containsName("sf"), false);
			Square sf = new Square(100, 100, 100, 100, "sf", 100, 100);
			b.addSquare(sf);
			assertEquals(b.containsName("sf"), false);
		}
		
		@Test
		public void testAddCircleFail(){
			assertEquals(b.containsName("Circlef"), false);
			circle cf = new circle(100.0, 100.0, 100.0, "Circlef", 120, 120);
			b.addCircle(cf);
			assertEquals(b.containsName("Circlef"), false);
		}
		
		@Test
		public void testAddTriangleFail(){
			assertEquals(b.containsName("tf"), false);
			Triangle tf = new Triangle(50,50, 20,100, 100, 100, "tf", 130,130);
			b.addTriangle(tf);
			assertEquals(b.containsName("tf"), false);
		}
		
		@Test
		public void testAddAbsorberFail(){
			assertEquals(b.containsName("a"), false);
			Absorber a = new Absorber(100, 100, 100, 100, "A");
			b.addAbsorber(a);
			assertEquals(b.containsName("A"), false);
		}
		
		@Test
		public void testAddFlipperFail(){
			assertEquals(b.containsName("f"), false);
			flipper3 ff = new flipper3("ff",30,30);
			b.addFlipper3(ff);
			assertEquals(b.containsName("ff"), false);
			
		}
		
		
		
		
		

	}



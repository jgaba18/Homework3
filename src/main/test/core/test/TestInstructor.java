package core.test;

import core.api.*;
import core.api.impl.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestInstructor {
	private IInstructor instructor;
	private IAdmin admin;
	
	@Before
	public void setup() {
		this.instructor = new Instructor();
		this.admin = new Admin();
	}
	
	// Test addHomework
	/* T1 : valid HW add
	 * T2 : emptyString for instr name
	 * T3 : emptyString for class name
	 * T4 : emptyString for hw name
	 * T5 : Year < 2017
	 */
	
	@Test
	public void testAddHW1 () {
		this.admin.createClass("Class1", 2017, "Inst1", 200);
		this.instructor.addHomework("Inst1", "Class1", 2017, "HW1");
		assertTrue(this.instructor.homeworkExists("Class1", 2017, "HW1"));
	}
	
	@Test
	public void testAddHW2 () {
		this.admin.createClass("Class2", 2017, "Inst2", 200);
		this.instructor.addHomework("", "Class2", 2017, "HW2");
		assertFalse(this.instructor.homeworkExists("Class2", 2017, "HW2"));
	}
	
	@Test
	public void testAddHW3 () {
		this.admin.createClass("Class3", 2017, "Inst3", 200);
		this.instructor.addHomework("Inst3", "", 2017, "HW3");
		assertFalse(this.instructor.homeworkExists("", 2017, "HW3"));
	}
	
	@Test
	public void testAddHW4 () {
		this.instructor.addHomework("Inst5", "Class5", 2017, "");
		assertFalse(this.instructor.homeworkExists("Class5", 2017, ""));
	}
	
	@Test
	public void testAddHW5 () {
		this.instructor.addHomework("Inst4", "Class4", 2016, "HW4");
		assertFalse(this.instructor.homeworkExists("Class4", 2016, "HW4"));
	}
	
	// Test AssignGrade
	/* T1 : valid assign grade 
	 * T2 : invalid year 
	 * T3 : update grades (assign twice)
	 * T4 : grade is less than 100
	 * T5 : grade is less than 0
	 * T6 : no student with that StudentName in class 
	 */
	@Test
	public void testAssignGrades1 () {
		this.admin.createClass("Class60", 2017, "Inst60", 50);
		this.instructor.addHomework("Inst60", "Class60", 2017, "HW6");
		this.instructor.assignGrade("Inst60", "Class60", 2017, "HW6", "S1", 90);
		int temp = this.instructor.getGrade("Class60", 2017, "HW6", "S1");
		assertEquals(temp, 90);
	}
	
	@Test
	public void testAssignGrades2 () {
		this.admin.createClass("Class61", 2017, "Inst61", 50);
		this.instructor.addHomework("Inst61", "Class61", 2017, "HW7");
		this.instructor.assignGrade("Inst61", "Class61", 2016, "HW7", "S2", 90);
		assertNull(this.instructor.getGrade("Class61", 2017, "HW7", "S2"));
	}
	
	@Test 
	public void testAssignGrades3 () {
		this.admin.createClass("Class62", 2017, "Inst62", 50);
		this.instructor.addHomework("Inst62", "Class62", 2017, "HW7");
		this.instructor.assignGrade("Inst62", "Class62", 2017, "HW7", "S3", 90);
		this.instructor.assignGrade("Inst62", "Class62", 2017, "HW7", "S3", 80);
		int temp = this.instructor.getGrade("Class62", 2017, "HW7", "S3");
		assertEquals(temp, 80);	
	}
	
	@Test 
	public void testAssignGrades4 () {
		this.admin.createClass("Class63", 2017, "Inst63", 50);
		this.instructor.addHomework("Inst63", "Class63", 2017, "HW8");
		this.instructor.assignGrade("Inst63", "Class63", 2017, "HW8", "S4", 110);
		assertNull(this.instructor.getGrade("Class63", 2017, "HW8", "S4"));
	}
	
	@Test 
	public void testAssignGrades5 () {
		this.admin.createClass("Class64", 2017, "Inst64", 50);
		this.instructor.addHomework("Inst64", "Class64", 2017, "HW9");
		this.instructor.assignGrade("Inst64", "Class64", 2017, "HW9", "S5", -11);
		assertNull(this.instructor.getGrade("Class64", 2017, "HW9", "S5"));
	}
	
	@Test 
	public void testAssignGrades6 () {
		this.admin.createClass("Class65", 2017, "Inst65", 50);
		this.instructor.addHomework("Inst65", "Class65", 2017, "HW10");
		this.instructor.assignGrade("Inst65", "Class65", 2017, "HW10", "S5", -11);
		assertNull(this.instructor.getGrade("Class65", 2017, "HW10", "S6"));
	}
	
		
}

package core.test;

import core.api.*;
import core.api.impl.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestStudent {
	
	//private IInstructor instructor;
	private IAdmin admin;
	private IInstructor instructor;
	private IStudent student;
	
	@Before
	public void setup() {
		this.admin = new Admin();
		this.instructor = new Instructor();
		this.student = new Student();
	}
	
	// Test registerForClass
	/* T1 : valid registration 
	 * T2 : empty string for student name
	 * T3 : empty string for class name 
	 * T4 : invalid year
	 * T5 : no student registered 
	 */
	
	@Test
	public void testRegisterForClass1() {
		this.admin.createClass("Class1", 2017, "Inst1", 200);
		this.student.registerForClass("S1", "Class1", 2017);
		assertTrue(this.student.isRegisteredFor("S1", "Class1", 2017));
	}
	
	@Test
	public void testRegisterForClass2() {
		this.admin.createClass("Class2", 2017, "Inst2", 200);
		this.student.registerForClass("", "Class2", 2017);
		assertFalse(this.student.isRegisteredFor("" , "Class2", 2017));
	}
	
	@Test
	public void testRegisterForClass3() {
		this.admin.createClass("Class3", 2017, "Inst3", 200);
		this.student.registerForClass("S3", "", 2017);
		assertFalse(this.student.isRegisteredFor("S3" , "", 2017));
	}
	
	@Test
	public void testRegisterForClass4() {
		this.admin.createClass("Class4", 2017, "Inst4", 200);
		this.student.registerForClass("S5", "Class4", 2016);
		assertFalse(this.student.isRegisteredFor("S5" , "Class4", 2016));
	}
	
	@Test
	public void testRegisterForClass5() {
		this.admin.createClass("Class5", 2017, "Inst5", 200);
		this.student.registerForClass("S3", "Class5", 2017);
		assertFalse(this.student.isRegisteredFor("S9" , "Class5", 2017));
	}
	
	// Test dropClass
	/* T1 : valid drop
	 * T2 : invalid drop (not enrolled in class)
	 * T3 : empty string for student name
	 * T4 : empty string for class name 
	 * T5 : invalid year
	 */
	@Test
	public void testDropClass1() {
		this.admin.createClass("Class6", 2017, "Inst6", 200);
		this.student.registerForClass("S6", "Class6", 2017);
		this.student.dropClass("S6", "Class6", 2017);
		assertFalse(this.student.isRegisteredFor("S9" , "Class6", 2017));
	}
	
	@Test
	public void testDropClass2() {
		this.admin.createClass("Class7", 2017, "Inst7", 200);
		this.admin.createClass("Class8", 2017, "Inst7", 200);
		this.student.registerForClass("S7", "Class7", 2017);
		this.student.dropClass("S7", "Class8", 2017);
		assertTrue(this.student.isRegisteredFor("S7" , "Class7", 2017));
	}
	
	@Test
	public void testDropClass3() {
		this.admin.createClass("Class9", 2017, "Inst9", 200);
		this.student.registerForClass("S9", "Class9", 2017);
		this.student.dropClass("", "Class9", 2017);
		assertFalse(this.student.isRegisteredFor("" , "Class9", 2017));
	}
	
	@Test 
	public void testDropClass4() {
		this.admin.createClass("Class9", 2017, "Inst9", 200);
		this.student.registerForClass("S9", "Class9", 2017);
		this.student.dropClass("S9", "", 2017);
		assertFalse(this.student.isRegisteredFor("S9" , "", 2017));
	}
	
	@Test 
	public void testDropClass5() {
		this.admin.createClass("Class10", 2017, "Inst10", 200);
		this.student.registerForClass("S10", "Class10", 2017);
		this.student.dropClass("S10", "Class10", 2016);
		assertFalse(this.student.isRegisteredFor("S9" , "Class10", 2016));
	}
	
	// test SubmitHW
	/* T1 : valid submission 
	 * T2 : wrong student name
	 * T3 : wrong HW name
	 * T4 : wrong Class name
	 * T5 : invalid year
	 * T6 : answerString is empty 
	 * T7 : answerString is null
	 * T8 : submit multiple HW18 from same student 
	 */
	
	@Test 
	public void testSubmitHomeWork1() {
		this.admin.createClass("Class11", 2017, "Inst11", 200);
		this.student.registerForClass("S11", "Class11", 2017);
		this.instructor.addHomework("Inst11", "Class11", 2017, "HW11");
		this.student.submitHomework("S11", "HW11", "abcd", "Class11", 2017);
		assertTrue(this.student.hasSubmitted("S11", "HW11", "Class11", 2017));
	}
	
	@Test 
	public void testSubmitHomeWork2() {
		this.admin.createClass("Class12", 2017, "Inst12", 200);
		this.student.registerForClass("S12", "Class12", 2017);
		this.instructor.addHomework("Inst12", "Class12", 2017, "HW12");
		this.student.submitHomework("S12", "HW12", "abcde", "Class12", 2017);
		assertFalse(this.student.hasSubmitted("S10", "HW12", "Class12", 2017));
	}
	
	@Test 
	public void testSubmitHomeWork3() {
		this.admin.createClass("Class13", 2017, "Inst13", 200);
		this.student.registerForClass("S13", "Class13", 2017);
		this.instructor.addHomework("Inst13", "Class13", 2017, "HW13");
		this.student.submitHomework("S13", "HW10", "abcde", "Class13", 2017);
		assertFalse(this.student.hasSubmitted("S13", "HW13", "Class13", 2017));
	}
	
	@Test 
	public void testSubmitHomeWork4() {
		this.admin.createClass("Class14", 2017, "Inst14", 200);
		this.student.registerForClass("S14", "Class14", 2017);
		this.instructor.addHomework("Inst14", "Class14", 2017, "HW14");
		this.student.submitHomework("S14", "HW14", "abcde", "Class14", 2017);
		assertFalse(this.student.hasSubmitted("S14", "HW14", "Class19", 2017));
	}
	
	@Test 
	public void testSubmitHomeWork5() {
		this.admin.createClass("Class15", 2017, "Inst15", 200);
		this.student.registerForClass("S15", "Class15", 2017);
		this.instructor.addHomework("Inst15", "Class15", 2017, "HW15");
		this.student.submitHomework("S15", "HW15", "abcde", "Class15", 2015);
		assertFalse(this.student.hasSubmitted("S15", "HW15", "Class15", 2015));
	}
	
	@Test 
	public void testSubmitHomeWork6() {
		this.admin.createClass("Class16", 2017, "Inst16", 200);
		this.student.registerForClass("S16", "Class16", 2017);
		this.instructor.addHomework("Inst16", "Class16", 2017, "HW16");
		this.student.submitHomework("S16", "HW16", "" , "Class16", 2017);
		assertFalse(this.student.hasSubmitted("S16", "HW16", "Class16", 2017));
	}
	
	@Test 
	public void testSubmitHomeWork7() {
		this.admin.createClass("Class17", 2017, "Inst17", 200);
		this.student.registerForClass("S17", "Class17", 2017);
		this.instructor.addHomework("Inst17", "Class17", 2017, "HW17");
		this.student.submitHomework("S17", "HW17", null , "Class17", 2017);
		assertFalse(this.student.hasSubmitted("S17", "HW17", "Class17", 2017));
	}
	
	@Test 
	public void testSubmitHomeWork8() {
		this.admin.createClass("Class18", 2017, "Inst18", 200);
		this.student.registerForClass("S18", "Class18", 2017);
		this.instructor.addHomework("Inst18", "Class18", 2017, "HW18");
		this.student.submitHomework("S18", "HW18", "hi" , "Class18", 2017);
		this.student.submitHomework("S18", "HW18", "hello" , "Class18", 2017);
		assertTrue(this.student.hasSubmitted("S18", "HW18", "Class18", 2017));
	}
	
	
}

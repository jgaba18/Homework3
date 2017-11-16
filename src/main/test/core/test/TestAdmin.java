package core.test;

import core.api.IAdmin;
import core.api.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAdmin {
	private IAdmin admin;
	
	@Before
	public void setup() {
		this.admin = new Admin();
	}
	
	// test createClass 
	/* T1 : valid input
	 * T2 : wrong year (2016)
	 * T3 : capacity = 0 
	 * T4 : capacity < 0
	 * T5 : give instructor 2 classes
	 * T6 : give instructor 3 classes
	 * T7 : duplicate class/year pair 
	 * T8 : empty string for Class Name
	 * T9 : empty string for Inst. Name
	 */
	
	@Test
	public void testCreateClass1() {
		this.admin.createClass("Class1", 2017, "Int1", 80);
		assertTrue(this.admin.classExists("Class1", 2017));
	}
	
	@Test
	public void testCreateClass2() {
		this.admin.createClass("Class2", 2016, "Int2", 15);
        assertFalse(this.admin.classExists("Class2", 2016));
	}

	@Test
	public void testCreateClass3() {
		this.admin.createClass("Class3", 2017, "Inst3", 0);
        assertFalse(this.admin.classExists("Class3", 2017));
	}
	
	@Test
	public void testCreateClass4() {
		this.admin.createClass("Class4", 2017, "Inst4", -3);
        assertFalse(this.admin.classExists("Class4", 2017));
	}
		
	@Test
	public void testCreateClass5() {
		this.admin.createClass("Class5", 2017, "Inst4", 10);
		this.admin.createClass("Class6", 2017, "Inst4", 10);
		assertTrue(this.admin.classExists("Class6", 2017));
	}
	
	@Test
	public void testCreateClass6() {
		this.admin.createClass("Class7", 2017, "Inst5", 10);
		this.admin.createClass("Class8", 2017, "Inst5", 10);
		this.admin.createClass("Class9", 2017, "Inst5", 10);
		assertFalse(this.admin.classExists("Class9", 2017));
	}
	
	@Test
	public void testCreateClass7() {
		this.admin.createClass("Class10", 2017, "Inst6", 10);
		this.admin.createClass("Class10", 2017, "Inst7", 100);
		assertEquals(this.admin.getClassInstructor("Class10", 2017), "Inst6");
	}
	
	@Test
	public void testCreateClass8() {
		this.admin.createClass("", 2017, "Inst50", 10);
		assertFalse(this.admin.classExists("", 2017));
	}
	
	@Test
	public void testCreateClass9() {
		this.admin.createClass("Class100", 2017, "", 10);
		assertFalse(this.admin.classExists("Class100", 2017));
	}
	
	// test changeCapacity 
	/* T1 : Change capacity with number higher than original 
	 * T2 : Change capacity with number same as original
	 * T3 : Change capacity with number lower than original 
	 * T4 : Change capacity with 0
	 */
	@Test
	public void testChangeCapacity1 () {
		this.admin.createClass("Class11", 2017, "Inst8", 150);
        this.admin.changeCapacity("Class11", 2017, 200);
        assertEquals(this.admin.getClassCapacity("Class11", 2017), 200);
	}

	@Test
	public void testChangeCapacity2 () {
		this.admin.createClass("Class12", 2017, "Inst9", 200);
        this.admin.changeCapacity("Class12", 2017, 200);
        assertEquals(this.admin.getClassCapacity("Class12", 2017), 200);
	}
	
	@Test
	public void testChangeCapacity3 () {
		this.admin.createClass("Class13", 2017, "Inst10", 150);
        this.admin.changeCapacity("Class13", 2017, 20);
        assertEquals(this.admin.getClassCapacity("Class13", 2017), 150);
	}
	
	@Test
	public void testChangeCapacity4 () {
		this.admin.createClass("Class14", 2017, "Inst11", 150);
        this.admin.changeCapacity("Class14", 2017, 20);
        assertEquals(this.admin.getClassCapacity("Class14", 2017), 150);
	}
}

package com.cdac.tdd.random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class VoidMethodTest {

	@Test
	void test_void_method() {
		VoidMethods emp = mock(VoidMethods.class);

		doThrow(IllegalArgumentException.class).when(emp).setName(null);

		doAnswer(i -> {
			System.out.println("Argument is " + i.getArgument(0));
			assertTrue("Pankaj".equals(i.getArgument(0)));
			return null;
		}).when(emp).setName(Mockito.anyString());

		doReturn("Pankaj").when(emp).getName();

		assertThrows(IllegalArgumentException.class, () -> emp.setName(null));
		assertEquals("Pankaj", emp.getName());
		emp.setName("Pankaj");

	}
}

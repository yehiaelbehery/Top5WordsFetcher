package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import client.Presenter;
import client.View;

class FetcherPresenterTests {

	@Test
	void testInit() {
		View view = new View();
		Presenter presenter = new Presenter(view);
		
		Class<Presenter> myObjectClass = Presenter.class;
		
		
		try {
			
			Field field = myObjectClass.getDeclaredField("view");
			field.setAccessible(true);
			assertTrue(field.get(presenter) == view);
			
		} catch (IllegalArgumentException | IllegalAccessException e) {

			fail(e.toString());
			
		} catch (NoSuchFieldException e) {
			
			fail(e.toString());
			
		} catch (SecurityException e) {
		
			fail(e.toString());
		}
	}

}

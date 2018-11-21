package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import client.ClientPresenter;
import client.ClientView;

class ClientPresenterTests {

	@Test
	void test_init() {
		ClientView view = new ClientView();
		ClientPresenter presenter = new ClientPresenter(view);
		
		try {
			
			Field field = presenter.getClass().getDeclaredField("view");
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
	

	@Test
	void test_updateWordsHashmapWithString() {
		ClientView view = new ClientView();
		ClientPresenter presenter = new ClientPresenter(view);
		
		try {
			Method method = presenter.getClass().getDeclaredMethod("updateWordsHashmapWithString", String.class);
			method.setAccessible(true);
			@SuppressWarnings("unused")
			Object r = method.invoke(presenter, "one two two four");

			Field field = presenter.getClass().getDeclaredField("wordCountMap");
			field.setAccessible(true);
			HashMap<String, Integer> wordCountMap = (HashMap<String, Integer>) field.get(presenter);

			assertTrue(wordCountMap.get("one") == 1);
			assertTrue(wordCountMap.get("two") == 2);
			assertTrue(wordCountMap.get("four") == 1);
			
		} catch (IllegalArgumentException | IllegalAccessException e) {

			fail(e.toString());
			
		} catch (SecurityException e) {
		
			fail(e.toString());
			
		} catch (NoSuchMethodException e) {

			fail(e.toString());
			
		} catch (InvocationTargetException e) {

			fail(e.toString());
			
		} catch (NoSuchFieldException e) {

			fail(e.toString());
			
		}
	}

}

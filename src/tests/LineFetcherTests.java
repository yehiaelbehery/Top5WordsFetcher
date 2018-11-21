package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import helper.RemoteFileLineFetcher;
import utility.Constant;

class LineFetcherTests {

	@Test
	void test() {
		RemoteFileLineFetcher server = new RemoteFileLineFetcher(9876);
		server.debugMode = true;
		String line = server.getNextLine();
		assertTrue(line.equals(Constant.socketCloseMagicWord));
	}

}

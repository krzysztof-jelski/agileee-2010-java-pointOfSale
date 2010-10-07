package ca.jbrains.pos.test;

import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.Test;

import ca.jbrains.pos.Screen;

public class ScreenTest {

	@Test
	public void displayNoProductFound() {
		PrintStream printStream = mock(PrintStream.class);
		Screen screen = new Screen(printStream);

		screen.displayNoProductFound("123");

		verify(printStream).println("no product found for barcode: 123");
	}
}

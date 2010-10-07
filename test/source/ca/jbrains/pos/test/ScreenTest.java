package ca.jbrains.pos.test;

import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.jbrains.pos.Screen;

@RunWith(MockitoJUnitRunner.class)
public class ScreenTest {
	@Mock
	private PrintStream printStream;

	@Test
	public void displayNoProductFound() {
		Screen screen = createScreen();

		screen.displayNoProductFound("123");

		verify(printStream).println("no product found for barcode: 123");
	}

	@Test
	public void displayScannedEmptyBarcode() {
		Screen screen = createScreen();

		screen.displayScannedEmptyBarcode();

		verify(printStream).println("scanned empty barcode");
	}

	@Test
	public void displayPrice() {
		Screen screen = createScreen();

		screen.displayPrice(12550);

		verify(printStream).println("$125.50");
	}

	private Screen createScreen() {
		return new Screen(printStream);
	}

}

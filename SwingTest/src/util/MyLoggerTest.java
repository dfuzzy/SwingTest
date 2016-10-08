package util;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class MyLoggerTest {

	@Test
	public void testGetLogger() {
		Logger logger = MyLogger.getLogger();
		logger.log(Level.INFO, "Hello");
		logger.log(Level.INFO, "{0}={1}", new Object[]{"test", 123});
		logger.log(Level.WARNING, "", new Exception("なんか知らんがワーニングじゃ"));
		logger.log(Level.SEVERE, "エラーやで", new Exception("なんか知らんがエラーじゃ"));
		assert(logger.getHandlers().length==1);
	}

}

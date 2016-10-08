package util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
	private static Formatter myFormatter = new MyLogFormatter();
	
	public static class MyLogFormatter extends Formatter {
		private static final DateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		private static final char separator = '\t';
		
		@Override
		public String format(LogRecord record) {
			StringBuilder sb = new StringBuilder(256);
			sb.append(MyLogFormatter.myDateFormat.format(new Date(record.getMillis())));
			sb.append(MyLogFormatter.separator);
			sb.append(record.getLevel().toString());
			sb.append(MyLogFormatter.separator);
			sb.append(record.getSourceClassName());
			sb.append(MyLogFormatter.separator);
			sb.append(record.getSourceMethodName());
			sb.append(MyLogFormatter.separator);
			sb.append(this.formatMessage(record));
			if(sb.charAt(sb.length()-1)!='\n') {
				sb.append('\n');
			}
			return sb.toString();
		}
	}
	
	public static Logger getLogger(String str) {
		return MyLogger.getLogger();
	}
	
	public static Logger getLogger() {
//		Logger logger = Logger.getLogger("MyLogger");
		Logger logger = Logger.getAnonymousLogger();
//		Logger logger = Logger.getLogger("");
		logger.setUseParentHandlers(false);
		
		if(logger.getHandlers().length==0) {
			logger.addHandler(new ConsoleHandler());
		}
		
		for(Handler h : logger.getHandlers()) {
			h.setFormatter(myFormatter);
		}
		return logger;
	}
	
	public static void main(String[] args) {
		Logger logger = MyLogger.getLogger();
		logger.log(Level.FINEST, "Finest");
		logger.log(Level.INFO, "INFO");
	}
	
//	public static final String LOG_CONFIG = "java.util.logging.StreamHandler.level=FINEST";
//	
//	static {
//		LogManager man = LogManager.getLogManager();
//		InputStream is = new ByteArrayInputStream(LOG_CONFIG.getBytes());
//		try {
//			man.readConfiguration(is);
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}

import java.io.FileInputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Task_worker {
    private Map<String, String> table_before;
    private Map<String, String> table_after;
    private boolean need_to_parse = false;
    private boolean debug = false;
    private Logger logger;

    protected Task_worker(Map<String, String> table_before, Map<String, String> table_after, boolean need_to_parse, boolean debug) {
        this.table_before = table_before;
        this.table_after = table_after;
        this.need_to_parse = need_to_parse;
        this.debug = debug;
    }

    protected String run() {
        try {
            if (debug) {
                FileInputStream ins = new FileInputStream("./logs/log.config");
                LogManager.getLogManager().readConfiguration(ins);
                logger = Logger.getLogger(Main.class.getName());
                logger.log(Level.INFO, "Start of program");
            }

            // если нужно спарсить html код страниц
            if (need_to_parse) {
                Parser parser = new Parser(logger, debug);
                for (String url : table_before.keySet()) table_before.put(url, parser.parse(url));
                for (String url : table_after.keySet()) table_after.put(url, parser.parse(url));
            }

            // обход таблиц
            Comparator comparator = new Comparator(logger, debug);
            String letter = comparator.compare(table_before, table_after);

            if (debug) {
                logger.log(Level.INFO, table_before.keySet().toString());
                logger.log(Level.INFO, table_after.keySet().toString());
                logger.log(Level.INFO, letter);
            }
            if (debug) logger.log(Level.INFO, "Program finished");
            return letter;
        } catch (Exception e) {
            if (debug) logger.log(Level.WARNING, "Program finished with exception:", e);
            return null;
        }
    }
}

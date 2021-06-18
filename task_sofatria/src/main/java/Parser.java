import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private boolean debug;
    static Logger logger;

    protected Parser(Logger logger, boolean debug) throws IOException {
        this.debug = debug;
        this.logger = logger;
        if (this.debug) this.logger.log(Level.INFO, "Parser Constructor");
    }

    /* Парсинг html кода страницы */
    protected String parse(String url) throws Exception {
        Document html_doc;
        if (debug) this.logger.log(Level.INFO, "Попытка открыть " + url);
        // блок контроля исключений при запросе содержимого страницы
        try {
            html_doc = Jsoup.connect(url).get(); // получить HTML-код страницы
            if (debug) this.logger.log(Level.INFO, "WEB файл " + url);
        } catch (java.net.MalformedURLException e) { // если не удалось, страница может быть локальным файлом
            String fileName = url.substring(7);
            if (debug) this.logger.log(Level.INFO, "Локальный файл " + fileName);
            File input = new File(fileName);
            html_doc = Jsoup.parse(input, "UTF-8");
        } catch (Exception e) {
            // обработка исключений при ошибке запроса содержимого страницы
            throw new Exception("Ошибка. " + url);
        }
        return html_doc.outerHtml();
    }
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Comparator {
    String output_text;
    private boolean debug;
    static Logger logger;

    protected Comparator(Logger logger, boolean debug) throws IOException {
        this.debug = debug;
        this.logger = logger;
        if (this.debug) this.logger.log(Level.INFO, "Comparator Constructor");
        output_text = "Здравствуйте, дорогая *И.О. секретаря*\n\nЗа последние сутки во вверенных Вам сайтах произошли следующие изменения:\n";
    }

    /* Сравнение html кодов страниц */
    private boolean compareHtml(String html_before, String html_after) {
        return html_before.equals(html_after);
    }

    /* Сравнение заданных таблиц и генерация письма */
    protected String compare(Map<String, String> table_before, Map<String, String> table_after) throws Exception {
        List<String> deleted = new ArrayList<>();
        List<String> added = new ArrayList<>();
        List<String> changed = new ArrayList<>();
        // обход страниц из таблицы за вчера, проверка удалений и изменений
        for (String url : table_before.keySet())
            if (!table_after.containsKey(url)) deleted.add(url);
            else if (!compareHtml(table_before.get(url), table_after.get(url))) changed.add(url);

        // обход страниц из таблицы за сегодня, проверка добавлений
        for (String url : table_after.keySet()) if (!table_before.containsKey(url)) added.add(url);

        // если есть удаленные страницы
        if (deleted.size() > 0) {
            output_text += "\nИсчезли следующие страницы: ";
            for (String url : deleted)
                if (deleted.indexOf(url) < deleted.size()-1) output_text += url + ", ";
                else output_text += url;
        }
        // если есть добавленные страницы
        if (added.size() > 0) {
            output_text += "\nПоявились следующие новые страницы: ";
            for (String url : added)
                if (added.indexOf(url) < added.size()-1) output_text += url + ", ";
                else output_text += url;
        }
        // если есть изменившиеся страницы
        if (changed.size() > 0) {
            output_text += "\nИзменились следующие страницы: ";
            for (String url : changed)
                if (changed.indexOf(url) < changed.size()-1) output_text += url + ", ";
                else output_text += url;
        }

        output_text += "\n\nС уважением,\n\nавтоматизированная система\n\nмониторинга.";

        if (debug) logger.log(Level.INFO, "Comparing finished");
        return output_text;
    }
}
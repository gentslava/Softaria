import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Testing {

    @Test
    public void equal_tables() {
        String wait_for = "Здравствуйте, дорогая *И.О. секретаря*\n\nЗа последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n\nС уважением,\n\nавтоматизированная система\n\nмониторинга.";
        Map<String, String> table_before = new HashMap<>();
        Map<String, String> table_after = new HashMap<>();
        table_before.put("https://www.w3.org/", "");
        table_after.put("https://www.w3.org/", "");
        Task_worker worker = new Task_worker(table_before, table_after, true, false);
        assertEquals(wait_for, worker.run());
    }

    @Test
    public void deleted_from() {
        String wait_for = "Здравствуйте, дорогая *И.О. секретаря*\n\nЗа последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\nИсчезли следующие страницы: https://www.w3schools.com/\n\nС уважением,\n\nавтоматизированная система\n\nмониторинга.";
        Map<String, String> table_before = new HashMap<>();
        Map<String, String> table_after = new HashMap<>();
        table_before.put("https://www.w3.org/", "");
        table_before.put("https://www.w3schools.com/", "");
        table_after.put("https://www.w3.org/", "");
        Task_worker worker = new Task_worker(table_before, table_after, true, false);
        assertEquals(wait_for, worker.run());
    }

    @Test
    public void added_to() {
        String wait_for = "Здравствуйте, дорогая *И.О. секретаря*\n\nЗа последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\nИсчезли следующие страницы: https://www.w3schools.com/\nПоявились следующие новые страницы: https://white-dental.ru/\n\nС уважением,\n\nавтоматизированная система\n\nмониторинга.";
        Map<String, String> table_before = new HashMap<>();
        Map<String, String> table_after = new HashMap<>();
        table_before.put("https://www.w3.org/", "");
        table_before.put("https://www.w3schools.com/", "");
        table_after.put("https://www.w3.org/", "");
        table_after.put("https://white-dental.ru/", "");
        Task_worker worker = new Task_worker(table_before, table_after, true, false);
        assertEquals(wait_for, worker.run());
    }

    @Test
    public void changed() {
        String wait_for = "Здравствуйте, дорогая *И.О. секретаря*\n\nЗа последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\nИсчезли следующие страницы: https://www.w3schools.com/\nПоявились следующие новые страницы: https://white-dental.ru/\nИзменились следующие страницы: http://www.randomtextgenerator.com/\n\nС уважением,\n\nавтоматизированная система\n\nмониторинга.";
        Map<String, String> table_before = new HashMap<>();
        Map<String, String> table_after = new HashMap<>();
        table_before.put("https://www.w3.org/", "");
        table_before.put("https://www.w3schools.com/", "");
        table_before.put("http://www.randomtextgenerator.com/", "");
        table_after.put("https://www.w3.org/", "");
        table_after.put("https://white-dental.ru/", "");
        table_after.put("http://www.randomtextgenerator.com/", "");
        Task_worker worker = new Task_worker(table_before, table_after, true, false);
        assertEquals(wait_for, worker.run());
    }
}

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static String letter;
    public static void main(String[] args) {
        // задание исходных таблиц (html код можно спарсить для облегчения процесса инициализации)
        Map<String, String> table_before = new HashMap<>();
        Map<String, String> table_after = new HashMap<>();
        table_before.put("https://stackoverflow.com/", ""); // страница удалена (сайт, который есть в "до", но нет в "после")
        table_before.put("https://white-dental.ru/", ""); // страница без изменений (сайт, который есть в "до" и в "после", без изменений)
        table_after.put("https://white-dental.ru/", ""); // она же :)
        table_before.put("http://www.randomtextgenerator.com/", ""); // страница изменена (сайт, который выдает случайный текст)
        table_after.put("http://www.randomtextgenerator.com/", "");
        table_after.put("https://habr.com/", ""); // страница добавлена (сайт, которого нет в "до", но есть в "после")

        // первый аргумент - таблица за вчера; второй аргумент - таблица за сегодня; третий аргумент - нужно ли спарсить содержимое страниц; четвертый аргумент - отладка
        Task_worker worker = new Task_worker(table_before, table_after, true, true);
        // возвращается сгенерированное письмо либо null в случае ошибки
        String letter = worker.run();
    }
}

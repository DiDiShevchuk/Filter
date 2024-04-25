import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {
    public static void main(String[] args) {
        String address = "143095 ГОЛИЦЫНО 5 ПАРКОВАЯ 7";
        System.out.println(address);
        System.out.println(filterForAddresses(address));
    }

    public static String filterForAddresses(String address) {
        String regexp = "(\\d{5})(\\d)\\s+([А-ЯЁ]+)\\s*(\\d)\\s+(.*)"; // регулярное выражение
        Pattern pat = Pattern.compile(regexp); // Созданем Pattern объекта, т.е. шаблон
        Matcher match = pat.matcher(address); // Созданем matcher объекта, с помощью него будем производить операции сопоставления с вводимым address
        if (match.find()) { // проверяем строку(адрес) на соответствие шаблону
            if (match.group(2).equals(match.group(4))) { // если последняя цифра индекса совпадает с цифрой после названия города
                String replacement = match.group(1) + match.group(2) + " " + match.group(3) + " " + match.group(5);  // заменить её на строку без цифры после названия города
                return replacement;
            } else {
                return address; // иначе возвращаем адрес без изменений
            }
        }
        return address;
    }
}

/*
"(\\d{5})(\\d)\\s+([А-ЯЁ]+)\\s*(\\d)\\s+(.*)" - состоит из 5 групп сбора, где
1. (\\d{5}) - первые 5 цифр индекса
2. (\\d) - 6 цифра индекса, вынесла отдельно, чтобы удобно было сравнивать
3. ([А-ЯЁ]+) - название города
4. (\\d) - цифра после названия города
5. (.*) - оставшаяся часть города
 */

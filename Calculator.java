import java.util.ArrayList;

public class Calculator {
    public static boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static boolean isValidNumber(int num) {
        return num >= 1 && num <= 10;
    }

    public static boolean isValidNumber2(int num) {
        return num >0;
    }

    public static String calc(String inp) throws InputException {
        inp = inp.replaceAll(" ", ""); // Удаляем все пробелы

        /* Проверка на пустой ввод */
        if (inp.isEmpty()) {
            throw new InputException("Ошибка ввода!");
        }

        /* Ищем знак операции и отталкиваясь от него определяем первое и второе число */
        ArrayList<String> inputs = new ArrayList();
        for (int i = 0; i < inp.length(); ++i) {
            if (isOperation(inp.charAt(i))) { // Ищем знак операции и делим строку на 3 элемента
                inputs.add(inp.substring(0, i));   // 1 число
                inputs.add(inp.substring(i, i + 1)); // оператор
                inputs.add(inp.substring(i + 1));   // 2 число
                break;
            }
        }
        /* Проверка неправильный ввод */
        if (inputs.isEmpty()) {
            throw new InputException("Ошибка ввода!");
        } else if (inputs.size() == 1) {
            throw new InputException("Ошибка ввода!");
        } else if (inputs.size() == 2) {
            throw new InputException("Ошибка ввода!");
        }

        int num1, num2;  // Переменные для записи чисел после парсинга
        boolean isRoman = false; // Флаг римских чисел.

        /* Парсинг чисел */
        try {
            // Пробуем получить обычное число
            num1 = Integer.parseInt(inputs.get(0));
            try {
                // Если сработало, пробуем получить второе число
                num2 = Integer.parseInt(inputs.get(2));
            } catch (NumberFormatException e) {
                throw new InputException("Ошибка ввода!");
            }
        } catch (NumberFormatException e) {
            try {
                // Получаем римское число
                num1 = RomanNumber.toRoman(inputs.get(0)).getNumber();
            } catch (IllegalArgumentException e1) {
                throw new InputException("Ошибка ввода!");
            }
            try {
                // Получаем второе римское число
                num2 = RomanNumber.toRoman(inputs.get(2)).getNumber();
            } catch (IllegalArgumentException e1) {
                throw new InputException("Ошибка ввода!");
            }
            isRoman = true; // Активируем флаг римских чисел
        }

        /* Проверяем числа на выход за пределы диапазона [1..10] */
        if (!isValidNumber(num1) || !isValidNumber(num2)) {

            throw new InputException("Ошибка ввода!");

        } else {
            int result;
            /* Определяем нужную операцию */
            switch (inputs.get(1).charAt(0)) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                default:
                    throw new InputException("Ошибка ввода!");
            }
            /* Проверяем результат на отсутствие отрицательных чисел */
            if (isValidNumber2(result)) {
                return isRoman ? RomanNumber.toRoman(result).toString() :
                        Integer.toString(result);

            } else {
                throw new InputException("Результат вычисления не поддерживается калькулятором.");
            }


        }
    }
}
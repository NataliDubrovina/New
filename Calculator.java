import java.util.ArrayList;

public class Calculator {
    public static boolean isOperation(char c){
        return c == '+' || c == '-' ||c == '*' || c == '/';
    }
    public static boolean isValidNumber(int num){
        return num>=1 && num<=10;
    }


    public static String calc(String inp) throws InputException {
        inp = inp.replaceAll(" ", ""); // Удаляем все пробелы

        /* Проверка на пустой ввод */
        if(inp.isEmpty()){
            throw new InputException("Ошибка ввода!");
        }

        /* Ищем знак операции и отталкиваясь от него определяем левое и правое число */
        ArrayList<String> inputs = new ArrayList();
        for(int i = 0; i < inp.length(); ++i){
            if( isOperation(inp.charAt(i)) ){ // Ищем знак операции и делим строку на 3 элемента
                inputs.add(inp.substring(0,i));   // Левое число
                inputs.add(inp.substring(i,i+1)); // Знак операции
                inputs.add(inp.substring(i+1));   // Правое число
                break;
            }
        }
        /* Проверка некоректный ввод */
        if(inputs.isEmpty()){
            throw new InputException("Ошибка ввода!");
        }else if(inputs.size() == 1){
            throw new InputException("Ошибка ввода!");
        }else if(inputs.size() == 2){
            throw new InputException("Ошибка ввода!");
        }

        int left, right;  // Переменные для записи чисел после парсинга
        boolean isRoman = false; // Флаг римских чисел.

        /* Парсинг чисел */
        try{
            // Пробуем получить обычное число
            left = Integer.parseInt(inputs.get(0));
            try{
                // Если сработало, пробуем получить второе число
                right = Integer.parseInt(inputs.get(2));
            }
            catch (NumberFormatException e){
                throw new InputException("Ошибка ввода!");
            }
        }
        catch (NumberFormatException e){
            try{
                // Пробуем получить римское число
                left = RomanNumber.toRoman(inputs.get(0)).getNumber();
            }
            catch (IllegalArgumentException e1){
                throw new InputException("Ошибка ввода!");
            }
            try{
                // Пробуем получить второе римское число
                right = RomanNumber.toRoman(inputs.get(2)).getNumber();
            }
            catch (IllegalArgumentException e1){
                throw new InputException("Ошибка ввода!");
            }
            isRoman = true; // Активируем флаг римских чисел
        }

        /* Проверяем входные числа на выход за пределы диапазона [1..10] */
        if (!isValidNumber(left) || !isValidNumber(right)) {
            /* Кидаем ошибку с указанием на некоректное значение*/
            throw new InputException("Ошибка ввода!");

        }
        else{
            int result;
            /* Определяем нужную операцию */
            switch (inputs.get(1).charAt(0)) {
                case '+':
                    result = left + right;
                    break;
                case '-':
                    result = left - right;
                    break;
                case '*':
                    result = left * right;
                    break;
                case '/':
                    result = left / right;
                    break;
                default:
                    throw new InputException("Ошибка ввода!");
            }
            /* Проверяем результат на принадлежность диапазону [1..10] */
            if(isValidNumber(result)){
                return isRoman ? RomanNumber.toRoman(result).toString() :
                        Integer.toString(result);

            }else{
                throw new InputException("Результат вычисления не поддерживается калькулятором.");
            }
        }

    }

}

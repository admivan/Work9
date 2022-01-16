public class Main {
    public static void main(String[] args) throws MyArrayDataException {
        int result = 0;
        String[][] arr = {{"1", "2", "3", "5"}, {"5", "6", "5","4"}};
        String[][] error_size ={{"1", "2", "3", "5"}, {"5", "6", "8"}};
        String[][] error_data = {{"1", "2", "2", "5" }, {"5", "7", "a", "5" }};

        System.out.println("Начало работы программы");

        System.out.println("Случай корректного массива");
        try {
            result = analyzArrays(arr);
        } catch(MyArraySizeException | MyArrayDataException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Результат суммирования элементов массива равна "+ result);
        }

        System.out.println("Случай некорректного массива");
        try {
            result = 0;
            result = analyzArrays(error_size);
        } catch(MyArraySizeException | MyArrayDataException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Результат суммирования элементов массива равна "+ result);
        }

        System.out.println("Случай некорректных данных");
        try {
            result = 0;
            result = analyzArrays(error_data);
        } catch(MyArraySizeException | MyArrayDataException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Результат суммирования элементов массива равна "+ result);
        }
    }

    /**
     * Не могу понять почему throws MyArrayDataException, MyArraySizeException не принуждает меня обернуть в try catch
     * при вызове метода
     * @param array
     * @return
     * @throws MyArrayDataException
     * @throws MyArraySizeException
     */
    public static int analyzArrays(String[][] array) throws MyArrayDataException, MyArraySizeException {
        int sum = 0;
        int value;

        if (array.length != 2 || array[0].length != 4 || array[1].length != 4) {
            throw new MyArraySizeException("Размер массива некорректный!");
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    try {
                        value = Integer.parseInt(array[i][j]);
                        sum += value;
                    }catch (RuntimeException e){//TODO: не понятно почему Runtime а не MyArrayDataException пришел методом тыка
                        String message = "в "+ i +" ряду, "+j+" ячейке";
                        throw new MyArrayDataException(message);
                    }

                }
            }
        }
        return sum;
    }
}

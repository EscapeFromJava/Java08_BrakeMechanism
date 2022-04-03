import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String line1 = "12121212";
        String line2 = "21212121";

        String maxLine = "0".repeat(line2.length()) + line1 + "0".repeat(line2.length());
        int[] maxArray = Arrays.stream(maxLine.split("")).mapToInt(Integer::parseInt).toArray();
        int[] subArray = Arrays.stream(line2.split("")).mapToInt(Integer::parseInt).toArray();
        
        ArrayList<int[]> list = new ArrayList<>();
        int startIndex = 0;

        for (int i = 0; i < maxArray.length - subArray.length + 1; i++) {
            boolean flag = true;
            int[] arr = maxArray.clone();
            for (int j = 0, k = startIndex; j < maxArray.length - startIndex; j++, k++) {
                if (j < subArray.length) {
                    if (maxArray[k] + subArray[j] > 3) {
                        flag = false;
                        break;
                    } else
                        arr[k] = maxArray[k] + subArray[j];
                }
            }
            if (flag)
                list.add(arr);
            startIndex++;
        }

        int minLenght = (int) Arrays.stream(list.get(0)).filter(x -> x != 0).count();

        for (var el : list) {
            if ((int)Arrays.stream(el).filter(x -> x != 0).count() < minLenght)
            minLenght = (int)Arrays.stream(el).filter(x -> x != 0).count();
        }

        System.out.println("Наименьшая длина конструкции: " + minLenght);
    }
}

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileInput = "INPUT.txt";
        String fileOutput = "OUTPUT.txt";
        List<String> line = Files.readAllLines(Paths.get(fileInput), StandardCharsets.UTF_8);

        String maxLine = "0".repeat(line.get(1).length()) + line.get(0) + "0".repeat(line.get(1).length());
        int[] maxArray = Arrays.stream(maxLine.split("")).mapToInt(Integer::parseInt).toArray();
        int[] subArray = Arrays.stream(line.get(1).split("")).mapToInt(Integer::parseInt).toArray();
        System.out.println("ne ok");
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

        Files.write(Paths.get(fileOutput), Collections.singleton(String.valueOf(minLenght)));
    }
}

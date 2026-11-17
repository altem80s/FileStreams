import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        ArrayList<String> products = new ArrayList<>();

        System.out.println("Product Writer");
        System.out.println();

        boolean moreProducts = true;

        while (moreProducts) {

            String id = SafeInput.getNonZeroLenString(in, "Enter Product ID");
            String name = SafeInput.getNonZeroLenString(in, "Enter Product Name");
            String description = SafeInput.getNonZeroLenString(in, "Enter Product Description");
            double cost = SafeInput.getDouble(in, "Enter Product Cost");

            String record = id + ", " + name + ", " + description + ", " + cost;

            products.add(record);

            System.out.println();
            moreProducts = SafeInput.getYNConfirm(in, "Add another product?");
            System.out.println();
        }

        String fileName = SafeInput.getNonZeroLenString(in,
                "Enter the file name to save");

        Path file = Path.of(fileName);

        try {
            BufferedWriter writer = Files.newBufferedWriter(file);

            for (String product : products) {
                writer.write(product);
                writer.newLine();
            }

            writer.close();

            System.out.println();
            System.out.println("Data saved to " + fileName);

        } catch (IOException e) {
            System.out.println("ERROR: Could not write to the file.");
            System.out.println(e.getMessage());
        }

        in.close();
    }
}
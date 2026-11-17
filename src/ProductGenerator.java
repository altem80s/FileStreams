import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductGenerator
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        ArrayList<Product> products = new ArrayList<>();

        boolean moreProducts = true;

        while (moreProducts)
        {
            String id = SafeInput.getNonZeroLenString(in, "Enter ID");
            String name = SafeInput.getNonZeroLenString(in, "Enter Product Name");
            String description = SafeInput.getNonZeroLenString(in, "Enter Description");
            double cost = SafeInput.getDouble(in, "Enter Cost");

            Product product = new Product(name, description, id, cost);

            products.add(product);

            moreProducts = SafeInput.getYNConfirm(in, "Add another product?");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the file name to save");
        Path file = Path.of(fileName);

        try
        {
            BufferedWriter writer = Files.newBufferedWriter(file);

            for (Product product : products)
            {
                writer.write(product.toCSV());
                writer.newLine();
            }

            writer.close();

            System.out.println("Product data saved successfully.");
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file.");
            System.out.println(e.getMessage());
        }
    }
}
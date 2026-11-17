import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ProductReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            Path file = chooser.getSelectedFile().toPath();

            ArrayList<Product> products = new ArrayList<>();

            try
            {
                BufferedReader reader = Files.newBufferedReader(file);

                String line;

                while ((line = reader.readLine()) != null)
                {
                    String[] parts = line.split(",");

                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    double cost = Double.parseDouble(parts[3].trim());

                    Product product = new Product(name, description, id, cost);

                    products.add(product);
                }

                reader.close();

                System.out.println();
                System.out.println("Product Data");
                System.out.println();

                System.out.printf("%-10s %-15s %-30s %10s%n",
                        "ID#", "Name", "Description", "Cost");

                System.out.println("================================================================");

                for (Product product : products)
                {
                    System.out.printf("%-10s %-15s %-30s $%9.2f%n",
                            product.getID(),
                            product.getName(),
                            product.getDescription(),
                            product.getCost());
                }
            }
            catch (IOException e)
            {
                System.out.println("Error reading file.");
                System.out.println(e.getMessage());
            }
        }
    }
}
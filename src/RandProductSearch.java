
import javax.swing.*;
import java.io.RandomAccessFile;

public class RandProductSearch
{
    private static final String FILE_NAME = "ProductsRandom.dat";

    public static void main(String[] args)
    {
        String searchName = JOptionPane.showInputDialog(
                null, "Enter part of a product name to search:");

        if (searchName == null || searchName.trim().isEmpty())
        {
            return;
        }

        StringBuilder results = new StringBuilder();
        int matchCount = 0;

        try (RandomAccessFile file =
                     new RandomAccessFile(FILE_NAME, "r"))
        {
            while (file.getFilePointer() < file.length())
            {
                String name = readFixedString(file, 35).trim();
                String description = readFixedString(file, 75).trim();
                String ID = readFixedString(file, 6).trim();
                double cost = file.readDouble();

                if (name.toLowerCase().contains(
                        searchName.trim().toLowerCase()))
                {
                    results.append("Name: ").append(name).append("\n");
                    results.append("Description: ").append(description).append("\n");
                    results.append("ID: ").append(ID).append("\n");
                    results.append("Cost: $").append(cost).append("\n\n");

                    matchCount++;
                }
            }

            if (matchCount == 0)
            {
                results.append("No matching products found.");
            }

            JTextArea output = new JTextArea(results.toString(), 12, 35);
            output.setEditable(false);

            JOptionPane.showMessageDialog(
                    null,
                    new JScrollPane(output),
                    "Product Search Results",
                    JOptionPane.INFORMATION_MESSAGE
            );

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(
                    null, "Error reading file: " + ex.getMessage());
        }
    }

    private static String readFixedString(
            RandomAccessFile file, int length) throws Exception
    {
        StringBuilder value = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            value.append(file.readChar());
        }

        return value.toString();
    }
}

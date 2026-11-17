
import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame
{
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField idField;
    private JTextField costField;
    private JTextField countField;

    private static final String FILE_NAME = "ProductsRandom.dat";

    private int recordCount = 0;

    public RandProductMaker()
    {
        setTitle("Random Product Maker");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        nameField = new JTextField();
        descriptionField = new JTextField();
        idField = new JTextField();
        costField = new JTextField();
        countField = new JTextField("0");
        countField.setEditable(false);

        add(new JLabel("Product Name:"));
        add(nameField);

        add(new JLabel("Description:"));
        add(descriptionField);

        add(new JLabel("Product ID:"));
        add(idField);

        add(new JLabel("Cost:"));
        add(costField);

        add(new JLabel("Records Entered:"));
        add(countField);

        JButton addButton = new JButton("Add Product");
        JButton quitButton = new JButton("Quit");

        add(addButton);
        add(quitButton);

        addButton.addActionListener(e -> addProduct());
        quitButton.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addProduct()
    {
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        String ID = idField.getText().trim();
        String costText = costField.getText().trim();

        if (name.isEmpty() || description.isEmpty() ||
                ID.isEmpty() || costText.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields.");
            return;
        }

        if (name.length() > 35 ||
                description.length() > 75 || ID.length() > 6)
        {
            JOptionPane.showMessageDialog(this,
                    "Name: maximum 35 characters.\n" +
                            "Description: maximum 75 characters.\n" +
                            "ID: maximum 6 characters.");
            return;
        }

        double cost;

        try
        {
            cost = Double.parseDouble(costText);

            if (cost < 0)
                throw new NumberFormatException();
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid cost.");
            return;
        }

        Product product = new Product(name, description, ID, cost);

        try (RandomAccessFile file =
                     new RandomAccessFile(FILE_NAME, "rw"))
        {
            file.seek(file.length());

            file.writeChars(product.getFixedName());
            file.writeChars(product.getFixedDescription());
            file.writeChars(product.getFixedID());
            file.writeDouble(product.getCost());

            recordCount++;
            countField.setText(String.valueOf(recordCount));

            nameField.setText("");
            descriptionField.setText("");
            idField.setText("");
            costField.setText("");

            nameField.requestFocus();

            JOptionPane.showMessageDialog(this,
                    "Product added successfully!");

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this,
                    "Error writing file: " + ex.getMessage());
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(RandProductMaker::new);
    }
}


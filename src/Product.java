import java.util.Objects;

public class Product
{
    private String name;
    private String description;
    private String ID;
    private double cost;

    public Product(String name, String description, String ID, double cost)
    {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getID()
    {
        return ID;
    }

    public double getCost()
    {
        return cost;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public String toCSV()
    {
        return ID + "," + name + "," + description + "," + cost;
    }

    public String toJSON()
    {
        return "{\"ID\":\"" + ID + "\",\"name\":\"" + name
                + "\",\"description\":\"" + description
                + "\",\"cost\":" + cost + "}";
    }

    public String toXML()
    {
        return "<Product><ID>" + ID + "</ID><name>" + name
                + "</name><description>" + description
                + "</description><cost>" + cost + "</cost></Product>";
    }

    @Override
    public String toString()
    {
        return name + " " + description + " $" + cost;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof Product))
        {
            return false;
        }

        Product product = (Product) o;

        return Double.compare(cost, product.cost) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(ID, product.ID);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, description, ID, cost);
    }



    public String getFixedName()
    {
        return String.format("%-35s", name).substring(0, 35);
    }

    public String getFixedDescription()
    {
        return String.format("%-75s", description).substring(0, 75);
    }

    public String getFixedID()
    {
        return String.format("%-6s", ID).substring(0, 6);
    }
}
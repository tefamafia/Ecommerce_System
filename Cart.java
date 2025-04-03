import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();
    private Customer customer;
    private static final double SHIPPING_FEE_PER_KG = 30.0;
    public Cart (Customer customer)
    {
        this.customer = customer;
    }
    public void addItem (Product product , int quantity)
    {
        if(!product.IsAvailable(quantity)) {
            System.out.println("Error: Not enough stock.");
            return;
        }
        if (product instanceof ExpirableProduct && ((ExpirableProduct) product).IsExpired()) {
            System.out.println("Error: " + product.GetName() + " is expired.");
            return;
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        double subtotal = 0;
        double totalWeight = 0;
        List<String> shippingItems = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            double itemTotal = product.GetPrice() * quantity;
            subtotal += itemTotal;

            System.out.println("DEBUG: " + quantity + "x " + product.GetName() + " = " + itemTotal);

            if (product instanceof ShippableProduct) {
                Shippable shippable = (Shippable) product;
                double itemWeight = shippable.getWeight() * quantity;
                totalWeight += itemWeight;
                shippingItems.add(quantity + "x " + product.GetName() + " " + itemWeight + "kg");
            }
        }

        System.out.println("DEBUG: Subtotal = " + subtotal);
        System.out.println("DEBUG: Total weight = " + totalWeight);

        double shippingFee = totalWeight * SHIPPING_FEE_PER_KG;
        System.out.println("DEBUG: Shipping Fee = " + shippingFee);

        double totalAmount = subtotal + shippingFee;
        System.out.println("DEBUG: Total Amount = " + totalAmount);


        if (!shippingItems.isEmpty()) {
            System.out.println("\n** Shipment notice **");
            for (String item : shippingItems) {
                System.out.println(item);
            }
            System.out.println("Total package weight: " + totalWeight + "kg");
        }

        System.out.println("\n** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%-20s %5d x %.2f = %.2f\n", product.GetName(), quantity, product.GetPrice(), (product.GetPrice() * quantity));
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal: %.2f\n", subtotal);
        System.out.printf("Shipping: %.2f\n", shippingFee);
        System.out.printf("Amount: %.2f\n", totalAmount);


        items.clear();
    }




}

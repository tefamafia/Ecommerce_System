import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("mohamed");


        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100.0, 10, LocalDate.of(2024, 1,5 ));
        ShippableProduct biscuits = new ShippableProduct("Biscuits", 150.0, 5, 0.7);
        Product scratchCard = new Product("Mobile Scratch Card", 50.0, 20) {
            @Override
            public boolean isShippable() {
                return false;
            }
        };


        Cart cart = new Cart(customer);
        cart.addItem(cheese, 2);
        cart.addItem(biscuits, 1);
        cart.addItem(scratchCard, 1);

        // Checkout
        cart.checkout();
    }
    }

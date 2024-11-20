import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InvoiceGenerator {
    public static void generateInvoice(Order order, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Ice Cream Shop Invoice\n");

            Map<String, Integer> flavorCount = new HashMap<>();
            for (Flavor flavor : order.getFlavors()) {
                flavorCount.put(flavor.getName(), flavorCount.getOrDefault(flavor.getName(), 0) + 1);
            }

            Map<String, Integer> toppingCount = new HashMap<>();
            for (Topping topping : order.getToppings()) {
                toppingCount.put(topping.getName(), toppingCount.getOrDefault(topping.getName(), 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : flavorCount.entrySet()) {
                String flavorName = entry.getKey();
                int quantity = entry.getValue();
                double pricePerScoop = order.getFlavors().stream()
                        .filter(flavor -> flavor.getName().equals(flavorName))
                        .findFirst()
                        .get()
                        .getPrice();
                double totalCost = pricePerScoop * quantity;
                writer.write(String.format("%s - %d scoop(s): $%.2f\n", flavorName, quantity, totalCost));
            }

            for (Map.Entry<String, Integer> entry : toppingCount.entrySet()) {
                String toppingName = entry.getKey();
                int quantity = entry.getValue();
                double pricePerTime = order.getToppings().stream()
                        .filter(topping -> topping.getName().equals(toppingName))
                        .findFirst()
                        .get()
                        .getPrice();
                double totalCost = pricePerTime * quantity;
                writer.write(String.format("%s - %d time(s): $%.2f\n", toppingName, quantity, totalCost));
            }

            if (order.isWaffleCone()) {
                writer.write(String.format("Waffle Cone - 1 time: $%.2f\n", Order.getWaffleConePrice()));
            }

            double subtotal = order.calculateSubtotal();
            double tax = order.calculateTax();
            double total = order.calculateTotal();
            writer.write(String.format("Subtotal: $%.2f\n", subtotal));
            writer.write(String.format("Tax: $%.2f\n", tax));
            writer.write(String.format("Total Amount Due: $%.2f\n", total));
        } catch (IOException e) {
            System.out.println("Error writing invoice: " + e.getMessage());
        }
    }
}

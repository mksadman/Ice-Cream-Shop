public class Main {
    public static void main(String[] args) {
        IceCreamRepository repository = new IceCreamRepository();
        repository.addFlavor(new Flavor("Mint Chocolate Chip", 2.80));
        repository.addFlavor(new Flavor("Chocolate Fudge", 3.00));
        repository.addTopping(new Topping("Sprinkles", 0.30));
        repository.addTopping(new Topping("Fresh Strawberries", 1.00));

        Order order = new Order(true);
        order.addFlavor(repository.getFlavors().get(0));
        order.addFlavor(repository.getFlavors().get(1));
        order.addTopping(repository.getToppings().get(0));
        order.addTopping(repository.getToppings().get(1));

        InvoiceGenerator.generateInvoice(order, "invoice.txt");
    }
}

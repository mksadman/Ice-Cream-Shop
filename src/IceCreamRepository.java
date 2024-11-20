import java.util.ArrayList;
import java.util.List;

public class IceCreamRepository {
    private final List<Flavor> flavors = new ArrayList<>();
    private final List<Topping> toppings = new ArrayList<>();

    public void addFlavor(Flavor flavor) {
        flavors.add(flavor);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public List<Flavor> getFlavors() {
        return new ArrayList<>(flavors);
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }
}

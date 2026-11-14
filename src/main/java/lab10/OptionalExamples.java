package lab10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple examples of java.util.Optional:
 * 1. Optional<Address> as a return type
 * 2. a list of Optionals
 * 3. handling an Optional
 */
public class OptionalExamples {

    static class Address {
        private final String city;

        Address(String city) {
            this.city = city;
        }

        String getCity() {
            return city;
        }
    }

    static class User {
        private final String name;
        private final Address address; // may be null

        User(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        String getName() {
            return name;
        }

        // The address may be missing, so we return Optional instead of null
        Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }
    }

    public static void main(String[] args) {
        User alice = new User("Alice", new Address("Kyiv"));
        User bob = new User("Bob", null);

        optionalAddress(alice, bob);
        listOfOptionals(alice, bob);
        handlingOptional(alice, bob);
    }

    // 1. Optional<Address>
    private static void optionalAddress(User alice, User bob) {
        System.out.println("=== Optional<Address> ===");
        Optional<Address> a = alice.getAddress();
        Optional<Address> b = bob.getAddress();

        System.out.println("Alice has address: " + a.isPresent()); // true
        System.out.println("Bob has address:   " + b.isPresent()); // false
    }

    // 2. List<Optional<Address>>
    private static void listOfOptionals(User alice, User bob) {
        System.out.println("\n=== List<Optional<Address>> ===");
        List<Optional<Address>> addresses = List.of(
                alice.getAddress(),
                bob.getAddress());

        for (Optional<Address> address : addresses) {
            System.out.println(address.map(Address::getCity).orElse("no address"));
        }

        // Keep only present values
        List<String> cities = new ArrayList<>();
        for (Optional<Address> address : addresses) {
            if (address.isPresent()) {
                cities.add(address.get().getCity());
            }
        }
        System.out.println("Cities: " + cities); // [Kyiv]
    }

    // 3. Handling an Optional
    private static void handlingOptional(User alice, User bob) {
        System.out.println("\n=== Handling Optional ===");
        for (User user : List.of(alice, bob)) {
            Optional<Address> address = Optional.of(user.getAddress().orElse(new Address("dfdf")));

            // do something only if present
            address.ifPresent(a -> System.out.println(user.getName() + " lives in " + a.getCity()));

            // default value
            String city = address.map(Address::getCity).orElse("unknown");
            System.out.println(user.getName() + ": " + city);
        }

        // throw if missing
        try {
            bob.getAddress().orElseThrow(() -> new IllegalStateException("Bob has no address"));
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}

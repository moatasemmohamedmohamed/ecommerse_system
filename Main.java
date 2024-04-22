import java.util.Scanner;

class Product {
    private int productId;
    private String name;
    private float price;

    public Product(int productId, String name, float price) {
        this.productId = Math.abs(productId); // Ensure positive value
        this.name = name;
        this.price = Math.abs(price); // Ensure positive value
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

class ElectronicProduct extends Product {
    private String brand;
    private int warrantyPeriod;

    public ElectronicProduct(int productId, String name, float price, String brand, int warrantyPeriod) {
        super(productId, name, price);
        this.brand = brand;
        this.warrantyPeriod = Math.abs(warrantyPeriod); // Ensure positive value
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}

class ClothingProduct extends Product {
    private String size;
    private String fabric;

    public ClothingProduct(int productId, String name, float price, String size, String fabric) {
        super(productId, name, price);
        this.size = size;
        this.fabric = fabric;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }
}

class BookProduct extends Product {
    private String author;
    private String publisher;

    public BookProduct(int productId, String name, float price, String author, String publisher) {
        super(productId, name, price);
        this.author = author;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

class Customer {
    private int customerId;
    private String name;
    private String address;

    public Customer(int customerId, String name, String address) {
        this.customerId = Math.abs(customerId); // Ensure positive value
        this.name = name;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

class Cart {
    private int customerId;
    private int nProducts;
    private Product[] products;

    public Cart(int customerId, int nProducts) {
        this.customerId = Math.abs(customerId); // Ensure positive value
        this.nProducts = Math.abs(nProducts); // Ensure positive value
        this.products = new Product[nProducts];
    }

    public void addProduct(Product product, int index) {
        if (index >= 0 && index < nProducts) {
            products[index] = product;
        } else {
            System.out.println("Invalid index.");
        }
    }

    public float calculatePrice() {
        float totalPrice = 0;
        for (Product product : products) {
            if (product != null) {
                totalPrice += product.getPrice();
            }
        }
        return totalPrice;
    }

    public void placeOrder() {
        // Place order logic goes here
    }

    // Getter for products array
    public Product[] getProducts() {
        return products;
    }
}

class Order {
    private int customerId;
    private int orderId;
    private Product[] products;
    private float totalPrice;

    public Order(int customerId, int orderId, Product[] products) {
        this.customerId = Math.abs(customerId); // Ensure positive value
        this.orderId = Math.abs(orderId); // Ensure positive value
        this.products = products;
        this.totalPrice = calculateTotalPrice();
    }

    public void printOrderInfo() {
        System.out.println("Here's your order's summary:");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Products:");

        for (Product product : products) {
            if (product != null) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
        }

        System.out.println("Total Price: $" + totalPrice);
    }

    private float calculateTotalPrice() {
        float totalPrice = 0;
        for (Product product : products) {
            if (product != null) {
                totalPrice += product.getPrice();
            }
        }
        return totalPrice;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the E-Commerce System!");
        System.out.println("Please enter your id");
        int customerId = Math.abs(scanner.nextInt()); // Ensure positive value
        scanner.nextLine(); // Consume newline
        System.out.println("Please enter your name");
        String name = scanner.nextLine();
        System.out.println("Please enter your address");
        String address = scanner.nextLine();

        Customer customer = new Customer(customerId, name, address);

        System.out.println("How many products you want to add to your cart?");
        int numProducts = scanner.nextInt();
        Cart cart = new Cart(customerId, numProducts);

        for (int i = 0; i < numProducts; i++) {
            System.out.println("Which product would you like to add? 1- Smartphone 2- T-Shirt 3- OOP");
            int productType = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Product product = null;
            switch (productType) {
                case 1:
                    product = new ElectronicProduct(1, "Smartphone", 599.9f, "Samsung", 1);
                    break;
                case 2:
                    product = new ClothingProduct(2, "T-shirt", 19.99f, "Medium", "Cotton");
                    break;
                case 3:
                    product = new BookProduct(3, "OOP", 39.99f, "O’Reilly", "X Publications");
                    break;
                default:
                    System.out.println("Invalid product choice.");
            }
            if (product != null) {
                cart.addProduct(product, i);
            }
        }

        float totalPrice = cart.calculatePrice();
        System.out.println("Your total is $" + totalPrice + ". Would you like to place the order? 1 Yes 2- No");
        int choice = scanner.nextInt();
        if (choice == 1) {
            Order order = new Order(customerId, 1, cart.getProducts());
            order.printOrderInfo();
        }

    }
}

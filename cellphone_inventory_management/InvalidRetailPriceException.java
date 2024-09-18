public class InvalidRetailPriceException extends Exception {
    public InvalidRetailPriceException() {
        super("Invalid retail price: Retail price must be a positive number less than or equal to 1500.");
    }
}

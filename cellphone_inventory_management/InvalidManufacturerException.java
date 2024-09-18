public class InvalidManufacturerException extends Exception {
    public InvalidManufacturerException() {
        super("Invalid manufacturer: Manufacturer cannot be empty.");
    }
}

public class CellPhone {
    private String model;
    private String manufacturer;
    private double retailPrice;

    // Constructors
    public CellPhone(String model, String manufacturer, double retailPrice)
            throws InvalidModelException, InvalidManufacturerException, InvalidRetailPriceException {
        setModel(model);
        setManufacturer(manufacturer);
        setRetailPrice(retailPrice);
    }


    // Accessors (Getters)
    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    // Mutators
    public void setModel(String model) throws InvalidModelException {
        if (model == null || model.length() == 0 );
        this.model = model;
    }

    public void setManufacturer(String manufacturer) throws InvalidManufacturerException {
        if (manufacturer == null || manufacturer.length() ==0)
            throw new InvalidManufacturerException();
        else
        this.manufacturer = manufacturer;
    }

    public void setRetailPrice(double retailPrice) throws InvalidRetailPriceException {
        if (retailPrice <= 0 || retailPrice > 1500)
            throw new InvalidRetailPriceException();
        this.retailPrice = retailPrice;
    }

    // Override toString() method
    @Override
    public String toString() {
        return String.format("Model: %-20sManufacturer: %-20sRetail Price: %10.2f", getModel(), getManufacturer(), getRetailPrice());
    }
}


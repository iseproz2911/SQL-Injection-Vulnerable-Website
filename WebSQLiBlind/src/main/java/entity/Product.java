package entity;

import java.math.BigDecimal;

public class Product {
    private int ID;
    private String NameSP;
    private BigDecimal Price;
    private String Description;
    private String codeProduct;

    public String getCodeProduct() {
		return codeProduct;
	}

	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getNameSP() {
        return NameSP;
    }

    public void setNameSP(String nameSP) {
        this.NameSP = nameSP;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        this.Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
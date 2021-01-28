package com.omerkorkmaz.moviboostore.model;


import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;


public class ProductForm 
{
	private int id;

	private String sku;

	private String name;
	private String description;

	private double price;
	private String imageUrl;
	private MultipartFile image;
	private boolean disabled;
	private int categoryId;
	private int subCategoryId;

	public Product toProduct() {
		Product p = new Product();
		p.setProductId(id);
		p.setTitle(name);
		p.setDescription(description);
		p.setDisabled(disabled);
		p.setPrice(price);
		p.setSku(sku);
//		Category category = new Category();
//		category.setCategoryId(categoryId);
//		p.setCategory(category );
		//p.setImageUrl(WebUtils.IMAGES_PREFIX+id+".jpg");
		return p;
	}
	
	public static ProductForm fromProduct(Product product)
	{
		ProductForm p = new ProductForm();
		p.setId(product.getProductId());
		p.setName(product.getTitle());
		p.setDescription(product.getDescription());
		p.setDisabled(product.isDisabled());
		p.setPrice(product.getPrice());
		p.setSku(product.getSku());
//		p.setCategoryId(product.getCategory().getCategoryId());
		//p.setImageUrl(WebUtils.IMAGES_PREFIX+product.getId()+".jpg");
		return p;
	}

	public ProductForm() {
	}

	public ProductForm(int id, String sku, String name, String description, double price, String imageUrl, MultipartFile image, boolean disabled, int categoryId, int subCategoryId) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.image = image;
		this.disabled = disabled;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
}

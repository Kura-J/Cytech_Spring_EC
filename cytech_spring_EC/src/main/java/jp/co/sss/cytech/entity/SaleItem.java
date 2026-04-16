package jp.co.sss.cytech.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_items")
public class SaleItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_item_id")
	private Integer saleItemId;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "company_id")
	private Integer companyId;
	
	@Column(name = "sale_name")
	private String saleName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "discount_rate")
	private Integer discountRate;
	
	@Column(name = "sales_img_path")
	private String salesImgPath;
	
	@Column(name = "start_month")
	private Timestamp startMonth;
	
	@Column(name = "end_month")
	private Timestamp endMonth;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public Integer getSaleItemId() {
		return saleItemId;
	}

	public void setSaleItemId(Integer saleItemId) {
		this.saleItemId = saleItemId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String ddescription) {
		this.description = ddescription;
	}

	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	public String getSalesImgPath() {
		return salesImgPath;
	}

	public void setSalesImgPath(String salesImgPath) {
		this.salesImgPath = salesImgPath;
	}

	public Timestamp getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(Timestamp startMonth) {
		this.startMonth = startMonth;
	}

	public Timestamp getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(Timestamp endMonth) {
		this.endMonth = endMonth;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}































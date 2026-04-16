package jp.co.sss.cytech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.cytech.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByProductNameContaining(String productName);
	List<Product> findByCategoryId(Integer categoryId);
	List<Product> findByProductNameContainingAndCategoryId(String productName, Integer categoryId);
}

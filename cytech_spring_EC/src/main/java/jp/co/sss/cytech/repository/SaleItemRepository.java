package jp.co.sss.cytech.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.cytech.entity.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Integer>{
	
	@Query("""
		SELECT s
		FROM SaleItem s
		WHERE
			(s.startMonth IS NULL OR s.startMonth <= :now)
		AND
			(s.endMonth IS NULL OR s.endMonth >= :now)
		ORDER BY s.saleItemId ASC
	""")
	List<SaleItem> findActiveSaleItems(@Param("now") Timestamp now);
}

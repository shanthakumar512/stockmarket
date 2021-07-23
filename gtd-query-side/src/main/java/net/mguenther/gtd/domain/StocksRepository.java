package net.mguenther.gtd.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@Repository
public interface StocksRepository extends JpaRepository<Stock, Long> {
	
		Optional<List<Stock>> findByCompanyCode(String companyCode);
		
		void deleteByCompanyCode(String companyCode);
}

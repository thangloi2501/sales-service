package com.demo.sales.db.repository;

import com.demo.sales.db.entity.StoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Loi Nguyen
 *
 */
@Repository
public interface StoreOrderRepo extends JpaRepository<StoreOrder, Long> {

}

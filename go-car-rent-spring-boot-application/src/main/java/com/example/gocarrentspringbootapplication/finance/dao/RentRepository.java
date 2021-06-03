package com.example.gocarrentspringbootapplication.finance.dao;

import com.example.gocarrentspringbootapplication.finance.po.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
    String TENANT_RENT_QUERY = "SELECT * FROM rents WHERE tenant_id=?1 ORDER BY rented_at DESC";
    @Query(value = TENANT_RENT_QUERY, nativeQuery = true)
    List<Rent> getAllByTenant(Long id);
}

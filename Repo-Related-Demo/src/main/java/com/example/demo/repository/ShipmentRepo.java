package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Shipment;

@Repository
public interface ShipmentRepo extends JpaRepository<Shipment, String> {

	Shipment findByType(String type);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Shipment SET ordername = :ordername WHERE  type = :type")
	Integer updateShipmentByType(String ordername, String type);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE Shipment WHERE  type = :type")
	Integer deleteShipmentByType(String type);

}

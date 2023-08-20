package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Shipment;
import com.example.demo.repository.ShipmentRepo;

@RestController
public class ShipmentController {
	
	@Autowired
	private ShipmentRepo shipmentRepo;
	
	@PostMapping("/shipmentOperation/{type}")
	public ResponseEntity<Shipment> shipmentOperation(@RequestBody Shipment shipment, @PathVariable("type") String type){
		
		if (type.equals("new")) {
			Shipment ship = shipmentRepo.save(shipment);
			return new ResponseEntity<Shipment>(ship, HttpStatus.CREATED);
		}
//		else if (type.equals("get")) {
//			Shipment ship = shipmentRepo.findByType(type);
//			return new ResponseEntity<Shipment>(HttpStatus.OK);
//		}
		else if (type.equals("delete")) {
			 int status = shipmentRepo.deleteShipmentByType(type);
			return new ResponseEntity<Shipment>(HttpStatus.NO_CONTENT);
		}
		return null;
		
	}

}

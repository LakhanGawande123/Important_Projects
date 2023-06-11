package com.nodeservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;  
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;  
import com.nodeservice.model.Node;
import com.nodeservice.response.NodeResponse;
import com.nodeservice.service.NodeService;

@RequestMapping("api/Node-Service")
@RestController
public class NodeController {
	
	@Autowired
	private NodeService nodeService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello Everyone.....!";
	}
	
	@GetMapping("/calling-hello")
	public String getHello() {
		String url = "http://localhost:9099/api/Node-Service/hello";
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
	
	@PostMapping()
	public ResponseEntity<NodeResponse> storedNodeData(@RequestBody Node node) {
		NodeResponse nodeResponse = nodeService.addNode(node);
		return ResponseEntity.status(HttpStatus.CREATED).body(nodeResponse);
	}
	
	
//	@PostMapping()
//	public Node savenode(@RequestBody Node node) {
//		Node node1 = nodeService.addNode(node);
//		return node1;
//		
//	}
	
	@GetMapping("/getAll/{realm}")
	public List<Node> getAllNodes(@PathVariable String realm){
		return nodeService.getNodes();
	}
	
//	@GetMapping("/nodeSpecification/{id}")
//	public ResponseEntity<MappingJacksonValue> getNode(@PathVariable UUID id) {
//		Optional<Node> node1 = nodeService.getSingleNode(id);
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(node1);
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("email","status");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("NodeFilter",filter);
//        mappingJacksonValue.setFilters(filters);
//
//        return ResponseEntity.of(Optional.of(mappingJacksonValue));
//		
//	}
	
//	@GetMapping("/nodeSpecification/{email}")
//	public ResponseEntity<MappingJacksonValue> getNodeByEmail(@PathVariable String email) {
//		Node node1 = nodeService.getByEmail(email);
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(node1);
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("email","nodeType");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicfilter",filter);
//        mappingJacksonValue.setFilters(filters);
//
//        return ResponseEntity.of(Optional.of(mappingJacksonValue));
//	}
	
	@GetMapping("/{id}")
	public Optional<Node> getNode1(@PathVariable UUID id) {
		Optional<Node> node1 = nodeService.getSingleNode(id);
		return node1;
	}
	
	
	@PutMapping("/update/{id}")
	public Node updatedata(@RequestBody Node node, @PathVariable UUID id) {
		Node node1 = nodeService.updateNode(id, node);
		return node1;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNode(@PathVariable("id") UUID id) {
		nodeService.deleteNode(id);
		return new ResponseEntity<>("Node deleted successfully...!", HttpStatus.OK);
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String email) {

		String response = nodeService.forgotPassword(email);

		if (!response.startsWith("Invalid")) {
			response = "http://localhost:9099/reset-password?token=" + response;
		}
		return response;
	}

	@PutMapping("/reset-password")
	public String resetPassword(@RequestParam String token,
			@RequestParam String password) {

		return nodeService.resetPassword(token, password);
	}
	
	
	
	
	
	
	
	
}

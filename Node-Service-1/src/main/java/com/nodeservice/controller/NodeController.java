package com.nodeservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nodeservice.model.Node;
import com.nodeservice.service.NodeService;

@RequestMapping("api/Node-Service")
@RestController
public class NodeController {
	
	@Autowired
	private NodeService nodeService;
	
	@PostMapping()
	public Node savenode(@RequestBody Node node) {
		Node node1 = nodeService.addNode(node);
		return node1;
		
	}
	
	@GetMapping("/getAll")
	public List<Node> getAllNodes(){
		return nodeService.getNodes();
	}
	
	@GetMapping("/{id}")
	public Optional<Node> getNode(@PathVariable UUID id) {
		return nodeService.getSingleNode(id);
		
	}
	
	@PutMapping("/update/{id}")
	public Node updatedata(@RequestBody Node node, @PathVariable UUID id) {
		Node node1 = nodeService.updateNode(id, node);
		return node1;
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

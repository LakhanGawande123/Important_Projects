package com.nodeservice.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nodeservice.model.Node;
import com.nodeservice.model.Status;
import com.nodeservice.model.repository.NodeRepository;
import com.nodeservice.response.NodeResponse;

@Service
public class NodeService {
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private NodeRepository nodeRepository;
	
	//Add Node with DATE & STATUS
	public NodeResponse addNode(Node node) {
		Date date = new Date();
		node.setTime(date);
		node.setRealm("Consumer");
		node.setStatus(Status.PENDING);
		Node node1 = nodeRepository.save(node);
		NodeResponse nodeResponse = modelMapper.map(node1, NodeResponse.class);

		return nodeResponse;
	}
	
	//Get list of node
	public List<Node> getNodes(){
		return nodeRepository.findAll();
	}
	
	//Get specific node by id
	public Optional<Node> getSingleNode(UUID id) {
		return nodeRepository.findById(id);
	}
	
	public Node getByEmail(String email) {
		return nodeRepository.findByEmail(email);
	}
	
	// Update Specific fields
	public Node updateNode(UUID id, Node node) {
		Node nodeDetails = nodeRepository.findById(id).get();
		nodeDetails.setEmail(node.getEmail());
		nodeDetails.setStatus(node.getStatus());
		return nodeRepository.save(nodeDetails);
	}
	
	public String forgotPassword(String email) {

		Optional<Node> nodeOptional = Optional
				.ofNullable(nodeRepository.findByEmail(email));

		if (!nodeOptional.isPresent()) {
			return "Invalid email id.";
		}

		Node node = nodeOptional.get();
		node.setToken(generateToken());

		node = nodeRepository.save(node);

		return node.getToken();
	}
	
	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}
	
	public String resetPassword(String token, String password) {

		Optional<Node> nodeOptional = Optional
				.ofNullable(nodeRepository.findByToken(token));

		if (!nodeOptional.isPresent()) {
			return "Invalid token.";
		}

		//LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

	

		  Node node = nodeOptional.get();

		node.setPassword(password);
		node.setToken(null);
		//node.setTokenCreationDate(null);

		nodeRepository.save(node);

		return "Your password successfully updated.";
	}
	
	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}
	
	public void deleteNode(UUID id) {
		nodeRepository.deleteById(id);
	}
	
	

}

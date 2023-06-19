package com.nodeservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nodeservice.dto.CloudServerDTO;
import com.nodeservice.model.CloudServer;
import com.nodeservice.repo.CloudServerRepository;

@Service
public class CloudServerService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CloudServerRepository clRepository;

	public CloudServerDTO storedCloudServerData(CloudServer cloudServer) {

//		if (clRepository.existsById(cloudServer.getDevicecloudservertype())) {
//			ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Device Cloud Server is already taken");
//			throw new BadRequestException(apiResponse);
//		}
		CloudServer cloudServer1 = clRepository.save(cloudServer);
		CloudServerDTO cloudServerdto = modelMapper.map(cloudServer1, CloudServerDTO.class);
		return cloudServerdto;

	}

	public List<CloudServerDTO> getAllDeviceCloudServer() {
		List<CloudServer> cloudServer1 = clRepository.findAll();
		List<CloudServerDTO> cloudServerdtos = new ArrayList<>();
		for (CloudServer server : cloudServer1) {
			// modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategy.STRICT);
			CloudServerDTO cloudServerdto = modelMapper.map(server, CloudServerDTO.class);
			cloudServerdtos.add(cloudServerdto);
		}
		return cloudServerdtos;
	}

}

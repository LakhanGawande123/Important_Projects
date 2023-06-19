package com.nodeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nodeservice.dto.CloudServerDTO;
import com.nodeservice.model.CloudServer;
import com.nodeservice.service.CloudServerService;

@RestController
//@RequestMapping("/PartnerPortal")
public class CloudController {

	@Autowired
	private CloudServerService cService;

	@PostMapping(value = "/createShared-dcsInfo", produces = { "application/json"})
	public ResponseEntity<CloudServerDTO> storedCloudServer(@RequestBody CloudServer cloudServer) {
		CloudServerDTO cloudServerDTO = cService.storedCloudServerData(cloudServer);
		//if (cloudServer.getDevicecloudservertype() != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(cloudServerDTO);
//		} else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cloudServerDTO);
//		}
	}

	@GetMapping("/get")
	public ResponseEntity<List<CloudServerDTO>> getAlldcs() {
		List<CloudServerDTO> cloudServerDTO = cService.getAllDeviceCloudServer();
		return ResponseEntity.status(HttpStatus.OK).body(cloudServerDTO);
	}

//	int addres = spw.publishDCS(token,xml);
//	if(addres!=201){ 
//	session.setAttribute("msg", "Device Cloud Server Failed,Try Again"); 
//	List<PlanBean> dcslist = spw.getdcs(id,token);
//	session.setAttribute("dcslist",dcslist);
//	return new ModelAndView("dcslist");
//	}
//	else{
//	session.setAttribute("msg", "Device Cloud Server Published");
//	List<PlanBean> dcslist = spw.getdcs(id,token);
//	session.setAttribute("dcslist",dcslist);
//	return new ModelAndView("dcslist");
//	}

}

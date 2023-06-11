/*
 * package com.nodeservice.controller;
 * 
 * import java.util.Optional; import java.util.UUID;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.converter.json.MappingJacksonValue; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.fasterxml.jackson.databind.ser.FilterProvider; import
 * com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter; import
 * com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider; import
 * com.nodeservice.model.Node; import com.nodeservice.service.NodeService;
 * 
 * @RequestMapping("api/Node-Service1")
 * 
 * @RestController public class NodeFiltterController {
 * 
 * @Autowired private NodeService nodeService;
 * 
 * 
 * @GetMapping("/getByBuyerId/{buyerId}") public MappingJacksonValue
 * findContractByBuyerId(@PathVariable String buyerId) {
 * 
 * Contract contract = contractService.getListByBuyerId(buyerId);
 * 
 * MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(contract);
 * 
 * SimpleBeanPropertyFilter filter =
 * SimpleBeanPropertyFilter.filterOutAllExcept("consumerAdminId","orderId",
 * "buyerId","category"); FilterProvider filters = new
 * SimpleFilterProvider().addFilter("ContractFilter",filter);
 * 
 * mappingJacksonValue.setFilters(filters); return mappingJacksonValue;
 * 
 * 
 * @GetMapping("/{id}") public MappingJacksonValue getNode(@PathVariable UUID
 * id) { Optional<Node> node1 = nodeService.getSingleNode(id);
 * MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(node1);
 * 
 * SimpleBeanPropertyFilter filter =
 * SimpleBeanPropertyFilter.filterOutAllExcept("status"); FilterProvider filters
 * = new SimpleFilterProvider().addFilter("NodeFilter",filter);
 * 
 * mappingJacksonValue.setFilters(filters); return mappingJacksonValue; }
 * 
 * @PostMapping() public MappingJacksonValue savenode(@RequestBody Node node) {
 * Node node1 = nodeService.addNode(node); MappingJacksonValue
 * mappingJacksonValue = new MappingJacksonValue(node1);
 * 
 * SimpleBeanPropertyFilter filter =
 * SimpleBeanPropertyFilter.filterOutAllExcept("status"); FilterProvider filters
 * = new SimpleFilterProvider().addFilter("NodeFilter",filter);
 * 
 * mappingJacksonValue.setFilters(filters); return mappingJacksonValue;
 * 
 * }
 * 
 * }
 */
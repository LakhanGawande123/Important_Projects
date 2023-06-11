//package com.nodeservice.model;
//
//import java.io.IOException;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.ObjectCodec;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//
//public class VericalDeserializer extends JsonDeserializer<Vertical> {
//
//	@Override
//	public Vertical deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//
//		ObjectCodec codec = p.getCodec();
//        JsonNode node = codec.readTree(p);
//        
//     // Extract customer properties from JSON node
//        String customerId = node.get("customerId").asText();
//        String customerName = node.get("customerName").asText();
//        
//        // Create a Customer object
//        Vertical vert = new Vertical();
//        vert.setVerticalid(customerId);
//        vert.setVerticalname(customerName);
//       
//		return vert;
//	}
//
//}

package com.nodeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
//@Table(name = "cloudserver", uniqueConstraints = { @UniqueConstraint(columnNames = { "devicecloudservertype" }})
@TypeDef(name = "jsonb", typeClass = JsonStringType.class)
@Table(name = "cloudserver")
public class CloudServer {

	@JsonIgnore
	private String serialno;
	@JsonIgnore
	private String plantype;
	@JsonIgnore
	private String domaintype;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dcst")
	private String devicecloudservertype;

	private String version;
	private String friendlyname;
	private String tenancytype;
	private String deploymenttype;
	private String baseurl;
	private String region;
	private String ipaddress;
	private String port;

	private String dcsimglink;

	@Column(name = "website")
	private String accesslink;
	private String status;
	
	@Type(type = "jsonb") // See (2)
    @Column(name = "jsonb")
    private Address address;

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getPlantype() {
		return plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public String getDomaintype() {
		return domaintype;
	}

	public void setDomaintype(String domaintype) {
		this.domaintype = domaintype;
	}

	public String getDevicecloudservertype() {
		return devicecloudservertype;
	}

	public void setDevicecloudservertype(String devicecloudservertype) {
		this.devicecloudservertype = devicecloudservertype;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFriendlyname() {
		return friendlyname;
	}

	public void setFriendlyname(String friendlyname) {
		this.friendlyname = friendlyname;
	}

	public String getTenancytype() {
		return tenancytype;
	}

	public void setTenancytype(String tenancytype) {
		this.tenancytype = tenancytype;
	}

	public String getDeploymenttype() {
		return deploymenttype;
	}

	public void setDeploymenttype(String deploymenttype) {
		this.deploymenttype = deploymenttype;
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDcsimglink() {
		return dcsimglink;
	}

	public void setDcsimglink(String dcsimglink) {
		this.dcsimglink = dcsimglink;
	}

	public String getAccesslink() {
		return accesslink;
	}

	public void setAccesslink(String accesslink) {
		this.accesslink = accesslink;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CloudServer [serialno=" + serialno + ", plantype=" + plantype + ", domaintype=" + domaintype
				+ ", devicecloudservertype=" + devicecloudservertype + ", version=" + version + ", friendlyname="
				+ friendlyname + ", tenancytype=" + tenancytype + ", deploymenttype=" + deploymenttype + ", baseurl="
				+ baseurl + ", region=" + region + ", ipaddress=" + ipaddress + ", port=" + port + ", dcsimglink="
				+ dcsimglink + ", accesslink=" + accesslink + ", status=" + status + ", address=" + address + "]";
	}

}

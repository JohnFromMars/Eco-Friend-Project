package com.ecofriend.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "request_order")
public class RequestOrder {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;

	@ManyToOne(targetEntity = Provider.class)
	@JoinColumn(name = "provider_id", nullable = false)
	private Provider provider;

	@ManyToOne(targetEntity = Sender.class)
	@JoinColumn(name = "sender_id", nullable = true)
	private Sender sender;

	@ManyToOne(targetEntity = Depot.class)
	@JoinColumn(name = "depot_id", nullable = true)
	private Depot depot;

	@Column(name = "added")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date added;

	@Column(name = "weight")
	private Integer weight;

	@Column(name = "confrim")
	private boolean confirm = false;

	@Column(name = "valid_container_number")
	private Integer validContainerNo;

	@Column(name = "total_container_number")
	private Integer totalContainerNo;

	@Column(name = "total_incentive")
	private BigDecimal totalIncentive;

	private BigDecimal providerIncentive;

	private BigDecimal senderIncentive;

	private BigDecimal ecoIncentive;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Integer getValidContainerNo() {
		return validContainerNo;
	}

	public void setValidContainerNo(Integer validContainerNo) {
		this.validContainerNo = validContainerNo;
	}

	public Integer getTotalContainerNo() {
		return totalContainerNo;
	}

	public void setTotalContainerNo(Integer totalContainerNo) {
		this.totalContainerNo = totalContainerNo;
	}

	public BigDecimal getTotalIncentive() {
		return totalIncentive;
	}

	public void setTotalIncentive(BigDecimal totalIncentive) {
		this.totalIncentive = totalIncentive;
	}

	public BigDecimal getProviderIncentive() {
		return providerIncentive;
	}

	public void setProviderIncentive(BigDecimal providerIncentive) {
		this.providerIncentive = providerIncentive;
	}

	public BigDecimal getSenderIncentive() {
		return senderIncentive;
	}

	public void setSenderIncentive(BigDecimal senderIncentive) {
		this.senderIncentive = senderIncentive;
	}

	public BigDecimal getEcoIncentive() {
		return ecoIncentive;
	}

	public void setEcoIncentive(BigDecimal ecoIncentive) {
		this.ecoIncentive = ecoIncentive;
	}

	@Override
	public String toString() {
		return "RequestOrder [orderId=" + orderId + ", provider=" + provider + ", sender=" + sender + ", depot=" + depot
				+ ", added=" + added + ", weight=" + weight + ", confirm=" + confirm + ", validContainerNo="
				+ validContainerNo + ", totalContainerNo=" + totalContainerNo + ", totalIncentive=" + totalIncentive
				+ "]";
	}

}

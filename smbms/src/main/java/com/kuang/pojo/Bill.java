package com.kuang.pojo;



public class Bill {

  private long id;
  private String billCode;
  private String productName;
  private String productDesc;
  private String productUnit;
  private double productCount;
  private double totalPrice;
  private long isPayment;
  private long createdBy;
  private java.sql.Timestamp creationDate;
  private long modifyBy;
  private java.sql.Timestamp modifyDate;
  private long providerId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getBillCode() {
    return billCode;
  }

  public void setBillCode(String billCode) {
    this.billCode = billCode;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public String getProductDesc() {
    return productDesc;
  }

  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc;
  }


  public String getProductUnit() {
    return productUnit;
  }

  public void setProductUnit(String productUnit) {
    this.productUnit = productUnit;
  }


  public double getProductCount() {
    return productCount;
  }

  public void setProductCount(double productCount) {
    this.productCount = productCount;
  }


  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }


  public long getIsPayment() {
    return isPayment;
  }

  public void setIsPayment(long isPayment) {
    this.isPayment = isPayment;
  }


  public long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(long createdBy) {
    this.createdBy = createdBy;
  }


  public java.sql.Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(java.sql.Timestamp creationDate) {
    this.creationDate = creationDate;
  }


  public long getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(long modifyBy) {
    this.modifyBy = modifyBy;
  }


  public java.sql.Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(java.sql.Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }


  public long getProviderId() {
    return providerId;
  }

  public void setProviderId(long providerId) {
    this.providerId = providerId;
  }

}

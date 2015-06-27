package com.hmel.myway.central.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String phone;

  @Enumerated(EnumType.STRING)
  private PhoneType phoneType;

  private Date creationTime;

  private Date modifiedTime;

  public Phone() {}

  public Phone(String phone) {
    this(phone, PhoneType.HOME);
  }

  public Phone(String phone, PhoneType phoneType) {
    this.phone = phone;
    this.phoneType = phoneType;
  }

  public enum PhoneType {
    HOME, WORK
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public PhoneType getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(PhoneType phoneType) {
    this.phoneType = phoneType;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }

  public Date getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(Date modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

}

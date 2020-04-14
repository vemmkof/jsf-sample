package com.ipn.escom.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 1146189021488607412L;

  @Id
  @Positive
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idUser;

  @NotEmpty
  @Column(nullable = false)
  private String username;

  @NotEmpty
  @Column(nullable = false)
  private String password;

  @PastOrPresent
  @CreationTimestamp
  private Timestamp createdAt;

  @PastOrPresent
  @UpdateTimestamp
  private Timestamp updatedAt;

  public UserEntity() {}

  public UserEntity(String username, String password) {
    this.username = username;
    this.password = password;
    this.createdAt = new Timestamp(System.currentTimeMillis());
  }

  public UserEntity(Integer idUser, String username, String password, Timestamp createdAt,
      Timestamp updatedAt) {
    this.idUser = idUser;
    this.username = username;
    this.password = password;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Integer getIdUser() {
    return idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "UserEntity [idUser=" + idUser + ", username=" + username + ", password=" + password
        + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
  }



}

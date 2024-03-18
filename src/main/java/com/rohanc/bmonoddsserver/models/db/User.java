/* (C)2024 */
package com.rohanc.bmonoddsserver.models.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "users")
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
  @NonNull @Column(name = "username", nullable = false, unique = true)
  private String username;

  @NonNull @Column(name = "password", nullable = false)
  private String password;

  @NonNull @Column(name = "balance", nullable = false)
  private Float balance;
}

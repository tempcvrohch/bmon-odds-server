/* (C)2024 */
package com.rohanc.bmonoddsserver.models.response;

import lombok.Data;

@Data
public class Session {
  private String username;
  private Float balance;
  private Integer pendingBetsAmount;

  public Session(String username, Float balance, Integer pendingBetsAmount) {
    this.username = username;
    this.balance = balance;
    this.pendingBetsAmount = pendingBetsAmount;
  }
}

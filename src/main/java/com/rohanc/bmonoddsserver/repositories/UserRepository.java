/* (C)2024 */
package com.rohanc.bmonoddsserver.repositories;

import com.rohanc.bmonoddsserver.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  @Modifying
  @Query(
      value = "UPDATE users SET balance = balance - :amount WHERE id = :userId",
      nativeQuery = true)
  void reduceBalanceByUsername(@Param("userId") Long userId, @Param("amount") Float amount);

  @Modifying
  @Query(
      value = "UPDATE users SET balance = balance + :amount WHERE id = :userId",
      nativeQuery = true)
  void incrementBalanceByUsername(@Param("userId") Long userId, @Param("amount") Float amount);
}

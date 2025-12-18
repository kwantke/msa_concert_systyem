package com.kokk.user.domain.model.entity;


import com.kokk.user.domain.model.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User extends AuditingFields{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String userId;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(nullable = false, length = 100, unique = true)
  private String email;

  @PrePersist
  private void prePersistUser() {
    if (this.createdBy == null) {
      this.createdBy = this.userId;
    }
    if (this.modifiedBy == null) {
      this.modifiedBy = this.userId;
    }
  }

  public static User of(String userId, String name, String password, String email) {
    return User.builder()
            .userId(userId)
            .name(name)
            .password(password)
            .email(email)
            .build();
  }

}

package com.cavisson.Home_login_app_sb_.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // must match your DB table name
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;
}




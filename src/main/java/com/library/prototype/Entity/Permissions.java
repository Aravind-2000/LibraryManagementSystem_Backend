package com.library.prototype.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incId;

    @Column(name = "user_email", nullable = false, updatable = false)
    private String user;

    @Column(name = "service_name", nullable = false, updatable = true)
    private String serviceName;

    @Column(name = "method_name", nullable = false, updatable = true)
    private String methodName;
    
}

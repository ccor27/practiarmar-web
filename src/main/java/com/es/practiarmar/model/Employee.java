package com.es.practiarmar.model;

import com.cmeza.sdgenerator.annotation.SDGenerate;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@SDGenerate
@Entity(name = "employee")
public class Employee extends Person implements UserDetails {
    @Column(unique = true)
    private String codigo;
    private String username;
    private String contrasena;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "login_attempts")
    private int loginAttempts;
    @Column(name = "is_account_locked")
    private boolean isAccountLocked;
    @Column(name = "lock_until")
    private LocalDateTime lockUntil;
    @Enumerated(EnumType.STRING)
    private Role rol;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinTable(name="pago_empleado")
    private List<EmployeePayment> pagos;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.PERSIST)
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                () -> "ROLE_" + this.rol.name()
        );
    }

    @Override
    public String getPassword() {
        return contrasena;
    }
}

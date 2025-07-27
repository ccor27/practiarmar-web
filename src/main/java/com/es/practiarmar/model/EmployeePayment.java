package com.es.practiarmar.model;

import com.cmeza.sdgenerator.annotation.SDGenerate;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@SDGenerate
@Entity(name = "pago")
public class EmployeePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_proyecto")
    private String codigoProyecto;
    private double total;
    @Enumerated(EnumType.STRING)
    private EmployeePaymentStatus estado;
}

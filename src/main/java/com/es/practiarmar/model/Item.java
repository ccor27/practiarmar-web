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
@Entity(name = "item")
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    @Column(name = "precio_por_unidad")
    private double precioPorUnidad;
}

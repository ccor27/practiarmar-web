package com.es.practiarmar.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ItemQuote {
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precioPorUnidad;
    private boolean esVisible;
}

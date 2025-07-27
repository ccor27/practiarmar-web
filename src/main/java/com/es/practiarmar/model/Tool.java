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
@Entity(name = "tools")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private Category categoria;
    @OneToOne(cascade = CascadeType.ALL)
    private Picture foto;
}

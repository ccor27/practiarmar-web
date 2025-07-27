package com.es.practiarmar.model;

import com.cmeza.sdgenerator.annotation.SDGenerate;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@SDGenerate
@Entity(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne()
    private Customer cliente;
    @Column(unique = true,name = "codigo")
    private String codigo;
    @Column(name = "precio_materiales")
    private double precioMateriales;
    @Column(name = "precio_por_empleado")
    private double precioPorEmpleado;
    @Column(name = "precio_mano_de_obra")
    private double precioManoDeObra;
    @Column(name = "precio_total")
    private double precioTotal;
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;
    @Column(name = "fecha_modificacion")
    private LocalDate fehcaModificacion;
    private String descripcion;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_proyecto")
    private ProjectStatus estadoProyecto;
    @ManyToMany(fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "empleado_proyecto")
    private List<Employee> empleados;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinTable(name = "item_proyecto")
    private List<Item> items;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "foto_proyecto")
    private List<Picture> fotos;
}

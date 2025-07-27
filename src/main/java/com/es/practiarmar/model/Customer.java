package com.es.practiarmar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.cmeza.sdgenerator.annotation.SDGenerate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@SDGenerate
@Entity(name = "customer")
public class Customer extends Person{
    @Column(name = "recibir_notificaciones")
    boolean recibirNotificaciones;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}

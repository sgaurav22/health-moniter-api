package com.clipboard.health.domains;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"Facility\"")
public class Facility implements Serializable {

    static final long serialVersionUID = -575217266733247101L;
    @Id
    @GeneratedValue(generator = "facility_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "facility_gen", sequenceName = "Facility_id_seq", allocationSize = 1)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
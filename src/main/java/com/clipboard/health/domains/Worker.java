package com.clipboard.health.domains;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"Worker\"")
public class Worker implements Serializable {

    static final long serialVersionUID = -3189229986729799152L;

    @Id
    @GeneratedValue(generator = "worker_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "worker_gen", sequenceName = "Worker_id_seq", allocationSize = 1)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "profession", columnDefinition = "Profession not null")
    @Enumerated(value = EnumType.STRING)
    private Profession profession;
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

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    /*
    TODO [JPA Buddy] create field to map the 'profession' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "profession", columnDefinition = "Profession not null")
    private Object profession;
*/
}
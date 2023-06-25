package com.clipboard.health.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"FacilityRequirement\"")
public class FacilityRequirement implements Serializable {

    static final long serialVersionUID = 3483642580765499144L;
    @Id
    @GeneratedValue(generator = "facility_requirement_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "facility_requirement_gen", sequenceName = "FacilityRequirement_id_seq", allocationSize = 1)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "facility_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Facility facility;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "document_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Document document;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
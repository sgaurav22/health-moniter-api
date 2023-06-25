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
@Table(name = "\"DocumentWorker\"")
public class DocumentWorker implements Serializable {

    static final long serialVersionUID = -9004067785117578067L;
    @Id
    @GeneratedValue(generator = "doc_worker_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "doc_worker_gen", sequenceName = "DocumentWorker_id_seq", allocationSize = 1)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "worker_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Worker worker;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "document_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Document document;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
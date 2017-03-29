package org.landal.bookland.usrmgmt.model;

import javax.persistence.*;

@MappedSuperclass
public class AbstractEntity implements Identifiable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Version
    private Integer version;

    protected AbstractEntity() {
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("@").append(hashCode()).append("[id = ").append(getId())
                .append("]");

        return sb.toString();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}


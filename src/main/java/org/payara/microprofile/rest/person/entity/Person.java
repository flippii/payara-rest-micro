package org.payara.microprofile.rest.person.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Schema(name="Person", description="POJO that represents a person.")
public class Person {

    @Id
    @GeneratedValue
    @Schema(required = true, example = "100")
    private Long id;

    @NotEmpty
    @Schema(required = true, example = "Hanz Mustermann")
    private String name;

    @NotEmpty
    @Schema(required = true, example = "Musterstrasse 12")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", address=" + address + '}';
    }

}
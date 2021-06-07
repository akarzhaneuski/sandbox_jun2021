package com.exadel.sandbox.team5.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee_discount")
public class EmployeeDiscountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id", nullable = false)
    private int id;



    @Column(name = "employeeId", nullable = false)
    private int employeeId;


    @OneToMany(mappedBy = "discountId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiscountEntity> discountId = new HashSet<DiscountEntity>();

    public void setDiscountId(Set<DiscountEntity> discountId) {
        this.discountId = discountId;
    }

    public Set<DiscountEntity> getDiscountId() {
        return discountId;
    }

    public void addDiscountId(DiscountEntity discount) {
        getDiscountId().add(discount);
    }

    public void removeDiscountId(DiscountEntity discount) {
        getDiscountId().remove(discount);
    }

}

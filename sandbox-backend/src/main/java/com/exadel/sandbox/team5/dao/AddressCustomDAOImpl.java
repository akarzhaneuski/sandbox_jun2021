package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Address;
import com.exadel.sandbox.team5.entity.City;
import com.exadel.sandbox.team5.entity.Country;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AddressCustomDAOImpl implements AddressCustomDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address saveOrUpdate(Address address) {
        if (address.getCity().getId() != null) {
            address.setCity(entityManager.getReference(City.class, address.getCity().getId()));
        }

        if (address.getCity().getCountry().getId() != null) {
            address.getCity().setCountry(entityManager.getReference(Country.class, address.getCity().getCountry().getId()));
        }

        if (address.getId() != null) {
            this.entityManager.persist(address);
            return address;
        } else {
            return this.entityManager.merge(address);
        }
    }
}


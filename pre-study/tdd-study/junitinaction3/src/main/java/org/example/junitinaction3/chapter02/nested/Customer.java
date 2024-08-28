package org.example.junitinaction3.chapter02.nested;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    private Gender gender;
    private String firstName;
    private String lastName;

    private String middleName;
    private Date becomeCustomer;

    @Builder
    public Customer(Gender gender,
                    String lastName,
                    String firstName,
                    String middleName,
                    Date becomeCustomer) {
        this.gender = gender;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.becomeCustomer = becomeCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return gender == customer.gender &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(middleName, customer.middleName) &&
                Objects.equals(becomeCustomer, customer.becomeCustomer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, firstName, lastName, middleName, becomeCustomer);
    }
}
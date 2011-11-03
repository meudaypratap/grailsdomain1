package com.poc.domainclass1

class Company {
    String name
    String address
    Date dateCreated
    Date lastUpdated

    static hasMany = [employees: Employee, projects: Project]

    static constraints = {
        name(nullable: true,blank: false)
    }

    String toString() {
        name
    }
}
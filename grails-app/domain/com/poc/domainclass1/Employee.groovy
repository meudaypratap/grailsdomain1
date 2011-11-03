package com.poc.domainclass1

class Employee {
    String firstName
    String lastName
    String email
    String code
    String password
    Float salary
    Date dob
    Date dateCreated
    Date lastUpdated
//    Set<Project>projects
    static transients = ['name']
    static belongsTo = [company: Company]
    static hasMany = [projects: Project]
    static constraints = {
        email(unique: true)
        password(size: 5..15, blank: false)
        salary(max: 1000000F)
    }

    String getName() {
        "${firstName ?: ''}${lastName ? ' ' + lastName : ''}"
    }

    String toString() {
        name
    }
}

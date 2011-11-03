package com.poc.domainclass1

class Project {
    String name
    String summary
    Company company
    Date dateCreated
    Date lastUpdated
    //    Set<Employee> employees

    static belongsTo = [Employee, Company]
    static hasMany = [employees: Employee, tasks: Task]
    static constraints = {
        summary(maxSize: 5000)
    }
}

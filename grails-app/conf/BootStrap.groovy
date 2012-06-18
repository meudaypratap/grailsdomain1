import com.poc.domainclass1.*

class BootStrap {

    def init = { servletContext ->
        ex1_Getter()
        ex2_ToString()
        ex3_Transient()
        ex4_Timestamp()
        ex5_Validation()
        ex6_OneToOne()
        ex7_OneToManyNoOwner()
        ex8_OneToManyOneOwner()
        ex9_ManyToMany()
    }

    void ex1_Getter() {
        Company company = new Company(name: "test", address: "test")
        company.save(flush: true)
        println "getter ${company.getName()} property ${company.name}"
    }

    void ex2_ToString() {
        Company company = new Company(name: "test", address: "test")
        company.save(flush: true)
        println "toString of company ${company}"
    }

    void ex3_Transient() {
        Company company = new Company(name: "test", address: "test")
        company.save(flush: true)
        Employee employee = new Employee(company: company, code: "123", firstName: "firstName", lastName: 'lastName', email: "test@gmail.com", password: "Test-5", dob: new Date() + 1, salary: 1000000F)
        employee.save()
        println "transients ${employee.name}"
    }

    void ex4_Timestamp() {
        Company company = new Company(name: "test", address: "test")
        company.save(flush: true)
        Employee employee = new Employee(company: company, code: "123", firstName: "firstName", lastName: 'lastName', email: "test@gmail.com", password: "Test-5", dob: new Date() + 1, salary: 1000000F)
        println "dateCreated before save -: ${employee.dateCreated}"
        println "lastUpdated before save -: ${employee.lastUpdated}"

        employee.save()

        println "dateCreated after save -: ${company.dateCreated}"
        println "lastUpdated after save -: ${company.lastUpdated}"

        Thread.sleep(10000)

        company.name = "Intelligrape"
        company.save(flush: true)

        println "dateCreated after update -: ${company.dateCreated}"
        println "lastUpdated after update -: ${company.lastUpdated}"
    }

    void ex5_Validation() {
        Company company = new Company(name: "test", address: "test")
        company.save(flush: true)
        Employee employee = new Employee(company: company, code: "123", firstName: "firstName", lastName: 'lastName', email: "test@gmail.com", password: "Test", dob: new Date() + 1, salary: 2000000F)
        println "Validate employee ${employee.save()}"

        employee.errors.allErrors.each {
            println it
        }
        //For more validation examples go through the classes
    }

    void ex6_OneToOne() {
        Car car = new Car()
        car.save()
        Engine engine = new Engine(car: car)
        engine.save()

        println "Engine of Car -: ${car.engine}"
        println "Car of Engine -: ${engine.car}"

    }

    void ex7_OneToManyNoOwner() {
        Company company = new Company(name: "test", address: "test")
        company.save(flush: true)

        Employee employee = new Employee(company: company, code: "123", firstName: "firstName", lastName: 'lastName', email: "test-1@gmail.com", password: "Test-5",
                dob: new Date() + 1, salary: 1000000F)
        employee.save()

        println "Project count before save  ${Project.count()}"
        Project project = new Project(company: company, name: "Project", summary: "Summary")
        project.save()

        Task task = new Task(assignedBy: employee, assignedTo: employee, name: "Test")
        task.save()
        println "Task count after save  ${Task.count()}"

        println "Project tasks before adding task -: ${project.tasks}"
        project.addToTasks(task)
        project.save()
        println "Project tasks after save -: ${project.tasks}"
    }

    void ex8_OneToManyOneOwner() {
        Company company = new Company(name: "test", address: "test")
        company.save(flush: true)

        Employee employee = new Employee(company: company, code: "123", firstName: "firstName", lastName: 'lastName', email: "test-2@gmail.com", password: "Test-5",
                dob: new Date() + 1, salary: 1000000F)
        employee.save()

        println "Before Employeed add ${company.employees}"
        company.addToEmployees(employee)
        println "After Employeed add ${company.employees}"

    }

    void ex9_ManyToMany() {
        Company company = new Company(name: "test", address: "test")
        company.save(flush: true)

        Employee employee = new Employee(company: company, code: "123", firstName: "firstName", lastName: 'lastName', email: "test-3@gmail.com", password: "Test-5",
                dob: new Date() + 1, salary: 1000000F)
        employee.save()

        Project project = new Project(company: company, name: "Project", summary: "Summary")
        project.save()

        println "Before adding project to employee ${employee.projects}"
        println "Before adding project to employee ${project.employees}"
        employee.addToProjects(project)
        employee.save()
        println "After adding project to employee ${employee.projects}"
        println "After adding project to employee ${project.employees}"
    }

    def destroy = {
    }
}

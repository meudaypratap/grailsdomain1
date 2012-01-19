import com.poc.domainclass1.*

class BootStrap {

    def init = { servletContext ->
        // Ex.1 Getters and Setters
        Company company = new Company(name: "test", address: "address")
        company.save(flush: true)
        println "getter ${company.getName()}"

        //Ex.2 Tostring
        println "toString of company ${company}"

        //Ex.3 dateCreated lastUpdated special fields

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

        //Ex.4 transient field
        println "transients ${employee.name}"

        //Ex.5 Validation Employee domain for more constraints
        //        Employee employee = new Employee(company: company, code: "123", firstName: "firstName", lastName: 'lastName', email: "test@gmail.com", password: "Test-5", dob: new Date() + 1, salary: 2000000F)

        println "Validate employee ${employee.validate()}"

        employee.errors.allErrors.each {
            println it
        }

        //Ex.6 One to One Relationship
        Car car = new Car()
        car.save()
        Engine engine = new Engine(car: car)
        engine.save()

        println "Engine of Car -: ${car.engine}"
        println "Car of Engine -: ${engine.car}"

        //Ex.7 One to Many with No owner Project hasMany Tasks

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

        //Ex.8 One to Many with one owner Company hasMany Employees
        println "Before Employeed add ${company.employees}"
        company.addToEmployees(employee)
        println "After Employeed add ${company.employees}"

        //Ex.9 Many to Many one is owner Project hasMany Employees and Employees hasMany Project
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

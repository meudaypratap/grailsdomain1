import com.poc.domainclass1.*

class BootStrap {

    def init = { servletContext ->
        Company company = new Company(name: "test", address: "address")
        company.save(flush: true)
        Employee employee = new Employee(company: company, code: "123", firstName: "firstName", lastName: 'lastName', email: "test@gmail.com", password: "Test-5", dob: new Date() + 1, salary: 1000000F)
        employee.errors.allErrors.each {
            println it
        }
        println "Validate employee ${employee.validate()}"
        employee.save()
        println "dateCreated before save -: ${employee.dateCreated}"
        println "lastUpdated before save -: ${employee.lastUpdated}"

        println "toString ${employee}"
        println "getter ${employee.getFirstName()}"

        println "dateCreated after save -: ${company.dateCreated}"
        println "lastUpdated after save -: ${company.lastUpdated}"
        Thread.sleep(10000)
        company.name = "Intelligrape"
        company.save(flush: true)

        println "dateCreated after update -: ${company.dateCreated}"
        println "lastUpdated after update -: ${company.lastUpdated}"

        println "Employee count -: ${Employee.count()}"
        println "Company count -: ${Company.count()}"

        Car car = new Car()
        car.save()
        Engine engine = new Engine(car: car)
        engine.save()

        println "Engine of Car -: ${car.engine}"
        println "Car of Engine -: ${engine.car}"

        println "Project count before save  ${Project.count()}"
        Project project = new Project(company: company, name: "Project", summary: "Summary")
        project.save()

        println "Project count after save  ${Project.count()}"

        println "Before Employeed add ${company.employees}"
        company.addToEmployees(employee)
        println "After Employeed add ${company.employees}"

        println "Task count before save  ${Task.count()}"
        Task task = new Task(assignedBy: employee, assignedTo: employee, name: "Test")
        task.save()
        println "Task count after save  ${Task.count()}"
        println "Project tasks before save -: ${project.tasks}"
        project.addToTasks(task)
        project.save()
        println "Project tasks after save -: ${project.tasks}"

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

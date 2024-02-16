import java.util.Scanner

data class Employee(
    val name: String,
    val id: String,
    val sex: String,
    val email: String,
    var salary: Double,
    val department: String,
    val yearOfJoining: Int,
    val position: Position,
    val subordinates: MutableList<Employee> = mutableListOf()
)

data class Position(
    val name: String,
    val hierarchyLevel: Int
)

data class Client(
    val name: String,
    val id: String,
    val sex: String,
    val email: String,
    var address: String,
    val phone: String
)

class Company(
    val name: String,
    val nit: String,
    val address: String,
    val employees: MutableList<Employee>,
    val clients: MutableList<Client>
) {
    fun calculateCompanyPayroll(): Double {
        return employees.sumByDouble { it.salary }
    }

    fun calculatePayrollByDepartment(department: String): Double {
        return employees.filter { it.department == department }.sumByDouble { it.salary }
    }

    fun percentageOfClientsBySex(sex: String): Double {
        val totalClients = clients.size.toDouble()
        val clientsBySex = clients.count { it.sex.equals(sex, ignoreCase = true) }.toDouble()
        return (clientsBySex / totalClients) * 100
    }

    fun numberOfEmployeesByPosition(positionName: String): Int {
        return employees.count { it.position.name.equals(positionName, ignoreCase = true) }
    }

    fun employeeWithMostSeniority(): Employee? {
        return employees.maxByOrNull { it.yearOfJoining }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val company = Company("My Company", "123456789", "Company Address", mutableListOf(), mutableListOf())

    var option: Int
    do {
        println("Select an option:")
        println("1. CRUD Employees")
        println("2. CRUD Clients")
        println("3. Calculate company payroll")
        println("4. Calculate payroll by department")
        println("5. Percentage of clients by sex")
        println("6. Number of employees by position")
        println("7. Employee with most seniority")
        println("8. Exit")
        option = scanner.nextInt()

        when (option) {
            1 -> crudEmployees(company)
            2 -> crudClients(company)
            3 -> println("Company payroll: ${company.calculateCompanyPayroll()}")
            4 -> {
                println("Enter the department to calculate the payroll:")
                val department = readLine()!!
                println("Payroll for department $department: ${company.calculatePayrollByDepartment(department)}")
            }
            5 -> {
                println("Enter the sex to calculate the percentage of clients:")
                val sex = readLine()!!
                println("Percentage of clients ($sex): ${company.percentageOfClientsBySex(sex)}%")
            }
            6 -> {
                println("Enter the position name to calculate the number of employees:")
                val positionName = readLine()!!
                println("Number of employees with position $positionName: ${company.numberOfEmployeesByPosition(positionName)}")
            }
            7 -> {
                val mostSeniorEmployee = company.employeeWithMostSeniority()
                if (mostSeniorEmployee != null) {
                    println("The employee with most seniority is ${mostSeniorEmployee.name} from the department ${mostSeniorEmployee.department}")
                } else {
                    println("No employees registered.")
                }
            }
            8 -> println("Exiting...")
            else -> println("Invalid option")
        }
    } while (option != 8)
}

fun crudEmployees(company: Company) {
    val scanner = Scanner(System.`in`)
    var option: Int
    do {
        println("\nSelect an option:")
        println("1. Create employee")
        println("2. Read employees")
        println("3. Update employee")
        println("4. Delete employee")
        println("5. Back to main menu")
        option = scanner.nextInt()

        when (option) {
            1 -> {
                println("Enter the name of the employee:")
                val name = readLine()!!
                println("Enter the ID of the employee:")
                val id = readLine()!!
                println("Enter the sex of the employee:")
                val sex = readLine()!!
                println("Enter the email of the employee:")
                val email = readLine()!!
                println("Enter the salary of the employee:")
                val salary = scanner.nextDouble()
                println("Enter the department of the employee:")
                val department = readLine()!!
                println("Enter the year of joining of the employee:")
                val yearOfJoining = scanner.nextInt()
                println("Enter the name of the position of the employee:")
                val positionName = readLine()!!
                println("Enter the hierarchy level of the position of the employee:")
                val hierarchyLevel = scanner.nextInt()

                val position = Position(positionName, hierarchyLevel)
                val employee = Employee(name, id, sex, email, salary, department, yearOfJoining, position)
                company.employees.add(employee)
                println("Employee created successfully.")
            }
            2 -> {
                println("\nList of employees:")
                company.employees.forEachIndexed { index, employee ->
                    println("Employee ${index + 1}: ${employee.name}")
                }
            }
            3 -> {
                println("\nEnter the index of the employee to update:")
                val index = scanner.nextInt() - 1
                if (index >= 0 && index < company.employees.size) {
                    val employee = company.employees[index]
                    println("Enter the new salary of the employee:")
                    val newSalary = scanner.nextDouble()
                    employee.salary = newSalary
                    println("Employee updated successfully.")
                } else {
                    println("Invalid index.")
                }
            }
            4 -> {
                println("\nEnter the index of the employee to delete:")
                val index = scanner.nextInt() - 1
                if (index >= 0 && index < company.employees.size) {
                    company.employees.removeAt(index)
                    println("Employee deleted successfully.")
                } else {
                    println("Invalid index.")
                }
            }
            5 -> println("Returning to main menu...")
            else -> println("Invalid option")
        }
    } while (option != 5)
}

fun crudClients(company: Company) {
    val scanner = Scanner(System.`in`)
    var option: Int
    do {
        println("\nSelect an option:")
        println("1. Create client")
        println("2. Read clients")
        println("3. Update client")
        println("4. Delete client")
        println("5. Back to main menu")
        option = scanner.nextInt()

        when (option) {
            1 -> {
                println("Enter the name of the client:")
                val name = readLine()!!
                println("Enter the ID of the client:")
                val id = readLine()!!
                println("Enter the sex of the client:")
                val sex = readLine()!!
                println("Enter the email of the client:")
                val email = readLine()!!
                println("Enter the address of the client:")
                val address = readLine()!!
                println("Enter the phone number of the client:")
                val phone = readLine()!!

                val client = Client(name, id, sex, email, address, phone)
                company.clients.add(client)
                println("Client created successfully.")
            }
            2 -> {
                println("\nList of clients:")
                company.clients.forEachIndexed { index, client ->
                    println("Client ${index + 1}: ${client.name}")
                }
            }
            3 -> {
                println("\nEnter the index of the client to update:")
                val index = scanner.nextInt() - 1
                if (index >= 0 && index < company.clients.size) {
                    val client = company.clients[index]
                    println("Enter the new address of the client:")
                    val newAddress = readLine()!!
                    client.address = newAddress
                    println("Client updated successfully.")
                } else {
                    println("Invalid index.")
                }
            }
            4 -> {
                println("\nEnter the index of the client to delete:")
                val index = scanner.nextInt() - 1
                if (index >= 0 && index < company.clients.size) {
                    company.clients.removeAt(index)
                    println("Client deleted successfully.")
                } else {
                    println("Invalid index.")
                }
            }
            5 -> println("Returning to main menu...")
            else -> println("Invalid option")
        }
    } while (option != 5)
}

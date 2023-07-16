import { Component } from '@angular/core';
import { Employee, ExtendedEmployee } from './employee';
import { EmployeeService } from './employee.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  id:number;
  employee:Employee;
  selectedEmployee:Employee | null = null;
  showUpdateForm: boolean = false;
  
  newEmployee: ExtendedEmployee = {
    id: null,
    title:'',
    firstName: '',
    secondName: '',
    age:'',
    email: '',
    username:'',
    picture:'',
    password:'',
    town:'',
    country:'',
    address:'',
    phoneNumber:''
  }
  
  employees: Employee[];

  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  generateEmployee(): void {
    this.employeeService.generateEmployee().subscribe(
      (employee: Employee) => {
        // Handle the generated employee response here
        console.log(employee);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  searchEmployee() {
    this.employeeService.findEmployees(this.id)
    .subscribe(data => {
      this.employee = data;
      this.showUpdateForm = false;
    })
  }

  addEmployee() {
    this.employeeService.addEmployees(this.newEmployee)
    .subscribe( (response: Employee) => {
      console.log(response);
      this.getEmployees(); // to refresh the list of employees
      this.newEmployee = { // reset the form
        id: null,
        title:'',
        firstName: '',
        secondName: '',
        age:'',
        email: '',
        username:'',
        picture:'',
        password:'',
        town:'',
        country:'',
        address:'',
        phoneNumber:''
      };
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }

  removeEmployee(employee: Employee):void {
    this.employeeService.deleteEmployees(employee.id)
    .subscribe(
      () => {
        console.log("Employee deleted successfully");
        // Refresh the list of employees
        this.getEmployees();
      },
      (error: any) => {
        console.error("Error deleting employee:", error);
      }
    );
  }

  updateEmployee(): void {
    if (this.employee) {
      this.employeeService.updateEmployees(this.employee)
        .subscribe(
          () => {
            console.log("Employee updated successfully");
            this.showUpdateForm = false; // Hide the update form
          },
          (error: any) => {
            console.error("Error updating employee:", error);
          }
        );
    }
  }

  modifyEmployee(employee: Employee):void {
    this.selectedEmployee = { ...employee }; //we make a shallow copy of the employee object using the spread operator ({ ...employee })

  }

}

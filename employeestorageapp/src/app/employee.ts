export interface Employee {
  id:number;
  title:string;
  firstName: string;
  secondName: string;
  age:string;
  email: string;
  username:string;
  picture:string;
  phoneNumber: string;
  address: string;
  country: string;
  town: string;
}

export interface ExtendedEmployee extends Employee {
  password: string;
}

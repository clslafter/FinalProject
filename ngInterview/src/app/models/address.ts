import { ComponentFactory } from "@angular/core";
import { Company } from "./company";
import { JobOpening } from "./job-opening";

export class Address {
  id: number;
  street: string;
  street2: string;
  city: string;
  state: string;
  zip: string;
  enabled: boolean;
  company?: Company;
  job?: JobOpening;
  constructor(
    id: number = 0, street: string = '', street2: string = '',
    city: string = '', state: string = '', zip: string = '',
    enabled: boolean = true, company?: Company, job?: JobOpening,
  ){
    this.id = id;
    this.street = street;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.enabled = enabled;
    this.company = company;
    this.job = job;
  }
}

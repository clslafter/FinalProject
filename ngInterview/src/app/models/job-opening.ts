import { Address } from "./address";
import { Company } from "./company";
import { User } from "./user";

export class JobOpening {
  id: number;
  description: string;
  urlPost: string;
  enabled: boolean;
  roleFilled: boolean;
  posted: string;
  user?: User;
  company?: Company;
  address?: Address;

  constructor(
    id: number = 0, description: string = '', urlPost: string = '',
    enabled: boolean = true, roleFilled: boolean = true, posted: string = '',
    user?: User, company?: Company, address?: Address
  ){
    this.id = id;
    this.description = description;
    this.urlPost = urlPost;
    this.enabled = enabled;
    this.roleFilled = roleFilled;
    this.posted = posted;
    this.user = user;
    this.company = company;
    this.address = address;
  }
}

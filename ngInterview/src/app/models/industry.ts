import { Company } from "./company";

export class Industry {
  id: number;
  name: string;
  enabled: boolean;
  companies?: Company[];

  constructor(
    id: number = 0, name: string = '', enabled: boolean = true,
    companies?: Company[]
  ){
    this.id = id;
    this.name = name;
    this.enabled = enabled;
    this.companies = companies;
  }
}

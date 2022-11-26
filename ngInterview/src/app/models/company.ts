import { Address } from "./address";
import { Industry } from "./industry";
import { JobOpening } from "./job-opening";
import { Question } from "./question";

export class Company {
  id: number;
  name: string;
  description: string;
  logoURL: string;
  address?: Address;
  enabled: boolean;
  questions?: Question[];
  industries?: Industry[];
  jobs?: JobOpening[];

  constructor(
    id: number = 0, name: string = '', description: string = '',
    logoURL: string = '', address?: Address, enabled: boolean = true,
    questions?: Question[], industries?: Industry[], jobs?: JobOpening[]
  ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.logoURL = logoURL;
    this.address = address;
    this.enabled = enabled;
    this.questions = questions;
    this.industries = industries;
    this.jobs = jobs;
  }

}

import { Answer } from "./answer";
import { Category } from "./category";
import { Company } from "./company";
import { User } from "./user";

export class Question {
  id: number;
  dateCreated: string;
  dateUpdated: string;
  question: string;
  enabled: boolean;
  user?: User;
  answers?: Answer[];
  categories?: Category[];
  companies?: Company[];

  constructor(
    id: number = 0, dateCreated: string = '', dateUpdated: string = '',
    question: string = '', enabled: boolean = true, user?: User,
    answers?: Answer[], categories?: Category[], companies?: Company[]
  ){
    this.id = id;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
    this.question = question;
    this.enabled = enabled;
    this.user = user;
    this.answers = answers;
    this.categories = categories;
    this.companies = companies;
  }
}

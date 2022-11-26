import { Question } from "./question";

export class Category {
  id: number;
  name: string;
  description: string;
  enabled: boolean;
  questions?: Question[];

  constructor(
    id: number = 0, name: string = '', description: string = '',
    enabled: boolean = true, questions?: Question[]
  ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.enabled = enabled;
    this.questions = questions;
  }
}

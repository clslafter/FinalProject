
import { AnswerRating } from "./answer-rating";
import { Question } from "./question";
import { User } from "./user";

export class Answer {
  id: number;
  dateCreated: string;
  dateUpdated: string;
  enabled: boolean;
  answer: string;
  user?: User;
  question?: Question;
  ratings?: AnswerRating[];

  constructor(
    id: number = 0, dateCreated: string = '', dateUpdated: string = '',
    enabled: boolean = true, answer: string = '', user?: User,
    question?: Question, ratings?: AnswerRating[]
    ){
      this.id = id;
      this.dateCreated = dateCreated;
      this.dateUpdated = dateUpdated;
      this.enabled = enabled;
      this.answer = answer;
      this.user = user;
      this.question = question;
      this.ratings = ratings;
    }
}

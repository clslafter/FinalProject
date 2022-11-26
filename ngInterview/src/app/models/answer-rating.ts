import { Answer } from "./answer";
import { AnswerRatingId } from "./answer-rating-id";
import { User } from "./user";

export class AnswerRating {
  id?: AnswerRatingId;
  upvote: boolean;
  ratingDate: string;
  answer?: Answer;
  user?: User;

  constructor(
    id?: AnswerRatingId,
    upvote: boolean = true, ratingDate: string = '',
    answer?: Answer, user?: User
  ){
    this.id = id;
    this.upvote = upvote;
    this.ratingDate = ratingDate;
    this.answer = answer;
    this.user = user;
  }
}

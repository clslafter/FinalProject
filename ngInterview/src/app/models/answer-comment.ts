import { User } from "./user";

export class AnswerComment {
  id: number;
  commentDate: string;
  commentText: string;
  user?: User;

  constructor(
    id: number = 0, commentDate: string = '', commentText: string = '', user?: User
  ){
    this.id = id;
    this.commentDate = commentDate;
    this.commentText = commentText;
    this.user = user;
  }
}

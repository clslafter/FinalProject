import { User } from "./user";

export class AnswerComment {
  id: number;
  commentDate: string;
  commentText: string;
  user?: User;
  enabled: boolean;

  constructor(
    id: number = 0, commentDate: string = '', commentText: string = '', enabled: boolean = true, user?: User
  ){
    this.id = id;
    this.commentDate = commentDate;
    this.commentText = commentText;
    this.user = user;
    this.enabled = enabled;
  }
}

export class AnswerComment {
  id: number;
  commentDate: string;
  commentText: string;

  constructor(
    id: number = 0, commentDate: string = '', commentText: string = ''
  ){
    this.id = id;
    this.commentDate = commentDate;
    this.commentText = commentText;
  }
}

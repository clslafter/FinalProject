export class AnswerRatingId {
  answerId: number;
  userId: number;

  constructor(
    answerId: number = 0, userId: number = 0
  ){
    this.answerId = answerId;
    this.userId = userId;
  }
}

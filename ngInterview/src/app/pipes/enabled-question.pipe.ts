import { Pipe, PipeTransform } from '@angular/core';
import { Question } from '../models/question';

@Pipe({
  name: 'enabledQuestion',
})
export class EnabledQuestionPipe implements PipeTransform {
  transform(questions: Question[]): Question[] {
    let results: Question[] = [];

    questions.forEach((question) => {
      if (question.enabled) {
        results.push(question);
      }
    });

    return results;
  }
}

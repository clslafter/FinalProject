import { Pipe, PipeTransform } from '@angular/core';
import { Category } from '../models/category';
import { Question } from '../models/question';

@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {



  transform(questions: Question [], category: String): Question[] {

        let results: Question[] = [];
          if(category.toLowerCase() === 'all') {
            return questions;
          }
          questions.forEach((question) => {
            console.log(question);
            question.categories?.forEach((cat: Category) => {
              if(cat.name === category) {
                results.push(question)
              }
            })
          });

          console.log(results);

        return results;
      }

}


import { Pipe, PipeTransform } from '@angular/core';
import { Company } from '../models/company';

@Pipe({
  name: 'enabledCompany'
})
export class EnabledCompanyPipe implements PipeTransform {

  transform(companies: Company[]): Company[] {
    let results: Company[] = [];

    companies.forEach((company) => {
      if (company.enabled) {
        results.push(company);
      }
    });

    return results;
  }

}

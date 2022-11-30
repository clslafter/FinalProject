import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  companies: Company [] = [];

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
    this.loadCompanies();
  }

  loadCompanies(){
    this.companyService.index().subscribe({
      next: (data: any) => {
        this.companies = data;
      },
      error: (fail: any) => {
        console.error('QuestionComponent.loadQuestions: error getting questions');
        console.error(fail);
      }
    })
  }

}

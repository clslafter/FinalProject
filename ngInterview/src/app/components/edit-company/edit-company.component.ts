import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { Industry } from 'src/app/models/industry';
import { CompanyService } from 'src/app/services/company.service';
import { IndustryService } from 'src/app/services/industry.service';

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css']
})
export class EditCompanyComponent implements OnInit {

  editCompany: Company | null = null;
  industries: Industry[] = [];

  selectedIndustries: boolean[] = [];

  constructor(private companyService: CompanyService, private route: ActivatedRoute, private router: Router, private industryService: IndustryService) { }

  ngOnInit(): void {
    this.loadCompany();
    this.loadIndustries();
  }

  clearEditCompany(): void {
    this.editCompany = null;
  }

  backToCompanyDetail () {
    this.router.navigateByUrl(`/companyDetail/${this.editCompany?.id}`);
    this.clearEditCompany();


  }


  loadCompanyIndustries() {
    if (this.editCompany?.industries) {
    for (let i = 0; i < this.editCompany.industries.length; i++) {
      for (let j = 0; j < this.industries.length; j++) {
        if(this.editCompany.industries[i].name === this.industries[j].name) {
          this.selectedIndustries[j] = true;
        }

      }


    }

  }
  }



  loadCompany(){
    let routeId = this.route.snapshot.paramMap.get('id');
    console.log(routeId);
    if (!this.editCompany && routeId) {
      let companyId = Number.parseInt(routeId);
      if(isNaN(companyId)) {
        this.router.navigateByUrl('invalidId');
      } else {
        this.companyService.show(companyId).subscribe({
          next: (data) => {
            this.editCompany = data;

          },
          error: (fail) => {
            console.error('EditCompanyComponent.ngOnInit: company not found');
            this.router.navigateByUrl('companyNotFound'); //doesn't exist
          }
        });
      }
    }
  }

  loadIndustries () {
    this.industryService.index().subscribe({
      next: (data) => {
        this.industries = data;
        this.industries.forEach(industry => {
          this.selectedIndustries.push(false);
        });
        this.loadCompanyIndustries();
        },
        error: (fail) => {
          console.error('EditCompanyComponent.loadIndustries: error getting industries');
          console.error(fail);
        }
      })
    }


  updateCompany(): void {
    if (this.editCompany) {
    this.editCompany.industries = [];
    for (let i = 0; i < this.selectedIndustries.length; i++) {

      if(this.selectedIndustries[i]) {
        this.editCompany?.industries?.push(this.industries[i]);
      }

    }
    console.log(this.editCompany);
      this.editCompany.enabled = true;

    this.companyService.update(this.editCompany).subscribe({
      next: (data: any) => {
        this.editCompany = data;
        this.router.navigateByUrl(`/companyDetail/${this.editCompany?.id}`);
        this.clearEditCompany();

      },
      error: (fail: any) => {
        console.error(
          'EditCompanyComponent.updateCompany(): error updating company:'
          );
          console.error(fail);
        },
      });
    }
    }


}



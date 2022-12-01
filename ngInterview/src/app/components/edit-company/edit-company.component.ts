import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css']
})
export class EditCompanyComponent implements OnInit {

  editCompany: Company | null = null;

  constructor(private companyService: CompanyService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.loadCompany();
  }

  clearEditCompany(): void {
    this.editCompany = null;
  }

  backToCompanyDetail () {
    this.router.navigateByUrl(`/companyDetail/${this.editCompany?.id}`);
    this.clearEditCompany();


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


  updateCompany(): void {
    if (this.editCompany) {
    // this.editCompany.categories = [];
    // for (let i = 0; i < this.selectedCategories.length; i++) {

    //   if(this.selectedCategories[i]) {
    //     this.editQuestion?.categories?.push(this.categories[i]);
    //   }

    // }
    // console.log(this.editQuestion);
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


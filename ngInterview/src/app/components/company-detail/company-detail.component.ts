import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  styleUrls: ['./company-detail.component.css']
})
export class CompanyDetailComponent implements OnInit {

  selected: Company | null = null;
  user: User = new User();

  constructor(private companyService: CompanyService,private route: ActivatedRoute, private router: Router, private auth: AuthService) { }

  ngOnInit(): void {
    this.loadPage();
    this.loadUser();
  }

  loadPage(){
    let routeId = this.route.snapshot.paramMap.get('id');
    console.log(routeId);
    if (!this.selected && routeId) {
      let companyId = Number.parseInt(routeId);
      if(isNaN(companyId)) {
        this.router.navigateByUrl('invalidId');
      } else {
        this.companyService.show(companyId).subscribe({
          next: (data: any) => {
            this.selected = data;
          },
          error: (fail: any) => {
            console.error('CompanyDetailComponent.ngOnInit: company not found');
            this.router.navigateByUrl('companyNotFound'); //doesn't exist
          }
        });
      }
    }
    }

    loadUser() {


      this.auth.getLoggedInUser().subscribe({
        next: (data) => {
          this.user = data;

        },
        error: (fail) => {
          console.error('CompanyDetailComponent.reload: error getting user');
          console.error(fail);
        },
      });

      // }
    }

    deleteCompany(id: number){
      if(confirm("Are you sure you want to delete your question?")){
      this.companyService.destroy(id).subscribe({
        next: (data: any) => {
          this.selected = null;
          this.router.navigateByUrl('companies')
          },
        error: (fail: any) => {
          console.error(
            'CompanyDetailComponent.disableCompany(): error disabling company:'
          );
          console.error(fail);
        },
      });
    }
    }

}




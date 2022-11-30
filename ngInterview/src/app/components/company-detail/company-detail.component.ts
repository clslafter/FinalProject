import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  styleUrls: ['./company-detail.component.css']
})
export class CompanyDetailComponent implements OnInit {

  selected: Company | null = null;

  constructor(private companyService: CompanyService,private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.loadPage();
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

}

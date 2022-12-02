import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Company } from 'src/app/models/company';
import { Industry } from 'src/app/models/industry';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import { IndustryService } from 'src/app/services/industry.service';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {

  newCompany: Company = new Company();

  addAddress = new Address();
newAddress: Address | null = null

industries: Industry[] = [];

   selectedIndustries: boolean[] = [];

   errorMessage = '';


   @Output() reloadParent = new EventEmitter<void>();

  constructor(private companyService: CompanyService, private router: Router,
    private auth: AuthService, private addressService: AddressService, private industryService: IndustryService) { }

  ngOnInit(): void {
    this.loadIndustries();
  }

  loadIndustries () {
    this.industryService.index().subscribe({
      next: (data) => {
        this.industries = data;
        this.industries.forEach(industry => {
          this.selectedIndustries.push(false);
        });
      },
      error: (fail) => {
        console.error('AddCompanyComponent.loadIndustries: error getting industries');
        console.error(fail);
      }
    })
  }

  //add error messages for required fields
  createCompany() {
    this.errorMessage = '';

    if(!this.newCompany.name) {
      this.errorMessage += '*Please enter the company name* ';
    }

    if(this.errorMessage) {
      return;
    }

    this.newCompany.industries = [];
    for (let i = 0; i < this.selectedIndustries.length; i++) {

      if(this.selectedIndustries[i]) {
        this.newCompany.industries?.push(this.industries[i]);
      }

    }
    console.log(this.newCompany);

    this.addressService.create(this.addAddress).subscribe({
      next: (addAddressData) => {

        this.newAddress = addAddressData;
        let aid = addAddressData.id;

        this.newCompany.address = addAddressData;
        this.newCompany.enabled = true;

        this.companyService.create(this.newCompany).subscribe({
          next: (newCompanyData) => {

            this.newCompany = new Company();
            this.reloadParent.emit();
            this.errorMessage = '';

          },
          error: (fail) => {
            console.error('AddCompanyComponent.reload: error creating company');
            console.error(fail);
          }

        });



      },
      error: (fail) => {
        console.error('AddCompanyComponent.reload: error creating address');
        console.error(fail);
      }
    });



  }


}




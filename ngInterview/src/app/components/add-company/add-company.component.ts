import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Company } from 'src/app/models/company';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {

  newCompany: Company = new Company();

  addAddress = new Address();
newAddress: Address | null = null

  //  categories: Category[] = [];

  // selectedCategories: boolean[] = [];

   @Output() reloadParent = new EventEmitter<void>();

  constructor(private companyService: CompanyService, private router: Router,
    private auth: AuthService, private addressService: AddressService) { }

  ngOnInit(): void {
  }

  //add error messages for required fields
  createCompany() {
    // this.newQuestion.categories = [];
    // for (let i = 0; i < this.selectedCategories.length; i++) {

    //   if(this.selectedCategories[i]) {
    //     this.newQuestion.categories?.push(this.categories[i]);
    //   }

    // }
    // console.log(this.newQuestion);

    this.addressService.create(this.addAddress).subscribe({
      next: (addAddressData) => {

        this.newAddress = addAddressData;
        let aid = addAddressData.id;

        this.newCompany.address = addAddressData;
        this.newCompany.enabled = true;

        this.companyService.create(this.newCompany).subscribe({
          next: (newCompnanyData) => {

            this.newCompany = new Company();
            this.reloadParent.emit();

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


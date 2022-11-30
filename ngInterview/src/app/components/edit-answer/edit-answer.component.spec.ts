import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAnswerComponent } from './edit-answer.component';

describe('EditAnswerComponent', () => {
  let component: EditAnswerComponent;
  let fixture: ComponentFixture<EditAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditAnswerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

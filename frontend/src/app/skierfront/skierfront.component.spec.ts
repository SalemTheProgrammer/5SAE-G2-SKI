import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkierfrontComponent } from './skierfront.component';

describe('SkierfrontComponent', () => {
  let component: SkierfrontComponent;
  let fixture: ComponentFixture<SkierfrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SkierfrontComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SkierfrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

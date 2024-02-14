import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarfacComponent } from './mostrarfac.component';

describe('MostrarfacComponent', () => {
  let component: MostrarfacComponent;
  let fixture: ComponentFixture<MostrarfacComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarfacComponent]
    });
    fixture = TestBed.createComponent(MostrarfacComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

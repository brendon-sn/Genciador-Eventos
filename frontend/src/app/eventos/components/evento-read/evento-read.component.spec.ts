import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventoReadComponent } from './evento-read.component';

describe('EventoReadComponent', () => {
  let component: EventoReadComponent;
  let fixture: ComponentFixture<EventoReadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventoReadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventoReadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

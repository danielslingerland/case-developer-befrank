import {Component, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {FormBuilder, ReactiveFormsModule} from "@angular/forms";
import {Pensioenservice} from "./pensioenservice";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  constructor(
    private formBuilder: FormBuilder,
    private pensioenService: Pensioenservice
  ) {}

  pensioenwaarde: number | null | undefined;

  title = 'case-developer-frontend';

  pensioenForm = this.formBuilder.group({
    deelnemerId: 0,
    pensioenleeftijd: 0
  });

  onSubmit(): void {
    this.pensioenService.getPensioenwaarde(
      this.pensioenForm.value.deelnemerId,
      this.pensioenForm.value.pensioenleeftijd
    ).subscribe(
      response => {
        this.pensioenwaarde = response;
      }
    );
  }
}

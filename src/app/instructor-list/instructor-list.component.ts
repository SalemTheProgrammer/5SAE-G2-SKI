import { Component, OnInit } from '@angular/core';
import { InstructorService } from '../instructor.service';
import { Instructor } from '../instructor.model';

@Component({
  selector: 'app-instructor-list',
  templateUrl: './instructor-list.component.html',
  styleUrls: ['./instructor-list.component.css']
})
export class InstructorListComponent implements OnInit {
  instructors: Instructor[] = [];
  isAddingInstructor = false;
  instructor: Instructor = { numInstructor:0, firstName: '', lastName: '', dateOfHire: '' };

  constructor(private instructorService: InstructorService) { }

  ngOnInit(): void {
    this.getInstructors();
  }

  getInstructors(): void {
    this.instructorService.getInstructors().subscribe(data => {
      this.instructors = data;
    });
  }

  deleteInstructor(id: number): void {
    this.instructorService.deleteInstructor(id).subscribe(() => {
      this.getInstructors(); // Recharge la liste après suppression
    });
  }

  showAddInstructorForm(): void {
    this.isAddingInstructor = true;
  }

  addInstructor(): void {
    this.instructorService.addInstructor(this.instructor).subscribe(() => {
      this.isAddingInstructor = false;
      this.instructor = {  numInstructor:0, firstName: '', lastName: '', dateOfHire: '' }; // Réinitialiser le formulaire
      this.getInstructors(); // Recharge la liste après l'ajout
    });
  }
}

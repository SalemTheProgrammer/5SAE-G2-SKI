import { Component, OnInit } from '@angular/core';
import { Instructor } from '../instructor.model';
import { InstructorService } from '../instructor.service';

@Component({
  selector: 'app-instructor-form',
  templateUrl: './instructor-form.component.html',
  styleUrls: ['./instructor-form.component.css']
})
export class InstructorFormComponent implements OnInit {
  instructor: Instructor = {
    numInstructor: 0,
    firstName: '',
    lastName: '',
    dateOfHire: '',
   
  };

  constructor(private instructorService: InstructorService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.instructor.numInstructor) {
      this.updateInstructor();
    } else {
      this.addInstructor();
    }
  }

  addInstructor() {
    this.instructorService.addInstructor(this.instructor).subscribe(response => {
      console.log('Instructor added', response);
    });
  }

  updateInstructor() {
    this.instructorService.updateInstructor(this.instructor).subscribe(response => {
      console.log('Instructor updated', response);
    });
  }
}

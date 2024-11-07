import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { PisteService } from './piste.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet , FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  pistes: any[] = [];
  newPiste: any = { namePiste: '', color: 'GREEN', length: 0, slope: 0 };  // Initialize with default values
  colors = ['GREEN', 'BLUE', 'RED', 'BLACK'];  // Available piste colors
  editMode: boolean = false;  // To track if we are in edit mode
  pisteToEdit: any = null;  // To store the piste being edited

  constructor(private pisteService: PisteService) {}

  ngOnInit(): void {
    this.loadPistes();
  }

  // Load all pistes
  loadPistes(): void {
    this.pisteService.getAllPistes().subscribe((data) => {
      this.pistes = data;
    });
  }

  // Add a new piste
  addPiste(): void {
    this.pisteService.addPiste(this.newPiste).subscribe(() => {
      this.loadPistes();  // Reload the list after adding
      this.newPiste = { namePiste: '', color: '1', length: 0, slope: 0 };  // Reset form
    });
  }

  // Delete a piste by ID
  deletePiste(id: number): void {
    this.pisteService.deletePiste(id).subscribe(() => {
      this.loadPistes();  // Reload the list after deletion
    });
  }

  // Set the selected piste for editing
  editPiste(piste: any): void {
    this.editMode = true;
    this.pisteToEdit = { ...piste };  // Create a copy to edit
  }

  // Update an existing piste
  updatePiste(): void {
    this.pisteService.updatePiste(this.pisteToEdit).subscribe(() => {
      this.loadPistes();  // Reload the list after update
      this.cancelEdit();  // Reset the edit form
    });
  }

  // Cancel edit and reset form
  cancelEdit(): void {
    this.editMode = false;
    this.pisteToEdit = null;
  }
}

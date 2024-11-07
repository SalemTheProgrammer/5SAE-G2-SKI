import { Component, OnInit } from '@angular/core';
import { SkierService } from '../skierservice.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-skier',
  templateUrl: './skierfront.component.html',
  styleUrls: ['./skierfront.component.css'],
  standalone:true,
  imports:[FormsModule,CommonModule]
})
export class SkierComponent implements OnInit {
  skiers: any[] = [];
  
  // Initialize newSkier with required structure
  newSkier: any = {
    numSkier: null,
    firstName: '',
    lastName: '',
    dateOfBirth: '',
    city: '',
    subscription: {
      numSub: null,
      startDate: '',
      endDate: '',
      price: null,
      typeSub: ''
    }
  };

  constructor(private skierService: SkierService) {}

  ngOnInit(): void {
    this.getAllSkiers();
  }

  getAllSkiers(): void {
    this.skierService.getAllSkiers().subscribe(
      data => this.skiers = data,
      error => console.error('Erreur lors du chargement des skieurs', error)
    );
  }

  addSkier(skier: any): void {
    this.skierService.addSkier(skier).subscribe(
      newSkier => {
        this.skiers.push(newSkier);
        this.resetNewSkier(); // Reset form after adding
      },
      error => console.error("Erreur lors de l'ajout du skieur", error)
    );
  }

  resetNewSkier(): void {
    this.newSkier = {
      numSkier: null,
      firstName: '',
      lastName: '',
      dateOfBirth: '',
      city: '',
      subscription: {
        numSub: null,
        startDate: '',
        endDate: '',
        price: null,
        typeSub: ''
      }
    };
  }
}

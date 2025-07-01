import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MealType } from '../model/meal-type';

@Component({
  selector: 'app-create-recipe-component',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './create-recipe-component.html',
  styleUrl: './create-recipe-component.scss'
})
export class CreateRecipeComponent {
  recipeForm!: FormGroup;
  mealTypes: string[] = Object.values(MealType);


  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.recipeForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      cookTimeInMinutes: [0, [Validators.required, Validators.min(1)]],
      mealType: ['', Validators.required],
      // ingredients: this.fb.array([
      //   this.createIngredient()
      // ])
    });
  }

  get ingredients(): FormArray {
    return this.recipeForm.get('ingredients') as FormArray;
  }

  // createIngredient(): FormGroup {
  //   return this.fb.group({
  //     name: ['', Validators.required],
  //     amount: ['', Validators.required]
  //   });
  // }

  // addIngredient(): void {
  //   this.ingredients.push(this.createIngredient());
  // }

  // removeIngredient(index: number): void {
  //   this.ingredients.removeAt(index);
  // }

  onSubmit(): void {
    if (this.recipeForm.valid) {
      console.log('Submitted Recipe:', this.recipeForm.value);
      // You can pass this to a service to save it
    } else {
      console.warn('Form is invalid');
    }
  }
}

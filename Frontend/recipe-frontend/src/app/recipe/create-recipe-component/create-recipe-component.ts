import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MealType } from '../model/meal-type';
import { RecipeService } from '../service/recipe-service';

@Component({
  selector: 'app-create-recipe-component',
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './create-recipe-component.html',
  styleUrl: './create-recipe-component.scss'
})
export class CreateRecipeComponent {
  recipeForm!: FormGroup;
  mealTypes: string[] = Object.values(MealType);


  constructor(
    private formBuilder: FormBuilder, 
    private recipeService: RecipeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.recipeForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      cookTimeInMinutes: [0, [Validators.required, Validators.min(1)]],
      mealType: ['', Validators.required],
      ingredients: this.formBuilder.array([
        this.createIngredient()
      ])
    });
  }

  get ingredients(): FormArray {
    return this.recipeForm.get('ingredients') as FormArray;
  }

  createIngredient(): FormGroup {
    return this.formBuilder.group({
      name: ['', Validators.required],
      amount: ['', Validators.required]
    });
  }

  addIngredient(): void {
    this.ingredients.push(this.createIngredient());
  }

  removeIngredient(index: number): void {
    this.ingredients.removeAt(index);
  }

  onSubmit(): void {
    if (this.recipeForm.valid) {
      const stringifiedRecipe: string = JSON.stringify(this.recipeForm.getRawValue());
      this.recipeService.createRecipe(JSON.parse(stringifiedRecipe)).subscribe({
        next: () => {
          this.router.navigateByUrl("/recipe/list");
        },
        error: () => {
          alert("Something went wrong with adding the recipe");
        }
      });
    } else {
      alert('Form is invalid');
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../service/recipe-service';
import { Observable } from 'rxjs';
import { Recipe } from '../model/recipe';
import { RouterModule } from '@angular/router';
import { AsyncPipe, CommonModule } from '@angular/common';

@Component({
  selector: 'app-recipe-list-component',
  imports: [RouterModule, CommonModule, AsyncPipe],
  templateUrl: './recipe-list-component.html',
  styleUrl: './recipe-list-component.scss'
})
export class RecipeListComponent implements OnInit {
  recipeList$: Observable<Recipe[]> = new Observable();

  constructor(private recipeService: RecipeService) {}

  ngOnInit(): void {
    this.recipeList$ = this.recipeService.getRecipeList();
  }
}

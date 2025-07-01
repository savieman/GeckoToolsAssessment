import { ChangeDetectionStrategy, Component, OnInit, Signal } from '@angular/core';
import { AsyncPipe, CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';

import { Recipe } from '../model/recipe';
import { RecipeService } from '../service/recipe-service';

@Component({
  selector: 'app-recipe-list-component',
  imports: [RouterModule, CommonModule, AsyncPipe],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './recipe-list-component.html',
  styleUrl: './recipe-list-component.scss'
})
export class RecipeListComponent {
  recipeListSignal!: Signal<Recipe[]>;

  constructor(private recipeService: RecipeService) {
     this.recipeListSignal = toSignal(
      this.recipeService.getRecipeList(),
      { initialValue: []}
    );
  }
}

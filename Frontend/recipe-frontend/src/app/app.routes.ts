import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'recipe/list',
        loadComponent: () => import('./recipe/recipe-list-component/recipe-list-component').then(c => c.RecipeListComponent)
    },
    {
        path: 'recipe/create',
        loadComponent: () => import('./recipe/create-recipe-component/create-recipe-component').then(c => c.CreateRecipeComponent)
    }
];

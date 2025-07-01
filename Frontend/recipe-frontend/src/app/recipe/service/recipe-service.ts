import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../model/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private readonly baseUrl: string = "/api"
  private readonly user: string = "/recipes"
  
  constructor(private httpClient: HttpClient) { }

  public getRecipeList(): Observable<Recipe[]> {
    return this.httpClient.get<Recipe[]>(this.entireUrl);
  }

  public addUser(recipe: Recipe): Observable<Recipe> {
    return this.httpClient.post<Recipe>(this.entireUrl, recipe);
  }

  private get entireUrl() {
    return this.baseUrl + this.user;
  }
}

import { Ingredient } from "./ingredient";
import { MealType } from "./meal-type";

export interface Recipe {
  id?: number;
  description: string;
  name: string;
  ingredients: Ingredient[];
  cookTimeInMinutes: number;
  mealType: MealType;
}

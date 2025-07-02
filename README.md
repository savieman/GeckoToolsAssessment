# GeckoTech Full-stack developer assessment

# How to get Started

## Back-end
### Requirements
What is needed for the back-end to run is the following:
- Java version 17 or higher
- docker desktop to be able to run the testcontainers
- A suitable IDE that can run java projects

### How to run
To be able to run the microservice, open the project in your favorite IDE, make sure all the maven dependencies get installed and run the main method in `RecipeServiceApplication.java`. To be able to see the openapi swagger-ui navigate to `http://localhost:8080/swagger-ui.html`. Here all the endpoints can be seen, what they do, what they require, and what expected responses can be given.

## Front-end
### Requirements
For the front-end some:
- npm/nodejs (I used npm version 10.9.2 and node version 22.13.1)
- Your favorite code editor

### How to run
Navigate to `Frontend/recipe-frontend/`

Then when it is your first time running the project run
```bash
npm install
```
Once everything is installed you can run the project by running the following
```bash 
npm run start
```
or if you have angular/cli installed you can also use
```bash
ng serve
```
After running the project make sure you have the microservice running on `localhost:8080`, if not, you can edit on what port or domain you have the microservice in `proxy.conf.json` in the `/src` folder. When running the project head to your browser and go to `http://localhost:4200/` if everything went ok, it should route to `/recipe/list`

# Approaches I took
## Back-end
For the back-end first of all I chose to have two classes `Recipe.java` and `Ingredient.java`. I first planned on making a many-to-many relationship between them, but in the end I chose to have a one-to-many relationship between the Recipe and Ingredients. This way the relationship is less complex and when deleting a recipe, the ingredients will get deleted with it because of the `CascadeType.Remove` in `Recipe.java`

Something else I've done is from the start setup an openapi swagger-ui page to be able to see all the endpoints and what payloads, parameters, variables and responses can be expected.

For integration testing I decided on doing that on the controller so that the entire flow can be tested. I chose to use a mocked/simulated environment since that will make the tests run faster but will make them slightly less accurate. I thought this would be preferable because probably this would be the approach on bigger projects to save time while tests run on the build server.

## Front-end
Because the assessment mentioned creating a front-end to be able to evaluate the use of observables and signals I chose to keep it very simple. I created a component that lists all the recipes at `/recipe/list` with a button that routes to `/recipe/create` to be able to create a recipe. After creating/submitting a recipe correctly, it'll head back to `/recipes/lists`. 

I chose to use signals in the RecipeListComponent because a subscription was needed to the endpoint for getting recipes and this requires a bit less code than for example using an observable and the async pipe in the template. The biggest win however is having added `changeDetection: ChangeDetectionStrategy.OnPush,`. This stops the change detection for the component and will only update when the signal gets updated. This makes the component much lighter.

For the create `CreateRecipeComponent` I chose to subscribe to the observable that posts the recipe to the microservice. By on success navigating back to the `RecipeListComponent` and by on error having a simple alert. I've made sure to add the subscription to a subscription variable so that through the onDestroy lifecycle I can unsubscribe from the observable to prevent memoryleaks.

Another thing I also chose to do is use reactive forms in `CreateRecipeComponent`. Template driven forms might've been sufficient but I know in practice projects can change, validation can get more complicated and changing from template driven to reactive forms can be a lot of work on big projects.

# What I would've done differently
There are quite some things I would've added if I had more time or if the scope of the assessment would've been bigger. They are listed below.
## Back-end
1. Create separate resource classes for `Ingredient` and `Recipe`. These resource classes would only be used in the controller while an assembler would convert them to entities and these will be used in the services and repositories. 
2. Add validation to the resource classes that are exclusive to validation of the endpoints payloads and responses, and not restrictions decided by the database
3. Create GET, POST and DELETE endpoints for the ingredients under `/recipes/{recipeId}/ingredients/{IngredientId}`
4. Create PUT endpoints for both Recipes and Ingredients
5. Introduce lombok to make the creation of getters, setters and constructors easier. I haven't worked much with lombok but I know this could save a lot of code but I didn't wanna risk anything that I haven't used much in this assessment
6. Introduce an additional interface that the `RecipeController` would implement so that the openapi documentation is separated from the logic of the endpoint. Right now it wasn't that necessary but in bigger projects there could potentially be a lot more documentation that it could clutter the controller.
7. Used gradle instead of maven. The only reason I used maven is because I noticed the testcontainer website shows more documentation with maven than with gradle.
8. Create more realistic examples in the swagger UI of the playloads and responses

## Front-end
1. Add a custom script to `package.json` that runs both the front-end and back-end to make things easier
2. Add more validation to the reactive forms
3. Have a proper UI
4. Add components and pages for the other endpoints
5. Add an additional service meant for logic to separate logic from the component but because not much logic was going on I didn't feel like it was needed in this case

# Work log
Work logging file can be found in the `work-log.md` file

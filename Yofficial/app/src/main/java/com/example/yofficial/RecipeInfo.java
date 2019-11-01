package com.example.yofficial;

public class RecipeInfo {
    private String recipeId;
    private String recipeTitle;
    private String recipeSubTitle;
    private String introRecipe;
    private String servings;
    private String difficulty;
    private String duraTime;
    private String mainIngredient;
    private String type;
    private String feature;
    private String[] ingredient;
    private String[] seasoning;
    private String youtubeUrl;
    private String[] startTime;
    private String[] endTime;
    private String imgsrc;
    private String[] stepDescrib;



    public void setIntroRecipe(String introRecipe) {
        this.introRecipe = introRecipe;
    }
    public String getIntroRecipe() {
        return this.introRecipe;
    }

    public void setRecipeTitle(String recipeTitle) { this.recipeTitle = recipeTitle; }
    public String getRecipeTitle() { return this.recipeTitle; }

    public void setRecipeSubTitle(String recipeSubTitle) { this.recipeSubTitle = recipeSubTitle; }
    public String getRecipeSubTitle() { return this.recipeSubTitle; }

    public void setServings(String servings) { this.servings = servings; }
    public String getServings() { return this.servings; }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getDifficulty() { return this.difficulty; }

    public void setDuraTime(String duraTime) { this.duraTime = duraTime; }
    public String getDuraTime() { return this.duraTime; }

    public void setMainIngredient(String mainIngredient) { this.mainIngredient = mainIngredient; }
    public String getMainIngredient() { return this.mainIngredient; }

    public void setType(String type) { this.type = type; }
    public String getType() { return this.type; }

    public void setFeature(String feature) { this.feature = feature; }
    public String getFeature() { return this.feature; }

    public void setIngredient (String[] ingredient) { this.ingredient = ingredient; }
    public String getIngredient () {
        String temp = "";

        for (int i = 0; i < ingredient.length; i++) {
            if (i != ingredient.length - 1) {
                temp += ingredient[i] + ", ";
            } else {
                temp += ingredient[i];
            }
        }

        temp = temp.replace('/',' ');
        return temp;
    }

    public void setSeasoning (String[] seasoning) { this.seasoning = seasoning; };
    public String getSeasoning() {
        String temp = "";

        for (int i = 0; i < seasoning.length; i++) {
            if (i != seasoning.length - 1) {
                temp += seasoning[i] + ", ";
            } else {
                temp += seasoning[i];
            }
        }

        temp = temp.replace('/',' ');
        return temp;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }
}




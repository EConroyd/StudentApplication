package com.example.emily.smellslikebakin;

/**
 * Created by Emily on 3/29/2017.
 */
public class IngredientsFragment extends CheckBoxesFragment {
    @Override
    public String[] getContents(int index) {
        return Recipes.ingredients[index].split("`");
    }
}

package stormedpanda.simplyjetpacks.integration;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import stormedpanda.simplyjetpacks.SimplyJetpacks;
import stormedpanda.simplyjetpacks.datagen.SJTags;
import stormedpanda.simplyjetpacks.item.JetpackItem;

import java.util.ArrayList;
import java.util.List;

public final class JetpackParticleRecipeMaker {

    public static List<Recipe<?>> createJetpackParticleRecipes() {
        List<Recipe<?>> recipes = new ArrayList<>();
        String group = "simplyjetpacks.particle_customization";
        // TODO: fix these
        List<Item> jetpackList = SJTags.JETPACK.getValues();
        List<Item> particleList = SJTags.PARTICLES.getValues();
        ItemStack jetpackStack;
        ItemStack particleStack;
        for (Item jetpack : jetpackList) {
            jetpackStack = new ItemStack(jetpack);
            for (Item particle : particleList) {
                particleStack = new ItemStack(particle);
                NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, Ingredient.of(particleStack), Ingredient.of(jetpackStack));
                ResourceLocation id = new ResourceLocation(SimplyJetpacks.MODID, particleStack.getItem() + "_" + jetpackStack.getItem());
                ItemStack output = JetpackItem.setParticleId(jetpackStack.copy(), particleStack);
                ShapelessRecipe recipe = new ShapelessRecipe(id, group, output, inputs);
                recipes.add(recipe);
            }
        }
        return recipes;
    }
}

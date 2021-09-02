package tonius.simplyjetpacks.util.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreIngredient;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.crafting.UpgradingRecipeShaped;
import tonius.simplyjetpacks.handler.RegistryHandler;
import tonius.simplyjetpacks.item.Fluxpack;
import tonius.simplyjetpacks.item.Jetpack;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public final class RecipeHelper {

    public static final List<IRecipe> RECIPE_LIST = RegistryHandler.RECIPES_TO_REGISTER;
    private static final String MODID = SimplyJetpacks.MODID;
    private static final String MODNAME = SimplyJetpacks.MODNAME;
    private static int j = 0;

    /*
     * This adds the recipe to the list of crafting recipes. Since whom cares about names, it adds it as recipesX, where X is the current recipe you are adding.
     */
    public static void addRecipe(int j, IRecipe rec) {
        addRecipe("recipes" + j, rec);
    }

    /*
     * This adds the recipe to the list of crafting recipes. Cares about names.
     */
    public static void addRecipe(String name, IRecipe rec) {
        Item i = rec.getRecipeOutput().getItem();
        if (rec.getRegistryName() == null) {
            RECIPE_LIST.add(rec.setRegistryName(new ResourceLocation(MODID, name)));
        } else {
            RECIPE_LIST.add(rec);
        }
        RecipeHandler.lastRecipe = rec;
    }

    /*
     * This adds a shaped recipe to the list of crafting recipes, using the Forge format.
     */
    public static void addOldShaped(ItemStack output, Object... input) {
        ShapedPrimer primer = CraftingHelper.parseShaped(input);
        addRecipe(j++, new ShapedRecipes(new ResourceLocation(MODID, "recipes" + j).toString(), primer.width, primer.height, primer.input, output));
    }

    /*
     * This adds a shaped recipe to the list of crafting recipes, using the Forge format, with a custom group.
     */
    public static void addOldShaped(String group, ItemStack output, Object... input) {
        ShapedPrimer primer = CraftingHelper.parseShaped(input);
        addRecipe(j++, new ShapedRecipes(new ResourceLocation(MODID, group).toString(), primer.width, primer.height, primer.input, output));
    }

    /*
     * This adds a shaped recipe to the list of crafting recipes, using the Forge format, with a custom group.
     */
    public static void addOldShaped(String name, String group, ItemStack output, Object... input) {
        ShapedPrimer primer = CraftingHelper.parseShaped(input);
        addRecipe(j++, new ShapedRecipes(new ResourceLocation(MODID, group).toString(), primer.width, primer.height, primer.input, output).setRegistryName(MODID, name));
    }

    /*
     * This adds a shapeless recipe to the list of crafting recipes, using the Forge format.
     */
    public static void addOldShapeless(ItemStack output, Object... input) {
        addRecipe(j++, new ShapelessRecipes(new ResourceLocation(MODID, "recipes" + j).toString(), output, createInput(input)));
    }

    /*
     * This adds a shapeless recipe to the list of crafting recipes, using the Forge format, with a custom group.
     */
    public static void addOldShapeless(String group, ItemStack output, Object... input) {
        addRecipe(j++, new ShapelessRecipes(new ResourceLocation(MODID, group).toString(), output, createInput(input)));
    }

    public static void addOldShapeless(String name, String group, ItemStack output, Object... input) {
        addRecipe(j++, new ShapelessRecipes(new ResourceLocation(MODID, group).toString(), output, createInput(input)).setRegistryName(MODID, name));
    }

    /*
     * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.
     */
    public static void addShapeless(ItemStack output, Object... inputs) {
        addRecipe(j++, new ShapelessRecipes(MODID + ":" + j, output, createInput(inputs)));
    }

    public static void addShapeless(Item output, Object... inputs) {
        addShapeless(new ItemStack(output), inputs);
    }

    public static void addShapeless(Block output, Object... inputs) {
        addShapeless(new ItemStack(output), inputs);
    }

    /*
     * Adds a shapeless recipe with X output using an array of inputs. Use Strings for OreDictionary support. This array is not ordered.  This has a custom group.
     */
    public static void addShapeless(String group, ItemStack output, Object... inputs) {
        addRecipe(j++, new ShapelessRecipes(MODID + ":" + group, output, createInput(inputs)));
    }

    public static void addShapeless(String group, Item output, Object... inputs) {
        addShapeless(group, new ItemStack(output), inputs);
    }

    public static void addShapeless(String group, Block output, Object... inputs) {
        addShapeless(group, new ItemStack(output), inputs);
    }

    /*
     * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
     * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid.
     */
    public static void addShaped(ItemStack output, int width, int height, Object... input) {
        addRecipe(j++, genShaped(output, width, height, input));
    }

    public static void addShaped(Item output, int width, int height, Object... input) {
        addShaped(new ItemStack(output), width, height, input);
    }

    public static void addShaped(Block output, int width, int height, Object... input) {
        addShaped(new ItemStack(output), width, height, input);
    }

    /*
     * Adds a shapeless recipe with X output on a crafting grid that is W x H, using an array of inputs.  Use null for nothing, use Strings for OreDictionary support, this array must have a length of width * height.
     * This array is ordered, and items must follow from left to right, top to bottom of the crafting grid. This has a custom group.
     */
    public static void addShaped(String group, ItemStack output, int width, int height, Object... input) {
        addRecipe(j++, genShaped(MODID + ":" + group, output, width, height, input));
    }

    public static void addShaped(String group, Item output, int width, int height, Object... input) {
        addShaped(group, new ItemStack(output), width, height, input);
    }

    public static void addShaped(String group, Block output, int width, int height, Object... input) {
        addShaped(group, new ItemStack(output), width, height, input);
    }

    public static ShapedRecipes genShaped(ItemStack output, int l, int w, Object[] input) {
        if (input[0] instanceof Object[]) {
            input = (Object[]) input[0];
        }
        if (l * w != input.length) {
            throw new UnsupportedOperationException("Attempted to add invalid shaped recipe. Complain to the author of " + MODNAME);
        }
        NonNullList<Ingredient> inputL = NonNullList.create();
        for (int i = 0; i < input.length; i++) {
            Object k = input[i];
            if (k instanceof String) {
                inputL.add(i, new OreIngredient((String) k));
            } else if (k instanceof ItemStack && !((ItemStack) k).isEmpty()) {
                inputL.add(i, Ingredient.fromStacks((ItemStack) k));
            } else if (k instanceof Item) {
                inputL.add(i, Ingredient.fromStacks(new ItemStack((Item) k)));
            } else if (k instanceof Block) {
                inputL.add(i, Ingredient.fromStacks(new ItemStack((Block) k)));
            } else {
                inputL.add(i, Ingredient.EMPTY);
            }
        }
        return new ShapedRecipes(MODID + ":" + j, l, w, inputL, output);
    }

    public static ShapedRecipes genShaped(String group, ItemStack output, int l, int w, Object[] input) {
        if (input[0] instanceof List) {
            input = ((List<?>) input[0]).toArray();
        } else if (input[0] instanceof Object[]) {
            input = (Object[]) input[0];
        }
        if (l * w != input.length) {
            throw new UnsupportedOperationException("Attempted to add invalid shaped recipe. Complain to the author of " + MODNAME);
        }
        NonNullList<Ingredient> inputL = NonNullList.create();
        for (int i = 0; i < input.length; i++) {
            Object k = input[i];
            if (k instanceof String) {
                inputL.add(i, new OreIngredient((String) k));
            } else if (k instanceof ItemStack && !((ItemStack) k).isEmpty()) {
                inputL.add(i, Ingredient.fromStacks((ItemStack) k));
            } else if (k instanceof Item) {
                inputL.add(i, Ingredient.fromStacks(new ItemStack((Item) k)));
            } else if (k instanceof Block) {
                inputL.add(i, Ingredient.fromStacks(new ItemStack((Block) k)));
            } else {
                inputL.add(i, Ingredient.EMPTY);
            }
        }
        return new ShapedRecipes(group, l, w, inputL, output);
    }

    public static NonNullList<Ingredient> createInput(Object[] input) {
        if (input[0] instanceof List) {
            input = ((List<?>) input[0]).toArray();
        } else if (input[0] instanceof Object[]) {
            input = (Object[]) input[0];
        }
        NonNullList<Ingredient> inputL = NonNullList.create();
        for (int i = 0; i < input.length; i++) {
            Object k = input[i];
            if (k instanceof String) {
                inputL.add(i, new OreIngredient((String) k));
            } else if (k instanceof ItemStack) {
                inputL.add(i, Ingredient.fromStacks((ItemStack) k));
            } else if (k instanceof Item) {
                inputL.add(i, Ingredient.fromStacks(new ItemStack((Item) k)));
            } else if (k instanceof Block) {
                inputL.add(i, Ingredient.fromStacks(new ItemStack((Block) k)));
            } else {
                throw new UnsupportedOperationException("Attempted to add invalid shapeless recipe. Complain to the author of " + MODNAME);
            }
        }
        return inputL;
    }

    public static void addArmoredReverseRecipe(EnumSet setJ, EnumSet setArmorJ, EnumSet setF, EnumSet setArmorF) {
        Iterator i = setJ.iterator();
        Iterator t = setArmorJ.iterator();
        EnumSet fluxpacks = setF.clone();
        //fluxpacks.remove(setF.toArray()[0]);
        Iterator i2 = fluxpacks.iterator();
        Iterator t2 = setArmorF.iterator();
        while (i.hasNext() && t.hasNext()) {
            ItemStack jetpack = ((Jetpack) i.next()).getStackJetpack();
            ItemStack jetpackArmored = ((Jetpack) t.next()).getStackJetpack();
            ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpack, "J", 'J', jetpackArmored));
        }
        while (i2.hasNext() && t2.hasNext()) {
            ItemStack fluxpack = ((Fluxpack) i2.next()).getStackFluxpack();
            ItemStack fluxpackArmored = ((Fluxpack) t2.next()).getStackFluxpack();
            ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpack, "F", 'F', fluxpackArmored));
        }
    }

    public static void addArmoredReverseRecipe(EnumSet set, EnumSet setArmor, Enum pack) {
        Iterator i = set.iterator();
        Iterator t = setArmor.iterator();
        while (i.hasNext() && t.hasNext()) {
            if (pack instanceof Jetpack) {
                ItemStack jetpack = ((Jetpack) i.next()).getStackJetpack();
                ItemStack jetpackArmored = ((Jetpack) t.next()).getStackJetpack();
                ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(jetpack, "J", 'J', jetpackArmored));
            } else {
                ItemStack fluxpack = ((Fluxpack) i.next()).getStackFluxpack();
                ItemStack fluxpackArmored = ((Fluxpack) t.next()).getStackFluxpack();
                ForgeRegistries.RECIPES.register(new UpgradingRecipeShaped(fluxpack, "J", 'J', fluxpackArmored));
            }
        }
    }
}

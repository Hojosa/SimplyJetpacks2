package tonius.simplyjetpacks.config;

import tonius.simplyjetpacks.client.util.RenderUtils.HUDPositions;
import tonius.simplyjetpacks.integration.ModType;

public class Defaults {
    // Item
    public static final boolean enableFuelEfficiencyEnchantment = true;
    public static final boolean addRAItemsIfNotInstalled = true;
    // Integration
    public static final boolean enableIntegrationVanilla = true;
    public static final boolean enableIntegrationEIO = ModType.ENDER_IO.loaded;
    public static final boolean enableIntegrationTE = ModType.THERMAL_EXPANSION.loaded;
    public static final boolean enableIntegrationTD = ModType.THERMAL_DYNAMICS.loaded;
    public static final boolean enableIntegrationMek = ModType.MEKANISM.loaded;
    public static final boolean enableIntegrationIE = ModType.IMMERSIVE_ENGINEERING.loaded;
    public static final boolean enableIntegrationRR = ModType.REDSTONE_REPOSITORY.loaded;
    public static final int gelidEnderiumEnergyUsageBonus = 80;
    // Controls
    public static final boolean customControls = false;
    public static final String flyKey = "SPACE";
    public static final String descendKey = "LSHIFT";
    public static final boolean invertHoverSneakingBehavior = false;
    public static final boolean doubleTapSprintInAir = true;
    // Aesthetics
    public static final boolean enableArmor3DModels = true;
    // Sounds
    public static final boolean jetpackSounds = true;
    // GUI
    public static final boolean holdShiftForDetails = true;
    public static final HUDPositions HUDPosition = HUDPositions.TOP_LEFT;
    public static final int HUDOffsetX = 0;
    public static final int HUDOffsetY = 0;
    public static final double HUDScale = 1.0D;
    public static final boolean showHUDWhileChatting = true;
    public static final boolean enableEnergyHUD = true;
    public static final boolean minimalEnergyHUD = false;
    public static final boolean showExactEnergyInHUD = false;
    public static final boolean enableStateHUD = true;
    public static final boolean enableStateMessages = true;
    public static final int HUDTextColor = 0xeeeeee;
    // Misc
    public static final boolean joinAdvancements = true;
    public static boolean enableIntegrationRA = ModType.REDSTONE_ARSENAL.loaded;
}
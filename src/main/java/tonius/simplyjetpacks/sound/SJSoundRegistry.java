package tonius.simplyjetpacks.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tonius.simplyjetpacks.SimplyJetpacks;

public enum SJSoundRegistry implements IModSound {

	JETPACK(SoundCategory.PLAYERS, "jetpack"),
	JETPACK_OTHER(SoundCategory.PLAYERS, "jetpack_other"),
	ROCKET(SoundCategory.PLAYERS, "rocket");

	private final ResourceLocation resourceLocation;
	private final SoundCategory soundCategory;
	private SoundEvent soundEvent = null;

	SJSoundRegistry(SoundCategory soundCategory, ResourceLocation resourceLocation) {
		this.soundCategory = soundCategory;
		this.resourceLocation = resourceLocation;
	}

	SJSoundRegistry(SoundCategory soundCategory, String name) {
		this(soundCategory, new ResourceLocation(SimplyJetpacks.MODID, name));
	}

	public static void init() {
		for (SJSoundRegistry soundRegistry : values()) {
			if (SoundEvent.REGISTRY.containsKey(soundRegistry.resourceLocation)) {
				soundRegistry.soundEvent = SoundEvent.REGISTRY.getObject(soundRegistry.resourceLocation);
			} else {
				soundRegistry.soundEvent = new SoundEvent(soundRegistry.resourceLocation);
				ForgeRegistries.SOUND_EVENTS.register(soundRegistry.soundEvent.setRegistryName(soundRegistry.resourceLocation));
			}
		}
	}

	@Override
	public boolean isValid() {
		return soundEvent != null;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return soundEvent;
	}

	@Override
	public SoundCategory getSoundCategory() {
		return soundCategory;
	}
}

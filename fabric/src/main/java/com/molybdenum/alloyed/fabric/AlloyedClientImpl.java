package com.molybdenum.alloyed.fabric;

import com.molybdenum.alloyed.fabric.events.ClientEventsFabric;
import net.fabricmc.api.ClientModInitializer;

public class AlloyedClientImpl implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AlloyedClient.init();
		ClientEventsFabric.init();
		//ConductorCapItemRenderer.register();
		//CRExtraDisplayTags.register();
	}

//	@SuppressWarnings({"unchecked", "rawtypes"}) // jank!
//	public static void registerClientCommands(Consumer<CommandDispatcher<SharedSuggestionProvider>> consumer) {
//		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
//			CommandDispatcher<SharedSuggestionProvider> casted = (CommandDispatcher) dispatcher;
//			consumer.accept(casted);
//		});
//	}
//
//	public static void registerModelLayer(ModelLayerLocation layer, Supplier<LayerDefinition> definition) {
//		EntityModelLayerRegistry.registerModelLayer(layer, definition::get);
//	}
//
//	public static void registerBuiltinPack(String id, String name) {
//		ModContainer mod = FabricLoader.getInstance().getModContainer(Alloyed.MOD_ID).orElseThrow();
//		ResourceManagerHelper.registerBuiltinResourcePack(
//				Railways.asResource(id), mod, name, ResourcePackActivationType.NORMAL
//		);
//	}
}

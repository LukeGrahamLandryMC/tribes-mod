package io.github.lukegrahamlandry.tribes.config;

import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ForgeConfigSpec;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TribesConfig {
    //Declaration of config variables
    private static ForgeConfigSpec.IntValue numTribes;
    private static ForgeConfigSpec.BooleanValue tribeRequired;
    private static ForgeConfigSpec.IntValue effectChangeDays;
    private static ForgeConfigSpec.ConfigValue<List<? extends Integer>> tierThresholds;
    private static ForgeConfigSpec.ConfigValue<List<? extends Integer>> tierNegEffects;
    private static ForgeConfigSpec.ConfigValue<List<? extends Integer>> tierPosEffects;

    //Initialization of the config files and their respective variables
    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client){
        server.comment("Server configuration settings")
                .push("server");
        numTribes = server
                .comment("Maximum Number of Tribes: ")
                .defineInRange("numberOfTribes", 10, 1, 999);
        effectChangeDays = server
                .comment("Days between Effect Change: ")
                .defineInRange("daysBetweenEffectChange", 10, 0, 999);
        tribeRequired = server
                .comment("Tribe Required: ")
                .define("tribesRequired", true);
        tierThresholds = server
                .comment("I:Tier Thresholds: ")
                .defineList("tier_thresholds", Arrays.asList(4,12),i -> (int)i>=0);
        tierNegEffects = server
                .comment("I:Tier Negative Effects: ")
                .defineList("tier_negative_effects", Arrays.asList(1,1,0),i -> (int)i>=0);
        tierPosEffects = server
                .comment("I:Tier Positive Effects: ")
                .defineList("tier_positive_effects", Arrays.asList(1,2,3),i -> (int)i>=0);
        server.pop();
    }

    //Getter Method for the max number of tribes
    public static int getMaxNumberOfTribes(){
        return numTribes.get();
    }

    //Getter Method for the whether or not tribes are required
    public static boolean isTribeRequired(){
        return tribeRequired.get();
    }

    //Getter Method for the number of days between effects change
    public static int getDaysBetweenEffects(){ return effectChangeDays.get(); }

    //Getter Method for tier thresholds
    public static List<? extends Integer> getTierThresholds(){
        return tierThresholds.get();
    }

    //Getter Method for tier negative effects
    public static List<? extends Integer> getTierNegativeEffects(){
        return tierNegEffects.get();
    }

    //Getter Method for tier positive effects
    public static List<? extends Integer> getTierPositiveEffects(){
        return tierPosEffects.get();
    }

    public static int getMaxTribeNameLength(){
        return 24;
    }


    // might change based on config later
    public static List<Effect> getGoodEffects(){
        ArrayList<Effect> theEffects = new ArrayList<>();

        for (Field field : Effects.class.getFields()){
            try {
                if (field.get(null) instanceof Effect){
                    Effect toCheck = (Effect) field.get(null);
                    if (toCheck.isBeneficial() && !toCheck.equals(Effects.INSTANT_HEALTH) && !toCheck.equals(Effects.CONDUIT_POWER) && !toCheck.equals(Effects.HEALTH_BOOST) && !toCheck.equals(Effects.LUCK) && !toCheck.equals(Effects.HERO_OF_THE_VILLAGE)){
                        theEffects.add(toCheck);
                    }
                }
            } catch (IllegalAccessException ignored) {}
        }

        return theEffects;
    }

    // might change based on config later
    public static List<Effect> getBadEffects(){
        ArrayList<Effect> theEffects = new ArrayList<>();

        for (Field field : Effects.class.getFields()){
            try {
                if (field.get(null) instanceof Effect){
                    Effect toCheck = (Effect) field.get(null);
                    if (!toCheck.isBeneficial() && !toCheck.equals(Effects.INSTANT_DAMAGE)){
                        theEffects.add(toCheck);
                    }
                }
            } catch (IllegalAccessException ignored) {}
        }

        return theEffects;
    }
}

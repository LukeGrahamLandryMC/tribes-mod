package io.github.lukegrahamlandry.tribes.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.lukegrahamlandry.tribes.commands.arguments.TribeArgumentType;
import io.github.lukegrahamlandry.tribes.tribe_data.Tribe;
import io.github.lukegrahamlandry.tribes.tribe_data.TribeActionResult;
import io.github.lukegrahamlandry.tribes.tribe_data.TribesManager;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class CountTribeCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("count")
                .requires(cs->cs.hasPermissionLevel(0)) //permission
                .then(Commands.argument("tribe", TribeArgumentType.tribe())
                        .executes(CountTribeCommand::handleCount)
                ).executes(ctx -> {
                            ctx.getSource().sendFeedback(new StringTextComponent("pick a tribe to count"), false);
                            return 0;
                        }
                );

    }

    public static int handleCount(CommandContext<CommandSource> source) throws CommandSyntaxException {
        Tribe tribe = TribeArgumentType.getTribe(source, "tribe");

        if (tribe != null) {
            source.getSource().sendFeedback(new StringTextComponent(tribe.getName() + " has " + tribe.getCount() + " members (tier " + tribe.getTribeTier() + ")"), true);
        }

        return Command.SINGLE_SUCCESS;
    }
}

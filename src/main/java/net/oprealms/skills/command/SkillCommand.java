package net.oprealms.skills.command;

import me.lucko.helper.Commands;
import net.oprealms.skills.gui.SkillGui;

public class SkillCommand {

    public SkillCommand() {
        Commands.create().assertPlayer().handler(command -> new SkillGui(command.sender()).open()).register("skill", "skills");
    }
}

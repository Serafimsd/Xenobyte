package forgefuck.team.xenobyte.modules;

import forgefuck.team.xenobyte.api.module.Category;
import forgefuck.team.xenobyte.api.module.CheatModule;
import forgefuck.team.xenobyte.api.module.PerformMode;

public class AutoSprint extends CheatModule {
    
    public AutoSprint() {
        super("AutoSprint", Category.PLAYER, PerformMode.TOGGLE);
    }
    
    @Override public void onTick(boolean inGame) {
        if (inGame) {
            utils.player().setSprinting(true);
        }
    }
    
    @Override public String moduleDesc() {
        return "Постоянный спринт";
    }

}

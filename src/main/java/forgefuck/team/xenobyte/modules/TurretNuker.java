package forgefuck.team.xenobyte.modules;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import forgefuck.team.xenobyte.api.config.Cfg;
import forgefuck.team.xenobyte.api.module.Category;
import forgefuck.team.xenobyte.api.module.CheatModule;
import forgefuck.team.xenobyte.api.module.PerformMode;
import forgefuck.team.xenobyte.gui.click.elements.Button;
import forgefuck.team.xenobyte.gui.click.elements.Panel;
import forgefuck.team.xenobyte.gui.click.elements.ScrollSlider;
import net.minecraftforge.client.event.MouseEvent;

public class TurretNuker extends CheatModule {
    
    @Cfg("onView") private boolean onView;
    @Cfg("radius") private int radius;
    
    public TurretNuker() {
        super("TurretNuker", Category.MODS, PerformMode.TOGGLE);
        radius = 1;
    }
    
    @SubscribeEvent public void mouseEvent(MouseEvent e) {
        if (e.button == 0 && e.buttonstate) {
            utils.nukerList(onView ? utils.mop() : utils.myCoords(), radius).forEach(pos -> {
                utils.sendPacket("openmodularturrets", (byte) 9, pos[0], pos[1], pos[2]);
            });
            e.setCanceled(true);
        }
    }
    
    @Override public boolean isWorking() {
        return Loader.isModLoaded("openmodularturrets");
    }
    
    @Override public String moduleDesc() {
        return "Разрушает блоки в радиусе по ЛКМ";
    }
    
    @Override public Panel settingPanel() {
        return new Panel(
            new ScrollSlider("Radius", radius, 20) {
                @Override public void onScroll(int dir, boolean withShift) {
                    radius = processSlider(dir, withShift);
                }
            },
            new Button("OnView", onView) {
                @Override public void onLeftClick() {
                    buttonValue(onView = !onView);
                }
                @Override public String elementDesc() {
                    return "Разрушение блоков по взгляду или вокруг игрока";
                }
            }
        );
    }

}

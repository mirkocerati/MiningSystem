package com.aubenoire.miningsystem.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;

@AllArgsConstructor
public class MinedBlock {

    @Getter @Setter
    private final Material material;

    @Getter @Setter
    private final Location location;

    @Getter @Setter
    private final Long time;

    /**
     * Regenerate the block that's been mined
     */
    public void regenerate() {
        location.getBlock().setType(material);
    }

}

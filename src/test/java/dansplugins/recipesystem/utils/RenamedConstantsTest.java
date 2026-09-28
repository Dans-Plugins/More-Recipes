package dansplugins.recipesystem.utils;

import org.bukkit.Material;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RenamedConstantsTest {

    @Test
    void takesTheFirstNameThisServerHas() {
        // the build's spigot-api predates the rename, so only the old name exists here
        assertEquals(Material.getMaterial("GRASS"), RenamedConstants.shortGrass());
        assertEquals(Material.DIRT, RenamedConstants.material("NOT_A_MATERIAL", "DIRT"));
    }

    @Test
    void refusesWhenNoNameExists() {
        assertThrows(IllegalStateException.class, () -> RenamedConstants.material("NOT_A_MATERIAL"));
    }
}

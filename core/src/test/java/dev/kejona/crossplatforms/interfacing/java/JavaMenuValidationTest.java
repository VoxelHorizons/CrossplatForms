package dev.kejona.crossplatforms.interfacing.java;

import dev.kejona.crossplatforms.inventory.InventoryLayout;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class JavaMenuValidationTest {

    @Test void validInventoryShapes() {
        for (int rows = 1; rows <= 6; rows++) {
            assertNull(JavaMenu.configurationProblem(InventoryLayout.CHEST, rows * 9,
                    Collections.singleton(rows * 9 - 1)));
        }
        assertNull(JavaMenu.configurationProblem(InventoryLayout.CHEST, 5, Collections.singleton(4)));
        assertNull(JavaMenu.configurationProblem(InventoryLayout.HOPPER, 9, Collections.singleton(4)));
        assertNull(JavaMenu.configurationProblem(InventoryLayout.DISPENSER, 9, Collections.singleton(8)));
    }

    @Test void rejectInvalidChestSizes() {
        for (int size : new int[]{0, 1, 8, 10, 53, 55, 63}) {
            assertNotNull(JavaMenu.configurationProblem(InventoryLayout.CHEST, size,
                    Collections.emptySet()), "size " + size);
        }
    }

    @Test void rejectOutOfBoundsSlots() {
        assertNotNull(JavaMenu.configurationProblem(InventoryLayout.CHEST, 9, Collections.singleton(-1)));
        assertNotNull(JavaMenu.configurationProblem(InventoryLayout.CHEST, 9, Collections.singleton(9)));
        assertNotNull(JavaMenu.configurationProblem(InventoryLayout.CHEST, 5, Collections.singleton(5)));
        assertNotNull(JavaMenu.configurationProblem(InventoryLayout.HOPPER, 9, Collections.singleton(5)));
        assertNotNull(JavaMenu.configurationProblem(InventoryLayout.DISPENSER, 9, Collections.singleton(9)));
        assertNotNull(JavaMenu.configurationProblem(null, 9, Collections.emptySet()));
    }
}

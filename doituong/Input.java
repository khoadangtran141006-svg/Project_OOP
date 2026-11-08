package doituong;

import java.util.Scanner;

/** Shared console input so menus and entities do not buffer System.in separately. */
public final class Input {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Input() {
    }

    public static Scanner get() {
        return SCANNER;
    }
}

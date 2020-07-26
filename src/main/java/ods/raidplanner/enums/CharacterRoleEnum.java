package ods.raidplanner.enums;

public enum CharacterRoleEnum {


    DAMAGE_DEALER("Damage Dealer", "DD"),
    TANK("Tank", "T"),
    HEALER("Healer", "H");

    private String description;
    private String tag;

    CharacterRoleEnum(String description, String tag) {
        this.description = description;
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }
}

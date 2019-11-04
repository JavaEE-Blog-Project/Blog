package cn.myblog.model.enums;

public enum JournalType implements ValueEnum<Integer>{

    PUBLIC(1),

    INTIMATE(0);

    private final int value;

    JournalType(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

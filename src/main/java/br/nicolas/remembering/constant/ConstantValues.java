package br.nicolas.remembering.constant;

public final class ConstantValues {

    private ConstantValues() {}

    public static final int NAME_MAXIMUM_SIZE = 75;

    public static final int NAME_MINIMUM_SIZE = 5;

    public static final int EMAIL_MAXIMUM_SIZE = 50;

    public static final int EMAIL_MINIMUM_SIZE = 12;

    public static final int PASSWORD_MINIMUM_SIZE = 8;

    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public static final int MINIMUM_GRADE_TO_PASS = 7;

    public static final int MINIMUM_GRADE = 0;

    public static final int MAXIMUM_GRADE = 10;

    public static final int MAXIMUM_GRADES_PER_STUDENT = 3;

    public static final int MAXIMUM_STUDENTS_PER_CLASS = 40;

    public static final int CLASS_MINIMUM_YEAR = 2022;

    public static final int CLASS_MAXIMUM_YEAR = 2026;

    public static final int MAX_CLASSES_TEACHER_CAN_LESSON = 3;
}

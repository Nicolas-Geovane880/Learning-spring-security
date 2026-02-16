package br.nicolas.remembering.constant;

public class ErrorMessage {

    private ErrorMessage () {}

    // Standard exception messages

    public static final String STUDENT_NOT_FOUND = "validation.student.not.found";

    public static final String TEACHER_NOT_FOUND = "validation.teacher.not.found";

    public static final String CLASS_NOT_FOUND = "validation.class.not.found";

    public static final String STUDENT_ALREADY_HAS_GRADE = "validation.student.already.has.grade";

    public static final String EMAIL_ALREADY_IN_USE = "validation.email.already.in.use";

    public static final String USER_NOT_FOUND = "validation.user.not.found";

    public static final String CLASS_EXCEED_STUDENTS_NUMBER = "validation.class.exceeds.students.number";

    public static final String REQUEST_BODY_IS_REQUIRED = "validation.request.body.is.required";

    public static final String JSON_INVALID_FORMAT = "validation.json.invalid.format";

    public static final String MAX_CLASSES_TEACHER_CAN_LESSON = "validation.max.classes.teacher.can.lesson";


    // Bean validation messages

    public static final String INVALID_GRADES = "{validation.invalid.grades}";

    public static final String GRADE_MUST_BE_FLOAT_NUMBER = "{validation.grade.must.be.float.number}";

    public static final String STUDENT_MUST_HAVE_3_GRADES = "{validation.student.must.have.three.grades}";

    public static final String EMAIL_INVALID = "{validation.email.invalid}";

    public static final String PASSWORD_MINIMUM_SIZE = "{validation.password.minimum.size}";

    public static final String FIELD_NOT_NULL = "{validation.field.not.null}";

    public static final String FIELD_NOT_BLANK = "{validation.field.not.blank}";

    public static final String POSITIVE_NUMBER_FIELD = "{validation.field.positive}";

    public static final String FIELD_SIZE = "{validation.field.size}";

    public static final String CLASS_YEAR_INTERVAL = "{validation.class_year_interval}";

    // Handler exception generic titles

    public static final String OPERATION_INTERRUPTED = "generic.operation.interrupted";

    public static final String INTERNAL_ERROR = "generic.internal.error";

    public static final String AUTHENTICATION_INTERRUPTED = "generic.authentication.interrupted";

    public static final String USER_NOT_ID_OWNER = "generic.authentication.failure.user.not.id.owner";
}

package ods.raidplanner.exceptions;

public class NotFoundException extends ODSException {

    public NotFoundException(Object parameter, Class className) {
        super("Entity " + className.getCanonicalName() + " with param " + parameter + " not found");
    }
}

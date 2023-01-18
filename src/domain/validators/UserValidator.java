package domain.validators;

import domain.User;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        validateFirstName(entity.getFirstName());
        validateLastName(entity.getLastName());
    }

    private void validateFirstName(String firstName) throws ValidationException {
        if(firstName == null)
            throw new ValidationException("Prenumele trebuie sa fie diferit de null!");
        if(firstName.length() >= 15)
            throw new ValidationException("Prenumele e prea lung!");
        if(firstName.isEmpty())
            throw new ValidationException("Prenumele nu trebuie sa fie gol!");

    }

    private void validateLastName(String lastName) throws ValidationException {
        if(lastName == null)
            throw new ValidationException("Numele trebuie sa fie diferit de null!");
        if(lastName.length() >= 15)
            throw new ValidationException("Numele e prea lung!");
        if(lastName.isEmpty())
            throw new ValidationException("Numele nu trebuie sa fie gol!");

    }
    private void validate_username(String username) throws ValidationException {
        if(username == null)
            throw new ValidationException("Username trebuie sa fie diferit de null!");
        if(username.length() >= 15)
            throw new ValidationException("Username e prea lung!");
        if(username.isEmpty())
            throw new ValidationException("Username nu trebuie sa fie gol!");

    }
}

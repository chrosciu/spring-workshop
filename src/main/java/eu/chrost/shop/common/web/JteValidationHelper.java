package eu.chrost.shop.common.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class JteValidationHelper {
    /*
        Needed to write custom validation helper to display BindingResult in templates
        https://github.com/casid/jte/issues/362
    */

    private BindingResult bindingResult;

    public boolean hasErrors() {
        return bindingResult != null && bindingResult.hasErrors();
    }

    public boolean hasGlobalErrors() {
        return bindingResult != null && bindingResult.hasGlobalErrors();
    }

    public boolean hasError(String name) {
        return bindingResult != null && bindingResult.hasFieldErrors(name);
    }

    public List<ObjectError> getErrors() {
        if (bindingResult == null) {
            return Collections.emptyList();
        } else {
            return bindingResult.getAllErrors();
        }
    }

    public FieldError getError(String fieldName) {
        if (bindingResult == null) {
            return null;
        } else {
            return bindingResult.getFieldError(fieldName);
        }
    }

    public List<ObjectError> getGlobalErrors() {
        if (bindingResult == null) {
            return Collections.emptyList();
        } else {
            return bindingResult.getGlobalErrors();
        }
    }
}

package pl.waw.mizinski.umowy.validation;

import pl.waw.mizinski.umowy.model.Umowa;

public class UmowaValidator {

	public void validate(Umowa umowa) throws ValidationException{
		if(umowa.getDataZawarcia().getTime() > umowa.getDataRozpoczecia().getTime()) {
			throw new ValidationException("Data zawarcia nie może być późniejsza niż data rozpoczęcia umowy");
		}
		if (umowa.getDataRozpoczecia().getTime() > umowa.getDataZakonczenia().getTime()){
			throw new ValidationException("Data rozpoczęcia nie może być późniejsza niż data zakończenia umowy");
		}
	}
}

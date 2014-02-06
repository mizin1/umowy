package pl.waw.mizinski.umowy.validation;

import pl.verbis.validators.pesel.BirthDateAndSex.Sex;
import pl.verbis.validators.pesel.PeselValidator;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.enums.Plec;

public class PracownikValidator {

	private static final int[] NIP_DIGIT_WEIGHTS = {6, 5, 7, 2, 3, 4, 5, 6, 7};
	
	private PeselValidator peselValidator = new PeselValidator();
	
	public void validate(Pracownik pracownik) throws ValidationException {
		validatePesel(pracownik);
		validateNip(pracownik);
	}

	private void validatePesel(Pracownik pracownik) throws ValidationException {
		if(pracownik.getPesel() !=null && !peselValidator.validatePeselFully(pracownik.getPesel(), pracownik.getDataUrodzenia(), getSex(pracownik))){
			throw new ValidationException("Nieporawny numer PESEL");
		}
	}

	private Sex getSex(Pracownik pracownik) {
		return pracownik.getPlec() == Plec.M ? Sex.M : Sex.F;
	}

	private void validateNip(Pracownik pracownik) throws ValidationException {
		if(pracownik.getNip() != null){
			String nip = pracownik.getNip();
			int checkSum = getCheckSum(nip);
			validateCheckSum(nip, checkSum);
		}

	}

	private void validateCheckSum(String nip, int checkSum) throws ValidationException {
		String stringLastDigit = String.valueOf(nip.charAt(9));
		int lastDigitFromNip = Integer.valueOf(stringLastDigit);
		int lastDigitFromCheckSum = checkSum%11;
		if (lastDigitFromNip != lastDigitFromCheckSum) {
			throw new ValidationException("Niepoprawny numer NIP");
		}
	}

	private int getCheckSum(String nip) {
		int ret = 0;
		for(int i=0; i<9; ++i){
			String stringDigit = String.valueOf(nip.charAt(i));
			int digit = Integer.valueOf(stringDigit);
			ret += digit*NIP_DIGIT_WEIGHTS[i];
		}
		return ret;
	}
}
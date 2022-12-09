package librarysystem.controls;

import javax.swing.JComponent;
import javax.swing.JTextField;

import business.Validator;

public class G8NumberInputVerifier extends G8EmptyInputVerifier {
    public G8NumberInputVerifier(String inputTitle, boolean allowToProceed) {
		super(inputTitle, allowToProceed);
	}
	@Override
    public boolean verify(JComponent input) {
		if(!super.verify(input))
        	return false;
        String text = ((JTextField) input).getText();
        boolean isValid = Validator.isValidNumber(text);
        return returnTrueIfIgnored("Value ["+ inputTitle +"] should be a valid Number!!!", isValid, input);                
    }	
}